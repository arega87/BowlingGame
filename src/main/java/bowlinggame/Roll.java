package bowlinggame;

public class Roll {

    private int score;


    public Roll(final int count) {
        this.score = count;
    }


    public int getScore() {
        return score;
    }


    public void setScore(final int score) {
        this.score = score;
    }


    @Override
    public String toString() {
        return String.valueOf(score);
    }
}
