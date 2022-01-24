package fixtures;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import bowlinggame.Bonus;
import bowlinggame.Frame;
import bowlinggame.Roll;
import static bowlinggame.Bonus.NONE;
import static bowlinggame.Bonus.SPARE;
import static bowlinggame.Bonus.STRIKE;
import static java.util.stream.Collectors.toList;

public class FrameFixtures {

    public static List<Frame> buildFramesToCalculateScoreFor() {
        List<Frame> frames = new ArrayList<>();

        frames.add(buildFrame(1, List.of(1, 4), NONE));
        frames.add(buildFrame(2, List.of(4, 5), NONE));
        frames.add(buildFrame(3, List.of(6, 4), SPARE));
        frames.add(buildFrame(4, List.of(5, 5), SPARE));
        frames.add(buildFrame(5, List.of(10, 0), STRIKE));
        frames.add(buildFrame(6, List.of(0, 1), NONE));
        frames.add(buildFrame(7, List.of(7, 3), SPARE));
        frames.add(buildFrame(8, List.of(6, 4), SPARE));
        frames.add(buildFrame(9, List.of(10, 0), STRIKE));
        frames.add(buildFrame(10, List.of(2, 8, 6), NONE));

        return frames;
    }


    public static Frame buildFrame(final int position, final List<Integer> rolls, final Bonus bonus) {
        Frame frame = new Frame();
        
        frame.setBonusType(bonus);
        frame.setRolls(buildRolls(rolls));
        frame.setScore(rolls.stream().filter(Objects::nonNull).mapToInt(Integer::intValue).sum());
        frame.setPosition(position);
        
        return frame;
    }


    public static List<Roll> buildRolls(final List<Integer> rolls) {
        return rolls.stream()
                .filter(Objects::nonNull)
                .map(Roll::new)
                .collect(toList());
    }
}
