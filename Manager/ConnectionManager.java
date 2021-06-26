package TP_Bis.Manager;

//public class ConnectionManager {
   /* public static boolean hostWon(Player host, Player client){
        GameManager gameManager=new GameManager(host, client);
        return gameManager.hostWon();
    }*/

   /* public static void gameOver(Player host, Player client, Data data, Gson gson, String str){
        ServerGraphics.gameOver();
        if(hostWon(host,client)){
            ServerGraphics.victory();
        }
        else{
            ServerGraphics.lose(client.getPlayerName());
        }

        data.setHostBoard(host.getBoard());
        data.setClientBoard(client.getBoard());
        data.setGameOver(true);

        str = gson.toJson(data);
//ENVIAR MENSAJE AL SERVER
        output.println(str);
        ServerGraphics.serverClosing();
        try{
            Thread.sleep(10000);
        }catch(Exception e){
            e.getLocalizedMessage();
        }


    }

    public void execute(Player host, Player enemy, Client client) throws IOException {
        boolean firstTurn=true;
        boolean gameOver = false;
        Data data = new Data();
        PlayerManager playerManager = new PlayerManager();
        Gson gson = new Gson();
        while (true) {

            String str = waitingOponent(client);

            data = gson.fromJson(str, Data.class);
            host.setMyBoard(data.getHostBoard());
            enemy.setMyBoard(data.getClientBoard());

            gameOver = data.isGameOver();

            if (gameOver){
                gameOver(host,enemy,data,gson,str,output);
                return;
            }


            server.setMessage("waiting");
            gameOver = playerManager.turn(host,enemy);
            if(gameOver){
                gameOver(host,enemy,data,gson,str,output);
                return;
            }
            data.setHostBoard(host.getBoard());
            data.setClientBoard(enemy.getBoard());
            data.setGameOver(gameOver);

            str = gson.toJson(data);

            server.setMessage(str);
            ServerGraphics.waitingEnemyTurn(enemy.getPlayerName());

        }
    }

    public String waitingOponent(Client client){
        String response=null;
        try{
            response = client.receiveMessage();
            while(response.equals("waiting")){//espero a que cambie el estado en server cliente
                try{
                    Thread.sleep(500);
                    response = client.receiveMessage();
                }catch(Exception e) {
                    Graphics.printException(e);
                }
            }
        }catch (Exception e){
            Graphics.printException(e);
        }
        return response;
    }

    /*public static void gameOver(Player host, Player client, Data data, Gson gson, String str, PrintWriter output){
        ServerGraphics.gameOver();
        if(hostWon(host,client)){
            ServerGraphics.victory();
        }
        else{
            ServerGraphics.lose(client.getPlayerName());
        }

        data.setHostBoard(host.getBoard());
        data.setClientBoard(client.getBoard());
        data.setGameOver(true);

        str = gson.toJson(data);

        output.println(str);
        ServerGraphics.serverClosing();
        try{
            Thread.sleep(10000);
        }catch(Exception e){
            e.getLocalizedMessage();
        }


    }

    public static void execute(BufferedReader input, Player host, Player client,
                                PrintWriter output) throws IOException {
        boolean firstTurn=true;
        boolean gameOver = false;
        Data data = new Data();
        PlayerManager playerManager = new PlayerManager();
        while (true) {

            Gson gson = new Gson();
            String str = input.readLine();

            data = gson.fromJson(str, Data.class);
            host.setMyBoard(data.getHostBoard());
            client.setMyBoard(data.getClientBoard());

            gameOver = data.isGameOver();

            if (gameOver){
                gameOver(host,client,data,gson,str,output);
                return;
            }

            if(firstTurn) {
                data.setHostName(host.getPlayerName());
                client.setPlayerName(data.getClientName());
                firstTurn = false;
            }

            gameOver = playerManager.turn(host,client);
            if(gameOver){
                gameOver(host,client,data,gson,str,output);
                return;
            }
            data.setHostBoard(host.getBoard());
            data.setClientBoard(client.getBoard());
            data.setGameOver(gameOver);

            str = gson.toJson(data);

            output.println(str);
            ServerGraphics.waitingEnemyTurn(client.getPlayerName());

        }
    }*/
//}
