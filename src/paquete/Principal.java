// GAME - *** LA FURIA DEL TWISTER *** - //
package paquete;

import java.util.Scanner;
import java.util.Random;

public class Principal {

    //----------------------------------------------------------------------
    public boolean existeSuministro(String[] inventario, String productoBuscar) {

        for (int i = 0; i < inventario.length; i++) {

            if (productoBuscar.equalsIgnoreCase(inventario[i])) {
                return true;
            }
        }
        return false;
    }

    //----------------------------------------------------------------------
    public void refugios() { // MÉTODO QUE SOLO IMPRIME LOS REFUGIOS DISPONIBLES Y SU COSTE DE ENERGÍA AL USUARIO. (ANTES DE ELEGIR UNO)

        System.out.println(" DEBERAS ELEGIR ENTRE 3 REFUGIOS. LA ELECCION INICIAL CONSUMIRA ENERGIA POR EL TRASLADO. \n ** AVISO ** CADA REFUGIO TIENE UN SUMINISTRO CLAVE QUE DEBES ENCONTRAR!\n");
        System.out.println(" 1. DESIERTO (RIESGO BAJO, -25 E)");
        System.out.println(" 2. RUINA MAYA (RIESGO MEDIO, -15 E)");
        System.out.println(" 3. CATACUMBAS DEL EXILIO (RIESGO ALTO, -10 E, -5 SALUD)\n");
    }

