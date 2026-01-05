package nmg.tooling.assistant.service.oracle.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nmg.tooling.assistant.entity.oracle.Employee;
import nmg.tooling.assistant.mapper.oracle.EmployeeMapper;
import nmg.tooling.assistant.service.oracle.EmployeeService;
import org.springframework.stereotype.Service;

@Service
@DS("oracle")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}