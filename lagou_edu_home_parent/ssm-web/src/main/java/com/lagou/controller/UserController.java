package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.vo.UserVo;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author xumiao
 * @creationTime 2023/3/7
 * @description 用户模块表现层
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户分页&条件查询
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {

        PageInfo<User> pageInfo = userService.findAllUserByPage(userVo);
        return new ResponseResult(true,200,"响应成功",pageInfo);
    }

    /**
     * 修改用户状态
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(Integer id,String status) {

        userService.updateUserStatus(id,status);
        return new ResponseResult(true,200,"修改成功",status);
    }

    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) {

        try {
            User loginUser = userService.login(user);
            // 判断loginUser是否为空，为空则登录失败
            if (null != loginUser) {
                // 登录成功，保存access_token到session中
                HttpSession session = request.getSession();
                // 随机生成access_token
                String access_token = UUID.randomUUID().toString();
                session.setAttribute("access_token",access_token);
                session.setAttribute("user_id",loginUser.getId());

                // 将access_token和user_id响应给前端
                Map<String,Object> map = new HashMap<>();
                map.put("access_token",access_token);
                map.put("user_id",loginUser.getId());
                // 将loginUser对象也响应给前台
                map.put("user",loginUser);

                return new ResponseResult(true,1,"登录成功",map);
            } else {
                return new ResponseResult(false,400,"用户名或密码错误",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(false,500,"服务器发生错误",null);
        }
    }

    /**
     * 分配角色(回显)
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleByUserId(Integer id) {

        List<Role> list = userService.findUserRoleByUserId(id);
        return new ResponseResult(true,200,"响应成功",list);
    }

    /**
     * 分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo,HttpServletRequest request) {

        // 获取到操作用户的信息
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");
        User user = userService.findUserById(userId);
        userService.userContextRole(userVo,user);
        return new ResponseResult(true,200,"角色分配成功",null);
    }

    /**
     * 获取用户拥有的菜单权限
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {

        // 1.获取请求头中的token
        String header_token = request.getHeader("Authorization");
        // 2.获取session中的token
        HttpSession session = request.getSession();
        String session_token = (String) session.getAttribute("access_token");
        // 判断两个token是否一致
        if (header_token.equals(session_token)) {
            Map<String, Object> map = userService.getUserPermissions((Integer) session.getAttribute("user_id"));
            return new ResponseResult(true,1,"响应成功",map);
        } else {
            return new ResponseResult(false,400,"获取失败",null);
        }
    }
}