    //----------------------------------------------------------------------
    public void energiaySalud(int energia, int salud) {
        System.out.println(" <<< ENERGIA: " + energia + "% | SALUD: " + salud + "% >>>\n");

    }
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    /* ESCRIBIR LA PRIMERA PARTE DE LA NARRATIVA: DESCRIPCIÓN DEL TWISTER Y DE 
        LOS REFUGIOS.*/
    // FASE 1 - ELECCIÓN DE REFUGIO Y NARRATIVA //
    public int[] eleccionRefugio(Scanner sc, int energia, int salud) { // CREO UN MÉTODO DE TIPO ARRAY YA QUE RETORNARÁ EL ESTADO DEL SUPERVIVIENTE JUNTO A LA ELECCIÓN ACTUAL DEL REFUGIO.

        System.out.println(" **************************************************");
        System.out.println(" | LA FURIA DEL TWISTER: SOBREVIVE A LA TORMENTA! | ");
        System.out.println(" **************************************************\n");

        try {
            Thread.sleep(900);
            System.out.println(" UN TWISTER DE CATEGORIA F5, APODADO 'LA FURIA', SE ACERCA A TU UBICACION. TIENES \n15 MINUTOS PARA PREPARARTE Y ELEGIR EL MEJOR LUGAR PARA SOBREVIVIR. TU ENERGIA \nINICIAL ES DE 100% Y TU SALUD INICIAL ES DE 100%.\n");
            System.out.println(" TU OBJETIVO ES LLEGAR AL REFUGIO Y SUPERAR LAS RONDAS DE ATAQUE ANTES DE QUE EL \nTWISTER TE ALCANCE. LA GESTION DE TU **INVENTARIO (5/9 ESPACIOS REQUERIDOS)** Y \nTU **ENERGIA** SON VITALES.\n");

            Thread.sleep(1200);
            System.out.println(" --------------------------------------------------");
            System.out.println(" |'EL TIEMPO SE ACABA. EL DESIERTO ES SEGURO PERO EL TRASLADO TE AGOTARA.\n | LA RUINA MAYA TIENE RECURSOS PERO PUEDE SER PELIGROSO. LAS CATACUMBAS \n | PROTEGEN DEL EXTERIOR, PERO ALGO HABITA EN LA OSCURIDAD.");
            System.out.println(" --------------------------------------------------\n");

        } catch (InterruptedException e) {
            System.out.println("ERROR EN LA NARRATIVA...");
        }

        /* limpiarPantalla();*/
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
                    System.out.println("\n | REFUGIO ELEGIDO: " + "DESIERTO" + ". COSTE: -25 ENERGIA\n ");
                    break;
                case 2:
                    energia -= 15;
                    System.out.println("\n | REFUGIO ELEGIDO: " + "RUINA MAYA" + ". COSTE: -15 ENERGIA\n ");
                    break;
                case 3:
                    energia -= 10;
                    salud -= 5;
                    System.out.println("\n | REFUGIO ELEGIDO: " + "CATACUMBAS DEL EXILIO" + ". COSTE: -10 ENERGIA | SALUD: -5\n ");
                    break;
                default:
                    System.out.print(" * OPCION INVALIDA, DEBES INGRESAR 1, 2 O 3: ");

            }
        }

        return new int[]{energia, salud, refugioElegido}; // RETORNA LOS TRES VALORES EN UN SOLO ARRAY.

    }

    //----------------------------------------------------------------------
    // FASE 2 - GESTIÓN DE SUMINISTROS //
    public String[] gestionSuministros(Scanner sc, String[] listaSuministros, String[] inventario, Principal p, int refugioElegido, int energia, int salud) {

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

            // SI YA TIENE 5 SUMINISTROS O MÁS, LE PERMITIMOS "AVANZAR" A LA SIGUIENTE FASE.
            if (i >= 5) {
                System.out.println("\n | INVENTARIO LISTO " + (i) + "/9 - TIENES LO SUFICIENTE PARA RESISTIR.");
                System.out.println(" | ESCRIBE 'AVANZAR' PARA INICIAR LA FASE 3 (ATAQUES) O PUEDES SEGUIR AGREGANDO SUMINISTROS.\n");
            }

            System.out.print(" > ");
            String suministroTemporal = sc.next().toUpperCase().trim();

            // SI ESCRIBE "AVANZAR", SE LLAMA AL SIGUIENTE MÉTODO Y EL BUCLE SE CORTA.
            if (suministroTemporal.equalsIgnoreCase("AVANZAR") && i >= 5) {
                return null;
            }

            // SI NO EXISTE SUMINISTRO, AGREGO.
            boolean valido = false; // VARIABLE QUE CONTROLA EL BUCLE
            while (!valido) { // BUCLE CON VALIDACIÓN DE SUMINISTROS REPETIDOS O NO EXISTENTES.

                while (p.existeSuministro(inventario, suministroTemporal)) {
                    System.out.print(" *NO PUEDES REPETIR EL SUMINISTRO, INGRESA OTRO: ");
                    suministroTemporal = sc.next();
                }

                if (!p.disponibilidadSuministro(listaSuministros, suministroTemporal)) {
                    System.out.print(" *NO EXISTE DICHO SUMINISTRO EN LA LISTA, INGRESA UNO VALIDO: ");
                    suministroTemporal = sc.next();

                } else {
                    valido = true;
                }
            }
            // ACÁ AGREGO EL SUMINISTRO
            inventario[i] = suministroTemporal;
            System.out.println(" + " + inventario[i]);
            System.out.println(" (MOCHILA " + (i + 1) + "/9 ) ");
        }
        System.out.println(" ");
        return inventario;
    }

    //----------------------------------------------------------------------
    public String[] eliminarSuministros(Scanner sc, String[] inventario) { // OPCIÓN 1 DEL 'MENÚ DE OPCIONES'

        boolean eliminado = false;
        while (eliminado == false) { // BUCLE PARA ASEGURARNOS QUE EL USUARIO INGRESE UN SUMINISTRO EXISTENTE.
            System.out.println(" | ESCRIBE EL SUMINISTRO QUE DESEAS QUITAR DE TU INVENTARIO: ");
            System.out.print(" > ");
            String quitar = sc.next().toUpperCase().trim();

            for (int i = 0; i < inventario.length; i++) {
                if (inventario[i] != null && inventario[i].equalsIgnoreCase(quitar)) {
                    System.out.println(" HAS QUITADO: " + inventario[i]);
                    inventario[i] = "";

                    eliminado = true;
                }
            }

            if (eliminado == false) {
                System.out.println(" NO TIENES DICHO SUMINISTRO. INTENTA DE NUEVO: ");
            }
        }

        return inventario;
    }

    //----------------------------------------------------------------------
    public void mostrarInventario(String[] inventario) { // OPCIÓN 3 DEL 'MENÚ DE OPCIONES'

        System.out.println(" >> INVENTARIO <<");

        for (int i = 0; i < inventario.length; i++) {
            System.out.println("  | #" + (i + 1) + " | " + inventario[i]);
        }
    }

    //----------------------------------------------------------------------
    public String[] agregarSuministro(Scanner sc, String[] inventario) {

        System.out.println(" | ESCRIBE EL SUMINISTRO QUE DESEAS AGREGAR: ");
        String agregarSuministro = sc.next().toUpperCase().trim();

        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] == null) {
                inventario[i] = agregarSuministro;
                System.out.println(" HAS AGREGADO: " + inventario[i]);
            }

        }
        return inventario;

    }

    //----------------------------------------------------------------------
    // FASE 3 - RONDAS DE ATAQUE //
    public int[] rondasAtaque(Scanner sc, int refugioElegido, int salud, int energia, String[] inventario) {

        int rondas = 1;

        Random r = new Random();

        String enemigo = "";

        if (refugioElegido == 1) { // ASIGNACIÓN DE ENEMIGOS SEGÚN REFUGIO ELEGIDO POR EL USUARIO.
            enemigo = "ALACRAN AMARILLO";
        } else if (refugioElegido == 2) {
            enemigo = "AJAW NAYAM";

        } else {
            enemigo = "VIGILIA SEPULCRAL";
        }
        //----------------------------------------------------------------------

        System.out.println(" |==============================================|");
        System.out.println("    ENFRENTAMIENTO CONTRA: " + enemigo + " ");
        System.out.println(" |==============================================|\n");

        // BUCLE DE TRES RONDAS (FASE 3)
        while (rondas <= 3 && salud > 0 && energia > 0) {

            System.out.println(" \n |    RONDA " + rondas + "/3    | ");
            energiaySalud(energia, salud);
            System.out.println(" --------------------------------------------------\n");
            System.out.println(" >>> ACCIONES DISPONIBLES <<< \n");
            System.out.println(" 1. ATACAR (-10 ENERGIA)");
            System.out.println(" 2. DEFENDER (REDUCE ATAQUE RECIBIDO)");
            System.out.println(" 3. USAR SUMINISTRO (BOTIQUIN/AGUA/COMIDA)\n");
            System.out.print(" > DECIDE TU ACCION (1,2/3) : ");

            int accion = sc.nextInt();
            System.out.println("");

            int damageRecibido = 0;
            int damageCausado = 0;

            switch (accion) {
                case 1:
                    energia -= 10;
                    damageCausado = 10 + r.nextInt(10);
                    damageRecibido = 10 + r.nextInt(15);
                    System.out.println(" ATACAS AL ENEMIGO Y LE CAUSAS " + damageCausado + " DE DAMAGE.");
                    System.out.println(" EL " + enemigo + " CONTRAATACA Y TE HACE " + damageRecibido + " DE DAMAGE.");
                    salud -= damageRecibido;
                    break;

                case 2:
                    System.out.println(" TE PREPARAS PARA BLOQUEAR EL ATAQUE.");
                    damageRecibido = 5 + r.nextInt(10);
                    salud -= damageRecibido;
                    System.out.println(" BLOQUEAS PARTE DEL GOLPE. PIERDES " + damageRecibido + " DE SALUD.");
                    energia -= 5;
                    break;

                case 3:
                    System.out.println(" | QUE SUMINISTRO QUIERES USAR? (BOTIQUIN/AGUA/COMIDA): ");
                    String usar = sc.next().toUpperCase().trim();
                    if (usar.equals("BOTIQUIN") && existeSuministro(inventario, "BOTIQUIN")) {
                        salud += 10;
                        if (salud > 100) {
                            salud = 100;
                        }
                        System.out.println(" USAS UN BOTIQUIN. TU SALUD SE RESTAURA A " + salud + "%.");
                    } else if (usar.equals("AGUA") && existeSuministro(inventario, "AGUA")) {
                        energia += 15;
                        if (energia > 100) {
                            energia = 100;
                        }
                        System.out.println(" BEBES AGUA Y RECUPERAS ENERGIA. NUEVA ENERGIA: " + energia + "%.");

                    } else if (usar.equals("COMIDA") && existeSuministro(inventario, "COMIDA")) {
                        energia += 10;
                        if (energia > 100) {
                            energia = 100;
                        }

                    } else {
                        System.out.println("NO POSEES ESE SUMINISTRO O NO ES UTIL AHORA.");
                    }

                    break;

                default:
                    System.out.println(" ACCION INVALIDA. PIERDES TIEMPO Y EL ENEMIGO TE ATACA.");
                    damageRecibido = 10 + r.nextInt(10);
                    salud -= damageRecibido;
                    System.out.println(" RECIBES " + damageRecibido + " DE DAMAGE.");
            }

            rondas++;
        }

        if (salud <= 0) {
            System.out.println(" \n (L _ L) HAS SIDO DERROTADO POR " + enemigo + ".");
            energiaySalud(energia, salud);
        } else if (energia <= 0) {
            System.out.println(" \n TE QUEDASTE SIN ENERGÍA Y NO PUEDES CONTINUAR.");
            energiaySalud(energia, salud);
        } else {
            System.out.println(" \n SOBREVIVISTE AL ATAQUE DE " + enemigo + "!");
            energiaySalud(energia, salud);
        }
        System.out.println(" --------------------------------------------------");
        return new int[]{salud, energia};

    }

    //----------------------------------------------------------------------
    public void limpiarPantalla() {

        try {
            for (int i = 0; i < 15; i++) {
                System.out.println(" * ");
                System.out.println(" / ");
                Thread.sleep(300); // 1000 = 1s ENTRE LÍNEAS.
            }
        } catch (InterruptedException e) {
            System.out.println("ERROR AL LIMPIAR PANTALLA...");
        }
        System.out.println("");
    }

    //----------------------------------------------------------------------
    public boolean disponibilidadSuministro(String[] listaDeSuministros, String suministroTemporal) {

        for (int i = 0; i < listaDeSuministros.length; i++) {
            if (suministroTemporal.equalsIgnoreCase(listaDeSuministros[i])) {
                return true;
            }

        }
        return false;
    }

    //----------------------------------------------------------------------
    //  FASE 4 - BATALLA FINAL CONTRA EL TWISTER //
    public int[] etapaFinal(int energia, int salud, int refugioElegido, String[] inventario) {

        Random r = new Random();

        int impactoRecibido;

        System.out.println("\n | LOGRASTE SOBREVIVIR LOS ATAQUES DEL GUARDIAN DEL REFUGIO, PERO EL TWISTER SE APROXIMA CADA VEZ\n | MAS RAPIDO SIN DEJARTE OPORTUNIDAD DE ESCAPAR, EL REFUGIO TIENE UNA PROTECCION ESPECIAL QUE \n | SE ACTIVARA CON UN SUMINISTRO CLAVE... TENDRAS DICHO SUMINISTRO EN TU INVENTARIO?\n");

        String suministroClave = "";
        if (refugioElegido == 1) {
            suministroClave = "BENGALA";
        } else if (refugioElegido == 2) {
            suministroClave = "LLAVE";
        } else {
            suministroClave = "LINTERNA";
        }

        boolean poseeSuministroClave = existeSuministro(inventario, suministroClave);

        if (!poseeSuministroClave) {
            System.out.println(" CARECES DEL SUMINISTRO CLAVE: " + suministroClave + "\n");
            System.out.println(" | TU REFUGIO NO PUDO ACTIVARSE A TIEMPO PARA PROTEGERTE Y RECIBES EL IMPACTO DIRECTO DEL TWISTER.");
            impactoRecibido = 50 + r.nextInt(10);
            energia -= impactoRecibido;
            salud -= impactoRecibido;
            System.out.println(" | EL TWISTER HA IMPACTADO EN EL REFUGIO Y TE HA QUITADO -" + impactoRecibido + " DE ENERGIA Y SALUD.");
            if (salud <= 0) {
                energia = 0;
                salud = 0;
            }
            if (energia <= 0 || salud <= 0) {
                System.out.println(" | NO HAS RESISTIDO AL IMPACTO FINAL DEL TWISTER, HAS MUERTO... (EL JUEGO SE TERMINA)\n");
                System.out.println(" --- HAS PERDIDO, ESPERO QUE TENGAS SUERTE EN LA PROXIMA VEZ ---\n");
                return new int[]{salud, energia};
            }
            return new int[]{salud, energia};
        }

        System.out.println(" SUMINISTRO CLAVE DETECTADO: " + suministroClave + "\n");
        System.out.println(" ACTIVAS EL MECANISMO DEL REFUGIO A TIEMPO Y EL TWISTER PASA SIN ALCANZARTE.\n");
        if (salud > 0 && energia > 0) {
            System.out.println(" +++ FELICIDADES, HAS SOBREVIVIDO A LOS COMBATES Y AL IMPACTO DE TWISTER +++\n");
        }

        return new int[]{salud, energia};
    }

    //----------------------------------------------------------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean jugar = true;
        while (jugar) { // BUCLE QUE PERMITE VOLVER A JUGAR / MEJORA PERSONAL: EL USUARIO PUEDE VOLVER A JUGAR LAS VECES QUE QUIERA.

            Principal p = new Principal();
            //----------------------------------------------------------------------
            int energia = 100; // ENERGÍA INICIAL DEL USUARIO
            int salud = 100; // SALUD INICIAL DEL USUARIO
            String[] listaDeSuministros = {"AGUA", "COMIDA", "HIERBA", "MUNICION", "CUERDA", "FOSFOROS", "LINTERNA", "BOTIQUIN", "BENGALA", "LLAVE"}; // CREO UN ARRAY DE TIPO STRING QUE ALOJA LOS SUMINISTROS PREDETERMINADOS DEL JUEGO.
            String[] inventario = new String[9]; // CREO CREO UN ARRAY DE TIPO STRING, QUE SE USARÁ COMO MOCHILA/INVENTARIO PARA LOS SUMINISTROS DEL USUARIO.
            //----------------------------------------------------------------------

            int[] resultado = p.eleccionRefugio(sc, energia, salud);

            energia = resultado[0];
            salud = resultado[1];
            int refugioElegido = resultado[2];
            p.limpiarPantalla();
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
            //----------------------------------------------------------------------

            String[] resultadoInv = p.gestionSuministros(sc, listaDeSuministros, inventario, p, refugioElegido, energia, salud); // FASE 2 -> MOSTRAR SUMINISTROS DISPONIBLES EN PANTALLA Y CARGARLOS EN EL ARRAY 'INVENTARIO'.

            if (resultadoInv == null) {
                int[] res = p.rondasAtaque(sc, refugioElegido, salud, energia, inventario);
                salud = res[0];
                energia = res[1];
            }

            p.limpiarPantalla();
            if (resultadoInv != null) {
                int opcion;
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
                        case 0:
                            System.out.println(" \n AVANZANDO A LA FASE 3... ");
                            p.limpiarPantalla();
                            int[] res = p.rondasAtaque(sc, refugioElegido, salud, energia, inventario);
                            salud = res[0];
                            energia = res[1];
                            break;
                    }
                } while (opcion != 0);
            }

            int[] ending = p.etapaFinal(energia, salud, refugioElegido, inventario);
            salud = ending[0];
            energia = ending[1];
            p.energiaySalud(energia, salud);

            System.out.print(" | JUGAR OTRA VEZ? (S/N): ");
            String r = sc.next().trim().toUpperCase();
            if (!r.equals("S")) {
                jugar = false;
            }

        }

        //----------------------------------------------------------------------
    } // FIN DE MAIN
} // FIN DE PRINCIPAL
 //----------------------------------------------------------------------
