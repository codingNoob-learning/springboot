package com.fastcampus.java.service;

import com.fastcampus.java.ifs.CrudInterface;
import com.fastcampus.java.model.entity.Category;
import com.fastcampus.java.model.entity.OrderDetail;
import com.fastcampus.java.model.entity.OrderGroup;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.CategoryApiRequest;
import com.fastcampus.java.model.network.request.OrderDetailApiRequest;
import com.fastcampus.java.model.network.response.CategoryApiResponse;
import com.fastcampus.java.model.network.response.OrderDetailApiResponse;
import com.fastcampus.java.repository.ItemRepository;
import com.fastcampus.java.repository.OrderDetailRepository;
import com.fastcampus.java.repository.OrderGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {
//    @Autowired
//    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderGroupRepository orderGroupRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest body = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                .arrivalDate(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .createdBy(body.getCreatedBy())
                .quantity(body.getQuantity())
                .status(body.getStatus())
                .totalPrice(body.getTotalPrice())
                .orderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                .item(itemRepository.getOne(body.getItemId()))
                .build();

        OrderDetail newOrderDetail = baseRepository.save(orderDetail);

        return response(newOrderDetail);
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(orderDetail -> response(orderDetail))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(entityOrderDetail -> {
                    entityOrderDetail.setArrivalDate(body.getArrivalDate())
                            .setCreatedAt(body.getCreatedAt())
                            .setCreatedBy(body.getCreatedBy())
                            .setQuantity(body.getQuantity())
                            .setStatus(body.getStatus())
                            .setTotalPrice(body.getTotalPrice())
                            .setOrderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                            .setItem(itemRepository.getOne(body.getItemId()));
                    return entityOrderDetail;
                })
                .map(newEntityOrderDetail -> {
                    baseRepository.save(newEntityOrderDetail);
                    return newEntityOrderDetail;
                })
                .map(orderDetail -> response(orderDetail))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(orderDetail -> {
                    baseRepository.delete(orderDetail);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<OrderDetailApiResponse> response(OrderDetail orderDetail) {
        OrderDetailApiResponse body = OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .arrivalDate(orderDetail.getArrivalDate())
                .createdAt(orderDetail.getCreatedAt())
                .createdBy(orderDetail.getCreatedBy())
                .quantity(orderDetail.getQuantity())
                .status(orderDetail.getStatus())
                .totalPrice(orderDetail.getTotalPrice())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .itemId(orderDetail.getItem().getId())
                .build();

        return Header.OK(body);
    }
}
