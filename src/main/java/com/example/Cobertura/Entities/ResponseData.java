package com.example.Cobertura.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData extends ResponseGeneral{
    @JsonProperty("States")
    public Object States;

}
