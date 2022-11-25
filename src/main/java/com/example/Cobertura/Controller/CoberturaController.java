package com.example.Cobertura.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

import java.util.List;



//Entities y logica
import com.example.Cobertura.Logic.*;

import com.example.Cobertura.Entities.*;

// Indicamos que nuestra clase va ser tipo controlador
@RestController
@RequestMapping("/v1")
public class CoberturaController {
    @Autowired
    CoberturaLogic coberturaLogic;

      /** Inserta una lista de estados en Firebase
     * @param input - Json tipo BodyCobertura
     * @return - ResponseEntity - status - body tipo ResponseData
     */
    @PostMapping(value = "/Insert_States")
    public ResponseEntity<?> InsertarCoberturas(@RequestBody List<BodyCobertura> input_list){
    ResponseGeneral resp = new ResponseGeneral();
    boolean valid;
    valid = coberturaLogic.Insertar_coberturas(input_list);
    if(valid){
        resp.setCode("200");
        resp.setResult("Cobertura añadida correctamente.");
        resp.setResultDescription("Se han insertado las coberturas");
        return ResponseEntity.status(200).body(resp);
    }else{
        resp.setCode("400");
        resp.setResult("Ha ocurrido un error");
        resp.setResultDescription("No se ha insertado las coberturas");
        return ResponseEntity.status(400).body(resp);
    }

    }


    /**Retorna un elemento consultado por su clave
     * @return - ResponseEntity - status - body tipo ResponseGeneral
     */
    @GetMapping(value = "/Get_one")
    public ResponseEntity<?> GetCve(@RequestParam String cve_id){
        try {
            BodyCobertura logic_ret = coberturaLogic.GetOne(cve_id);
            if(logic_ret.equals(null)){
                Exception e = new IOException();
                throw e;
            }
            ResponseData resp = new ResponseData();
            resp.setCode("200");
            resp.setResult("Succesfully");
            resp.setResultDescription("Servicio consumido correctamente");
            resp.setStates(logic_ret);
            return ResponseEntity.status(200).body(resp);
            
        } catch (Exception e) {
           Loggers.ErrorLog("Get_cve - Controller", e.toString());

           ResponseGeneral resp = new ResponseGeneral();
           resp.setCode("400");
           resp.setResult("Error");
           resp.setResultDescription("Error al consumir el servicio, ó clave no existente en FB");
           return ResponseEntity.status(400).body(resp);
        }
    }


    /**Retorna una Lista con los datos de todas las regiones de cobertura
     * @return - ResponseEntity - status - body tipo ResponseGeneral
     */
    @GetMapping(value = "/Get_All_States")
    public ResponseEntity<?> GetAll(){
        try {
            ResponseData resp = new ResponseData();
            List<BodyCobertura> cobertura = coberturaLogic.GetAll();
            if(cobertura.equals(null)){
                Exception e = new Exception();
                throw e;
            }
            resp.setCode("200");
            resp.setResult("Servicio consumido correctamente.");
            resp.setResultDescription("Se han consultado los datos correctamente.");
            resp.setStates(cobertura);
            return ResponseEntity.status(200).body(resp);
        } catch (Exception e) {
            Loggers.ErrorLog("Getl_All_States - Controller", e.toString());

            ResponseGeneral resp = new ResponseGeneral();
            resp.setCode("400");
            resp.setResult("Ha ocurrido un error");
            resp.setResultDescription("No se ha podido realizar la consulta");
            return ResponseEntity.status(400).body(resp);
        }
        
    }

 
}
