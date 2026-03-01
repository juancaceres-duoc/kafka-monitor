package com.example.kafka_monitor.service;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.example.kafka_monitor.dto.HorarioUpdateDTO;
import com.example.kafka_monitor.dto.UbicacionVehiculoDTO;
import com.example.kafka_monitor.model.KafkaHorarioEntity;
import com.example.kafka_monitor.model.KafkaUbicacionEntity;
import com.example.kafka_monitor.repository.KafkaHorarioRepo;
import com.example.kafka_monitor.repository.KafkaUbicacionRepo;

@Service
public class KafkaMonitorListenerService {

    private static final Logger log = LoggerFactory.getLogger(KafkaMonitorListenerService.class);

    private final KafkaUbicacionRepo ubicacionRepo;
    private final KafkaHorarioRepo horarioRepo;

    public KafkaMonitorListenerService(KafkaUbicacionRepo ubicacionRepo, KafkaHorarioRepo horarioRepo) {
        this.ubicacionRepo = ubicacionRepo;
        this.horarioRepo = horarioRepo;
    }

    @KafkaListener(
        topics = "${app.kafka.topic-ubicaciones:ubicaciones}",
        containerFactory = "ubicacionesKafkaListenerContainerFactory"
    )
    public void onUbicacion(UbicacionVehiculoDTO u, Acknowledgment ack) {
        try {
            KafkaUbicacionEntity e = KafkaUbicacionEntity.builder()
                    .codigoUnidad(u.getCodigoVehiculo())
                    .codigoRuta(u.getRuta())
                    .latitud(u.getLat())
                    .longitud(u.getLon())
                    .fechaEvento(Instant.parse(u.getTimestamp()))
                    .build();

            ubicacionRepo.save(e);

            ack.acknowledge();

            log.info("DB ubicacion OK | unidad={} ruta={} fechaEvento={}",
                    u.getCodigoVehiculo(), u.getRuta(), u.getTimestamp());

        } catch (Exception ex) {
            log.error("DB ubicacion FAIL | unidad={} ruta={} fechaEvento={} | {}",
                    safe(u != null ? u.getCodigoVehiculo() : null),
                    safe(u != null ? u.getRuta() : null),
                    safe(u != null ? u.getTimestamp() : null),
                    ex.getMessage(), ex);
                    ack.acknowledge();
        }
    }

    @KafkaListener(
        topics = "${app.kafka.topic-horarios:horarios}",
        containerFactory = "horariosKafkaListenerContainerFactory"
    )
    public void onHorario(HorarioUpdateDTO h, Acknowledgment ack) {
        try {
            KafkaHorarioEntity e = KafkaHorarioEntity.builder()
                    .codigoUnidad(h.getCodigoVehiculo())
                    .codigoRuta(h.getRuta())
                    .retrasoMin((double) h.getDelayMin())
                    .estado(h.getEstado())
                    .fechaEvento(Instant.parse(h.getTimestamp()))
                    .build();

            horarioRepo.save(e);

            ack.acknowledge();

            log.info("DB horario OK | unidad={} ruta={} retrasoMin={} estado={} fechaEvento={}",
                    h.getCodigoVehiculo(), h.getRuta(), h.getDelayMin(), h.getEstado(), h.getTimestamp());

        } catch (Exception ex) {
            log.error("DB horario FAIL | unidad={} ruta={} retrasoMin={} estado={} fechaEvento={} | {}",
                    safe(h != null ? h.getCodigoVehiculo() : null),
                    safe(h != null ? h.getRuta() : null),
                    h != null ? h.getDelayMin() : null,
                    safe(h != null ? h.getEstado() : null),
                    safe(h != null ? h.getTimestamp() : null),
                    ex.getMessage(), ex);
                    ack.acknowledge();
        }
    }

    private static String safe(String v) {
        return v == null ? "null" : v;
    }
}