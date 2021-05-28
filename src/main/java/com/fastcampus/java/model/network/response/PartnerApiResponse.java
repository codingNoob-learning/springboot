package com.fastcampus.java.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerApiResponse {
    private Long id;
    private String address;
    private String businessNumber;
    private String callCenter;
    private Long categoryId;
    private String ceoName;
    private LocalDateTime createdAt;
    private String createdBy;
    private String name;
    private String partnerNumber;
    private LocalDateTime registeredAt;
    private String status;
    private LocalDateTime unregisteredAt;
}
