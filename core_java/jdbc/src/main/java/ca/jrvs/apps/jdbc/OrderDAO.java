package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.utils.DataAccessObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO extends DataAccessObject<Order> {
  
  private final static String GET_BY_ID = "SELECT c.first_name, c.last_name, c.email, o.order_id, "
                                            +  "o.creation_date, o.total_due, o.status, "
                                            +  "s.first_name, s.last_name, s.email, ol.quantity, "
                                            +  "p.code, p.name, p.size, p.variety, p.price "
                                            +  "FROM orders o JOIN customer c "
                                            +  "on o.customer_id = c.customer_id "
                                            +  "JOIN salesperson s "
                                            +  "on o.salesperson_id = s.salesperson_id "
                                            +  "JOIN order_item ol on ol.order_id = o.order_id"
                                            +  "JOIN product p on ol.product_id = p.product_id "
                                            +  "WHERE o.order_id = ?";
  
  private static final String GET_FOR_CUST = "SELECT * FROM get_orders_by_customer(?)";

  private static final String GET_ALL = "SELECT * FROM orders";
  
  private static final String UPDATE = "UPDATE order SET customerFirstName = ?, customerLastLane=?, "
                                           + "customerEmail = ?, creationDate = ?, totalDue = ?, "
                                           + "status = ?, salespersonFirstName = ?,"
                                           + " salespersonLastName = ?, salespersonEmail = ?, "
                                           + " orderLines = ? "
                                           + "WHERE  customer_id = ?";

private static final String DELETE = "DELETE FROM order WHERE order_id = ?";

private static final String INSERT = "INSERT INTO order (customerFirstName, customerLastLane, "
                                         + "customerEmail, creationDate, "
                                         + "totalDue, status, salespersonFirstName, "
                                         + "salespersonLastName, orderLines) VALUES (?, ?, ?, ?, "
                                         + "?, ?, ?, ?, ?)";

public OrderDAO(Connection connection) {
    super(connection);
  }
  
  @Override
  public Order findById(long id) {
    Order order = new Order();
    try (PreparedStatement statement = this.connection.prepareStatement(GET_BY_ID);) {
      statement.setLong(1, id);
      ResultSet resultSet = statement.executeQuery();
      long orderId = 0;
      List<OrderLine> orderLines = new ArrayList<>();
      while (resultSet.next()) {
        if (orderId == 0) {
          order.setCustomerFirstName(resultSet.getString(1));
          order.setCustomerLastLane(resultSet.getString(2));
          order.setCustomerEmail(resultSet.getString(3));
          order.setId(resultSet.getLong(4));
          orderId = order.getId();
          order.setCreationDate(new Date(resultSet.getDate(5).getTime()));
          order.setTotalDue(resultSet.getBigDecimal(6));
          order.setStatus(resultSet.getString(7));
          order.setSalespersonFirstName(resultSet.getString(8));
          order.setSalespersonLastName(resultSet.getString(9));
          order.setSalespersonEmail(resultSet.getString(10));
          
        }
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(resultSet.getInt(11));
        orderLine.setProductCode(resultSet.getString(12));
        orderLine.setProductName(resultSet.getString(13));
        orderLine.setProductSize(resultSet.getInt(14));
        orderLine.setProductVariety(resultSet.getString(15));
        orderLine.setProductPrice(resultSet.getBigDecimal(16));
        orderLines.add(orderLine);
      }
      order.setOrderLines(orderLines);
    }catch(SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return order;
  }
  
  @Override
  public List<Order> findAll() {
    List<Order> orderList = new ArrayList<>();
    try (PreparedStatement statement = this.connection.prepareStatement(GET_ALL)) {
      ResultSet resultSet = statement.executeQuery();
      List<OrderLine> orderLines = new ArrayList<>();
      while (resultSet.next()){
        Order order = new Order();
        OrderLine orderLine = new OrderLine();
        
        order.setId(resultSet.getInt(1));
        order.setCustomerFirstName(resultSet.getString(1));
        order.setCustomerLastLane(resultSet.getString(2));
        order.setCustomerEmail(resultSet.getString(3));
        order.setId(resultSet.getLong(4));
        order.setCreationDate(new Date(resultSet.getDate(5).getTime()));
        order.setTotalDue(resultSet.getBigDecimal(6));
        order.setStatus(resultSet.getString(7));
        order.setSalespersonFirstName(resultSet.getString(8));
        order.setSalespersonLastName(resultSet.getString(9));
        order.setSalespersonEmail(resultSet.getString(10));
        
        orderLine.setQuantity(resultSet.getInt(11));
        orderLine.setProductCode(resultSet.getString(12));
        orderLine.setProductName(resultSet.getString(13));
        orderLine.setProductSize(resultSet.getInt(14));
        orderLine.setProductVariety(resultSet.getString(15));
        orderLine.setProductPrice(resultSet.getBigDecimal(16));
        orderLines.add(orderLine);
        
        order.setOrderLines(orderLines);
        orderList.add(order);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orderList;
  }
  
  @Override
  public Order update(Order dto) {
    Order order = null;
    try {
      this.connection.setAutoCommit(false);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    try (PreparedStatement statement = this.connection.prepareStatement(UPDATE);) {
      statement.setString(1, dto.getCustomerFirstName());
      statement.setString(2, dto.getCustomerLastLane());
      statement.setString(3, dto.getCustomerEmail());
      statement.setDate(4, (java.sql.Date) dto.getCreationDate());
      statement.setBigDecimal(5, dto.getTotalDue());
      statement.setString(6, dto.getStatus());
      statement.setString(7, dto.getSalespersonFirstName());
      statement.setString(8, dto.getSalespersonLastName());
      statement.setString(9, dto.getSalespersonEmail());
      statement.setString(10, dto.getOrderLines().toString());
      statement.setLong(11, dto.getId());
      statement.execute();
      this.connection.commit();
      order = this.findById(dto.getId());
    } catch (SQLException e) {
      try {
        this.connection.rollback();
      } catch (SQLException sqle) {
        e.printStackTrace();
        throw new RuntimeException(sqle);
      }
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return order;
  }
  
  @Override
  public Order create(Order dto) {
    try {
      this.connection.setAutoCommit(false);
    } catch(SQLException e) {
      throw new RuntimeException("Unable to disable databse autocommit.");
    }
    try(PreparedStatement statement = this.connection.prepareStatement(INSERT)) {
      statement.setString(2, dto.getCustomerFirstName());
      statement.setString(3, dto.getCustomerLastLane());
      statement.setString(4, dto.getCustomerEmail());
      statement.setDate(5, (java.sql.Date) dto.getCreationDate());
      statement.setBigDecimal(6, dto.getTotalDue());
      statement.setString(7, dto.getStatus());
      statement.setString(8, dto.getSalespersonFirstName());
      statement.setString(9, dto.getSalespersonLastName());
      statement.setString(10, dto.getSalespersonEmail());
      statement.setString(11, dto.getOrderLines().toString());
      statement.execute();
      int id = this.getLastVal(ORDER_SEQUENCE);
      this.connection.commit();
      return this.findById(id);
    } catch(SQLException e) {
      try {
        this.connection.rollback();
      } catch(SQLException ex) {
        throw new RuntimeException(ex);
      }
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public void delete(long id) {
    try {
      this.connection.setAutoCommit(false);
      try(PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE)) {
        preparedStatement.setLong(1, id);
        preparedStatement.execute();
        this.connection.commit();
      } catch(SQLException ex) {
        try {
          this.connection.rollback();
        } catch(SQLException e) {
          throw new SQLTransactionRollbackException(e);
        }
        throw new RuntimeException(ex);
      }
    } catch(SQLException e) {
      throw new RuntimeException(e);
    }
  }
  
  public List<Order> getOrdersForCustomer(long customerId) {
    List<Order> orders = new ArrayList<>();
    try(PreparedStatement statement = this.connection.prepareStatement(GET_FOR_CUST);) {
      statement.setLong(1, customerId);
      ResultSet resultSet = statement.executeQuery();
      long orderId = 0;
      Order order = null;
      while(resultSet.next()) {
        long localOrderId = resultSet.getLong(4);
        if(orderId != localOrderId) {
          order = new Order();
          orders.add(order);
          order.setId(localOrderId);
          orderId = localOrderId;
          order.setCustomerFirstName(resultSet.getString(1));
          order.setCustomerLastLane(resultSet.getString(2));
          order.setCustomerEmail(resultSet.getString(3));
          order.setCreationDate(new Date(resultSet.getDate(5).getTime()));
          order.setTotalDue(resultSet.getBigDecimal(6));
          order.setStatus(resultSet.getString(7));
          order.setSalespersonFirstName(resultSet.getString(8));
          order.setSalespersonLastName(resultSet.getString(9));
          order.setSalespersonEmail(resultSet.getString(10));
          List<OrderLine> orderLines = new ArrayList<>();
          order.setOrderLines(orderLines);
        }
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(resultSet.getInt(11));
        orderLine.setProductCode(resultSet.getString(12));
        orderLine.setProductName(resultSet.getString(13));
        orderLine.setProductSize(resultSet.getInt(14));
        orderLine.setProductVariety(resultSet.getString(15));
        orderLine.setProductPrice(resultSet.getBigDecimal(16));
        order.getOrderLines().add(orderLine);
      }
    }catch(SQLException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return orders;
  }
}
