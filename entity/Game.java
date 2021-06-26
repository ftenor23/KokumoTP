package TP_Bis.entity;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.ClientGraphics;
import TP_Bis.Graphic.Graphics;
import TP_Bis.Graphic.ConnectionGraphics;
import TP_Bis.Manager.GameManager;
import TP_Bis.Manager.PlayerManager;


import TP_Bis.validator.IPValidator;

public class Game {
    private Player playerOne;
    private Player playerTwo;
    private PlayerManager playerManager;
    private boolean showTitle;
    private final static int PLAY_AS_HOST = 1;
    private final static int PLAY_AS_CLIENT=2;
    private final static int SINGLE_PLAY=3;
    private final static int SHOW_INSTRUCTIONS=4;
    private final static int EXIT_GAME=5;

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
                play(true);
                //singlePlay();
            }if (action==PLAY_AS_CLIENT){
                play(false);
                /*Graphics.thanksForPlaying();
                return;*/
            }if(action==SINGLE_PLAY){
                singlePlay();
            }
            if(action==SHOW_INSTRUCTIONS) {
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
    private void singlePlay(){
        final int pOne=1;
        final int pTwo=2;
        Graphics.enterPlayerName(pOne);
        String namePlayerOne= EnterData.nextLine();
        Graphics.enterPlayerName(pTwo);
        String namePlayerTwo=EnterData.nextLine();
        playerOne=new Player(namePlayerOne);
        playerTwo=new Player(namePlayerTwo);

        while(!gameOver());

        if(playerOneLost()){
            Graphics.playerWon(playerTwo.getPlayerName());

        }
        if(playerTwoLost()){
            Graphics.playerWon(playerOne.getPlayerName());

        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return;
    }

   /*public void playAsHost(){



        Server server = new Server();
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public void play(boolean runAsHost){
        GameManager gameManager = new GameManager();
        boolean ipIsValid=false;
        String oponentIp=new String();
        while(!ipIsValid) {
            ConnectionGraphics.enterOpponentIp();
            oponentIp = EnterData.nextLine();
            ipIsValid= IPValidator.ipIsValid(oponentIp);
            if(!ipIsValid){
                ClientGraphics.ipNotValid(oponentIp);
            }
        }
        gameManager.startGame(runAsHost,oponentIp);
        /*Client client = new Client();
        try {
            client.run();
        } catch (IOException e) {
            Graphics.printException(e);
        }*/
    }
    public static void main(String args[]) {


        Game game = new Game();
        game.execute();
    }
}
