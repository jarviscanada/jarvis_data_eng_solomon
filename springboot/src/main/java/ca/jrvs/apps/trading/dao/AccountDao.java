package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class AccountDao extends JdbcCrudDao<Account>{

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleInsert;
  private final String TableName = "account";
  private final String IdColumn = "id";
  
  @Autowired
  public AccountDao (DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource)
                            .withTableName(TableName).usingGeneratedKeyColumns(IdColumn);
  }
  
  public JdbcTemplate getJdbcTemplate () {
    return jdbcTemplate;
  }
  
  public SimpleJdbcInsert getSimpleJdbcInsert () {
    return simpleInsert;
  }
  
  public String getTableName () {
    return TableName;
  }
  
  public String getIdColumnName () {
    return IdColumn;
  }
  
  Class<Account> getEntityClass () {
    return Account.class;
  }
  
  @Override
  public void delete (Account account) {
    throw new UnsupportedOperationException("Not implemented.");
  }
  
  @Override
  public void deleteAll (Iterable iterable) {
    throw new UnsupportedOperationException("Not implemented.");
  }
}
