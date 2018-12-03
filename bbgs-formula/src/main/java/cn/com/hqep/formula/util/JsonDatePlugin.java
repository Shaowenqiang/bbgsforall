package cn.com.hqep.formula.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDatePlugin
        implements JsonValueProcessor {
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private DateFormat dateFormat;

    public JsonDatePlugin() {
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public JsonDatePlugin(String datePattern) {
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
        return value != null ? this.dateFormat.format((Date) value) : null;
    }
}
