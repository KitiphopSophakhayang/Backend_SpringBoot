package com.testRestful.restful.service;

import com.testRestful.restful.entity.DiningTable;
import com.testRestful.restful.entity.Order;
import com.testRestful.restful.entity.OrderItem;
import com.testRestful.restful.repository.DiningTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DiningTableService {

    private final DiningTableRepository diningTableRepository;

    @Autowired
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

    // สามารถเพิ่มเมธอดเพื่อดึงข้อมูลโต๊ะตามความต้องการเพิ่มเติมได้

}
