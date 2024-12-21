package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class day1 {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> left = new ArrayList<>();
        ArrayList<String> right = new ArrayList<>();

        File file = new File("./src/main/resources/inputDay1.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] test = line.split(" {3}");
            left.add(test[0]);
            right.add(test[1]);
        }

        System.out.println(getTotal(left, right));
        System.out.println(getScore(left, right));

    }

    //Part 1
    private static String getTotal(ArrayList<String> left, ArrayList<String> right) {

        int total = 0;

        left.sort(Comparator.naturalOrder());
        right.sort(Comparator.naturalOrder());

        for (int i = 0; i < left.size(); i++) {
            total += Math.abs(Integer.parseInt(left.get(i)) - Integer.parseInt(right.get(i)));
        }

        return Integer.toString(total);
    }

    //Part 2
    private static String getScore(ArrayList<String> left, ArrayList<String> right) {

        List<Integer> list = left.stream().map(numberLeft -> {

            AtomicReference<Integer> count = new AtomicReference<>(0);

            right.forEach(numberRight -> {

                if(numberLeft.equalsIgnoreCase(numberRight)) {
                    count.set(count.get() + 1);
                }

            });
            return count.get();
        }).toList();

        int score = 0;

        for(int i = 0; i < left.size(); i++) {
            score += Integer.parseInt(left.get(i)) * list.get(i);
        }

        return Integer.toString(score);
    }
}