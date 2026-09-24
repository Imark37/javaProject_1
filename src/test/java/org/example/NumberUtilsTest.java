package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumberUtilsTest {

    private static final Random random = new Random();

    // boolean isEven(int n) — запустить метод один раз со случайным числом от 1 до 100;
    @Test
    @Tag("task1")
    void testIsEvenTask1()
    {
        System.out.println("========================");
        System.out.println("Test method start");
        int number = random.nextInt(1, 101);
        System.out.println("Testing isEven with number: " + number);
        boolean result = NumberUtils.isEven(number);
        System.out.println("Result: " + result);
        System.out.println("Test method end");
        System.out.println("========================");
    }

    // String checkAccess(int age) — запустить метод 20 раз со случайными числами от 0 до 99;

    @RepeatedTest(20)
    @Tag("task1")
    void testCheckAccessTask1(RepetitionInfo repetitionInfo) {
        System.out.println("========================");
        System.out.println("Test method start");
        int age = random.nextInt(0, 100);
        System.out.println("Repetition " + repetitionInfo.getCurrentRepetition() +
                "/" + repetitionInfo.getTotalRepetitions() +
                ", age = " + age);
        String result = NumberUtils.checkAccess(age);
        System.out.println("Result: " + result);
        System.out.println("Test method end");
        System.out.println("========================");
    }

    // String getGrade(int score) — запустить метод в параметризованных тестах с массивом случайных чисел от 0 до 100.

    @ParameterizedTest
    @Tag("task1")
    @MethodSource("provideRandomScores")
    void testGetGradeTask1(int score) {
        System.out.println("========================");
        System.out.println("Test method start");
        System.out.println("Testing getGrade with score: " + score);
        String result = NumberUtils.getGrade(score);
        System.out.println("Result: " + result);
        System.out.println("Test method end");
        System.out.println("========================");
    }

    // Метод (10 случайных чисел от 0 до 100)
    static IntStream provideRandomScores() {
        return random.ints(10, 0, 101);
    }


    // ЗАДАНИЕ 2 + ассерты (12 методов, тег task2)

    @RepeatedTest(10)
    @Tag("task2")
    void testIsEven() {
        int number = random.nextInt(1, 101);
        boolean expected = (number % 2 == 0);
        boolean actual = NumberUtils.isEven(number);

        assertThat(actual)
                .as("isEven(%d): ожидалось %s", number, expected)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    @Tag("task2")
    void testIsPositive() {
        int number = random.nextInt(-100, 101);
        boolean expected = (number >= 0);
        boolean actual = NumberUtils.isPositive(number);

        assertThat(actual)
                .as("isPositive(%d): ожидалось %s", number, expected)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    @Tag("task2")
    void testSumToN() {
        int n = random.nextInt(0, 21);
        int expected = 0;
        for (int i = 1; i <= n; i++) expected += i;
        int actual = NumberUtils.sumToN(n);

        assertThat(actual)
                .as("sumToN(%d): ожидалось %d", n, expected)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    @Tag("task2")
    void testGetEvenInRange() {
        int start = random.nextInt(-10, 11);
        int end = start + random.nextInt(1, 10);

        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                if (sb.length() > 0) sb.append(" ");
                sb.append(i);
            }
        }
        String expected = sb.toString();
        String actual = NumberUtils.getEvenInRange(start, end);

        assertThat(actual)
                .as("getEvenInRange(%d, %d): ожидалось '%s'", start, end, expected)
                .isEqualTo(expected);
    }


    @RepeatedTest(10)
    @Tag("task2")
    void testCheckAccess() {
        int age = random.nextInt(0, 100);
        String expected = (age > 18) ? "Allowed" : "Denied";
        String actual = NumberUtils.checkAccess(age);

        assertThat(actual)
                .as("checkAccess(%d): ожидалось '%s'", age, expected)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    @Tag("task2")
    void testGetGrade() {
        int score = random.nextInt(0, 101);
        String expected = getExpectedGrade(score);
        String actual = NumberUtils.getGrade(score);

        assertThat(actual)
                .as("getGrade(%d): ожидалось '%s'", score, expected)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    @Tag("task2")
    void testFindMax() {
        int[] arr = generateRandomArray(10);
        int expected = findExpectedMax(arr);
        int actual = NumberUtils.findMax(arr);

        assertThat(actual)
                .as("findMax(%s): ожидалось %d", Arrays.toString(arr), expected)
                .isEqualTo(expected);
    }

    @RepeatedTest(10)
    @Tag("task2")
    void testCalcAverage() {
        List<Integer> list = generateRandomList(10);
        double expected = calculateExpectedAverage(list);
        double actual = NumberUtils.calcAverage(list);

        assertThat(actual)
                .as("calcAverage(%s): ожидалось %f", list, expected)
                .isEqualTo(expected);
    }



    @ParameterizedTest
    @Tag("task2")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testBlastOff(int start, String expected) {
        String actual = NumberUtils.blastOff(start);

        assertThat(actual)
                .as("blastOff(%d): ожидалось '%s'", start, expected)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @Tag("task2")
    @CsvFileSource(resources = "/has-bug-data.csv", numLinesToSkip = 1)
    void testHasBug(String input, boolean expected) {
        String[] messages = input.split(",");
        boolean actual = NumberUtils.hasBug(messages);

        assertThat(actual)
                .as("hasBug(%s): ожидалось %s", input, expected)
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @Tag("task2")
    @CsvFileSource(resources = "/reverse-data.csv", numLinesToSkip = 1)
    void testReverse(String input, String expected) {
        String[] inputArray = input.split(",");
        String[] expectedArray = expected.split(",");
        String[] actual = NumberUtils.reverse(inputArray);

        assertThat(actual)
                .as("reverse(%s): ожидалось %s", input, expected)
                .containsExactly(expectedArray);
    }

    @ParameterizedTest
    @Tag("task2")
    @MethodSource("removeSpecificNameData")
    void testRemoveSpecificName(List<String> input, String nameToRemove, List<String> expected) {
        List<String> actual = NumberUtils.removeSpecificName(input, nameToRemove);

        assertThat(actual)
                .as("removeSpecificName(%s, '%s'): ожидалось %s", input, nameToRemove, expected)
                .containsExactlyElementsOf(expected);
    }

    //Методы-источники данных

    static Stream<Arguments> removeSpecificNameData() {
        return Stream.of(
                Arguments.of(Arrays.asList("Alice", "Bob", "Alice"), "Alice", Arrays.asList("Bob")),
                Arguments.of(Arrays.asList("John", "Jane", "John"), "John", Arrays.asList("Jane")),
                Arguments.of(Arrays.asList("One", "Two", "Three"), "Four", Arrays.asList("One", "Two", "Three")),
                Arguments.of(new ArrayList<>(), "Any", new ArrayList<>()),
                Arguments.of(Arrays.asList("A", "B", "C"), "A", Arrays.asList("B", "C")),
                Arguments.of(Arrays.asList("X", "Y", "Z"), "Z", Arrays.asList("X", "Y")),
                Arguments.of(Arrays.asList("a", "b", "c", "a"), "a", Arrays.asList("b", "c")),
                Arguments.of(Arrays.asList("One", "One", "One"), "One", new ArrayList<>()),
                Arguments.of(Arrays.asList("A", "B"), "C", Arrays.asList("A", "B")),
                Arguments.of(Arrays.asList("M", "N", "M", "N"), "M", Arrays.asList("N", "N"))
        );
    }

    // Other

    // Для всех объектов (int, String, boolean, double, List и т.д.)
    private static void printResult(String testName, Object expected, Object actual) {
        boolean passed = expected.equals(actual);
        System.out.println("Test: " + testName);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
        System.out.println(passed ? "TEST PASSED" : "TEST FAILED");
        System.out.println("========================");
    }

    // Для массивов
    private static void printResult(String testName, String[] expected, String[] actual) {
        boolean passed = Arrays.equals(expected, actual);
        System.out.println("Test: " + testName);
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Actual: " + Arrays.toString(actual));
        System.out.println(passed ? "TEST PASSED" : "TEST FAILED");
        System.out.println("========================");
    }

    private static String getExpectedGrade(int score) {
        if (score >= 0 && score <= 20) return "E";
        if (score >= 21 && score <= 40) return "D";
        if (score >= 41 && score <= 60) return "C";
        if (score >= 61 && score <= 80) return "B";
        if (score >= 81 && score <= 100) return "A";
        return "Error";
    }

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(-100, 101);
        }
        return arr;
    }

    private static int findExpectedMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }

    private static List<Integer> generateRandomList(int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(-100, 101));
        }
        return list;
    }

    private static double calculateExpectedAverage(List<Integer> list) {
        int sum = 0;
        for (int num : list) {
            sum += num;
        }
        return (double) sum / list.size();
    }
}