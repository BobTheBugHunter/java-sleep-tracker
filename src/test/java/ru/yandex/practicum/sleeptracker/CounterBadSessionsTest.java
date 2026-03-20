package ru.yandex.practicum.sleeptracker;

import Calculation.Functions.CounterBadSessions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CounterBadSessionsTest {
    private CounterBadSessions counterBadSessions;
    @BeforeEach
    public void setUp() {
        counterBadSessions = new CounterBadSessions();
    }

    @Test
    public void shouldReturnOneForSingleSession() {
        SleepingSession session = new SleepingSession("01.01.22 22:00;01.01.22 23:30;BAD");
        List<SleepingSession> sleepingSessions = List.of(session);

        Integer result = counterBadSessions.apply(sleepingSessions);

        Assertions.assertEquals(1, result);
    }

    @Test
    public void shouldReturnCountOfBadSessionForMultipleSessions() {
        SleepingSession session = new SleepingSession("01.01.22 22:00;01.01.22 23:30;GOOD");
        SleepingSession session2 = new SleepingSession("01.01.22 23:00;02.01.22 09:30;GOOD");
        SleepingSession session3 = new SleepingSession("01.01.22 11:00;02.01.22 19:30;BAD");
        SleepingSession session4 = new SleepingSession("03.01.22 11:00;03.01.22 13:30;BAD");
        SleepingSession session5 = new SleepingSession("03.01.22 11:00;03.01.22 14:30;BAD");
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(session);
        sleepingSessions.add(session2);
        sleepingSessions.add(session3);
        sleepingSessions.add(session4);
        sleepingSessions.add(session5);

        Integer result = counterBadSessions.apply(sleepingSessions);

        Assertions.assertEquals(3, result);
    }

    @Test
    public void shouldReturnZeroCountOfBadSession() {
        SleepingSession session = new SleepingSession("01.01.22 22:00;01.01.22 23:30;GOOD");
        SleepingSession session2 = new SleepingSession("01.01.22 23:00;02.01.22 09:30;GOOD");
        SleepingSession session3 = new SleepingSession("01.01.22 11:00;02.01.22 19:30;NORMAL");
        SleepingSession session4 = new SleepingSession("03.01.22 11:00;03.01.22 13:30;GOOD");
        SleepingSession session5 = new SleepingSession("03.01.22 11:00;03.01.22 14:30;NORMAL");
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(session);
        sleepingSessions.add(session2);
        sleepingSessions.add(session3);
        sleepingSessions.add(session4);
        sleepingSessions.add(session5);

        Integer result = counterBadSessions.apply(sleepingSessions);

        Assertions.assertEquals(0, result);
    }
}
