package com.hoang.AuthenticationSystem.Mapper;

import com.hoang.AuthenticationSystem.dto.user.UserDto;
import com.hoang.AuthenticationSystem.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);
}
