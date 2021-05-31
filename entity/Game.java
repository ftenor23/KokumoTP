package TP_Bis.entity;

import java.util.Scanner;

public class Game {
    private Player playerOne;
    private Player playerTwo;
    private Scanner in;
    private boolean showTitle;

    public Game() {
        this.in=new Scanner(System.in);
        this.showTitle=true;
    }

    private boolean gameOver() {
        while(playerOne.turn(playerTwo) && playerTwo.turn(playerOne));
        return true;
    }

    private boolean playerOneLost(){
        return playerOne.getBoard().allSoldiersAreDead();
    }

    private boolean playerTwoLost(){
        return playerTwo.getBoard().allSoldiersAreDead();
    }
    private Player getPlayerOne() {
        return playerOne;
    }

    private Player getPlayerTwo() {
        return playerTwo;
    }

    private void printInstructions(){
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
        return;
    }

    public void execute(){

        int action=showOptions();

        if(action<1 || action>3){
            System.out.println("Seleccion invalida.");
            execute();
        }
        if(action==1 || action==2){
            if(action==1){
                play();
            } else {
                printInstructions();
            }
            execute();
        }
        if(action==3){
            System.out.println("Gracias por jugar, hasta luego!");
        }
        return;
    }

    private int showOptions(){
        if(showTitle) {
            System.out.println("Bienvenido a Kokumo no monogatari\n");
            this.showTitle=false;
        }
        System.out.println("Seleccione una opcion:");
        System.out.println("1) Jugar.");
        System.out.println("2) Leer las reglas del juego.");
        System.out.println("3) Salir.");
        int action = in.nextInt();
        System.out.println("");
        return action;
    }
    private void play(){
        Scanner nameIn=new Scanner(System.in);
        System.out.println("Ingrese el nombre del jugador 1: ");
        String namePlayerOne= nameIn.nextLine();
        System.out.println("Ingrese el nombre del jugador 2: ");
        String namePlayerTwo=nameIn.nextLine();
        playerOne=new Player(namePlayerOne);
        playerTwo=new Player(namePlayerTwo);

        while(!gameOver());

        if(playerOneLost()){
            System.out.println("Gano " + playerTwo.getPlayerName() + "!!");
        }
        if(playerTwoLost()){
            System.out.println("Gano " + playerOne.getPlayerName() + "!!");
        }
        return;
    }

    public static void main(String args[]) {
        Game game = new Game();
        game.execute();
    }
}
