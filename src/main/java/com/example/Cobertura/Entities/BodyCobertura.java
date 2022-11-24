package com.example.Cobertura.Entities;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BodyCobertura {
    @JsonProperty("sec_distrito")
    public String sec_distrito;

    @JsonProperty("nombre_distrito")
    public String nombre_distrito;

    @JsonProperty("cvd_distrito")
    public String cvd_distrito;
}
