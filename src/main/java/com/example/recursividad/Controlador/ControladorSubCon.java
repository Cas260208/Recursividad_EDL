// src/main/java/com/example/recursividad/Controlador/ControladorSubCon.java
package com.example.recursividad.Controlador;

import com.example.recursividad.Vista.IU_Portada;
import com.example.recursividad.Modelo.Recursividad;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControladorSubCon {
    private final Stage stage;

    public ControladorSubCon(Stage stage) {
        this.stage = stage;
    }

    /**
     * Genera y devuelve todos los subconjuntos de Z = {a,b,c,d,e}.
     */
    public List<List<String>> obtenerSubconjuntos() {
        // 1) Conjunto base: índices 0..4
        List<Integer> base = List.of(0, 1, 2, 3, 4);
        // 2) Generamos subconjuntos numéricos
        List<List<Integer>> raw = new ArrayList<>();
        Recursividad.SubconjuntosUtil.generarSubconjuntos(base, 0, new ArrayList<>(), raw);
        // 3) Mapeo índice → letra
        Map<Integer, String> mapa = Map.of(
                0, "a", 1, "b", 2, "c", 3, "d", 4, "e"
        );
        // 4) Convertimos cada lista de Integer a lista de String
        return raw.stream()
                .map(listaInt ->
                        listaInt.stream()
                                .map(mapa::get)
                                .collect(Collectors.toList())
                )
                .collect(Collectors.toList());
    }

    /**
     * Returns the color associated with each element.
     */
    public javafx.scene.paint.Color colorFor(String e) {
        return switch (e) {
            case "a" -> javafx.scene.paint.Color.web("#e76f51");
            case "b" -> javafx.scene.paint.Color.web("#f4a261");
            case "c" -> javafx.scene.paint.Color.web("#e9c46a");
            case "d" -> javafx.scene.paint.Color.web("#2a9d8f");
            case "e" -> javafx.scene.paint.Color.web("#59cabc");
            default  -> javafx.scene.paint.Color.GRAY;
        };
    }

    public void volverPortada() {
        try {
            new IU_Portada().start(new Stage());
            stage.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
