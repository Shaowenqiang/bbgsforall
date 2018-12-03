package cn.com.hqep.templet.action;

import cn.com.hqep.templet.common.jsonMsg;
import cn.com.hqep.templet.model.tblBbgsWeightPercentModel;
import cn.com.hqep.templet.service.percentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 权重页面Controller
 *
 * @author swq
 * @date 2017年10月25日 下午04:36:08
 */
@Controller
@RequestMapping("/percent")
public class percentAction {
    @Autowired
    percentService service;

    /**
     * 新增一个权重信息
     *
     * @param js 技术权重
     * @param sw 商务权重
     * @param jg 价格权重
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveOne")
    public Map saveOne(String js, String sw, String jg) {
        StringBuffer sb = new StringBuffer();
        sb.append("技术：").append(js).append("%，");
        sb.append("商务：").append(sw).append("%，");
        sb.append("价格：").append(jg).append("%");
        return service.saveOne(sb.toString());
    }

    /**
     * 删除一个权重
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(String id) {
        return jsonMsg.booleanToMap(service.delete(id));
    }

    /**
     * 查询所有权重信息，
     * 显示到表格中
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryAll")
    public List<tblBbgsWeightPercentModel> queryAll() {
        tblBbgsWeightPercentModel model = new tblBbgsWeightPercentModel();
        return service.queryAll(model);
    }

    /**
     * 将一个权重设置为默认权重
     * 当它为默认权重时其它所有权重都为不默认
     * 即只能有一个权重为默认权重
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("setDefault")
    public Map setDefault(String id) {
        return jsonMsg.booleanToMap(service.setDefalut(id));
    }
}
