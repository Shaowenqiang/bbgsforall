package cn.com.hqep.templet.service.impl;

import cn.com.hqep.templet.common.jsonMsg;
import cn.com.hqep.templet.dao.percentDao;
import cn.com.hqep.templet.model.tblBbgsWeightPercentModel;
import cn.com.hqep.templet.service.percentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 权重实现类
 *
 * @author hq4
 * @date 2017年10月25日 下午04:44:07
 */
@Repository
public class percentServiceImpl implements percentService {
    @Autowired
    percentDao dao;

    @Override
    public Map saveOne(String str) {
        tblBbgsWeightPercentModel model = new tblBbgsWeightPercentModel();
        model.setWeightName(str);
        model.setWeightContent(str);
        model.setIsDefault("N");
        if (dao.queryAll(model).size() > 0) {
            return jsonMsg.toJosn("error", "存在相同权重");
        }
        model.setId(UUID.randomUUID().toString().replace("-", ""));

        return jsonMsg.booleanToMap(dao.save(model) > 0);
    }

    @Override
    public boolean delete(String id) {
        return dao.delete(id) > 0;
    }

    @Override
    public List<tblBbgsWeightPercentModel> queryAll(tblBbgsWeightPercentModel model) {
        return dao.queryAll(model);
    }

    @Override
    public boolean setDefalut(String id) {
        tblBbgsWeightPercentModel model = new tblBbgsWeightPercentModel();
        model.setIsDefault("N");
        dao.updateModel(model);
        model.setId(id);
        model.setIsDefault("Y");
        dao.updateModel(model);
        return true;
    }
}
