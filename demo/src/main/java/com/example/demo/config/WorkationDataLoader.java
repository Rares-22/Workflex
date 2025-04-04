package com.example.demo.config;

import com.example.demo.entity.Workation;
import com.example.demo.repository.WorkationRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class WorkationDataLoader implements CommandLineRunner {

    @Autowired
    private WorkationRepository workationRepository;

    @Override
    public void run(String... args) throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("workations.csv");
        if (inputStream == null) {
            System.out.println("CSV file not found in the classpath!");
            return;
        }

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] header = csvReader.readNext(); // Skip header if present

            String[] record;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            while ((record = csvReader.readNext()) != null) {
                Workation workation = new Workation();
                workation.setEmployee(record[1]);
                workation.setOrigin(record[2]);
                workation.setDestination(record[3]);

                Date startDate = dateFormat.parse(record[4]);
                Date endDate = dateFormat.parse(record[5]);
                workation.setStartDate(startDate);
                workation.setEndDate(endDate);

                workation.setWorkingDays(Integer.parseInt(record[6]));
                workation.setRiskLevel(record[7]);

                workationRepository.save(workation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
