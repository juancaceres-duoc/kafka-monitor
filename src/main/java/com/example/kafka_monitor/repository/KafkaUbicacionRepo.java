package com.example.kafka_monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.kafka_monitor.model.KafkaUbicacionEntity;

public interface KafkaUbicacionRepo extends JpaRepository<KafkaUbicacionEntity, Long> {
}