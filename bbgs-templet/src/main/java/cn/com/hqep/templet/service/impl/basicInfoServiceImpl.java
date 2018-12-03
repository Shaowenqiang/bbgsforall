package cn.com.hqep.templet.service.impl;

import cn.com.hqep.templet.common.*;
import cn.com.hqep.templet.dao.*;
import cn.com.hqep.templet.model.*;
import cn.com.hqep.templet.service.basicInfoService;
import cn.com.hqep.templet.service.scoreImportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 对基础信息
 *
 * @author shaowenqiang
 * @date 2017年10月09日 下午04:39:54
 */
@Repository
@Transactional
public class basicInfoServiceImpl implements basicInfoService {
    @Autowired
    batchDao batchDao;
    @Autowired
    supplierDao supplierDao;
    @Autowired
    tendererDao tendererDao;
    @Autowired
    bidSupplierDao bidSupplierDao;
    @Autowired
    bidInfoDao bidInfoDao;
    @Autowired
    templetDao templetDao;
    @Autowired
    scoreImportService scoreImportService;
    @Autowired
    flowStateDao flowStateDao;
    @Autowired
    expertAll expertDao;

    /**
     * 向各个表中插入基础数据
     *
     * @param file     Excel文件
     * @param category 类别 1物资 2非物资
     * @return
     */
    public Map<String, String> saveData(MultipartFile file, String category) {
        //需要验证excel表格的列 list 为空则不进行验证
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Map map = null;
        String type = file.getOriginalFilename();
        if (type.indexOf(".xlsx") > 0) {
            map = ExcelUtil.readXlsx(file, list);
        } else if (type.indexOf(".xls") > 0) {
            map = ExcelUtil.readXls(file, list);
        }
        //判断文件是否正确 正确执行插入数据操作 错误 返回错误信息
        if (map == null || map.size() == 0) {
            Map<String, String> errorMap = new HashMap();
            errorMap.put("state", "error");
            errorMap.put("msg", "请检查上传文件");
            return errorMap;
        }
        if (map.size() > 0) {
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                //所有sheet页 数据
                Map sheetMap = (Map) entry.getValue();
                //sheet页 题头 例如 国网黑龙江省电力有限公司2017年第二批紧急物资集中采购项目投标人统计
                //判断sheet页数据是否有问题
                if (sheetMap.get("flag").equals(false)) {
                    Map errorMap = new HashMap();
                    errorMap.put("state", "error");
                    errorMap.put("msg", sheetMap.get("msg").toString());
                    return errorMap;
                }
                if (sheetMap.get("flag").equals(true)) {
                    //用于储存正确的sheet页title数据
                    String title = "";
                    //批次表对象
                    tblBbgsBatchModel tblBbgsBatch;
                    //用于储存所有供应商名称(名称不重复)
                    Set<String> set = new HashSet();
                    //用于储存标段简称(名称不重复)
                    Set<String> set2 = new HashSet();
                    //储存所有 投标人信息
                    List<tblBbgsTendererModel> tendererModels = new ArrayList<tblBbgsTendererModel>();
                    //生成批次表对象
                    title = sheetMap.get("title").toString().replace("投标人统计", "");
                    String id = UUID.randomUUID().toString().replace("-", "");
                    tblBbgsBatch = new tblBbgsBatchModel(id, title, category);
                    //执行插入批次表数据操作
//                    Dao.saveTenderer
                    //正确的sheet页数据
                    List<Map> sheetInfoList = (List<Map>) sheetMap.get("data");
                    //将导入的基础信息转化成各对象实体 进行插入操作
//                    supplierDao.saveSupplier(tblBbgsSupplierModel)

                    for (Map sheetOne : sheetInfoList) {
                        List<String> checkList = new ArrayList<>();
                        checkList.add("标段简称");
                        checkList.add("包号");
                        checkList.add("供应商名称");
                        checkList.add("联系电话");
                        checkList.add("电子邮箱");
                        checkList.add("是否汇款");
                        checkList.add("是否发送");
                        for (String checkStr : checkList) {
                            if (!sheetOne.keySet().contains(checkStr)) {
                                return jsonMsg.toJosn("error", "导入文件中 " + checkStr + " 信息缺失");
                            }
                        }
                        String email = sheetOne.get("电子邮箱").toString();
                        if (email.length() > 64) {
                            return jsonMsg.toJosn("error", "第一个sheet页第" + sheetOne.get("rowNum") + "行 电子邮箱过长");
                        }
                        String telephone = sheetOne.get("联系电话").toString();
                        if (telephone.length() > 32) {
                            return jsonMsg.toJosn("error", "第一个sheet页第" + sheetOne.get("rowNum") + "行 联系电话过长");
                        }
                        String remit = sheetOne.get("是否汇款").toString();
                        if (!remit.equals("是否汇款") && remit.length() > 2) {
                            return jsonMsg.toJosn("error", "第一个sheet页第" + sheetOne.get("rowNum") + "行 是否汇款过长");
                        }
                        String send = sheetOne.get("是否发送").toString();
                        if (!send.equals("是否发送") && send.length() > 2) {
                            return jsonMsg.toJosn("error", "第一个sheet页第" + sheetOne.get("rowNum") + "行 是否发送过长");
                        }
                        if (!sheetOne.get("供应商名称").equals("供应商名称")) {
//                            System.out.println(sheetOne.get("rowNum"));
                            int rowNum = (int) sheetOne.get("rowNum");
                            String bidAbbreviaion = sheetOne.get("标段简称").toString();
                            if (!bidAbbreviaion.contains("-") || !bidAbbreviaion.substring(0, bidAbbreviaion.indexOf
                                    ("-")).matches("^[0-9]*$")) {
                                return jsonMsg.toJosn("error", "第一个sheet页，第" + rowNum + "行 标段简称为：" + bidAbbreviaion +
                                        "的命名应该以数字+'-'开头例如（001-工具）");
                            }
                            String packagename = sheetOne.get("包号").toString();
                            if (!packagename.startsWith("包")) {
                                return jsonMsg.toJosn("error", "第一个sheet页，第" + rowNum + "行 标段简称为：" + bidAbbreviaion +
                                        "的包号应该以 '包' 字开头 ");
                            }
                            bidAbbreviaion = bidAbbreviaion + packagename;
                            String supplierName = sheetOne.get("供应商名称").toString();
                            int num1 = 0;
                            int i = 0;
                            for (Map map1 : sheetInfoList) {
                                if (map1.get("标段简称").equals(sheetOne.get("标段简称").toString()) && map1.get("包号").equals
                                        (packagename) && map1.get("供应商名称").equals(supplierName)) {
                                    i = i + 1;
                                    if (i == 1) {
                                        num1 = (int) map1.get("rowNum");
                                    }
                                    if (i == 2) {
                                        return jsonMsg.toJosn("error", "导入文件中 第" + num1 + "和第" + map1.get("rowNum") +
                                                "行标段简称和供应商重复");
                                    }
                                }
                            }

                            set.add(supplierName);
                            if (!bidAbbreviaion.contains("-")) {
                                return jsonMsg.toJosn("error", "导入文件中" + "第一个sheet页，第" + rowNum + "行 标段简称命名为：" +
                                        bidAbbreviaion + " 格式不正确");
                            }


                            set2.add(bidAbbreviaion);
                            tblBbgsTendererModel tenderer = new tblBbgsTendererModel();
                            tenderer.setSupplier(supplierName);
                            tenderer.setBidAbbreviaion(bidAbbreviaion);
                            tenderer.setBatchTitle(title);
                            tenderer.setEmail(email);
                            tenderer.setTel(telephone);
                            tenderer.setRemit(remit);
                            tenderer.setSend(send);
                            tendererModels.add(tenderer);
                        }
                    }
                    //插入批次
                    batchDao.saveBatch(tblBbgsBatch);
                    //插入供应商
                    if (set.size() > 0) {
                        for (String oneSupplier : set) {
                            supplierDao.saveSupplier(oneSupplier);
                        }
                    }
                    //插入投标人表
                    if (tendererModels.size() > 0) {
                        for (tblBbgsTendererModel model : tendererModels) {
                            tblBbgsTendererModel m = new tblBbgsTendererModel();
                            m.setBidAbbreviaion(model.getBidAbbreviaion());
                            m.setSupplier(model.getSupplier());
                            if (tendererDao.queryAllByModel(m).size() == 0) {
                                model.setId(UUID.randomUUID().toString().replace("-", ""));
                                tendererDao.saveTenderer(model);
                                tblBbgsPackageSupplierModel packageSupplier = new tblBbgsPackageSupplierModel();
                                packageSupplier.setId(UUID.randomUUID().toString().replace("-", ""));
                                packageSupplier.setBidAbbreviaion(model.getBidAbbreviaion());
                                packageSupplier.setBid(model.getBidAbbreviaion().substring(0, model.getBidAbbreviaion
                                        ().indexOf("-")));
                                packageSupplier.setSupplier(model.getSupplier());
                                String packagename = model.getBidAbbreviaion().substring(model.getBidAbbreviaion()
                                        .lastIndexOf("包"), model.getBidAbbreviaion().length());
                                packageSupplier.setPackagename(packagename);
                                bidSupplierDao.saveBid(packageSupplier);
                            }
                        }
                    }
                    //插入标段表信息
                    if (set2.size() > 0) {
                        for (String oneBid : set2) {
                            tblBbgsBidInformationModel bidInfo = new tblBbgsBidInformationModel();
                            bidInfo.setBidAbbreviaion(oneBid);
                            bidInfo.setId(UUID.randomUUID().toString().replace("-", ""));
                            bidInfo.setInvalid("1");
                            bidInfo.setFlag("N");
                            bidInfoDao.saveBidInfo(bidInfo);
                        }
                    }
                    flowStateDao.updateStateById("501", "1");
                    return jsonMsg.booleanToMap(true);
                }
            }
        }
        return jsonMsg.toJosn("error", "请检查上传文件");
    }


    /**
     * 导入投标报价方法
     * 说明：基础信息后面加一列报价
     *
     * @param file Excel文件
     * @return
     */
    public Map<String, String> saveBaseOffer(MultipartFile file) {
        //需要验证excel表格的列 list 为空则不进行验证
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        Map map = null;
        String type = file.getOriginalFilename();
        if (type.indexOf(".xlsx") > 0) {
            map = ExcelUtil.readXlsx(file, list);
        } else if (type.indexOf(".xls") > 0) {
            map = ExcelUtil.readXls(file, list);
        }
        //判断文件是否正确 正确执行插入数据操作 错误 返回错误信息
        if (map == null || map.size() == 0) {
            Map<String, String> errorMap = new HashMap();
            errorMap.put("state", "error");
            errorMap.put("msg", "请检查上传文件");
            return errorMap;
        }
        if (map.size() > 0) {
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                //所有sheet页 数据
                Map sheetMap = (Map) entry.getValue();
                //sheet页 题头 例如 国网黑龙江省电力有限公司2017年第二批紧急物资集中采购项目投标人统计
                //判断sheet页数据是否有问题
                if (sheetMap.get("flag").equals(false)) {
                    Map errorMap = new HashMap();
                    errorMap.put("state", "error");
                    errorMap.put("msg", sheetMap.get("msg").toString());
                    return errorMap;
                }
                if (sheetMap.get("flag").equals(true)) {
                    //用于储存正确的sheet页title数据
                    String title = "";
                    //储存所有 投标人信息
                    List<tblBbgsTendererModel> tendererModels = new ArrayList<tblBbgsTendererModel>();
                    //生成批次表对象
                    title = sheetMap.get("title").toString().replace("投标人统计", "");
                    //正确的sheet页数据
                    List<Map> sheetInfoList = (List<Map>) sheetMap.get("data");
                    //将导入的基础信息转化成各对象实体 进行插入操作
//                    supplierDao.saveSupplier(tblBbgsSupplierModel)

                    for (Map sheetOne : sheetInfoList) {
                        List<String> checkList = new ArrayList<>();
                        checkList.add("标段简称");
                        checkList.add("包号");
                        checkList.add("供应商名称");
                        checkList.add("联系电话");
                        checkList.add("电子邮箱");
                        checkList.add("是否汇款");
                        checkList.add("是否发送");
                        checkList.add("投标报价（万元）");
                        for (String checkStr : checkList) {
                            if (!sheetOne.keySet().contains(checkStr)) {
                                return jsonMsg.toJosn("error", "导入文件中 " + checkStr + " 信息缺失");
                            }
                        }
                        String email = sheetOne.get("电子邮箱").toString();
                        if (email.length() > 64) {
                            return jsonMsg.toJosn("error", "第一个sheet页第" + sheetOne.get("rowNum") + "行 电子邮箱过长");
                        }
                        String telephone = sheetOne.get("联系电话").toString();
                        if (telephone.length() > 32) {
                            return jsonMsg.toJosn("error", "第一个sheet页第" + sheetOne.get("rowNum") + "行 联系电话过长");
                        }
                        String remit = sheetOne.get("是否汇款").toString();
                        if (!remit.equals("是否汇款") && remit.length() > 2) {
                            return jsonMsg.toJosn("error", "第一个sheet页第" + sheetOne.get("rowNum") + "行 是否汇款过长");
                        }
                        String send = sheetOne.get("是否发送").toString();
                        if (!send.equals("是否发送") && send.length() > 2) {
                            return jsonMsg.toJosn("error", "第一个sheet页第" + sheetOne.get("rowNum") + "行 是否发送过长");
                        }
                        if (!sheetOne.get("供应商名称").equals("供应商名称")) {
//                            System.out.println(sheetOne.get("rowNum"));
                            int rowNum = (int) sheetOne.get("rowNum");
                            String bidAbbreviaion = sheetOne.get("标段简称").toString();
                            if (!bidAbbreviaion.contains("-") || !bidAbbreviaion.substring(0, bidAbbreviaion.indexOf
                                    ("-")).matches("^[0-9]*$")) {
                                return jsonMsg.toJosn("error", "第一个sheet页，第" + rowNum + "行 标段简称为：" + bidAbbreviaion +
                                        "的命名应该以数字+'-'开头例如（001-工具）");
                            }
                            String packagename = sheetOne.get("包号").toString();
                            if (!packagename.startsWith("包")) {
                                return jsonMsg.toJosn("error", "第一个sheet页，第" + rowNum + "行 标段简称为：" + bidAbbreviaion +
                                        "的包号应该以 '包' 字开头 ");
                            }
                            bidAbbreviaion = bidAbbreviaion + packagename;
                            String supplierName = sheetOne.get("供应商名称").toString();
                            int num1 = 0;
                            int i = 0;
                            for (Map map1 : sheetInfoList) {
                                if (map1.get("标段简称").equals(sheetOne.get("标段简称").toString()) && map1.get("包号").equals
                                        (packagename) && map1.get("供应商名称").equals(supplierName)) {
                                    i = i + 1;
                                    if (i == 1) {
                                        num1 = (int) map1.get("rowNum");
                                    }
                                    if (i == 2) {
                                        return jsonMsg.toJosn("error", "导入文件中 第" + num1 + "和第" + map1.get("rowNum") +
                                                "行标段简称和供应商重复");
                                    }
                                }
                            }
                            if (!bidAbbreviaion.contains("-")) {
                                return jsonMsg.toJosn("error", "导入文件中" + "第一个sheet页，第" + rowNum + "行 标段简称命名为：" +
                                        bidAbbreviaion + " 格式不正确");
                            }
                            String offer = sheetOne.get("投标报价（万元）").toString();
                            //验证报价是否是double类型
                            String regex = "^[-//+]?[0-9]*([\\\\.][0-9]+)?$";
                            Pattern pattern = Pattern.compile(regex);
                            boolean flag = pattern.matcher(offer).matches();
                            boolean b = offer.equals("无");
                            System.out.println(b);
                            if (!flag && !b) {
                                Map errorMap = new HashMap();
                                errorMap.put("state", "error");
                                errorMap.put("msg", "第" + rowNum + "行投标报价有误！");
                                return errorMap;
                            }
                            String strOffer = null;
                            if (!b) {
                                strOffer = String.format("%.6f", Double.parseDouble(offer));
                                tblBbgsTendererModel tenderer = new tblBbgsTendererModel();
                                tenderer.setSupplier(supplierName);
                                tenderer.setBidAbbreviaion(bidAbbreviaion);
                                tenderer.setBatchTitle(title);
                                tenderer.setEmail(email);
                                tenderer.setTel(telephone);
                                tenderer.setRemit(remit);
                                tenderer.setSend(send);
                                tenderer.setOffer(strOffer);
                                tendererModels.add(tenderer);
                            }
                        }
                    }

                    //插入投标人表
                    if (tendererModels.size() > 0) {
                        tendererDao.deleteForSaveOffer();
                        //用于储存所有供应商名称(名称不重复)
                        Set<String> set = new HashSet();
                        //用于储存标段简称(名称不重复)
                        Set<String> set2 = new HashSet();
                        for (tblBbgsTendererModel model : tendererModels) {
                            tblBbgsTendererModel m = new tblBbgsTendererModel();
                            m.setBidAbbreviaion(model.getBidAbbreviaion());
                            m.setSupplier(model.getSupplier());
                            if (!"无".equals(model.getOffer())) {
                                model.setId(UUID.randomUUID().toString().replace("-", ""));
                                tendererDao.saveTenderer(model);
                                tblBbgsPackageSupplierModel packageSupplier = new tblBbgsPackageSupplierModel();
                                packageSupplier.setId(UUID.randomUUID().toString().replace("-", ""));
                                packageSupplier.setBidAbbreviaion(model.getBidAbbreviaion());
                                packageSupplier.setBid(model.getBidAbbreviaion().substring(0, model.getBidAbbreviaion
                                        ().indexOf("-")));
                                packageSupplier.setSupplier(model.getSupplier());
                                String packagename = model.getBidAbbreviaion().substring(model.getBidAbbreviaion()
                                        .lastIndexOf("包"), model.getBidAbbreviaion().length());
                                packageSupplier.setPackagename(packagename);
                                packageSupplier.setOffer(model.getOffer());
                                packageSupplier.setFlagBit("1");
                                packageSupplier.setFlagInvalid("1");
                                packageSupplier.setFlagBidInvalid("1");
                                bidSupplierDao.saveBid(packageSupplier);
                                //存入供应商，为后面存入供应商表服务
                                set.add(model.getSupplier());
                                //存入标段简称，为后面存入基础信息表服务
                                set2.add(model.getBidAbbreviaion());
                            }
                        }
                        //插入供应商
                        if (set.size() > 0) {
                            for (String oneSupplier : set) {
                                supplierDao.saveSupplier(oneSupplier);
                            }
                        }
                        //插入标段表信息
                        if (set2.size() > 0) {
                            for (String oneBid : set2) {
                                tblBbgsBidInformationModel bidInfo = new tblBbgsBidInformationModel();
                                bidInfo.setBidAbbreviaion(oneBid);
                                bidInfo.setId(UUID.randomUUID().toString().replace("-", ""));
                                bidInfo.setInvalid("1");
                                bidInfo.setFlag("N");
                                bidInfoDao.saveBidInfo(bidInfo);
                            }
                        }
                    }
                    flowStateDao.updateStateById("601", "1");
                    return jsonMsg.toJosn("success", "");
                }
            }
        }
        return jsonMsg.toJosn("error", "请检查上传文件");
    }


    private Map checkDuplicate(List<Map> sheetInfoList) {
        for (Map m : sheetInfoList) {
            int i = 0;
            for (Map m2 : sheetInfoList) {
                if (m.equals(m2)) {
                    i = i + 1;
                    if (i > 2) {
                        return jsonMsg.toJosn("error", "导入文件中 第" + m.get("rowNum") + "和第" + m2.get("rowNum") +
                                "行标段简称和供应商重复");
                    }
                }
            }
        }
        return jsonMsg.booleanToMap(true);
    }

    /**
     * 清空当前批次所有数据
     *
     * @return
     */
    public boolean delAllData() {
        tendererDao.deleteAllData();
        flowStateDao.updateStateById(null, "0");
        return true;
    }


    /**
     * 判断是否存在批次数据
     *
     * @return Boolean true 存在数据 false 不存在数据可以执行导入操作
     */
    public boolean existData() {
        List<tblBbgsBatchModel> list = batchDao.listAll();
        return list != null && list.size() > 0;
    }

    /**
     * 查询当前批次名称
     *
     * @return
     */
    public Map queryName() {
        Map<String, String> m = new HashMap<String, String>();
        m.put("state", "error");
        List<tblBbgsBatchModel> list = batchDao.listAll();
        if (list != null) {
            if (list.size() > 0) {
                m.put("state", "success");
                m.put("batchName", list.get(0).getBatchName());
                m.put("category", list.get(0).getIsMaterial());
            } else {
                m.put("state", "error");
                m.put("errorMsg", "当前无可用批次数据！");
            }
        }
        return m;
    }

    /**
     * 查询 标段-包信息
     *
     * @return
     */
    public List queryAllBid() {
        List<tblBbgsBidInformationModel> list = bidInfoDao.queryAllBid();
        Set<Map<String, Object>> set = new LinkedHashSet<>();
        for (tblBbgsBidInformationModel model : list) {
            String bidAbbreviaion = model.getBidAbbreviaion();
            String bid = bidAbbreviaion.substring(0, bidAbbreviaion.lastIndexOf("包"));
            Map m = new LinkedHashMap();
            m.put("bid", bid);
            set.add(m);
        }
        return new ArrayList(set);
    }

    @Override
    public List<tblBbgsBidInformationModel> queryAllByModel(tblBbgsBidInformationModel model) {
        return bidInfoDao.queryAllByModel(model);
    }

    /**
     * 查询 标段-包信息 操作记录
     *
     * @return
     */
    public List<tblBbgsBidInformationModel> queryAllBidRecord(tblBbgsBidInformationModel model) {
        return bidInfoDao.queryAllByModel(model);
    }

    /**
     * 更新多个 标段-包 的模板信息
     *
     * @param ids
     * @param data
     */
    public Map saveBidInfos(String ids, tblBbgsBidInformationModel data) {
        //查询流程进行到哪一个节点
        tblBbgsFlowStateModel model1 = flowStateDao.queryMinIdByState();
        if (model1.getId() != null && Integer.valueOf(model1.getId()) <= Integer.valueOf("501")) {
            return jsonMsg.toJosn("error", model1.getAlertMessage());
        }

        String[] list = ids.split(",");
        List<tblBbgsBidInformationModel> list1 = bidInfoDao.queryAllBid();
        //修改标志位 证明该标段包邮模板对应
        for (String id : list) {
            for (tblBbgsBidInformationModel model : list1) {
                if (model.getBidAbbreviaion().equals(id)) {
                    data.setId(model.getId());
                    data.setFlag("Y");
                    data.setBidAbbreviaion(model.getBidAbbreviaion());
                    bidInfoDao.updateBidInfo(data);
                }
            }
        }
        tblBbgsBidInformationModel model = new tblBbgsBidInformationModel();
        model.setFlag("N");
        List l1 = bidInfoDao.queryAllByModel(model);
        int size1 = l1.size();
        model.setFlag("Y");
        List l2 = bidInfoDao.queryAllByModel(model);
        int size2 = l2.size();
        if (size1 == 0 && size2 > 0) {
            flowStateDao.updateStateById("602", "1");
        }
        return jsonMsg.booleanToMap(true);
    }

    /**
     * 更新 标段-包信息 标志位
     *
     * @param id
     * @return
     */
    public boolean updateBidInfoFlag(String id) {
        tblBbgsBidInformationModel bidInfo = new tblBbgsBidInformationModel();
        bidInfo.setId(id);
        bidInfo.setFlag("N");
        bidInfo.setTechnologyExpertGroup("");
        bidInfo.setM("");
        if (bidInfoDao.updateBidInfoFlag(bidInfo)) {
            flowStateDao.updateStateById("602", "0");
            return true;
        }
        return false;
    }


    /**
     * 导出专家组阅标记录
     *
     * @param sourcePath
     * @return
     */
    @Override
    public Map exportBidRecordes(String sourcePath, String vbsPath) {
        //查询流程进行到哪一个节点
        tblBbgsFlowStateModel model1 = flowStateDao.queryMinIdByState();
        if (model1.getId() != null && Integer.valueOf(model1.getId()) <= Integer.valueOf("602")) {
            return jsonMsg.toJosn("error", model1.getAlertMessage());
        }
        File sourceFile = new File(sourcePath);
        if (sourceFile.exists())
            FileAction.deleteFile(sourceFile);
        File file = new File(sourcePath);
        if (!file.exists()) {
            //创建新的文件夹
            file.mkdirs();
        }
        //取得批次名称
        String batchName = "";
        List<tblBbgsBatchModel> batchList = batchDao.listAll();
        if (batchList.size() == 0) {
            batchName = "";
        } else if (batchList.size() == 1) {
            batchName = batchList.get(0).getBatchName();
        } else if (batchList.size() > 1) {
            batchName = batchList.get(batchList.size() - 1).getBatchName();
        }
        List<tblBbgsPackageSupplierModel> list = bidSupplierDao.queryAllListByModel(new tblBbgsPackageSupplierModel());

        //阅标记录====================================
        //技术专家组
        Map<String, String> map = new LinkedHashMap<>();
        //商务专家组
        Map<String, String> map1 = new LinkedHashMap<>();
        for (tblBbgsPackageSupplierModel model : list) {
            map.put(model.getTechnologyExpertGroup(), model.getTechnologyExpertGroupName());
            map1.put(model.getBusinessExpertGroup(), model.getBusinessExpertGroupName());
        }
        String title = batchName + "技术组阅标记录";
        String techTemplatePath = null;
        for (String s : map.keySet()) {
            //技术阅标记录
            //查询该技术组负责的标段简称取出标号
            if (s != null) {
                tblBbgsPackageSupplierModel model = new tblBbgsPackageSupplierModel();
                model.setTechnologyExpertGroup(s);
                List<tblBbgsPackageSupplierModel> list1 = bidSupplierDao.queryAllListByModel(model);
                Map<String, Set<String>> supplierList = new LinkedHashMap<>();
                for (tblBbgsPackageSupplierModel m : list1) {
                    //获取标段列表
                    if (supplierList.keySet().contains(m.getBid())) {
                        supplierList.get(m.getBid()).add(m.getSupplier());
                    } else {
                        Set<String> l = new HashSet<>();
                        l.add(m.getSupplier());
                        supplierList.put(m.getBid(), l);
                    }
                    techTemplatePath = m.getTechnologyPath();
                    if (techTemplatePath == null || "".equals(techTemplatePath)) {
                        return jsonMsg.toJosn("error", m.getBidAbbreviaion() + "使用的技术阅标记录模板文件不存在");

                    }
                    File file_t = new File(sourcePath.replace("WEB-INF/page/bbgs/excel/专家组阅标记录/","")+techTemplatePath);
                    if (!file_t.exists()) {
                        return jsonMsg.toJosn("error", m.getBidAbbreviaion() + "使用的技术阅标记录模板文件不存在");
                    }
                }
                Map m = ExcelUtil_poi.exportBidRecord(sourcePath + "/" + map.get(s) + "技术阅标记录.xls", sourcePath.replace("WEB-INF/page/bbgs/excel/专家组阅标记录/","")+techTemplatePath,
                        supplierList, title, "技术");
                if (m.get("state").equals("error")) {
                    return m;
                }
                cmdOrderUtil.fixHeaerAndCss(m.get("msg").toString(), "$1:$3", vbsPath);
            }
        }
        title = batchName + "商务组阅标记录";
        String busnessPath = null;
        for (String s : map1.keySet()) {
            if (s != null) {
                tblBbgsPackageSupplierModel model = new tblBbgsPackageSupplierModel();
                model.setBusinessExpertGroup(s);
                List<tblBbgsPackageSupplierModel> list1 = bidSupplierDao.queryAllListByModel(model);
                Map<String, Set<String>> supplierList = new LinkedHashMap<>();
                for (tblBbgsPackageSupplierModel m : list1) {
                    //获取标段列表
                    if (supplierList.keySet().contains(m.getBid())) {
                        supplierList.get(m.getBid()).add(m.getSupplier());
                    } else {
                        Set<String> l = new HashSet<>();
                        l.add(m.getSupplier());
                        supplierList.put(m.getBid(), l);
                    }

                    busnessPath = m.getBusinessPath();
                    if (busnessPath == null || "".equals(busnessPath)) {
                        return jsonMsg.toJosn("error", m.getBidAbbreviaion() + "使用的商务阅标记录模板文件不存在");
                    }
                    File file_b = new File(sourcePath.replace("WEB-INF/page/bbgs/excel/专家组阅标记录/","")+busnessPath);
                    if (!file_b.exists()) {
                        return jsonMsg.toJosn("error", m.getBidAbbreviaion() + "使用的商务阅标记录模板文件不存在");
                    }
                }
                ExcelUtil_poi.exportBidRecord(sourcePath + "/" + map1.get(s) + "商务阅标记录.xls", sourcePath.replace("WEB-INF/page/bbgs/excel/专家组阅标记录/","")+busnessPath,
                        supplierList, title, "商务");
            }
        }
        return jsonMsg.booleanToMap(true);
    }

    /**
     * 导出投标人记录
     *
     * @param sourcePath
     * @return
     */
