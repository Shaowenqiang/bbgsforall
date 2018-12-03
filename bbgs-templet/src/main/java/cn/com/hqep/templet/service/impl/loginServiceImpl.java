package cn.com.hqep.templet.service.impl;

import cn.com.hqep.templet.dao.loginDao;
import cn.com.hqep.templet.model.tblBbgsUser;
import cn.com.hqep.templet.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 登录
 *
 * @author hq4
 * @date 2017年10月31日 下午04:18:02
 */
@Repository
public class loginServiceImpl implements loginService {
    @Autowired
    loginDao dao;

    @Override
    public tblBbgsUser queryUser(tblBbgsUser user) {
        tblBbgsUser u = dao.queryUser(user);
        if (u != null)
            return dao.queryUser(user);
        return null;
    }
}
