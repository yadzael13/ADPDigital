package com.example.Cobertura.Firebase;

import com.example.Cobertura.Entities.Loggers;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;


@Service
public class FirebaseConection {

   @PostConstruct
    public void firestoreInit() throws IOException{
        String projectId = "proyecto-bg-v1";
        try{
            
            String reciclable_path = "C:/Users/hvargasc/Documents/Totalplay/ADPDigital/AdopcionDigital/src/main/java/com/example/Cobertura/Firebase";
            FileInputStream credentials = new FileInputStream(reciclable_path+"/key.json");
            System.out.println(credentials);
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(credentials))
                .setProjectId(projectId)
                .setDatabaseUrl("https://"+projectId+".firebaseio.com")
                .build();
            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
            Loggers.InfoLog("Firebase Connection", "Correctamente conectado a proyecto "+projectId);
        } catch(IOException e){
            Loggers.ErrorLog("Firebase Connection Exception", e.toString());
        }
            
    }
    /* 
    @PostConstruct
    private void firestoreInit() {
        String projectId = "fintech-delivery-dev";
        try {

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .setProjectId(projectId)
                    .setDatabaseUrl("https://fintech-delivery-dev.firebaseio.com")
                    .build();

            if(FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
            Loggers.InfoLog("Firebase Connection", "Correctamente conectado a proyecto "+projectId);

        } catch (Exception e) {
            Loggers.ErrorLog("Firebase Connection Exception", e.toString());
        }

    } */

    public Firestore getFirestore(){
        return FirestoreClient.getFirestore();
    }
    
}
