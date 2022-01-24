package bowlinggame;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static fixtures.FrameFixtures.buildFramesToCalculateScoreFor;
import static org.assertj.core.api.Assertions.assertThat;

class FrameScoreCalculatorTest {

    private final FrameScoreCalculator underTest = new FrameScoreCalculator();


    @Test
    void calculateScore() {

        final List<Frame> frames = buildFramesToCalculateScoreFor();

        final List<Frame> result = underTest.calculateScore(frames);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(10, result.size());
        assertThat(result)
                .extracting(Frame::getScore)
                .containsExactly(5, 14, 29, 49, 60, 61, 77, 97, 117, 133);
    }
}