package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.naming.OperationNotSupportedException;
import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public class PositionDao extends JdbcCrudDao<Position> {

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleInsert;
  private final String TableName = "position";
  private final String IdColumn = "account_id";
  private BeanPropertyRowMapper<Position> mapper = new BeanPropertyRowMapper<>(getEntityClass());
  
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
  
  public List<Position> findByAccountId(Integer accountId) {
    return (List<Position>) super.findAllById(Arrays.asList(accountId));
  }

  public Optional<Position> findAllByAccountIdAndTicker(Integer accountId, String ticker) {
    String find_sql = "SELECT * FROM \"" + getTableName() + "\" WHERE ticker = \'" + ticker
                          + "\' AND " + getIdColumnName() + " = " + accountId.toString();
    return Optional.ofNullable(getJdbcTemplate()
                                   .query(find_sql,
                                       new BeanPropertyRowMapper<>(getEntityClass())).get(0));
  }
  
  @Override
  public <S extends Position> S save (S entity) {
    throw new UnsupportedOperationException("Method not implemented");
  }
  
  @Override
  public int updateOne (Position entity) {
    throw new UnsupportedOperationException("Method not implemented");
  }
  
  @Override
  public <S extends Position> Iterable<S> saveAll (Iterable<S> iterable) {
    throw new UnsupportedOperationException("Method not implemented");
  }
  
  @Override
  public Optional<Position> findById (Integer integer) {
    throw new UnsupportedOperationException("Method not implemented");
  }
  
  @Override
  public boolean existsById (Integer integer) {
    throw new UnsupportedOperationException("Method not implemented");
  }
  
  public Iterable findAll () {
    throw new UnsupportedOperationException("Method not implemented");
  }
  
  @Override
  public void deleteById (Integer id) {
    throw new UnsupportedOperationException("Method not implemented");
  }
  
  @Override
  public void delete (Position entity) {
    throw new UnsupportedOperationException("Method not implemented");
  }

  @Override
  public void deleteAll () {
    throw new UnsupportedOperationException("Method not implemented");
  }
  @Override
  public void deleteAll (Iterable<? extends Position> iterable) {
    throw new UnsupportedOperationException("Method not implemented");
  }
}
