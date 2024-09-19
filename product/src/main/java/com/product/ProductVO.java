package com.product;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductVO {
    private int idx; // idx(pk)
    private String SiteName; // 현장명
    private String Type; // 타입
    private String ProductCodeList; // 제품코드목록
    private String ITEM; // ITEM
    private String CutSize; // 재단 사이즈
    private String Quantity; // 수량
    private String WindowSET; // 창SET
    private String SingleMiddle; // 단중
    private String Weight; // 중량
    private String WindowNumber; // 창호넘버
    private MultipartFile Image; // 이미지
    private String StrImage; // 이미지

    // 여기부터 페이징 (검색)
    private  String  searchCondition;
    private  String  searchKeyword;
    private  String  searchKeywordR;

    private  int  tc ; // 총 방명록 수(전체 레코드)
    private  int  tp ; // 총 페이지 수

    private int ps; // page start
    private int pe; // page end

    private int nowPage; // 현재 페이지
    private int qs; // qurry start
    private int qe; // qurry end

    private int pageSize=10; // 페이지 크기


    private  int  start ;
    private  int  end ;

}
