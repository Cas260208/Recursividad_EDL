package com.example.recursividad.Vista;

import com.example.recursividad.Controlador.ControladorTorre;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class IU_TorreHan extends Application {
    private List<String> movimientos;
    private List<VBox> pegs;
    private Text moveText;
    private Button btnRun;
    private final Color[] diskColors = {
            Color.web("#ef7c5f"),
            Color.web("#fad988"),
            Color.web("#fcb276"),
            Color.web("#59cabc")
    };

    @Override
    public void start(Stage primaryStage) {
        // Fuentes
        Font titleFont   = Font.loadFont(
                getClass().getResourceAsStream("/com/example/Fuentes/Pixeled.ttf"), 36);
        Font contentFont = Font.loadFont(
                getClass().getResourceAsStream("/com/example/Fuentes/CutiveMono-Regular.ttf"), 18);

        ControladorTorre ctrl = new ControladorTorre(primaryStage);

        // Título como imagen al 40%
        Image imgTitle = new Image(getClass().getResourceAsStream("/com/example/Imagenes/THT.png"));
        ImageView titleView = new ImageView(imgTitle);
        titleView.setPreserveRatio(true);
        titleView.fitWidthProperty().bind(primaryStage.widthProperty().multiply(0.4));
        titleView.setEffect(new DropShadow(4, Color.rgb(0,0,0,0.3)));

        // Explicación tipo máquina de escribir
        String explText = "Torres de Hanoi: mueve todos los discos de la torre origen a la torre destino, "
                + "siguiendo estas reglas:\n"
                + "1. Solo se mueve un disco a la vez.\n"
                + "2. Nunca se coloca un disco mayor sobre uno menor.\n\n"
                + "Algoritmo recursivo:\n"
                + "Para n discos:\n"
                + "  • Mover n-1 discos de Origen a Auxiliar.\n"
                + "  • Mover el disco más grande de Origen a Destino.\n"
                + "  • Mover los n-1 discos de Auxiliar a Destino.";
        Text expl = new Text();
        expl.setFont(contentFont);
        expl.setFill(Color.WHITE);
        expl.setTextAlignment(TextAlignment.JUSTIFY);
        expl.wrappingWidthProperty().bind(primaryStage.widthProperty().multiply(0.8));
        Timeline tw = new Timeline();
        for (int i = 0; i <= explText.length(); i++) {
            final int idx = i;
            tw.getKeyFrames().add(new KeyFrame(
                    Duration.millis(50 * i),
                    e -> expl.setText(explText.substring(0, idx))
            ));
        }
        tw.play();

        // Generar lista de movimientos
        movimientos = new ArrayList<>();
        generarMovimientos(4, 'A', 'C', 'B');

        // Construir pegs y discos (área blanca reducida + giro 180°)
        HBox pegContainer = new HBox(60);
        pegContainer.setAlignment(Pos.BOTTOM_CENTER);
        pegContainer.setPrefWidth(400);
        pegContainer.setPadding(new Insets(10, 0, 10, 0));

        pegs = new ArrayList<>();
        Color rodColor = Color.web("#59cabc");
        for (int i = 0; i < 3; i++) {
            VBox peg = new VBox(5);
            peg.setAlignment(Pos.BOTTOM_CENTER);
            peg.setPrefWidth(100);
            Rectangle rod = new Rectangle(8, 180, rodColor);
            rod.setArcWidth(6);
            rod.setArcHeight(6);
            StackPane stack = new StackPane(rod, peg);
            stack.setAlignment(Pos.BOTTOM_CENTER);
            pegs.add(peg);
            pegContainer.getChildren().add(stack);
        }

        StackPane towerPane = new StackPane(pegContainer);
        towerPane.setStyle("-fx-background-color: white;");
        towerPane.setPadding(new Insets(10));
        towerPane.setMaxWidth(400);
        towerPane.setRotate(180);

        // Añadir discos iniciales
        resetTower();

        // Texto del movimiento actual, centrado
        moveText = new Text();
        moveText.setFont(contentFont);
        moveText.setFill(Color.web("#e9c46a"));
        moveText.setWrappingWidth(400);
        moveText.setTextAlignment(TextAlignment.CENTER);
        HBox moveBox = new HBox(moveText);
        moveBox.setAlignment(Pos.CENTER);

        // Botón Correr (ahora es campo de clase para poder habilitar/deshabilitar)
        btnRun = new Button("Correr");
        btnRun.setFont(contentFont);
        btnRun.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-border-color: #cccccc;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
                        + "-fx-text-fill: white;"
        );
        btnRun.setEffect(new DropShadow(4, Color.BLACK));
        btnRun.setOnAction(e -> {
            btnRun.setDisable(true);
            playAll();
        });

        Button btnBack = new Button("Regresar");
        btnBack.setFont(contentFont);
        btnBack.setStyle(
                "-fx-background-color: transparent;"
                        + "-fx-border-color: #cccccc;"
                        + "-fx-border-radius: 8;"
                        + "-fx-background-radius: 8;"
                        + "-fx-text-fill: white;"
        );
        btnBack.setEffect(new DropShadow(4, Color.BLACK));
        btnBack.setOnAction(e -> ctrl.volverPortada());

        // Layout general
        VBox root = new VBox(20, titleView, expl, btnRun, towerPane, moveBox, btnBack);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: BLACK; -fx-border-color: WHITE");

        VBox rootR = new VBox(10, root);
        rootR.setAlignment(Pos.CENTER);
        rootR.setStyle("-fx-background-color: BLACK; -fx-padding: 10");

        primaryStage.setScene(new Scene(rootR));
        primaryStage.setTitle("Torres de Hanoi (4 discos)");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    // Rellena la torre de partida con los discos en orden
    private void resetTower() {
        for (VBox peg : pegs) peg.getChildren().clear();
        for (int size = 4; size >= 1; size--) {
            Rectangle disk = new Rectangle(size * 30, 20, diskColors[size - 1]);
            disk.setArcWidth(10);
            disk.setArcHeight(10);
            pegs.get(0).getChildren().add(disk);
        }
        if (moveText != null) moveText.setText("");
    }

    // Reproduce todos los movimientos muy despacio (1.2 s cada uno)
    // y, al terminar, vuelve a habilitar el botón Correr
    private void playAll() {
        Timeline tl = new Timeline();
        for (int i = 0; i < movimientos.size(); i++) {
            final int idx = i;
            tl.getKeyFrames().add(new KeyFrame(
                    Duration.millis(1200L * idx),
                    e -> applyMove(idx)
            ));
        }
        tl.setOnFinished(e -> btnRun.setDisable(false));
        tl.play();
    }

    private void applyMove(int idx) {
        String mov = movimientos.get(idx);
        moveText.setText(mov);
        String[] parts = mov.split(" ");
        int src = parts[4].charAt(0) - 'A';
        int dst = parts[6].charAt(0) - 'A';
        if (!pegs.get(src).getChildren().isEmpty()) {
            Rectangle top = (Rectangle) pegs.get(src)
                    .getChildren()
                    .remove(pegs.get(src).getChildren().size() - 1);
            pegs.get(dst).getChildren().add(top);
        }
    }

    private void generarMovimientos(int n, char origen, char destino, char auxiliar) {
        if (n == 0) return;
        generarMovimientos(n - 1, origen, auxiliar, destino);
        movimientos.add(String.format("Mover disco %d de %s > %s", n, origen, destino));
        generarMovimientos(n - 1, auxiliar, destino, origen);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
