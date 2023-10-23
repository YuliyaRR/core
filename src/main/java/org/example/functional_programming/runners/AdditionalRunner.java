package org.example.functional_programming.runners;

import org.example.functional_programming.dto.Gender;
import org.example.functional_programming.dto.Operator;
import org.example.functional_programming.dto.Person;
import org.example.functional_programming.dto.Phone;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.example.functional_programming.func_util.AdditionalFunctionUtil.*;

public class AdditionalRunner {
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

        List<LocalDate> dates = new ArrayList<>(List.of(
                LocalDate.of(2020, 10, 15),
                LocalDate.of(2021, 1, 2),
                LocalDate.of(2019, 3, 31),
                LocalDate.of(2025, 12, 25),
                LocalDate.of(2022, 2, 6),
                LocalDate.of(2020, 6, 11),
                LocalDate.of(2027, 10, 18),
                LocalDate.of(2022, 9, 1)
        ));

        groupPersonsBySpecificOperatorAndDefineSizeEachGroup(persons, Operator.A1);

        groupPersonByAmountOfPhones(persons);

        generateNumbers(2, 10, 0);

        getDatesBeforeSpecificDate(dates, LocalDate.now());

        createPhoneBook(persons);
    }
}
