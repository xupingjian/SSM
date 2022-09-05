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
 * ��ѯ���е�Ա����Ϣ-->/employee-->get
 * ��ѯԱ���ķ�ҳ��Ϣ-->/employee/page/1-->get
 * ����id��ѯԱ����Ϣ-->/employee/1-->get
 * ��ת�����ҳ��-->/to/add-->get
 * ���Ա����Ϣ-->/employee-->post
 * �޸�Ա����Ϣ-->/employee-->put
 * ɾ��Ա����Ϣ-->/employee/1-->delete
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

//    ɾ���Ŀ��Ʒ���
    @RequestMapping(value = "/employee/delete/{empId}",method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("empId") Integer empId){
        employeeService.deleteEmployee(empId);
        return "redirect:/employee/page/1";
    }
//���µĿ��Ʒ���
    @RequestMapping(value = "/employee",method = RequestMethod.PUT)
    public String updateEmployee(Employee employee){
        employeeService.updateEmployee(employee);
        return "redirect:/employee/page/1";
    }

    @RequestMapping(value = "/employee/page/{pageNum}",method = RequestMethod.GET)
    public String getEmployeePage(@PathVariable("pageNum")Integer pageNum,Model model){
        //��ȡԱ���ķ�ҳ��Ϣ
        PageInfo<Employee> page = employeeService.getEmployeePage(pageNum);
        //����ҳ���ݹ��������
        model.addAttribute("page",page);
        return "employee_list";
    }

    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    public String getAllEmployee(Model model){
//        ��ѯ���е�Ա����Ϣ
        List<Employee> list = employeeService.getAllEmployee();
        model.addAttribute("list",list);
        return "employee_allList";
    }

    @RequestMapping(value = "/employee/{empId}",method = RequestMethod.GET)
    public String getEmployeeById(@PathVariable("empId") Integer empId,Model model){
//        ����id��Ա����Ϣ
        Employee employee=employeeService.getEmployeeById(empId);
        model.addAttribute("employee",employee);
        return "employee_update";
    }
//����û���Ϣ
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
