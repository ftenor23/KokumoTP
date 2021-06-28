package TP_Bis.entity;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.GameGraphics;
import TP_Bis.Graphic.Graphics;
import TP_Bis.Graphic.ConnectionGraphics;
import TP_Bis.Manager.GameManager;
import TP_Bis.Manager.PlayerManager;


import TP_Bis.validator.IPValidator;

import java.net.BindException;

public class Game {
    private PlayerManager playerManager;
    private boolean showTitle;
    private final static int CREATE_GAME = 1;
    private final static int JOIN_GAME =2;
    private final static int SINGLE_PLAY=3;
    private final static int SHOW_INSTRUCTIONS=4;
    private final static int EXIT_GAME=5;

    public Game() {
        this.showTitle=true;
        playerManager=new PlayerManager();
    }

    private boolean gameOver(Player playerOne, Player playerTwo) {
       // while(!playerOne.turn(playerTwo) && !playerTwo.turn(playerOne));
        while(!playerManager.turn(playerOne, playerTwo) && !playerManager.turn(playerTwo,playerOne));
        return true;
    }

    private boolean playerOneLost(Player playerOne){
        return playerManager.mySoldiersAreDead(playerOne);
    }

    private boolean playerTwoLost(Player playerTwo){
        return playerManager.mySoldiersAreDead(playerTwo);
    }

    private void printInstructions(){
        GameGraphics.printInstructions();
    }


    public void execute(){

        int action=showOptions();

        if(action< CREATE_GAME || action>EXIT_GAME){
            Graphics.invalidSelection();
            execute();
        }
        if(action!=EXIT_GAME){
            if(action== CREATE_GAME){
                playOnline(true);
            }if (action== JOIN_GAME){
                playOnline(false);
            }if(action==SINGLE_PLAY){
                singlePlay();
            }
            if(action==SHOW_INSTRUCTIONS) {
                printInstructions();
            }
            if(action!=SHOW_INSTRUCTIONS){
                GameGraphics.cleanConsole();
            }
            execute();
        }
        if(action==EXIT_GAME){
            GameGraphics.thanksForPlaying();
        }
        return;
    }

    private int showOptions(){
        if(showTitle) {
            GameGraphics.showTitle();
            this.showTitle=false;
        }
        printOptions();
        int action = EnterData.nextInt();
        Graphics.printLine();
        return action;
    }

    private void printOptions(){
        GameGraphics.printOptions();
    }
    private void singlePlay(){
        final int pOne=1;
        final int pTwo=2;
        GameGraphics.enterPlayerName(pOne);
        String namePlayerOne= EnterData.nextLine();
        GameGraphics.enterPlayerName(pTwo);
        String namePlayerTwo=EnterData.nextLine();
        Player playerOne=new Player(namePlayerOne);
        Player playerTwo=new Player(namePlayerTwo);

        while(!gameOver(playerOne, playerTwo));

        if(playerOneLost(playerOne)){
            GameGraphics.playerWon(playerTwo.getPlayerName());

        }
        if(playerTwoLost(playerTwo)){
            GameGraphics.playerWon(playerOne.getPlayerName());

        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return;
    }


    public void playOnline(boolean runAsHost){
        GameManager gameManager = new GameManager();
        boolean ipIsValid=false;
        String oponentIp="";
        while(!ipIsValid) {
            //pedimos la ip del oponente
            ConnectionGraphics.enterEnemyIp(runAsHost);
            oponentIp = EnterData.nextLine();
            //verificamos si la ip es valida
            ipIsValid= IPValidator.ipIsValid(oponentIp);
            if(!ipIsValid){
                ConnectionGraphics.ipNotValid(oponentIp);
            }
        }
        try {
            gameManager.startGame(runAsHost, oponentIp);
        }catch (BindException e){
            Graphics.printException(e);
        }catch(Exception e){
            Graphics.printException(e);
        }
    }
    public static void main(String args[]) {


        Game game = new Game();
        game.execute();
    }
}
