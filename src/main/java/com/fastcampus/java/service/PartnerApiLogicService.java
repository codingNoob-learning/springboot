package com.fastcampus.java.service;

import com.fastcampus.java.ifs.CrudInterface;
import com.fastcampus.java.model.entity.Partner;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.PartnerApiRequest;
import com.fastcampus.java.model.network.response.PartnerApiResponse;
import com.fastcampus.java.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PartnerApiLogicService implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {
    @Autowired
    PartnerRepository partnerRepository;

    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        PartnerApiRequest body = request.getData();

        Partner partner = Partner.builder()
                .address(body.getAddress())
                .businessNumber(body.getBusinessNumber())
                .callCenter(body.getCallCenter())
                .ceoName(body.getCeoName())
                .createdAt(LocalDateTime.now())
                .createdBy(body.getCreatedBy())
                .name(body.getName())
                .partnerNumber(body.getPartnerNumber())
                .registeredAt(LocalDateTime.now())
                .status(body.getStatus())
                .build();

        Partner newPartner = partnerRepository.save(partner);
        return response(newPartner);
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return partnerRepository.findById(id)
                .map(partner -> response(partner))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        PartnerApiRequest body = request.getData();

        return partnerRepository.findById(body.getId())
                .map(entityPartner -> {
                    entityPartner.setAddress(body.getAddress())
                            .setBusinessNumber(body.getBusinessNumber())
                            .setCallCenter(body.getCallCenter())
                            .setCeoName(body.getCeoName())
                            .setCreatedAt(LocalDateTime.now())
                            .setCreatedBy(body.getCreatedBy())
                            .setName(body.getName())
                            .setPartnerNumber(body.getPartnerNumber())
                            .setRegisteredAt(body.getRegisteredAt())
                            .setStatus(body.getStatus());
                    return entityPartner;
                })
                .map(newEntityPartner -> partnerRepository.save(newEntityPartner))
                .map(partner -> response(partner))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return partnerRepository.findById(id)
                .map(partner -> {
                    partnerRepository.delete(partner);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<PartnerApiResponse> response(Partner partner) {
        PartnerApiResponse body = PartnerApiResponse.builder()
                .address(partner.getAddress())
                .businessNumber(partner.getBusinessNumber())
                .callCenter(partner.getCallCenter())
                .ceoName(partner.getCeoName())
                .createdAt(partner.getCreatedAt())
                .createdBy(partner.getCreatedBy())
                .name(partner.getName())
                .partnerNumber(partner.getPartnerNumber())
                .registeredAt(partner.getRegisteredAt())
                .status(partner.getStatus())
                .build();

        return Header.OK(body);
    }
}
