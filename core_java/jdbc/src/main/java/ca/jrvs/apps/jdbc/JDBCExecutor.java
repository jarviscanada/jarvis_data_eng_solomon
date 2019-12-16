package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCExecutor {
  
  public static void main(String... args) {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
        "hplussport", "postgres", "password");
    try {
      Connection connection = dcm.getConnection();
      CustomerDAO customerDAO = new CustomerDAO(connection);
      
      //findAllSorted
      customerDAO.findAllSorted(20).forEach(System.out::println);
      System.out.println("Paged");
      for (int i = 1; i < 3; i++) {
        System.out.println("Page number: " + i);
        //findAllPaged
        customerDAO.findAllPaged(10, i).forEach(System.out::println);
      }
      
      //update
      customerDAO.update();
      //delete
      customerDAO.delete();
      //create
      customerDAO.create(new Customer());
      
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
