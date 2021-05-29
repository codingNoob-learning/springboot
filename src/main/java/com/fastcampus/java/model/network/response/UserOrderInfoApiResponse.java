package com.fastcampus.java.model.network.response;

import com.fastcampus.java.model.entity.OrderGroup;
import com.fastcampus.java.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderInfoApiResponse {
    private UserApiResponse userApiResponse;
}
