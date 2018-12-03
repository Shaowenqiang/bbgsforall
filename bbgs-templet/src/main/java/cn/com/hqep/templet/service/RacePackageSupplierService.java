package cn.com.hqep.templet.service;

import cn.com.hqep.templet.model.RaceTblBbgsPackageSupplierModel;

import java.util.List;

/**
 * Created by hq2 on 2017/10/12.
 */
public interface RacePackageSupplierService {
    List<RaceTblBbgsPackageSupplierModel> packageSupplierScore(RaceTblBbgsPackageSupplierModel packageSupplierModel);

    List<RaceTblBbgsPackageSupplierModel> getzbnolist(RaceTblBbgsPackageSupplierModel model);
}
