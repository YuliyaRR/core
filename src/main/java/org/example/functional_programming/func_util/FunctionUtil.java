package org.example.functional_programming.func_util;

import org.example.functional_programming.custom_collectors.MyGroupingCollector;
import org.example.functional_programming.custom_collectors.MyMapCollector;
import org.example.functional_programming.custom_collectors.MyStringCollector;
import org.example.functional_programming.dto.Gender;
import org.example.functional_programming.dto.Operator;
import org.example.functional_programming.dto.Person;
import org.example.functional_programming.dto.Phone;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionUtil {
    private FunctionUtil() {
    }

    //Получите список Person и отфильтруйте только те, у которых age > n и выведите в консоль
    public static void filterPersonsByAge(List<Person> list, int limitAge) {
        list.stream()
                .filter(person -> person.getAge() > limitAge)
                .forEach(System.out::println);
    }

    //Получите список Person, отфильтруйте только те, у кого weight > n, преобразуйте в name и выведите в консоль
    public static void filterPersonsByWeightAndTransformToListNames(List<Person> list, int limitWeight) {
        list.stream()
                .filter(person -> person.getWeight() > limitWeight)
                .map(Person::getName)
                .forEach(System.out::println);
    }

    //Получите список Person, отфильтруйте только те, у кого кол-во телефонов > n, преобразуйте в номера телефонов и выведите в консоль
    public static void filterPersonsByAmountOfPhonesConvertToListPhoneNumbers(List<Person> list, int amountOfPhones) {
        list.stream()
                .filter(person -> person.getPhones().size() > amountOfPhones)
                .flatMap(person -> person.getPhones().stream())
                .map(Phone::getNumber)
                .forEach(System.out::println);
    }

    //Получите список Person, преобразуйте в name и затем преобразуйте в строку, чтобы имена были через запятую.
    public static String convertPersonsToNamesThenCollectToStringWithComma(List<Person> list) {
        return list.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));
    }

    public static String convertPersonsToNamesUsingCustomStringCollector(List<Person> list, String delimiter) {
        return list.stream()
                .map(Person::getName)
                .collect(new MyStringCollector(delimiter));
    }

    //Получите список Person и отсортируйте их по возрасту в порядке убывания, если возраст равен, то по именам и выведите в консоль
    public static void sortPersonsByAgeDescIfAgeEqualThenByName(List<Person> list) {
        list.stream()
                .sorted(Comparator.comparingInt(Person::getAge)
                        .reversed()
                        .thenComparing(Person::getName))
                .forEach(System.out::println);

    }

    //Получите список Person и сгруппируйте их по полу
    //Дополнено: вывести имена Person, принадлежащих к каждому гендеру, через запятую
    public static void groupPersonsByGender(List<Person> list) {
        list.stream()
                .collect(Collectors.groupingBy(Person::getGender))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue()
                        .stream()
                        .map(Person::getName)
                        .collect(Collectors.joining(", "))))
                .forEach((k, v) -> System.out.printf("%s - %s\n", k, v));
    }

    public static void groupPersonsByGenderUsingCustomCollectors(List<Person> list, String delimiter, Function<Person, Gender> grouping) {
        list.stream()
                .collect(new MyGroupingCollector<>(grouping))
                .entrySet()
                .stream()
                .collect(new MyMapCollector<>(Map.Entry::getKey, entry -> entry.getValue()
                        .stream()
                        .map(Person::getName)
                        .collect(new MyStringCollector(delimiter))))
                .forEach((k, v) -> System.out.printf("%s - %s\n", k, v));
    }
    //Получите список Person и проверьте есть ли в этом списке человек, у которого номер телефона = N
    public static boolean containsPersonWithSpecificPhoneNumber(List<Person> list, String number) {
        return list.stream()
                .flatMap(person -> person.getPhones()
                        .stream()
                        .filter(phone -> phone.getNumber().equals(number)))
                .findFirst()
                .isPresent();
    }

    //Получите список Person, получите n по порядку человека и получите операторов его телефонов исключая дублирование
    public static void collectUniquePhoneOperatorsFromNPerson(List<Person> list, int indexPerson) {
        list.stream()
                .skip(indexPerson - 1)
                .findFirst()
                .get()
                .getPhones().stream()
                .map(Phone::getOperator)
                .distinct()
                .forEach(System.out::println);
    }

    //Получите список Person и получите их средний вес
    public static double avgWeightOfPersons(List<Person> list) {
        return list.stream()
                .mapToDouble(Person::getWeight)
                .summaryStatistics()
                .getAverage();
    }

    //Получите список Person и найдите самого младшего по возрасту
    public static Person findTheYoungestPerson(List<Person> list) {
        return list.stream()
                .min(Comparator.comparingInt(Person::getAge))
                .get();
    }

    //Получите список Person, получите их телефоны, сгруппируйте по оператору и результатом группировки должны быть только номера телефонов
    public static void groupPhoneByOperatorAndPrintOnlyPhoneNumber(List<Person> list) {
        list.stream()
                .flatMap(person -> person.getPhones().stream())
                .collect(Collectors.groupingBy(Phone::getOperator))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue()
                        .stream()
                        .map(Phone::getNumber)
                        .collect(Collectors.joining(", "))))
                .forEach((k, v) -> System.out.printf("%s - %s\n", k, v));
    }

    public static void groupPhoneAndPrintOnlyPhoneNumberUsingCustomCollectors(List<Person> list,
                                                                              String delimiter,
                                                                              Function<Phone, Operator> grouping) {
        list.stream()
                .flatMap(person -> person.getPhones().stream())
                .collect(new MyGroupingCollector<>(grouping))
                .entrySet()
                .stream()
                .collect(new MyMapCollector<>(Map.Entry::getKey, entry -> entry.getValue()
                        .stream()
                        .map(Phone::getNumber)
                        .collect(new MyStringCollector(delimiter))))
                .forEach((k, v) -> System.out.printf("%s - %s\n", k, v));
    }

    //Получите список Person, сгруппируйте их по полу и результатом группировки должно быть их кол-во
    public static void groupPersonsByGenderAndCountEachGender(List<Person> list) {
        list.stream()
                .collect(Collectors.groupingBy(Person::getGender))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> (long) entry.getValue()
                        .size()))
                .forEach((k, v) -> System.out.printf("%s - %s\n", k, v));
    }

    public static void groupPersonsAndCountEachGroupUsingCustomCollectors(List<Person> list, Function<Person, Gender> grouping) {
        list.stream()
                .collect(new MyGroupingCollector<>(grouping))
                .entrySet()
                .stream()
                .collect(new MyMapCollector<>(Map.Entry::getKey, entry -> (long) entry.getValue()
                        .size()))
                .forEach((k, v) -> System.out.printf("%s - %s\n", k, v));
    }

    //Прочтите содержимое текстового файла и сделайте из него частотный словарик (слово -> и какое кол-во раз это слово встречается в нём)
    public static Map<String, Integer> countWordsInFile(File file) {
        String regEx = "\\s*(\\s-|\\s|--|!|\\?|\\.|,|:|;|(\")|\\(|\\)|\\*|'|\\n|\\t)\\s*";
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            return reader.lines()
                    .flatMap(line -> Arrays.stream(line.split(regEx)))
                    .collect(Collectors.toMap(String::toLowerCase,
                            word -> 1,
                            Integer::sum));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //Получите список дат и найдите количество дней между первой и последней датой
    public static long diffBetweenFirstAndLastDate(List<LocalDate> dates) {

        return ChronoUnit.DAYS.between(dates
                        .stream()
                        .min(LocalDate::compareTo)
                        .get(),
                dates.stream()
                        .max(LocalDate::compareTo)
                        .get());
    }

    //Получите список строк, преобразуйте их в числа, и посчитайте среднее значение (не забудьте отфильтровать не валидные строки)
    public static double avg(List<String> list) {
        return list.stream()
                .filter(str -> str.matches("-?\\d+\\.?\\d+"))
                .mapToDouble(Double::parseDouble)
                .summaryStatistics()
                .getAverage();
    }

    //Сгенерируйте миллион рандомных чисел и посчитайте их сумму используя parallelStream с двумя потоками
    public static long sumRandomNumbersWithParallelStream() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            list.add(ThreadLocalRandom.current().nextInt());
        }

        return list.parallelStream()
                .reduce(0,(a, b) -> a + b, Integer::sum);
    }

}

