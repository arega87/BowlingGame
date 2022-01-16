package bowlinggame;

import java.util.List;
import static bowlinggame.Bonus.NONE;
import static java.util.Collections.emptyList;

public class Frame {

    private Bonus bonusType;
    private List<Roll> rolls;
    private int total;


    public Frame() {
        this.bonusType = NONE;
        this.rolls = emptyList();
        this.total = 0;
    }


    public Bonus getBonusType() {
        return bonusType;
    }


    public void setBonusType(final Bonus bonusType) {
        this.bonusType = bonusType;
    }


    public List<Roll> getRolls() {
        return rolls;
    }


    public void setRolls(final List<Roll> rolls) {
        this.rolls = rolls;
    }


    public int getTotal() {
        return total;
    }


    public void setTotal(final int total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "Frame{" +
                "bonusType=" + bonusType +
                ", rolls=" + rolls +
                ", total=" + total +
                '}';
    }
}
