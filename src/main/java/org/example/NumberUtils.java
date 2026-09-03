package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumberUtils {
    //Задача 1
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    //Задача 2
    public static String checkAccess(int age) {
        if (age > 18) {
            return "Allowed";
        } else {
            return "Denied";
        }
    }

    //Задача 3
    public static boolean isPositive(int n) {
        return (n >= 0) ? true : false;
    }

    //Задача 4
    public static String getGrade(int score) {
        if (score >= 0 && score <= 20) {
            return "E";
        } else if (score >= 21 && score <= 40) {
            return "D";
        } else if (score >= 41 && score <= 60) {
            return "C";
        } else if (score >= 61 && score <= 80) {
            return "B";
        } else if (score >= 81 && score <= 100) {
            return "A";
        } else {
            return "Error";
        }
    }

    //Задача 5
    public static String blastOff(int start) {
        String result = "";
        for (int i = start; i >= 1; i--) {
             result += i + " ";
        }
        return result + "Поехали!";
    }

    //Задача 6
    public static int sumToN(int n) {
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result += i;
        }
        return result;
    }

    //Задача 7
    public static boolean hasBug(String[] messages) {
        for (int i = 0; i < messages.length; i++) {
            String element = messages[i];
            if (element.equalsIgnoreCase("Bug")) {
                return true;
            }
        }
        return false;
    }

    //Задача 8
    public static String getEvenInRange(int start, int end) {
        String result = "";
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                if (!result.isEmpty()) {
                    result += " "; // добавляем пробел только если результат не пустой
                }
                result += i;
            }
        }
        return result;
    }

    //Задача 9
    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    //Задача 10
    public static String[] reverse(String[] arr) {
        String[] reversedArr = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversedArr[arr.length - 1 - i] = arr[i];
        }
        return reversedArr;
    }

    //Задача 11
    public static double calcAverage(List<Integer> list) {
        int result = 0;
        for (int num : list) {
            result += num;
        }
        return (double) result / list.size();
    }

    //Задача 12
    public static List<String> removeSpecificName(List<String> list, String nameToRemove) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i);
            if (!element.equals(nameToRemove)) {
                result.add(element);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //1
        System.out.println(isEven(2)); // true
        System.out.println(isEven(1)); // false
        //2
        System.out.println(checkAccess(19)); // Allowed
        System.out.println(checkAccess(17)); // Denied
        //3
        System.out.println(isPositive(3)); // true
        System.out.println(isPositive(-3)); // false
        //4
        System.out.println(getGrade(50)); //C
        System.out.println(getGrade(101)); //ERROR
        //5
        System.out.println(blastOff(6)); //6 5 4 3 2 1 Поехали!
        //6
        System.out.println(sumToN(3));//6
        //7
        String[] test2 = {"Test1", "Test2", "bug"};
        String[] test3 = {"Test3", "Test4", "Test5"};
        System.out.println(hasBug(test2)); // true
        System.out.println(hasBug(test3)); // false
        //8
        System.out.println(getEvenInRange(1,7));//2 4 6
        //9
        int[] numbers = {6, 12, 3, 8, 255, 1};
        System.out.println(findMax(numbers)); // 255
        //10
        String[] test4 = {"A", "B", "C", "D"};
        System.out.println(Arrays.toString(reverse(test4))); // [D, C, B, A]
        //11
        List<Integer> numbersTwo = new ArrayList<>();
        numbersTwo.add(1);
        numbersTwo.add(2);
        numbersTwo.add(3);
        numbersTwo.add(4);
        System.out.println(calcAverage(numbersTwo)); // 2.5
        //12
        List<String> names = new ArrayList<>();
        names.add("Черный");
        names.add("Желтый");
        names.add("Красный");
        List<String> newList = removeSpecificName(names, "Alice");
        System.out.println(newList); //[Черный, Желтый, Красный]
    }
}
