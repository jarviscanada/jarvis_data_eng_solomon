import scala.io.Source

val file = Source.fromFile("/home/solomon/dev/jarvis_data_eng_solomon/spark/src/main/resources/employees.csv")

val fileToList = file.getLines().toList

class Employee(id0 : Int, name0 : String, city0 : String, age0 : Int) {
  val id = id0
  val name = name0
  val city = city0
  val age = age0
}

val splitList = fileToList
  .map(_.split(","))

val arrayToEmployee = (array : Array[Any]) => (employee : Employee){
  new Employee(
    array(0).asInstanceOf[Int],
    array(1).toString,
    array(2).toString,
    array(4).asInstanceOf[Int]
  )
}

var employeeList = splitList.map(_ => arrayToEmployee(_))