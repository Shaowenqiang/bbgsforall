package cn.com.hqep.templet.service.impl;

import cn.com.hqep.templet.common.ExcelUtil_poi;
import cn.com.hqep.templet.common.jsonMsg;
import cn.com.hqep.templet.dao.bidSupplierDao;
import cn.com.hqep.templet.dao.flowStateDao;
import cn.com.hqep.templet.dao.scoreImportDao;
import cn.com.hqep.templet.dao.templetDao;
import cn.com.hqep.templet.model.tblBbgsExpertModel;
import cn.com.hqep.templet.model.tblBbgsFlowStateModel;
import cn.com.hqep.templet.model.tblBbgsPackageSupplierModel;
import cn.com.hqep.templet.model.tblBbgsTechnologyBusinessModel;
import cn.com.hqep.templet.service.scoreImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.*;

/**
 * 技术商务评分导入导出serviceImpl
 *
 * @author hq4
 * @date 2017年10月17日 上午09:25:27
 */
@Repository
public class scoreImportServiceImpl implements scoreImportService {
    @Autowired
    templetDao templetDao;
    @Autowired
    scoreImportDao scoreImportDao;
    @Autowired
    flowStateDao flowStateDao;
    @Autowired
    bidSupplierDao bidSupplierDao;

    /**
     * 保存多个文件内容
     *
     * @param
     * @return
     */
    @Override
    public List save(Map<String, File> map) {
        List msgList = new ArrayList();
        List<Map> list = new ArrayList<>();
        boolean b = true;
        tblBbgsFlowStateModel model1 = flowStateDao.queryMinIdByState();
        if (model1.getId() != null && Integer.valueOf(model1.getId()) <= Integer.valueOf("602")) {
            list.add(jsonMsg.toJosn("error",model1.getAlertMessage()));
            return list;
        }
        //利用KeySet 迭代
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            File file = map.get(key);
            String wjj = key.substring(0, key.lastIndexOf("/"));
            String name = key.replace(wjj, "");
            if (!wjj.contains("（") && !wjj.contains("）")) {
                list.add(jsonMsg.toJosn("error", "检查文件夹格式"));
                return list;
            }
            Map map1 = ExcelUtil_poi.getExcelList(file, key);
            if (map1.get("state").equals("error")) {
                list.add(map1);
                return list;
            }
            list.add(map1);
            Map Msg = new LinkedHashMap();
            Msg.put("state", "success");
            Msg.put("msg", subExcelName(key) + "导入成功");
            if (map1.get("标段简称") == null || map1.get("标段简称").equals("")) {
                Msg.put("state", "error");
                Msg.put("msg", subExcelName(key) + "导入失败！");
                b = false;
            }
            msgList.add(Msg);
        }
        if (b) {
            for (Map map2 : list) {
                String expertName = subStr(map2.get("文件夹名").toString());
                String type = subName(map2.get("文件夹名").toString());
                tblBbgsTechnologyBusinessModel model = new tblBbgsTechnologyBusinessModel();
                model.setExpertName(expertName);
                model.setType(type);
                model.setBidAbbreviaion(map2.get("标段简称").toString());
                Map scoreMap = (Map) map2.get("总分");
                Iterator item = scoreMap.keySet().iterator();
                while (item.hasNext()) {
                    String key = item.next().toString();
                    tblBbgsTechnologyBusinessModel model_check = new tblBbgsTechnologyBusinessModel();
                    model.setSupplier(key);
                    model.setPrice(scoreMap.get(key).toString());
                    model_check.setSupplier(key);
                    model_check.setBidAbbreviaion(map2.get("标段简称").toString());
                    model_check.setExpertName(expertName);
                    if (model.getBidAbbreviaion() != null && model.getSupplier() != null && !model.getBidAbbreviaion().equals("") && !model.getSupplier().equals("")) {
                        int i = scoreImportDao.selectCount(model_check);
                        if (i > 0) {
                            scoreImportDao.updateModel(model);
                        } else {
                            model.setId(UUID.randomUUID().toString().replace("-", ""));
                            scoreImportDao.saveModel(model);
                            flowStateDao.updateStateById("701","1");
                        }
                    }

                }
            }
        }
        return msgList;
    }

    public String subStr(String str) {
        str = str.substring(0, str.lastIndexOf("/"));
        return str.substring(str.lastIndexOf("（") + 1, str.lastIndexOf("）"));
    }

    public String subExcelName(String name) {
//        name = name.replace(name.substring(0, name.lastIndexOf("/") + 1), "");
        return name;
    }

    public String subName(String type) {
        type = type.substring(type.lastIndexOf("/") + 1, type.lastIndexOf("."));
        return type.trim().substring(0, 2);
    }

    @Override
    public List<String> querySupplierName(String bidAbbreviaion) {
        tblBbgsPackageSupplierModel model = new tblBbgsPackageSupplierModel();
        model.setBidAbbreviaion(bidAbbreviaion);
        model.setFlagBit("1");
        model.setFlagInvalid("1");
        model.setFlagBidInvalid("1");
        List<tblBbgsPackageSupplierModel> list = bidSupplierDao.queryList(model);
        List<String> suppliers = new ArrayList<>();
        for (tblBbgsPackageSupplierModel model1 : list) {
            suppliers.add(model1.getSupplier());
        }
        return suppliers;
    }

    @Override
    public List<String> queryExpertNames(String technologyGroupName) {
        List<tblBbgsExpertModel> list = scoreImportDao.queryExpertNames(technologyGroupName);
        List<String> l = new ArrayList<>();
        for (tblBbgsExpertModel model : list) {
            l.add(model.getExpertName());
        }
        return l;
    }

    @Override
    public List<tblBbgsExpertModel> getAllExpert() {
        return scoreImportDao.getAllExpert();
    }

    @Override
    public List<tblBbgsExpertModel> getCount() {
        return scoreImportDao.getCount();
    }

    @Override
    public void queryScoreInfo() {
        scoreImportDao.queryScoreInfo();
    }
}
