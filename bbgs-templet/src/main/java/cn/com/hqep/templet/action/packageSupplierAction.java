package cn.com.hqep.templet.action;

import cn.com.hqep.templet.model.tblBbgsPackageSupplierModel;
import cn.com.hqep.templet.service.packageSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hq2 on 2017/10/12.
 */
@Controller
@RequestMapping("/packageSupplier")
public class packageSupplierAction {
    @Autowired
    private packageSupplierService service;

    @ResponseBody
    @RequestMapping("/packageSupplierScore")
    public List<tblBbgsPackageSupplierModel> packageSupplierScore(String bdname, String tbdw) {
        tblBbgsPackageSupplierModel packageSupplierModel = new tblBbgsPackageSupplierModel();
        packageSupplierModel.setBidAbbreviaion(bdname);
        packageSupplierModel.setSupplier(tbdw);
        List<tblBbgsPackageSupplierModel> list = service.packageSupplierScore(packageSupplierModel);
        return list;
    }

    @ResponseBody
    @RequestMapping("/packageSupplierzbno")
    public List<tblBbgsPackageSupplierModel> packageSupplierzbno(tblBbgsPackageSupplierModel model) {
        List<tblBbgsPackageSupplierModel> biaoduanjianchenglist = service.getzbnolist(model);
        return biaoduanjianchenglist;
    }
}
