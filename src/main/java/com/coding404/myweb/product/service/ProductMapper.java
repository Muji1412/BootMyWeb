package com.coding404.myweb.product.service;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.controller.ProductVO;
import com.coding404.myweb.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    int productRegist(ProductVO vo);
    List<ProductVO> getList(@Param("prodWriter") String prodWriter,
                            @Param("cri")Criteria cri);
    ProductVO getDetail(String prodId);
    void productUpdate(ProductVO vo);
    int productDelete(ProductVO vo);
    int getTotal(@Param("prodWriter") String prodWriter,
                 @Param("cri")Criteria cri);
    List<CategoryVO> getCategory();
    List<CategoryVO> getCategorySub(CategoryVO vo); // 2,3단 셀렉트`
}
