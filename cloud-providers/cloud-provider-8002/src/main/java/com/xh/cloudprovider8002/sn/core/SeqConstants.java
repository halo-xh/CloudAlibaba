package com.xh.cloudprovider8002.sn.core;

/**
 * @author xiaohong
 * @date 2022/2/17 13:21
 **/
public interface SeqConstants {


    String REDIS_KEY_MAX_VAL_PREFIX = "ds:sn:max:";

    String REDIS_KEY_SEQ_VAL_PREFIX = "ds:sn:seq:";

    String REDIS_KEY_SEQ_INIT_PREFIX = "ds:sn:init:";

    String MYSQL_LOCK_BIZ_TYPE ="just used as lock";

}
