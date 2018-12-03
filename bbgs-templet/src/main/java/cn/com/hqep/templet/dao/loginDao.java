package cn.com.hqep.templet.dao;

import cn.com.hqep.templet.model.tblBbgsUser;
import org.springframework.stereotype.Repository;

/**
 * 登录
 *
 * @author hq4
 * @date 2017年10月31日 下午04:18:36
 */
@Repository
public interface loginDao {
    /**
     * 查询用户信息
     * @param user
     * @return
     */
    tblBbgsUser queryUser(tblBbgsUser user);
}
