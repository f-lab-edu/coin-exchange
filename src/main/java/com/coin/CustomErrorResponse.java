package com.coin;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomErrorResponse {

  private CustomErrorCode customErrorCode;
  private String message;
}
