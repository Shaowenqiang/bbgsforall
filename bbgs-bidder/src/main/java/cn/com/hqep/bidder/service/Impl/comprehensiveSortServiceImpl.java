package cn.com.hqep.bidder.service.Impl;

import cn.com.hqep.bidder.common.MySortList;
import cn.com.hqep.bidder.common.ZipUtils;
import cn.com.hqep.bidder.common.exportSortExcelUtil;
import cn.com.hqep.bidder.common.jsonMsg;
import cn.com.hqep.bidder.dao.*;
import cn.com.hqep.bidder.model.tblBbgsFlowStateModel;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierModel;
import cn.com.hqep.bidder.model.tblBbgsPriceInformationModel;
import cn.com.hqep.bidder.model.tblBbgsTechnologyBusinessModel;
import cn.com.hqep.bidder.service.comprehensiveSortService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.ContextLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by hq6 on 2017-10-25.
 */
@Repository
public class comprehensiveSortServiceImpl implements comprehensiveSortService {

    @Autowired
    comprehensiveSortDao sortDao;

    @Autowired
    bidderDao bidderDao;

    @Autowired
    priceInfoDao priceInfoDao;

    @Autowired
    technologyAndBusinessDao technologyAndBusinessDao;


    /**
     * 计算综合排名
     * 计算结果 根据Id存入排名信息
     * @return 计算结果true or false
     */
    public Map updateSortDataById(){
        Map resultMap = new HashMap();

        //查询流程进行到的位置
        tblBbgsFlowStateModel model1 = bidderDao.queryMinIdByState();
        if (model1.getId() != null && Integer.valueOf(model1.getId()) < 801) {
            resultMap.put("state",false);
            resultMap.put("msg",model1.getAlertMessage());
            return  resultMap;
        }

        String supplier = null;                                     //供应商
        String bidAbbreviaion = null;                               //标段简称
        double technologyWeight = 0;                                //技术权重
        double businessWeight = 0;                                  //商务权重
        double priceWeight = 0;                                     //价格权重
        String priceScore = null;                                   //价格得分
        double totalScore = 0;                                      //总分
        double temp = 0;                                            //中间存储变量
        int index = 0;                                              //打分个数
        tblBbgsPackageSupplierModel packageSupplierModel = new tblBbgsPackageSupplierModel();            //数据 存储 的 目标表
        tblBbgsPriceInformationModel priceInfoModel = new tblBbgsPriceInformationModel();                //价格打分表 获取价格得分
        tblBbgsTechnologyBusinessModel technologyBusinessModel = new tblBbgsTechnologyBusinessModel();   //获取技术商务得分
        List<tblBbgsPackageSupplierModel> listName = new ArrayList<tblBbgsPackageSupplierModel>();        //存储去除重复的 所有 标段简称                                       //去除重复的标段简称
        List<tblBbgsPackageSupplierModel> packageSupplierModelList = new ArrayList<tblBbgsPackageSupplierModel>(); //数据 存储 的 目标表 ==== list集合
        List<tblBbgsPriceInformationModel> priceInfoModelList = null;                                    //价格打分表 获取价格得分 ====  list集合
        List<tblBbgsTechnologyBusinessModel> technologyBusinessModelList = null;                         //获取技术商务得分 ==== list集合
        try {
            List<tblBbgsPackageSupplierModel> list = sortDao.queryList();                         //查询 关系表 中 所有 的数据 去除废标流标
            listName = sortDao.queryListToName();                                                 //查询所有标段 （去除重复）去除（流标）
            if (listName != null && listName.size()<1){
                resultMap.put("state",false);
                resultMap.put("msg","当前无有效数据！");
                return  resultMap;
            }
            for (int k = 0; k < listName.size(); k++) {
                bidAbbreviaion = listName.get(k).getBidAbbreviaion();//查询去除重复的 所有 标段简称
                if (list != null && list.size()>0) {
                    for (int i = 0; i < list.size(); i++) {
                        packageSupplierModel = list.get(i);
                        if (!bidAbbreviaion.equals(packageSupplierModel.getBidAbbreviaion())) {
                            continue;
                        }
                        bidAbbreviaion = list.get(i).getBidAbbreviaion();
                        supplier = list.get(i).getSupplier();
                        //清空总分
                        totalScore = 0;
                        //获取 价格得分
                        priceInfoModel.setBidAbbreviaion(bidAbbreviaion);
                        priceInfoModel.setSupplier(supplier);
                        priceInfoModelList = priceInfoDao.queryListByNameAndSupplier(priceInfoModel);
                        if (priceInfoModelList != null && priceInfoModelList.size() > 0) {
                            //得到三个权重
                            technologyWeight = Double.parseDouble(priceInfoModelList.get(0).getTechnologyWeight());   //技术权重
                            businessWeight = Double.parseDouble(priceInfoModelList.get(0).getBusinessWeight());         //商务权重
                            priceWeight = Double.parseDouble(priceInfoModelList.get(0).getPriceWeight());             //价格权重
                            //得到 价格得分 乘以 权重得分
                            if(priceInfoModelList.get(0).getPriceScore() != null){
                                priceScore = priceInfoModelList.get(0).getPriceScore();
                                temp = Double.parseDouble(priceScore) * priceWeight;
                                if(temp != 0){
//                                    String  f2 = new java.text.DecimalFormat("#.00").format(temp);
                                        String  f2 = String.format("%.2f", temp);
                                        packageSupplierModel.setPriceScore(f2);
                                        totalScore += Double.parseDouble(f2);
                                }else{
                                    packageSupplierModel.setPriceScore("0.00");
                                }
                            }else {
                                packageSupplierModel.setPriceScore("0.00");
                            }

                            //得到技术得分
                            technologyBusinessModel.setBidAbbreviaion(bidAbbreviaion);
                            technologyBusinessModel.setSupplier(supplier);
                            technologyBusinessModel.setType("技术");
                            technologyBusinessModelList = technologyAndBusinessDao.queryByParam(technologyBusinessModel);
                            if(technologyBusinessModelList.size()<1){
                                resultMap.put("state",false);
                                resultMap.put("msg","请先导入专家打分！");
                                return  resultMap;
                            }
                            index = 0;
                            temp = 0;
                            for (int j = 0; j < technologyBusinessModelList.size(); j++) {
                                if (technologyBusinessModelList.get(j).getPrice() != null) {
                                    index++;
                                    temp += Double.parseDouble(technologyBusinessModelList.get(j).getPrice());
                                }
                            }
                            if (index != 0){
                                double price = temp * technologyWeight / index;
                                if (price != 0){
//                                    String f2 = new java.text.DecimalFormat("#.00").format(price);
                                    String f2 = String.format("%.2f", price);
                                    packageSupplierModel.setTechnologyScore(f2);  //存入技术分
                                    totalScore += Double.parseDouble(f2);

                                }else {
                                    packageSupplierModel.setTechnologyScore("0.00");  //存入商务分
                                }
                            }else {
                                packageSupplierModel.setTechnologyScore("0.00");  //存入商务分
                            }
                            //得到商务得分
                            technologyBusinessModel.setType("商务");
                            technologyBusinessModelList = technologyAndBusinessDao.queryByParam(technologyBusinessModel);
                            index = 0;
                            temp = 0;
                            for (int j = 0; j < technologyBusinessModelList.size(); j++) {
                                if (technologyBusinessModelList.get(j).getPrice() != null) {
                                    index++;
                                    temp += Double.parseDouble(technologyBusinessModelList.get(j).getPrice());
                                }
                            }
                            if (index != 0){
                                double priceBusiness = temp * businessWeight / index;
                                if (priceBusiness != 0){
//                                    String f2 = new java.text.DecimalFormat("#.00").format(priceBusiness);
                                    String f2 = String.format("%.2f", priceBusiness);
                                    packageSupplierModel.setBusinessScore(f2);  //存入商务分
                                    totalScore += Double.parseDouble(f2);

                                }else {
                                    packageSupplierModel.setBusinessScore("0.00");  //存入商务分
                                }
                            }else {
                                packageSupplierModel.setBusinessScore("0.00");  //存入商务分
                            }
                            //存入总分
                            if (totalScore != 0 ){
//                                String f2 = new java.text.DecimalFormat("#.00").format(totalScore);
                                String f2 = String.format("%.2f", totalScore);
                                packageSupplierModel.setTotalScore(f2);
                            }else{
                                packageSupplierModel.setTotalScore("0.00");
                            }
                            packageSupplierModelList.add(packageSupplierModel);
                        }else{
                            resultMap.put("state",false);
                            resultMap.put("msg","请先计算评标价格得分！");
                            return  resultMap;
                        }
                    }
                    //将 list 进行排序
                    MySortList<tblBbgsPackageSupplierModel> sortListUtil = new MySortList<tblBbgsPackageSupplierModel>();
                    sortListUtil.sortByMethod(packageSupplierModelList, "getTotalScore", "double", true);
                    System.out.println(packageSupplierModelList);
                    //处理排序后的 list
                    for (int n = 1; n < packageSupplierModelList.size(); n++) {
                        if (n==1){
                            packageSupplierModelList.get(0).setSort(Integer.toString( 1));
                            sortDao.updateSortDataById(packageSupplierModelList.get(0));
                        }
                        if (Double.parseDouble(packageSupplierModelList.get(n).getTotalScore())
                                == Double.parseDouble(packageSupplierModelList.get(n-1).getTotalScore())){
                            if (Double.parseDouble(packageSupplierModelList.get(n).getTechnologyScore())
                                    == Double.parseDouble(packageSupplierModelList.get(n-1).getTechnologyScore())){
                                if (Double.parseDouble(packageSupplierModelList.get(n).getPriceScore())
                                        == Double.parseDouble(packageSupplierModelList.get(n-1).getPriceScore())){
                                    packageSupplierModelList.get(n).setSort(packageSupplierModelList.get(n-1).getSort());
                                    sortDao.updateSortDataById(packageSupplierModelList.get(n));
                                    continue;
                                }
                            }
                        }
                        packageSupplierModelList.get(n).setSort(Integer.toString(n + 1));
                        //存储 实体
                        sortDao.updateSortDataById(packageSupplierModelList.get(n));
                    }
                    packageSupplierModelList = new ArrayList<tblBbgsPackageSupplierModel>();
                }else {
                    resultMap.put("state",false);
                    resultMap.put("msg","当前无有效数据！");
                    return  resultMap;
                }
            }
        }catch (Exception exp){
            exp.printStackTrace();
            resultMap.put("state",false);
            resultMap.put("msg","数据异常！");
            return  resultMap;
        }
        bidderDao.updateStateById("801","1");
        resultMap.put("state",true);
        resultMap.put("msg","操作成功！");
        return resultMap;
    }




