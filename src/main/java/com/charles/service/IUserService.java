package com.charles.service;

import com.charles.bean.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-06
 */
public interface IUserService extends IService<User> {

    User loginUser(User loginUser);

    User loginUserUpdate(User loginUser,String userIp);

    Integer getMims_onlineCount();

    Integer userLoginout(User userLoginout);

    User updatePassword(User userLogin, String theFirstPassword);

    Integer mims_userPerDelete(Integer userId);

    Integer mims_userPerState(Integer id, Integer locked);

    Map<String,Object> mims_perUserGoUpdate(Integer userId);

    Boolean mims_perUserUpdate(User user, Integer[] roles);
}
