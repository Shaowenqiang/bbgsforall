package cn.com.hqep.templet.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date 时间处理 工具类
 *
 * @author hq4
 * @date 2017年10月13日 下午12:57:19
 */
public class DateUtil {
    /**
     * 返回14位数 数字 的时间格式
     *
     * @return
     */
    public static Integer time14() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMddhhmmss");
        Date date = new Date();
        return Integer.valueOf(dateFormater.format(date));
    }

    /**
     * 返回8位数 数字 的时间格式
     *
     * @return
     */
    public static Integer time8() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return Integer.valueOf(dateFormater.format(date));
    }
}
