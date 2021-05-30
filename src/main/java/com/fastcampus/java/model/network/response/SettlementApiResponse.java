package com.fastcampus.java.model.network.response;

import com.fastcampus.java.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettlementApiResponse {
    private Long id;
    private BigDecimal price;
}
