package com.example.id;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;


@Component
public class IdCreator implements DisposableBean, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(IdCreator.class);

    @Autowired
    private IdCreatorService idCreatorService;

    @Autowired
    private IdCreatorProperty idCreatorProperty;

    private final AtomicBoolean initFlag = new AtomicBoolean(false);

    private String sectionId;
    private String workerId;
    private String firstPlace;
    private String offset;
    private long lastTime;
    private int millionMax = 0;
    private long shortLastTime;
    private int shortWorkerLength2 = 3;
    private int shortMaxPerSec;
    private int shortMaxLength = 4;
    private String shortOffset;
    private static int max = 0;

    private static final String zeroStr = "0";



    @Override
    public void afterPropertiesSet() throws Exception {
        if (initFlag.compareAndSet(false, true)) {
            workerId = idCreatorService.getWorkerId();
            idCreatorService.initRedisKey(workerId);

            offset = Optional.ofNullable(idCreatorService.getOffset(false)).orElse(zeroStr);
            offset = withZero(offset, 4);

            sectionId = idCreatorProperty.getSectionId();
            firstPlace = idCreatorProperty.getFirstPlace();
            if (!StringUtils.hasText(firstPlace) || Long.parseLong(firstPlace) < 1L) {
                firstPlace = "1";
            }

            String longLastTime = idCreatorService.getLastTime(false);
            this.lastTime = longLastTime == null ? 0L : Long.parseLong(longLastTime);

            String sLastTime = idCreatorService.getLastTime(true);
            shortLastTime = (sLastTime == null) ? 0L : Long.parseLong(sLastTime);

            shortOffset = Optional.ofNullable(idCreatorService.getOffset(true)).orElse(zeroStr);
        }
    }


    public void destroy() {
        idCreatorService.destroy();
    }

    public synchronized String getUUID() {
        while (calculate(false, false) != 0) {
        }
        return firstPlace + withZero(String.valueOf(lastTime), 14) + withZero(sectionId, 2) + withZero(workerId, 4) + withZero(Long.toString(millionMax), 4) + offset;
    }

    public synchronized Long getShortID() {
        long startTime = System.currentTimeMillis();

        do {
            if (calculateShort()) {
                logger.info("create id with time {} workId {} shortMaxPerSec {}", shortLastTime, workerId, shortMaxPerSec);
                long id = Long.parseLong(shortLastTime + withZero(workerId, shortWorkerLength2) + withZero(Integer.toString(shortMaxPerSec), shortMaxLength));
                if (!"0".equals(shortOffset)) {
                    id = Long.parseLong(id + shortOffset);
                }
                return id;
            }
        } while (System.currentTimeMillis() - startTime <= 5000L);

        throw new RuntimeException("fetch id fail");
    }

    private boolean calculateShort() {
        long currentTime = getSecTime(System.currentTimeMillis());
        if (currentTime > shortLastTime) {
            shortMaxPerSec = 0;
            idCreatorService.updateLastTime(currentTime, true);
        } else if (currentTime < shortLastTime) {
            shortMaxPerSec = 0;
            moveShortOffset();
        } else {
            if (9999 == shortMaxPerSec) {
                return false;
            }

            ++shortMaxPerSec;
        }

        shortLastTime = currentTime;
        return true;
    }

    private void moveShortOffset() {
        shortOffset = String.valueOf(Integer.parseInt(shortOffset) + 1);
        idCreatorService.updateOffset(shortOffset, true);
    }

    private long getSecTime(long millisTime) {
        return Long.parseLong(String.valueOf(millisTime).substring(0, 10));
    }

    private int calculate(boolean shortFlag, boolean testFlag) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > lastTime) {
            millionMax = 0;
            if (!shortFlag && !testFlag) {
                idCreatorService.updateLastTime(currentTimeMillis, false);
            }
        } else if (!shortFlag && currentTimeMillis < lastTime) {
            millionMax = 0;
            if (!testFlag) {
                moveOffset();
            }
        } else {
            if (millionMax == 9999) {
                return 1;
            }
            ++millionMax;
        }

        if (max < millionMax) {
            max = millionMax;
        }

        lastTime = currentTimeMillis;
        return 0;
    }

    private void moveOffset() {
        offset = String.valueOf(Integer.parseInt(offset) + 1);
        idCreatorService.updateOffset(offset, false);
    }

    private static String withZero(String number, int totalLength) {
        if (number.length() >= totalLength) {
            return number;
        } else {
            int left = totalLength - number.length();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < left; ++i) {
                sb.append("0");
            }

            sb.append(number);
            return sb.toString();
        }
    }


}

