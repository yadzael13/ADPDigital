package com.example.Cobertura.Logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.var;
import org.springframework.stereotype.Service;

import com.example.Cobertura.Entities.InputCobertura;
import com.example.Cobertura.Entities.RegionBody;
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
    public List<InputCobertura> GetAll(){
        try {
             List<InputCobertura> listCobert = new ArrayList<>();
            var cobertura = getAllRegions();
            for(var cob : cobertura){
                InputCobertura inpCob = new InputCobertura();
               String state = cob.getId();
               Map<String,Object> region = cob.getData();
                
               inpCob.setState(state);
               inpCob.setRegion((String) region.get("region"));

               listCobert.add(inpCob);

            }
            return listCobert;
            
        } catch (Exception e) {
            System.out.println("Error ---- "+e.toString());
            return null;
        }
        
    }
}
