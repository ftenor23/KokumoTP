package TP_Bis.Graphic;

public abstract class AttackGraphics extends Graphics{

    //ATTACKMANAGER
    public static void printSelectPositionToAttackMessage(){
        System.out.println("Seleccione la posicion a atacar: ");
    }

    public static void printNotValidAttack(){
        System.out.println("Ataque no valido");
    }

    public static void attackToImpassableZone(){
        System.out.println("El ataque fue dirigido a una zona intransitable, perdiste tu turno.");
    }

    public static void enemyAttacked(){
        System.out.println("Le diste a tu enemigo.");
    }

}
