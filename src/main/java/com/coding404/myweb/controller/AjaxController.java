package com.coding404.myweb.controller;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AjaxController {

    @Autowired
    @Qualifier("productService")
    private ProductService productService;


    @GetMapping("/getCategory")
    public ResponseEntity<List<CategoryVO>> getCategory() {
        List<CategoryVO> vo = productService.getCategory();
        return new ResponseEntity<>(vo, HttpStatus.OK); // 가져온 실제 데이터를 반환
    }

    @GetMapping("/getCategorySub/{groupId}/{categoryLv}/{categoryDetailLv}")
    public ResponseEntity<List<CategoryVO>> getCategorySub(@PathVariable("groupId") String groupId,
                                                           @PathVariable("categoryLv") Integer categoryLv,
                                                           @PathVariable("categoryDetailLv") Integer categoryDetailLv) {
        CategoryVO vo = CategoryVO.builder()
                .groupId(groupId)
                .categoryLv(categoryLv)
                .categoryDetailLv(categoryDetailLv)
                .build();
        return new ResponseEntity<>(productService.getCategorySub(vo), HttpStatus.OK);
    }

}
