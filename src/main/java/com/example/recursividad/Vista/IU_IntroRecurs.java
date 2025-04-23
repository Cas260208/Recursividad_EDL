package com.example.recursividad.Vista;

import com.example.recursividad.Controlador.ControladorIntroRecurs;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class IU_IntroRecurs extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // 1) Carga de fuentes para contenido
        Font contentFont = Font.loadFont(
                getClass().getResourceAsStream("/com/example/Fuentes/CutiveMono-Regular.ttf"),
                18
        );

        ControladorIntroRecurs controlador = new ControladorIntroRecurs(stage);

        // 2) Título con imagen personalizada
        Image titleImg = new Image(
                getClass().getResourceAsStream("/com/example/Imagenes/IR.png")
        );
        ImageView titleView = new ImageView(titleImg);
        titleView.setPreserveRatio(true);
        // Reducimos tamaño a 40% del ancho
        titleView.fitWidthProperty().bind(stage.widthProperty().multiply(0.4));
        DropShadow imgShadow = new DropShadow(4, Color.rgb(0, 0, 0, 0.3));
        imgShadow.setOffsetX(2);
        imgShadow.setOffsetY(2);
        titleView.setEffect(imgShadow);

        // 3) Concepto de Recursividad
        Text conceptTitle = new Text("Concepto de Recursividad");
        conceptTitle.setFont(contentFont);
        conceptTitle.setFill(Color.WHITE);
        conceptTitle.setTextAlignment(TextAlignment.LEFT);
        conceptTitle.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.8));

        Text conceptDesc = new Text(
                "La recursividad es una técnica donde una función se llama a sí misma para resolver subproblemas más pequeños, " +
                        "hasta llegar a un caso base que detiene el proceso."
        );
        conceptDesc.setFont(contentFont);
        conceptDesc.setFill(Color.WHITE);
        conceptDesc.setTextAlignment(TextAlignment.JUSTIFY);
        conceptDesc.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.8));

        // 4) Importancia en Estructuras de Datos Lineales
        Text impTitle = new Text("Importancia en Estructuras de Datos Lineales");
        impTitle.setFont(contentFont);
        impTitle.setFill(Color.WHITE);
        impTitle.setTextAlignment(TextAlignment.LEFT);
        impTitle.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.8));

        Text imp1 = new Text("\u2022 Simplifica problemas complejos en problemas más sencillos.");
        imp1.setFont(contentFont);
        imp1.setFill(Color.WHITE);
        imp1.setTextAlignment(TextAlignment.LEFT);
        imp1.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.8));

        Text imp2 = new Text("\u2022 Facilita la manipulación de estructuras auto-referenciales (como listas enlazadas).");
        imp2.setFont(contentFont);
        imp2.setFill(Color.WHITE);
        imp2.setTextAlignment(TextAlignment.LEFT);
        imp2.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.8));

        // 5) Ejemplo en pseudocódigo agrupado
        Text ejemploTitle = new Text("Ejemplo en pseudocódigo");
        ejemploTitle.setFont(contentFont);
        ejemploTitle.setFill(Color.WHITE);
        ejemploTitle.setTextAlignment(TextAlignment.LEFT);
        ejemploTitle.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.8));

        Text ej1Title = new Text("Factorial Recursivo");
        ej1Title.setFont(contentFont);
        ej1Title.setFill(Color.WHITE);
        ej1Title.setTextAlignment(TextAlignment.LEFT);
        ej1Title.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.8));

        Text ej1 = new Text(
                "FUNCION Factorial(n)\n" +
                        "    SI (n == 0 O n == 1) ENTONCES\n" +
                        "        RETORNAR 1\n" +
                        "    SINO\n" +
                        "        RETORNAR n * Factorial(n - 1)\n" +
                        "    FIN SI\n" +
                        "FIN FUNCION"
        );
        ej1.setFont(Font.font("Monospaced", 13));
        ej1.setFill(Color.web("#e9c46a"));
        ej1.setTextAlignment(TextAlignment.LEFT);
        ej1.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.8));

        Text ej2Title = new Text("Imprimir lista enlazada recursivamente");
        ej2Title.setFont(contentFont);
        ej2Title.setFill(Color.WHITE);
        ej2Title.setTextAlignment(TextAlignment.LEFT);
        ej2Title.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.8));

        Text ej2 = new Text(
                "FUNCION ImprimirLista(nodo)\n" +
                        "    SI nodo ES NULO ENTONCES\n" +
                        "        RETORNAR\n" +
                        "    SINO\n" +
                        "        MOSTRAR nodo.valor\n" +
                        "        ImprimirLista(nodo.siguiente)\n" +
                        "    FIN SI\n" +
                        "FIN FUNCION"
        );
        ej2.setFont(Font.font("Monospaced", 13));
        ej2.setFill(Color.web("#e9c46a"));
        ej2.setTextAlignment(TextAlignment.LEFT);
        ej2.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.8));

        // Contenedor de pseudocódigos
        VBox pseudoBox = new VBox(5, ej1Title, ej1, ej2Title, ej2);
        pseudoBox.setAlignment(Pos.TOP_LEFT);
        pseudoBox.setPadding(new Insets(10, 0, 0, 0));

        // 6) Botón Regresar con estilo de portada
        Button btnRegresar = new Button("Regresar");
        btnRegresar.setFont(contentFont);
        btnRegresar.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: #cccccc;" +
                        "-fx-border-radius: 20;" +
                        "-fx-background-radius: 20;" +
                        "-fx-text-fill: white;"
        );
        DropShadow btnShadow = new DropShadow(6, Color.rgb(0, 0, 0, 0.3));
        btnRegresar.setEffect(btnShadow);
        btnRegresar.setPrefHeight(40);
        btnRegresar.setOnAction(e -> controlador.volverPortada());

        // 7) Layout principal con todos los elementos
        VBox root = new VBox(15,
                titleView,
                conceptTitle, conceptDesc,
                impTitle, imp1, imp2,
                ejemploTitle,
                pseudoBox,
                btnRegresar
        );
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: BLACK;");

        // 8) Escena y Stage responsive
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Introducción a la Recursividad");
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
