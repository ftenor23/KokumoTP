package TP_Bis.Graphic;

public class AttackGraphics extends Graphics{

    //ATTACKMANAGER
    public void printSelectPositionToAttackMessage(){
        System.out.println("Seleccione la posicion a atacar: ");
    }

    public void printNotValidAttack(){
        System.out.println("Ataque no valido");
    }

    public void attackToImpassableZone(){
        System.out.println("El ataque fue dirigido a una zona intransitable, perdiste tu turno.");
    }

    public void enemyAttacked(){
        System.out.println("Le diste a tu enemigo.");
    }

}
