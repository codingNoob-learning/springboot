package com.fastcampus.java.controller.api;

import com.fastcampus.java.controller.CrudController;
import com.fastcampus.java.model.entity.Settlement;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.SettlementApiRequest;
import com.fastcampus.java.model.network.response.SettlementApiResponse;
import com.fastcampus.java.service.SettlementApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/settlement")
public class SettlementApiController extends CrudController<SettlementApiRequest, SettlementApiResponse, Settlement> {
    @Autowired
    SettlementApiLogicService settlementApiLogicService;

    @Override
    @GetMapping("/{id}")
    public Header<SettlementApiResponse> read(@PathVariable Long id) {
        return settlementApiLogicService.read(id);
    }
}
