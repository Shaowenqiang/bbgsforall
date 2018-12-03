package cn.com.hqep.bidder.action;

import cn.com.hqep.bidder.common.jsonMsg;
import cn.com.hqep.bidder.model.tblBbgsFlowStateModel;
import cn.com.hqep.bidder.service.bidderService;
import cn.com.hqep.bidder.service.wordExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wordExport")
public class wordExportAction {
    @Autowired
    wordExportService service;

    @Autowired
    bidderService bidderService;
    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletRequest request,HttpServletResponse response){
        try {
            OutputStream os = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net
                    .URLEncoder.encode("招标评标活动汇报","utf-8") + ".doc");
            response.setContentType("application/x-msdownloadoctet-stream;charset=utf-8");
            String sourcePath = request.getRealPath("/") + "WEB-INF/page/bbgs/word/word-template";
            service.export(os,sourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @ResponseBody
    @RequestMapping("querySortState")
    public  Map querySortState(){
        if(service.quertSort()){
            return jsonMsg.toJosn("error","请先在步骤⑧中计算综合排序");
        }
        return jsonMsg.toJosn("success","可以导出评标汇报");
    }

    @ResponseBody
    @RequestMapping(value = "/queryMinIdByState")
    public Map queryMinIdByState(String id){
        try {
            //查询流程进行到的位置
            tblBbgsFlowStateModel model = bidderService.queryMinIdByState();
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
}
