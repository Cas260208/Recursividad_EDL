package com.example.recursividad.Controlador;

import com.example.recursividad.Vista.IU_Portada;
import javafx.stage.Stage;

public class ControladorIntroRecurs {
    private final Stage primaryStage;

    public ControladorIntroRecurs(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void volverPortada() {
        try {
            IU_Portada portada = new IU_Portada();
            Stage newStage = new Stage();
            portada.start(newStage);
            // Cerramos la ventana de introducción
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
