package only.calculation.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CounterBadSessions implements Function<List<SleepingSession>, Integer> {

    private final List<String> grades = new ArrayList<>();

    @Override
    public Integer apply(List<SleepingSession> sleepingSessions) {

        if (sleepingSessions.isEmpty()) {
            return grades.size();
        }

        List<String> s = sleepingSessions.stream()
                .flatMap(sleepingSession -> Arrays.stream(sleepingSession.getSession().split(";")))
                .collect(Collectors.toList());

        String grade = s.get(2);
        if (grade.equals("BAD")) {
            grades.add("BAD");
        }
        return apply(sleepingSessions.subList(1, sleepingSessions.size()));
    }
}
