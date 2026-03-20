package ru.yandex.practicum.sleeptracker;

import classifications.ClassificationOfUser;
import types.of.users.Lark;
import types.of.users.Owl;
import types.of.users.Pigeon;
import types.of.users.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ClassificationOfUserTest {
    private ClassificationOfUser classificationOfUser;
    @BeforeEach
    public void setUp() {
        classificationOfUser = new ClassificationOfUser();
    }

    @Test
    public void shouldReturnNullForSingleSession() {
        SleepingSession session = new SleepingSession("01.01.22 22:00;01.01.22 23:30;BAD");
        List<SleepingSession> sleepingSessions = List.of(session);

        UserType result = classificationOfUser.apply(sleepingSessions);

        Assertions.assertNull(result);
    }

    @Test
    public void shouldReturnPigeonForSingleSession() {
        SleepingSession session = new SleepingSession("01.01.22 22:00;02.01.22 09:30;BAD");
        List<SleepingSession> sleepingSessions = List.of(session);

        UserType result = classificationOfUser.apply(sleepingSessions);

        Assertions.assertEquals(new Pigeon(), result);
    }

    @Test
    public void shouldReturnPigeonForMultipleSession() {
        SleepingSession session = new SleepingSession("01.01.22 23:00;02.01.22 09:30;BAD");
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

        UserType result = classificationOfUser.apply(sleepingSessions);

        Assertions.assertEquals(new Pigeon(), result);
    }

    @Test
    public void shouldReturnOwlForSingleSession() {
        SleepingSession session = new SleepingSession("01.01.22 23:01;02.01.22 09:30;BAD");
        List<SleepingSession> sleepingSessions = List.of(session);

        UserType result = classificationOfUser.apply(sleepingSessions);

        Assertions.assertEquals(new Owl(), result);
    }

    @Test
    public void shouldReturnOwlForMultipleSession() {
        SleepingSession session = new SleepingSession("01.01.22 23:01;02.01.22 09:31;GOOD");  // сова
        SleepingSession session2 = new SleepingSession("02.01.22 23:30;03.01.22 10:00;GOOD"); // сова
        SleepingSession session3 = new SleepingSession("03.01.22 23:15;04.01.22 09:01;GOOD"); // сова
        SleepingSession session4 = new SleepingSession("04.01.22 22:30;05.01.22 08:00;GOOD"); // голубь
        SleepingSession session5 = new SleepingSession("05.01.22 21:00;06.01.22 08:00;GOOD"); // голубь
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(session);
        sleepingSessions.add(session2);
        sleepingSessions.add(session3);
        sleepingSessions.add(session4);
        sleepingSessions.add(session5);

        UserType result = classificationOfUser.apply(sleepingSessions);

        Assertions.assertEquals(new Owl(), result);
    }

    @Test
    public void shouldReturnLarkForSingleSession() {
        SleepingSession session = new SleepingSession("01.01.22 21:59;02.01.22 06:30;BAD");
        List<SleepingSession> sleepingSessions = List.of(session);

        UserType result = classificationOfUser.apply(sleepingSessions);

        Assertions.assertEquals(new Lark(), result);
    }

    @Test
    public void shouldReturnLarkForMultipleSession() {
        SleepingSession session = new SleepingSession("01.01.22 21:01;02.01.22 06:31;GOOD");  // жав
        SleepingSession session2 = new SleepingSession("02.01.22 21:30;03.01.22 05:00;GOOD"); // жав
        SleepingSession session3 = new SleepingSession("03.01.22 20:15;04.01.22 06:01;GOOD"); // жав
        SleepingSession session4 = new SleepingSession("04.01.22 22:30;05.01.22 08:00;GOOD"); // голубь
        SleepingSession session5 = new SleepingSession("05.01.22 21:00;06.01.22 08:00;GOOD"); // голубь
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(session);
        sleepingSessions.add(session2);
        sleepingSessions.add(session3);
        sleepingSessions.add(session4);
        sleepingSessions.add(session5);

        UserType result = classificationOfUser.apply(sleepingSessions);

        Assertions.assertEquals(new Lark(), result);
    }

    @Test
    public void shouldReturnNullToDaySession() {
        SleepingSession session = new SleepingSession("01.01.22 13:00;01.01.22 14:30;GOOD");
        List<SleepingSession> sleepingSessions = List.of(session);

        UserType result = classificationOfUser.apply(sleepingSessions);

        Assertions.assertNull(result);
    }

    @Test
    public void shouldReturnPigeonForMultipleSessionWith2LarkAndOwl() {
        SleepingSession session = new SleepingSession("01.01.22 21:01;02.01.22 06:31;GOOD");  // жав
        SleepingSession session2 = new SleepingSession("02.01.22 21:30;03.01.22 05:00;GOOD"); // жав
        SleepingSession session4 = new SleepingSession("04.01.22 23:30;05.01.22 11:00;GOOD"); // сова
        SleepingSession session5 = new SleepingSession("05.01.22 23:01;06.01.22 10:00;GOOD"); // сова
        List<SleepingSession> sleepingSessions = new ArrayList<>();
        sleepingSessions.add(session);
        sleepingSessions.add(session2);
        sleepingSessions.add(session4);
        sleepingSessions.add(session5);

        UserType result = classificationOfUser.apply(sleepingSessions);

        Assertions.assertEquals(new Pigeon(), result);
    }
}
