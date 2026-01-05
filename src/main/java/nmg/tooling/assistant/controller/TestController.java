package nmg.tooling.assistant.controller;

import nmg.tooling.assistant.entity.mysql.User;
import nmg.tooling.assistant.entity.oracle.Employee;
import nmg.tooling.assistant.service.mysql.UserService;
import nmg.tooling.assistant.service.oracle.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    // MySQL 测试接口
    @GetMapping("/mysql/users")
    public List<User> getMysqlUsers() {
        return userService.list();
    }

    @PostMapping("/mysql/user")
    public User addMysqlUser(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    // Oracle 测试接口
    @GetMapping("/oracle/employees")
    public List<Employee> getOracleEmployees() {
        return employeeService.list();
    }

    @PostMapping("/oracle/employee")
    public Employee addOracleEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return employee;
    }
}