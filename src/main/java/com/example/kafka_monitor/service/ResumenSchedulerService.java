package com.example.kafka_monitor.service;

import java.io.IOException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import java.nio.charset.StandardCharsets;

@Service
public class ResumenSchedulerService {
    private final JdbcTemplate jdbc;    

    @Value("classpath:sql/resumen.sql")
    private Resource resumenSql;

    public ResumenSchedulerService(JdbcTemplate jdbc,
                                   NamedParameterJdbcTemplate namedJdbc) {
        this.jdbc = jdbc;        
    }

    @Scheduled(cron = "0 */5 * * * *")
public void generarResumen() throws IOException {
  try {
    jdbc.execute("TRUNCATE TABLE KAFKA_RESUMEN_VEHICULO");

    String sql = new String(resumenSql.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    int inserted = jdbc.update(sql);

    System.out.println("Resumen generado. inserted=" + inserted);
  } catch (Exception e) {
    e.printStackTrace();
  }
}
}