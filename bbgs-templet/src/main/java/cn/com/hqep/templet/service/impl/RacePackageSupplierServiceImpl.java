package cn.com.hqep.templet.service.impl;

import cn.com.hqep.templet.dao.RaceTblBbgsPackageSupplierDao;
import cn.com.hqep.templet.model.RaceTblBbgsPackageSupplierModel;
import cn.com.hqep.templet.service.RacePackageSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hq2 on 2017/10/12.
 */
@Repository
public class RacePackageSupplierServiceImpl implements RacePackageSupplierService {

    @Autowired
    private RaceTblBbgsPackageSupplierDao dao;

    public List<RaceTblBbgsPackageSupplierModel> packageSupplierScore(RaceTblBbgsPackageSupplierModel packageSupplierModel) {
        List<RaceTblBbgsPackageSupplierModel> list = dao.packageSupplierScore(packageSupplierModel);
        return list;
    }

    /*招标编号  就是  标段简称*/
    public List<RaceTblBbgsPackageSupplierModel> getzbnolist(RaceTblBbgsPackageSupplierModel model) {
        List<RaceTblBbgsPackageSupplierModel> zbnolist = dao.getzbnolist(model);
        return zbnolist;
    }
}
