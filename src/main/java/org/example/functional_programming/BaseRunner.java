package org.example.functional_programming;

import org.example.functional_programming.dto.Gender;
import org.example.functional_programming.dto.Operator;
import org.example.functional_programming.dto.Person;
import org.example.functional_programming.dto.Phone;
import org.example.functional_programming.func_util.FunctionUtil;

import java.io.File;
import java.time.LocalDate;
import java.util.*;


public class BaseRunner {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Kate", 8, 57, Gender.FEMALE, new ArrayList<>(List.of(new Phone(Operator.LIFE, "123-789-456")))));
        persons.add(new Person("Alex", 52, 92, Gender.MALE, new ArrayList<>(List.of(new Phone(Operator.A1, "666-789-456"), new Phone(Operator.A1, "987-852-741")))));
        persons.add(new Person("Sam", 8, 40, Gender.FEMALE, new ArrayList<>(List.of(new Phone(Operator.LIFE, "323-000-456")))));
        persons.add(new Person("Max", 43, 72, Gender.MALE, new ArrayList<>(List.of(new Phone(Operator.LIFE, "584-789-425"), new Phone(Operator.A1, "000-000-425"), new Phone(Operator.A1, "147-000-425")))));
        persons.add(new Person("Tasha", 18, 60, Gender.FEMALE, new ArrayList<>(List.of(new Phone(Operator.LIFE, "888-789-456")))));
        persons.add(new Person("Ella", 36, 82, Gender.FEMALE, new ArrayList<>(List.of(new Phone(Operator.LIFE, "654-00-425"), new Phone(Operator.A1, "100-000-425"), new Phone(Operator.MTC, "963-789-000")))));
        persons.add(new Person("Tom", 6, 30, Gender.MALE, new ArrayList<>(List.of(new Phone(Operator.A1, "999-789-456")))));
        persons.add(new Person("Ann", 76, 60, Gender.FEMALE, new ArrayList<>(List.of(new Phone(Operator.MTC, "333-789-456")))));
        persons.add(new Person("Serge", 43, 92, Gender.MALE, new ArrayList<>(List.of(new Phone(Operator.LIFE, "123-789-456"), new Phone(Operator.A1, "600-000-425"), new Phone(Operator.MTC, "903-789-000")))));
        persons.add(new Person("Katty", 29, 50, Gender.FEMALE, new ArrayList<>(List.of(new Phone(Operator.A1, "777-789-456")))));

        int limitAge = 20;
        System.out.printf("Filter persons older than %d\n", limitAge);
        System.out.println("-----------------------------------------------");
        FunctionUtil.filterPersonsByAge(persons, limitAge);
        System.out.println("***********************************************");

        int limitWeight = 60;
        System.out.printf("Filter persons heavier than %d kg than and print their names\n", limitWeight);
        System.out.println("-----------------------------------------------");
        FunctionUtil.filterPersonsByWeightAndTransformToListNames(persons, limitWeight);
        System.out.println("***********************************************");


        int amountPhones = 2;
        System.out.printf("Filter persons who have more than %d phone(-s) than and print their phone numbers\n", amountPhones);
        System.out.println("-----------------------------------------------");
        FunctionUtil.filterPersonsByAmountOfPhonesConvertToListPhoneNumbers(persons, amountPhones);
        System.out.println("***********************************************");

        System.out.println("Print all person's names separated by commas");
        System.out.println("-----------------------------------------------");
        String names = FunctionUtil.convertPersonsToNamesThenCollectToStringWithComma(persons);
        System.out.println(names);
        System.out.println("-----------------------------------------------");
        System.out.println("Print all person's names separated by commas using custom collector");
        System.out.println("-----------------------------------------------");
        String usingCustomCollector = FunctionUtil.convertPersonsToNamesUsingCustomStringCollector(persons, ", ");
        System.out.println(usingCustomCollector);
        System.out.println("***********************************************");

        System.out.println("Sort all person by age in descending order if age equals then sort by name");
        System.out.println("-----------------------------------------------");
        FunctionUtil.sortPersonsByAgeDescIfAgeEqualThenByName(persons);
        System.out.println("***********************************************");

