package com.example.recursividad.Controlador;

import com.example.recursividad.Vista.IU_Portada;
import javafx.stage.Stage;

public class ControladorCalculoInter {
    private final Stage primaryStage;

    public ControladorCalculoInter(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Calcula el monto final usando recursión.
     */
    public double calcularInteres(double P, double r, int n, int t) {
        if (t == 0) return P;
        return calcularInteres(P * (1 + r / n), r, n, t - 1);
    }

    /**
     * Vuelve a la pantalla de Portada.
     */
    public void volverPortada() {
        try {
            IU_Portada portada = new IU_Portada();
            Stage newStage = new Stage();
            portada.start(newStage);
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}