package com.coding404.myweb.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVO {

    // 필드 선언 - DB 컬럼과 매핑
    private Integer prodId;                 // 상품 ID (PK, 자동 증가)
    private LocalDateTime prodRegdate;      // 등록일
    private String prodEnddate;         // 판매종료일
    private String prodCategory;        // 카테고리 키
    private String prodWriter;          // 작성자
    private String prodName;            // 상품명
    private Integer prodPrice;              // 가격
    private Integer prodCount;              // 재고
    private Integer prodDiscount;           // 할인율
    private String prodPurchaseYn;      // 구매여부(Y/N)
    private String prodContent;         // 내용
    private String prodComment;         // 메모

    // n : 1
    private String categoryNav;

}



