// JUEGO: EL TWISTER
package paquete;

import java.util.Scanner;

public class Principal {

    //**********************************************************************
    public void refugios() { // MÉTODO QUE SOLO IMPRIME LOS REFUGIOS DISPONIBLES Y SU COSTE DE ENERGÍA AL USUARIO. (ANTES DE ELEGIR UNO)

        System.out.println(" DEBERAS ELEGIR ENTRE 3 REFUGIOS. LA ELECCION INICIAL CONSUMIRA ENERGIA POR EL TRASLADO. \n ** AVISO ** CADA REFUGIO TIENE UN SUMINISTRO CLAVE QUE DEBES ENCONTRAR!\n");
        System.out.println(" 1. MONTAÑA (RIESGO BAJO, -25 E)");
        System.out.println(" 2. BOSQUE (RIESGO MEDIO, -15 E)");
        System.out.println(" 3. BÚNKER (RIESGO ALTO, -10 E)\n");
    }

    //**********************************************************************
    public void energiaySalud(int energia, int salud) {
        System.out.println(" -------------------------------");
        System.out.println(" | ENERGIA: " + energia + "% | SALUD: " + salud + "% |");
        System.out.println(" -------------------------------\n");
    }
    //**********************************************************************

    /* ESCRIBIR LA PRIMERA PARTE DE LA NARRATIVA: DESCRIPCIÓN DEL TWISTER Y DE 
    LOS REFUGIOS.*/
    public void eleccionRefugio(Scanner sc, int energia, int salud) {

        System.out.println(" **************************************************");
        System.out.println(" | LA FURIA DEL TWISTER: SOBREVIVE A LA TORMENTA! | ");
        System.out.println(" **************************************************\n");
        System.out.println(" UN TWISTER DE CATEGORIA F5, APODADO 'LA FURIA', SE ACERCA A TU UBICACION. TIENES \n15 MINUTOS PARA PREPARARTE Y ELEGIR EL MEJOR LUGAR PARA SOBREVIVIR. TU ENERGIA \nINICIAL ES DE 100% Y TU SALUD INICIAL ES DE 100%.\n");
        System.out.println(" TU OBJETIVO ES LLEGAR AL REFUGIO Y SUPERAR LAS RONDAS DE ATAQUE ANTES DE QUE EL \nTWISTER TE ALCANCE. LA GESTION DE TU **INVENTARIO (5/9 ESPACIOS REQUERIDOS)** Y \nTU **ENERGIA** SON VITALES.\n");
        System.out.println(" --------------------------------------------------");
        System.out.println(" |'EL TIEMPO SE ACABA. LA MONTANA ES SEGURA PERO EL ASCENSO TE AGOTARA.\n | EL BOSQUE TIENE RECURSOS PERO ES PELIGROSO. EL BUNKER ES HERMETICO, \n | PERO, TENDRA LO QUE NECESITO?'");
        System.out.println(" --------------------------------------------------\n");

        refugios(); // LLAMO AL MÉTODO QUE IMPRIME LOS REFUGIOS DISPONIBLES JUNTO A SU REQUERIMIENTO DE ENERGÍA.

        System.out.println(" --------------------------------");
        System.out.println(" | ENERGIA: " + energia + "% | SALUD: " + salud + "% |");
        System.out.println(" --------------------------------\n");

        System.out.println(" *** EL TWISTER SE ACERCA! ***");
        System.out.print(" > FASE 1: ELIGE UN REFUGIO -> ESCRIBE EL NOMBRE (EJ: BOSQUE): ");
        String refugioElegido = sc.next().toUpperCase();

        // ESTRUCTURA DE CONTROL CONDICIONAL PARA LA ELECCIÓN DE LOS DIFERENTES REFUGIOS, JUNTO AL MENSAJE PERSONALIZADO Y EL COSTE DE ENERGÍA.
        switch (refugioElegido) {
            case "MONTAÑA":
                energia -= 25;
                System.out.println(" | REFUGIO ELEGIDO: " + refugioElegido + ". COSTE: -25 ENERGIA");
                energiaySalud(energia, salud);
                break;
            case "BOSQUE":
                energia -= 15;
                System.out.println(" | REFUGIO ELEGIDO: " + refugioElegido + ". COSTE: -15 ENERGIA ");
                energiaySalud(energia, salud);
                break;
            case "BÚNKER":
                energia -= 10;
                System.out.println(" | REFUGIO ELEGIDO: " + refugioElegido + ". COSTE: -10 ENERGIA");
                energiaySalud(energia, salud);
            default:

                while (!refugioElegido.equals("MONTAÑA") && (!refugioElegido.equals("BOSQUE")) && (!refugioElegido.equals("BÚNKER"))) {
                    System.out.print(" (X) HAS INTRODUCIDO UN REFUGIO INCORRECTO, INTENTA DE NUEVO (MONTAÑA, BOSQUE O BUNKER): ");
                    refugioElegido = sc.next().toUpperCase();
                    switch (refugioElegido) {
                        case "MONTAÑA":
                            energia -= 25;
                            System.out.println(" | REFUGIO ELEGIDO: " + refugioElegido + ". COSTE: -25 ENERGIA");
                            energiaySalud(energia, salud);
                            break;
                        case "BOSQUE":
                            energia -= 15;
                            System.out.println(" | REFUGIO ELEGIDO: " + refugioElegido + ". COSTE: -15 ENERGIA ");
                            energiaySalud(energia, salud);
                            break;
                        case "BÚNKER":
                            energia -= 10;
                            System.out.println(" | REFUGIO ELEGIDO: " + refugioElegido + ". COSTE: -10 ENERGIA");
                            energiaySalud(energia, salud);
                    }

                }
        }

    }

    //**********************************************************************
    public static void main(String[] args) {

        Principal p = new Principal();
        Scanner sc = new Scanner(System.in);
        //**********************************************************************
        int energia = 100; // ENERGÍA INICIAL DEL USUARIO
        int salud = 100; // SALUD INICIAL DEL USUARIO
        String[] listaDeSuministros = {"AGUA", "COMIDA", "HIERBA", "MUNICION", "CUERDA", "FOSFOROS", "LINTERNA", "BOTIQUIN", "BENGALA", "LLAVE"}; // CREO UN ARRAY DE TIPO STRING QUE ALOJA LOS SUMINISTROS PREDETERMINADOS DEL JUEGO.
        String[] valija = new String[9]; // CREO CREO UN ARRAY DE TIPO STRING, QUE SE USARÁ COMO MOCHILA/INVENTARIO PARA LOS SUMINISTROS DEL USUARIO.
        //**********************************************************************

        p.eleccionRefugio(sc, energia, salud);

    } // FIN DE MAIN
} // FIN DE PRINCIPAL
