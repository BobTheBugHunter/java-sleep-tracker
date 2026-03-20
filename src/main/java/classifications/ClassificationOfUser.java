package classifications;

import types.of.users.Lark;
import types.of.users.Owl;
import types.of.users.Pigeon;
import types.of.users.UserType;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ClassificationOfUser implements Function<List<SleepingSession>, UserType> {
    private final List<Owl> owl = new ArrayList<>();
    private final List<Lark> lark = new ArrayList<>();
    private final List<Pigeon> pigeon = new ArrayList<>();

    private final int SLEEP_AT_NIGHT = 6;
    private final int WAKE_AT_DAY = 9;
    private final int TIME_FOR_OWL = 23;
    private final int TIME_FOR_LARK = 22;
    private final int TIME_FOR_WAKE_UP_LARK = 7;



    @Override
    public UserType apply(List<SleepingSession> sleepingSessions) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

        List<String> sleepingSessionsSplit = sleepingSessions.stream()
                .flatMap(sleepingSession -> Arrays.stream(sleepingSession.getSession().split(";")))
                .collect(Collectors.toList());

        if (sleepingSessions.isEmpty()) {
            int owlCount = owl.size();
            int larkCount = lark.size();
            int pigeonCount = pigeon.size();

            if (owlCount == 0 && larkCount == 0 && pigeonCount == 0) {
                return null; // не могу понять какое значение по умолчанию лучше поставить
            }

            int max = Math.max(owlCount, Math.max(larkCount, pigeonCount));
            if (max == owlCount && owlCount > larkCount && owlCount > pigeonCount) {
                System.out.println(owl.getFirst().getGetDescription());
                return new Owl();
            } else if (max == larkCount && larkCount > owlCount && larkCount > pigeonCount) {
                System.out.println(lark.getFirst().getGetDescription());
                return new Lark();
            }
            else {
                pigeon.add(new Pigeon());
                System.out.println(pigeon.getFirst().getGetDescription());
                return new Pigeon();
            }
        }

        String firstSession = sleepingSessionsSplit.getFirst();
        String secondSession = sleepingSessionsSplit.get(1);
        LocalDateTime firstSessionTime = LocalDateTime.parse(firstSession, dateTimeFormatter);
        LocalDateTime secondSessionTime = LocalDateTime.parse(secondSession, dateTimeFormatter);
        long firstSessionHour = firstSessionTime.getHour();
        long firstSessionMinute = firstSessionTime.getMinute();
        long secondSessionMinute = secondSessionTime.getMinute();
        long secondSessionHour = secondSessionTime.getHour();
        if (firstSessionTime.getDayOfYear() == secondSessionTime.getDayOfYear()) {
            if (firstSessionHour < SLEEP_AT_NIGHT && secondSessionHour > WAKE_AT_DAY) {
                owl.add(new Owl());
                return apply(sleepingSessions.subList(1, sleepingSessions.size()));
            } else {
                return apply(sleepingSessions.subList(1, sleepingSessions.size()));
            }
        }

        if (((firstSessionHour == TIME_FOR_OWL && firstSessionMinute > 0)) &&
                (secondSessionHour > WAKE_AT_DAY || (secondSessionHour == WAKE_AT_DAY && secondSessionMinute > 0))) {
            owl.add(new Owl());
        }
        else if (firstSessionHour < TIME_FOR_LARK && secondSessionHour < TIME_FOR_WAKE_UP_LARK) {
            lark.add(new Lark());
        } else {
            pigeon.add(new Pigeon());
        }
        return apply(sleepingSessions.subList(1, sleepingSessions.size()));
    }
}
