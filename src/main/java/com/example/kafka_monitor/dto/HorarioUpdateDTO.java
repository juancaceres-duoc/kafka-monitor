package com.example.kafka_monitor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioUpdateDTO {
    private String codigoVehiculo;
    private String ruta;
    private int delayMin;
    private String estado;
    private String timestamp;
}