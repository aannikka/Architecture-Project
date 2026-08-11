package app.service;

import app.dto.request.UserRequest;
import app.dto.response.UserResponse;
import app.entity.User;
import app.exception.EntityNotFoundException;
import app.mapper.UserMapper;
import app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
     private final UserRepository userRepository;
     private final UserMapper mapper;

    public UserResponse create(UserRequest request) {
        User user = mapper.toEntity(request);
        User saved = userRepository.save(user);
        return mapper.toResponse(saved);
    }

    public UserResponse update(Long id, UserRequest request) {
        User user = getUser(id);
        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setEmail(request.email());
        User updated = userRepository.save(user);
        return mapper.toResponse(updated);
    }

    public UserResponse findById(Long id) {
        User user = getUser(id);
        return mapper.toResponse(user);
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public void deleteById(Long id) {
        userRepository.delete(getUser(id));
    }

    private User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                String.format("User with id %d not found", id)));
    }
}
