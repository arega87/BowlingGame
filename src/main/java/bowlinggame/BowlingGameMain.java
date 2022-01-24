package bowlinggame;

import java.util.List;

public class BowlingGameMain {
    
    public static final int TOTAL_PINS_COUNT = 10;
    public static final int TOTAL_FRAMES_COUNT = 10;
    
    public static final int NUMBER_OF_ROLLS_TO_CONSIDER_FOR_STRIKE = 2;
    public static final int NUMBER_OF_ROLLS_TO_CONSIDER_FOR_SPARE = 1;


    public static void main(String[] args) {
        try {
            final FramePopulator framePopulator = new FramePopulator();
            final List<Frame> populatedFrames = framePopulator.populateFrames();

            final FrameScoreCalculator frameScoreCalculator = new FrameScoreCalculator();
            final List<Frame> framesWithScoresCalculated = frameScoreCalculator.calculateScore(populatedFrames);
            
            System.out.println(framesWithScoresCalculated);
            
        } catch (Exception e) {
            System.out.printf("Something went wrong, the application has stopped.\n Reason: %s", e.getMessage());
        }
    }
}
