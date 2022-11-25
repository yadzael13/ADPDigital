package com.example.Cobertura.Firebase;

import java.util.HashMap;
import java.util.List;

import java.util.concurrent.ExecutionException;

import com.example.Cobertura.Entities.Loggers;
import com.google.api.core.ApiFuture;

import com.google.cloud.firestore.DocumentReference;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;





public interface FirebaseMethods {
    FirebaseConection firebase = new FirebaseConection();

    //Nombre de la colección en Firebase
    String col_name = "cobertura";

       
    /** Obtiene un Documento po cve_id
     * @param cve_id
     * @return DocumentReference del Documento
     */
    default DocumentReference getDoc(String cve_id){
        Loggers.InfoLog("Consulta Firebase", "Id consultado : -- "+cve_id);
        return firebase.getFirestore()
                                    .collection(col_name)
                                    .document(cve_id);
    }


    /**Obtiene todas las entidades en Cobertura en Firebase
     * @return - Lista con Documentos -List<QueryDocumentSnapshot>
     * @throws InterruptedException
     * @throws ExecutionException
     */
    default List<QueryDocumentSnapshot> getAllRegions() throws InterruptedException, ExecutionException{
        ApiFuture<QuerySnapshot> future = firebase.getFirestore().collection(col_name).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        Loggers.InfoLog("Firebase - Get All ", "Consulta de todos los datos --- ");
        return documents;
    }


  
    /** Inserta la cobertura con id de cvd_distrito y el cuerpo mandado
     * @param cvd_distrito
     * @param input
     * @return True o false, si se ha insertado o no
     */
    static Boolean InsertarCoberturas(String cve_distrito, HashMap<String, String> input){
        Boolean valid = false;
       try {
            firebase.getFirestore()
                            .collection(col_name)
                            .document(cve_distrito).set(input);
            valid = true;
            Loggers.InfoLog("Firebase Insersion", "Clave insertada -- "+cve_distrito);
            return valid;
       } catch (Exception e) {
        Loggers.ErrorLog("Insertar coberturas - Firebase Methods", e.toString());
        return valid;
       }
    }

}
