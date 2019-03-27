package com.charles.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.charles.bean.Department;
import com.charles.bean.EmpDepart;
import com.charles.service.IDepartmentService;
import com.charles.service.IEmpDepartService;
import com.charles.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.geom.FlatteningPathIterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-22
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    IDepartmentService iDepartmentService;
    @Autowired
    IEmpDepartService iEmpDepartService;
    //mims_list
    @RequestMapping("/mims_list")
    public String mims_list(Model model, @RequestParam(name = "pageIndex",defaultValue = "1")Integer pageIndex,@RequestParam(name = "pageSize",defaultValue = "3")Integer pageSize){
        Page<Department> page = new Page<>(pageIndex, pageSize);
        Wrapper<Department> wrapper = new EntityWrapper<Department>().eq("del", 0);
        Page<Department> selectPage = iDepartmentService.selectPage(page, wrapper);
        Integer totalCount = ((Long) selectPage.getTotal()).intValue();
        List<Department> records = selectPage.getRecords();
        for (Department record : records) {
            int empCount = iEmpDepartService.selectCount(new EntityWrapper<EmpDepart>().eq("mims_depart_id", record.getId()));
            record.setEmpCount(empCount);
        }
        PageHelper<Department> pageHelper = new PageHelper<>(pageSize, pageIndex, totalCount, records, null);
        model.addAttribute("pageHelper",pageHelper);
        return "/mims_workDepartment.jsp";
    }
    //mims_delete
    @ResponseBody
    @RequestMapping("mims_delete")
    public Integer mims_delete(Integer id){
        if (id!=null){
            Integer integer = iDepartmentService.mims_delete(id);
            return integer;
        }
        return 0;
    }
    //mims_echo
    @ResponseBody
    @RequestMapping("/mims_echo")
    public Map<String,Object> mims_echo(Integer id){
        HashMap<String, Object> hashMap = new HashMap<>();
        Wrapper<Department> wrapper = new EntityWrapper<Department>().eq("del", 0);
        if (id!=null){
            Department department = iDepartmentService.selectOne(wrapper);
            hashMap.put("department",department);
        }else{
            List<Department> departments = iDepartmentService.selectList(wrapper);
            hashMap.put("departments",departments);
        }
        return hashMap;
    }
    //mims_add
    @ResponseBody
    @RequestMapping("/mims_add")
    public Boolean mims_add(Department department){
        if (department!=null){
            String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            department.setCreatetime(createTime);
            department.setLocked(0);
            department.setDel(0);
            department.setEmpCount(0);
            department.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            iDepartmentService.insert(department);
            return true;
        }
        return false;
    }
    //mims_update
    @ResponseBody
    @RequestMapping("/mims_update")
    public Boolean mims_update(Department department){
        if (department!=null){
            iDepartmentService.updateById(department);
            return true;
        }
        return false;
    }

}