        System.out.println("Group persons by gender and print their names");
        System.out.println("-----------------------------------------------");
        FunctionUtil.groupPersonsByGender(persons);
        System.out.println("-----------------------------------------------");
        System.out.println("Group persons by gender using custom collectors");
        System.out.println("-----------------------------------------------");
        FunctionUtil.groupPersonsByGenderUsingCustomCollectors(persons, ", ", Person::getGender);
        System.out.println("***********************************************");

        String phoneNumber = "584-789-425";
        System.out.printf("Find person with phone number - %s\n", phoneNumber);
        System.out.println("-----------------------------------------------");
        boolean result = FunctionUtil.containsPersonWithSpecificPhoneNumber(persons, phoneNumber);
        System.out.printf("Result - %b\n", result);
        System.out.println("***********************************************");

        int limitPerson = 4;
        System.out.printf("Collect unique phone operators from %d person\n", limitPerson);
        System.out.println("-----------------------------------------------");
        FunctionUtil.collectUniquePhoneOperatorsFromNPerson(persons, limitPerson);
        System.out.println("***********************************************");

        System.out.println("Calculate average weight of all persons");
        System.out.println("-----------------------------------------------");
        double avgWeight = FunctionUtil.avgWeightOfPersons(persons);
        System.out.printf("Average weight - %.3f\n", avgWeight);
        System.out.println("***********************************************");

        System.out.println("Define the youngest person");
        System.out.println("-----------------------------------------------");
        System.out.println(FunctionUtil.findTheYoungestPerson(persons));
        System.out.println("***********************************************");

        System.out.println("Group phones by operator and print phone numbers belonging to each operator");
        System.out.println("-----------------------------------------------");
        FunctionUtil.groupPhoneByOperatorAndPrintOnlyPhoneNumber(persons);
        System.out.println("-----------------------------------------------");
        System.out.println("Group phones by operator using custom collectors");
        System.out.println("-----------------------------------------------");
        FunctionUtil.groupPhoneAndPrintOnlyPhoneNumberUsingCustomCollectors(persons, ", ", Phone::getOperator);
        System.out.println("***********************************************");

        System.out.println("Group persons by gender and count them in each group");
        System.out.println("-----------------------------------------------");
        FunctionUtil.groupPersonsByGenderAndCountEachGender(persons);
        System.out.println("-----------------------------------------------");
        System.out.println("Group persons by gender and count them in each group using custom collectors");
        System.out.println("-----------------------------------------------");
        FunctionUtil.groupPersonsAndCountEachGroupUsingCustomCollectors(persons, Person::getGender);
        System.out.println("***********************************************");

        System.out.println("Count the frequency of words in a file");
        System.out.println("-----------------------------------------------");
        File file = new File("test.txt");
        Map<String, Integer> res = FunctionUtil.countWordsInFile(file);
        res.forEach((k, v) -> System.out.printf("%s - %s\n", k, v));
        System.out.println("***********************************************");

        System.out.println("Define number of days between min and max date in the list");
        System.out.println("-----------------------------------------------");
        List<LocalDate> dates = List.of(LocalDate.of(2020, 10, 15),
                LocalDate.of(2021, 1, 2),
                LocalDate.of(2019, 3, 31),
                LocalDate.of(2025, 12, 25)
        );
        long diffBetweenFirstAndLastDate = FunctionUtil.diffBetweenFirstAndLastDate(dates);
        System.out.printf("Between first and last date %d days\n", diffBetweenFirstAndLastDate);
        System.out.println("***********************************************");

        List<String> info = new ArrayList<>(List.of("abc", "15", "0.25", "-100*;l", "-70"));
        System.out.println("Define avg in list which may contain invalid strings");
        System.out.println("-----------------------------------------------");
        double avg = FunctionUtil.avg(info);
        System.out.printf("Average value between numbers in a list - %f\n", avg);
        System.out.println("***********************************************");

        System.out.println("Generate 1 million random numbers and sum them");
        System.out.println("-----------------------------------------------");
        long sumRandom = FunctionUtil.sumRandomNumbersWithParallelStream();
        System.out.printf("Sum 1 million random numbers is %d\n", sumRandom);
        System.out.println("***********************************************");

    }
}
