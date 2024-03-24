package com.coin;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class ResultVO {
	private int calculateQuantity;		//계산 후의 수량값
	private int breakIndex;		//종료 시점의 인덱스 값
	
}
