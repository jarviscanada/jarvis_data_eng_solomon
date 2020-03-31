package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class TraderDao extends JdbcCrudDao<Trader> {

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleInsert;
  private final String TableName = "trader";
  private final String IdColumn = "id";
  
  @Autowired
  public TraderDao (DataSource dataSource) {
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
  Class<Trader> getEntityClass () {
    return Trader.class;
  }
}
