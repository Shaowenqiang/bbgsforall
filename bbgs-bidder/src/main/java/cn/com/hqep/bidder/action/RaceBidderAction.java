package cn.com.hqep.bidder.action;

import cn.com.hqep.bidder.model.*;
import cn.com.hqep.bidder.service.RaceBidderService;
import cn.com.hqep.bidder.service.RaceBidderTendererService;
import cn.com.hqep.bidder.service.RaceConductService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Created by sssJL on 2017-10-12.
 * 处理唱标报价的Controller类
 */
@Controller
@RequestMapping("/RaceBidder")
/**
 * Created by HQSpring on 2018-04-08.
 */
public class RaceBidderAction {

    @Autowired
    RaceBidderService service;  //查询唱标报价Service

    @Autowired
    RaceBidderTendererService tendererService; //导出唱标报价模板Service

    @Autowired
    RaceConductService RaceConductService;
    /**
     * 导入唱标报价的Excel
     * @return 导入结果反馈 存入map集合返回
     */
    @ResponseBody
    @RequestMapping("/importExcel")
    public Map importExcel(@RequestParam("file")MultipartFile file) {
        Map map = new HashMap();
        try {
            map = service.saveBid(file);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return map;
    }

    /***
     * 查询：
     * 根据参数 标段简称 得到所有对应供应商 返回前台
     * @param model 带参数的实体
     * @return 实体集合
     */
    @ResponseBody
    @RequestMapping("/queryPriceListByName")
    public List<tblBbgsPriceInformationRaceModel> queryPriceListByName(tblBbgsPriceInformationRaceModel model){
        List<tblBbgsPriceInformationRaceModel> list = null;
        try {
            list = service.queryPriceListByName(model);
        }catch (Exception exp){
            exp.printStackTrace();
            list = null;
        }
        return list;
    }


    /***
     * 查询使用
     * 根据条件进行模糊查询 符合条件的供应商信息
     * @param model 存储查询条件的实体 标段简称和供应商
     * @return 实体集合
     */
    @ResponseBody
    @RequestMapping("queryList")
    public List<tblBbgsPackageSupplierRaceModel> queryList(tblBbgsPackageSupplierRaceModel model){
        List<tblBbgsPackageSupplierRaceModel> list = null;
        try {
            list = service.queryDataList(model);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }


    /**
     * 根据标段简称查询所有供应商信息
     * 适用导入唱标报价之后，页面显示数据
     * @param model 存储查询条件的实体
     * @return 实体集合
     */
    @ResponseBody
    @RequestMapping("queryListByName")
    public List<tblBbgsPackageSupplierRaceModel> queryListByName(tblBbgsPackageSupplierRaceModel model){
        List<tblBbgsPackageSupplierRaceModel> list = null;
        try {
            list = service.queryListByName(model);
        }catch (Exception exp){
            exp.printStackTrace();
            list = null;
        }
        return list;
    }

    /***
     * 导出唱标标价模板
     * 导出excel 转移到swq 的action 中
     * 这个方法已经转移，今后不再使用
     * @param response HttpServletResponse
     */
    @RequestMapping("/commonExportExcel")
    public void commonExportExcel(HttpServletResponse response) {
        try {
            List<tblBbgsTendererRaceModel> list = tendererService.queryList();
            String[][] head = { {"bidAbbreviaion", "标段简称"},{"supplier", "供应商名称"}, {"id", "投标报价（万元）"}, {"id", "签字确认"}};
//            new exportExcel().commonExportExcel("唱标标价","国网黑龙江省电力有限公司2017年第二批紧急物资集中采购项目唱标表", head, list, response);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }


    /**
     * 查询去重的所有标段
     * @return 实体集合
     */
    @ResponseBody
    @RequestMapping("/queryDistinctBidList")
    public List<tblBbgsPackageSupplierRaceModel> queryDistinctBidList(){
        List<tblBbgsPackageSupplierRaceModel> list = null;
        try {
            list = service.queryDistinctBidList();
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }

    /**
     * 查询去重的所有标段简称
     * @return 实体集合
     */
    @ResponseBody
    @RequestMapping("/queryDistinctBidAbbreviaionList")
    public List<tblBbgsPackageSupplierRaceModel> queryDistinctBidAbbreviaionList(){
        List<tblBbgsPackageSupplierRaceModel> list = null;
        try {
            list = service.queryDistinctBidAbbreviaionList();
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }

    /**
     * 根据标段 查询包并去除重复
     * @param model 带查询条件的实体
     * @return 查询结果（实体集合）
     */
    @ResponseBody
    @RequestMapping("/queryDistinctPackageList")
    public List<tblBbgsPackageSupplierRaceModel> queryDistinctPackageList(tblBbgsPackageSupplierRaceModel model){
        List<tblBbgsPackageSupplierRaceModel> list = null;
        try {
            list = service.queryDistinctPackageList(model);
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return list;
    }


    /**
     * 操作一个 标段流标和招标
     * @return 操作结果 true or false
     */
    @ResponseBody
    @RequestMapping("/updateBidInvalidActionOne")
    public boolean updateBidInvalidActionOne(tblBbgsPackageSupplierRaceModel model){
        boolean flag = true;
        tblBbgsPackageSupplierRaceModel queryModel = new tblBbgsPackageSupplierRaceModel();
        try {
            service.updateByModel(model);
            queryModel.setFlagBidInvalid(model.getFlagBidInvalid());
            queryModel.setBid(model.getBid());
            RaceConductService.updateLikeBidName(queryModel);
        }catch (Exception exp){
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 批量操作 标段流标和招标
     * @param str rows 转JsonString
     * @param dealType 流标还是招标标志
     * @return 操作结果 true or false
     */
    @ResponseBody
    @RequestMapping("/updateBidInvalidActionList")
    public boolean updateBidInvalidActionList(String str,String dealType){
        boolean flag = true;
        try {
            JSONArray array = JSONArray.fromObject(str);
            List<tblBbgsPackageSupplierRaceModel> list = JSONArray.toList(array,new tblBbgsPackageSupplierRaceModel(), new JsonConfig());
            tblBbgsPackageSupplierRaceModel queryModel = new tblBbgsPackageSupplierRaceModel();
            for (tblBbgsPackageSupplierRaceModel model : list){
                if ("pass".equals(dealType)){
                    if ("0".equals(model.getFlagBidInvalid())){
                        continue;
                    }
                    model.setFlagBidInvalid("0");
                }
                if ("regain".equals(dealType)){
                    if ("1".equals(model.getFlagBidInvalid())){
                        continue;
                    }
                    model.setFlagBidInvalid("1");
                }
                service.updateByModel(model);
                queryModel.setFlagBidInvalid(model.getFlagBidInvalid());
                queryModel.setBid(model.getBid());
                RaceConductService.updateLikeBidName(queryModel);
            }
        }catch (Exception exp){
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }


    /***
     * 操作一个包  流标和招标的方法
     * @param model 操作信息 标段和包名和流标标志位
     * @return 操作结果
     */
    @ResponseBody
    @RequestMapping("/updateInvalidActionOne")
    public boolean updateInvalidActionOne(tblBbgsPackageSupplierRaceModel model){
        boolean flag = true;
        try {
            service.updateByModel(model);
            List<tblBbgsPackageSupplierRaceModel> list = service.queryListByName(model);
            if (list !=null && list.size()>0){
                tblBbgsBidInformationRaceModel updateModel = new tblBbgsBidInformationRaceModel();
                updateModel.setBidAbbreviaion(list.get(0).getBidAbbreviaion());
                updateModel.setInvalid(model.getFlagInvalid());
                RaceConductService.updateBidInfo(updateModel);
            }
        }catch (Exception exp){
            exp.printStackTrace();
            flag=false;
        }
        return flag;
    }

    /**
     * 批量操作 包的流标和招标
     * @param str rows 转JsonString
     * @param dealType 流标还是招标标志
     * @return 操作结果 true or false
     */
    @ResponseBody
    @RequestMapping("/updateInvalidActionList")
    public boolean updateInvalidActionList(String str,String dealType){
        boolean flag = true;
        try {
            tblBbgsBidInformationRaceModel updateModel = new tblBbgsBidInformationRaceModel();
            JSONArray array = JSONArray.fromObject(str);
            List<tblBbgsPackageSupplierRaceModel> list = JSONArray.toList(array,new tblBbgsPackageSupplierRaceModel(), new JsonConfig());
            for (tblBbgsPackageSupplierRaceModel model : list){
                if ("pass".equals(dealType)){
                    if ("0".equals(model.getFlagInvalid())){
                        continue;
                    }
                    model.setFlagInvalid("0");
                    updateModel.setInvalid("0");

                }
                if ("regain".equals(dealType)){
                    if ("1".equals(model.getFlagInvalid())){
                        continue;
                    }
                    model.setFlagInvalid("1");
                    updateModel.setInvalid("1");
                }
                service.updateByModel(model);
                List<tblBbgsPackageSupplierRaceModel> l = service.queryListByName(model);
                if (l !=null && l.size()>0){
                    updateModel.setBidAbbreviaion(l.get(0).getBidAbbreviaion());
                    RaceConductService.updateBidInfo(updateModel);
                }
            }
        }catch (Exception exp){
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }


    /***
     * 操作一个 供应商的废标标和复标
     * 根据Id更新 供应商信息表 的 废标标志位
     * @param model 存储更新条件的实体（ID）
     * @return 更新结果
     */
    @ResponseBody
    @RequestMapping("updateById")
    public boolean updateById(tblBbgsPackageSupplierRaceModel model){
        boolean flag = true;
        try {
            service.updateById(model);
        }catch (Exception exp){
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }


    /**
     * 批量操作 供应商的废标标和复标
     * @param str rows 转JsonString
     * @param dealType 废标标还是复标标志
     * @return 操作结果 true or false
     */
    @ResponseBody
    @RequestMapping("/updateByIdList")
    public boolean updateByIdList(String str,String dealType){
        boolean flag = true;
        try {
            JSONArray array = JSONArray.fromObject(str);
            List<tblBbgsPackageSupplierRaceModel> list = JSONArray.toList(array,new tblBbgsPackageSupplierRaceModel(), new JsonConfig());
            for (tblBbgsPackageSupplierRaceModel model : list){
                if ("pass".equals(dealType)){
                    if ("0".equals(model.getFlagBit())){
                        continue;
                    }
                    model.setFlagBit("0");
                }
                if ("regain".equals(dealType)){
                    if ("1".equals(model.getFlagBit())){
                        continue;
                    }
                    model.setFlagBit("1");
                }
                service.updateById(model);
            }
        }catch (Exception exp){
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }


    /***
     *  导出定标纪要
     * @return 结果信息
     */
    @ResponseBody
    @RequestMapping("/exportMinutesExcel")
    public Map exportMinutesExcel(){
        Map map = new HashMap();
        try {
            map = service.exportForMinutes();
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/queryMinIdByState")
    public Map queryMinIdByState(String id){
        try {
            //查询流程进行到的位置
            tblBbgsFlowStateRaceModel model = service.queryMinIdByState();
            if (model.getId() != null && Integer.valueOf(model.getId()) < 901) {
                Map map = new HashMap();
                map.put("result", false);
                map.put("msg",model.getAlertMessage());
                return  map;
            }else{
                Map map = new HashMap();
                map.put("result", true);
                return  map;
            }
        }catch (Exception exp){
            exp.printStackTrace();
            Map map = new HashMap();
            map.put("result", false);
            map.put("msg","操作失败！");
            return  map;
        }

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
            result = service.queryScoreGroupList();
        }catch (Exception exp){
            exp.printStackTrace();
        }
        return result;
    }
}
