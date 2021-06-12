package TP_Bis.entity;

import TP_Bis.Graphic.Graphics;
import TP_Bis.Manager.PlayerManager;
import TP_Bis.Server_Client.Client;
import TP_Bis.Server_Client.Server;


import java.io.IOException;
import java.util.Scanner;

public class Game {
    private Player playerOne;
    private Player playerTwo;
    private PlayerManager playerManager;
    private boolean showTitle;
    private final static int PLAY_AS_HOST = 1;
    private final static int PLAY_AS_CLIENT=2;
    private final static int SHOW_INSTRUCTIONS=3;
    private final static int EXIT_GAME=4;

    public Game() {
        this.showTitle=true;
        playerManager=new PlayerManager();
        /*try{
            Server server = new Server();
        }catch(IOException e){
            System.out.println(e.getLocalizedMessage());
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }*/
    }

    private boolean gameOver() {
       // while(!playerOne.turn(playerTwo) && !playerTwo.turn(playerOne));
        while(!playerManager.turn(playerOne, playerTwo) && !playerManager.turn(playerTwo,playerOne));
        return true;
    }

    private boolean playerOneLost(){
        return playerManager.mySoldiersAreDead(playerOne);
    }

    private boolean playerTwoLost(){
        return playerManager.mySoldiersAreDead(playerTwo);
    }
    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
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

        if(action<PLAY_AS_HOST || action>EXIT_GAME){
            System.out.println("Seleccion invalida.");
            execute();
        }
        if(action!=EXIT_GAME){
            if(action==PLAY_AS_HOST){
                //playAsHost();
            }if (action==PLAY_AS_CLIENT){
                //playAsClient();
            } if(action==SHOW_INSTRUCTIONS) {
                printInstructions();
            }
            execute();
        }
        if(action==EXIT_GAME){
            System.out.println("Gracias por jugar, hasta luego!");
        }
        return;
    }

    private int showOptions(){
        Scanner in = new Scanner(System.in);
        if(showTitle) {
            System.out.println("Bienvenido a Kokumo no monogatari\n");
            this.showTitle=false;
        }
        printOptions();
        int action = in.nextInt();
        System.out.println("");
        return action;
    }

    private void printOptions(){
        System.out.println("Seleccione una opcion:");
        System.out.println("1) Jugar como Host.");
        System.out.println("2) Jugar como invitado");
        System.out.println("3) Leer las reglas del juego.");
        System.out.println("4) Salir.");
    }
    /*private void play(){
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
    }*/

   /* public void playAsHost(){
        Server server = new Server();
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void playAsClient(){
        Client client = new Client();
        try {
            client.run();
        } catch (IOException e) {
            Graphics.printException(e);
        }
    }*/
    public static void main(String args[]) {


        Game game = new Game();
        game.execute();
    }
}
