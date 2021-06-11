package TP_Bis.Manager;

import TP_Bis.entity.Player;

public class GameManager {
    private Player host;
    private Player client;
    private PlayerManager playerManager=new PlayerManager();

    public GameManager(Player host, Player client){
        this.host=host;
        this.client=client;
    }

    public boolean hostWon(){
        return playerManager.enemiesAreDead(client);
    }

    public boolean clientWon(){
        return playerManager.enemiesAreDead(host);
    }
}
