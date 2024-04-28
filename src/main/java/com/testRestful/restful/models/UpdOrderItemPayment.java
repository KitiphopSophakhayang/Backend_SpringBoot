package com.testRestful.restful.models;


import java.util.List;

import lombok.Data;

@Data
public class UpdOrderItemPayment {
    List<Long> orderItemIds;
    String status;
}
