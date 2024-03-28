package com.testRestful.restful.controller;

import com.testRestful.restful.entity.DiningTable;
import com.testRestful.restful.service.DiningTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
public class DiningTableController {

    private final DiningTableService diningTableService;

    @Autowired
    public DiningTableController(DiningTableService diningTableService) {
        this.diningTableService = diningTableService;
    }

    @GetMapping("/tables")
    public ResponseEntity<List<DiningTable>> getAllDiningTables() {
        List<DiningTable> diningTables = diningTableService.getAllDiningTables();
        return ResponseEntity.ok(diningTables);
    }

    @GetMapping("/tables/{id}")
    public ResponseEntity<DiningTable> getTableByid(@PathVariable Long id) {
        return ResponseEntity.ok(diningTableService.getTableById(id));
    }

    
}
