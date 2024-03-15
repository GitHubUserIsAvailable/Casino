package horseRacing;

public class GameData {

    private int antwortWetten;


    private int antwortWettbetrag;

    private Player player1 = new Player("Saschalein");

    public int getAntwortWetten() {
        return antwortWetten;
    }

    public void setAntwortWetten(int antwortWetten) {
        this.antwortWetten = antwortWetten;
    }

    public int getAntwortWettbetrag() {
        return antwortWettbetrag;
    }

    public void setAntwortWettbetrag(int antwortWettbetrag) {
        this.antwortWettbetrag = antwortWettbetrag;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
}
