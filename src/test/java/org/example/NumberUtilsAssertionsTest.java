package org.example;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberUtilsAssertionsTest {

    private static final Random random = new Random();

    //  isEven — метод возвращает boolean
    @Tag("assertions")
    @Test
    void testIsEvenWithAssertion() {
        int number = random.nextInt(1, 101);
        boolean actual = NumberUtils.isEven(number);
        boolean expected = (number % 2 == 0);

        assertThat(actual)
                .as("Число %d должно быть %s", number, expected ? "чётным" : "нечётным")
                .isEqualTo(expected);
    }

    // removeSpecificName — метод возвращает список
    @Tag("assertions")
    @Test
    void testRemoveSpecificNameWithAssertion() {
        List<String> input = new ArrayList<>(Arrays.asList("One", "Two", "Three", "Four"));
        String nameToRemove = "Three";
        List<String> actual = NumberUtils.removeSpecificName(input, nameToRemove);
        assertThat(actual)
                .as("После удаления '%s' в списке не должно остаться этого имени", nameToRemove)
                .doesNotContain(nameToRemove)
                .isNotEmpty()
                .hasSize(3)
                .containsExactly("One", "Two", "Four");
    }
    // isPositive — метод возвращает boolean, тоже проверяем
    @Tag("assertions")
    @Test
    void testIsPositiveWithAssertion() {
        int number = random.nextInt(-100, 101);
        boolean actual = NumberUtils.isPositive(number);
        boolean expected = (number >= 0);

        assertThat(actual)
                .as("Число %d должно быть %s", number, expected ? "неотрицательным" : "отрицательным")
                .isEqualTo(expected);
    }

    // СПЕЦИАЛЬНО ПАДАЮЩИЙ ТЕСТ (демонстрация информативности AssertJ)
    @Tag("assertions")
    @Test
    void testFailingAssertion_Demo() {
        int number = 4; // чётное число
        boolean actual = NumberUtils.isEven(number);

        assertThat(actual)
                .as("Демонстрация: число %d — чётное, но мы ожидаем false, чтобы ассерт упал", number)
                .isFalse();  // <-- ЭТО УПАДЁТ, потому что 4 — чётное
    }
}