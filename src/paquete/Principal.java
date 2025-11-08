// JUEGO: EL TWISTER
package paquete;

import java.util.Scanner;
import java.util.Random;

public class Principal {

    //**********************************************************************
    public void refugios() { // MÉTODO QUE SOLO IMPRIME LOS REFUGIOS DISPONIBLES Y SU COSTE DE ENERGÍA AL USUARIO. (ANTES DE ELEGIR UNO)

        System.out.println(" DEBERAS ELEGIR ENTRE 3 REFUGIOS. LA ELECCION INICIAL CONSUMIRA ENERGIA POR EL TRASLADO. \n ** AVISO ** CADA REFUGIO TIENE UN SUMINISTRO CLAVE QUE DEBES ENCONTRAR!\n");
        System.out.println(" 1. DESIERTO (RIESGO BAJO, -25 E)");
        System.out.println(" 2. RUINA MAYA (RIESGO MEDIO, -15 E)");
        System.out.println(" 3. CATACUMBAS DEL EXILIO (RIESGO ALTO, -10 E, -5 SALUD)\n");
    }
    //**********************************************************************

    public void energiaySalud(int energia, int salud) {

        System.out.println(" | ENERGIA: " + energia + "% | SALUD: " + salud + "% |\n");

    }
    //**********************************************************************

