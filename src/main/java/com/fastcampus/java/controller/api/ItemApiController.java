package com.fastcampus.java.controller.api;

import com.fastcampus.java.ifs.CrudInterface;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.ItemApiRequest;
import com.fastcampus.java.model.network.response.ItemApiResponse;
import com.fastcampus.java.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {
    @Autowired
    private ItemApiLogicService itemApiLogicService;
    @Override
    @PostMapping("")            // /api/item
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")         // /api/item/{id}
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping("")             // /api/item
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")      // /api/item/{id}
    public Header delete(@PathVariable Long id) {
        return itemApiLogicService.delete(id);
    }
}
