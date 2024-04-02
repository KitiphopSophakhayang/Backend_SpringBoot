package com.testRestful.restful.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Top5MenuList {
    private String menuName;
    private Integer totalOrderedQuantity;
}
