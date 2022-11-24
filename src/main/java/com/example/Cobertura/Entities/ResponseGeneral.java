package com.example.Cobertura.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResponseGeneral {

    @JsonProperty("Code")
    public String code;

    @JsonProperty("Result")
    public String result;

    @JsonProperty("ResultDescription")
    public String resultDescription;

}