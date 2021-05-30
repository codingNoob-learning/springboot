package com.fastcampus.java.model.network.request;

import com.fastcampus.java.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SettlementApiRequest {
    private Long id;
    private BigDecimal price;
}
