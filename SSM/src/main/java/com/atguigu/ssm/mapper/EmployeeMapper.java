package com.atguigu.ssm.mapper;

import com.atguigu.ssm.pojo.Employee;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    List<Employee> getAllEmployee();


    Employee getEmployeeById(@Param("empId") Integer empId);

    void addEmployee( Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(@Param("empId") Integer empId);
}
