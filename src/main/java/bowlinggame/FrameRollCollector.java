package bowlinggame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static bowlinggame.BowlingGameMain.TOTAL_PINS_COUNT;

public class FrameRollCollector {


    private static final int MAX_INPUT_RETRY_COUNT = 3;


    public int collectInputFromUser(final Frame currentFrame, final int currentRollIndex) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int input = 0;
        boolean isInputNotValid;
        int retryCount = 0;

        do {
            boolean hasFormatException = false;
            System.out.printf("Frame %d - Roll %d : ", currentFrame.getPosition(), currentRollIndex);

            try {
                input = Integer.parseInt(bufferedReader.readLine());
            } catch (NumberFormatException | IOException e) {
                hasFormatException = true;
            }

            isInputNotValid = hasFormatException || isOutOfBounds(input) || hasTotalExceededPinsCount(currentFrame, input);

            if (isInputNotValid) {
                retryCount++;
                logExceptions(currentFrame);
            }

        } while (isInputNotValid && retryCount < MAX_INPUT_RETRY_COUNT);

        handleRetryExceededError(isInputNotValid);

        return input;
    }


    private void handleRetryExceededError(final boolean isInputNotValid) {
        if (isInputNotValid) {
            throw new IllegalArgumentException("Wrong user input after " + MAX_INPUT_RETRY_COUNT + " retries");
        }
    }


    private void logExceptions(final Frame currentFrame) {
        System.out.printf("Only Numeric between 0 and 10 is accepted " +
                "and current total should not exceed %d pins (current score: %d)." +
                " Please Retry! \n", TOTAL_PINS_COUNT, currentFrame.getScore());
    }


    private boolean isOutOfBounds(final int input) {
        return input < 0 || input > TOTAL_PINS_COUNT;
    }


    private boolean hasTotalExceededPinsCount(final Frame currentFrame, final int input) {
        return !currentFrame.isLastFrame() && input + currentFrame.getScore() > TOTAL_PINS_COUNT;
    }
}
