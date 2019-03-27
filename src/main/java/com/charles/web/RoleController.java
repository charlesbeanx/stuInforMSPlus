package com.charles.web;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.charles.bean.Permission;
import com.charles.bean.Role;
import com.charles.bean.RolePermission;
import com.charles.service.IPermissionService;
import com.charles.service.IRolePermissionService;
import com.charles.service.IRoleService;
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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    IRoleService iRoleService;
    @Autowired
    IPermissionService iPermissionService;
    @Autowired
    IRolePermissionService iRolePermissionService;
    //1 create
    //新增一个角色并且给角色权限
    @ResponseBody
    @RequestMapping("/mims_perRoleAdd")
    public Boolean mims_perRoleAdd(Role role,Integer[] ids){
        if (role!=null){
            return iRoleService.perRoleAdd(role, ids);
        }
        return false;
    }
    //2 retrieve
    //无条件查询全部角色信息
    @RequestMapping("mims_perRoleList")
    public String mims_perRoleList(Model model, @RequestParam(name = "pageIndex",defaultValue = "1")Integer pageIndex,@RequestParam(name = "pageSize",defaultValue = "3")Integer pageSize){
        //1 设置mybatis的分页插件
        Page<Role> page = new Page<>(pageIndex, pageSize);
        //2 设置查询条件
        EntityWrapper<Role> wrapper = new EntityWrapper<>();
        //3 直接分页查询
        Page<Role> rolePage = iRoleService.selectPage(page, wrapper);
        //4 设置成自己需要的pageHelper
        List<Role> roles = rolePage.getRecords();
        Integer totalCount = ((Long)rolePage.getTotal()).intValue();
        PageHelper<Role> pageHelper = new PageHelper<>(pageSize, pageIndex, totalCount, roles, null);
        model.addAttribute("pageHelper",pageHelper);
        System.out.println(pageHelper.toString());
        return "/mims_perRoleList.jsp";
    }
    //根据roleId更新的角色信息回显
    @ResponseBody
    @RequestMapping("mims_perRoleGoUpdate")
    public Map<String,Object> mims_perRoleGoUpdate(@RequestParam(name = "roleId")Integer roleId){
        HashMap<String, Object> hashMap = new HashMap<>();
        if (roleId!=null){
            Role role = iRoleService.selectById(roleId);
            hashMap.put("role",role);
            //查询全部的菜单信息
            List<Permission> permissions = iPermissionService.selectList(new EntityWrapper<Permission>().eq("locked",0));
            ArrayList<Permission> menu = new ArrayList<>();
            ArrayList<Permission> permission = new ArrayList<>();
            for (Permission per : permissions) {
                if (per.getType().equals("menu")){
                    menu.add(per);
                }else {
                    permission.add(per);
                }
                if(iRolePermissionService.selectOne(new EntityWrapper<RolePermission>().eq("mims_role_id",roleId).eq("mims_permission_id",per.getId()))!=null){
                    per.setFlag(true);
                }
            }
            hashMap.put("menu",menu);
            hashMap.put("permission",permission);
            //查询全部的权限信息
        }
        return hashMap;
    }
    //添加角色时候回显资源信息
    @ResponseBody
    @RequestMapping("mims_perRoleGetMenuPermission")
    public Map<String,Object> mims_perRoleGetMenuPermission(){
        HashMap<String, Object> hashMap = new HashMap<>();
        List<Permission> permissions = iPermissionService.selectList(new EntityWrapper<Permission>().eq("locked", 0));
        ArrayList<Permission> menu = new ArrayList<>();
        ArrayList<Permission> permission = new ArrayList<>();
        for (Permission per : permissions) {
            if (per.getType().equals("menu")){
                menu.add(per);
            }else {
                permission.add(per);
            }
        }
        hashMap.put("menu",menu);
        hashMap.put("permission",permission);
        return hashMap;
    }
    //3 update
    //根据RoleId更新角色及其权限
    @ResponseBody
    @RequestMapping("mims_perRoleUpdate")
    public  Boolean mims_perRoleUpdate(@RequestParam(name = "rids")Integer[] rids,Role role){
        if(role!=null){
            return iRoleService.mims_perRoleUpdate(rids,role);
        }
        return false;
    }
    //4 delete
    //根据roleId删除所有该角色
    @ResponseBody
    @RequestMapping("mims_perRoleDelete")
    public  Boolean mims_perRoleDelete(@RequestParam(name = "roleId")Integer roleId){
        //超级管理员这个角色是不能删除的
        if (roleId!=null&&roleId!=1){
            Integer integer = iRoleService.perRoleDelete(roleId);
            if (integer>0){
                return true;
            }
        }
        return false;
    }
}

