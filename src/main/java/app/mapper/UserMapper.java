package app.mapper;

import app.dto.request.UserRequest;
import app.dto.response.UserResponse;
import app.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        return User.builder()
                .username(request.username())
                .password(request.password())
                .email(request.email())
                .build();
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
