package Classifications;

import TypesOfUser.Lark;
import TypesOfUser.Owl;
import TypesOfUser.Pigeon;
import TypesOfUser.UserType;
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

            int max = Math.max(owlCount, Math.max(larkCount, pigeonCount));
            if (max == owlCount) {
                System.out.println(owl.getFirst().getGetDescription());
                return new Owl();
            } else if (max == larkCount) {
                System.out.println(lark.getFirst().getGetDescription());
                return new Lark();
            } else {
                System.out.println(pigeon.getFirst().getGetDescription());
                return new Pigeon();
            }
        }

        String firstSession = sleepingSessionsSplit.getFirst();
        String secondSession = sleepingSessionsSplit.get(1);
        LocalDateTime firstSessionTime = LocalDateTime.parse(firstSession, dateTimeFormatter);
        LocalDateTime secondSessionTime = LocalDateTime.parse(secondSession, dateTimeFormatter);
        long firstSessionHour = firstSessionTime.getHour();
        long secondSessionHour = secondSessionTime.getHour();
        if (firstSessionTime.getDayOfYear() == secondSessionTime.getDayOfYear()) {
            if (firstSessionHour < 6 && secondSessionHour > 9) {
                owl.add(new Owl());
            } else {
                return apply(sleepingSessions.subList(1, sleepingSessions.size()));
            }
        } else if (firstSessionHour < 22 && secondSessionHour < 7) {
            lark.add(new Lark());
        } else {
            pigeon.add(new Pigeon());
        }
        return apply(sleepingSessions.subList(1, sleepingSessions.size()));
    }
}
