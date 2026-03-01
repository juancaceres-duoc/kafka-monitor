package com.example.kafka_monitor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UbicacionVehiculoDTO {
    private String codigoVehiculo;
    private String ruta;
    private double lat;
    private double lon;
    private String timestamp;
}