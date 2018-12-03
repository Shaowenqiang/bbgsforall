package cn.com.hqep.templet.dao;

import cn.com.hqep.templet.model.tblBbgsTemplateModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 操作 模板表的Dao
 *
 * @author shaowenqiang
 * @date 2017年09月26日 下午03:24:15
 */
@Repository
public interface templetDao {
    /**
     * 保存一个模板
     *
     * @param templet 模板类型
     * @return
     */
    int saveTemplet(tblBbgsTemplateModel templet);

    /**
     * 查询所有符合条件模板
     *
     * @param templet
     * @return
     */
    List<tblBbgsTemplateModel> listAll(tblBbgsTemplateModel templet);

    /**
     * 删除一个模板
     *
     * @param templet
     * @return
     */
    int delTemplet(tblBbgsTemplateModel templet);

    /**
     * 返回子模板实体
     *
     * @param id 子模板id
     * @return
     */
    tblBbgsTemplateModel queryTempletModelById(@Param("id") String id);

    /**
     * 查询模板表 记录数
     *
     * @param templet
     * @return
     */
    int queryCount(tblBbgsTemplateModel templet);

    /**
     * 更新为默认模板
     *
     * @param model
     * @return
     */
    int updateModel(tblBbgsTemplateModel model);

    /**
     * 搜索实体中属性不为空的  返回模板实体
     *
     * @param
     * @return
     */
    List<tblBbgsTemplateModel> queryTempletModel(tblBbgsTemplateModel model);
}
