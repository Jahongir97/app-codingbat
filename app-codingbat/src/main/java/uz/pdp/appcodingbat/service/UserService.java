package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entitiy.User;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.UserDto;
import uz.pdp.appcodingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * In this method we are returning all Users
     *
     * @return Users
     */

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * In this method we are returning a User with given id
     *
     * @param id Integer
     * @return User
     */

    public User getUserById(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    /**
     * In this method we are creating new User
     *
     * @return ApiResponse
     * We are getting Json object.
     * putting Validation User
     */

    public ApiResponse addUser(UserDto userDto) {
        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        if (exists) {
            return new ApiResponse("This User already exist", false);
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("User successfully added", true);
    }

    /**
     * User editing method
     *
     * @param id      Integer
     * @param userDto Json
     * @return ApiResponse
     * We are getting id and UserDto Json Object.
     */

    public ApiResponse editUser(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User editingUser = optionalUser.get();
            editingUser.setEmail(userDto.getEmail());
            editingUser.setPassword(userDto.getPassword());
            userRepository.save(editingUser);
            return new ApiResponse("Successfully edited", true);
        }
        return new ApiResponse("User not found", false);
    }

    /**
     * Deleting User by Id
     *
     * @param id Integer
     * @return ApiResponse
     */

    public ApiResponse deleteUser(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("User deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);

        }
    }
}
