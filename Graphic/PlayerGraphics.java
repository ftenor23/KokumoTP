package TP_Bis.Graphic;

import TP_Bis.entity.Player;

public class PlayerGraphics extends Graphics{
    public static void playerTurn(Player player){
        System.out.println("Turno de " + player.getPlayerName() + "\n");
    }

    public static void selectMoveMenu(int id){
        System.out.println("Ingrese el movimiento a realizar con el soldado nro " + id + ": ");
    }

    public static void attack(){
        System.out.println("1) Atacar");
    }

    public static void moveSoldier(){
        System.out.println("2) Mover soldado");
    }
}
