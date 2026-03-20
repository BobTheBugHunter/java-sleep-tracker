package ru.yandex.practicum.sleeptracker;

import Calculation.Functions.*;
import com.sun.tools.javac.Main;

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
        System.out.println(functions.getFirst().apply(sleepingSessions));
        System.out.println(functions.get(1).apply(sleepingSessions));
        System.out.println(functions.get(2).apply(sleepingSessions));
        System.out.println(functions.get(3).apply(sleepingSessions));
        System.out.println(functions.get(4).apply(sleepingSessions));
        System.out.println(functions.get(5).apply(sleepingSessions));
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