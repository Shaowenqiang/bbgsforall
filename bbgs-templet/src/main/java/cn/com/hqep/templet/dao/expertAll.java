package cn.com.hqep.templet.dao;

import cn.com.hqep.templet.model.tblBbgsExpertGroupModel;
import cn.com.hqep.templet.model.tblBbgsExpertModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * 专家、专家组插入
 * @author swq
 * @date
 */
@Repository
public interface expertAll {
    /**
     * 查询 所有专家组
     * @return
     * @param check
     */
    List<tblBbgsExpertGroupModel> queryExpertGroup(tblBbgsExpertGroupModel check);

    /**
     * 新增一个专家组
     * @param model
     */
    void saveExpertGroup(tblBbgsExpertGroupModel model);

    void saveExpert(tblBbgsExpertModel expertModel);

    /**
     * 删除所有专家分组和专家
     */
    int delAllExpert();

    /**
     * 查看专家数量
     * @return
     */
    int queryExpertCount();
}
