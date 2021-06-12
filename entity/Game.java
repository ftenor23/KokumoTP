package TP_Bis.entity;

import TP_Bis.DataIn.EnterData;
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
        Graphics.printInstructions();
    }


    public void execute(){

        int action=showOptions();

        if(action<PLAY_AS_HOST || action>EXIT_GAME){
            Graphics.invalidSelection();
            execute();
        }
        if(action!=EXIT_GAME){
            if(action==PLAY_AS_HOST){
                playAsHost();
            }if (action==PLAY_AS_CLIENT){
                playAsClient();
            } if(action==SHOW_INSTRUCTIONS) {
                printInstructions();
            }
            execute();
        }
        if(action==EXIT_GAME){
            Graphics.thanksForPlaying();
        }
        return;
    }

    private int showOptions(){
        if(showTitle) {
            Graphics.showTitle();
            this.showTitle=false;
        }
        printOptions();
        int action = EnterData.nextInt();
        Graphics.printLine();
        return action;
    }

    private void printOptions(){
        Graphics.printOptions();
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

   public void playAsHost(){
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
    }
    public static void main(String args[]) {


        Game game = new Game();
        game.execute();
    }
}
