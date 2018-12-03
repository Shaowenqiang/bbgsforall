package cn.com.hqep.templet.dao;

import cn.com.hqep.templet.model.tblBbgsWeightPercentModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 权重
 *
 * @author hq4
 * @date 2017年10月25日 下午04:50:08
 */
@Repository
public interface percentDao {
    /**
     * 新增权重
     *
     * @param model
     * @return
     */
    int save(tblBbgsWeightPercentModel model);

    /**
     * 删除权重
     *
     * @param id
     * @return
     */
    int delete(@Param("id") String id);

    /**
     * 查询全部
     */
    List<tblBbgsWeightPercentModel> queryAll(tblBbgsWeightPercentModel model);

    /**
     * 更新权重
     */
    int updateModel(tblBbgsWeightPercentModel model);
}
