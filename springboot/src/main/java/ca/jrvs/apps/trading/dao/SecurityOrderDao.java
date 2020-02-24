package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class SecurityOrderDao extends JdbcCrudDao<SecurityOrder> {

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleInsert;
  private final String TableName = "security_order";
  private final String IdColumn = "id";
  
  @Autowired
  public SecurityOrderDao (DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource)
                            .withTableName(TableName).usingGeneratedKeyColumns(IdColumn);
  }
  
  @Override
  public JdbcTemplate getJdbcTemplate () {
    return jdbcTemplate;
  }
  
  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert () {
    return simpleInsert;
  }
  
  @Override
  public String getTableName () {
    return TableName;
  }
  
  @Override
  public String getIdColumnName () {
    return IdColumn;
  }
  
  @Override
  Class<SecurityOrder> getEntityClass () {
    return SecurityOrder.class;
  }
}
