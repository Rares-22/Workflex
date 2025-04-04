package com.example.demo.service;

import com.example.demo.config.WorkationDataLoader;
import com.example.demo.entity.Workation;
import com.example.demo.repository.WorkationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class WorkationDataLoaderTest {

    @Autowired
    private WorkationDataLoader workationDataLoader;

    @Autowired
    private WorkationRepository workationRepository;

    @BeforeEach
    void setUp() {
        // Clean up the database before each test (optional, if needed)
        workationRepository.deleteAll();
    }

    @Test
    void testWorkationDataLoaded() throws Exception {
        workationDataLoader.run();

        List<Workation> workations = workationRepository.findAll();
        assertNotNull(workations);
        assertFalse(workations.isEmpty(), "Workation data should not be empty");

        Workation firstWorkation = workations.get(0);
        assertEquals("Steffen Jacobs", firstWorkation.getEmployee());
        assertEquals("Germany", firstWorkation.getOrigin());
        assertEquals("United States", firstWorkation.getDestination());
    }
}
