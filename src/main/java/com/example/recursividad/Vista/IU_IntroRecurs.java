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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class IU_IntroRecurs extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // 1) Fuentes
        Font titleFont   = Font.loadFont(
                getClass().getResourceAsStream("/com/example/Fuentes/Pixeled.ttf"), 16);
        Font contentFont = Font.loadFont(
                getClass().getResourceAsStream("/com/example/Fuentes/CutiveMono-Regular.ttf"), 16);

        ControladorIntroRecurs controlador = new ControladorIntroRecurs(stage);

        // 2) Título con imagen al 40%
        Image titleImg = new Image(getClass().getResourceAsStream("/com/example/Imagenes/IR.png"));
        ImageView titleView = new ImageView(titleImg);
        titleView.setPreserveRatio(true);
        titleView.fitWidthProperty().bind(stage.widthProperty().multiply(0.4));
        titleView.setEffect(new DropShadow(4, Color.rgb(0,0,0,0.3)));

        // 3) Explicación de la recursividad
        Text conceptTitle = new Text("¿Qué es la recursividad?");
        conceptTitle.setFont(titleFont);
        conceptTitle.setFill(Color.web("#e9c46a"));
        conceptTitle.setTextAlignment(TextAlignment.CENTER);

        Text conceptDesc = new Text(
                "La recursividad es una técnica de programación donde una función" +
                        " se invoca a sí misma para resolver un problema dividiéndolo" +
                        " en subproblemas más pequeños, hasta alcanzar un caso base que" +
                        " detiene las llamadas recursivas."
        );
        conceptDesc.setFont(contentFont);
        conceptDesc.setFill(Color.WHITE);
        conceptDesc.setTextAlignment(TextAlignment.JUSTIFY);
        conceptDesc.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.8));

        // 4) Importancia
        Text importanceTitle = new Text("¿Por qué usar recursividad?");
        importanceTitle.setFont(titleFont);
        importanceTitle.setFill(Color.web("#2a9d8f"));

        Text imp1 = new Text("• Divide problemas complejos en subproblemas manejables.");
        Text imp2 = new Text("• Facilita el trato de estructuras auto-referenciales (árboles, listas).");
        Text imp3 = new Text("• Proporciona soluciones elegantes y mantenibles.");
        for (Text t : new Text[]{imp1, imp2, imp3}) {
            t.setFont(contentFont);
            t.setFill(Color.WHITE);
            t.setTextAlignment(TextAlignment.LEFT);
            t.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.8));
        }

        // 5) Ejemplo 1: Factorial
        Text ex1Title = new Text("Ejemplo 1: Factorial recursivo");
        ex1Title.setFont(titleFont);
        ex1Title.setFill(Color.web("#e76f51"));


        /***Gráfico (árbol de llamadas)
        Image imgFact = new Image(
                getClass().getResourceAsStream("/com/example/Imagenes/FactorialTree.png")
        );
        ImageView factView = new ImageView(imgFact);
        factView.setPreserveRatio(true);
        factView.fitWidthProperty().bind(stage.widthProperty().multiply(0.25));
        factView.setEffect(new DropShadow(4, Color.rgb(0,0,0,0.3)));**/

        // Pseudocódigo
        Text factCode = new Text(
                "int factorial(int n) {\n" +
                        "    if (n <= 1) return 1;        // caso base\n" +
                        "    else return n * factorial(n - 1); // recursión\n" +
                        "}"
        );
        factCode.setFont(Font.font("Monospaced", 14));
        factCode.setFill(Color.web("#e9c46a"));
        factCode.setTextAlignment(TextAlignment.LEFT);
        factCode.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.4));

        HBox example1 = new HBox(20,  factCode);
        example1.setAlignment(Pos.CENTER_LEFT);

        // 6) Ejemplo 2: Imprimir lista enlazada
        Text ex2Title = new Text("Ejemplo 2: Imprimir lista enlazada");
        ex2Title.setFont(titleFont);
        ex2Title.setFill(Color.web("#f4a261"));

        Text listCode = new Text(
                "void printList(Node node) {\n" +
                        "    if (node == null) return;      // caso base\n" +
                        "    System.out.println(node.value);   // procesar\n" +
                        "    printList(node.next);              // recursión\n" +
                        "}"
        );
        listCode.setFont(Font.font("Monospaced", 14));
        listCode.setFill(Color.web("#f4a261"));
        listCode.setTextAlignment(TextAlignment.LEFT);
        listCode.wrappingWidthProperty().bind(stage.widthProperty().multiply(0.4));

        HBox example2 = new HBox(20,  listCode);
        example2.setAlignment(Pos.CENTER_LEFT);

        // 7) Botón regresar
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
        btnBack.setOnAction(e -> controlador.volverPortada());

        // 8) Layout final
        VBox root = new VBox(20,
                titleView,
                conceptTitle, conceptDesc,
                importanceTitle, imp1, imp2, imp3,
                ex1Title, example1,
                ex2Title, example2,
                btnBack
        );
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: BLACK; -fx-border-color: WHITE");

        VBox rootR = new VBox(10, root);
        rootR.setAlignment(Pos.CENTER);
        rootR.setStyle("-fx-background-color: BLACK; -fx-padding: 10");

        Scene scene = new Scene(rootR);
        stage.setScene(scene);
        stage.setTitle("Introducción a la Recursividad");
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
