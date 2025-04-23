package com.example.recursividad.Controlador;

import com.example.recursividad.Vista.IU_IntroRecurs;
import com.example.recursividad.Vista.IU_CalculoInter;
import com.example.recursividad.Vista.IU_CalcPoten;
import com.example.recursividad.Vista.IU_TorreHan;
import com.example.recursividad.Vista.IU_SubCon;
import javafx.stage.Stage;

public class ControladorPrincipal {
    private final Stage primaryStage;

    public ControladorPrincipal(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void abrirIntroduccion() {
        try {
            IU_IntroRecurs introduccion = new IU_IntroRecurs();
            Stage newStage = new Stage();
            introduccion.start(newStage);
            // Cerramos la ventana actual
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirInteresCompuesto() {
        try {
            IU_CalculoInter ui = new IU_CalculoInter();
            Stage newStage = new Stage();
            ui.start(newStage);
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirPotencia() {
        try {
            IU_CalcPoten vista = new IU_CalcPoten();
            Stage newStage = new Stage();
            vista.start(newStage);
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirTorresDeHanoi() {
        try {
            IU_TorreHan vista = new IU_TorreHan();
            Stage newStage = new Stage();
            vista.start(newStage);
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirSubconjuntos() {
        try {
            IU_SubCon vista = new IU_SubCon();
            Stage newStage = new Stage();
            vista.start(newStage);
            primaryStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
