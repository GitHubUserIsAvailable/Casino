package horseRacing;

import javax.naming.Name;

public class Player {
    private int cash;

    private String name;

    public Player(String name) {
        this.cash = 1000;
        this.name = name;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
