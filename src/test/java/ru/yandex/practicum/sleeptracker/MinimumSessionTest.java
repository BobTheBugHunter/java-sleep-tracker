package ru.yandex.practicum.sleeptracker;

import only.calculation.functions.MinimumSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MinimumSessionTest {
    private MinimumSession minimumSession;
    @BeforeEach
    public void setUp() {
        minimumSession = new MinimumSession();
    }

    @Test
    public void shouldReturnDurationForSingleSession() {
        SleepingSession session = new SleepingSession("01.01.22 22:00;01.01.22 23:30;GOOD");
        List<SleepingSession> sleepingSessions = List.of(session);

        Long result = minimumSession.apply(sleepingSessions);

        Assertions.assertEquals(90L, result);
    }

    @Test
    public void shouldReturnDurationForMultipleSessions() {
        SleepingSession session = new SleepingSession("01.01.22 23:00;02.01.22 09:30;GOOD");
        SleepingSession session2 = new SleepingSession("01.01.22 20:00;01.01.22 20:30;GOOD");
        SleepingSession session3 = new SleepingSession("01.01.22 11:00;02.01.22 19:30;GOOD");
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(session);
        sleepingSessions.add(session2);
        sleepingSessions.add(session3);

        Long result = minimumSession.apply(sleepingSessions);

        Assertions.assertEquals(30L, result);
    }
}
