package com.example.kafka_monitor.model;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "KAFKA_HORARIO")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class KafkaHorarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FECHA_EVENTO", nullable = false)
    private Instant fechaEvento;

    @Column(name = "CODIGO_UNIDAD", nullable = false, length = 30)
    private String codigoUnidad;

    @Column(name = "CODIGO_RUTA", nullable = false, length = 20)
    private String codigoRuta;

    @Column(name = "RETRASO_MIN", nullable = false)
    private Double retrasoMin;

    @Column(name = "ESTADO", nullable = false, length = 20)
    private String estado;

    @Column(name = "FECHA_INGESTA", insertable = false, updatable = false, nullable = false)
    private Instant fechaIngesta;
}