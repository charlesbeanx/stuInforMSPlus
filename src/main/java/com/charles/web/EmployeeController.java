package com.charles.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.charles.bean.Department;
import com.charles.bean.EmpDepart;
import com.charles.bean.Employee;
import com.charles.service.IDepartmentService;
import com.charles.service.IEmpDepartService;
import com.charles.service.IEmployeeService;
import com.charles.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    IEmployeeService iEmployeeService;
    @Autowired
    IEmpDepartService iEmpDepartService;
    @Autowired
    IDepartmentService iDepartmentService;
    //mims_list
    @RequestMapping("/mims_list")
    public String mims_list(@RequestParam(name = "pageIndex",defaultValue = "1")Integer pageIndex, @RequestParam(name = "pageSize",defaultValue = "3")Integer pageSize, Model model){
        Page<Employee> page = new Page<>(pageIndex, pageSize);
        Wrapper<Employee> wrapper = new EntityWrapper<Employee>().eq("del", 0);
        Page<Employee> selectPage = iEmployeeService.selectPage(page, wrapper);
        PageHelper<Employee> pageHelper = new PageHelper<>(pageSize, pageIndex, ((Long) selectPage.getTotal()).intValue(), selectPage.getRecords(), null);
        model.addAttribute("pageHelper",pageHelper);
        return "/mims_workEmployee.jsp";
    }
    //mims_delete
    @ResponseBody
    @RequestMapping("/mims_delete")
    public Boolean mims_delete(Integer id){
        if (id!=null){
            Boolean aBoolean = iEmployeeService.mims_delete(id);
            return aBoolean;
        }
        return false;
    }
    //mims_changeState
    @ResponseBody
    @RequestMapping("/mims_changeState")
    public Boolean changeState(Integer id,Integer locked){
        Employee employee = iEmployeeService.selectById(id);
        if (locked==0){
            employee.setLocked(1);
        }
        if (locked==1){
            employee.setLocked(0);
        }
        boolean b = iEmployeeService.updateById(employee);
        return b;
    }
    //mims_echo
    @ResponseBody
    @RequestMapping("/mims_echo")
    public Map<String,Object> mims_echo(Integer id){
        HashMap<String, Object> hashMap = new HashMap<>();
        //查询员工信息
        Employee employee = iEmployeeService.selectById(id);
        hashMap.put("employee",employee);
        //查询员工对应的部门信息
        List<Department> departments = iDepartmentService.selectList(null);
        for (Department department : departments) {
            //如果用角色和部门对应的两个标识去中间表能够查询到数据，表示该角色属于该部门
            EmpDepart empDepart = iEmpDepartService.selectOne(new EntityWrapper<EmpDepart>().eq("mims_emp_id", id).eq("mims_depart_id", department.getId()));
            if (empDepart!=null){
                department.setFlag(1);
            }
        }
        hashMap.put("departments",departments);
        return hashMap;
    }
    //mims_add
    @ResponseBody
    @RequestMapping("/mims_add")
    public Boolean mims_add(Employee employee,Integer[] ids){
        if (employee!=null){
            Boolean aBoolean = iEmployeeService.mims_add(employee, ids);
            return aBoolean;
        }
        return false;
    }
    //mims_update
    @ResponseBody
    @RequestMapping("/mims_update")
    public Boolean mims_update(Employee employee,Integer[] ids){
        if (employee!=null){
            Boolean aBoolean = iEmployeeService.mims_update(employee, ids);
            return aBoolean;
        }
        return false;
    }
}

