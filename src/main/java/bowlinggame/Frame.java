package bowlinggame;

import java.util.List;
import static bowlinggame.Bonus.NONE;
import static bowlinggame.Bonus.STRIKE;
import static java.util.Collections.emptyList;

public class Frame {

    private Bonus bonusType;
    private int position;
    private List<Roll> rolls;
    private int score;


    public Frame() {
        this.bonusType = NONE;
        this.rolls = emptyList();
        this.score = 0;
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


    public int getScore() {
        return score;
    }


    public void setScore(final int score) {
        this.score = score;
    }


    public int getPosition() {
        return position;
    }


    public void setPosition(final int position) {
        this.position = position;
    }


    public boolean isLastFrame() {
        return position == 10;
    }

    
    public boolean isStrike() {
        return bonusType == STRIKE;
    }

    @Override
    public String toString() {
        return "Frame " + position + " :" +
                " rolls= " + rolls +
                " bonusType=" + bonusType +
                " total=" + score +
                "\n";
    }
}
