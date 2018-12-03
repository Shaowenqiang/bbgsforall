package cn.com.hqep.templet.dao;

import cn.com.hqep.templet.model.tblBbgsExpertModel;
import cn.com.hqep.templet.model.tblBbgsTechnologyBusinessModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 技术商务评分模板导出
 *
 * @author hq4
 * @date 2017年10月23日 下午02:59:58
 */
@Repository
public interface scoreImportDao {
    /**
     * 根据专家组名称 id查询该专家组下的所有成员
     *
     * @param technologyGroupName 专家组名称
     * @return
     */
    List<tblBbgsExpertModel> queryExpertNames(@Param("groupId") String technologyGroupName);

    /**
     * 查出所有专家
     *
     * @return
     */
    List<tblBbgsExpertModel> getAllExpert();

    /**
     * 查出所有专家数量
     *
     * @return
     */
    List<tblBbgsExpertModel> getCount();

    /**
     * 保存分数
     *
     * @param model
     */
    void saveModel(tblBbgsTechnologyBusinessModel model);

    /**
     * 查询数量
     *
     * @param model
     * @return
     */
    int selectCount(tblBbgsTechnologyBusinessModel model);

    /**
     * 更新 总分
     *
     * @param model
     */
    void updateModel(tblBbgsTechnologyBusinessModel model);

    /**
     * 用于查询评分汇总
     */
    void queryScoreInfo();
}
