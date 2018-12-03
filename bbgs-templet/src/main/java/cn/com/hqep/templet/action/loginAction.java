package cn.com.hqep.templet.action;

import cn.com.hqep.templet.common.jsonMsg;
import cn.com.hqep.templet.model.tblBbgsUser;
import cn.com.hqep.templet.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录
 *
 * @author swq
 * @date 2017年10月31日 下午04:10:48
 */
@Controller
@RequestMapping("/login")
public class loginAction {
    @Autowired
    private loginService loginService;

    /**
     * 输入登录名和密码
     * 验证登录名和密码
     * 正确则进入首页
     *
     * @param loginName
     * @param password
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/intoIndex")
    public Map login(String loginName, String password, HttpServletRequest request) {
        tblBbgsUser user = new tblBbgsUser();
        user.setUsername(loginName);
        user.setUserpassword(password);
        user = loginService.queryUser(user);
        if (user != null) {
            HttpSession session = request.getSession();
            user.setUserpassword(null);
            //使用session储存用户登录信息
            session.setAttribute("SESSION_USER_KEY", user);
            //设置session失效时长为6小时
            session.setMaxInactiveInterval(6 * 60 * 60);
            return jsonMsg.toJosn("success", loginService.queryUser(user));
        }
        return jsonMsg.toJosn("error", "用户名或密码不正确");
    }

    /**
     * 通过session来查询当前登录人信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryLoginer")
    public Map queryLoginer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return jsonMsg.toJosn("success", session.getAttribute("SESSION_USER_KEY"));
    }

    /**
     * 清空session信息达到登出效果
     * @param request
     */
    @RequestMapping("/loginOut")
    public void loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("SESSION_USER_KEY");
        request.getSession().invalidate();
    }
}
