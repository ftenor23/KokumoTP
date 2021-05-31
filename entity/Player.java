package TP_Bis.entity;

import java.util.Scanner;

public class Player {
    private Board myBoard;
    private Board enemyBoard;
    private String playerName;
    private Scanner in;
    private boolean firstTurn;
    private boolean commanderIsDead;
    //si el comandante muere, los demas no pueden moverse

    public Player(String name) {
        this.playerName = name;
        this.myBoard = new Board();
        this.firstTurn=true;
        this.commanderIsDead=false;
        in = new Scanner(System.in);
    }

    public boolean turn(Player enemy) {
        this.enemyBoard = enemy.getBoard();

        System.out.println("Turno de " + this.playerName + "\n");
        if (firstTurn) {
            setSoldiers();
        } else {
            this.commanderIsDead = myBoard.commanderIsDead();

            for (int i = 0; i < myBoard.getNumberOfSoldiers(); i++) {
                int action = -1;
                int id = i+1;
                while (action != 1 && action != 2) {
                    if (!myBoard.getSoldier(id).isDead()) {
                        System.out.println("Ingrese el movimiento a realizar con el soldado nro " + id + ": ");
                        System.out.println("1) Atacar");
                        if (!commanderIsDead && myBoard.getSoldier(id).canMove()) {
                            System.out.println("2) Mover soldado");
                        }

                        action = in.nextInt();
                        if (action != 1 || action != 2) {
                            if ((commanderIsDead||!myBoard.getSoldier(id).canMove()) && action == 2) {
                                action = -1;
                                System.out.println("OPCION INVALIDA");
                            }
                        }


                        switch (action) {
                            case 1:
                                attackEnemy(enemyBoard, id);
                                if(enemy.getBoard().allSoldiersAreDead()){
                                    return false;
                                }
                                break;
                            case 2:
                                moveSoldier(id);
                                break;
                            default:
                                break;
                        }

                    }
                    action=1; //cambiamos el valor a uno que nos haga salir del while

                }

            }
            printMyBoard();
            printEnemyBoard(enemyBoard);

        }
        return true;
    }

    private void printMyBoard() {
        System.out.println("Mi tablero: ");
        this.myBoard.printOwnBoard();
    }

    public Board getBoard(){
        return this.myBoard;
    }

    private void printEnemyBoard(Board enemyBoard){
        this.enemyBoard=enemyBoard;
        System.out.println("\nTablero enemigo");
        enemyBoard.showEnemyBoard();
    }

    private void setSoldiers(){
        System.out.println("Posicionar soldados:\n"); //todo iria dentro de soldierManager
        int positionOne=-1;
        int positionTwo=-1;
        int positionThree=-1;
        boolean arrayOutOfBounds = true;
        while(arrayOutOfBounds) {
            System.out.println("Ingrese la posicion del comandante: ");
            positionOne = in.nextInt();
            arrayOutOfBounds = arrayOutOfBounds(positionOne); //positionValidator
            if(arrayOutOfBounds) {
                System.out.println("Posicion fuera de los limites. Ingrese un numero entre 1 y " + myBoard.getMatrix().length);
            }
        }
        arrayOutOfBounds = true;
        while (arrayOutOfBounds) {
            System.out.println("Ingrese la posicion del soldado numero 1: ");
            positionTwo = in.nextInt();
            arrayOutOfBounds = arrayOutOfBounds(positionTwo);
            if(arrayOutOfBounds) {
                System.out.println("Posicion fuera de los limites. Ingrese un numero entre 1 y " + myBoard.getMatrix().length);
            }
        }
        arrayOutOfBounds=true;
        while(arrayOutOfBounds) {
            System.out.println("Ingrese la posicion del soldado numero 2: ");
            positionThree = in.nextInt();
            arrayOutOfBounds=arrayOutOfBounds(positionThree);
            if(arrayOutOfBounds){
                System.out.println("Posicion fuera de los limites. Ingrese un numero entre 1 y " + myBoard.getMatrix().length);
            }
        }
        myBoard.setSoldiers(positionOne, positionTwo, positionThree, firstTurn);
        /*try {

        }catch (ArrayIndexOutOfBoundsException e){
            while(arrayOutOfBounds(positionOne,positionTwo,positionThree)) {
                if (positionOne < 1 || positionOne > 25) {
                    System.out.println("Posicion uno invalida. Vuelva a ingresar un numero entre 1 y " + myBoard.getMatrix().length + ": ");
                    positionOne = in.nextInt();
                    while(positionOne==positionTwo || positionOne==positionThree){
                        System.out.println("Posicion ocupada por otro ninja. Vuelva a ingresar un numero entre 1 y " + myBoard.getMatrix().length + ": ");
                        positionOne = in.nextInt();
                    }
                }
                if (positionTwo < 1 || positionTwo > 25) {
                    System.out.println("Posicion dos invalida. Vuelva a ingresar un numero entre 1 y " + myBoard.getMatrix().length + ": ");
                    positionTwo = in.nextInt();
                    while(positionOne==positionTwo || positionTwo==positionThree){
                        System.out.println("Posicion ocupada por otro ninja. Vuelva a ingresar un numero entre 1 y " + myBoard.getMatrix().length + ": ");
                        positionTwo = in.nextInt();
                    }
                }
                if (positionThree < 1 || positionThree > 25) {
                    System.out.println("Posicion tres invalida. Vuelva a ingresar un numero entre 1 y " + myBoard.getMatrix().length + ": ");
                    positionThree = in.nextInt();
                    while(positionThree==positionTwo || positionOne==positionThree){
                        System.out.println("Posicion ocupada por otro ninja. Vuelva a ingresar un numero entre 1 y " + myBoard.getMatrix().length + ": ");
                        positionThree = in.nextInt();
                    }
                }
            }
            myBoard.setSoldiers(positionOne, positionTwo, positionThree, firstTurn);
        }*/
        firstTurn=false;
    }


