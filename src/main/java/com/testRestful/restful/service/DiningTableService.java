package com.testRestful.restful.service;

import com.testRestful.restful.entity.DiningTable;
import com.testRestful.restful.repository.DiningTableRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DiningTableService {

    private final DiningTableRepository diningTableRepository;

    public DiningTableService(DiningTableRepository diningTableRepository) {
        this.diningTableRepository = diningTableRepository;
    }

    public List<DiningTable> getAllDiningTables() {
        return diningTableRepository.findAll();
    }

    public DiningTable getTableById(Long tableId) {
                Optional<DiningTable> optionalOrder = diningTableRepository.findById(tableId);
        return optionalOrder.get();
    }

    public Integer getAllTable() {
        return diningTableRepository.getAllTable();
    }
}
