package com.example.Cobertura.Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.var;
import org.springframework.stereotype.Service;

import com.example.Cobertura.Entities.*;
import com.example.Cobertura.Firebase.FirebaseMethods;



@Service
public class CoberturaLogic implements FirebaseMethods{
    
    /**Inserta un State con Region individualmente
     * @param bodyCountry
     * @return - True si se ha creado, False si ha ocurrido un error
     */
    public Boolean InsertCountry(InputCobertura bodyCountry){
        Boolean valida = false;
        try {
            String state = bodyCountry.getState();     
            RegionBody region = new RegionBody(bodyCountry.getRegion());
            valida = InsertCobertura(state, region);
            return valida;
        } catch (Exception e) {
            System.out.println("Error ---- "+e.toString());
            return valida;
        }
    }

    /**Obtiene todas las entidades en Cobertura
     * @return -- List con todas los state
     */
    public List<BodyCobertura> GetAll(){
        try {
             List<BodyCobertura> listCobert = new ArrayList<>();
            var cobertura = getAllRegions();
            for(var cob : cobertura){
                BodyCobertura inpCob = new BodyCobertura();
               String cvd_distrito = cob.getId();
               Map<String,Object> BodyData = cob.getData();
                
               String nombre_distrito = (String) BodyData.get("nombre_distrito");
               String sec_distrito = (String) BodyData.get("sec_distrito");

               inpCob.setCvd_distrito(cvd_distrito);
               inpCob.setNombre_distrito(nombre_distrito);
               inpCob.setSec_distrito(sec_distrito);

               listCobert.add(inpCob);

            }
            return listCobert;
            
        } catch (Exception e) {
            System.out.println("Error ---- "+e.toString());
            return null;
        }
        
    }

       /**Nueva Lógica
     * @return -- 
     */
    public Boolean Insertar_coberturas(List<BodyCobertura> input){
        Boolean ret;
        try {
            for(BodyCobertura bd : input){
                String cvd_distrito = bd.getCvd_distrito();
                String sec_distrito = bd.getSec_distrito();
                String nombre_distrito = bd.getNombre_distrito();
                HashMap<String, String> aux = new HashMap<>();
                aux.put("sec_distrito", sec_distrito);
                aux.put("nombre_distrito", nombre_distrito);
                // Insertar en Firebase
                FirebaseMethods.InsertarCoberturas(cvd_distrito,aux);
            }
            ret = true;
            return ret;
            
        } catch (Exception e) {
            System.out.println("\n ---- ERROR ---- \n"+e.toString());
            ret = false;
            return false;
        }
        
    }
}
