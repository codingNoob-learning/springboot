package com.fastcampus.java.service;

import com.fastcampus.java.ifs.CrudInterface;
import com.fastcampus.java.model.entity.AdminUser;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.AdminUserApiRequest;
import com.fastcampus.java.model.network.response.AdminUserApiResponse;
import com.fastcampus.java.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminUserApiLogicService implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {
    @Autowired
    AdminUserRepository adminUserRepository;

    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest body = request.getData();

        AdminUser adminUser = AdminUser.builder()
                .account(body.getAccount())
                .createdAt(LocalDateTime.now())
                .createdBy(body.getCreatedBy())
                .lastLoginAt(body.getLastLoginAt())
                .loginFailCount(body.getLoginFailCount())
                .password(body.getPassword())
                .registeredAt(LocalDateTime.now())
                .role(body.getRole())
                .status(body.getStatus())
                .build();

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {
        return adminUserRepository.findById(id)
                .map(adminUser -> response(adminUser))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
        AdminUserApiRequest body = request.getData();

        return adminUserRepository.findById(body.getId())
                .map((entityAdminUser -> {
                    entityAdminUser.setAccount(body.getAccount())
                            .setLoginFailCount(body.getLoginFailCount())
                            .setPassword(body.getPassword())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setRole(body.getRole())
                            .setStatus(body.getStatus());
                    return entityAdminUser;
                }))
                .map(newEntityAdminUser -> {
                    adminUserRepository.save(newEntityAdminUser);
                    return newEntityAdminUser;
                })
                .map(adminUser -> response(adminUser))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return adminUserRepository.findById(id)
                .map(adminUser -> {
                    adminUserRepository.delete(adminUser);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<AdminUserApiResponse> response(AdminUser adminUser) {
        AdminUserApiResponse body = AdminUserApiResponse.builder()
                .account(adminUser.getAccount())
                .createdAt(adminUser.getCreatedAt())
                .createdBy(adminUser.getCreatedBy())
                .lastLoginAt(adminUser.getLastLoginAt())
                .loginFailCount(adminUser.getLoginFailCount())
                .password(adminUser.getPassword())
                .registeredAt(adminUser.getRegisteredAt())
                .role(adminUser.getRole())
                .status(adminUser.getStatus())
                .build();

        return Header.OK(body);
    }
}
