package cn.com.hqep.templet.service;

import cn.com.hqep.templet.model.tblBbgsBidInformationModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 基础信息导入 （第一次信息输入）
 *
 * @author hq4
 * @date 2017年10月10日 下午02:58:23
 */
public interface basicInfoService {
    /**
     * 第一次基础信息录入
     *
     * @param file     Excel文件
     * @param category 类别 物资 非物资
     * @return
     */
    Map<String, String> saveData(MultipartFile file, String category);

    /**
     * 导入投标报价方法
     *说明：基础信息后面加一列报价
     * @param file     Excel文件
     * @return
     */
    public Map<String, String> saveBaseOffer(MultipartFile file);
    /**
     * 清空当前批次所有数据
     *
     * @return
     */
    boolean delAllData();

    /**
     * 判断是否存在批次数据
     *
     * @return boolean true 存在数据 false 不存在数据可以执行导入操作
     */
    boolean existData();

    /**
     * 查询最新批次名称
     */
    Map queryName();

    /**
     * 查询 标段-包信息
     *
     * @return
     */
    List queryAllBid();

    /**
     * 查询全部
     */
    List<tblBbgsBidInformationModel> queryAllByModel(tblBbgsBidInformationModel model);

    /**
     * 查询 标段-包信息 操作记录
     *
     * @return
     */
    List<tblBbgsBidInformationModel> queryAllBidRecord(tblBbgsBidInformationModel model);

    /**
     * 更新一个或多个标段包的模板信息
     *
     * @param ids
     * @param data
     */
    Map saveBidInfos(String ids, tblBbgsBidInformationModel data);

    /**
     * 更新一个或多个标段包的模板信息
     *
     * @param id
     */
    boolean updateBidInfoFlag(String id);


    /**
     * 导出阅标记录
     *
     * @return
     */
    Map exportBidRecordes(String sourcePath,String vbsPath);
    /**
     * 导出投标人清单
     *
     * @return
     */
    Map exportBidderList(String sourcePath);

    List queryPackageByBid(String bid);

    /**
     * 保存专家信息
     * @param m
     * @return
     */
    Map saveExpert(Map<String,List<String>> m);

    /**
     * 删除所有专家组、专家
     * @return
     */
    Map delAllExpert();

    /**
     * 查看是否能导入专家信息
     * @return
     */
    boolean viewIsImport();

    /**
     * 根据流程id查询此id之前是否有未完成节点
     * @param id
     * @return
     */
    String queryFlowState(String id);

    Map saveDataForE(MultipartFile[] files, String category);

    List queryPackageByBid_new(String bids);

    /**
     * 查询标段号和标段名称集合（用于绑定标段信息）
      * @return List
     */
    List getBidNameList();

//    boolean makeScoreExcel(String sourcePath);
}
