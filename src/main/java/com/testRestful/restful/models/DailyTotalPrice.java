package com.testRestful.restful.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyTotalPrice {
    private String dayOfWeek;
    private Double totalPrice;
}
