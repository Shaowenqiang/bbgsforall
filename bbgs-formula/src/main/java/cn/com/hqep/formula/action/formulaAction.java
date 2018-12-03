package cn.com.hqep.formula.action;

import cn.com.hqep.formula.model.formulaConditionsModel;
import cn.com.hqep.formula.model.priceFormulaModel;
import cn.com.hqep.formula.model.supplierModel;
import cn.com.hqep.formula.model.tblBbgsFlowStateModel;
import cn.com.hqep.formula.service.formulaService;
import cn.com.hqep.formula.service.impl.formulaServiceImpl;
import cn.com.hqep.formula.util.JsonUtil;
import cn.com.hqep.formula.util.ZipUtils;
import cn.com.hqep.formula.util.deleteFileUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 招标公式_根据评标价计算价格得分_物资类/服务类
 * Created by spring on 2017/10/10.
 */
@Controller
@RequestMapping("/priceformula")
public class formulaAction {
    @Autowired
    private formulaService service;
    private String JSON_RESULT_FAILE = "{\"success\":false}";
    private String JSON_RESULT_SUCCESS = "{\"success\":true}";
    private String exportPath = "WEB-INF/page/bbgs/excel/";

    @ResponseBody
    @RequestMapping("/calculateAndSavePriceScore")
    public String calculateAndSavePriceScore(HttpServletRequest request) {
        //查询流程进行到的位置
        tblBbgsFlowStateModel model = service.queryMinIdByState();
        if (model.getId() != null && Integer.valueOf(model.getId()) < 603) {
            JSONObject temp = new JSONObject();
            temp.put("msg", model.getAlertMessage());
            temp.put("success", false);
            return temp.toString();
        }
        String result = JSON_RESULT_SUCCESS;
        String bid_abbreviaion = request.getParameter("bid_abbreviaion");
        String packName = request.getParameter("packName");
        String suppliers = request.getParameter("suppliers");
        String priceFormulaPath = request.getParameter("priceFun");
        List<priceFormulaModel> list;
        HashMap hashMap = new HashMap();
        hashMap.put("bid_abbreviaion", bid_abbreviaion);
        hashMap.put("packagename", packName);
        hashMap.put("supplier", suppliers);
        hashMap.put("priceFun", priceFormulaPath);
        try {
            list = service.queryInfoToCalculate(hashMap);
            HashMap jsonObject = this.groupByBid_abbreviaion(list);
            Iterator iterator = jsonObject.entrySet().iterator();
            String key;
            ArrayList value;
            ArrayList afterCaculateList = new ArrayList();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                key = (String) entry.getKey();
                value = (ArrayList) entry.getValue();
                value = service.calculatePriceScore(value, hashMap);
                afterCaculateList.addAll(value);
            }
            long a = System.currentTimeMillis();
            //保存前先删除所有旧数据
            service.deletePriceInfo(null);
            if (afterCaculateList.size() > 0) {
                service.savePriceInfoList(afterCaculateList);
            } else {
                JSONObject temp = new JSONObject();
                temp.put("msg", "当前无有效数据！");
                temp.put("success", false);
                return temp.toString();
            }
            long b = System.currentTimeMillis();
            System.out.println("耗时" + (b - a));
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject temp = new JSONObject();
            temp.put("msg", "操作失败！");
            temp.put("success", false);
            return temp.toString();
        }
        //更新流程状态
        service.updateStateById("603","1");
        return result;
    }

    @ResponseBody
    @RequestMapping("/calculateIfWin")
    public String calculateIfWin(HttpServletRequest request, HttpServletResponse response) {
        //查询流程进行到的位置
        tblBbgsFlowStateModel model = service.queryMinIdByState();
        if (model.getId() != null && Integer.valueOf(model.getId()) < 901) {
            JSONObject temp = new JSONObject();
            temp.put("msg", model.getAlertMessage());
            temp.put("success", false);
            return temp.toString();
        }
        String result = "";
        String bid_abbreviaion = request.getParameter("bid_abbreviaion");
        String packName = request.getParameter("packName");
        String suppliers = request.getParameter("suppliers");
        String bid = request.getParameter("bid");
        String calculeteType=request.getParameter("type");
        List<supplierModel> list;
        HashMap hashMap = new HashMap();
        hashMap.put("bid_abbreviaion", bid_abbreviaion);
        hashMap.put("packagename", packName);
        hashMap.put("supplier", suppliers);
        hashMap.put("bid", bid);
        hashMap.put("type", calculeteType);
        try {
            list = service.querySupplierInfoToCalculateIfWin(hashMap);
            HashMap jsonObject = this.supplier_groupByBid(list);
            Iterator iterator = jsonObject.entrySet().iterator();
            String key;
            ArrayList value;
            ArrayList afterCaculateList = new ArrayList();
            long a = System.currentTimeMillis();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                key = (String) entry.getKey();
                value = (ArrayList) entry.getValue();
                value = service.calculateSupplierIfWin(value, hashMap);
                afterCaculateList.addAll(value);
            }
            long b = System.currentTimeMillis();
            System.out.println("耗时" + (b - a));
            //保存前清空is_win字段值
            service.resetSupplierWin();
            //保存所有数据
            if (afterCaculateList.size() > 0) {
                service.updateSupplierInfoList(afterCaculateList);
            } else {
                JSONObject temp = new JSONObject();
                temp.put("msg", "当前无有效数据！");
                temp.put("success", false);
                return temp.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JSONObject temp = new JSONObject();
            temp.put("msg", "计算失败！");
            temp.put("success", false);
            return temp.toString();
        }
        //更新流程状态
        service.updateStateById("901","1");
        return JSON_RESULT_SUCCESS;
    }

    @ResponseBody
    @RequestMapping(value = "/exportToExcel")
    public Map exportToExcel(HttpServletRequest request, HttpServletResponse response) {
        //查询流程进行到的位置
        tblBbgsFlowStateModel model = service.queryMinIdByState();
        if (model.getId() != null && Integer.valueOf(model.getId()) <= 603) {
            Map map = new HashMap();
            map.put("result", false);
            map.put("msg",model.getAlertMessage());
            return  map;
        }
        String result = JSON_RESULT_SUCCESS;
        String bid_abbreviaion = request.getParameter("bid_abbreviaion");
        String packName = request.getParameter("packName");
        String suppliers = request.getParameter("suppliers");
        String priceFormulaPath = request.getParameter("priceFun");
        List<priceFormulaModel> list;
        HashMap hashMap = new HashMap();
        hashMap.put("bid_abbreviaion", bid_abbreviaion);
        hashMap.put("packagename", packName);
        hashMap.put("supplier", suppliers);
        hashMap.put("priceFun", priceFormulaPath);
        System.out.println("开始导出价格得分");
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        try {
            list = service.queryInfoToExport(hashMap);
            HashMap jsonObject = this.groupByBid_abbreviaion(list);
            Iterator iterator = jsonObject.entrySet().iterator();
            String key, export_type;
            ArrayList value;
            String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
            String zipName = "评标价格计算得分表";                              //String.valueOf(System.currentTimeMillis());
            String zipDir = webPath + exportPath + "/" + currentTimeMillis + "/"+ zipName;

            File tatFile = new File(zipDir);
            //导出前先删除之前导出的记录 否则出现之前导出的内容和本次一同压缩到导出zip中
            if (tatFile.exists()) {
//                new deleteFileUtil().deleteDir(tatFile);
            }
            File delFZ = new File(zipDir+".zip");
            if (delFZ.exists()) {
//                new deleteFileUtil().deleteDir(delFZ);
            }
            //加list为空判断
            if (list.size()<1){
                Map map = new HashMap();
                map.put("success", false);
                map.put("msg","无有效数据生成导出文件");
                return  map;
            }
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                key = (String) entry.getKey();
                value = (ArrayList) entry.getValue();
                if (value.size() > 0) {
                    export_type = ((priceFormulaModel) value.get(0)).getExport_type();
                    service.exportToExcel(value, key, key, zipDir, export_type);
                }

            }
            if (list.size() > 0) {
                ZipUtils.zip(zipDir, zipDir + ".zip", true);
                //result = zipName + ".zip";
            }
        } catch (Exception e) {
            e.printStackTrace();
            Map map = new HashMap();
            map.put("success", false);
            map.put("msg","导出文件失败！");
            return  map;
        }
        System.out.println("结束导出价格得分");
        Map map = new HashMap();
        map.put("success", true);
        map.put("currentTimeMillis", currentTimeMillis);
        return  map;
    }

    @ResponseBody
    @RequestMapping("/queryConditions")
    public String queryConditions(HttpServletRequest request) {
        String data = request.getParameter("data");
        JSONObject jsonObject = JSONObject.fromObject(data);
        formulaConditionsModel model = new formulaConditionsModel();
        JsonUtil.jsonToBean(jsonObject, model);
        List<formulaConditionsModel> list = service.queryFormulaConditions(model);
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();
    }

    @ResponseBody
    @RequestMapping("/querySupplierWinInfo")
    public String querySupplierWinInfo(HttpServletRequest request) {
        String data = request.getParameter("data");
        JSONObject jsonObject = JSONObject.fromObject(data);
        HashMap hashMap = new HashMap(JsonUtil.jsonToMap(jsonObject));
        List<supplierModel> list = service.querySupplierInfo(hashMap);
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();
    }

    @ResponseBody
    @RequestMapping("/queryBidInSupplier")
    public List queryBidInSupplier(HttpServletRequest request) {
        String data = request.getParameter("data");
        JSONObject jsonObject = JSONObject.fromObject(data);
        jsonObject = jsonObject != null ? jsonObject : new JSONObject();
        HashMap hashMap = new HashMap(JsonUtil.jsonToMap(jsonObject));
        List<supplierModel> list = service.querySupplierInfo(hashMap);
        List<supplierModel> result = new ArrayList<supplierModel>();
        HashMap<String, String> dis = new HashMap<String, String>();
        for (int i = 0; i < list.size(); i++) {
            String bid = list.get(i).getBid();
            if (!dis.containsKey(bid)) {
                dis.put(bid, bid);
                result.add(list.get(i));
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/saveConditions")
    public String saveConditions(HttpServletRequest request) {
        String edittype = request.getParameter("edittype");
        String data = request.getParameter("data");
        JSONObject jsonObject = JSONObject.fromObject(data);
        formulaConditionsModel model = new formulaConditionsModel();
        JsonUtil.jsonToBean(jsonObject, model);
        try {
            if ("add".equals(edittype)) {
                model.setId(formulaServiceImpl.getID());
                service.addFormulaConditions(model);
            } else if ("update".equals(edittype)) {
                service.updateFormulaConditions(model);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSON_RESULT_FAILE;
        }
        return JSON_RESULT_SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/deleteFormulaConditions")
    public String deleteFormulaConditions(HttpServletRequest request) {
        String id = request.getParameter("id");
        formulaConditionsModel model = new formulaConditionsModel();
        model.setId(id);
        service.deleteFormulaConditions(model);
        return this.JSON_RESULT_SUCCESS;
    }

    private HashMap groupByBid_abbreviaion(List<priceFormulaModel> list) {
        HashMap jsonData = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            priceFormulaModel item = list.get(i);
            String groupby = item.getBid_abbreviaion();
            if (!jsonData.containsKey(groupby)) {
                jsonData.put(groupby, new ArrayList<priceFormulaModel>());
            }
            ((ArrayList) jsonData.get(groupby)).add(item);
        }
        return jsonData;
    }

    private HashMap supplier_groupByBid(List<supplierModel> list) {
        HashMap jsonData = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            supplierModel item = list.get(i);
            String groupby = item.getBid();
            if (!jsonData.containsKey(groupby)) {
                jsonData.put(groupby, new ArrayList<priceFormulaModel>());
            }
            ((ArrayList) jsonData.get(groupby)).add(item);
        }
        return jsonData;
    }

    // 导出文件，此功能必须放在最后，否则若放在上面@下载点，当@下载点之后代码出现异常
    // 会导致文件流无法正常关闭
    private void exportFile(String fileName, String filePath, HttpServletResponse response) {
        File file = new File(filePath);
        FileInputStream fileStream = null;
        OutputStream outputStream = null;
        try {
            fileStream = new FileInputStream(file);
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = fileStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, i);
            }
            outputStream.close();
            fileStream.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @ResponseBody
    @RequestMapping("/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("fileName");
        String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
        String filepath = webPath + exportPath + fileName;
        File file = new File(filepath);
        if (file.exists()) {
            exportFile(fileName, filepath, response);
        }

    }

    @ResponseBody
    @RequestMapping(value = "/exportWin")
    public String exportWin(HttpServletRequest request, HttpServletResponse response) {
        //查询流程进行到的位置
        tblBbgsFlowStateModel model = service.queryMinIdByState();
        if (model.getId() != null && Integer.valueOf(model.getId()) <= 901) {
            JSONObject temp = new JSONObject();
            temp.put("msg", model.getAlertMessage());
            temp.put("success", false);
            return temp.toString();
        }
        String result = JSON_RESULT_SUCCESS;
        List<supplierModel> list;
        HashMap hashMap = new HashMap();
        hashMap.put("is_win", "1");
        try {
            String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
            String fileName = "中标结果";
            String name = service.queryBatchName();
            if (name != null && name.contains("2017")) {
                name = "（" + name.replace("\\", "").replace(name.substring(0, name.indexOf("2017")), "") + "）";
            } else if (name != null) {
                name = "（" + name + "）";
            } else {
                name = "";
            }
            fileName = fileName + name;
            String fileDir = webPath + exportPath;
            list = service.querySupplierInfo(hashMap);
            service.exportWinToExcel(list, "中标结果", fileName, fileDir);
            if (list.size() > 0) {
                //ZipUtils.zip(zipDir, zipDir + ".zip", true);
                JSONObject jsonObject = JSONObject.fromObject(JSON_RESULT_SUCCESS);
                jsonObject.put("filename", fileName + ".xlsx");
                result = jsonObject.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject temp = new JSONObject();
            temp.put("msg", "导出操作失败！");
            temp.put("success", false);
            return temp.toString();
        }
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/queryMinIdByState")
    public Map queryMinIdByState(String id){
        try {
            //查询流程进行到的位置
            tblBbgsFlowStateModel model = service.queryMinIdByState();
            if (model.getId() != null && Integer.valueOf(model.getId()) < Integer.parseInt(id)) {
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
}
