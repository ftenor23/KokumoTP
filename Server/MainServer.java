package TP_Bis.Server;

public class MainServer {
    public static void main(String args[]){
        try{
            Server s = new Server(8000);

        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }


    }
}
