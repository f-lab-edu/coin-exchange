package com.coin.controller;

import java.math.BigDecimal;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coin.CustomException;
import com.coin.dao.AccountDao;
import com.coin.dto.OrderDTO;
import com.coin.service.OrderManagementService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

  private OrderManagementService orderService;

  public OrderController(OrderManagementService orderService) {
    this.orderService = orderService;
  }
  
  @PostMapping
  public HttpEntity<String> addOrder(HttpServletRequest request) {
    
    ResponseEntity<String> responseEntity;
    int orderResult = 0;
    try {
      orderResult = orderService.addOrder(orderBuilder(request));
    } catch (CustomException e) {
      responseEntity =
          new ResponseEntity<>(e.getCustomErrorCode().getMsg(), e.getCustomErrorCode().getStatus());
      return responseEntity;
    }
    switch (orderResult) {
      case 1:
        responseEntity = new ResponseEntity<>("주문을 입력하였습니다", HttpStatus.OK);
        break;
      case 2:
        responseEntity = new ResponseEntity<>("거래가 정상적으로 종료되었습니다", HttpStatus.OK);
        break;
      default:
        responseEntity = new ResponseEntity<>(String.valueOf(orderResult), HttpStatus.OK);
    }
    return responseEntity;
  }

  // 거래 상세정보
  @GetMapping("/{id}")
  public void retrieveOrder(@PathVariable int userNumber) {}

  // 거래 내용 변경
  @PutMapping("/{id}")
  public void updateOrder() {}

  // 거래정보 삭제
  @DeleteMapping("/{id}")
  public void deleteOrder() {}

  public OrderDTO orderBuilder(HttpServletRequest request) {
    int userNumber = 0;
    String coinCode = "";
    // 01 매수 02 매도
    String buySellCode = "";
    int tranQuantity = 0;
    int tranAmount = 0;

    // 필수로 넣을 값들은 이런식으로 방어가 굳이 필요하지 않을까?
    if (request.getParameter("userNumber") != null) {
      userNumber = Integer.parseInt(request.getParameter("userNumber"));
    }
    if (request.getParameter("coinCode") != null) {
      coinCode = request.getParameter("coinCode");
    }
    if (request.getParameter("buySellCode") != null) {
      buySellCode = request.getParameter("buySellCode");
    }
    if (request.getParameter("tranQuantity") != null) {
      tranQuantity = Integer.parseInt(request.getParameter("tranQuantity"));
    }
    if (request.getParameter("tranAmount") != null) {
      tranAmount = Integer.parseInt(request.getParameter("tranAmount"));
    }

//    userNumber = 231;
//    coinCode = "101";
//    buySellCode = "01";
//    tranQuantity = 6;
//    tranAmount = 100;

    return OrderDTO.builder().userNumber(userNumber).coinCode(coinCode).buySellCode(buySellCode)
        .tranQuantity(tranQuantity).tranAmount(BigDecimal.valueOf(tranAmount)).build();
  }
}
