package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PositionDao extends JdbcCrudDao<Position> {

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleInsert;
  private final String TableName = "position";
  private final String IdColumn = "account_id";
  
  @Autowired
  public PositionDao (DataSource dataSource) {
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
  Class<Position> getEntityClass () {
    return Position.class;
  }
  
  @Override
  public int updateOne (Position entity) {
    throw new UnsupportedOperationException("Not implemented.");
  }

  @Override
  public void delete (Position position) {
    throw new UnsupportedOperationException("Not implemented.");
  }
  
  @Override
  public void deleteAll (Iterable iterable) {
    throw new UnsupportedOperationException("Not implemented.");
  }
  
}
