package com.example.recursividad.Vista;

import com.example.recursividad.Controlador.ControladorPrincipal;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.List;

public class IU_Portada extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        // 0) Fuente de datos
        Font dataFont = Font.loadFont(
                getClass().getResourceAsStream("/com/example/Fuentes/Pixeled.ttf"),
                10
        );

        // 1) Controlador
        ControladorPrincipal controlador = new ControladorPrincipal(stage);

        // 2) Imagen de título
        Image titleImage = new Image(getClass().getResourceAsStream("/com/example/Imagenes/title.png"));
        ImageView titleView = new ImageView(titleImage);
        titleView.setPreserveRatio(true);
        titleView.setFitWidth(700);

        // 3) Botones principales
        Button introBtn    = makeIconButton("/com/example/Imagenes/recu.png",
                "Introducción a\nRecursividad", 140, 140);
        Button interesBtn  = makeIconButton("/com/example/Imagenes/ca.png",
                "Cálculo de Interés\nCompuesto", 140, 140);
        Button hanoiBtn    = makeIconButton("/com/example/Imagenes/th.png",
                "Torres de Hanoi\n(4 discos)", 140, 140);
        Button potenciaBtn = makeIconButton("/com/example/Imagenes/po.png",
                "Cálculo de\nPotencia", 140, 140);
        Button subconjBtn  = makeIconButton("/com/example/Imagenes/cons.png",
                "Subconjuntos de\nun Conjunto", 140, 140);

        List<Button> botones = List.of(introBtn, interesBtn, hanoiBtn, potenciaBtn, subconjBtn);
        DropShadow shadow = new DropShadow(6, Color.rgb(0,0,0,0.3));
        botones.forEach(b -> {
            b.setEffect(shadow);
            b.setPadding(new Insets(10));
            b.setStyle(
                    "-fx-background-color: transparent;" +
                            "-fx-border-color: #cccccc;" +
                            "-fx-border-radius: 20;" +
                            "-fx-background-radius: 20;"
            );
        });

        introBtn   .setOnAction(e -> controlador.abrirIntroduccion());
        interesBtn .setOnAction(e -> controlador.abrirInteresCompuesto());
        hanoiBtn   .setOnAction(e -> controlador.abrirTorresDeHanoi());
        potenciaBtn.setOnAction(e -> controlador.abrirPotencia());
        subconjBtn .setOnAction(e -> controlador.abrirSubconjuntos());

        HBox botonesPane = new HBox(20, introBtn, interesBtn, hanoiBtn, potenciaBtn, subconjBtn);
        botonesPane.setAlignment(Pos.CENTER);

        // 4) Botones de ayuda / salir
        Button helpBtn = new Button("Instrucciones");
        helpBtn.setFont(dataFont);
        helpBtn.setOnAction(e -> {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("Ayuda rápida");
            info.setHeaderText("Cómo usar Recursividad");
            info.setContentText(
                    "• Haz clic en cada icono para abrir la sección correspondiente.\n" +
                            "• En cada ventana tendrás ejemplos, explicaciones y gráficos.\n" +
                            "• Usa los botones “Regresar” para volver a esta portada.\n\n" +
                            "¡Disfruta explorando la recursividad!"
            );
            info.showAndWait();
        });

        Button exitBtn = new Button("Salir");
        exitBtn.setFont(dataFont);
        exitBtn.setOnAction(e -> Platform.exit());

        HBox auxPane = new HBox(10, helpBtn, exitBtn);
        auxPane.setAlignment(Pos.CENTER);
        auxPane.setPadding(new Insets(10,0,0,0));

        // 5) Datos personales
        Label uniLabel     = new Label("UNIVERSIDAD AUTÓNOMA METROPOLITANA, UNIDAD CUAJIMALPA");
        Label deptoLabel   = new Label("DEPARTAMENTO DE MATEMÁTICAS APLICADAS Y SISTEMAS");
        Label carreraLabel = new Label("INGENIERÍA EN COMPUTACIÓN");
        Label ueaLabel     = new Label("ESTRUCTURA DE DATOS LINEALES");
        Label profLabel    = new Label("ROGELIO ERNESTO GARCÍA CHÁVEZ");
        Label m1           = new Label("CASANDRA ZETINA RODRÍGUEZ");
        Label m2           = new Label("MARIA FERNANDA RICO ARELLANO");
        Label m3           = new Label("ÁNGEL ARMANDO OLIVEROS GUTIÉRREZ");

        for (Label lbl : List.of(uniLabel, deptoLabel, carreraLabel, ueaLabel, profLabel, m1, m2, m3)) {
            lbl.setFont(dataFont);
            switch (lbl.getText()) {
                case "UNIVERSIDAD AUTÓNOMA METROPOLITANA, UNIDAD CUAJIMALPA" ->
                        lbl.setTextFill(Color.web("#e76f51"));
                case "DEPARTAMENTO DE MATEMÁTICAS APLICADAS Y SISTEMAS" ->
                        lbl.setTextFill(Color.web("#f4a261"));
                case "INGENIERÍA EN COMPUTACIÓN" ->
                        lbl.setTextFill(Color.web("#e9c46a"));
                case "ESTRUCTURA DE DATOS LINEALES" ->
                        lbl.setTextFill(Color.web("#2a9d8f"));
                case "ROGELIO ERNESTO GARCÍA CHÁVEZ", "CASANDRA ZETINA RODRÍGUEZ" ->
                        lbl.setTextFill(Color.web("#e76f51"));
                case "MARIA FERNANDA RICO ARELLANO" ->
                        lbl.setTextFill(Color.web("#f4a261"));
                case "ÁNGEL ARMANDO OLIVEROS GUTIÉRREZ" ->
                        lbl.setTextFill(Color.web("#2a9d8f"));
            }
        }
        VBox dataBox = new VBox(0,
                uniLabel, deptoLabel, carreraLabel, ueaLabel, profLabel, m1, m2, m3
        );
        dataBox.setAlignment(Pos.CENTER);

        // 6) Contenedor principal
        VBox root = new VBox(30,
                titleView,
                botonesPane,
                dataBox,
                auxPane
        );
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: BLACK; -fx-border-color: WHITE");

        VBox rootR = new VBox(10, root);
        rootR.setAlignment(Pos.CENTER);
        rootR.setStyle("-fx-background-color: BLACK; -fx-padding: 10");

        Scene scene = new Scene(rootR);
        stage.setScene(scene);
        stage.setTitle("Recursividad");
        stage.setMaximized(true);
        stage.show();
    }

    private Button makeIconButton(String imagePath, String text, double w, double h) {
        Image img = new Image(getClass().getResourceAsStream(imagePath));
        ImageView iv = new ImageView(img);
        iv.setFitWidth(w);
        iv.setFitHeight(h);
        iv.setPreserveRatio(true);

        Label lbl = new Label(text);
        lbl.setFont(Font.font(14));
        lbl.setTextFill(Color.WHITE);
        lbl.setTextAlignment(TextAlignment.CENTER);
        lbl.setAlignment(Pos.CENTER);

        StackPane stack = new StackPane(iv, lbl);
        stack.setAlignment(Pos.CENTER);

        Button btn = new Button();
        btn.setGraphic(stack);
        btn.setPrefSize(w + 40, h + 60);
        return btn;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
