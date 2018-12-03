package cn.com.hqep.templet.service;


import cn.com.hqep.templet.model.tblBbgsTemplateModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 模板 Service
 *
 * @author swq
 * @date 2017年10月13日 下午07:27:13
 */
public interface templetService {
    /**
     * 新增模板
     *
     * @param
     * @return
     */
    Map saveTemplet(tblBbgsTemplateModel templet);

    /**
     * 查询 条件过滤后的所有模板
     *
     * @param templet
     * @return
     */
    List<tblBbgsTemplateModel> listAll(tblBbgsTemplateModel templet);

    /**
     * 通过id查询实体
     *
     * @param id
     * @return
     */
    tblBbgsTemplateModel queryModelById(@Param("id") String id);

    /**
     * 删除某个模板
     *
     * @param templet
     * @return
     */
    boolean delTemplet(tblBbgsTemplateModel templet);

    /**
     * 查询 条件过滤后的 记录总数
     *
     * @param templet
     * @return
     */
    int queryCount(tblBbgsTemplateModel templet);

    /**
     * 设置默认模板
     *
     * @param id
     * @return
     */
    boolean setDefault(String id);

    /**
     * 查看子模板Excel文件详情
     *
     * @param id
     * @param sourcePath
     * @return
     */
    Map viewDetail(String id, String sourcePath);
}
