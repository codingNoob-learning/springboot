package com.fastcampus.java.service;

import com.fastcampus.java.model.entity.*;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.OrderGroupApiRequest;
import com.fastcampus.java.model.network.request.SettlementApiRequest;
import com.fastcampus.java.model.network.response.SettlementApiResponse;
import com.fastcampus.java.repository.OrderGroupRepository;
import com.fastcampus.java.repository.SettlementRepository;
import com.fastcampus.java.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class SettlementApiLogicService extends BaseService<SettlementApiRequest, SettlementApiResponse, Settlement> {
    @Autowired
    SettlementRepository settlementRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Header<SettlementApiResponse> create(Header<SettlementApiRequest> request) {
        return null;
    }

    @Override
    public Header<SettlementApiResponse> read(Long id) {
        return null;
//            baseRepository.findById(id)
//                .map(settlement -> response(settlement))
//                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<SettlementApiResponse> update(Header<SettlementApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<SettlementApiResponse> response(Settlement settlement) {
        SettlementApiResponse body = SettlementApiResponse.builder()
                .id(settlement.getUserId())
                .price(settlement.getPrice())
                .build();
        return Header.OK(body);
    }

    public Header<SettlementApiResponse> settlementInfo(Long id) {
        return settlementRepository.findById(id)
                .map(settlement -> response(settlement))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public Header<SettlementApiResponse> settlementCreate(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();

        SettlementApiRequest settlement = SettlementApiRequest.builder()
                .id(body.getUserId())
                .price(body.getTotalPrice())
                .build();

        // UserId가 PK 이므로 기존에 생성되어있는 UserId의 값이 들어왔을 경우 새로 row를 만들 수 없음.
        // 그렇기 때문에 기존 UserId를 찾아서 새로운 주문건의 가격을 합쳐야 함.
        Optional<Settlement> settlementCheck = settlementRepository.findById(body.getUserId());
        if(settlementCheck.isPresent()) {
            BigDecimal price = settlement.getPrice();
            settlementCheck.map(updateSettlement -> {
                updateSettlement.setPrice(updateSettlement.getPrice().add(price));
                return updateSettlement;
            })
                    .map(resultSettlement -> baseRepository.save(resultSettlement))
                    .map(settlementResponse -> response(settlementResponse));
        } else {
            Settlement settlementResponse = Settlement.builder()
                    .userId(settlement.getId())
                    .price(settlement.getPrice())
                    .build();

            Settlement createSettlement = baseRepository.save(settlementResponse);

            return response(createSettlement);
        }
        return Header.OK();
    }
}
