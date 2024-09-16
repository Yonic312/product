package com.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao {
    void insert(ProductVO vo);
    void delete(ProductVO vo);
    List<ProductVO> select(ProductVO vo);
    List<ProductVO> selectOne(ProductVO vo);
}
