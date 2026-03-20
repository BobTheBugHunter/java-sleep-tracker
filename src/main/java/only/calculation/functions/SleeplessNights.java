package only.calculation.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleeplessNights implements Function<List<SleepingSession>, Integer> {

    private final HashMap<LocalDateTime, LocalDateTime> sleeplessSessions = new HashMap<>();
    private final int INTERVAL = 6;

    @Override
    public Integer apply(List<SleepingSession> sleepingSessions) {
        DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        List<String> sleepingSessionsSplit = sleepingSessions.stream().flatMap(sleepingSession ->
                        Arrays.stream(sleepingSession.getSession().split(";")))
                .collect(Collectors.toList());

        if (sleepingSessionsSplit.isEmpty()) {
            return sleeplessSessions.size();
        }

        String firstSession = sleepingSessionsSplit.getFirst();
        String secondSession = sleepingSessionsSplit.get(1);
        LocalDateTime firstSessionTime = LocalDateTime.parse(firstSession, dateTimeFormatter);
        LocalDateTime secondSessionTime = LocalDateTime.parse(secondSession, dateTimeFormatter);
        long hourOfFirstSession = firstSessionTime.getHour();
        if (firstSessionTime.getDayOfYear() == secondSessionTime.getDayOfYear()) {
            if (hourOfFirstSession >= INTERVAL) {
                sleeplessSessions.put(firstSessionTime, secondSessionTime);
                //System.out.println("Бессонная ночь!" + firstSessionTime.toString() + "\n" + "время проснутия" + secondSessionTime.toString());
            }
        }
//        else {
//            System.out.println("Ночь сонная!!!"  + firstSessionTime.toString() + "\n" + "время проснутия" +
//                    secondSessionTime.toString());
//        }


        return apply(sleepingSessions.subList(1, sleepingSessions.size()));
    }
}