    public List<tblBbgsPackageSupplierModel> querySortList(tblBbgsPackageSupplierModel model) {
        List<tblBbgsPackageSupplierModel> list = new ArrayList<tblBbgsPackageSupplierModel>();
        try {
            list = sortDao.querySortList(model);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }


    /**
     * 查询：
     * 查询所有技术/商务打分情况表数据
     * 使用：导出平均分汇总表
     *
     * @return 实体集合
     */
    public List<tblBbgsTechnologyBusinessModel> queryListToTechnologyBusiness() {
        List<tblBbgsTechnologyBusinessModel> list = new ArrayList<tblBbgsTechnologyBusinessModel>();
        try {
            list = technologyAndBusinessDao.queryListByNameAndSupplierAndType(new tblBbgsTechnologyBusinessModel());

        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }

    /**
     * 查询：
     * 查询所有专家名字
     *
     * @return 实体集合
     */
    public List<String> queryExpertNames() {
        List<String> list = new ArrayList<String>();
        try {
            list = technologyAndBusinessDao.queryExpertNames();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }

    /***
     * 查询：
     * 查询去除重复的供应商和标段简称
     * @return 实体集合
     */
    public List<tblBbgsTechnologyBusinessModel> queryListToBidAbbreviaionSupplier() {
        List<tblBbgsTechnologyBusinessModel> list = new ArrayList<tblBbgsTechnologyBusinessModel>();
        try {
            list = technologyAndBusinessDao.queryListToBidAbbreviaionSupplier();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return list;
    }


    /**
     * 更新：
     * 处理计算结果
     * 选择第一，其余并列第一转为第二
     * @param model 条件实体
     * @return
     */
    public boolean dealSideFirstData(tblBbgsPackageSupplierModel model){
        boolean flag = true;
        try {
           sortDao.updateOneToTwo(model);
           sortDao.updateByNameAndSupplier(model);
        }catch (Exception exp){
            exp.printStackTrace();
            flag = false;
        }
        return flag;
    }


    /***
     * 修改 ==》导出 平均分汇总，综合排名
     * @return  文件名称，格式：文件.zip
     */
    public Map queryScoreGroupList() {
        Map result = new HashMap();
        try {
            //查询流程进行到的位置
            tblBbgsFlowStateModel model1 = bidderDao.queryMinIdByState();
            if (model1.getId() != null && Integer.valueOf(model1.getId()) <= 801) {
                result.put("flag","fail");
                result.put("msg",model1.getAlertMessage());
                return  result;
            }
            String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
            String zipName = "平均分汇总、综合排序表";//String.valueOf(System.currentTimeMillis());
            String zipDir = webPath + "WEB-INF/page/bbgs/excel/" + zipName;
            File delF = new File(zipDir);
            if (delF.exists()) {
                deleteDir(delF);
            }
            File delFZ = new File(zipDir+".zip");
            if (delFZ.exists()) {
                deleteDir(delFZ);
            }
            List listNum = bidderDao.queryDistinctBidAbbreviaionList();
            if (listNum.size()<1){
                result.put("flag","fail");
                result.put("msg","无有效数据生成导出文件");
                return result;
            }

            tblBbgsPackageSupplierModel modelParam = new tblBbgsPackageSupplierModel();
            modelParam.setFlagInvalid("0");
            modelParam.setFlagBidInvalid("0");
            modelParam.setFlagBit("0");
            modelParam.setOffer("无");
            List<tblBbgsPackageSupplierModel> sortList = sortDao.querySortList(modelParam);
            if (sortList.size()>0){
                if (sortList.get(0).getTotalScore() != null){
                    new exportSortExcelUtil().exportSortExcel(zipDir,"综合排序","评分汇总表",sortList);
                }else{
                    result.put("flag","fail");
                    result.put("msg","请先计算综合排名！");
                    return result;
                }
            }else {
                result.put("flag","fail");
                result.put("msg","无有效数据生成导出文件");
                return result;
            }

            //创建组 -对应组数据 变量map
            Map<String, List<tblBbgsTechnologyBusinessModel>> map =
                    new LinkedHashMap<String, List<tblBbgsTechnologyBusinessModel>>();
            //查询所有打分信息 得到List
            List<tblBbgsTechnologyBusinessModel> list = technologyAndBusinessDao.queryScoreGroupList();
            if (list.size() < 1){
                result.put("flag","fail");
                result.put("msg","请先导入专家打分！");
                return result;
            }
            //变量打分信息 分组 存入 map<String,List>
            for (tblBbgsTechnologyBusinessModel model : list) {
                System.out.println(model.getBidAbbreviaion());
                System.out.println(model.getExpertGroupName());
                if (map.keySet().contains(model.getExpertGroupName())) {
                    map.get(model.getExpertGroupName()).add(model);
                } else {
                    List<tblBbgsTechnologyBusinessModel> l = new ArrayList<tblBbgsTechnologyBusinessModel>();
                    l.add(model);
                    map.put(model.getExpertGroupName(), l);
                }
            }
            String flag = "";
            //遍历组
            for (String groupName:map.keySet()){
                //得到组内数据
                List<tblBbgsTechnologyBusinessModel> groupList  = map.get(groupName);
                tblBbgsTechnologyBusinessModel indexModel = new tblBbgsTechnologyBusinessModel();
                if (groupName!=null&&groupName.startsWith("技术")){
                    indexModel.setExpertGroupName(groupName);
                    indexModel.setType("技术");
                    List<tblBbgsTechnologyBusinessModel> rowList = technologyAndBusinessDao.queryByGroupName(indexModel);
                    //导出 当前组 excel
                    flag = exportPriceExcel(groupList,rowList,groupName,zipDir,"技术");
                }
                if (groupName!=null&&groupName.startsWith("商务")){
                    indexModel.setExpertGroupName(groupName);
                    indexModel.setType("商务");
                    List<tblBbgsTechnologyBusinessModel> rowList = technologyAndBusinessDao.queryByGroupName(indexModel);
                    //导出 当前组 excel
                    flag = exportPriceExcel(groupList,rowList,groupName,zipDir,"商务");
                }
                if (!"success".equals(flag)){
                    result.put("flag","fail");
                    result.put("msg","导出失败！");
                    return result;
                }
            }

            if (list.size() > 0 && list.get(0).getPrice() != null) {
                ZipUtils.zip(zipDir, zipDir + ".zip", true);
                result.put("flag","success");
                result.put("msg","导出成功！");
                result.put("fileName",zipName + ".zip");
                return result;
            }
        } catch (Exception exp) {
            exp.printStackTrace();
            result.put("flag","fail");
            result.put("msg","导出失败！");
            return result;
        }
        return result;
    }


    /**
     * 导出：
     * 导出方法===》导出平均分 调用该方法
     * @param list 打分的分数List
     * @param rowList 供应商 标段简称 row
     * @param groupName 组名 作为文件名称
     * @param parentOPath 文件路径
     * @param type 类型
     * @return 返回结果
     */
    public String exportPriceExcel(List<tblBbgsTechnologyBusinessModel> list,List<tblBbgsTechnologyBusinessModel> rowList,String groupName,String parentOPath,String type){
        String result ="";
        String[] addNum= {"A","B","C","D","E","F","G","H","I","G","K","L","M","N","O","P","Q","R","S","T","U","V","W","S","Y","Z"};
        try {
            String webPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/");
            String tempPath = webPath + "export/sort/平均分汇总模板.xlsx";
            OutputStream outputStream;
            double technologyWeight = 0;                                //技术权重
            double businessWeight = 0;                                  //商务权重
            double temp = 0;
            //读取模板
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(tempPath));
            Sheet sheet = workbook.getSheetAt(0);
            sheet.setForceFormulaRecalculation(true);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(2);

            // 生成一个样式
            XSSFCellStyle infostyle = workbook.createCellStyle();
            // 设置这些样式
            infostyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            infostyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            infostyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            infostyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            infostyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            infostyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            infostyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            infostyle.setWrapText(true);
            // 生成一个字体
            XSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("宋体");
            // 把字体应用到当前的样式
            infostyle.setFont(font);

            // 生成一个样式
            XSSFCellStyle styleTitle = workbook.createCellStyle();
            // 设置这些样式
            styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            styleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            styleTitle.setWrapText(true);
            // 生成一个字体
            XSSFFont fontTitle = workbook.createFont();
            fontTitle.setFontHeightInPoints((short) 12);
            fontTitle.setFontName("宋体");
            fontTitle.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            styleTitle.setFont(fontTitle);

            // 生成一个double样式
            XSSFCellStyle styleDouble = workbook.createCellStyle();
            // 设置这些样式
            styleDouble.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
            styleDouble.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
            styleDouble.setBorderBottom(HSSFCellStyle.BORDER_THIN);//边框
            styleDouble.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            styleDouble.setBorderRight(HSSFCellStyle.BORDER_THIN);
            styleDouble.setBorderTop(HSSFCellStyle.BORDER_THIN);
            styleDouble.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            styleDouble.setWrapText(true);
            // 生成一个字体
            XSSFFont fontDouble = workbook.createFont();
            fontDouble.setFontHeightInPoints((short) 12);
            fontDouble.setFontName("宋体");
            fontDouble.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            // 把字体应用到当前的样式
            styleDouble.setFont(fontDouble);
            //生成单元格格式
            XSSFDataFormat df = workbook.createDataFormat();
            //设置单元格格式
            styleDouble.setDataFormat(df.getFormat("#,##0.00"));

            List<tblBbgsTechnologyBusinessModel> exportList = new ArrayList<tblBbgsTechnologyBusinessModel>();
            //创建专家组List （为后续导出excel使用）
            List<String> expertNames = new ArrayList<String>();
            //创建专家组set（去除重复）
            Set<String> names = new HashSet<String>();
            List<tblBbgsTechnologyBusinessModel> indexModelList = new ArrayList<tblBbgsTechnologyBusinessModel>();
            tblBbgsTechnologyBusinessModel indexModel = new tblBbgsTechnologyBusinessModel();
                //将专家存入set，去除重复，得到组内专家
                for (tblBbgsTechnologyBusinessModel model :list){
                    if (type.equals(model.getType())){
                        names.add(model.getExpertName());
                        exportList.add(model);
                    }
                }
                //遍历专家 将存入专家List （为后续导出excel使用）
                Iterator<String> it = names.iterator();
                while(it.hasNext()){
                    expertNames.add(it.next());
                }
                //将数据写入excel
                if (exportList != null && exportList.size()>0 && exportList.get(0).getPrice() != null){
                    String addMergedRegionSign = "";
                    int startRowNum = 3;
                    //遍历导出数据
                    for (int i=0;i<rowList.size();i++){
                        if (i==0){
                            addMergedRegionSign = rowList.get(0).getBidAbbreviaion();
                            startRowNum = 3;
                        }else if(i==rowList.size()-1){
                            if (addMergedRegionSign.equals(rowList.get(i).getBidAbbreviaion())){
                                sheet.addMergedRegion(new CellRangeAddress(startRowNum, i+3, 0, 0));
                            }else{
                                sheet.addMergedRegion(new CellRangeAddress(startRowNum, i+2, 0, 0));
                            }
                        }else{
                            if (! addMergedRegionSign.equals(rowList.get(i).getBidAbbreviaion())){
                                sheet.addMergedRegion(new CellRangeAddress(startRowNum, i+2, 0, 0));
                                addMergedRegionSign = rowList.get(i).getBidAbbreviaion();
                                startRowNum = i+3;
                            }
                        }
                        row = sheet.createRow(i+3);
                        row.setHeight((short)(16 * 38));
                        cell = row.createCell(0);
                        cell.setCellStyle(infostyle);
                        cell.setCellValue(rowList.get(i).getBidAbbreviaion().toString());
                        cell = row.createCell(1);
                        cell.setCellStyle(infostyle);
                        cell.setCellValue(rowList.get(i).getSupplier().toString());
                        //遍历专家
                        for (int j=0;j<expertNames.size();j++){
                            Row rowexpert = sheet.getRow(2);
                            Cell cellExpert = rowexpert.createCell(j+2);
                            cellExpert.setCellStyle(styleTitle);
                            cellExpert.setCellValue(expertNames.get(j).toString());
                            rowexpert = sheet.getRow(1);
                            cellExpert = rowexpert.createCell(j+2);
                            cellExpert.setCellStyle(styleTitle);
                            //查找数据写入
                            for (int k=0;k<exportList.size();k++){
                                if (exportList.get(k).getExpertName().equals(expertNames.get(j))
                                        && exportList.get(k).getSupplier().equals(rowList.get(i).getSupplier())
                                        && exportList.get(k).getBidAbbreviaion().equals(rowList.get(i).getBidAbbreviaion())
                                        && exportList.get(k).getType().equals("技术")){
                                    if (exportList.get(k).getTechnologyWeight() != null){
                                        technologyWeight = Double.parseDouble(exportList.get(k).getTechnologyWeight());     //技术权重
                                        temp = technologyWeight * Double.parseDouble(exportList.get(k).getPrice());
//                                        BigDecimal b = new BigDecimal(temp);
//                                        double price = b.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
//                                        String  f2 = new java.text.DecimalFormat("#.00").format(temp);
                                        String f2 = String.format("%.2f", temp);
                                        double price = Double.parseDouble(f2);
                                        cell =  row.createCell(j+2);
                                        cell.setCellStyle(styleDouble);
                                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                        cell.setCellValue(price);
                                    }
                                }
                                if (exportList.get(k).getExpertName().equals(expertNames.get(j))
                                        && exportList.get(k).getSupplier().equals(rowList.get(i).getSupplier())
                                        && exportList.get(k).getBidAbbreviaion().equals(rowList.get(i).getBidAbbreviaion())
                                        && exportList.get(k).getType().equals("商务")){
                                    if (exportList.get(k).getBusinessWeight() != null){
                                        businessWeight = Double.parseDouble(exportList.get(k).getBusinessWeight());     //商务权重
                                        temp = businessWeight * Double.parseDouble(exportList.get(k).getPrice());
//                                        BigDecimal b = new BigDecimal(temp);
//                                        double price = b.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
//                                        String  f2 = new java.text.DecimalFormat("#.00").format(temp);
                                        String f2 = String.format("%.2f", temp);
                                        double price = Double.parseDouble(f2);
                                        cell =  row.createCell(j+2);
                                        cell.setCellStyle(styleDouble);
                                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                        cell.setCellValue(price);
                                    }
                                }
                            }
                        }
                        cell = row.createCell(expertNames.size()+2);
                        cell.setCellType(Cell.CELL_TYPE_FORMULA);
                        String str = "AVERAGE(C"+(i+4)+":"+addNum[expertNames.size()+1]+(i+4)+")";
                        cell.setCellStyle(styleDouble);
                        cell.setCellFormula(str);
                    }

                    //合并评委打分
                    sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, expertNames.size()+1));
                    cell = sheet.getRow(1).getCell(2);
                    cell.setCellStyle(styleTitle);
                    cell.setCellValue("评委打分");
                    //合并平均分列
                    sheet.addMergedRegion(new CellRangeAddress(1, 2, expertNames.size()+2, expertNames.size()+2));
                    cell = sheet.getRow(1).createCell(expertNames.size()+2);
                    cell.setCellStyle(styleTitle);
                    cell.setCellValue("平均得分");
                    cell = sheet.getRow(2).createCell(expertNames.size()+2);
                    cell.setCellStyle(styleTitle);
                    cell.setCellValue("");
                    //写入标题
                    sheet.getRow(0).getCell(1).setCellValue("评分汇总表("+list.get(0).getType()+")");


                    File groupFile = new File(parentOPath);
                    if (!groupFile.exists()) {
                        groupFile.mkdirs();
                    }
                    File dirFile = new File(parentOPath+"/"+ type);
                    if (!dirFile.exists()) {
                        dirFile.mkdirs();
                    }
                    String fileName = null;
                    if (groupName == null){
                        fileName = type + "平均分汇总.xlsx";
                    }else {
                        fileName = groupName + "平均分汇总.xlsx";
                    }
                    File excelFile = new File(dirFile, fileName);
                    outputStream = new FileOutputStream(excelFile);
                    workbook.write(outputStream);
                    outputStream.close();
                }else {
                    result = "listPriceNull";
                    return result;
                }
            result = "success";
        } catch (Exception exp) {
            exp.printStackTrace();
            result = "Fail";
        }
        return result;
    }


    /***
     * 删除文件夹 及文件夹下的所有文件
     * @param dir 文件
     * @return 删除结果 true or false
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null){
                for (int i=0; i<children.length; i++) {
                    boolean success = deleteDir(new File(dir, children[i]));
                    if (!success) {
                        return false;
                    }
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

}

