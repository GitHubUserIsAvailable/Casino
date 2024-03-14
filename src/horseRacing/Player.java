package horseRacing;

import javax.naming.Name;

public class Player {
    private Double money;
    private String name;

    public Player(Double money, String name) {
        this.money = money;
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
