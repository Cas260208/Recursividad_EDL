// src/main/java/com/example/recursividad/Controlador/ControladorPrincipal.java
package com.example.recursividad.Controlador;

import com.example.recursividad.Vista.IU_IntroRecurs;
import com.example.recursividad.Vista.IU_CalculoInter;
import com.example.recursividad.Vista.IU_CalcPoten;
import com.example.recursividad.Vista.IU_TorreHan;
import com.example.recursividad.Vista.IU_SubCon;
import javafx.stage.Stage;

public class ControladorPrincipal {
    private final Stage ownerStage;

    public ControladorPrincipal(Stage ownerStage) {
        this.ownerStage = ownerStage;
    }

    public void abrirIntroduccion() {
        try {
            // Cada IU_* extiende Application, así que llamamos start() manualmente en una nueva ventana
            new IU_IntroRecurs().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirInteresCompuesto() {
        try {
            new IU_CalculoInter().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirPotencia() {
        try {
            new IU_CalcPoten().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirTorresDeHanoi() {
        try {
            new IU_TorreHan().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirSubconjuntos() {
        try {
            new IU_SubCon().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
