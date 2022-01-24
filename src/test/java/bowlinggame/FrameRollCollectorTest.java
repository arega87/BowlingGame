package bowlinggame;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

class FrameRollCollectorTest {

    private final FrameRollCollector underTest = new FrameRollCollector();


    @Test
    void getValidInputFromUser() {
        Frame frame = new Frame();

        System.setIn(new ByteArrayInputStream("11\n12\n4".getBytes()));

        final int validInput1 = underTest.collectInputFromUser(frame, 1);

        Assertions.assertEquals(4, validInput1);

        System.setIn(new ByteArrayInputStream("11\n2\n15".getBytes()));

        final int validInput2 = underTest.collectInputFromUser(frame, 1);

        Assertions.assertEquals(2, validInput2);
    }


    @Test
    void getValidInputFromUser_formatException() {
        Frame frame = new Frame();

        System.setIn(new ByteArrayInputStream("B\n11\nS\n22".getBytes()));

        Throwable throwable = catchThrowable(() -> underTest.collectInputFromUser(frame, 1));

        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
    }
}