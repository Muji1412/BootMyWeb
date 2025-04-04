package com.coding404.myweb.util;

import lombok.Data;

@Data //게터세터 투스트링
public class Criteria {

    //사용자가 조회하는 페이지번호, 데이터개수
    private int page; //페이지 번호
    private int amount; //데이터 개수

    //검색키워드
    private String searchName;
    private String searchContent;
    private String startDate;     // 시작 날짜
    private String endDate;       // 종료 날짜
    private String searchPrice;   // 가격 정렬 방식


    public Criteria() {
        this(1, 10);
    }

    public Criteria(int page, int amount) {
        this.page = page;
        this.amount = amount;



    }

    //리미트함수 첫번쨰 값에 전달될 함수
    public int getPageStart() {
        return (page - 1) * amount;
    }
}
