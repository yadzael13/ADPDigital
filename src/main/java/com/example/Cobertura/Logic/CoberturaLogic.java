package com.example.Cobertura.Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.var;
import org.springframework.stereotype.Service;

import com.example.Cobertura.Entities.*;
import com.example.Cobertura.Firebase.FirebaseMethods;



@Service
public class CoberturaLogic implements FirebaseMethods{
    

    /**
     * @param cve_id
     * @return
     */
    public BodyCobertura GetOne(String cve_id){
        BodyCobertura bdret = new BodyCobertura();
        try {
            var doc = getDoc(cve_id).get().get();
            String cve = doc.getId();
            
            Map<String, Object> data_doc = doc.getData();

            if(data_doc == null){
                return null;
            }
            String name = (String) data_doc.get("nombre_distrito");
            String sec = (String) data_doc.get("sec_distrito");
            bdret.setCve_distrito(cve);
            bdret.setNombre_distrito(name);
            bdret.setSec_distrito(sec);
            return bdret;
        } catch (Exception e) {
            Loggers.ErrorLog("Logic - GetOne", e.toString());
            return bdret;
        }
    }

   
    /**
     * @return
     */
    public List<BodyCobertura> GetAll(){
        try {
             List<BodyCobertura> listCobert = new ArrayList<>();
            var cobertura = getAllRegions();
            for(var cob : cobertura){
                BodyCobertura inpCob = new BodyCobertura();
               String cve_distrito = cob.getId();
               Map<String,Object> BodyData = cob.getData();
                
               String nombre_distrito = (String) BodyData.get("nombre_distrito");
               String sec_distrito = (String) BodyData.get("sec_distrito");

               inpCob.setCve_distrito(cve_distrito);
               inpCob.setNombre_distrito(nombre_distrito);
               inpCob.setSec_distrito(sec_distrito);

               listCobert.add(inpCob);

            }
            return listCobert;
            
        } catch (Exception e) {
            Loggers.ErrorLog("Get All - Logic", e.toString());
            return null;
        }
        
    }

  
    /**
     * @param input
     * @return
     */
    public Boolean Insertar_coberturas(List<BodyCobertura> input){
        Boolean ret;
        try {
            for(BodyCobertura bd : input){
                String cve_distrito = bd.getCve_distrito();
                String sec_distrito = bd.getSec_distrito();
                String nombre_distrito = bd.getNombre_distrito();
                HashMap<String, String> aux = new HashMap<>();
                aux.put("sec_distrito", sec_distrito);
                aux.put("nombre_distrito", nombre_distrito);
                // Insertar en Firebase
                Boolean fb_valid = FirebaseMethods.InsertarCoberturas(cve_distrito,aux);
                if(!fb_valid){
                    return false;
                }
            }
            ret = true;
            return ret;
            
        } catch (Exception e) {
            Loggers.ErrorLog("Insertar Coberturas - Logic", e.toString());
            ret = false;
            return false;
        }
        
    }
}
