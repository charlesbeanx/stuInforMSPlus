package com.charles.web;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.charles.bean.*;
import com.charles.service.*;

import com.charles.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-06
 */
@Scope("prototype")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iUserService;
    @Autowired
    ILoginlogService iLoginlogService;
    @Autowired
    IPermissionService iPermissionService;
    @Autowired
    IUserRoleService iUserRoleService;
    @Autowired
    IRoleService iRoleService;
    //1 create

    //2 retrieve
    //用户登录
    @RequestMapping("mims_userLogin")
    public void mims_userLogin(String loginAddress,User loginUserInfo, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws  Exception{
        //设置响应编码，防止乱码
        response.setContentType("text/html;charset=utf-8");
        //先判断前端传回的数据是否为空，为空则不去service进行操作
        System.out.println(loginUserInfo);
        //判断账号或密码是否为空
        if(!loginUserInfo.getUsername().equals("")&&!loginUserInfo.getPassword().equals("")){
            //密码加密涉及信息的安全性，这是后面必须要添加的
            User loginUser = iUserService.loginUser(loginUserInfo);
            //判断用户的账号密码是否正确
            if(loginUser!=null){
//                //判断用户是否已经登录
//                if(loginUser.getOnline()==1){
//                    response.getWriter().write("<script>alert('该用户已登录 ~  请勿重复登录');location.href='"+request.getContextPath()+"/mims_userLogin.jsp';</script>");
//                }
                //判断该用户是否被锁定
                if(loginUser.getLocked()==0){
                    //1 更新用户的登录信息（最后登录时间、当前登录时间、当前的登录状态）
                    User loginUserUpdate = iUserService.loginUserUpdate(loginUser,request.getRemoteAddr());
                    //2 存储用户信息到session里面，用于免密登录
                    session.setAttribute("userLogin",loginUserUpdate);
                    session.setMaxInactiveInterval(30*60);
                    //3 存储信息到登录日记表
                    //4 并且查询最近五条的登录记录
                    if(loginAddress!=null){
                        List<Loginlog> loginlogs = iLoginlogService.insertAndGetLoginlog(loginUserUpdate, loginAddress, request.getRemoteAddr());
                        session.setAttribute("userLoginLogs",loginlogs);
                    }
                    System.out.println("查询日志结束");
                    //5 查询该用户的目录权限
                    List<Permission> userLoginMenus = iPermissionService.getUserLoginMenus(loginUserUpdate);
                    session.setAttribute("userMenus",userLoginMenus);
                    //6 查询主页显示的相关信息(在线人数<onlineCount>、员工人数<empCount>、在读班级<gradeCount>、学员人数<studentCount>)
                    HashMap<String, Integer> mims_mainInforCount = new HashMap<>();
                    Integer mims_onlineCount = iUserService.getMims_onlineCount();
                    mims_mainInforCount.put("userMessageCount",5);
                    mims_mainInforCount.put("onlineCount",mims_onlineCount);
                    mims_mainInforCount.put("empCount",7);
                    mims_mainInforCount.put("gradeCount",8);
                    mims_mainInforCount.put("studentCount",9);
                    session.setAttribute("miamInforCount",mims_mainInforCount);
                    System.out.println(mims_mainInforCount.get("gradeCount"));
                    response.getWriter().write("<script>location.href='"+request.getContextPath()+"/mims_index.jsp';</script>");
                }else{
                    response.getWriter().write("<script>alert('该用户已被禁用，请联系管理员 管理员电话：17628055948');location.href='"+request.getContextPath()+"/mims_userLogin.jsp';</script>");
                }
            }else {
                response.getWriter().write("<script>alert('账号或密码错误 ~  请重新输入');location.href='"+request.getContextPath()+"/mims_userLogin.jsp';</script>");
            }
        }else{
            //如果前端传回的数据我们直接判断账号或者是密码为空的  提示用户重新输入账号密码
            response.getWriter().write("<script>alert('账号或密码不能为空~  请重新输入');location.href='" + request.getContextPath() + "/mims_userLogin.jsp';</script>");
        }

    }
    //查询全部用户信息
    @RequestMapping("mims_perUserList")
    public String mims_perUserList(User user, Model model, @RequestParam(name = "pageIndex",defaultValue = "1")Integer pageIndex,@RequestParam(name = "pageSize",defaultValue = "3")Integer pageSize){
        //使用mybatisPlus分页插件查询
        Page<User> userPage = new Page<>(pageIndex, pageSize);
        Wrapper<User> userWrapper = new EntityWrapper<User>().eq("del", 0);
        //查询条件2   根据是否启用禁用进行查询
        Integer locked = user.getLocked();
        if(user!=null&&locked!=null&&locked!=-1){
            userWrapper.eq("locked",locked);
        }
        //查询条件1   根据字段模糊查询
        System.out.println("前端获取的查询条件"+user.toString());
        String username = user.getUsername();
        if (user!=null&&username!=null&&(!username.equals(""))){
            userWrapper.like("username",user.getUsername());
        }
        Page<User> selectPage = iUserService.selectPage(userPage, userWrapper);
        //获取总的条数
        int totalCount = ((Long) selectPage.getTotal()).intValue();
        //获取查询到的所有数据
        List<User> users = selectPage.getRecords();
        //获取用户对应的角色信息
        UserRole userRole = new UserRole();
        for (User u : users) {
            ArrayList<Role> roles = new ArrayList<>();
            userRole.setMimsUserId(u.getId());
            List<UserRole> userRoles = iUserRoleService.selectList(new EntityWrapper<>(userRole));
            for (UserRole ur : userRoles) {
                Integer mimsRoleId = ur.getMimsRoleId();
                Role role = iRoleService.selectById(mimsRoleId);
                roles.add(role);
            }
            u.setRoles(roles);
        }
        PageHelper pageHelper = new PageHelper(pageSize,pageIndex,totalCount,users,null);
        model.addAttribute("pageHelper",pageHelper);
        model.addAttribute("user",user);
        return "/mims_perUserList.jsp";
    }
    //查询一个用户的详情
    @RequestMapping("mims_perUserInfor")
    public String mims_perUserInfor(@RequestParam(name = "userId")Integer userId,Model model){
        if (userId!=null){
            User user = iUserService.selectById(userId);
            model.addAttribute("userLogin",user);
            return "/mims_userInfor.jsp";
        }
        return null;
    }
    //更新的回显值
    @RequestMapping("mims_perUserGoUpdate")
    @ResponseBody
    public Map<String,Object> mims_perUserGoUpdate(@RequestParam(name = "userId")Integer userId){
        if (userId!=null){
            return iUserService.mims_perUserGoUpdate(userId);
        }
        return null;
    }
    //3 update
    //修改密码
    @RequestMapping("mims_userChangePassword")
    public void mims_userChangePassword(String firstPassword,HttpSession session,HttpServletRequest request,HttpServletResponse response)throws Exception{
        User userLogin = (User) session.getAttribute("userLogin");
        User newUser = iUserService.updatePassword(userLogin, firstPassword);
        if (newUser!=null){
            session.removeAttribute("userLogin");
            session.removeAttribute("userLoginLogs");
            session.removeAttribute("userMenus");
            session.removeAttribute("miamInforCount");
            response.getWriter().write("<script>alert('密码修改成功，请重新登录');top.location.href='"+request.getContextPath()+"/mims_userLogin.jsp'</script>");
        }
    }
    //更新状态
    @RequestMapping("mims_perUserState")
    public void mims_perUserState(HttpServletResponse response,Integer id,@RequestParam(name = "locked")Integer locked)throws Exception{
        if (id!=null&&id!=1&&locked!=null){
            Integer integer = iUserService.mims_userPerState(id, locked);
            if(integer>0){
                response.getWriter().write("true");
            }else {
                response.getWriter().write("false");
            }
        }
    }
    //更新信息
    @RequestMapping("mims_perUserUpdate")
    @ResponseBody
    public Boolean mims_perUserUpdate(Integer[] ids,User user){
        if(user!=null){
            return iUserService.mims_perUserUpdate(user, ids);
        }else{
            return false;
        }
    }
    //4 delete
    //用户选择注销就删除其session里面的东西
    @RequestMapping("mims_userLoginout")
    public void mims_userLoginout(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
        response.setContentType("text/html;charset=utf-8");
        User userLoginout = (User) session.getAttribute("userLogin");
        Integer integer = iUserService.userLoginout(userLoginout);
        if(integer>0){
            session.removeAttribute("userLogin");
            session.removeAttribute("userLoginLogs");
            session.removeAttribute("userMenus");
            session.removeAttribute("miamInforCounts");
            response.getWriter().write("<script>alert('注销成功！');location.href='"+request.getContextPath()+"/mims_userLogin.jsp'</script>");
        }
    }
    //删除用户信息,假删除
    @RequestMapping("mims_perUserDelete")
    public void mims_userPerDelete(@RequestParam(name = "userId")Integer userId,HttpServletResponse response)throws Exception{
        //超级管理员是不能删除的
        if(userId!=1){
            Integer integer = iUserService.mims_userPerDelete(userId);
            if (integer>0){
                response.getWriter().write("true");
            }else{
                response.getWriter().write("false");
            }
        }

    }
}

