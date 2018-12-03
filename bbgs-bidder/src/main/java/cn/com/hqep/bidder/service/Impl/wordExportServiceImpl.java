package cn.com.hqep.bidder.service.Impl;

import cn.com.hqep.bidder.common.DateUtil;
import cn.com.hqep.bidder.common.ExportWord;
import cn.com.hqep.bidder.common.jsonMsg;
import cn.com.hqep.bidder.dao.bidderDao;
import cn.com.hqep.bidder.dao.technologyAndBusinessDao;
import cn.com.hqep.bidder.dao.wordExportDao;
import cn.com.hqep.bidder.model.tblBbgsBidInformationModel;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierModel;
import cn.com.hqep.bidder.service.wordExportService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class wordExportServiceImpl implements wordExportService {
    @Autowired
    technologyAndBusinessDao t_bDao;
    @Autowired
    bidderDao bidderDao;
    @Autowired
    wordExportDao dao;

    public Map export(OutputStream outputStream, String sourcePath) {

        //查询tbl_bbgs_tenderer获取所有包
        List<tblBbgsBidInformationModel> bidInfos = dao.queryAllBidInfo();
        Map<String, Integer> map = new LinkedHashMap<>();
        //成功包数量
        int successPackageNum = 0;
        //流标的包数量
        int failPackageNum = 0;
        //第二个表格数据
        List<Map> list2 = new ArrayList<>();
        //流标的标段
        List<Map> list4 = new ArrayList<>();
        for (tblBbgsBidInformationModel model : bidInfos) {
            Map m = new LinkedHashMap();
            String key = model.getBidAbbreviaion().substring(0, model.getBidAbbreviaion().lastIndexOf("包"));

            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                m.put("分标编号", "");
                m.put("分标号", key.substring(0, key.indexOf("-") + 1));
                m.put("分标名称", key.substring(key.indexOf("-") + 1, key.length()));
                if (model.getInvalid().equals("0"))
                    list4.add(m);
                if ( model.getTechnologyWeight() == null){
                    model.setTechnologyWeight("0.00");
                    model.setBusinessWeight("0.00");
                    model.setPriceWeight("0.00");
                }
                m.put("比例", "价格：" + model.getTechnologyWeight().substring((model.getTechnologyWeight().indexOf(".") + 1), model.getTechnologyWeight().length()) + "%、商务：" + model.getBusinessWeight().substring((model.getBusinessWeight().indexOf(".") + 1), model.getBusinessWeight().length()) + "%、价格：" + model.getPriceWeight().substring((model.getPriceWeight().indexOf(".") + 1), model.getPriceWeight().length()) + "%");
                list2.add(m);
                map.put(key, 1);
            }
            if (model.getInvalid().equals("1")) {
                successPackageNum = successPackageNum + 1;
            }
            if (model.getInvalid().equals("0")) {
                failPackageNum = failPackageNum + 1;
            }
        }
        //项目概况第一个表格数据
        List<Map> list = new ArrayList<>();
        //项目概况下描述
        Map<String, String> map1 = new LinkedHashMap();
        //项目名称
        map1.put("p_xmmc", dao.queryBatchName());
        //分标数量
        map1.put("p_fbnum1", String.valueOf(map.size()));
        //分包数量
        map1.put("p_fbnum2", String.valueOf(bidInfos.size()));
        //采购数量
        map1.put("p_fbnum3", String.valueOf(successPackageNum));
        //流标数量
        map1.put("p_fbnum4", String.valueOf(failPackageNum));
        //推荐中标总金额
        float p_zbje = 0;
        Map<String, Float> map2 = new LinkedHashMap<>();
        List<tblBbgsPackageSupplierModel> t_bModels = bidderDao.queryList(new tblBbgsPackageSupplierModel());
        for (tblBbgsPackageSupplierModel model : t_bModels) {
            String bidAbbreviaion = model.getBidAbbreviaion();
            if (model.getOffer() == null || model.getOffer().equals("无")) {
                continue;
            }
            float f = Float.parseFloat(model.getOffer());
            if (model.getIsWin() != null && model.getIsWin().equals("1")) {
                map2.put(bidAbbreviaion, f);
            } else if (model.getSort() != null && model.getSort().equals("1")) {
                if (!map2.containsKey(bidAbbreviaion)) {
                    map2.put(bidAbbreviaion, f);
                }
            }
        }
        for (String s : map2.keySet()) {
            p_zbje = p_zbje + map2.get(s);
        }
        float p_hjje = dao.queryP_hjje();

        String p_zbje_f = new BigDecimal(p_zbje / 10000).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
        String p_hjje_f = new BigDecimal(p_hjje / 10000).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
        String p_jyzj_f = new BigDecimal((p_zbje / 10000) - (p_hjje / 10000)).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
        map1.put("p_zbje", p_zbje_f);
        map1.put("p_hjje", p_hjje_f);
        map1.put("p_jyzj", p_jyzj_f);
        map1.put("p_date", DateUtil.time8().toString());
        //
        List<Map> list3 = new ArrayList<>();
        List<Map> list5 = new ArrayList<>();
        List<Map> maps = dao.queryNums();
        maps.forEach((map3) -> {

            Map m = new LinkedHashMap();
            String bidName = map3.get("bidName").toString();
            bidName = bidName.substring(bidName.indexOf("-") + 1, bidName.lastIndexOf("包"));
            m.put("分标编号", "");
            m.put("采购数量", "");
            m.put("采购金额", "");
            m.put("分标号", map3.get("bid") + "-");
            m.put("分标名称", bidName);
            m.put("投标人数", map3.get("bidderNum"));
            m.put("投标文件份数", map3.get("total").toString());
            m.put("详评人数", map3.get("successNum"));
            int num1 = (Integer.valueOf(map3.get("total").toString()) - Integer.valueOf(map3.get("successNum").toString()));
            m.put("未响应份数", num1);
            int num2 = Integer.valueOf(map3.get("total").toString());
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(2);
            String result = numberFormat.format((float) num1 / (float) num2 * 100);
            m.put("否决率", result + "%");
            list3.add(m);
        });
        List<Map> allBidOffer = dao.queryBidOffer();
        Double zbjeSum = Double.valueOf(0);
        for (Map m : allBidOffer) {
            if (m.get("firstPrice") != null) {
                String bidName = m.get("bidName").toString();
                bidName = bidName.substring(bidName.indexOf("-") + 1, bidName.lastIndexOf("包"));
                Map m1 = new LinkedHashMap();
                m1.put("分标编号", "");
                m1.put("采购数量", "");
                Map m5 = new LinkedHashMap();
                m5.put("分标号", m.get("bid") + "-");
                m5.put("分标名称", bidName);
                m5.put("单位", "");
                m5.put("数量", "");
                m5.put("投标人数量", m.get("bidderNum"));
                m5.put("中标人数量", m.get("firstNum"));
                if (m.get("winPrice") != null) {
                    Double winPrice = (Double) m.get("winPrice");
                    zbjeSum = zbjeSum + winPrice;
                    float num2 = p_zbje;
                    Double num1 = winPrice;
                    NumberFormat numberFormat = NumberFormat.getInstance();
                    // 设置精确到小数点后2位
                    numberFormat.setMaximumFractionDigits(2);
                    String result = numberFormat.format( num1 / num2 * 100);
                    m1.put("采购金额", new BigDecimal(winPrice).setScale(6, BigDecimal.ROUND_HALF_UP).toString());
                    m5.put("金额", new BigDecimal(winPrice).setScale(6, BigDecimal.ROUND_HALF_UP).toString());
                    m5.put("比例",result+"%");
                } else if (m.get("firstPrice") != null) {
                    Double firstPrice = (Double) m.get("firstPrice");
                    zbjeSum = zbjeSum + firstPrice;
                    m1.put("采购金额", new BigDecimal(firstPrice).setScale(6, BigDecimal.ROUND_HALF_UP).toString());
                    m5.put("金额", new BigDecimal(firstPrice).setScale(6, BigDecimal.ROUND_HALF_UP).toString());
                    float num2 = p_zbje;
                    Double num1 = firstPrice;
                    NumberFormat numberFormat = NumberFormat.getInstance();
                    // 设置精确到小数点后2位
                    numberFormat.setMaximumFractionDigits(2);
                    String result = numberFormat.format( num1 / num2 * 100);
                    m.put("否决率", result + "%");
                    m5.put("比例", result+"%");
                }
                m1.put("分标号", m.get("bid") + "-");
                m1.put("分标名称", bidName);
                m1.put("标包数量", m.get("packageNum"));


                list.add(m1);
                list5.add(m5);
            }
        }
        map1.put("p_totalPrice", new BigDecimal(zbjeSum).setScale(6, BigDecimal.ROUND_HALF_UP).toString());
        new ExportWord().exportWord(list, list2, list3, list4, list5, map1, outputStream, sourcePath);
        return map;
    }

    @Override
    public boolean quertSort() {
        return dao.querySort()==null;
    }
}
