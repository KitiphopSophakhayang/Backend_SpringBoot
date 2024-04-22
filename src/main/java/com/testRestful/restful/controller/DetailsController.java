package com.testRestful.restful.controller;

import com.testRestful.restful.entity.Details;
import com.testRestful.restful.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/details")
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @GetMapping
    public List<Details> getAllDetails() {
        return detailsService.getAllDetails();
    }

    @GetMapping("/{id}")
    public Details getDetailsById(@PathVariable Long id) {
        return detailsService.getDetailsById(id).orElse(null);
    }

    @PostMapping
    public Details createDetails(@RequestBody Details details) {
        return detailsService.createDetails(details);
    }

    @PutMapping("/{id}")
    public Details updateDetails(@PathVariable Long id, @RequestBody Details details) {
        return detailsService.updateDetails(id, details);
    }

    @DeleteMapping("/{id}")
    public String deleteDetails(@PathVariable Long id) {
        return detailsService.deleteDetails(id);
    }
}
