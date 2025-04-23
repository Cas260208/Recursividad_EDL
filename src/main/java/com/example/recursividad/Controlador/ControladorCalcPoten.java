package com.example.recursividad.Controlador;

import com.example.recursividad.Vista.IU_Portada;
import javafx.stage.Stage;

public class ControladorCalcPoten {
    private final Stage stage;
    public ControladorCalcPoten(Stage stage) {
        this.stage = stage;
    }
    public void volverPortada() {
        try {
            IU_Portada portada = new IU_Portada();
            Stage newStage = new Stage();
            portada.start(newStage);
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
