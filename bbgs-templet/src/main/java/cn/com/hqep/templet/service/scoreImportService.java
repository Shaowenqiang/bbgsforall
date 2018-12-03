package cn.com.hqep.templet.service;

import cn.com.hqep.templet.model.tblBbgsExpertModel;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 技术商务评分导入导出Service
 *
 * @author hq4
 * @date 2017年10月17日 上午09:23:27
 */
public interface scoreImportService {
    /**
     * 保存多个EXCEL文件中内容
     *
     * @param
     * @return
     */
    List save(Map<String, File> map);

    /**
     * 查询厂商列表
     *
     * @param bidAbbreviaion
     * @return
     */
    List<String> querySupplierName(String bidAbbreviaion);

    /**
     * 查询专家组内成员名字
     *
     * @param technologyGroupName
     * @return
     */
    List<String> queryExpertNames(String technologyGroupName);

    /**
     * 查询所有专家
     *
     * @return
     */
    List<tblBbgsExpertModel> getAllExpert();

    /**
     * 查询专家组内专家数量
     *
     * @return
     */
    List<tblBbgsExpertModel> getCount();

    /**
     * 用于查询评分汇总信息
     */
    void queryScoreInfo();
}
