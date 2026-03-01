package com.example.kafka_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kafka_monitor.model.KafkaHorarioEntity;

public interface KafkaHorarioRepo extends JpaRepository<KafkaHorarioEntity, Long> {
}