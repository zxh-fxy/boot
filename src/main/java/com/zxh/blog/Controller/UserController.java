package com.zxh.blog.Controller;

import cn.dev33.satoken.spring.SaBeanInject;
import cn.dev33.satoken.stp.StpUtil;
import com.zxh.blog.entity.User;
import com.zxh.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


//@SaCheckLogin: 登录认证 —— 只有登录之后才能进入该方法。
//@SaCheckRole("admin"): 角色认证 —— 必须具有指定角色标识才能进入该方法。
//@SaCheckPermission("user:add"): 权限认证 —— 必须具有指定权限才能进入该方法。
//@SaCheckSafe: 二级认证校验 —— 必须二级认证之后才能进入该方法。
//@SaCheckBasic: HttpBasic认证 —— 只有通过 Basic 认证后才能进入该方法。
//@SaIgnore：忽略认证 —— 表示被修饰的方法或类无需进行注解认证和路由拦截认证。
//@SaCheckDisable("comment")：账号服务封禁校验 —— 校验当前账号指定服务是否被封禁。

@RestController
@RequestMapping("user")
@Api(value = "用户管理", tags = "用户管理相关的接口")
@Slf4j
public class UserController {
  @Autowired
  private UserService userService;
  @RequestMapping(value = "addUser",method = RequestMethod.PUT)
  @ApiOperation(value = "添加用户",notes = "添加用户",httpMethod = "PUT")
  public ResponseEntity<User> addUser(final User user){
    return userService.addUser(user);
  }
  // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
  @RequestMapping("doLogin")
  public String doLogin(String username, String password) {
    // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
    if("zhang".equals(username) && "123456".equals(password)) {
      StpUtil.login(10001);
      return "登录成功";
    }
    return "登录失败";
  }

  // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
  @RequestMapping("isLogin")
  public String isLogin() {
    return "当前会话是否登录：" + StpUtil.isLogin();
  }

}