import scala.io.Source

val file = Source.fromFile("/home/solomon/dev/jarvis_data_eng_solomon/spark/src/main/resources/employees.csv")
/**
 * Count number of elements
 * Get the first element
 * Get the last element
 * Get the first 5 elements
 * Get the last 5 elements
 *
 * hint: use the following methods
 * head
 * last
 * size
 * take
 * tails
 */
val ls = List.range(0,10)
//write you solution here
val numOfElements = ls.size
val head = ls.head
val last = ls.last
val firstFive = ls.take(5)
val lastFive = ls.takeRight(5)

/**
 * Double each number from the numList and return a flatten list
 * e.g.res4: List[Int] = List(2, 3, 4)
 *
 * Compare flatMap VS ls.map().flatten
 */
val numList = List(List(1,2), List(3));
//write you solution here
val flattenedList = numList.flatten.map(n => n * 2)
val flatMapped = numList.flatMap(nums => nums.map(_ * 2))

/**
 * Sum List.range(1,11) in three ways
 * hint: sum, reduce, foldLeft
 *
 * Compare reduce and foldLeft
 * https://stackoverflow.com/questions/7764197/difference-between-foldleft-and-reduceleft-in-scala
 */
//write you solution here
val rangeNums = List.range(1, 11)
val summed = rangeNums.sum
val reduced = rangeNums.reduce((x : Int, y : Int) => x + y)
val foldedLeft = rangeNums.foldLeft(0){(x : Int, y : Int) => x + y}

/**
 * Practice Map and Optional
 *
 * Map question1:
 *
 * Compare get vs getOrElse (Scala Optional)
 * countryMap.get("Amy");
 * countryMap.getOrElse("Frank", "n/a");
 */
val countryMap = Map("Amy" -> "Canada", "Sam" -> "US", "Bob" -> "Canada")
countryMap.get("Amy")
countryMap.get("edward")
countryMap.getOrElse("edward", "n/a")

//get and getOrElse will return an optional containing the value for the given key
//getOrElse will return the provided second argument
// as a default value assuming the first returns None

/**
 * Map question2:
 *
 * create a list of (name, country) tuples using `countryMap` and `names`
 *
 * val countryMap = Map("Amy" -> "Canada", "Sam" -> "US", "Bob" -> "Canada")
 *
 * e.g. res2: List[(String, String)] = List((Amy,Canada), (Sam,US), (Eric,n/a), (Amy,Canada))
 */
val names = List("Amy", "Sam", "Eric", "Amy")
//write you solution here

val pairedList = names.map(key => (key, countryMap.getOrElse(key, "n/a")))

/**
 * Map question3:
 *
 * count number of people by country. Use `n/a` if the name is not in the countryMap  using `countryMap` and `names`
 * e.g. res0: scala.collection.immutable.Map[String,Int] = Map(Canada -> 2, n/a -> 1, US -> 1)
 * hint: map(get_value_from_map) ; groupBy country; map to (country,count)
 */
//write you solution here
//pairedList.groupBy(identity).mapValues(_.size)
pairedList.groupBy(_._2).mapValues(_.size)

/**
 * number each name in the list from 1 to n
 * e.g. res3: List[(Int, String)] = List((1,Amy), (2,Bob), (3,Chris))
 */
val names2 = List("Amy", "Bob", "Chris", "Dann")
//write you solution here
//val numberedNames = List.range(1, names2.size + 1).zip(names2)
val numberedNames = names2 zip (Stream from 1)

/**
 * SQL questions1:
 *
 * read file lines into a list
 * lines: List[String] = List(id,name,city, 1,amy,toronto, 2,bob,calgary, 3,chris,toronto, 4,dann,montreal)
 */
//write you solution here
val fileToList = file.getLines().toList

/**
 * SQL questions2:
 *
 * Convert lines to a list of employees
 * e.g. employees: List[Employee] = List(Employee(1,amy,toronto), Employee(2,bob,calgary), Employee(3,chris,toronto), Employee(4,dann,montreal))
 */
//write you solution here
case class Employee(id : Int, name : String, city: String, age: Int)

var employeeList = fileToList
  .drop(1)
  .map(_.split(","))
  .map({case Array(e1, e2, e3, e4) => Employee(e1.toInt, e2, e3, e4.toInt)})

/**
 * SQL questions3:
 *
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 *
 * result:
 * upperCity: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(2,bob,CALGARY,19), Employee(3,chris,TORONTO,20), Employee(4,dann,MONTREAL,21), Employee(5,eric,TORONTO,22))
 */
//write you solution here
val upperCity = employeeList
  .map({case Employee(id, name, city, age) => Employee(id, name, city.toUpperCase, age)})

/**
 * SQL questions4:
 *
 * Implement the following SQL logic using functional programming
 * SELECT uppercase(city)
 * FROM employees
 * WHERE city = 'toronto'
 *
 * result:
 * res5: List[Employee] = List(Employee(1,amy,TORONTO,20), Employee(3,chris,TORONTO,20), Employee(5,eric,TORONTO,22))
 */
//write you solution here
val torontoEmployees = upperCity.filter(_.city.equalsIgnoreCase("toronto"))

/**
 * SQL questions5:
 *
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city
 *
 * result:
 * cityNum: scala.collection.immutable.Map[String,Int] = Map(CALGARY -> 1, TORONTO -> 3, MONTREAL -> 1)
 */
//write you solution here
val cityNum = upperCity.groupBy(_.city).mapValues(_.size);

/**
 * SQL questions6:
 *
 * Implement the following SQL logic using functional programming
 *
 * SELECT uppercase(city), count(*)
 * FROM employees
 * GROUP BY city,age
 *
 * result:
 * res6: scala.collection.immutable.Map[(String, Int),Int] = Map((MONTREAL,21) -> 1, (CALGARY,19) -> 1, (TORONTO,20) -> 2, (TORONTO,22) -> 1)
 */
//write you solution here
 val cityAgeNum = upperCity
  .groupBy(employee => (employee.city, employee.age))
  .mapValues(_.size)