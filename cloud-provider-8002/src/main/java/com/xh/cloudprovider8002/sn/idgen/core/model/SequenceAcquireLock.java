package com.xh.cloudprovider8002.sn.idgen.core.model;

import lombok.Data;

/**
 * @author xiaohong
 * @date 2022/2/16 12:24
 **/
@Data
public class SequenceAcquireLock {

    private boolean locked;

    private SequenceDetail sequenceDetail;

    public SequenceAcquireLock() {
    }

    public SequenceAcquireLock(boolean locked, SequenceDetail sequenceDetail) {
        this.locked = locked;
        this.sequenceDetail = sequenceDetail;
    }
}
