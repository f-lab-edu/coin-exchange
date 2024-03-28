package com.coin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.coin.dto.OrderDTO;
import com.coin.response.ApiResponse;
import com.coin.service.OrderManagementService;
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
  public ApiResponse<String> addOrder(@ModelAttribute OrderDTO orderDto) {
    return orderService.addOrder(orderDto);
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
}
