package cn.com.hqep.formula.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonTimestampPlugin implements JsonValueProcessor {

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private DateFormat dateFormat;

    public JsonTimestampPlugin() {
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public JsonTimestampPlugin(String datePattern) {
        try {
            this.dateFormat = new SimpleDateFormat(datePattern);
        } catch (Exception ex) {
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value, jsonConfig);
    }

    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        return process(value, jsonConfig);
    }

    private Object process(Object value, JsonConfig jsonConfig) {
        if (value == null)
            return null;
        Calendar c = Calendar.getInstance();
        c.setTime((Date) value);
        return this.dateFormat.format(c.getTime());
    }
}

