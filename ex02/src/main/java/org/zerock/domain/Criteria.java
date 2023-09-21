package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {
	// 페이지처리 하기위한 정보
	// 페이지 번호(pageNum),
	// 한페이지에 보여줄 개수(amount)
	// 검색 선택(type) - C(내용)T(타이틀)W(작성어)
	// 검색어(keyword)
	
	private int pageNum;
	private int amount;
	private String type;
	private String keyword;
	
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public Criteria(){
		this(1,10);
	}
	
	// type글자를 한 글자씩 자르는 메소드
	public String[] getTypeArr()	{	// typeArr #{typeArr}쓰면 이 getter가 호출
		return type!=null ? type.split("") : new String[] {};
	}
	
	
}







