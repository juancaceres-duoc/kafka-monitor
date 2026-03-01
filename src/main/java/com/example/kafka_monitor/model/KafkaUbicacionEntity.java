package com.example.kafka_monitor.model;

import java.time.Instant;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "KAFKA_UBICACION")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class KafkaUbicacionEntity {

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

    @Column(name = "LATITUD", nullable = false)
    private Double latitud;

    @Column(name = "LONGITUD", nullable = false)
    private Double longitud;

    @Column(name = "FECHA_INGESTA", insertable = false, updatable = false, nullable = false)
    private Instant fechaIngesta;
}