package bowlinggame;

public class Roll {
    
    private int count;


    public Roll(final int count) {
        this.count = count;
    }


    public int getCount() {
        return count;
    }


    public void setCount(final int count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "Roll{" +
                "count=" + count +
                '}';
    }
}
