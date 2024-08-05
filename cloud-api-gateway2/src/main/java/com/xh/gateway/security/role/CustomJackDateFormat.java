package com.xh.gateway.security.role;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@Slf4j
public class CustomJackDateFormat extends DateFormat {

    private DateFormat dateFormat;

    public CustomJackDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
        // 时间转换时，默认相差 8 小时，所以这里设置了时区
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    }


    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return dateFormat.format(date, toAppendTo, fieldPosition);

    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = format.parse(source, pos);
        } catch (Exception e) {
            log.error("自定义转换Date类型异常", e);
            date = dateFormat.parse(source, pos);
        }
        return date;
    }

    // 这里装饰clone方法的原因是因为clone方法在jackson中也有用到
    @Override
    public Object clone() {
        Object format = dateFormat.clone();
        return new CustomJackDateFormat((DateFormat) format);
    }
}