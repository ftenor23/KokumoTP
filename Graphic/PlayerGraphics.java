package TP_Bis.Graphic;

import TP_Bis.entity.Player;

public class PlayerGraphics extends Graphics{
    public static void playerTurn(Player player){
        System.out.println("Turno de " + player.getPlayerName() + "\n");
    }

    public static void selectMoveMenu(Player player,int id){
        System.out.println("Ingrese el movimiento a realizar con el soldado nro " + id + ": ");
        printSoldierPosition(player,id);
    }

    public static void attack(){
        System.out.println("1) Atacar");
    }

    public static void moveSoldier(){
        System.out.println("2) Mover soldado");
    }

    public static void informCommanderIsDead(){
        System.out.println("Tu comandante esta muerto. El o los soldados restantes" +
                " no pueden moverse.");
    }

    public static void printSoldierPosition(Player player, int id){
        System.out.println("El soldado se encuentra en la posicion " + (player.getBoard().getSoldier(id).getPosition()+1));
    }

    public static void soldierWasAttacked( int id){
        //El id 0 corresponde al comandante
        if(id==1) {
            System.out.println("El comandante fue atacado.");

        }else{
            System.out.println("El soldado " + (id) + " murio.");
        }
        return;
    }
}
