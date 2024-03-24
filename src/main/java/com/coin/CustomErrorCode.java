package com.coin;

import org.springframework.http.HttpStatus;
import lombok.*;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {
  INSUFFICIENT_BALANCE(423, "잔액이 부족합니다");
//402
  private final int status;
  private final String msg;

  public HttpStatus getStatus() {
    return HttpStatus.valueOf(status);
  }
}
