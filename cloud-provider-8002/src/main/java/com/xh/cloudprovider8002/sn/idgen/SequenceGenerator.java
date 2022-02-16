package com.xh.cloudprovider8002.sn.idgen;

import com.xh.cloudprovider8002.sn.idgen.model.Sequence;
import org.springframework.lang.Nullable;

/**
 * @author xiaohong
 * @date 2022/2/16 10:35
 **/
public interface SequenceGenerator {


    Sequence nextSequence(@Nullable Object seqKey);


}
