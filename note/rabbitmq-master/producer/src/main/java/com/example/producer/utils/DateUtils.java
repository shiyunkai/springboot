package com.example.producer.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/23 13:32
 * @Description:
 */
public class DateUtils {
    /* * @Auther: shiyunkai
     * @Description: 日期转换成特定的格式
     * @Param: [date, dateFormat]
     * @Date:  13:33 2019/3/23
     * @return: java.lang.String
     **/
    public static String formatDateByPattern(Date date, String dateFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /* * @Auther: shiyunkai
     * @Description: 将输入的日期转换成cron表达式
     * @Param: [date]
     * @Date:  13:33 2019/3/23
     * @return: cron表达式
     **/
    public static String DateToCron(Date  date){
        String dateFormat="ss mm HH dd MM ? yyyy";
        return formatDateByPattern(date, dateFormat);
    }
}
