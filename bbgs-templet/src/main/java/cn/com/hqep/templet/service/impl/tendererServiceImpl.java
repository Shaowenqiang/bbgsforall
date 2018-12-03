package cn.com.hqep.templet.service.impl;


import cn.com.hqep.templet.dao.tendererDao;
import cn.com.hqep.templet.model.tblBbgsTendererModel;
import cn.com.hqep.templet.service.tendererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hq2 on 2017/10/9.
 */
/*投标信息类
* 郭
* */
@Repository
public class tendererServiceImpl implements tendererService {
    @Autowired
    private tendererDao dao;

    /*查询功能*/
    public List<tblBbgsTendererModel> contentAll(tblBbgsTendererModel tblBbgsTendererModel) {
        List<tblBbgsTendererModel> list = dao.contentAll(tblBbgsTendererModel);
        return list;
    }


}
