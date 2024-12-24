package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day3 {
    public static void main(String[] args) throws FileNotFoundException {

        StringBuilder corruptedMemory = new StringBuilder();

        File file = new File("./src/main/resources/inputDay3.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            corruptedMemory.append(scanner.nextLine());
        }

        System.out.println(getSumCorruptedMemory(corruptedMemory));

    }

    //Part 1
    private static int getSumCorruptedMemory(StringBuilder corruptedMemory) {

        Pattern pattern = Pattern.compile("mul\\((\\d{1,3},\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(corruptedMemory);

        List<MatchResult> results = matcher.results().toList();

        int sum = 0;

        for (MatchResult matchResult : results) {
            ArrayList<String> numbers = new ArrayList<>(Arrays.asList(matchResult.group(1).split(",")));
            int mul = Integer.parseInt(numbers.get(0)) * Integer.parseInt(numbers.get(1));
            sum += mul;
        }
        return sum;
    }
}