package com.xh.cloudprovider8002.sn.designresource;

import com.xh.cloudprovider8002.sn.core.SequenceDetailProvider;
import com.xh.cloudprovider8002.sn.core.model.Sequence;
import com.xh.cloudprovider8002.sn.core.service.SequenceDetailService;
import com.xh.cloudprovider8002.sn.core.BizTypeProvider;
import com.xh.cloudprovider8002.sn.core.generator.MysqlDistributedSequenceGenerator;
import com.xh.cloudprovider8002.sn.core.model.SequenceDetail;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author xiaohong
 * @date 2022/2/17 10:53
 **/
@Service
public class MysqlDesignResourceSnGenerator extends MysqlDistributedSequenceGenerator implements BizTypeProvider {

    private static final int DESIGN_RESOURCE_DATA_TYPE = 22;
    private static final DateTimeFormatter DATE_PATTERN_YYMMDD = DateTimeFormatter.ofPattern("yyMMdd");


    private final RedisTemplate<String, String> redisTemplate;

    public MysqlDesignResourceSnGenerator(SequenceDetailService sequenceDetailService,
                                          RedisTemplate<String, String> redisTemplate) {
        super(sequenceDetailService);
        this.redisTemplate = redisTemplate;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation = Isolation.READ_COMMITTED)
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
