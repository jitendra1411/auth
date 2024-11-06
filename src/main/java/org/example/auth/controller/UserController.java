package org.example.auth.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.LogstashMarker;
import org.example.auth.controller.request.RegisterUserReq;
import org.example.auth.dto.GenericResponse;
import org.example.auth.dto.user.UserDto;
import org.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static net.logstash.logback.marker.Markers.append;

/**
 * @author Jitendra (Jeetu)
 */


@Slf4j
@RestController
@RequestMapping("v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public GenericResponse<UserDto> registerUser(@RequestBody @Valid RegisterUserReq registerUserReq){
        LogstashMarker marker = append("method", "registerUser")
                .and(append("username", registerUserReq.getUsername()));
        log.info(marker, "request received to register user");
        return new GenericResponse<>(userService.registerUser(registerUserReq));
    }
}
