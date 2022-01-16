package bowlinggame;

import java.io.ByteArrayInputStream;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static bowlinggame.Bonus.NONE;
import static bowlinggame.Bonus.SPARE;
import static bowlinggame.Bonus.STRIKE;

class FramePopulatorTest {

    final FramePopulator underTest = new FramePopulator();

    @Test
    void updateFrameRollsFromInput() {
        Frame frame = new Frame();
        underTest.updateFrameRollsFromInput(frame,6);

        Assertions.assertEquals(1, frame.getRolls().size());
        Assertions.assertEquals(6, frame.getRolls().get(0).getCount());

        underTest.updateFrameRollsFromInput(frame,4);
        Assertions.assertEquals(2, frame.getRolls().size());
        Assertions.assertEquals(6, frame.getRolls().get(0).getCount());
        Assertions.assertEquals(4, frame.getRolls().get(1).getCount());
    }
    
    @Test
    void addPreviousFrameTotalToCurrent() {
        Frame previousFrame = new Frame();
        previousFrame.setTotal(5);
        
        Frame currentFrame = new Frame();
        currentFrame.setTotal(5);
        underTest.addPreviousFrameTotalToCurrent(List.of(previousFrame),currentFrame);

        Assertions.assertEquals(10, currentFrame.getTotal());
    }
    
    
    @Test
    void getValidInputFromUser() {
        System.setIn(new ByteArrayInputStream("11\n12\n4".getBytes()));

        final int validInput1 = underTest.getValidInputFromUser(1, 1);
        
        Assertions.assertEquals(4, validInput1);
        
        System.setIn(new ByteArrayInputStream("11\n2\n15".getBytes()));

        final int validInput2 = underTest.getValidInputFromUser(1, 1);
        
        Assertions.assertEquals(2, validInput2);
    }
    
    @Test
    void assignBonusTypesToFrame_strikeOnNewFrame() {
        Frame frame = new Frame();
        underTest.assignBonusTypesToFrame(frame, 10);
        Assertions.assertEquals(STRIKE, frame.getBonusType());
    }
    
    @Test
    void assignBonusTypesToFrame_spareOnSecondRoll() {
        Frame frame = new Frame();
        Roll roll = new Roll(6);
        frame.setRolls(List.of(roll));
        
        underTest.assignBonusTypesToFrame(frame, 4);
        Assertions.assertEquals(SPARE, frame.getBonusType());
    }
    
    @Test
    void assignBonusTypesToFrame_noBonus() {
        Frame frame = new Frame();
        Roll roll = new Roll(6);
        frame.setRolls(List.of(roll));
        
        underTest.assignBonusTypesToFrame(frame, 2);
        Assertions.assertEquals(NONE, frame.getBonusType());
    }
}