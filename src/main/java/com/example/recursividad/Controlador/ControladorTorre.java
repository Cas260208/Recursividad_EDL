// src/main/java/com/example/recursividad/Controlador/ControladorTorre.java
package com.example.recursividad.Controlador;

import com.example.recursividad.Vista.IU_Portada;
import javafx.stage.Stage;

public class ControladorTorre {
    private final Stage stage;

    public ControladorTorre(Stage stage) {
        this.stage = stage;
    }

    public void volverPortada() {
        try {
            IU_Portada portada = new IU_Portada();
            Stage nueva = new Stage();
            portada.start(nueva);
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
