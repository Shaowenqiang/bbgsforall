package cn.com.hqep.bidder.action;

import cn.com.hqep.bidder.model.tblBbgsBidInformationModel;
import cn.com.hqep.bidder.model.tblBbgsPackageSupplierModel;
import cn.com.hqep.bidder.service.conductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by sssJL on 2017-10-12.
 * 处理标段信息表的controller类
 */

@Controller
@RequestMapping("/conduct")
public class conductAction {

    @Autowired
    conductService service;

    /***
     * 查询
     * 查询所有标段信息表 的记录
     * @return 返回实体集合
     */
    @ResponseBody
    @RequestMapping("queryList")
    public List<tblBbgsBidInformationModel> queryList(){
        List<tblBbgsBidInformationModel> list = null;
        try {
            list = service.queryList();
        }catch (Exception exp){
            exp.printStackTrace();
            list = null;
        }
        return list;
    }

}
