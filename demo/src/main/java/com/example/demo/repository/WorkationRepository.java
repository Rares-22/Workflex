package com.example.demo.repository;

import com.example.demo.entity.Workation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkationRepository extends JpaRepository<Workation, Long> {
}
