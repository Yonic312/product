package com.product;

import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    void insert(ProductVO vo);
    void update(ProductVO vo);
    void delete(ProductVO vo);
    List<ProductVO> select(ProductVO vo);
    int totalCount(String searchCondition, String searchKeywordR);
    ProductVO selectOne(ProductVO vo);
}
