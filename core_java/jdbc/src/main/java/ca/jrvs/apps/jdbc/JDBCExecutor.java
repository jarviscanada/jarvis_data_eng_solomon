package ca.jrvs.apps.jdbc;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCExecutor {
  
  public static void main(String... args) {
    DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
        "hplussport", "postgres", "YU3YiFwxspb4X");
    try {
      Connection connection = dcm.getConnection();
      CustomerDAO customerDAO = new CustomerDAO(connection);
      OrderDAO orderDAO = new OrderDAO(connection);
      
      customerDAO.findAllSorted(20).forEach(System.out::println);
      
      System.out.println("Paged");
      for (int i = 1; i < 3; i++) {
        System.out.println("Page number: " + i);
        customerDAO.findAllPaged(10, i).forEach(System.out::println);
      }
  
      orderDAO.getOrdersForCustomer(789).forEach(System.out::println);
      
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
