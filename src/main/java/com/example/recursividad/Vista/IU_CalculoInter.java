package com.example.recursividad.Vista;

import com.example.recursividad.Controlador.ControladorCalculoInter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class IU_CalculoInter extends Application {
    @Override
    public void start(Stage primaryStage) {
        // fuentes
        Font titleFont   = Font.loadFont(
                getClass().getResourceAsStream("/com/example/Fuentes/Pixeled.ttf"), 36);
        Font contentFont = Font.loadFont(
                getClass().getResourceAsStream("/com/example/Fuentes/CutiveMono-Regular.ttf"), 18);

        // controlador
        ControladorCalculoInter ctrl = new ControladorCalculoInter(primaryStage);

        // título con imagen más pequeña
        Image titleImg = new Image(getClass().getResourceAsStream("/com/example/Imagenes/IC.png"));
        ImageView titleView = new ImageView(titleImg);
        titleView.setPreserveRatio(true);
        titleView.fitWidthProperty().bind(
                primaryStage.widthProperty().multiply(0.4));
        titleView.setEffect(new DropShadow(4, Color.rgb(0,0,0,0.3)));

        // explicación con efecto máquina de escribir
        String explText = "El interés compuesto consiste en calcular intereses sobre el capital inicial y " +
                "los intereses acumulados, provocando crecimiento exponencial. Cada período, " +
                "los intereses se suman al capital y se recalculan sobre la nueva base.";
        Text expl = new Text();
        expl.setFont(contentFont);
        expl.setFill(Color.WHITE);
        expl.setTextAlignment(TextAlignment.JUSTIFY);
        expl.wrappingWidthProperty().bind(
                primaryStage.widthProperty().multiply(0.6));
        Timeline typewriter = new Timeline();
        for (int i = 0; i <= explText.length(); i++) {
            final int idx = i;
            typewriter.getKeyFrames().add(
                    new KeyFrame(Duration.millis(30 * i), e -> expl.setText(explText.substring(0, idx)))
            );
        }
        typewriter.play();

        // campos de entrada
        TextField tfP = new TextField();
        tfP.setPromptText("Capital inicial (P)");
        tfP.setStyle(
                "-fx-background-color: #ef7c5f;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: transparent;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-radius: 8;"
        );
        TextField tfr = new TextField();
        tfr.setPromptText("Tasa (r), ej. 0.05");
        tfr.setStyle(
                "-fx-background-color: #fcb276;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: transparent;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-radius: 8;"
        );
        TextField tfn = new TextField();
        tfn.setPromptText("Veces al año (n)");
        tfn.setStyle(
                "-fx-background-color: #fad988;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: transparent;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-radius: 8;"
        );
        TextField tft = new TextField();
        tft.setPromptText("Años (t)");
        tft.setStyle(
                "-fx-background-color: #59cabc;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: transparent;" +
                        "-fx-background-radius: 8;" +
                        "-fx-border-radius: 8;"
        );
        HBox inputs = new HBox(10, tfP, tfr, tfn, tft);
        inputs.setAlignment(Pos.CENTER);
        inputs.setPadding(new Insets(10,0,10,0));

        // Label de resultado
        Label resultLabel = new Label();
        resultLabel.setFont(contentFont);
        resultLabel.setTextFill(Color.web("#e9c46a"));

        // configuración de ejes y chart (con etiquetas)
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Periodo");
        xAxis.setTickLabelFill(Color.web("#59cabc"));
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Monto");
        yAxis.setTickLabelFill(Color.web("#59cabc"));
        LineChart<String,Number> chart = new LineChart<>(xAxis, yAxis);
        chart.setLegendVisible(false);
        chart.setAnimated(false);
        chart.setCreateSymbols(true);
        chart.setMaxHeight(300);

        // botones
        Button btnCalc = new Button("Calcular");
        btnCalc.setFont(contentFont);
        btnCalc.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: #cccccc;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: white;"
        );
        btnCalc.setEffect(new DropShadow(4, Color.BLACK));
        btnCalc.setOnAction(e -> {
            try {
                double P0 = Double.parseDouble(tfP.getText());
                double r  = Double.parseDouble(tfr.getText());
                int n = Integer.parseInt(tfn.getText());
                int t = Integer.parseInt(tft.getText());
                int total = n * t;

                XYChart.Series<String,Number> serie = new XYChart.Series<>();
                double current = P0;
                Timeline chartAnim = new Timeline();
                serie.getData().add(new XYChart.Data<>("0", current));
                for (int i=1; i<=total; i++) {
                    final int idx = i;
                    chartAnim.getKeyFrames().add(
                            new KeyFrame(Duration.millis(200*idx), ev -> {
                                double val = current * Math.pow(1 + r/n, idx);
                                serie.getData().add(new XYChart.Data<>(String.valueOf(idx), val));
                            })
                    );
                }
                chart.getData().setAll(serie);
                chartAnim.setOnFinished(ev -> {
                    chart.lookupAll(".chart-series-line").forEach(node -> node.setStyle("-fx-stroke: #ffda81;"));
                    double finalAmt = current * Math.pow(1 + r/n, total);
                    resultLabel.setText(String.format("Monto final: %.2f", finalAmt));
                });
                chartAnim.play();

            } catch (NumberFormatException ex) {
                resultLabel.setText("Ingresa valores válidos.");
            }
        });

        Button btnBack = new Button("Regresar");
        btnBack.setFont(contentFont);
        btnBack.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-border-color: #cccccc;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;" +
                        "-fx-text-fill: white;"
        );
        btnBack.setEffect(new DropShadow(4, Color.BLACK));
        btnBack.setOnAction(e -> ctrl.volverPortada());

        // layout
        VBox root = new VBox(15,
                titleView,
                expl,
                inputs,
                btnCalc,
                resultLabel,
                chart,
                btnBack
        );
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: BLACK; -fx-border-color: WHITE");

        VBox rootR = new VBox(10, root);
        rootR.setAlignment(Pos.CENTER);
        rootR.setStyle("-fx-background-color: BLACK; -fx-padding: 10");

        Scene scene = new Scene(rootR);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cálculo de Interés Compuesto");
        primaryStage.setMaximized(true);
        primaryStage.show();

        // colorear axis-labels tras mostrar
        Node xLabel = xAxis.lookup(".axis-label");
        if (xLabel instanceof Text) ((Text) xLabel).setFill(Color.web("#59cabc"));
        Node yLabel = yAxis.lookup(".axis-label");
        if (yLabel instanceof Text) ((Text) yLabel).setFill(Color.web("#59cabc"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
