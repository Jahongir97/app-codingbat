package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entitiy.User;
import uz.pdp.appcodingbat.payload.ApiResponse;
import uz.pdp.appcodingbat.payload.UserDto;
import uz.pdp.appcodingbat.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * In this method we are returning all Users
     *
     * @return Users
     */

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * In this method we are returning a User with given id
     *
     * @param id Integer
     * @return User
     */

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {

        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * In this method we are creating new User
     *
     * @return ApiResponse
     * We are getting Json object.
     * putting Validation User
     */

    @PostMapping
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody UserDto userDto
    ) {
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * User editing method
     *
     * @param id      Integer
     * @param userDto Json
     * @return ApiResponse
     * We are getting id and UserDto Json Object.
     */

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editUser(@PathVariable Integer id, @Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.editUser(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * Deleting User by Id
     *
     * @param id Integer
     * @return ApiResponse
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        ApiResponse apiResponse = userService.deleteUser(id);

        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse);
    }
}
