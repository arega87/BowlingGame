package bowlinggame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static bowlinggame.Bonus.SPARE;
import static bowlinggame.Bonus.STRIKE;
import static java.util.Arrays.asList;

public class FramePopulator {


    /**
     * Populates the frames from user input
     *
     * @return populated frames from user input
     */
    public List<Frame> populateFrames() {
        List<Frame> frames = new ArrayList<>();

        do {
            Frame frame = buildFrame(frames);
            frames.add(frame);
        } while (frames.size() < 10);

        return frames;
    }


    private Frame buildFrame(final List<Frame> frames) {
        Frame frame = new Frame();

        for (int index = 1; index <= 3; index++) {
            if (isThirdRollNotAccepted(frames, frame, index)) {
                continue;
            }
            final int input = getValidInputFromUser(frames.size() + 1, index);
            assignBonusTypesToFrame(frame, input);
            updateFrameRollsFromInput(frame, input);
            addPreviousFrameTotalToCurrent(frames, frame);
        }

        return frame;
    }


    protected void addPreviousFrameTotalToCurrent(final List<Frame> frames, final Frame frame) {
        if (!frames.isEmpty()) {
            frame.setTotal(frame.getTotal() + frames.get(frames.size() - 1).getTotal());
        }
    }


    protected void assignBonusTypesToFrame(final Frame frame, final int input) {
        if (frame.getRolls().isEmpty()) {
            handleFirstRoll(frame, input);
        } else if (frame.getRolls().size() == 1 && frame.getBonusType() != STRIKE) {
            handleSecondRoll(frame, input);
        }
    }


    private boolean isThirdRollNotAccepted(final List<Frame> frames, final Frame frame, final int index) {
        return isRollAfterStrikeAndNotInLastFrame(frames, frame, index) || isThirdRollNotInLastFrame(frames, index);
    }


    private boolean isThirdRollNotInLastFrame(final List<Frame> frames, final int index) {
        return isNotLastFrame(frames) && index > 2;
    }


    private boolean isRollAfterStrikeAndNotInLastFrame(final List<Frame> frames, final Frame frame, final int index) {
        return frame.getBonusType() == STRIKE && isNotLastFrame(frames) && index >= 2;
    }


    private boolean isNotLastFrame(final List<Frame> frames) {
        return frames.size() != 9;
    }


    private void handleSecondRoll(final Frame frame, final int input) {
        final int firstRollCount = frame.getRolls().iterator().next().getCount();
        final boolean isSpare = (firstRollCount + input) == 10;
        if (isSpare) {
            frame.setBonusType(SPARE);
        }
    }


    protected int getValidInputFromUser(final int frameNumber, final int i) {
        final Scanner scan = new Scanner(System.in);
        int input;
        do {
            System.out.print("Frame " + frameNumber + " - Roll " + i + " : ");
            final String inputLine = scan.nextLine();
            input = Integer.parseInt(inputLine);
        } while (!isInputValid(input));
        return input;
    }


    private void handleFirstRoll(final Frame frame, final int input) {
        final boolean isStrike = input == 10;
        if (isStrike) {
            frame.setBonusType(STRIKE);
        }
    }


    protected void updateFrameRollsFromInput(final Frame frame, final int input) {
        final Roll currentRoll = new Roll(input);

        if (frame.getRolls().isEmpty()) {
            frame.setRolls(List.of(currentRoll));
            frame.setTotal(currentRoll.getCount());
        } else {
            final Roll previousRoll = frame.getRolls().get(0);
            frame.setRolls(asList(previousRoll, currentRoll));
            frame.setTotal(previousRoll.getCount() + currentRoll.getCount());
        }
    }


    private boolean isInputValid(final int input) {
        return input >= 0 && input <= 10;
    }
}
