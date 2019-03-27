package com.charles.service;

import com.charles.bean.Loginlog;
import com.baomidou.mybatisplus.service.IService;
import com.charles.bean.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-14
 */
public interface ILoginlogService extends IService<Loginlog> {
    List<Loginlog> insertAndGetLoginlog(User loginUserUpdate, String loginAddress, String remoteAddr);
}
