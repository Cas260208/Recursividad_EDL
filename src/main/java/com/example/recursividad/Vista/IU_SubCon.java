package com.example.recursividad.Vista;

import com.example.recursividad.Controlador.ControladorSubCon;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
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

import java.util.List;

public class IU_SubCon extends Application {

    private int currentIndex = 0;
    private List<List<String>> subconjuntos;
    private VBox rowsBox;
    private Button btnNext;

    @Override
    public void start(Stage primaryStage) {
        // 1) Título con imagen
        Image titleImg = new Image(
                getClass().getResourceAsStream("/com/example/Imagenes/SC.png")
        );
        ImageView titleView = new ImageView(titleImg);
        titleView.setPreserveRatio(true);
        titleView.fitWidthProperty().bind(
                primaryStage.widthProperty().multiply(0.4)
        );
        titleView.setEffect(new DropShadow(8, Color.web("#e9c46a")));

        // 2) Descripción con máquina de escribir
        String fullDesc = "Un conjunto es una colección bien definida de elementos; " +
                "un subconjunto es cualquier colección de elementos obtenida " +
                "seleccionando cero o más elementos del conjunto original.";
        Text desc = new Text();
        desc.setFont(Font.loadFont(
                getClass().getResourceAsStream("/com/example/Fuentes/CutiveMono-Regular.ttf"), 16));
        desc.setFill(Color.WHITE);
        desc.setTextAlignment(TextAlignment.JUSTIFY);
        desc.wrappingWidthProperty().bind(primaryStage.widthProperty().multiply(0.8));

        Timeline typewriter = new Timeline();
        for (int i = 0; i <= fullDesc.length(); i++) {
            final int idx = i;
            typewriter.getKeyFrames().add(
                    new KeyFrame(Duration.millis(20 * i), e ->
                            desc.setText(fullDesc.substring(0, idx))
                    )
            );
        }
        typewriter.play();

        // 3) Datos del modelo
        ControladorSubCon ctrl = new ControladorSubCon(primaryStage);
        subconjuntos = ctrl.obtenerSubconjuntos();

        // 4) Contenedor de filas
        rowsBox = new VBox(10);
        rowsBox.setAlignment(Pos.TOP_CENTER);

        // 5) ScrollPane estrecho y alto, track color de 'c', thumb negro
        ScrollPane scroll = new ScrollPane(rowsBox);
        scroll.setFitToWidth(true);
        scroll.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.4));
        scroll.prefHeightProperty().bind(primaryStage.heightProperty().multiply(0.6));
        scroll.setStyle(
                "-fx-background: white;" +
                        "-fx-border-color: transparent;"
        );
        primaryStage.setOnShown(e -> {
            String trackColor = "#e9c46a"; // color de la letra 'c'
            scroll.lookupAll(".scroll-bar").forEach(node ->
                    node.setStyle("-fx-background-color: " + trackColor + ";")
            );
            scroll.lookupAll(".thumb").forEach(node ->
                    node.setStyle("-fx-background-color: black;")
            );
        });

        // 6) Botones
        btnNext = new Button("Siguiente");
        btnNext.setFont(desc.getFont());
        btnNext.setOnAction(e -> mostrarSiguiente(ctrl));

        Button btnBack = new Button("Regresar");
        btnBack.setFont(desc.getFont());
        btnBack.setOnAction(e -> ctrl.volverPortada());

        HBox controls = new HBox(20, btnNext, btnBack);
        controls.setAlignment(Pos.CENTER);
        controls.setPadding(new Insets(10, 0, 0, 0));

        // 7) Layout principal
        VBox root = new VBox(20, titleView, desc, scroll, controls);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: BLACK; -fx-border-color: WHITE");

        VBox rootR = new VBox(10, root);
        rootR.setAlignment(Pos.CENTER);
        rootR.setStyle("-fx-background-color: BLACK; -fx-padding: 10");

        Scene scene = new Scene(rootR);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Subconjuntos de un Conjunto");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void mostrarSiguiente(ControladorSubCon ctrl) {
        if (currentIndex < subconjuntos.size()) {
            List<String> sub = subconjuntos.get(currentIndex);

            Text label = new Text(String.format("Subconjunto %d:", currentIndex + 1));
            label.setFont(Font.font(14));
            label.setFill(Color.BLACK);

            HBox badges = new HBox(5);
            badges.setAlignment(Pos.CENTER_LEFT);
            for (String e : sub) {
                Text letra = new Text(e);
                letra.setFont(Font.font(14));
                letra.setFill(Color.WHITE);
                StackPane badge = new StackPane(letra);
                badge.setPadding(new Insets(4, 8, 4, 8));
                badge.setBackground(new Background(new BackgroundFill(
                        ctrl.colorFor(e), new CornerRadii(4), Insets.EMPTY
                )));
                badges.getChildren().add(badge);
            }

            HBox row = new HBox(10, label, badges);
            row.setAlignment(Pos.CENTER);
            rowsBox.getChildren().add(row);

            currentIndex++;
            if (currentIndex >= subconjuntos.size()) {
                btnNext.setDisable(true);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
