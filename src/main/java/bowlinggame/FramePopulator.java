package bowlinggame;

import java.util.ArrayList;
import java.util.List;
import static bowlinggame.Bonus.SPARE;
import static bowlinggame.Bonus.STRIKE;
import static bowlinggame.BowlingGameMain.TOTAL_FRAMES_COUNT;
import static bowlinggame.BowlingGameMain.TOTAL_PINS_COUNT;

public class FramePopulator {

    /**
     * Populates the frames from user input
     *
     * @return populated frames from user input
     */
    public List<Frame> populateFrames() {
        List<Frame> frames = new ArrayList<>();
        int currentFramePosition;

        do {
            currentFramePosition = frames.size() + 1;
            Frame frame = new Frame();
            frame.setPosition(currentFramePosition);

            populateFrameRolls(frame);

            frames.add(frame);
        } while (currentFramePosition < TOTAL_FRAMES_COUNT);

        return frames;
    }


    private void populateFrameRolls(final Frame frame) {
        for (int currentRollIndex = 1; currentRollIndex <= 3; currentRollIndex++) {

            final boolean hasStrikeOrSpare = frame.getBonusType() == STRIKE || frame.getBonusType() == SPARE;
            final boolean isThirdRoll = currentRollIndex == 3;

            final boolean isAllowedToRollExceptForLastFrame = !frame.isLastFrame() && !hasStrikeOrSpare && !isThirdRoll;
            final boolean isAllowedToRollForLastFrameOnly = frame.isLastFrame() && (hasStrikeOrSpare || !isThirdRoll);

            if (isAllowedToRollExceptForLastFrame || isAllowedToRollForLastFrameOnly) {
                final int input = collectUserInput(frame, currentRollIndex);
                assignBonusType(frame, input);
                addRollInputToFrame(frame, input);
            }
        }
    }


    private int collectUserInput(final Frame frame, final int currentRollIndex) {
        FrameRollCollector frameRollCollector = new FrameRollCollector();
        return frameRollCollector.collectInputFromUser(frame, currentRollIndex);
    }


    protected void assignBonusType(final Frame frame, final int input) {
        if (frame.getRolls().isEmpty()) {
            handleFirstRoll(frame, input);
        } else if (frame.getBonusType() != STRIKE) {
            handleSecondRoll(frame, input);
        }
    }


    private void handleFirstRoll(final Frame frame, final int input) {
        final boolean isStrike = input == TOTAL_PINS_COUNT;
        if (isStrike) {
            frame.setBonusType(STRIKE);
        }
    }


    private void handleSecondRoll(final Frame frame, final int input) {
        final int firstRollCount = frame.getRolls().get(0).getScore();
        final boolean isSpare = (firstRollCount + input) == 10;
        if (isSpare) {
            frame.setBonusType(SPARE);
        }
    }


    protected void addRollInputToFrame(final Frame frame, final int input) {
        final Roll currentRoll = new Roll(input);

        if (frame.getRolls().isEmpty()) {
            frame.setRolls(List.of(currentRoll));
        } else {
            List<Roll> rolls = new ArrayList<>(frame.getRolls());
            rolls.add(currentRoll);
            frame.setRolls(rolls);
        }
    }
}
