package entity.dto.dto.repository.service.controller.config;

public package com.example.orders;

import com.example.orders.dto.CreateOrderRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.orders.controller.OrderController;
import com.example.orders.service.OrderService;
import com.example.orders.dto.OrderResponse;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

  @Autowired MockMvc mvc;
  @Autowired ObjectMapper om;
  @MockBean OrderService service;

  @Test
  void create_returns201Body() throws Exception {
    CreateOrderRequest req = new CreateOrderRequest("ram@example.com","SKU-1",1,new BigDecimal("10.00"));
    OrderResponse resp = new OrderResponse(1L,"ram@example.com","SKU-1",1,new BigDecimal("10.00"),
        new BigDecimal("10.00"),"NEW", OffsetDateTime.now());

    when(service.create(any())).thenReturn(resp);

    mvc.perform(post("/api/orders")
        .contentType(MediaType.APPLICATION_JSON)
        .content(om.writeValueAsString(req)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id").value(1));
  }
}
 {
    
}
