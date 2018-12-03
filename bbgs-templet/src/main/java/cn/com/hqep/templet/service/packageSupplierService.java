package cn.com.hqep.templet.service;

import cn.com.hqep.templet.model.tblBbgsPackageSupplierModel;

import java.util.List;

/**
 * Created by hq2 on 2017/10/12.
 */
public interface packageSupplierService {
    List<tblBbgsPackageSupplierModel> packageSupplierScore(tblBbgsPackageSupplierModel packageSupplierModel);

    List<tblBbgsPackageSupplierModel> getzbnolist(tblBbgsPackageSupplierModel model);
}