    private boolean arrayOutOfBounds(int positionOne, int positionTwo, int positionThree){
        if(positionOne<1 || positionOne>25 || positionTwo<1 || positionTwo>25 || positionThree<1 || positionThree>25){
            return true;
        }
        return false;
    }

    private boolean arrayOutOfBounds(int position) {
        return position<0 || position>myBoard.getMatrix().length;
    }
    private void attackEnemy(Board enemyBoard, int id){
        System.out.println("Seleccione la posicion a atacar: ");
        int position = in.nextInt()-1;
        if(!myBoard.getSoldier(id).canMove()){
            myBoard.getSoldier(id).setCanMove(true);
        }
        if(enemyBoard.getMatrix()[position].isImpassable()){
            System.out.println("El ataque fue dirigido a una zona intransitable, perdiste tu turno.");
        }
        if(enemyBoard.getMatrix()[position].isOccupied()){
            System.out.println("Le diste a tu enemigo.");
        }
        enemyBoard.attackReceived(position);
    }

    private void moveSoldier(int id){

        int position= myBoard.getSoldierPosition(id);

        if(position<0){
            System.out.println("Posicion invalida"); //cargar error
            return;
        }
        //creamos un ciclo hasta que se cumpla la condicion
        int move=-1;
        while(move<1 || move>4){
            System.out.println("Ingrese el tipo de movimiento a realizar: ");
            System.out.println("1)Mover arriba");
            System.out.println("2)Mover abajo");
            System.out.println("3)Mover a la izquierda");
            System.out.println("4)Mover a la derecha");
            move = in.nextInt();
            if(move<1 || move>4){
                System.out.println("OPCION INVALIDA");
            }
            /*1=arriba
            2=abajo
            3=izquierda
            4=derecha
         */}
        move(position, myBoard.getSoldier(id),move);
        myBoard.getSoldier(id).setCanMove(false); //modificar para que cuando pase un turno pueda moverse
    }

    private void move(int position, Soldier soldier, int move){
        //verificar funcion
        int newPosition=-1;
        switch (move){
            case 1:
                newPosition=position-5; //se mueve hacia arriba
                break;
            case 2:
                newPosition=position+5; //se mueve hacia abajo
                break;
            case 3:
                newPosition=position-1;
                break;
            case 4:
                newPosition=position+1;
                break;
            default:
                System.out.println("Error"); //guardar errror
                break;
        }
        if(newPosition<0 || newPosition>myBoard.getMatrix().length){
            System.out.println("Movimiento invalido");
            return;
        }
        if(myBoard.getMatrix()[newPosition].isOccupied()) {
            System.out.println("Posicion ocupada.");
            return;
        }
        if(myBoard.getMatrix()[newPosition].isImpassable()){
            System.out.println("Posicion intransitable.");
            return;
        }
            myBoard.getMatrix()[position].emptyGrid();
            myBoard.setSoldierPosition(newPosition, soldier,firstTurn);
            return;
        }

    public String getPlayerName() {
        return playerName;
    }
}
