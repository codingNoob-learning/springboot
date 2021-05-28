package com.fastcampus.java.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminUserApiRequest {
    private Long id;
    private String account;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime lastLoginAt;
    private Integer loginFailCount;
    private String password;
    private LocalDateTime passwordUpdatedAt;
    private LocalDateTime registeredAt;
    private String role;
    private String status;
    private LocalDateTime unregisteredAt;
}
