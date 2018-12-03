package cn.com.hqep.bidder.action;

import cn.com.hqep.bidder.common.exportSortExcelUtil;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierModel;
import cn.com.hqep.bidder.service.bidderService;
import cn.com.hqep.bidder.service.comprehensiveSortService;
import cn.com.hqep.bidder.service.conductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by sssJL on 2017-10-23.
 * 处理综合排序的Controller类
 */
@Controller
@RequestMapping("/sort")
public class comprehensiveSortAction {

    @Autowired
    private bidderService service; //模糊查询Service

    @Autowired
    private comprehensiveSortService sortService;   //综合排序Service


    /***
     * 模糊查询
     * 根据条件进行模糊查询 符合条件的供应商信息
     * @param model 存储查询条件的实体 标段简称 和 供应商名称
     * @return 实体集合
     */
    @ResponseBody
    @RequestMapping("queryList")
    public List<tblBbgsPackageSupplierModel> queryList(tblBbgsPackageSupplierModel model){
        List<tblBbgsPackageSupplierModel> list = null;
        try {
            list = service.queryDataList(model);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }


    /**
     * 导出excel
     * 导出评分汇总表
     * @param response HttpServletResponse
     */
    @RequestMapping("exportSortExcel")
    public void exportSortExcel(HttpServletResponse response,tblBbgsPackageSupplierModel model){
        try {
            List<tblBbgsPackageSupplierModel> list = sortService.querySortList(model);
            String[] head = {"标段简称","供应商名称",
                    "技术得分","报价得分","商务得分","总分"
                    , "投标报价（万元）","排名"
           };
            new exportSortExcelUtil().exportSortExcel("综合排序","评分汇总表",list, response);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }


    /**
     * 计算综合排名方法
     * @return 计算结果成功 或者 失败
     */
    @ResponseBody
    @RequestMapping("/computeComprehensiveSort")
    public Map computeComprehensiveSort(){
        Map map;
        try {
            map = sortService.updateSortDataById();
        }catch (Exception exp){
            exp.printStackTrace();
            map = new HashMap();
            map.put("state","false");
            map.put("msg","数据异常！");
            return map;
        }
        return map;
    }


    /***
     * 导出excel：
     * 导出平均分汇总 和 综合排名评分汇总表
     * @return 文件zip 名称
     */
    @ResponseBody
    @RequestMapping("/exportAveragePriveExcel")
    public Map exportAveragePriveExcel(){
        Map result = new HashMap();
        try {
            result = sortService.queryScoreGroupList();
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return result;
    }


    /**
     * 查询：
     * 处理计算综合排名后，
     * 当同一标段简称下
     * 第一名存在并列时
     * 查询结果存入结果List返回
     * @return 实体集合
     */
    @ResponseBody
    @RequestMapping("/querySideFirstData")
    public  List<tblBbgsPackageSupplierModel> querySideFirstData(tblBbgsPackageSupplierModel paramModel){
        //创建返回值List
        List<tblBbgsPackageSupplierModel> resultList = new ArrayList<tblBbgsPackageSupplierModel>();
        try {
            //得到有序的 操作 List
            paramModel.setOffer("无");
            paramModel.setFlagBit("0");
            paramModel.setFlagInvalid("0");
            paramModel.setFlagBidInvalid("0");
            paramModel.setSort("非空");
            List<tblBbgsPackageSupplierModel> packageSupplierModelList = sortService.querySortList(paramModel);
            //使用map<String,Lsit>将数据按照标段简称进行分组
            Map<String,List<tblBbgsPackageSupplierModel>> modelListMap = new LinkedHashMap<String, List<tblBbgsPackageSupplierModel>>();
            //分组操作
            for (tblBbgsPackageSupplierModel model : packageSupplierModelList){
                if (modelListMap.keySet().contains(model.getBidAbbreviaion())){
                    modelListMap.get(model.getBidAbbreviaion()).add(model);
                }else {
                    modelListMap.put(model.getBidAbbreviaion(),new ArrayList<tblBbgsPackageSupplierModel>());
                    modelListMap.get(model.getBidAbbreviaion()).add(model);
                }
            }
            //将有并列第一的实体存入返回值List
            for (String mapKeyName : modelListMap.keySet()){
                packageSupplierModelList = modelListMap.get(mapKeyName);
                if (packageSupplierModelList.size()>1){
                    int index = 0;
                    for (int i=0;i<packageSupplierModelList.size();i++){
                        if (packageSupplierModelList.get(i).getSort() != null
                                && Integer.parseInt(packageSupplierModelList.get(i).getSort()) == 1){
                            index++;
                        }
                    }
                    if (index > 1){
                        for (int i=0;i<packageSupplierModelList.size();i++){
                            if (packageSupplierModelList.get(i).getSort() != null
                                    && Integer.parseInt(packageSupplierModelList.get(i).getSort()) == 1){
                                resultList.add(packageSupplierModelList.get(i));
                            }
                        }
                    }

                }
            }
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return resultList;
    }


    /***
     * 更新：
     * 处理计算结果
     * @param model 条件实体
     * @return 处理结果
     */
    @ResponseBody
    @RequestMapping("/dealSideFirstData")
    public boolean dealSideFirstData(tblBbgsPackageSupplierModel model){
        boolean flag = true;
        try {
            flag =  sortService.dealSideFirstData(model);
        }catch (Exception exp){
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }
}
