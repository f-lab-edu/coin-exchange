package com.coin;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  private CustomErrorCode customErrorCode;
//  private String message;
  
  
  public CustomException(CustomErrorCode customErrorCode) {
//    super(customErrorCode.getStatus());
    this.customErrorCode = customErrorCode;
  }
}
