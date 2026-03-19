package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SleepTrackerApp {

    private static final List<SleepingSession> logFromFile = new ArrayList<>();
    private static final List<Functional> functions = new ArrayList<>();

    public static void main(String[] args) {

        readFile("sleep_log.txt");

    }

    public static void readFile(String fileName) {
        try(BufferedReader br = new BufferedReader((new FileReader(fileName)))) {

            String line;
            while ((line = br.readLine()) != null) {
                logFromFile.add(new  SleepingSession(line));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}