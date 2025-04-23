package com.example.recursividad.Modelo;

import java.util.ArrayList;
import java.util.List;
public class Recursividad {

    public class InteresCompuestoUtil {
        public static double calcularInteres(double P, double r, int n, int t) {
            if (t == 0) {
                return P;
            }
            return calcularInteres(P * (1 + r / n), r, n, t - 1);
        }
        public static void probarCalculoInteres() {
            double capital = 1000;
            double tasa = 0.05;
            int vecesPorAnio = 4;
            int anios = 5;
            double resultado = calcularInteres(capital, tasa, vecesPorAnio, anios);
        }
    }

    public class TorresDeHanoiUtil {
        public static void moverTorres(int n, char origen, char destino, char auxiliar) {
            if (n == 1) {
                return;
            }
            moverTorres(n - 1, origen, auxiliar, destino);
            moverTorres(n - 1, auxiliar, destino, origen);
        }
        public static void probarTorresDeHanoi(int numeroDeDiscos) {
            moverTorres(numeroDeDiscos, 'A', 'C', 'B');
        }
    }

    public class PotenciaUtil {
        public static double calcularPotencia(double base, int exponente) {
            if (exponente == 0) {
                return 1;
            }
            return base * calcularPotencia(base, exponente - 1);
        }
        public static void probarCalculoPotencia() {
            double base = 2;
            int exponente = 5;

            double resultado = calcularPotencia(base, exponente);
        }
    }

    public class SubconjuntosUtil {
        public static void generarSubconjuntos(List<Integer> conjunto, int indice, List<Integer> actual, List<List<Integer>> resultado) {
            if (indice == conjunto.size()) {
                resultado.add(new ArrayList<>(actual));
                return;
            }
            generarSubconjuntos(conjunto, indice + 1, actual, resultado);
            actual.add(conjunto.get(indice));
            generarSubconjuntos(conjunto, indice + 1, actual, resultado);
            actual.remove(actual.size() - 1);}
        public static void probarGeneracionSubconjuntos(List<Integer> conjunto) {
            List<List<Integer>> resultado = new ArrayList<>();
            generarSubconjuntos(conjunto, 0, new ArrayList<>(), resultado);
            for (List<Integer> subconjunto : resultado) {
            }
        }
    }
}
