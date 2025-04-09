package com.coding404.myweb.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoOrderVO {
    private int oid;
    private int mid;
    private String productName;

    //1ST - 1관계 테이블이 여러 컬럼이 필요한게 아니라면, 단순이 컬럼명은 N에 추가
    //private String name;
    //2nd - 객체 매핑
    private DemoMemberVO member;
}
