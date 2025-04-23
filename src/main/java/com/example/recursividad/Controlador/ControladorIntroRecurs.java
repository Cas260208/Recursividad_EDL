package com.example.recursividad.Controlador;

import com.example.recursividad.Vista.IU_Portada;
import javafx.stage.Stage;

public class ControladorIntroRecurs {

    private final Stage stage;

    public ControladorIntroRecurs(Stage stage) {
        this.stage = stage;
    }

    public void volverPortada() {
        try {
            // Abre de nuevo la ventana de portada
            new IU_Portada().start(new Stage());
            // Cierra la ventana actual
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
