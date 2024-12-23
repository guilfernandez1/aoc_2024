package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class day2 {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> reports = new ArrayList<>();

        File file = new File("./src/main/resources/inputDay2.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            reports.add(line);
        }

        System.out.println(getSafeReports(reports));

    }
    //Part 1 and Part 2
    private static int getSafeReports(ArrayList<String> reports) {

        int safeReports = 0;

        for (String report : reports) {

            ArrayList<String> levels = new ArrayList<>(Arrays.asList(report.split(" ")));

            boolean isSafe = loopLevels(levels);

            if (isSafe) {
                safeReports++;
            } else {
                for (int i = 0; i < levels.size(); i++) {

                    ArrayList<String> unsafeLevels = new ArrayList<>(levels);

                    unsafeLevels.remove(i);
                    isSafe = loopLevels(unsafeLevels);

                    if (isSafe) {
                        safeReports++;
                        break;
                    }
                }
            }
        }

        return safeReports;
    }

    private static boolean loopLevels(ArrayList<String> levels) {

        int firstDirection = 0;
        int actualDirection;
        boolean isSafe = false;

        for (int i = 0; i < levels.size() - 1; i++) {

            int actualLevel = Integer.parseInt(levels.get(i));
            int nextLevel = Integer.parseInt(levels.get(i + 1));

            if (isNotTolerance(actualLevel, nextLevel)) {
                isSafe = false;
                break;
            }

            if (i == 0) {
                if (isDifferencePositive(actualLevel, nextLevel)) {
                    firstDirection = 1;
                } else if (isDifferenceNegative(actualLevel, nextLevel)) {
                    firstDirection = -1;
                } else {
                    break;
                }
            }

            if (isDifferencePositive(actualLevel, nextLevel)) {
                actualDirection = 1;
            } else if (isDifferenceNegative(actualLevel, nextLevel)) {
                actualDirection = -1;
            } else {
                isSafe = false;
                break;
            }

            if (isIncreaseOrDecrease(firstDirection, actualDirection)) {
                isSafe = true;
            } else {
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    private static boolean isIncreaseOrDecrease(int firstDirection, int actualDirection) {
        return isIncrease(firstDirection, actualDirection) || isDecrease(firstDirection, actualDirection);
    }

    private static boolean isDifferencePositive(int levelA, int levelB) {
        return levelA - levelB > 0;
    }

    private static boolean isDifferenceNegative(int levelA, int levelB) {
        return levelA - levelB < 0;
    }

    private static boolean isNotTolerance(int levelA, int levelB) {
        int absDifference = Math.abs(levelA - levelB);
        return !(absDifference >= 1 && absDifference <= 3);
    }

    private static boolean isIncrease(int firstDirection, int actualDirection) {
        return firstDirection > 0 && actualDirection > 0;
    }

    private static boolean isDecrease(int firstDirection, int actualDirection) {
        return firstDirection < 0 && actualDirection < 0;
    }
}