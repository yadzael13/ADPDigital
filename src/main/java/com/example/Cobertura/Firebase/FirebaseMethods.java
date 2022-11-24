package com.example.Cobertura.Firebase;

import java.util.HashMap;
import java.util.List;

import java.util.concurrent.ExecutionException;

import com.example.Cobertura.Entities.BodyCobertura;
import com.example.Cobertura.Entities.RegionBody;
import com.google.api.core.ApiFuture;

import com.google.cloud.firestore.DocumentReference;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;




public interface FirebaseMethods {
    FirebaseConection firebase = new FirebaseConection();

    //Nombre de la colección en Firebase
    String col_name = "cobertura";

        /**Obtiene una region individualmente (Se usa en la obtención de todas)
         * @param region
         * @return - Regresa una entidad Individaualmente -DocumentReference
         */
    default DocumentReference getRegion(String region){
        assert false;
        return firebase.getFirestore()
                                    .collection(col_name)
                                    .document(region);
    }

    /**Obtiene todas las entidades en Cobertura en Firebase
     * @return - Lista con Documentos -List<QueryDocumentSnapshot>
     * @throws InterruptedException
     * @throws ExecutionException
     */
    default List<QueryDocumentSnapshot> getAllRegions() throws InterruptedException, ExecutionException{
        assert false;
        ApiFuture<QuerySnapshot> future = firebase.getFirestore().collection(col_name).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        return documents;
    }

    /**Inserta una entidad en la tabla cobertura con su nombre, y respectivo cuerpo
     * @param country
     * @param region
     * @return - True si ha sido correcto / False si algo ha fallado
     */
    default Boolean InsertCobertura(String country, RegionBody region){
        Boolean valid = false;
        assert false;
       try {
            firebase.getFirestore()
                            .collection(col_name)
                            .document(country).set(region);
            valid = true;
            return valid;
       } catch (Exception e) {
        System.out.println(e.toString());
        return valid;
       }
    }

      /**Nueva Logica
     * @param country
     * @param region
     * @return - True si ha sido correcto / False si algo ha fallado
     */
    static Boolean InsertarCoberturas(String cvd_distrito, HashMap<String, String> input){
        Boolean valid = false;
        assert false;
       try {
            firebase.getFirestore()
                            .collection(col_name)
                            .document(cvd_distrito).set(input);
            valid = true;
            return valid;
       } catch (Exception e) {
        System.out.println(e.toString());
        return valid;
       }
    }

}
