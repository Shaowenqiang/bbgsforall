package cn.com.hqep.templet.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.*;

/**
 * json数据
 *
 * @author hq4
 * @date 2017年10月09日 下午08:08:31
 */
public class jsonMsg {
    /**
     * 消息封装
     * true ==>> success false ==>> erro
     *
     * @param b
     * @return
     */
    public static Map booleanToMap(boolean b) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("state", "error");
        if (b) {
            m.put("state", "success");
        }
        return m;
    }

    /**
     * 消息封装
     *
     * @param state
     * @param object
     * @return
     */
    public static Map toJosn(String state, Object object) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("state", state);
        m.put("msg", object);
        return m;
    }

    /**
     * 统一确认消息封装
     *
     * @param state    状态 boolean true成功 false 失败
     * @param trueMsg  成功信息
     * @param falseMsg 失败信息
     * @return
     */
    public static Map<String, Object> msgByBoolean(boolean state, Object trueMsg, Object falseMsg) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (state) {
            resultMap.put("state", "true");
            if (null != trueMsg) {
                resultMap.put("resultObject", trueMsg);
            }
        } else {
            resultMap.put("result", "false");
            if (null != falseMsg) {
                resultMap.put("resultObject", falseMsg);
            }
        }
        return resultMap;
    }

    /**
     * 将List封装为EasyUI列表
     *
     * @param list
     * @return
     */
    public static Map<String, Object> msgByListForEasyuiTable(List list) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        if (list instanceof Page) {
            PageInfo pageInfo = new PageInfo<>(list);
            resultMap.put("total", pageInfo.getTotal());
            resultMap.put("rows", pageInfo.getList() == null ? new ArrayList<>() : pageInfo.getList());
        } else {
            resultMap.put("total", list.size());
            resultMap.put("rows", list == null ? new ArrayList<>() : list);
        }
        return resultMap;
    }
}