//    @Override
//    public Map exportBidderList_2(String sourcePath) {
//        File file = new File(sourcePath);
//        if (!file.exists()) {
//            //创建新的文件夹
//            file.mkdirs();
//        }
//        //取得批次名称
//        String batchName = "";
//        List<tblBbgsBatchModel> batchList = batchDao.listAll();
//        if (batchList.size() == 0) {
//            batchName = "";
//        } else if (batchList.size() == 1) {
//            batchName = batchList.get(0).getBatchName();
//        } else if (batchList.size() > 1) {
//            batchName = batchList.get(batchList.size() - 1).getBatchName();
//        }
//        List<tblBbgsPackageSupplierModel> list = bidSupplierDao.queryAllListByModel(new tblBbgsPackageSupplierModel());
//
//        //投标人清单导出======================================
//        //标段名称:供应商列表
//        Map<String, Map<String, String>> supplierMap = new LinkedHashMap<>();
//        //查询投标人清单
//        for (tblBbgsPackageSupplierModel bidSupplier : list) {
//            String bidName = bidSupplier.getBidAbbreviaion();
//            String bidAbbreviaion = bidName;
//            if (bidName.lastIndexOf("包") > -1) {
//                bidName = bidName.substring(0, bidName.lastIndexOf("包"));
//            }
//            if (supplierMap.keySet().contains(bidName)) {
//                if (supplierMap.get(bidName).keySet().contains(bidSupplier.getSupplier())) {
//                    supplierMap.get(bidName).put(bidSupplier.getSupplier(), supplierMap.get(bidName).get(bidSupplier
//                            .getSupplier()) + "," + bidSupplier.getPackagename());
//                } else {
//                    supplierMap.get(bidName).put(bidSupplier.getSupplier(), bidSupplier.getPackagename());
//                }
//            } else {
//                Map<String, String> biderSupplierList = new LinkedHashMap<>();
//                biderSupplierList.put(bidSupplier.getSupplier(), bidSupplier.getPackagename());
//                supplierMap.put(bidName, biderSupplierList);
//            }
//        }
//        tblBbgsTemplateModel template = new tblBbgsTemplateModel();
//        template.setIsDefault("Y");
//        template.setTemplateType("5");
//        List<tblBbgsTemplateModel> templates = templetDao.listAll(template);
//        if (templates == null || templates.size() == 0) {
//            return jsonMsg.toJosn("error", "请检查步骤①中投标人清单模板是否有默认模板");
//        } else {
//            String bidderTemplatePath = templates.get(0).getFilePath();
//            File f = new File(bidderTemplatePath);
//            if (!f.exists()) {
//                return jsonMsg.toJosn("error", "使用的投标人清单 模板文件不存在");
//            }
//            String filePath = ExcelUtil_poi.exportBiderList(sourcePath + "/" + "投标人清单记录", batchName + "投标人清单",
//                    supplierMap, bidderTemplatePath);
//            if (filePath != null) {
//                return jsonMsg.toJosn("success", filePath);
//            }
//            return jsonMsg.booleanToMap(false);
//        }
//    }
    @Override
    public Map exportBidderList(String sourcePath) {
        File file = new File(sourcePath+"WEB-INF/page/bbgs/excel/投标人清单");
        if (!file.exists()) {
            //创建新的文件夹
            file.mkdirs();
        }
        //取得批次名称
        String batchName = "";
        List<tblBbgsBatchModel> batchList = batchDao.listAll();
        if (batchList.size() == 0) {
            batchName = "";
        } else if (batchList.size() == 1) {
            batchName = batchList.get(0).getBatchName();
        } else if (batchList.size() > 1) {
            batchName = batchList.get(batchList.size() - 1).getBatchName();
        }
        List<tblBbgsPackageSupplierModel> list = bidSupplierDao.queryAllListByModel(new tblBbgsPackageSupplierModel());

        //投标人清单导出======================================
        //标段名称:供应商列表
        Map<String, Map<String, String>> supplierMap = new LinkedHashMap<>();
        //查询投标人清单
        for (tblBbgsPackageSupplierModel bidSupplier : list) {
            //标段简称
            String bidName = bidSupplier.getBidAbbreviaion();
//            标段简称
            String bidAbbreviaion = bidName;
            if (bidName.lastIndexOf("包") > -1) {
                //标名
                bidName = bidName.substring(0, bidName.lastIndexOf("包"));
            }
            if (supplierMap.keySet().contains(bidName)) {
                if (supplierMap.get(bidName).keySet().contains(bidSupplier.getSupplier())) {
                    supplierMap.get(bidName).put(bidSupplier.getSupplier(), supplierMap.get(bidName).get(bidSupplier
                            .getSupplier()) + "," + bidSupplier.getPackagename());
                } else {
                    supplierMap.get(bidName).put(bidSupplier.getSupplier(), bidSupplier.getPackagename());
                }
            } else {
                Map<String, String> biderSupplierList = new LinkedHashMap<>();
                biderSupplierList.put(bidSupplier.getSupplier(), bidSupplier.getPackagename());
                supplierMap.put(bidName, biderSupplierList);
            }
        }
        tblBbgsTemplateModel template = new tblBbgsTemplateModel();
        template.setIsDefault("Y");
        template.setTemplateType("5");
        List<tblBbgsTemplateModel> templates = templetDao.listAll(template);
        if (templates == null || templates.size() == 0) {
            return jsonMsg.toJosn("error", "请检查步骤①中投标人清单模板是否有默认模板");
        } else {
            String bidderTemplatePath = sourcePath+templates.get(0).getFilePath();
            File f = new File(bidderTemplatePath);
            if (!f.exists()) {
                return jsonMsg.toJosn("error", "使用的投标人清单 模板文件不存在");
            }
            String filePath = ExcelUtil_poi.exportBiderList(sourcePath+"WEB-INF/page/bbgs/excel/投标人清单/" + "投标人清单记录", batchName,
                    supplierMap, bidderTemplatePath);
            if (filePath != null) {
                return jsonMsg.toJosn("success", filePath);
            }
            return jsonMsg.booleanToMap(false);
        }
    }

    @Override
    public List queryPackageByBid(String bid) {
        List<tblBbgsBidInformationModel> list = bidInfoDao.queryAllBid();
        List<tblBbgsBidInformationModel> l = new ArrayList<>();
        for (tblBbgsBidInformationModel model : list) {
            String bidAbbreviaion = model.getBidAbbreviaion();
            if (bid.equals(bidAbbreviaion.substring(0, bidAbbreviaion.lastIndexOf("包")))) {
                l.add(model);
            }
        }
        return l;
    }

    /**
     * 保存excel中的专家信息
     *
     * @param
     * @return
     */
    @Override
    public Map saveExpert(Map<String, List<String>> m) {
        for (String str : m.keySet()) {
            tblBbgsExpertGroupModel check = new tblBbgsExpertGroupModel();
            check.setExpertGroupName(str);
            List<tblBbgsExpertGroupModel> list = expertDao.queryExpertGroup(check);
            //创建专家组
            String id = "";
            if (list != null && list.size() > 0) {
                id = list.get(0).getId();
            } else {
                tblBbgsExpertGroupModel model = new tblBbgsExpertGroupModel();
                id = UUID.randomUUID().toString().replace("-", "");
                model.setExpertGroupName(str);
                model.setId(id);
                if (str.contains("技术")) {
                    model.setType("技术");
                }
                if (str.contains("商务")) {
                    model.setType("商务");
                }
                if (str.contains("一") || str.contains("1") || str.contains("壹")) {
                    model.setSortid("1");
                }
                if (str.contains("二") || str.contains("2") || str.contains("贰")) {
                    model.setSortid("2");
                }
                if (str.contains("三") || str.contains("3") || str.contains("弎")) {
                    model.setSortid("3");
                }
                if (str.contains("四") || str.contains("4") || str.contains("肆")) {
                    model.setSortid("4");
                }
                if (str.contains("五") || str.contains("5") || str.contains("伍")) {
                    model.setSortid("5");
                }
                if (str.contains("六") || str.contains("6") || str.contains("陆")) {
                    model.setSortid("6");
                }
                if (str.contains("七") || str.contains("7") || str.contains("柒")) {
                    model.setSortid("7");
                }
                if (str.contains("八") || str.contains("8") || str.contains("捌")) {
                    model.setSortid("8");
                }
                if (str.contains("九") || str.contains("9") || str.contains("玖")) {
                    model.setSortid("9");
                }
                if (str.contains("十") || str.contains("10") || str.contains("拾")) {
                    model.setSortid("10");
                }
                expertDao.saveExpertGroup(model);
            }
            for (String string : m.get(str)) {
                tblBbgsExpertModel expertModel = new tblBbgsExpertModel();
                expertModel.setGroupId(id);
                expertModel.setId(UUID.randomUUID().toString().replace("-", ""));
                expertModel.setExpertName(string);
                expertDao.saveExpert(expertModel);
            }
        }
        return jsonMsg.booleanToMap(true);
    }

    @Override
    public Map delAllExpert() {
        int num = expertDao.delAllExpert();
        if (num > 0) {
            return jsonMsg.booleanToMap(true);
        } else if (num == 0) {
            return jsonMsg.toJosn("error", "已经全部删除");
        }
        return jsonMsg.toJosn("error", "删除失败");
    }

    /**
     * 查看是否能导入专家信息
     *
     * @return
     */
    @Override
    public boolean viewIsImport() {
        return expertDao.queryExpertCount() == 0;
    }

    @Override
    public String queryFlowState(String id) {
        tblBbgsFlowStateModel model = flowStateDao.queryMinIdByState();
        if (model.getId() != null && Integer.valueOf(model.getId()) <= Integer.valueOf(id)) {
            return model.getAlertMessage();
        }
        return null;
    }

    @Override
    public Map saveDataForE(MultipartFile[] files, String category) {
        CommonsMultipartFile f = (CommonsMultipartFile) files[0];
        String filepath = f.getFileItem().getName();
        String batchName = filepath.substring(0, filepath.indexOf("/"));
        //插入批次
        tblBbgsBatchModel tblBbgsBatch = new tblBbgsBatchModel(getUUid(), batchName, category);
        batchDao.saveBatch(tblBbgsBatch);
        Map<MultipartFile, Map> excelContent = this.readExcelList(files);
        for (MultipartFile fileKey : excelContent.keySet()) {
            Map<String, Map> sheetMap = excelContent.get(fileKey);
            for (String sheetKey : sheetMap.keySet()) {
                Map<String, Map> rowMap = sheetMap.get(sheetKey);
                for (String key : rowMap.keySet()) {
                    Map<String, String> oneRow = rowMap.get(key);
                    oneRow.put("id", UUID.randomUUID().toString().replace("-", ""));

                    List<String> existRowPrice = tendererDao.queryRowPrice(oneRow);
                    if (existRowPrice != null && existRowPrice.size() > 0) {
                        return jsonMsg.toJosn("error", existRowPrice.get(0) +
                                "与" + oneRow.get("filename") + "内容相同");
                    }
                    int i = tendererDao.insertRowPrice(oneRow);
//                    System.out.println(i);
                }
            }
        }
        //查询各个供应商所投标包的总报价
        List<tblBbgsTendererModel> tendererModels = tendererDao.querySupplierTotalPriceForBid();
        Set<String> bidAbbrSet = new LinkedHashSet();
        for (tblBbgsTendererModel model : tendererModels) {
            bidAbbrSet.add(model.getBidAbbreviaion());
            model.setId(this.getUUid());
            //插入投标人表
            tendererDao.saveTenderer(model);
            tblBbgsPackageSupplierModel packageSupplier = new tblBbgsPackageSupplierModel();
            packageSupplier.setId(UUID.randomUUID().toString().replace("-", ""));
            packageSupplier.setBidAbbreviaion(model.getBidAbbreviaion());
            packageSupplier.setBid(model.getBidAbbreviaion().substring(0, model.getBidAbbreviaion
                    ().indexOf("-")));
            packageSupplier.setSupplier(model.getSupplier());
            String packagename = model.getBidAbbreviaion().substring(model.getBidAbbreviaion()
                    .lastIndexOf("包"), model.getBidAbbreviaion().length());
            packageSupplier.setPackagename(packagename);
            System.out.println(model.getOffer().replace(",",""));
            packageSupplier.setOffer(model.getOffer().replace(",",""));
            //插入标包和供应商关联关系
            bidSupplierDao.saveBid(packageSupplier);

        }
        //插入标包信息表
        for (String bidAbbr : bidAbbrSet) {
            tblBbgsBidInformationModel bidInfo = new tblBbgsBidInformationModel();
            bidInfo.setBidAbbreviaion(bidAbbr);
            bidInfo.setId(UUID.randomUUID().toString().replace("-", ""));
            bidInfo.setInvalid("1");
            bidInfo.setFlag("N");
            bidInfoDao.saveBidInfo(bidInfo);
        }
        flowStateDao.updateStateById("501", "1");
        flowStateDao.updateStateById("601", "1");
        return jsonMsg.toJosn("success", "");
    }

    @Override
    public List queryPackageByBid_new(String bids) {
        List<tblBbgsBidInformationModel> list = null;
        if(bids==null||"".equals(bids)||"".equals(bids.trim())){
           list = bidInfoDao.queryAllBid();
        }else {
            List<String> bidList = Arrays.asList(bids.split(","));
            list = bidInfoDao.queryAllBidUseIN(bidList);
        }
        return list;
    }

    @Override
    public List getBidNameList() {
        return bidInfoDao.getBidNameList();
    }

    private Map readExcelList(MultipartFile[] files) {
        Map<MultipartFile, Map> result = new LinkedHashMap();
        for (MultipartFile file : files) {
            Map fileMap = this.readExcel(file, 2, this.getExcelArray());
            result.put(file, fileMap);
        }
        return result;
    }

    private List<ArrayList> getExcelArray() {
        List<ArrayList> result = new ArrayList();
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        result.add(list1);
        result.add(list2);
        list1.add("序号");
        list2.add("row_type");
        list1.add("单价\n" +
                "（含税 元）");
        list2.add("row_price");
        list1.add("单价加权系数(该系数仅作为价格评审使用)");
        list2.add("row_price_weight");
        return result;
    }

    private Map readExcel(MultipartFile file, int titleRowNum, List<ArrayList> rowMapKeys) {
        Workbook wb;
        Map result = new LinkedHashMap();
        try {
            InputStream is = new BufferedInputStream(file.getInputStream(), 1024);
            if (file.getOriginalFilename().endsWith(".xls")) {
                wb = new HSSFWorkbook(is);
            } else if (file.getOriginalFilename().endsWith(".xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook();
            }
//            String filename = file.getOriginalFilename();
            CommonsMultipartFile f = (CommonsMultipartFile) file;
            String filename = f.getFileItem().getName();

            int sheetNum = wb.getNumberOfSheets();
            for (int i = 0; i < sheetNum; i++) {
                Sheet sheet = wb.getSheetAt(i);
                String bidAbbr = sheet.getRow(0).getCell(2).getStringCellValue().trim();
                System.out.println(filename);
                String s1 = bidAbbr.substring(0,3);
                String s2 = bidAbbr.substring(3,bidAbbr.length());
                bidAbbr = s1 +"-"+ s2;
                String supplier = sheet.getRow(1).getCell(2).getStringCellValue().trim();
                String totalPrice = formatExcelCellTypeToString(sheet.getRow(sheet.getLastRowNum()).getCell(11));
                totalPrice = totalPrice.trim();
                totalPrice = totalPrice.replace("万元","");
                totalPrice = totalPrice.replace("元","");
                if (totalPrice.trim()==""){
                    totalPrice = "0";
                }
                totalPrice = this.decimalFormatNum(Double.valueOf(totalPrice) / 10000, 7);
                System.out.println(totalPrice);
                Map sheetMap = new LinkedHashMap();
                result.put(wb.getSheetName(i), sheetMap);
                int rowNum = sheet.getPhysicalNumberOfRows();
                for (int j = titleRowNum + 1; j < rowNum - 1; j++) {
                    Map rowMap = new LinkedHashMap();
                    sheetMap.put(String.valueOf(j), rowMap);
                    Row row = sheet.getRow(j);
                    int colNum = row.getPhysicalNumberOfCells();
                    for (int k = 0; k < colNum; k++) {
                        Cell cell = row.getCell(k);
//                        System.out.println(i+"sheet页"+j+"行"+k+"列");
                        String colContent = this.formatExcelCellTypeToString(cell);
                        String title = sheet.getRow(titleRowNum).getCell(k).getStringCellValue().trim();
                        int keyIndex = rowMapKeys.get(0).indexOf(title);
                        if (keyIndex >= 0) {
                            rowMap.put("filename", filename + "文件中" + wb.getSheetName(i) + "sheet页,第" + j + "行，第" + k + "列");
                            rowMap.put("bid_abbreviaion", bidAbbr);
                            rowMap.put("supplier", supplier);
                            rowMap.put("total_price", totalPrice.toString());
                            rowMap.put(rowMapKeys.get(1).get(keyIndex), colContent);
                        }
                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return result;
    }


//    CellType 类型 值
//    CELL_TYPE_NUMERIC 数值型 0
//    CELL_TYPE_STRING 字符串型 1
//    CELL_TYPE_FORMULA 公式型 2
//    CELL_TYPE_BLANK 空值 3
//    CELL_TYPE_BOOLEAN 布尔型 4
//    CELL_TYPE_ERROR 错误 5

    /**
     * cell内容转为String
     *
     * @param cell
     * @return
     */
    private String formatExcelCellTypeToString(Cell cell) {
        String result = "";
        if(cell==null){
            result = "0";
        } else if (cell.getCellType() == 0) {
            result = this.decimalFormatNum(cell.getNumericCellValue(), 7);
        } else if (cell.getCellType() == 1) {
            result = cell.getStringCellValue().trim();
        } else if (cell.getCellType() == 2) {
            try {
                result = String.valueOf(cell.getNumericCellValue());
            } catch (IllegalStateException e) {
                result = String.valueOf(cell.getRichStringCellValue());
            }
        } else if (cell.getCellType() == 3) {
            result = "";
        } else if (cell.getCellType() == 4) {
            boolean b = cell.getBooleanCellValue();
            if (b) {
                result = "true";
            } else {
                result = "false";
            }
        } else if (cell.getCellType() == 5) {
            result = "";
        }
        return result;
    }

    private String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    // 格式化小数点位数
    public String decimalFormatNum(double num, int ws) {
        return String.format("%." + ws + "f", num);
    }
}
