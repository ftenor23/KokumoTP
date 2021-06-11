package TP_Bis.entity;

public class Players {
    private Player hostPlayer;
    private Player clientPlayer;

    public Players(Player hostPlayer, Player clientPlayer) {
        this.hostPlayer = hostPlayer;
        this.clientPlayer = clientPlayer;
    }

    public Player getHostPlayer() {
        return hostPlayer;
    }

    public void setHostPlayer(Player hostPlayer) {
        this.hostPlayer = hostPlayer;
    }

    public Player getClientPlayer() {
        return clientPlayer;
    }

    public void setClientPlayer(Player clientPlayer) {
        this.clientPlayer = clientPlayer;
    }
}
