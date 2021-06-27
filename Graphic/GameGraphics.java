package TP_Bis.Graphic;

public abstract class GameGraphics extends Graphics{
    public static void enterPlayerName(int id){
        System.out.println("Ingrese el nombre del jugador numero " + id + ": ");
    }

    public static void playerWon(String playerName){
        System.out.println("Gano " + playerName + "!");
    }

    public static void thanksForPlaying(){
        System.out.println("Gracias por jugar, hasta luego!");
    }

    public static void showTitle(){
        System.out.println("Bienvenido a Kokumo no monogatari\n");
    }

    public static void printOptions() {
        System.out.println("Seleccione una opcion:");
        System.out.println("1) Crear una partida");
        System.out.println("2) Unirse a una partida");
        System.out.println("3) Jugar en la misma pc");
        System.out.println("4) Leer las reglas del juego.");
        System.out.println("5) Salir.");
    }


    public static void printInstructions(){
        System.out.println("Reglas del juego:\n");
        System.out.println("1) Al inicio de la partida, cada jugador deberá posicionar sus tres ninjas dentro de una\ncuadrícula de 5 x 5 casilleros.");
        System.out.println("2) En ningún momento el oponente deberá conocer las ubicaciones de las tropas enemigas;\n cada jugador verá dos cuadrículas:");
        System.out.println("    a.La cuadrícula principal, en la que se hallan desplegados sus ninjas.");
        System.out.println("    b.La cuadrícula secundaria, en la que se marcarán los ataques realizados contra su oponente.");
        System.out.println("3) Una vez dispuestos los guerreros en el campo de batalla, se debe decidir qué jugador \niniciará la partida para, luego, cederle el turno al contrincante (y así sucesivamente).");
        System.out.println("4) En su turno, cada jugador podrá realizar una de las siguientes acciones por cada ninja \nvivo:");
        System.out.println("\ta.Moverlo 1 casillero: en cualquier dirección y con la limitante de no poder optar \npor esta maniobra en turnos consecutivos.");
        System.out.println("\tb.Realizar un ataque: indicar la coordenada del terreno enemigo, al cual dirigirá el ataque.");
        System.out.println("5) Si el ataque del oponente es dirigido hacia una casilla ocupada por un ninja, éste pierde \nla vida; en cambio, si está despoblada, el terreno queda intransitable.");
        System.out.println("6) La partida termina cuando uno de los dos jugadores elimina a la facción enemiga.\n");
        System.out.println("Consideraciones:\n");
        System.out.println("-Uno de los tres ninjas será el comandante del escuadrón y poseerá dos vidas.");
        System.out.println("-Todo ninja puede moverse siempre y cuando el comandante siga con vida.\n");
    }

    public static void cleanConsole(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            Graphics.printException(e);
        }
    }
}
