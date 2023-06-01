package com.Abdessalam.friendMA.service;

import com.Abdessalam.friendMA.dto.mapperInterface.IMapperDto;
import com.Abdessalam.friendMA.dto.model.UserDto;
import com.Abdessalam.friendMA.entity.User;
import com.Abdessalam.friendMA.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final IMapperDto<User, UserDto> userMapper;

    public UserDto save(UserDto userDto) {
        log.info("Saving user with username: {}", userDto.getUsername());
        User user = userMapper.convertToEntity(userDto, User.class);
        user = userRepository.save(user);
        return userMapper.convertToDto(user, UserDto.class);
    }

    public List<UserDto> findAll() {
        log.info("Retrieving all users");
        return userMapper.convertListToListDto(userRepository.findAll(), UserDto.class);
    }

    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserDto findByUsername(String username) {
        return userMapper.convertToDto(userRepository.findByUsername(username), UserDto.class);
    }
}
