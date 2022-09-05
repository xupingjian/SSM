package com.atguigu.ssm.service.impl;

import com.atguigu.ssm.mapper.EmployeeMapper;
import com.atguigu.ssm.pojo.Employee;
import com.atguigu.ssm.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    public List<Employee> getAllEmployee() {
        return employeeMapper.getAllEmployee();
    }

    public PageInfo<Employee> getEmployeePage(Integer pageNum) {
//        开启分页功能
        PageHelper.startPage(pageNum,4);
//        查询所有员工信息
        List<Employee> list = employeeMapper.getAllEmployee();
//获取分页相关数据
        PageInfo<Employee> page = new PageInfo<Employee>(list, 3);
        return page;
    }

    public Employee getEmployeeById(Integer empId) {
        Employee emp = employeeMapper.getEmployeeById(empId);
        return emp ;
    }

    public void addEmployee(Employee employee) {
        employeeMapper.addEmployee(employee);

    }

    public void updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }

    public void deleteEmployee(Integer empId) {
        employeeMapper.deleteEmployee(empId);
    }
}
