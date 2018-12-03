package cn.com.hqep.templet.service.impl;

import cn.com.hqep.templet.dao.tblBbgsPackageSupplierDao;
import cn.com.hqep.templet.model.tblBbgsPackageSupplierModel;
import cn.com.hqep.templet.service.packageSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hq2 on 2017/10/12.
 */
@Repository
public class packageSupplierServiceImpl implements packageSupplierService {

    @Autowired
    private tblBbgsPackageSupplierDao dao;

    public List<tblBbgsPackageSupplierModel> packageSupplierScore(tblBbgsPackageSupplierModel packageSupplierModel) {
        List<tblBbgsPackageSupplierModel> list = dao.packageSupplierScore(packageSupplierModel);
        return list;
    }

    /*招标编号  就是  标段简称*/
    public List<tblBbgsPackageSupplierModel> getzbnolist(tblBbgsPackageSupplierModel model) {
        List<tblBbgsPackageSupplierModel> zbnolist = dao.getzbnolist(model);
        return zbnolist;
    }
}
