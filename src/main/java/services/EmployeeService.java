package services;

import entities.Employee;
import entities.EmployeeView;
import services.utils.BaseEntityService;
import utility.Const;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService extends BaseEntityService implements EmployeeServiceI {

    @Override
    public List<EmployeeView> getAllEmployees() {
        try {
            List<EmployeeView> employees = executeQuery(Const.EMPLOYEE_VW_SELECT_ALL_QUERY, null);
            return employees;
        } catch (Exception ex) {
            return null;
        } finally {
            exit();
        }

    }

    @Override
    public Employee getEmployeeByUserName(String userName) {
        try {
            String sql = Const.EMPLOYEE_SELECT_ALL_QUERY_WITH_WHERE + "m01UserName = :userName";
            Map<String, Object> queryParams = new HashMap<String, Object>();
            queryParams.put("userName", userName);
            List<Employee> employees = executeQuery(sql, queryParams);
            if (employees.size() < 1) {
                return null;
            }
            return employees.get(0);
        } catch (Exception ex) {
            return null;
        } finally {

            exit();
        }
    }

    @Override
    public Employee getActiveEmployeeByUserName(String userName)
    {
        try {
            String sql = Const.ACTIVE_EMPLOYEE_SELECT + Const.AND + "m01UserName = :userName";
            Map<String, Object> queryParams = new HashMap<String, Object>();
            queryParams.put("userName", userName);
            List<Employee> employees = executeQuery(sql, queryParams);
            if (employees.size() < 1) {
                return null;
            }
            return employees.get(0);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Employee employee = (Employee) getById(id, new Employee());
        return employee;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        try {
            return add(employee);
        } catch (Exception ex) {
            return false;
        } finally {
        }
    }

}
