package com.example.demo.controller;

import com.example.demo.entity.Workation;
import com.example.demo.repository.WorkationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workflex")
public class WorkationController {

    @Autowired
    private WorkationRepository workationRepository;

    @GetMapping("/workation")
    public List<Workation> getWorkations() {
        return workationRepository.findAll();
    }
}

