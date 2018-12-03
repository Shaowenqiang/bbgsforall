package cn.com.hqep.formula.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class JsonUtil {
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final Object[] types = {Class.class, String.class, Timestamp.class, java.sql.Date.class, java.util.Date.class, Integer.TYPE, Character.TYPE, Boolean.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Integer.class, Character.class, Boolean.class, Long.class, Float.class, Double.class, BigInteger.class, BigDecimal.class};

    private static JsonConfig initJsonConfig(String date) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonTimestampPlugin(date));
        jsonConfig.registerJsonValueProcessor(java.sql.Date.class, new JsonDatePlugin());
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDatePlugin());
        return jsonConfig;
    }

    private static boolean isSupportType(Object obj) {
        if ((obj == null) || ("".equals(obj)))
            return false;
        try {
            Object[] objs = BeanUtils.describe(obj).keySet().toArray();
            for (int i = 0; i < objs.length; i++) {
                PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(obj, objs[i].toString());
                if (!isSupportClass(pd.getPropertyType()))
                    throw new JSONException("Unsupported type");
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static boolean isSupportClass(Class clazz) {
        for (int i = 0; i < types.length; i++) {
            if (clazz == types[i])
                return true;
        }
        return false;
    }

    public static Object beanToJson(Object bean) {
        if ((bean == null) || ("".equals(bean)) || (!isSupportType(bean)))
            return null;
        try {
            return JSONObject.fromObject(bean, initJsonConfig(DEFAULT_DATE_PATTERN));
        } catch (JSONException je) {
            throw je;
        }

    }

    public static Object beanToJson(Object bean, String date) {
        if ((bean == null) || ("".equals(bean)) || (!isSupportType(bean)))
            return null;
        try {
            return JSONObject.fromObject(bean, initJsonConfig(date));
        } catch (JSONException je) {
            throw je;
        }
    }

    public static Object jsonToBean(Object data, Object bean) {
        if ((data == null) || (!isSupportType(bean)))
            return null;
        try {
            Map map = (Map) JSONObject.toBean(JSONObject.fromObject(data, initJsonConfig(DEFAULT_DATE_PATTERN)), Map.class);
            return mapToBean(map, bean);
        } catch (JSONException je) {
            throw je;
        }

    }

    public static Map jsonToMap(Object data) {
        if (data == null)
            return null;
        try {
            return (Map) JSONObject.toBean(JSONObject.fromObject(data, initJsonConfig(DEFAULT_DATE_PATTERN)), Map.class);
        } catch (JSONException je) {
            throw je;
        }

    }

    private static Object mapToBean(Map map, Object bean) {
        if ((map == null) || (map.isEmpty()))
            return bean;
        Object[] objs = map.keySet().toArray();
        String key = null;
        Object[] parameter = {new Object()};
        try {
            for (int i = 0; i < objs.length; i++) {
                key = (String) objs[i];
                PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(bean, key);
                if (pd == null)
                    continue;
                Method writeMethod = pd.getWriteMethod();
                writeMethod.setAccessible(true);
                if (writeMethod == null)
                    continue;
                Object param = map.get(key);
                if ((param instanceof JSONObject)) {
                    param = new JSONObject().get(key);
                }
                parameter[0] = formatTransform(param, pd.getPropertyType());
                writeMethod.invoke(bean, parameter);
            }
        } catch (Exception e) {
            return null;
        }
        return bean;
    }

    private static Object formatTransform(Object obj, Class clazz) {
        if ((obj == null) || ("".equals(obj))) {
            return null;
        }
        if ((clazz == null) || ("".equals(clazz))) {
            clazz = obj.getClass();
        }
        try {
            if (clazz == Timestamp.class) {
                return Timestamp.valueOf(String.valueOf(obj));
            }
            if (clazz == java.sql.Date.class) {
                java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(obj));
                return new java.sql.Date(utilDate.getTime());
            }

            if (clazz == java.util.Date.class) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(String.valueOf(obj));
            }
            if ((clazz == Character.TYPE) || (clazz == Character.class)) {
                return new Character(String.valueOf(obj).toCharArray()[0]);
            }
            if ((clazz == Float.TYPE) || (clazz == Float.class)) {
                return new Float(Float.parseFloat(String.valueOf(obj)));
            }
            if ((clazz == Long.TYPE) || (clazz == Long.class)) {
                return new Long(Long.parseLong(String.valueOf(obj)));
            }
            if (clazz == BigInteger.class) {
                return new BigInteger(String.valueOf(obj));
            }
            if (clazz == BigDecimal.class)
                return new BigDecimal(String.valueOf(obj));
        } catch (ParseException pe) {
            return null;
        } catch (Exception e) {
            return null;
        }
        return obj;
    }
}
