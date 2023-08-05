package JDBC.ManHanLouRestaurant.Service;

import JDBC.ManHanLouRestaurant.DAO.EmployeeDAO;
import JDBC.ManHanLouRestaurant.Domain.Employee;

// todo service层还要再写sql语句么？（完全不建议sql语句放在service层，sql语句只停留在DAO层就好）
public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public Employee getEmployeeByIdAndPwd(String id, String pwd) {
        return employeeDAO.querySingle("select * from employee where empId = ? and pwd = md5(?)", Employee.class, id, pwd);
    }
}
