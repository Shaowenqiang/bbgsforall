package cn.com.hqep.templet.dao;

import cn.com.hqep.templet.model.RaceTblBbgsPackageSupplierModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hq2 on 2017/10/12.
 */
@Repository
public interface RaceTblBbgsPackageSupplierDao {
    public List<RaceTblBbgsPackageSupplierModel> packageSupplierScore(RaceTblBbgsPackageSupplierModel packageSupplierModel);

    public List<RaceTblBbgsPackageSupplierModel> getzbnolist(RaceTblBbgsPackageSupplierModel model);
}
