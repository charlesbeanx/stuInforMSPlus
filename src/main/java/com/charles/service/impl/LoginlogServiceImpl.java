package com.charles.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.charles.bean.Loginlog;
import com.charles.bean.User;
import com.charles.mapper.LoginlogMapper;
import com.charles.service.ILoginlogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-14
 */
@Service
public class LoginlogServiceImpl extends ServiceImpl<LoginlogMapper, Loginlog> implements ILoginlogService {

    @Autowired
    LoginlogMapper loginlogMapper;
    @Override
    public List<Loginlog> insertAndGetLoginlog(User loginUserUpdate, String loginAddress, String remoteAddr) {
        //存储信息到login表
        Loginlog loginlog = new Loginlog(remoteAddr, loginUserUpdate.getCurrentTime(), loginAddress, loginUserUpdate.getId());
        Integer insert = loginlogMapper.insert(loginlog);
        if (insert>0){
            List<Loginlog> loginlogs = loginlogMapper.selectList(new EntityWrapper<Loginlog>()
                    .eq("userid", loginUserUpdate.getId())
                    .orderBy("id", false)
                    .last("limit 0,5"));
            return loginlogs;
        }
        return null;
    }
}
