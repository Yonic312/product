package com.product;

import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    void insert(ProductVO vo);
    void delete(ProductVO vo);
    List<ProductVO> select(ProductVO vo);
}