    //**********************************************************************
    /* ESCRIBIR LA PRIMERA PARTE DE LA NARRATIVA: DESCRIPCIÓN DEL TWISTER Y DE 
    LOS REFUGIOS.*/
    public int[] eleccionRefugio(Scanner sc, int energia, int salud) { // CREO UN MÉTODO DE TIPO ARRAY YA QUE RETORNARÁ EL ESTADO DEL SUPERVIVIENTE JUNTO A LA ELECCIÓN ACTUAL DEL REFUGIO.

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
                    System.out.println("\n | REFUGIO ELEGIDO: " + "DESIERTO" + ". COSTE: -25 ENERGIA");
                    break;
                case 2:
                    energia -= 15;
                    System.out.println("\n | REFUGIO ELEGIDO: " + "RUINA MAYA" + ". COSTE: -15 ENERGIA ");
                    break;
                case 3:
                    energia -= 10;
                    salud -= 5;
                    System.out.println("\n | REFUGIO ELEGIDO: " + "CATACUMBAS DEL EXILIO" + ". COSTE: -10 ENERGIA | SALUD: -5 ");
                    break;
                default:
                    System.out.print(" * OPCION INVALIDA, DEBES INGRESAR 1, 2 O 3: ");

            }
        }

        return new int[]{energia, salud, refugioElegido}; // RETORNA LOS TRES VALORES EN UN SOLO ARRAY.

    }
    //**********************************************************************

    public String[] gestionSuministros(Scanner sc, String[] listaSuministros, String[] inventario) {
        System.out.println(" --------------------------------------------------");
        System.out.println(" | ANTES DE LLEGAR AL REFUGIO TUVISTE TIEMPO PARA RECOLECTAR SUMINISTROS ESENCIALES PARA RESISTIR\n | EL ATAQUE DEL GUARDIAN DEL REFUGIO, JUNTO A LA FURIA DEL TWISTER QUE SE ESTA APROXIMANDO A TI Y\n | ARRASANDO CON TODA EL AREA.");
        System.out.println(" --------------------------------------------------\n");

        // CARGO EN PANTALLA LOS SUMINISTROS DISPONIBLES A ELEGIR USANDO UN FOR.
        System.out.println(" >>> LISTA DE SUMINISTROS <<< \n");
        for (int i = 0; i < listaSuministros.length; i++) {
            System.out.println("  | #" + (i + 1) + " | " + listaSuministros[i]);
        }

        // ASIGNACIÓN DE SUMINISTROS AL ARREGLO 'INVENTARIO' (MOCHILA DEL SUPERVIVIENTE).
        System.out.println("\n > FASE 2: GESTION DE SUMINISTROS (MOCHILA: 0/9 - MINIMO 5/9 ESPACIOS PARA AVANZAR).");
        System.out.println(" | ESCRIBE EL SUMINISTRO DESEADO (EJ: AGUA, COMIDA, LINTERNA,...):  ");

        for (int i = 0; i < inventario.length; i++) {
            System.out.print(" > ");
            inventario[i] = sc.next().toUpperCase().trim();
            if (inventario[i].equalsIgnoreCase(listaSuministros[i])) {
                System.out.println(" HAS AGREGADO: " + inventario[i]);
            }
            System.out.println(" (MOCHILA " + (i + 1) + "/9 ) ");
        }
        return inventario;
    }
    //**********************************************************************

    public String[] eliminarSuministros(Scanner sc, String[] inventario) { // OPCIÓN 1 DEL 'MENÚ DE OPCIONES'

        boolean eliminado = false;
        while (eliminado == false) { // BUCLE PARA ASEGURARNOS QUE EL USUARIO INGRESE UN SUMINISTRO EXISTENTE.
            System.out.println(" | ESCRIBE EL SUMINISTRO QUE DESEAS QUITAR DE TU INVENTARIO: ");
            String quitar = sc.next().toUpperCase().trim();

            for (int i = 0; i < inventario.length; i++) {
                if (inventario[i] != null && inventario[i].equalsIgnoreCase(quitar)) {
                    System.out.println(" HAS QUITADO: " + inventario[i]);
                    inventario[i] = null;

                    eliminado = true;
                }
            }

            if (eliminado == false) {
                System.out.println(" NO TIENES DICHO SUMINISTRO. INTENTA DE NUEVO: ");
            }
        }

        return inventario;
    }
    //**********************************************************************

    public void mostrarInventario(String[] inventario) { // OPCIÓN 3 DEL 'MENÚ DE OPCIONES'

        System.out.println(" >> INVENTARIO <<");

        for (int i = 0; i < inventario.length; i++) {
            System.out.println("  | #" + (i + 1) + " | " + inventario[i]);
        }
    }

    //**********************************************************************
    public String[] agregarSuministro(Scanner sc, String[] inventario) {

        System.out.println(" | ESCRIBE EL SUMINISTRO QUE DESEAS AGREGAR: ");
        String agregarSuministro;

        for (int i = 0; i < inventario.length; i++) {
            System.out.print(" > ");
            agregarSuministro = sc.next().toUpperCase().trim();
            System.out.println(" (MOCHILA " + (i + 1) + "/9 ) ");
            if (inventario[i] == null) {
                inventario[i] = agregarSuministro;

            }

        }
        return inventario;

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

        int[] resultado = p.eleccionRefugio(sc, energia, salud);

        energia = resultado[0];
        salud = resultado[1];
        int refugioElegido = resultado[2];

        p.energiaySalud(energia, salud);

        if (resultado[2] == 1) {
            System.out.println(" REFUGIO ACTUAL: DESIERTO");
            System.out.println(" * EL SOL ARDIENTE CAE SOBRE LAS DUNAS DEL DESIERTO. EL VIENTO ARRASTRA LA ARENA,\n "
                    + "Y EL CALOR TE OBLIGA A RACIONAR TU AGUA. A LO LEJOS, UNA SOMBRA PARECE MOVERSE ENTRE LAS TORMENTAS DE ARENA...\n");
        } else if (resultado[2] == 2) {
            System.out.println(" REFUGIO ACTUALA: RUINA MAYA");
            System.out.println(" * LAS ANTIGUAS PIEDRAS ESTAN CUBIERTAS DE MUSGO. ENTRE COLUMNAS ROTAS Y SIMBOLOS SAGRADOS,\n "
                    + "SIENTES UNA ENERGIA ANCESTRAL... PERO TAMBIEN LA MIRADA DE ALGO QUE AUN VIGILA EL LUGAR.\n");

        } else {

            System.out.println(" REFUGIO ACTUAL: CATACUMBAS DEL EXILIO");
            System.out.println(" * TE HAS HECHO UNA HERIDA AL DESCENDER POR UN TUNEL OSCURO Y HUMEDO. EL ECO DE TUS PASOS RESUENA,\n"
                    + " Y UNA CORRIENTE HELADA TE ERIZA LA PIEL. NO ESTAS SOLO AQUI ABAJO...\n");
        }
        //**********************************************************************

        p.gestionSuministros(sc, listaDeSuministros, inventario); // FASE 2 -> MOSTRAR SUMINISTROS DISPONIBLES EN PANTALLA Y CARGARLOS EN EL ARRAY 'INVENTARIO'.

        int opcion = 0;
        do {
            System.out.println("\n > MENU DE OPCIONES < \n");
            System.out.println(" 1. QUITAR SUMINISTRO");
            System.out.println(" 2. AGREGAR SUMINISTRO");
            System.out.println(" 3. ABRIR INVENTARIO");
            System.out.println(" 0. AVANZAR A LA SIGUIENTE FASE");
            System.out.print("\n > INGRESA LA OPCION (1,2,3 / 0): ");
            opcion = sc.nextInt();
            System.out.println("\n");

            switch (opcion) {

                case 1:
                    inventario = p.eliminarSuministros(sc, inventario);
                    break;
                case 2:
                    inventario = p.agregarSuministro(sc, inventario);
                    break;
                case 3:
                    p.mostrarInventario(inventario);
                    break;

            }
        } while (opcion != 0);

    } // FIN DE MAIN
} // FIN DE PRINCIPAL
