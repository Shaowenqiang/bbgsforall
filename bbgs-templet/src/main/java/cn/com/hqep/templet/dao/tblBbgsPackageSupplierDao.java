package cn.com.hqep.templet.dao;

import cn.com.hqep.templet.model.tblBbgsPackageSupplierModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hq2 on 2017/10/12.
 */
@Repository
public interface tblBbgsPackageSupplierDao {
    public List<tblBbgsPackageSupplierModel> packageSupplierScore(tblBbgsPackageSupplierModel packageSupplierModel);

    public List<tblBbgsPackageSupplierModel> getzbnolist(tblBbgsPackageSupplierModel model);
}
