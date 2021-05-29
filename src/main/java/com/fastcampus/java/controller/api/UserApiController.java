package com.fastcampus.java.controller.api;

import com.fastcampus.java.controller.CrudController;
import com.fastcampus.java.ifs.CrudInterface;
import com.fastcampus.java.model.entity.User;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.UserApiRequest;
import com.fastcampus.java.model.network.response.UserApiResponse;
import com.fastcampus.java.model.network.response.UserOrderInfoApiResponse;
import com.fastcampus.java.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {
    @Autowired
    private UserApiLogicService userApiLogicService;

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id) {
        return userApiLogicService.orderInfo(id);
    }

    @GetMapping("")
    public Header<List<UserApiResponse>> search(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("{}", pageable);
        return userApiLogicService.search(pageable);
    }

//
//    @PostConstruct
//    public void init() {
//        this.baseService = userApiLogicService;
//    }

//    @Override
//    @PostMapping("")            // /api/user
//    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
//        log.info("{}", request);
//        return userApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}")         // /api/user/{id}
//    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
//        log.info("read id : {}", id);
//        return userApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("")             // /api/user
//    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
//        return userApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}")      // /api/user/{id}
//    public Header delete(@PathVariable Long id) {
//        log.info("delete : {}", id);
//        return userApiLogicService.delete(id);
//    }
}
