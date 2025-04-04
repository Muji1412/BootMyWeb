package com.coding404.myweb.util;

import lombok.Data;

import java.util.List;
import java.util.ArrayList;  // 이 import가 필요해요!

@Data
public class PageVO {

    // 페이지네이션을 계산하기 위한 클래스야

    // 화면에 표시할 페이지 번호 정보들
    private int start;      // 화면에 보여줄 시작 페이지 번호 (예: 1, 11, 21)
    private int end;        // 화면에 보여줄 끝 페이지 번호 (예: 10, 20, 30)
    private boolean prev;   // '이전' 버튼 필요한지? (true면 보여줌)
    private boolean next;   // '다음' 버튼 필요한지? (true면 보여줌)

    // 페이지와 데이터 관련 정보들
    private int page;       // 사용자가 지금 보고 있는 현재 페이지 번호
    private int amount;     // 한 페이지에 보여줄 게시글 개수 (예: 10개씩)
    private int total;      // 전체 게시글 수 (DB에 저장된 총 게시글 수)
    private int realEnd;   // 실제 마지막 페이지 번호 (총 게시글 수로 계산)

    private Criteria cri;   // 페이지 정보가 담긴 객체 (page와 amount 정보가 들어있음)
    private List<Integer> pageList;  // 화면에 표시할 페이지 번호들을 모아둔 리스트

    private int pageCount = 5; //화면에 그려지는 페이지네이션 개수

    // 페이지 계산하는 생성자 (페이지 정보와 전체 게시글 수를 받아서 계산함)
    public PageVO(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;
        this.page = cri.getPage();    // 현재 페이지 번호 가져오기
        this.amount = cri.getAmount(); // 한 페이지당 게시글 수 가져오기

        // 1단계: 화면에 보여줄 마지막 페이지 번호 계산하기
        // 현재 페이지가 1~10이면 end=10, 11~20이면 end=20이 되도록 함
        // 예: 현재 페이지가 3이면 → 3/10.0 = 0.3 → 올림하면 1 → 1*10 = 10
        // 예: 현재 페이지가 15면 → 15/10.0 = 1.5 → 올림하면 2 → 2*10 = 20
        this.end = (int) (Math.ceil(this.page / (double)this.pageCount)) * pageCount;

        // 2단계: 화면에 보여줄 시작 페이지 번호 계산하기
        // 한 화면에 페이지 번호를 10개씩 보여주려고 함
        // 예: end가 10이면 → start는 1 (1~10 보여줌)
        // 예: end가 20이면 → start는 11 (11~20 보여줌)
        this.start = this.end - this.pageCount + 1;

        // 3단계: 실제 데이터에 따른 진짜 마지막 페이지 계산하기
        // 예: 게시글이 53개고 한 페이지당 10개씩 보여주면 → 53/10 = 5.3 → 올림하면 6
        // 즉, 마지막 페이지는 6페이지가 됨
        this.realEnd = (int)Math.ceil(this.total/(double)this.amount);

        // 4단계: 화면에 보여줄 마지막 페이지 번호 조정하기
        // 만약 계산한 화면 마지막 번호가 실제 마지막 페이지보다 크면 실제 값으로 수정
        // 예: 게시글이 53개라서 실제 마지막은 6페이지인데,
        //    화면에 1~10을 보여주는 상황이면 end=10이지만 readlEnd=6이므로
        //    end를 6으로 조정해서 1,2,3,4,5,6만 표시하게 함
        if (this.end > this.realEnd) {
            this.end = this.realEnd;
        }

        // 5단계: 이전 버튼 필요한지 결정하기
        // 시작 페이지가 1보다 크면 이전 페이지들이 있다는 뜻이니까 버튼 필요함
        // 예: start가 1이면 이전 버튼 필요 없음 (false)
        // 예: start가 11이면 이전 버튼 필요함 (true)
        this.prev = start > 1;

        // 6단계: 다음 버튼 필요한지 결정하기
        // 실제 마지막 페이지가 화면에 보이는 마지막 페이지보다 크면 다음 버튼 필요함
        // 예: 실제 마지막이 17인데 화면에는 10까지만 보이면 다음 버튼 필요함 (true)
        // 예: 실제 마지막이 17인데 화면에는 11~17이 보이면 다음 버튼 필요 없음 (false)
        this.next = this.realEnd > this.end;

        // 7단계: 화면에 표시할 페이지 번호들을 리스트에 저장하기
        // 1) pageList를 먼저 초기화해야 함 (그렇지 않으면 NullPointerException 발생)
        // 2) end를 포함해야 함 (i <= end로 변경)
        this.pageList = new ArrayList<>();  // 리스트 초기화 먼저 필요!
        for (int i = start; i <= end; i++) {  // <= 로 바꿔서 end 포함시켜야 함
            pageList.add(i);
        }
    }
}
