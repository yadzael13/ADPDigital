package com.example.Cobertura.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    /** Recibe un Json y lo inserta en tabla cobertura
     * @param input - Json con "state" y "region"
     * @return - ResponseEntity - status - body tipo ResponseGeneral
     */
    @PostMapping(value = "/InsertaCobertura")
    public ResponseEntity<?> InsertCobertura(@RequestBody InputCobertura input){
        ResponseGeneral resp = new ResponseGeneral();
        Boolean valid = false;

        valid = coberturaLogic.InsertCountry(input);
        if(valid){
            resp.setCode("200");
            resp.setResult("Cobertura añadida correctamente.");
            resp.setResultDescription(input.getState()+" insertado");
            return ResponseEntity.status(200).body(resp);
        }else{
            resp.setCode("400");
            resp.setResult("Ha ocurrido un error");
            resp.setResultDescription(input.getState()+" no se ha insertado");
            return ResponseEntity.status(400).body(resp);
        }

    }

    /**Retorna una Lista con los datos de todas las regiones de cobertura
     * @return - ResponseEntity - status - body tipo ResponseGeneral
     */
    @GetMapping(value = "/GetAll_Cobertura")
    public ResponseEntity<?> GetAll(){
        ResponseGeneral resp = new ResponseGeneral();

        try {
            List<InputCobertura> cobertura= coberturaLogic.GetAll();
            resp.setCode("200");
            resp.setResult("Servicio consumido correctamente.");
            resp.setResultDescription("Se han consultado los datos correctamente.");
            return ResponseEntity.status(200).body(cobertura);
        } catch (Exception e) {
            resp.setCode("400");
            resp.setResult("Ha ocurrido un error");
            resp.setResultDescription("No se ha podido realizar la consulta");
            System.out.println("Error --- "+e.toString());
            return ResponseEntity.status(400).body(resp);
        }
        
    }

    /**De una lista de Jsons con varios "state" y "region" crea todos en la lista
     * @param list_regions
     * @return
     */
    @PostMapping(value = "/InsertaVariasCoberturas")
    public ResponseEntity<?> InsertVariasCobertura(@RequestBody List<InputCobertura>list_regions){
        ResponseGeneral resp = new ResponseGeneral();

        try {
            for(InputCobertura el : list_regions){
                coberturaLogic.InsertCountry(el);
            }
            resp.setCode("200");
            resp.setResult("Coberturas añadidas");
            resp.setResultDescription("Todas han sido añadidas");
            return ResponseEntity.status(200).body(resp);

        } catch (Exception e) {
            System.out.println("Error -- "+e.toString());

            resp.setCode("400");
            resp.setResult("Ha ocurrido un error");
            resp.setResultDescription("No se han podido añadir las regiones");
            return ResponseEntity.status(400).body(resp);
        }
       
    }
}
