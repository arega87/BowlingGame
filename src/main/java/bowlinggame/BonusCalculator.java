package bowlinggame;

import java.util.List;

public class BonusCalculator {


    /**
     * Goes through all frames again to calculate the bonuses
     *             
     * @param populatedFrames previously populated frames from user input
     * @return updated frames with calculated bonuses 
     */
    public List<Frame> calculateBonuses(final List<Frame> populatedFrames) {

        //TODO if frame has no bonus assigned or is last, the loop will break

        //TODO if selected Frame has a Strike bonus assigned go two frames forward (check if index exists first), 
        // then add both first rolls quantity to total

        //TODO if selected Frame has a Spare bonus assigned go one frame forward (check if index exists first), 
        // then add next first rolls quantity to total

        return populatedFrames;
    }
}
