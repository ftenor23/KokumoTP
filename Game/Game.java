package TP_Bis.Game;

import TP_Bis.DataIn.EnterData;
import TP_Bis.Graphic.GameGraphics;
import TP_Bis.Graphic.Graphics;
import TP_Bis.Manager.GameManager;
import TP_Bis.Manager.PlayerManager;


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
        GameManager gameManager=new GameManager();
        gameManager.singlePlay();
    }


    public void playOnline(boolean runAsHost){
        GameManager gameManager = new GameManager();
        String oponentIp=gameManager.enterIp(runAsHost);
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
