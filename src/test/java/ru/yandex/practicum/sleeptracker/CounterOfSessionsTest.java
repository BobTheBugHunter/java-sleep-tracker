package ru.yandex.practicum.sleeptracker;

import Calculation.Functions.CounterOfSessions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CounterOfSessionsTest {

    CounterOfSessions counter;

    @BeforeEach
    public void setUpClass() {
        counter = new CounterOfSessions();
    }

    @Test
    public void shouldReturnZeroForEmptyList() {
        List<SleepingSession> list = new ArrayList<>();

        Integer result = counter.apply(list);

        Assertions.assertEquals(0, result);
    }

    @Test
    public void shouldReturnOneForSingleSession() {
        List<SleepingSession> list = new ArrayList<>();
        list.add(new SleepingSession("1"));

        Integer result = counter.apply(list);

        Assertions.assertEquals(1, result);
    }
}