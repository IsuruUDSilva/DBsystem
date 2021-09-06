package services;

import entities.Employee;
import entities.EmployeeView;

import java.util.List;

public interface ProductServiceI {


    List<EmployeeView> getAllProducts();

    Employee getProductByUserName(String userName);

    Employee getActiveEmployeeByUserName(String userName);

    boolean addEmployee(Employee employee);

    Employee getEmployeeById(Integer id);
}
