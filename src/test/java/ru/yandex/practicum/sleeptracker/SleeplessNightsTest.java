package ru.yandex.practicum.sleeptracker;

import Calculation.Functions.MinimumSession;
import Calculation.Functions.SleeplessNights;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SleeplessNightsTest {
    private SleeplessNights sleeplessNights;
    @BeforeEach
    public void setUp() {
        sleeplessNights = new SleeplessNights();
    }

    @Test
    public void shouldReturnOneForSingleSession() {
        SleepingSession session = new SleepingSession("01.01.22 22:00;01.01.22 23:30;GOOD");
        List<SleepingSession> sleepingSessions = List.of(session);

        Integer result = sleeplessNights.apply(sleepingSessions);

        Assertions.assertEquals(1, result);
    }

    @Test
    public void shouldReturnCountOfSleeplessNightsForMultipleSessions() {
        SleepingSession session = new SleepingSession("01.01.22 23:00;02.01.22 09:30;GOOD");
        SleepingSession session2 = new SleepingSession("01.01.22 20:00;01.01.22 20:30;GOOD");
        SleepingSession session3 = new SleepingSession("01.01.22 11:00;01.01.22 19:30;GOOD");
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(session);
        sleepingSessions.add(session2);
        sleepingSessions.add(session3);

        Integer result = sleeplessNights.apply(sleepingSessions);

        Assertions.assertEquals(2, result);
    }

    @Test
    public void shouldReturnZeroCountOfSleeplessNights() {
        SleepingSession session = new SleepingSession("01.01.22 23:00;02.01.22 06:30;GOOD");
        SleepingSession session2 = new SleepingSession("01.01.22 06:00;02.01.22 23:59;GOOD");
        SleepingSession session3 = new SleepingSession("01.01.22 00:00;02.01.22 06:00;GOOD");
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(session);
        sleepingSessions.add(session2);
        sleepingSessions.add(session3);

        Integer result = sleeplessNights.apply(sleepingSessions);

        Assertions.assertEquals(0, result);
    }

    @Test
    public void shouldReturnCountOfSleeplessNightsForSuitableSession() {
        SleepingSession session = new SleepingSession("01.01.22 06:59;01.01.22 23:59;GOOD");
        SleepingSession session2 = new SleepingSession("01.01.22 07:00;01.01.22 20:59;GOOD");
        SleepingSession session3 = new SleepingSession("01.01.22 06:00;01.01.22 07:00;GOOD");
        SleepingSession session4 = new SleepingSession("01.01.22 22:00;01.01.22 23:00;GOOD");
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(session);
        sleepingSessions.add(session2);
        sleepingSessions.add(session3);
        sleepingSessions.add(session4);

        Integer result = sleeplessNights.apply(sleepingSessions);

        Assertions.assertEquals(4, result);
    }
}
