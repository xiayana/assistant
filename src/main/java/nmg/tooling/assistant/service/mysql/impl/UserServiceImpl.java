package nmg.tooling.assistant.service.mysql.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import nmg.tooling.assistant.entity.mysql.User;
import nmg.tooling.assistant.mapper.mysql.UserMapper;
import nmg.tooling.assistant.service.mysql.UserService;
import org.springframework.stereotype.Service;

@Service
@DS("mysql")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}