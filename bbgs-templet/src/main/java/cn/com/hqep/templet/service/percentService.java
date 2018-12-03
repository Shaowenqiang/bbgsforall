package cn.com.hqep.templet.service;

import cn.com.hqep.templet.model.tblBbgsWeightPercentModel;

import java.util.List;
import java.util.Map;

/**
 * 权重操作
 *
 * @author hq4
 * @date 2017年10月25日 下午04:38:11
 */
public interface percentService {
    /**
     * 新增一条权重信息
     *
     * @param str
     * @return
     */
    Map saveOne(String str);

    /**
     * 删除一条权重记录
     *
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 查询所有权重记录
     *
     * @param model
     * @return
     */
    List<tblBbgsWeightPercentModel> queryAll(tblBbgsWeightPercentModel model);

    /**
     * 将一个权重设置为默认权重
     *
     * @param id
     * @return
     */
    boolean setDefalut(String id);
}
