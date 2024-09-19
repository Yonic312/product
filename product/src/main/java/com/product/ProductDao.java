package com.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {
    void insert(ProductVO vo);
    void update(ProductVO vo);
    void delete(ProductVO vo);
    List<ProductVO> select(ProductVO vo);
    int totalCount(String searchCondition, String searchKeywordR);
    ProductVO selectOne(ProductVO vo);
}
