package cn.com.hqep.templet.action;

import cn.com.hqep.templet.model.RaceTblBbgsPackageSupplierModel;
import cn.com.hqep.templet.service.RacePackageSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hq2 on 2017/10/12.
 */
@Controller
@RequestMapping("/RacePackageSupplier")
public class RacePackageSupplierAction {
    @Autowired
    private RacePackageSupplierService service;

    @ResponseBody
    @RequestMapping("/packageSupplierScore")
    public List<RaceTblBbgsPackageSupplierModel> packageSupplierScore(String bdname, String tbdw) {
        RaceTblBbgsPackageSupplierModel packageSupplierModel = new RaceTblBbgsPackageSupplierModel();
        packageSupplierModel.setBidAbbreviaion(bdname);
        packageSupplierModel.setSupplier(tbdw);
        List<RaceTblBbgsPackageSupplierModel> list = service.packageSupplierScore(packageSupplierModel);
        return list;
    }

    @ResponseBody
    @RequestMapping("/packageSupplierzbno")
    public List<RaceTblBbgsPackageSupplierModel> packageSupplierzbno(RaceTblBbgsPackageSupplierModel model) {
        List<RaceTblBbgsPackageSupplierModel> biaoduanjianchenglist = service.getzbnolist(model);
        return biaoduanjianchenglist;
    }
}
