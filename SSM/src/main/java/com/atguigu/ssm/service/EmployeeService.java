package com.atguigu.ssm.service;

import com.atguigu.ssm.pojo.Employee;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> getAllEmployee();
    //��ȡԱ���ķ�ҳ��Ϣ
    PageInfo<Employee> getEmployeePage(Integer pageNum);

    Employee getEmployeeById(Integer empId);

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Integer empId);
}
