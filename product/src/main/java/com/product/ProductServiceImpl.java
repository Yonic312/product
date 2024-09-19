package com.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao dao;
    ProductServiceImpl(){
        System.out.println("ProductServiceImpl 생성자");
    }

    public void insert(ProductVO vo) {
        dao.insert(vo);
    }

    public void update(ProductVO vo) {
        dao.update(vo);
    }

    public void delete(ProductVO vo) {
        dao.delete(vo);
    }

    public List<ProductVO> select(ProductVO vo) {
        return dao.select(vo);
    }

    public int totalCount(String searchCondition, String searchKeywordR) {
        return dao.totalCount(searchCondition, searchKeywordR);
    }

    public ProductVO selectOne(ProductVO vo) {
        return dao.selectOne(vo);
    }

}
