package bowlinggame;

import java.util.List;
import static bowlinggame.Bonus.NONE;
import static bowlinggame.BowlingGameMain.NUMBER_OF_ROLLS_TO_CONSIDER_FOR_SPARE;
import static bowlinggame.BowlingGameMain.NUMBER_OF_ROLLS_TO_CONSIDER_FOR_STRIKE;
import static java.lang.Math.min;

public class FrameScoreCalculator {


    /**
     * Goes through all frames again to calculate the scores with bonuses included
     *
     * @param allFrames previously populated frames from user input
     * @return updated frames with calculated bonuses
     */
    public List<Frame> calculateScore(final List<Frame> allFrames) {
        int previousFrameScoreToUpdate = 0;

        for (Frame currentFrame : allFrames) {
            final int currentFrameRollsTotal = calculateCurrentFrameRollsTotal(currentFrame);

            if (!currentFrame.isLastFrame() && currentFrame.getBonusType() != NONE) {
                currentFrame.setScore(calculateCurrentFrameRollsTotal(allFrames, previousFrameScoreToUpdate, currentFrame, currentFrameRollsTotal));
            } else {
                currentFrame.setScore(previousFrameScoreToUpdate + currentFrameRollsTotal);
            }

            previousFrameScoreToUpdate = currentFrame.getScore();
        }

        return allFrames;
    }


    private int calculateCurrentFrameRollsTotal(
            final List<Frame> allFrames,
            final int previousFrameScoreToUpdate,
            final Frame currentFrame,
            final int currentFrameRollsSum
    ) {
        final List<Roll> nextFrameRolls = allFrames.get(currentFrame.getPosition()).getRolls();
        int calculatedBonus = currentFrame.isStrike() ? calculateBonusForStrike(nextFrameRolls) : calculateBonusForSpare(nextFrameRolls);
        
        return previousFrameScoreToUpdate + currentFrameRollsSum + calculatedBonus;
    }


    private int calculateBonusForSpare(final List<Roll> nextFrameRolls) {
        return calculateBonus(nextFrameRolls, NUMBER_OF_ROLLS_TO_CONSIDER_FOR_SPARE);
    }


    private int calculateBonusForStrike(final List<Roll> nextFrameRolls) {
        return calculateBonus(nextFrameRolls, NUMBER_OF_ROLLS_TO_CONSIDER_FOR_STRIKE);
    }


    private int calculateBonus(final List<Roll> nextFrameRolls, final int numberOfRollsToConsider) {
        int cumulatedScore = 0;
        int iterationCount = 0;

        int effectiveRollsToConsider = min(numberOfRollsToConsider, nextFrameRolls.size());

        for (Roll roll : nextFrameRolls) {
            if (iterationCount >= effectiveRollsToConsider) {
                break;
            }

            cumulatedScore = cumulatedScore + roll.getScore();
            iterationCount++;
        }

        return cumulatedScore;
    }


    private int calculateCurrentFrameRollsTotal(final Frame currentFrame) {
        return currentFrame.getRolls().stream()
                .map(Roll::getScore)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
