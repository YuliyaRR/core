package org.example.functional_programming.func_util;

import org.example.functional_programming.dto.Operator;
import org.example.functional_programming.dto.Person;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdditionalFunctionUtil {

    private AdditionalFunctionUtil() {
    }

    //сгруппировать Person по переданному оператору (в одной группе те, у кого есть этот оператор, в другой все те,
    // у кого любой другой оператор), посчитать количество Person в каждой группе, вывести информацию в консоль
    public static void groupPersonsBySpecificOperatorAndDefineSizeEachGroup(List<Person> list, Operator operator) {
        System.out.printf("How many people have %s?\n", operator);
        list.stream()
                .collect(Collectors.partitioningBy(person -> person.getPhones()
                                .stream()
                                .anyMatch(phone -> phone.getOperator().equals(operator)),
                        Collectors.counting()))
                .forEach((k, v) -> System.out.printf("%b - %d\n", k, v));
    }

    //сгруппировать Person по количеству телефонов, вывести в консоль имена Person, принадлежащих каждой группе
    public static void groupPersonByAmountOfPhones(List<Person> list) {
        System.out.println("How many phones do people have?");
        list.stream()
                .collect(Collectors.groupingBy(person -> person.getPhones().size(),
                        Collectors.mapping(Person::getName, Collectors.toList())))
                .forEach((k, v) -> System.out.printf("%d phone(-s) - %s\n", k, v));
    }

    //сгенерировать поток чисел, начиная с заданного числа, каждое следующее число должно быть в два раза больше предыдущего,
    //размер потока ограничить до заданного размера, в консоль вывести числа, которые содержат заданную цифру 2
    public static void generateNumbers(long startNum, int limitSize, int findDigit) {
        System.out.printf("Find numbers with number %d\n", findDigit);
        Stream.iterate(startNum, num -> num * 2)
                .limit(limitSize)
                .filter(num -> String.valueOf(num).contains(String.valueOf(findDigit)))
                .forEach(System.out::println);
    }

    //передан список дат, вывести в консоль только даты, которые идут ДО заданной даты (не использовать filter)
    public static void getDatesBeforeSpecificDate(List<LocalDate> dates, LocalDate dateSpc) {
        System.out.printf("Find dates before %tF\n", dateSpc);
        dates.stream()
                .sorted(LocalDate::compareTo)
                .takeWhile(date -> date.isBefore(dateSpc))
                .forEach(System.out::println);
    }

    //составить телефонную книгу из переданного списка Person, записи должны быть отсортированы в алфавитном порядке
    public static void createPhoneBook(List<Person> list) {
        System.out.println("Phone book");
        list.stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.groupingBy(person -> person.getName().charAt(0),
                        Collectors.toMap(Person::getName, Person::getPhones)))
                .forEach((k, v) -> System.out.printf("%s - %s\n", k, v));
    }

}
