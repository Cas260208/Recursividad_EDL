package com.example.recursividad.Vista;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class IU_SubCon extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Etiquetas con la información
        Label uniLabel = new Label("Universidad Autónoma Metropolitana, Unidad Cuajimalpa");
        Label deptoLabel = new Label("División: Departamento de Matemáticas Aplicadas y Sistemas");
        Label carreraLabel = new Label("Carrera: Ingeniería en Computación");
        Label ueaLabel = new Label("UEA: Estructura de Datos Lineales");
        Label profLabel = new Label("Profesor: Rogelio Ernesto García Chávez");
        Label equipoLabel = new Label("Integrantes del equipo:");
        Label miembro1 = new Label("• CASANDRA ZETINA RODRIGUEZ");
        Label miembro2 = new Label("• MARIA FERNANDA RICO ARELLANO");
        Label miembro3 = new Label("• ANGEL ARMANDO OLIVEROS GUTIERREZ");

        // Botones para los temas
        Button listasBtn = new Button("Listas Enlazadas");
        Button pilasBtn  = new Button("Pilas");
        Button colasBtn  = new Button("Colas");

        // Contenedor horizontal para los botones
        HBox botonesBox = new HBox(20, listasBtn, pilasBtn, colasBtn);
        botonesBox.setAlignment(Pos.CENTER);

        // Contenedor vertical principal
        VBox root = new VBox(10,
                uniLabel,
                deptoLabel,
                carreraLabel,
                ueaLabel,
                profLabel,
                equipoLabel,
                miembro1,
                miembro2,
                miembro3,
                botonesBox
        );
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        // Configurar y mostrar escena
        Scene scene = new Scene(root, 700, 400);
        primaryStage.setTitle("Recursividad");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Ejemplo de acción para un botón (puedes personalizar)
        listasBtn.setOnAction(e -> {
            System.out.println("Abrir tema de Listas Enlazadas...");
            // Aquí podrías lanzar otra ventana o cambiar de vista
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
