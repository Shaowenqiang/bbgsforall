package cn.com.hqep.templet.dao;

import cn.com.hqep.templet.model.tblBbgsFlowStateModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 招标项目流程管理
 */
@Repository
public interface flowStateDao {
    /**
     * 查询进行到了哪一个节点及提示信息
     * @return
     */
    tblBbgsFlowStateModel queryMinIdByState();

    /**
     * 通过id 更新流程状态
     * id 为 null 时 更新所有记录
     * @param id
     * @param state
     */
    void updateStateById(@Param("id") String id, @Param("state") String state);
}
