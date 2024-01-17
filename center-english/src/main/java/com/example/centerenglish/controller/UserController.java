package com.example.centerenglish.controller;


import com.example.centerenglish.request.UserRequest;
import com.example.centerenglish.request.UserRequestFilter;
import com.example.centerenglish.response.SuccessResponse;
import com.example.centerenglish.response.UserResponse;
import com.example.centerenglish.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
    private final MessageSource messageSource;

    private final UserServiceImpl userService;

    @PostMapping("")
    public ResponseEntity<SuccessResponse<UserResponse>> createUser(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.createUserWithSchedule(userRequest);
        var message = messageSource.getMessage(
                "user.create-success",
                new Long[]{userResponse.getId()},
                null,
                LocaleContextHolder.getLocale()
        );
        SuccessResponse<UserResponse> response = new SuccessResponse<>(
                HttpStatus.OK.value(),
                message,
                userResponse);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/get-all")
    public ResponseEntity<Map<String,Object>> getAllUsersPagination(@RequestBody UserRequestFilter userRequestFilter){
        return ResponseEntity.ok().body(this.userService.getAllUsers(userRequestFilter));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<SuccessResponse<UserResponse>> getUserById(@PathVariable Long userId) {
        UserResponse userResponse = userService.getUserById(userId);
            var message = messageSource.getMessage(
                    "user.get-success",
                    new Long[]{userResponse.getId()},
                    null,
                    LocaleContextHolder.getLocale()
            );
            SuccessResponse<UserResponse> response = new SuccessResponse<>(
                    HttpStatus.OK.value(),
                    message,
                    userResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @PostMapping(value = "/update/task")
    public ResponseEntity<SuccessResponse<Boolean>> updateUser(@RequestBody Map<String, Object> body) {
        Boolean result = this.userService.updateUser(body);

        if (!result) {
            // If the update was not successful
            SuccessResponse<Boolean> response = new SuccessResponse<>(
                    HttpStatus.CONFLICT.value(),
                    "user.update-failure",
                    false);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        // If the update was successful
        SuccessResponse<Boolean> response = new SuccessResponse<>(
                HttpStatus.OK.value(),
                "user.update-success",
                true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/delete-by-id")
    public ResponseEntity<SuccessResponse<Boolean>> deleteUserById(@RequestParam Long id){
        Boolean result= userService.deleteRequestUserById(id);
        if (!result){
            SuccessResponse<Boolean> response = new SuccessResponse<>(
                    HttpStatus.CONFLICT.value(),
                    "user.delete-failure",
                    false);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        // If the update was successful
        SuccessResponse<Boolean> response = new SuccessResponse<>(
                HttpStatus.OK.value(),
                "user.delete-success",
                true);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
