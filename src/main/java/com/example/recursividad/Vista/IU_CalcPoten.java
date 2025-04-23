package com.example.recursividad.Vista;

import com.example.recursividad.Controlador.ControladorCalcPoten;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class IU_CalcPoten extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1) Fuentes
        Font titleFont   = Font.loadFont(getClass().getResourceAsStream("/com/example/Fuentes/Pixeled.ttf"), 36);
        Font contentFont = Font.loadFont(getClass().getResourceAsStream("/com/example/Fuentes/CutiveMono-Regular.ttf"), 18);

        // 2) Controlador
        ControladorCalcPoten ctrl = new ControladorCalcPoten(primaryStage);

        // 3) Título como imagen
        Image titleImg = new Image(getClass().getResourceAsStream("/com/example/Imagenes/cp.png"));
        ImageView titleView = new ImageView(titleImg);
        titleView.setPreserveRatio(true);
        titleView.fitWidthProperty().bind(primaryStage.widthProperty().multiply(0.4));
        titleView.setEffect(new DropShadow(4, Color.rgb(0,0,0,0.3)));

        // 4) Explicación con «typewriter»
        String explan = "La potenciación es una operación matemática que consiste en multiplicar una base " +
                "por sí misma tantas veces como indique el exponente:\n\n" +
                "aⁿ = a × a × ... × a   (n veces)";
        Text explText = new Text();
        explText.setFont(contentFont);
        explText.setFill(Color.WHITE);
        explText.setTextAlignment(TextAlignment.JUSTIFY);
        explText.wrappingWidthProperty().bind(primaryStage.widthProperty().multiply(0.8));
        Timeline typer = new Timeline();
        for (int i = 0; i <= explan.length(); i++) {
            final int idx = i;
            typer.getKeyFrames().add(new KeyFrame(
                    Duration.millis(30 * i),
                    e -> explText.setText(explan.substring(0, idx))
            ));
        }
        typer.play();

        // 5) Campos de entrada
        TextField tfBase = new TextField();
        tfBase.setPromptText("Base (a)");
        tfBase.setStyle(
                "-fx-background-color: #e76f51;" +
                        "-fx-text-fill: white;"  +
                        "-fx-prompt-text-fill: rgba(0,0,0,0.6);" +
                        "-fx-background-radius: 6;" +
                        "-fx-border-radius: 6;"
        );
        TextField tfExp = new TextField();
        tfExp.setPromptText("Exponente (n)");
        tfExp.setStyle(
                "-fx-background-color: #2a9d8f;" +
                        "-fx-text-fill: white;"  +
                        "-fx-prompt-text-fill: rgba(0,0,0,0.6);" +
                        "-fx-background-radius: 6;" +
                        "-fx-border-radius: 6;"
        );
        HBox inputs = new HBox(10, tfBase, tfExp);
        inputs.setAlignment(Pos.CENTER);
        inputs.setPadding(new Insets(10,0,10,0));

        // 6) Área de pasos y resultado FINAL dentro de cuadro blanco
        VBox stepsBox = new VBox(5);
        stepsBox.setPadding(new Insets(10));
        stepsBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        stepsBox.setPrefWidth(400);

        ScrollPane scroll = new ScrollPane(stepsBox);
        scroll.setFitToWidth(true);
        scroll.setPrefViewportHeight(150);
        scroll.setMaxWidth(400);

        // 7) Botones
        Button btnCalc = new Button("Calcular");
        btnCalc.setFont(contentFont);
        btnCalc.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: #cccccc;" +
                        "-fx-border-radius: 6;" +
                        "-fx-background-radius: 6;" +
                        "-fx-text-fill: white;"
        );
        btnCalc.setEffect(new DropShadow(4, Color.BLACK));
        btnCalc.setOnAction(e -> {
            stepsBox.getChildren().clear();
            try {
                int a = Integer.parseInt(tfBase.getText());
                int n = Integer.parseInt(tfExp.getText());

                // cálculo iterativo y acumulación de pasos
                List<Integer> valores = new ArrayList<>();
                int acum = a;
                valores.add(acum);
                for (int i = 2; i <= n; i++) {
                    acum *= a;
                    valores.add(acum);
                }

                // mostrar pasos
                for (int i = 0; i < valores.size(); i++) {
                    Text paso = new Text(String.format("Paso %d: %d", i + 1, valores.get(i)));
                    paso.setFont(contentFont);
                    stepsBox.getChildren().add(paso);
                }

                // mostrar resultado final al final del mismo cuadro
                Text resultado = new Text(String.format("Resultado: %d", acum));
                resultado.setFont(contentFont);
                resultado.setFill(Color.web("black"));
                resultado.setWrappingWidth(380);
                resultado.setTextAlignment(TextAlignment.CENTER);
                stepsBox.getChildren().add(new Separator());
                stepsBox.getChildren().add(resultado);

            } catch (NumberFormatException ex) {
                Text error = new Text("Ingresa valores enteros válidos");
                error.setFont(contentFont);
                error.setFill(Color.CRIMSON);
                stepsBox.getChildren().add(error);
            }
        });

        Button btnBack = new Button("Regresar");
        btnBack.setFont(contentFont);
        btnBack.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: #cccccc;" +
                        "-fx-border-radius: 6;" +
                        "-fx-background-radius: 6;" +
                        "-fx-text-fill: white;"
        );
        btnBack.setEffect(new DropShadow(4, Color.BLACK));
        btnBack.setOnAction(e -> ctrl.volverPortada());

        // 8) Layout final
        VBox root = new VBox(15,
                titleView,
                explText,
                inputs,
                btnCalc,
                scroll,
                btnBack
        );
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: BLACK; -fx-border-color: WHITE");

        VBox rootR = new VBox(10, root);
        rootR.setAlignment(Pos.CENTER);
        rootR.setStyle("-fx-background-color: BLACK; -fx-padding: 10");

        primaryStage.setScene(new Scene(rootR));
        primaryStage.setTitle("Cálculo de Potencia");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
