package org.example.auth.service;


import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.LogstashMarker;
import org.example.auth.constant.RoleStatus;
import org.example.auth.constant.UserAccountStatus;
import org.example.auth.controller.request.RegisterUserReq;
import org.example.auth.dto.user.UserDto;
import org.example.auth.entity.RoleEntity;
import org.example.auth.entity.UserAccountEntity;
import org.example.auth.entity.UserRoleEntity;
import org.example.auth.exception.Errors;
import org.example.auth.exception.ServiceException;
import org.example.auth.repository.RoleRepo;
import org.example.auth.repository.UserAccountRepo;
import org.example.auth.repository.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import static net.logstash.logback.marker.Markers.append;

/**
 * @author Jitendra (Jeetu)
 */

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private TransactionTemplate transactionTemplate;

    public UserDto registerUser(RegisterUserReq registerUserReq){
        LogstashMarker marker = append("method", "registerUser")
                .and(append("username", registerUserReq.getUsername()));
        log.info(marker, "validating request");
        RoleEntity roleEntity = roleRepo.findByNameAndStatus(registerUserReq.getRole(), RoleStatus.ACTIVE.name());
        validateReq(registerUserReq, roleEntity, marker);
        return transactionTemplate.execute(data -> createUser(registerUserReq, roleEntity, marker));
    }



    private UserDto createUser(RegisterUserReq registerUserReq, RoleEntity roleEntity, LogstashMarker marker){
        log.info(marker, "creating a new user");
        UserAccountEntity userAccount = userAccountRepo.save(userAccountFromReq(registerUserReq));
        userRoleRepo.save(userRoleFromReq(userAccount, roleEntity));
        log.info("user created successfully");
        return fromUserAccountAndUserRole(userAccount, roleEntity);

    }

    private UserDto fromUserAccountAndUserRole(UserAccountEntity userAccount, RoleEntity role){
        UserDto user = new UserDto();
        user.setUserId(userAccount.getId());
        user.setUsername(userAccount.getUsername());
        user.setEmail(userAccount.getEmail());
        user.setEmailVerified(userAccount.getEmailVerified() != null && userAccount.getEmailVerified());
        user.setPhone(userAccount.getPhone());
        user.setPhoneVerified(userAccount.getPhoneVerified() != null && userAccount.getPhoneVerified());
        user.setStatus(userAccount.getStatus());
        user.setCreatedOn(userAccount.getCreatedOn());
        user.setLastUpdatedOn(userAccount.getLastUpdatedOn());
        user.setRole(role.getName());
        return user;
    }

    private UserRoleEntity userRoleFromReq(UserAccountEntity userAccount, RoleEntity role){
        UserRoleEntity userRole = new UserRoleEntity();
        userRole.setRoleId(role.getId());
        userRole.setUserId(userAccount.getId());
        return userRole;
    }

    private UserAccountEntity userAccountFromReq(RegisterUserReq registerUserReq){
        UserAccountEntity userAccount = new UserAccountEntity();
        userAccount.setUsername(registerUserReq.getUsername());
        userAccount.setPassword(registerUserReq.getPassword());
        userAccount.setPhone(registerUserReq.getPhone());
        userAccount.setPhoneVerified(false);
        userAccount.setEmail(registerUserReq.getEmail());
        userAccount.setEmailVerified(false);
        userAccount.setStatus(UserAccountStatus.USER_CREATED.name());
        return userAccount;
    }

    private void validateReq(RegisterUserReq registerUserReq, RoleEntity roleEntity, LogstashMarker marker){
        checkValidRole(roleEntity, marker);
        checkUserAlreadyExist(registerUserReq.getUsername(), registerUserReq.getPhone(), registerUserReq.getEmail(), marker);
    }

    public void checkValidRole(RoleEntity roleEntity, LogstashMarker marker){
        log.info(marker, "checking valid role");
        if(roleEntity == null){
            log.error(marker, "role not found");
            throw ServiceException.notFound(Errors.ROLE_DOES_NOT_EXIST,
                    Errors.errorMap.get(Errors.ROLE_DOES_NOT_EXIST));
        }
    }
    private void checkUserAlreadyExist(String username, String phone, String email, LogstashMarker marker){
        log.info(marker, "checking user already exist");
        if(phone != null && userAccountRepo.findByPhone(phone) != null){
            log.error(marker, "user already exist with given phone");
            throw ServiceException.conflict(Errors.USER_ALREADY_EXIST_WITH_PHONE,
                    Errors.errorMap.get(Errors.USER_ALREADY_EXIST_WITH_PHONE));
        }

        if(email != null && userAccountRepo.findByEmail(email) != null){
            log.error(marker, "user already exist with given email");
            throw ServiceException.conflict(Errors.USER_ALREADY_EXIST_WITH_EMAIL,
                    Errors.errorMap.get(Errors.USER_ALREADY_EXIST_WITH_EMAIL));
        }

        if(username != null && userAccountRepo.findByUsername(username) != null){
            log.error(marker, "user already exist with given username");
            throw ServiceException.conflict(Errors.USER_ALREADY_EXIST_WITH_USERNAME,
                    Errors.errorMap.get(Errors.USER_ALREADY_EXIST_WITH_USERNAME));
        }
    }
}
