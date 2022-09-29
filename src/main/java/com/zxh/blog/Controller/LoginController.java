package com.zxh.blog.Controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.zxh.blog.entity.User;
import com.zxh.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acc/")
public class LoginController {
  @Autowired
  private UserService userService;
  // 测试登录  ---- http://localhost:8081/acc/doLogin?name=zhang&pwd=123456
  @RequestMapping("doLogin")
  public SaResult doLogin(String accountId, String pwd) {
    // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
    User user=new User();
    user.setAccountid(accountId);
    User real_user = userService.getOneUser(user).getBody();
    if (real_user==null){
      return SaResult.ok("用户不存在");
    }
    if(real_user.getPassword().equals(pwd)) {
      StpUtil.login(real_user.getId());
      return SaResult.ok("登录成功");
    }
    return SaResult.error("登录失败");
  }

  // 查询登录状态  ---- http://localhost:8081/acc/isLogin
  @RequestMapping("isLogin")
  public SaResult isLogin() {
    return SaResult.ok("是否登录：" + StpUtil.isLogin());
  }

  // 查询 Token 信息  ---- http://localhost:8081/acc/tokenInfo
  @RequestMapping("tokenInfo")
  public SaResult tokenInfo() {
    return SaResult.data(StpUtil.getTokenInfo());
  }

  // 测试注销  ---- http://localhost:8081/acc/logout
  @RequestMapping("logout")
  public SaResult logout() {
    StpUtil.logout();
    return SaResult.ok();
  }
  @RequestMapping(value = "take_logout")
  public SaResult take_logout(String id, String token) {
    StpUtil.logout(id);
    StpUtil.logoutByTokenValue(token);
    return SaResult.ok();
  }

  @RequestMapping(value = "kick_logout")
  public SaResult kick_logout(String id, String token) {
    StpUtil.kickoutByTokenValue(token);
    StpUtil.kickout(id);
    return SaResult.ok();
  }

}