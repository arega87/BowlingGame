package bowlinggame;

import java.util.List;

public class BowlingGameMain {

    public static void main(String[] args) {
        try {
            final FramePopulator framePopulator = new FramePopulator();
            final List<Frame> framesWithoutBonuses = framePopulator.populateFrames();

            final BonusCalculator bonusCalculator = new BonusCalculator();
            final List<Frame> framesIncludingBonuses = bonusCalculator.calculateBonuses(framesWithoutBonuses);
            System.out.println(framesIncludingBonuses);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
