package com.atguigu.ssm.controller;

import com.atguigu.ssm.pojo.Employee;
import com.atguigu.ssm.service.EmployeeService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Date:2022/7/11
 * Author:ybc
 * Description:
 * 查询所有的员工信息-->/employee-->get
 * 查询员工的分页信息-->/employee/page/1-->get
 * 根据id查询员工信息-->/employee/1-->get
 * 跳转到添加页面-->/to/add-->get
 * 添加员工信息-->/employee-->post
 * 修改员工信息-->/employee-->put
 * 删除员工信息-->/employee/1-->delete
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

//    删除的控制方法
    @RequestMapping(value = "/employee/delete/{empId}",method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("empId") Integer empId){
        employeeService.deleteEmployee(empId);
        return "redirect:/employee/page/1";
    }
//更新的控制方法
    @RequestMapping(value = "/employee",method = RequestMethod.PUT)
    public String updateEmployee(Employee employee){
        employeeService.updateEmployee(employee);
        return "redirect:/employee/page/1";
    }

    @RequestMapping(value = "/employee/page/{pageNum}",method = RequestMethod.GET)
    public String getEmployeePage(@PathVariable("pageNum")Integer pageNum,Model model){
        //获取员工的分页信息
        PageInfo<Employee> page = employeeService.getEmployeePage(pageNum);
        //将分页数据共享到域对象
        model.addAttribute("page",page);
        return "employee_list";
    }

    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    public String getAllEmployee(Model model){
//        查询所有的员工信息
        List<Employee> list = employeeService.getAllEmployee();
        model.addAttribute("list",list);
        return "employee_allList";
    }

    @RequestMapping(value = "/employee/{empId}",method = RequestMethod.GET)
    public String getEmployeeById(@PathVariable("empId") Integer empId,Model model){
//        根据id查员工信息
        Employee employee=employeeService.getEmployeeById(empId);
        model.addAttribute("employee",employee);
        return "employee_update";
    }
//添加用户信息
    @RequestMapping(value = "/Add",method = RequestMethod.POST)
    public String addEmployee(Employee emp){
        Integer empId = emp.getEmpId();
        Integer age = emp.getAge();
        String empName = emp.getEmpName();
        String email = emp.getEmail();
        String gender = emp.getGender();
        Employee employee = new Employee(empId, empName, age, gender, email);
        employeeService.addEmployee(employee);

        return "redirect:/employee/page/1";
    }
}
