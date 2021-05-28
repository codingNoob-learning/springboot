package com.fastcampus.java.service;

import com.fastcampus.java.ifs.CrudInterface;
import com.fastcampus.java.model.entity.Category;
import com.fastcampus.java.model.network.Header;
import com.fastcampus.java.model.network.request.CategoryApiRequest;
import com.fastcampus.java.model.network.response.CategoryApiResponse;
import com.fastcampus.java.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryApiLogicService implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        CategoryApiRequest body = request.getData();

        Category category = Category.builder()
                .createdAt(body.getCreatedAt())
                .createdBy(body.getCreatedBy())
                .title(body.getTitle())
                .type(body.getType())
                .build();

        Category newCategory = categoryRepository.save(category);

        return response(newCategory);
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return categoryRepository.findById(id)
                .map(category -> response(category))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        CategoryApiRequest body = request.getData();

        return categoryRepository.findById(body.getId())
                .map(entityCategory -> {
                    entityCategory.setCreatedAt(body.getCreatedAt())
                        .setCreatedBy(body.getCreatedBy())
                        .setTitle(body.getTitle())
                        .setType(body.getType());
                        return entityCategory;
                    })
                .map(newEntityCategory -> {
                    categoryRepository.save(newEntityCategory);
                    return newEntityCategory;
                })
                .map(category -> response(category))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.delete(category);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private Header<CategoryApiResponse> response(Category category) {
        CategoryApiResponse body = CategoryApiResponse.builder()
                .createdAt(category.getCreatedAt())
                .createdBy(category.getCreatedBy())
                .title(category.getTitle())
                .type(category.getType())
                .build();

        return Header.OK(body);
    }
}
