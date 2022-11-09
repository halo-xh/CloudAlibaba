package com.xh.cloudprovider8002.sn.designresource;

import com.xh.cloudprovider8002.sn.core.model.Sequence;
import com.xh.cloudprovider8002.sn.core.BizTypeProvider;
import com.xh.cloudprovider8002.sn.core.SequenceDetailProvider;
import com.xh.cloudprovider8002.sn.core.generator.RedisDistributedSequenceGenerator;
import com.xh.cloudprovider8002.sn.core.model.SequenceDetail;
import com.xh.cloudprovider8002.sn.core.service.SequenceDetailService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author xiaohong
 * @date 2022/2/17 10:53
 **/
@Service
public class RedisDesignResourceSnGenerator extends RedisDistributedSequenceGenerator implements BizTypeProvider {

    private static final int DESIGN_RESOURCE_DATA_TYPE = 22;
    private static final DateTimeFormatter DATE_PATTERN_YYMMDD = DateTimeFormatter.ofPattern("yyMMdd");

    public RedisDesignResourceSnGenerator(SequenceDetailService sequenceDetailService,
                                          RedisTemplate<String, String> redisTemplate) {
        super(redisTemplate, sequenceDetailService);
    }


    public String getSn(String categoryPrefix, boolean free) {
        String bizType = getBizType(categoryPrefix);
        Sequence sequence = nextSequence(bizType);
        if (sequence != null) {
            DesignResourceSequence designResourceSequence = new DesignResourceSequence(sequence, free ? "f" : "m");
            return designResourceSequence.result();
        }
        return null;
    }

    /**
     * 生成设计资源编号业务类型
     * e.g : ASDQ220217
     *
     * @param seqKey 类型配置的前缀
     * @return 生成对应的seq头
     */
    @Override
    public String getBizType(Object seqKey) {
        String categoryPrefix = (String) seqKey;
        String datePattern = LocalDate.now().format(DATE_PATTERN_YYMMDD);
        return categoryPrefix + datePattern;
    }

    private SequenceDetail buildSequenceDetails(String bizType) {
        SequenceDetail sequenceDetail = new SequenceDetail();
        sequenceDetail.setBizType(bizType);
        sequenceDetail.setMaxVal(getMax());
        sequenceDetail.setStep(getStep());
        return sequenceDetail;
    }

    private Integer getStep() {
        return 1;
    }

    private Long getMax() {
        return 9999L;
    }

    @Override
    protected SequenceDetailProvider getSequenceDetailProvider() {
        return new DesignResourceSequenceDetailProvider();
    }

    class DesignResourceSequenceDetailProvider implements SequenceDetailProvider {

        @Override
        public SequenceDetail get(Object seqKey) {
            return buildSequenceDetails((String) seqKey);
        }
    }


}
