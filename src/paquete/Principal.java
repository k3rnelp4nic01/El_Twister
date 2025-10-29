// JUEGO: EL TWISTER
package paquete;

import java.util.Scanner;

public class Principal {

    //**********************************************************************
    public void refugios() { // MÉTODO QUE SOLO IMPRIME LOS REFUGIOS DISPONIBLES Y SU COSTE DE ENERGÍA AL USUARIO. (ANTES DE ELEGIR UNO)

        System.out.println(" DEBERAS ELEGIR ENTRE 3 REFUGIOS. LA ELECCION INICIAL CONSUMIRA ENERGIA POR EL TRASLADO. \n ** AVISO ** CADA REFUGIO TIENE UN SUMINISTRO CLAVE QUE DEBES ENCONTRAR!\n");
        System.out.println(" 1. DESIERTO (RIESGO BAJO, -25 E)");
        System.out.println(" 2. RUINA MAYA (RIESGO MEDIO, -15 E)");
        System.out.println(" 3. CATACUMBAS DEL EXILIO (RIESGO ALTO, -10 E)\n");
    }

    public void energiaySalud(int energia, int salud) {

        System.out.println(" | ENERGIA: " + energia + "% | SALUD: " + salud + "% |\n");

    }
    //**********************************************************************v

    /* ESCRIBIR LA PRIMERA PARTE DE LA NARRATIVA: DESCRIPCIÓN DEL TWISTER Y DE 
    LOS REFUGIOS.*/
    public int eleccionRefugio(Scanner sc, int energia, int salud) {

        System.out.println(" **************************************************");
        System.out.println(" | LA FURIA DEL TWISTER: SOBREVIVE A LA TORMENTA! | ");
        System.out.println(" **************************************************\n");
        System.out.println(" UN TWISTER DE CATEGORIA F5, APODADO 'LA FURIA', SE ACERCA A TU UBICACION. TIENES \n15 MINUTOS PARA PREPARARTE Y ELEGIR EL MEJOR LUGAR PARA SOBREVIVIR. TU ENERGIA \nINICIAL ES DE 100% Y TU SALUD INICIAL ES DE 100%.\n");
        System.out.println(" TU OBJETIVO ES LLEGAR AL REFUGIO Y SUPERAR LAS RONDAS DE ATAQUE ANTES DE QUE EL \nTWISTER TE ALCANCE. LA GESTION DE TU **INVENTARIO (5/9 ESPACIOS REQUERIDOS)** Y \nTU **ENERGIA** SON VITALES.\n");
        System.out.println(" --------------------------------------------------");
        System.out.println(" |'EL TIEMPO SE ACABA. EL DESIERTO ES SEGURO PERO EL TRASLADO TE AGOTARA.\n | LA RUINA MAYA TIENE RECURSOS PERO PUEDE SER PELIGROSO. LAS CATACUMBAS \n | PROTEGEN DEL EXTERIOR, PERO ALGO HABITA EN LA OSCURIDAD.");
        System.out.println(" --------------------------------------------------\n");

        refugios(); // LLAMO AL MÉTODO QUE IMPRIME LOS REFUGIOS DISPONIBLES JUNTO A SU REQUERIMIENTO DE ENERGÍA.
        energiaySalud(energia, salud); // VISUALIZACIÓN DE LA ENERGÍA Y SALUD INICIAL DEL SUPERVIVIENTE.

        int refugioElegido = 0;

        System.out.println(" *** EL TWISTER SE ACERCA! ***");
        System.out.print(" > FASE 1: ELIGE UN REFUGIO > ESCRIBE LA OPCION (1, 2 O 3): ");

        // ESTRUCTURA DE CONTROL CONDICIONAL PARA LA ELECCIÓN DE LOS DIFERENTES REFUGIOS, JUNTO AL MENSAJE PERSONALIZADO Y EL COSTE DE ENERGÍA.
        while (refugioElegido < 1 || refugioElegido >= 4) {

            refugioElegido = sc.nextInt();

            switch (refugioElegido) {
                case 1:
                    energia -= 25;
                    System.out.println(" | REFUGIO ELEGIDO: " + "DESIERTO " + ". COSTE: -25 ENERGIA");
                    break;
                case 2:
                    energia -= 15;
                    System.out.println(" | REFUGIO ELEGIDO: " + "RUINA MAYA" + ". COSTE: -15 ENERGIA ");
                    break;
                case 3:
                    energia -= 10;
                    System.out.println(" | REFUGIO ELEGIDO: " + "CATACUMBAS DEL EXILIO" + ". COSTE: -10 ENERGIA");
                    break;
                default:
                    System.out.print(" * OPCION INVALIDA, DEBES INGRESAR 1, 2 O 3: ");

            }
        }
        energiaySalud(energia, salud);
        return energia;

    }

    //**********************************************************************
    public static void main(String[] args) {

        Principal p = new Principal();
        Scanner sc = new Scanner(System.in);
        //**********************************************************************
        int energia = 100; // ENERGÍA INICIAL DEL USUARIO
        int salud = 100; // SALUD INICIAL DEL USUARIO
        String[] listaDeSuministros = {"AGUA", "COMIDA", "HIERBA", "MUNICION", "CUERDA", "FOSFOROS", "LINTERNA", "BOTIQUIN", "BENGALA", "LLAVE"}; // CREO UN ARRAY DE TIPO STRING QUE ALOJA LOS SUMINISTROS PREDETERMINADOS DEL JUEGO.
        String[] inventario = new String[9]; // CREO CREO UN ARRAY DE TIPO STRING, QUE SE USARÁ COMO MOCHILA/INVENTARIO PARA LOS SUMINISTROS DEL USUARIO.
        //**********************************************************************

        energia = p.eleccionRefugio(sc, energia, salud);

    } // FIN DE MAIN
} // FIN DE PRINCIPAL
