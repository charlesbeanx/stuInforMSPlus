package com.charles.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.charles.bean.Role;
import com.charles.bean.User;
import com.charles.bean.UserRole;
import com.charles.mapper.RoleMapper;
import com.charles.mapper.UserMapper;
import com.charles.mapper.UserRoleMapper;
import com.charles.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-06
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    //用户登录
    @Override
    public User loginUser(User loginUser) {
        User user = userMapper.selectOne(loginUser);
        //如果查询不到数据，那下面的if语句中的user就会报空指针异常
        if(user!=null&&user.getDel()==0){
            return user;
        }
         return null;
    }
    //用户登录成功 更新最后登录时间
    @Override
    public User loginUserUpdate(User user,String userIp) {
        //如果用户存在并且没有被禁用或者被删除   重置登录的时间
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        user.setLastTime(user.getCurrentTime());
        user.setCurrentTime(currentTime);
        user.setOnline(1);
        user.setIp(userIp);
        //是更新信息不是直接插入信息
        userMapper.updateAllColumnById(user);
        return user;
    }
    //查看当前的在线人数
    @Override
    public Integer getMims_onlineCount() {
        return userMapper.selectCount(new EntityWrapper<User>().eq("online",1));
    }
    //用户注册
    @Override
    public Integer userLoginout(User userLoginout) {
        System.out.println("要注销的用户信息是:"+userLoginout);
        userLoginout.setOnline(0);
        return userMapper.updateAllColumnById(userLoginout);
    }
    //修改密码
    @Override
    public User updatePassword(User userLogin, String theFirstPassword) {
        if(theFirstPassword!=null){
            userLogin.setPassword(theFirstPassword);
            Integer integer = userMapper.updateAllColumnById(userLogin);
            if (integer>0){
                return userLogin;
            }
        }
        return null;
    }
    //用户删除
    @Override
    public Integer mims_userPerDelete(Integer userId) {
        User user = userMapper.selectById(userId);
        user.setDel(1);
        return userMapper.updateAllColumnById(user);
    }
    //更新用户状态
    @Override
    public Integer mims_userPerState(Integer id, Integer locked) {
        User user = userMapper.selectById(id);
        if (locked==0){
            user.setLocked(1);
        }else{
            user.setLocked(0);
        }
        return userMapper.updateAllColumnById(user);
    }

    //更新之前的回显
    @Override
    public Map<String, Object> mims_perUserGoUpdate(Integer userId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        User user = userMapper.selectById(userId);
        hashMap.put("user",user);
        List<Role> roles = roleMapper.selectList(null);
        for (Role role : roles) {
            Integer integer = userRoleMapper.selectCount(new EntityWrapper<UserRole>().eq("mims_user_id", user.getId()).eq("mims_role_id", role.getId()));
            if (integer>0){
                role.setflag(1);
            }
        }
        hashMap.put("roles",roles);
        System.out.println("更新回显值为："+hashMap.toString());
        return hashMap;
    }
    //真正开始更新数据
    @Override
    public Boolean mims_perUserUpdate(User user, Integer[] ids) {
        //删除该用户的所有角色信息
        //如果用户之前就不存在这个角色信息呢!  简直是笨死
        userRoleMapper.delete(new EntityWrapper<UserRole>().eq("mims_user_id", user.getId()));
        //更新用户的信息
        userMapper.updateById(user);
        //更新用户的角色信息
        UserRole userRole = new UserRole();
        for (Integer role : ids) {
            userRole.setMimsUserId(user.getId());
            userRole.setMimsRoleId(role);
            userRoleMapper.insert(userRole);
        }
        return true;
    }


}
