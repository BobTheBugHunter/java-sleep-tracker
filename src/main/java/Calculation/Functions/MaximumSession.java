package Calculation.Functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaximumSession implements Function<List<SleepingSession>, Long> {
    private final List<Duration> durations = new ArrayList<>();
    private final int END_OF_SESSIONS = 2;

    @Override
    public Long apply(List<SleepingSession> sleepingSessions) {

        if (sleepingSessions.size() < END_OF_SESSIONS) {
            Optional<Duration> maxDuration = durations.stream().max(Duration::compareTo);
            return maxDuration.get().toMinutes();
        }

        DateTimeFormatter dateTimeFormatter  = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        List<String> s = sleepingSessions.stream()
                .flatMap(sleepingSession -> Arrays.stream(sleepingSession.getSession().split(";")))
                .collect(Collectors.toList());

        String firstTime = s.getFirst();
        String secondTime = s.get(1);
        LocalDateTime dateTimeFirst = LocalDateTime.parse(firstTime, dateTimeFormatter);
        LocalDateTime dateTimeSecond = LocalDateTime.parse(secondTime, dateTimeFormatter);
        Duration duration = Duration.between(dateTimeFirst, dateTimeSecond);
        durations.add(duration);
        return apply(sleepingSessions.subList(1, sleepingSessions.size()));
    }
}