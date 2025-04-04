package com.coding404.myweb.product.service;

import com.coding404.myweb.controller.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public int productRegist(ProductVO vo) {
        return productMapper.productRegist(vo);
    }

    @Override
    public List<ProductVO> getList(String prodWriter, Criteria cri) {
        return productMapper.getList(prodWriter, cri);
    }

    @Override
    public int getTotal(String prodWriter, Criteria cri) {
        return productMapper.getTotal(prodWriter, cri);
    }


    @Override
    public ProductVO getDetail(String prodId) {
        return productMapper.getDetail(prodId);
    }

    @Override
    public void productUpdate(ProductVO vo) {
        productMapper.productUpdate(vo);
    }

    @Override
    public int productDelete(ProductVO vo) {
        return productMapper.productDelete(vo);
    }




}
