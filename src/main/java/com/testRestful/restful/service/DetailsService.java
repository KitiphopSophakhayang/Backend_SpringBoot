package com.testRestful.restful.service;

import com.testRestful.restful.entity.Details;
import com.testRestful.restful.repository.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailsService {

    @Autowired
    private DetailsRepository repository;

    public List<Details> getAllDetails() {
        return repository.findAll();
    }

    public Optional<Details> getDetailsById(Long id) {
        return repository.findById(id);
    }

    public Details createDetails(Details details) {
        return repository.save(details);
    }

    public Details updateDetails(Long id, Details updatedDetails) {
        Optional<Details> existingDetailsOptional = repository.findById(id);
        if (existingDetailsOptional.isPresent()) {
            Details existingDetails = existingDetailsOptional.get();
            // ทำการอัพเดทข้อมูลที่ต้องการ
            existingDetails.setDetailname(updatedDetails.getDetailname());
            return repository.save(existingDetails);
        } else {
            // ในกรณีที่ไม่พบข้อมูล
            return null;
        }
    }

    public String deleteDetails(Long id) {
        repository.deleteById(id);
        return "Deleted details with id: " + id;
    }
}
