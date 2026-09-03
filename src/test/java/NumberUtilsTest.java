import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.IntStream;

class NumberUtilsTest {

    private static final Random random = new Random();

    // boolean isEven(int n) — запустить метод один раз со случайным числом от 1 до 100;

    @Test
    void testIsEven() {
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
    void testCheckAccess(RepetitionInfo repetitionInfo) {
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
    @MethodSource("provideRandomScores")
    void testGetGrade(int score) {
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
}