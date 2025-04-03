package com.coding404.myweb.product.service;

import com.coding404.myweb.controller.ProductVO;
import com.coding404.myweb.util.Criteria;

import java.util.List;

public interface ProductService {
    int productRegist(ProductVO vo);
    List<ProductVO> getList(String prodWriter, Criteria cri);
    ProductVO getDetail(String prodId);
    void productUpdate(ProductVO vo);
    int productDelete(ProductVO vo);
    int getTotal(String prodWriter);
}
