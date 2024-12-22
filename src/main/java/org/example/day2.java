package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    private static int getSafeReports(ArrayList<String> reports) {

        int safes = 0;

        for (String report : reports) {

            String[] levels = report.split(" ");

            boolean isSafe = false;
            int firstDirection = 0;

            for (int i = 0; i < levels.length - 1; i++) {

                Integer actualLevel = Integer.parseInt(levels[i]);
                Integer nextLevel = Integer.parseInt(levels[i + 1]);

                int difference = actualLevel - nextLevel;
                int absDifference = Math.abs(difference);
                int actualDirection;

                if (isNotTolerance(absDifference)) {
                    isSafe = false;
                    break;
                }

                if (i == 0) {
                    if (difference > 0) {
                        firstDirection = 1;
                    } else if (difference < 0) {
                        firstDirection = -1;
                    } else {
                        break;
                    }
                    continue;
                }

                if (difference > 0) {
                    actualDirection = 1;
                } else if (difference < 0) {
                    actualDirection = -1;
                } else {
                    isSafe = false;
                    break;
                }

                if (isIncrease(firstDirection, actualDirection) || isDecrease(firstDirection, actualDirection)) {
                    isSafe = true;
                } else {
                    isSafe = false;
                    break;
                }
            }
            if (isSafe) {
                safes++;
            }
        }

        return safes;
    }

    private static boolean isNotTolerance(Integer absDifference) {
        return !(absDifference >= 1 && absDifference <= 3);
    }

    private static boolean isIncrease(int firstDirection, int actualDirection) {
        return firstDirection > 0 && actualDirection > 0;
    }

    private static boolean isDecrease(int firstDirection, int actualDirection) {
        return firstDirection < 0 && actualDirection < 0;
    }
}