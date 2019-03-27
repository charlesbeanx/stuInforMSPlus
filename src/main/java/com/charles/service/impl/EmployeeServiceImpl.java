package com.charles.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.charles.bean.EmpDepart;
import com.charles.bean.Employee;
import com.charles.bean.User;
import com.charles.mapper.EmpDepartMapper;
import com.charles.mapper.EmployeeMapper;
import com.charles.mapper.UserMapper;
import com.charles.service.IEmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author charlesBean
 * @since 2019-03-22
 */
@Transactional
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Autowired
    EmpDepartMapper empDepartMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public Boolean mims_delete(Integer id) {
        // 1删除其中间表的信息
        empDepartMapper.delete(new EntityWrapper<EmpDepart>().eq("mims_emp_id",id));
        // 2删除员工表(假删除)
        Employee employee = employeeMapper.selectById(id);
        employee.setDel(1);
        Integer integer = employeeMapper.updateById(employee);
        if (integer>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean mims_add(Employee employee, Integer[] ids) {
        // 1添加员工信息到员工表
        employee.setLocked(0);
        employee.setDel(0);
        employee.setNumber("mimsEmp");
        employee.setPhoto("\\media\\images\\admin.jpg");
        employeeMapper.insert(employee);
        Employee addEmp = employeeMapper.selectOne(employee);
        addEmp.setNumber(addEmp.getNumber()+addEmp.getId());
        Integer insertToEmp = employeeMapper.updateById(addEmp);
        // 2添加员工到用户表
        User user = new User(addEmp
                .getNumber(),
                addEmp.getNumber(),
                0,0,"/media/images/admin.jpg",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                "",
                "",
                "我是员工"+addEmp.getName(),
                1);
        Integer insertToUser = userMapper.insert(user);
        // 3设置其对应部门信息
        EmpDepart empDepart = new EmpDepart();
        for (Integer id : ids) {
            empDepart.setMimsDepartId(id);
            empDepart.setMimsEmpId(addEmp.getId());
            empDepartMapper.insert(empDepart);
        }
        if (insertToEmp>0&&insertToUser>0){
            return true;
        }
        return false;
    }

    @Override
    public Boolean mims_update(Employee employee, Integer[] ids) {
        //更新员工信息
        Integer updateById = employeeMapper.updateById(employee);
        //更新部门信息
        empDepartMapper.delete(new EntityWrapper<EmpDepart>().eq("mims_emp_id", employee.getId()));
        EmpDepart empDepart = new EmpDepart();
        empDepart.setMimsEmpId(employee.getId());
        for (Integer id : ids) {
            empDepart.setMimsDepartId(id);
            empDepartMapper.insert(empDepart);
        }
        if (updateById>0){
            return true;
        }
        return false;
    }
}
