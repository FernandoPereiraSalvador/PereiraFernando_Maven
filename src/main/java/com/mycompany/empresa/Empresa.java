/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.empresa;

/**
 *
 * @author Fernando
 */
public class Empresa {

    /**
     * Creamos un nueva vivienda
     *
     * @param edifici Matriz tridimensional con los datos de las viviendas
     */
    public static void construirVivenda(vivenda edifici[][][]) {
        // Llenamos la matriz
        for (vivenda[][] edificiEscala : edifici) {
            for (vivenda[] edificiPlanta : edificiEscala) {
                for (vivenda edificiPorta : edificiPlanta) {
                    // Pedimos los datos
                    System.out.println("Construir vivienda en ");
                        edificiPorta.m2 = Teclat.lligInt("Introduce los metros cuadrados: ");
                    edificiPorta.q_hab = Teclat.lligInt("Introduce el q_hab: ");
                    edificiPorta.preu = Teclat.lligInt("Introduce el precio: ");
                    edificiPorta.nif = Teclat.lligString("Introduce el nif del propietario: ");

                    String nom = Teclat.lligString("Introduce el nombre de la vivienda: ");
                    while (comprobarNom(edifici, nom) == true) {
                        System.out.println("Elnombre se esta usando selecciona otro");
                        nom = Teclat.lligString("Introduce el nombre de la vivienda: ");
                    }
                    edificiPorta.nom = nom;

                }
            }
        }
    }

    /**
     * Si encontramos la vivienda devuelve un true si no un false
     *
     * @param edifici Matriz tridimensional con los datos de las viviendas
     * @param nom El nombre de la vivienda que queremos comprobar
     * @return
     */
    public static boolean comprobarNom(vivenda edifici[][][], String nom) {
        for (vivenda[][] edificiEscala : edifici) {
            for (vivenda[] edificiPlanta : edificiEscala) {
                // Encontramos la vivienda
                for (vivenda edificiPorta : edificiPlanta) {
                    if (edificiPorta.nom == null ? nom == null : edificiPorta.nom.equals(nom)) {
                        return true;
                    }
                }
            }
        }
        // No encontramos la vivienda
        return false;
    }

    /**
     * Se realiza la compra de una vivienda si no tiene dueño y esta construida
     *
     * @param edifici Matriz tridimensional con los datos de las viviendas
     */
    public static void comprarVivienda(vivenda edifici[][][]) {
        // Iniciamos una variable booleana para saber si hemos encontrado la vivienda
        boolean encontrado = false;
        // Imprimimos el nombre de la funcion
        System.out.println("Comprar vivienda");
        // Pedimos le nif del edificio
        String nom = Teclat.lligString("Introduce el nombre: ");
        // Recorremos la matriz
        for (vivenda[][] edificiEscala : edifici) {
            for (vivenda[] edificiPlanta : edificiEscala) {
                for (vivenda edificiPorta : edificiPlanta) {
                    // Buscamos el edificio con el nom
                    if (edificiPorta.nom.equals(nom) && edificiPorta.nif.equals("")) {
                        encontrado = true;
                        // Comprobamos si está construido y no tiene propietario
                        if (edificiPorta.m2 > 0) {
                            edificiPorta.nif = Teclat.lligString("Introduce el NIF del propietario: ");
                        } else {
                            System.out.println("No está construido");
                        }

                    }
                }
            }
            // Si no se ha encontrado imprimimos un mensaje
            if (encontrado == false) {
                System.out.println("No se ha encontrado la vivienda o ya tiene un propietario");

            }
        }
    }

    /**
     * Muestra los datos de una vivienda
     *
     * @param edifici Matriz tridimensional con los datos de las viviendas
     * @param escala La escala de la vivienda
     * @param pis El piso de la vivienda
     * @param porta La porta de la vivienda
     */
    public static void propietats(vivenda edifici[][][], int escala, int pis, int porta) {
        // Recorremos la matriz tridimensional
        System.out.println("Los metros cuadrados son: " + edifici[escala][pis][porta].m2);
        System.out.println("El nif del propietario es: " + edifici[escala][pis][porta].nif);
        System.out.println("El nombre es: " + edifici[escala][pis][porta].nom);
        System.out.println("El precio es: " + edifici[escala][pis][porta].preu);
        System.out.println("La cantidad de habitaciones es: " + edifici[escala][pis][porta].q_hab);

    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        // Definimos las variables 8 6 5
        int escales = 1;
        int plantes = 1;
        int portes = 1;
        // Creamos la matriz edifici
        vivenda[][][] edifici = new vivenda[escales][plantes][portes];

        // Reservamos memoria
        for (vivenda[][] edifici1 : edifici) {
            for (vivenda[] edifici11 : edifici1) {
                for (int k = 0; k < edifici11.length; k++) {
                    edifici11[k] = new vivenda();
                }
            }
        }

        int opcion = 0;

        while (opcion != 4) {
            System.out.println("Opcion 1: Construir vivienda");
            System.out.println("Opcion 2: Comprar vivienda");
            System.out.println("Opcion 3: Mostrar propiedades");
            System.out.println("Opcion 4: Salir");

            opcion = Teclat.lligInt("Introduce la opción que quieras: ");

            switch (opcion) {
                case 1 ->
                    construirVivenda(edifici);
                case 2 ->
                    comprarVivienda(edifici);
                case 3 -> {
                    int escalaSolicitada = Teclat.lligInt("Introduce la escala de la vivienda: ") - 1;
                    int pisSolicitada = Teclat.lligInt("Introduce el pis de la vivienda: ") - 1;
                    int portaSolicitada = Teclat.lligInt("Introduce la porta de la vivienda: ") - 1;

                    propietats(edifici, escalaSolicitada, pisSolicitada, portaSolicitada);
                }
                default -> {
                    System.out.println("Ha introducido una opcion incorrecta");
                }
            }
        }

        // Llamamos a las funciones
    }
}

/**
 * Se definen los datos de una vivienda
 *
 * @author Fernando
 */
class vivenda {

    // Metros cuadrados
    int m2;
    // Cantidad habitaciones
    int q_hab;
    // Precio
    int preu;
    // Nombre que la identifica
    String nom;
    // nif del propietario
    String nif;
}
