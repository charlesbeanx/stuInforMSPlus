package com.charles.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.charles.bean.Permission;
import com.charles.mapper.PermissionMapper;
import com.charles.service.IPermissionService;
import com.charles.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    IPermissionService iPermissionService;
    //分页查询全部信息
    @RequestMapping("mims_list")
    public String mims_list(Model model, @RequestParam(name = "pageIndex",defaultValue = "1")Integer pageIndex,@RequestParam(name = "pageSize",defaultValue = "3")Integer pageSize){
        //1 分页工具
        Page<Permission> page = new Page<>(pageIndex,pageSize);
        //2 查询条件
        Wrapper<Permission> wrapper = new EntityWrapper<Permission>().eq("locked", 0);
        //3 开始分页查询
        Page<Permission> permissions = iPermissionService.selectPage(page, wrapper);
        //4 封装到自己写的pageHelper
        PageHelper<Permission> pageHelper = new PageHelper<>(pageSize, pageIndex, ((Long) permissions.getTotal()).intValue(), permissions.getRecords(), null);
        model.addAttribute("pageHelper",pageHelper);
        return "/mims_perPermissionList.jsp";
    }
    //删除信息  
    @ResponseBody
    @RequestMapping("mims_delete")
    public Boolean mims_delete(@RequestParam(name = "id")Integer id){
        if (id!=null){
            //删除资源同时，把对应表中对应的也要删除，涉及事务
            Boolean delete = iPermissionService.mims_delete(id);
            return delete;
        }
        return false;
    }
    //数据回显
    @ResponseBody
    @RequestMapping("mims_echo")
    public Map<String,Object> mims_echo(Integer id){
        //返回值
        HashMap<String, Object> hashMap = new HashMap<>();
        //条件
        EntityWrapper<Permission> wrapper = new EntityWrapper<>();
        //1 新增时候的回显
        if(id==null){
            List<Permission> per = iPermissionService.selectList(wrapper.eq("type", "menu").eq("parentid",0));
            hashMap.put("permission",per);
        }else{
            Permission per = iPermissionService.selectById(id);
            //2 更新1级菜单的回显
            if (per.getParentid()==0){
                hashMap.put("permission",per);
            }else {
                //3 更新2级菜单的回显
                List<Permission> menus = iPermissionService.selectList(wrapper.eq("type", "menu").eq("parentid",0));
                for (Permission menu : menus) {
                    if (menu.getId()==per.getParentid()){
                        menu.setFlag(true);
                    }
                }
                hashMap.put("permission",per);
                hashMap.put("menu",menus);
            }
        }
        return hashMap;
    }
    //新增信息
    @ResponseBody
    @RequestMapping("mims_add")
    public Boolean mims_add(Permission permission){
        if(permission!=null){
            permission.setParentids("0/"+permission.getParentid());
            permission.setSortstring("0");
            iPermissionService.insert(permission);
            return true;
        }
        return false;
    }
    //更新信息
    @ResponseBody
    @RequestMapping("mims_update")
    public  Boolean mims_update(Permission permission){
        System.out.println("已经进入资源更新后台"+permission);
        if (permission!=null){
            if (permission.getParentid()!=0){
                permission.setParentids("0/"+permission.getParentid());
            }
            permission.setSortstring("0");
            iPermissionService.updateById(permission);
            return true;
        }
        return false;
    }
}

