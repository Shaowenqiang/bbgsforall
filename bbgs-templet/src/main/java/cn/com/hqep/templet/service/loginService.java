package cn.com.hqep.templet.service;

import cn.com.hqep.templet.model.tblBbgsUser;

/**
 * 登录
 *
 * @author hq4
 * @date 2017年10月31日 下午04:14:15
 */
public interface loginService {
    /**
     * 查询用户信息
     *
     * @param user
     * @return
     */
    tblBbgsUser queryUser(tblBbgsUser user);
}
