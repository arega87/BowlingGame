package bowlinggame;

import java.util.List;
import org.junit.jupiter.api.Test;
import static bowlinggame.Bonus.NONE;
import static bowlinggame.Bonus.SPARE;
import static bowlinggame.Bonus.STRIKE;
import static org.junit.jupiter.api.Assertions.assertEquals;


class FramePopulatorTest {

    private final FramePopulator underTest = new FramePopulator();


    @Test
    void updateFrameRollsFromInput() {
        Frame frame = new Frame();
        underTest.addRollInputToFrame(frame, 6);

        assertEquals(1, frame.getRolls().size());
        assertEquals(6, frame.getRolls().get(0).getScore());

        underTest.addRollInputToFrame(frame, 4);
        assertEquals(2, frame.getRolls().size());
        assertEquals(6, frame.getRolls().get(0).getScore());
        assertEquals(4, frame.getRolls().get(1).getScore());
    }


    @Test
    void assignBonusTypesToFrame_strikeOnNewFrame() {
        Frame frame = new Frame();
        underTest.assignBonusType(frame, 10);
        assertEquals(STRIKE, frame.getBonusType());
    }


    @Test
    void assignBonusTypesToFrame_spareOnSecondRoll() {
        Frame frame = new Frame();
        Roll roll = new Roll(6);
        frame.setRolls(List.of(roll));

        underTest.assignBonusType(frame, 4);
        assertEquals(SPARE, frame.getBonusType());
    }


    @Test
    void assignBonusTypesToFrame_noBonus() {
        Frame frame = new Frame();
        Roll roll = new Roll(6);
        frame.setRolls(List.of(roll));

        underTest.assignBonusType(frame, 2);
        assertEquals(NONE, frame.getBonusType());
    }
}