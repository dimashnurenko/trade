package com.trade.domain.user;

import com.trade.common.ResourceNotFoundException;
import com.trade.web.user.UserDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Optional.ofNullable;

@Service(value = "userService")  class UserServiceImpl implements UserService {
    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    private final UserRepo userRepo;
    private final UserMapper mapper;

    UserServiceImpl (UserRepo userRepo, UserMapper mapper) {
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    @Override
    @Transactional (readOnly = true)
    public UserDto findOne(Long id) {
        return ofNullable(id).map(userRepo::findOne)
                .map(mapper::map)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public long createUser(UserDto dto) {
        Long userId = saveOrUpdate(dto);
        LOG.debug(String.format("User with id: %s created.", userId));
        return userId;
    }

    @Override
    @Transactional
    public void updateUser(UserDto dto) {
        Long userId = saveOrUpdate(dto);
        LOG .debug(String.format("User with id: %s updated.", userId));
    }

    private long saveOrUpdate(UserDto dto) {
        User user  = userRepo.save(mapper.map(dto));
        return user.getId();
    }
}
