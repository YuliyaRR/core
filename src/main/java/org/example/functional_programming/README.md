### Общее описание
Данный пакет содержит решение задач по Stream API.

Подпакет ***func_util*** содержит утильные классы, каждый метод которых решает отдельную задачу.

Подпакет ***custom_collectors*** содержит кастомные коллекторы.

Подпакет ***runners*** содержит классы для запуска и просмотра консольного вывода утильных методов.

Подпакет ***dto*** содержит описание объектов, используемых для отработки методов Stream API.

### Объекты

Person (String name, int age, double weight, Gender gender, List<Phone> phones)

Phone (Operator operator, String number)

Enums -> Gender, Operator


### Задачи

#### Базовые задачи (класс FunctionUtil)

**1.**
Получите список Person и отфильтруйте только те, у которых age > n и выведите в консоль.

<u>Решает метод:</u> *filterPersonsByAge (List\<Person> list, int limitAge)*

**2.**
Получите список Person, отфильтруйте только те, у кого weight > n, преобразуйте в name и выведите в консоль.

<u>Решает метод:</u> *filterPersonsByWeightAndTransformToListNames (List\<Person> list, int limitWeight)*

**3.**
Получите список Person, отфильтруйте только те, у кого кол-во телефонов > n, преобразуйте в номера телефонов и выведите в консоль.

<u>Решает метод:</u> *filterPersonsByAmountOfPhonesConvertToListPhoneNumbers (List\<Person> list, int amountOfPhones)*

**4.**
Получите список Person, преобразуйте в name и затем преобразуйте в строку, чтобы имена были через запятую.

<u>Решают методы:</u> *convertPersonsToNamesThenCollectToStringWithComma (List\<Person> list)* и *convertPersonsToNamesUsingCustomStringCollector (List\<Person> list, String delimiter)*, использующий кастомный коллектор

**5.**
Получите список Person и отсортируйте его по возрасту в порядке убывания, если возраст равен, то по именам и выведите в консоль.

<u>Решает метод:</u> *sortPersonsByAgeDescIfAgeEqualThenByName (List\<Person> list)*

**6.**
Получите список Person и сгруппируйте их по полу.

*Дополнено*: вывести имена Person, принадлежащих к каждому гендеру, через запятую.

<u>Решают методы:</u> *groupPersonsByGender (List\<Person> list)* и *groupPersonsByGenderUsingCustomCollectors (List\<Person> list, String delimiter, Function<Person, Gender> grouping)*, использующий кастомные коллекторы

**7.**
Получите список Person и проверьте есть ли в этом списке человек, у которого номер телефона равен заданному значению.

<u>Решает метод:</u> *containsPersonWithSpecificPhoneNumber (List\<Person> list, String number)*

**8.**
Получите список Person, получите n по порядку человека и получите операторов его телефонов, исключая дублирование. 

<u>Решает метод:</u> *collectUniquePhoneOperatorsFromNPerson (List\<Person> list, int indexPerson)*

**9.**
Получите список Person и получите их средний вес.

<u>Решает метод:</u> *avgWeightOfPersons (List\<Person> list)*

**10.**
Получите список Person и найдите самого младшего по возрасту.

<u>Решает метод:</u> *findTheYoungestPerson (List\<Person> list)*

**11.**
Получите список Person, получите их телефоны, сгруппируйте по оператору и результатом группировки должны быть только номера телефонов.

<u>Решают методы:</u> *groupPhoneByOperatorAndPrintOnlyPhoneNumber (List\<Person> list)* и *groupPhoneAndPrintOnlyPhoneNumberUsingCustomCollectors (List\<Person> list, 
String delimiter, Function<Phone, Operator> grouping)*, использующий кастомные коллекторы

**12.**
Получите список Person, сгруппируйте их по полу. Результатом группировки должно быть их кол-во.

<u>Решают методы:</u> *groupPersonsByGenderAndCountEachGender (List\<Person> list)* и *groupPersonsAndCountEachGroupUsingCustomCollectors (List\<Person> list,
Function<Person, Gender> grouping)*, использующий кастомные коллекторы

**13.**
Прочтите содержимое текстового файла и сделайте из него частотный словарик (слово -> и какое кол-во раз это слово встречается в нём)

<u>Решает метод:</u> *countWordsInFile (File file)*

**14.**
Получите список дат и найдите количество дней между первой и последней датой.

*Примечание:* рассчитывается разница между самой ранней и самой поздней из дат, переданных в списке

<u>Решает метод:</u> *diffBetweenFirstAndLastDate (List\<LocalDate> dates)*

**15.**
Получите список строк, преобразуйте их в числа и посчитайте среднее значение (необходимо отфильтровать невалидные строки)

<u>Решает метод:</u> *avg (List\<String> list)*

**16.**
Сгенерируйте миллион рандомных чисел и посчитайте их сумму, используя parallelStream с двумя потоками.

<u>Решает метод:</u> *sumRandomNumbersWithParallelStream ()*

#### Дополнительные задачи (класс AdditionalFunctionUtil)

**1.**
Получите список Person, сгруппируйте Person по переданному оператору (в одной группе те, у кого есть этот оператор, в другой все те, у кого другой оператор),
посчитайте количество Person в каждой группе, выведите информацию в консоль

<u>Решает метод:</u> *groupPersonsBySpecificOperatorAndDefineSizeEachGroup (List\<Person> list, Operator operator)*

**2.**
Получить список Person, сгруппировать по количеству телефонов, вывести в консоль имена Person, принадлежащих каждой группе

<u>Решает метод:</u> *groupPersonByAmountOfPhones (List\<Person> list)*

**3.**
Сгенерировать поток чисел, начиная с заданного числа, каждое следующее число должно быть в два раза больше предыдущего, 
размер потока ограничить до заданного размера, в консоль вывести числа, которые содержат заданную цифру

<u>Решает метод:</u> *generateNumbers (long startNum, int limitSize, int findDigit)*

**4.**
Передан список дат, вывести в консоль только даты, которые идут ДО заданной даты (не использовать filter)

<u>Решает метод:</u> *getDatesBeforeSpecificDate (List\<LocalDate> dates, LocalDate dateSpc)*

**5.**
Составить телефонную книгу из переданного списка Person, записи должны быть отсортированы в алфавитном порядке

<u>Решает метод:</u> *createPhoneBook (List\<Person> list)*

