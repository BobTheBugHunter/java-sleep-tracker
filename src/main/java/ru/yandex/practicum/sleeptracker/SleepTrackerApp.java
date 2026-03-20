package ru.yandex.practicum.sleeptracker;

import classifications.ClassificationOfUser;
import com.sun.tools.javac.Main;
import only.calculation.functions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    private static final List<SleepingSession> sleepingSessions = new ArrayList<>();
    private static final List<Function> functions = new ArrayList<>();

    public static void main(String[] args) {

        readFile("sleep_log.txt");
        functions.add(new CounterOfSessions());
        functions.add(new MinimumSession());
        functions.add(new MaximumSession());
        functions.add(new AverageSession());
        functions.add(new CounterBadSessions());
        functions.add(new SleeplessNights());
        functions.add(new ClassificationOfUser());
        System.out.printf("Количество ваших сессий сна: %d%n", functions.getFirst().apply(sleepingSessions));
        System.out.printf("Минимальная сессия сна: %d минут%n", functions.get(1).apply(sleepingSessions));
        System.out.printf("Максимальная сессия сна: %d минут%n", functions.get(2).apply(sleepingSessions));
        System.out.printf("Средняя продолжительность сессии: %d минут%n", functions.get(3).apply(sleepingSessions));
        System.out.printf("Количество плохих сессий сна: %d%n", functions.get(4).apply(sleepingSessions));
        System.out.printf("Количество бессонных ночей: %d%n", functions.get(5).apply(sleepingSessions));
        System.out.print("Определение вашего хронотипа: ");
        functions.get(6).apply(sleepingSessions);
    }

    public static void readFile(String fileName) {
        try (InputStream is = Main.class.getClassLoader()
                .getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            if (is == null) {
                System.out.println("Файл не найден: " + fileName);
                return;
            }

            System.out.println("Файл найден");
            String line;
            while ((line = br.readLine()) != null) {
                sleepingSessions.add(new SleepingSession(line));
            }

        } catch (IOException e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Файл не найден: " + fileName);
        }
    }
}