package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import com.sun.org.apache.xpath.internal.operations.Quo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

  public static final String TABLE_NAME = "quote";
  public static final String ID_COLUMN_NAME = "ticker";
  
  public static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;
  
  @Autowired
  public QuoteDao (DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
  }
  
  @Override
  public Quote save (Quote quote) {
    if (existsById(quote.getId())) {
      int updatedRowNo = updateOne(quote);
      if (updatedRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(quote);
    }
    return quote;
  }
  
  private void addOne(Quote quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);
    if (row != 1) {
      throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
    }
  }
  
  private int updateOne(Quote quote) {
    String update_sql = "UPDATE quote SET last_price=?, bid_price=?, bid_size=?, ask_price=?, " +
                            "ask_size=? WHERE ticker=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(quote));
  }
  
  private Object[] makeUpdateValues(Quote quote) {
    return new Object[]{quote.getLastPrice(), quote.getBidPrice(), quote.getBidSize(),
                             quote.getAskPrices(), quote.getAskSize(), quote.getId()};
  }
  
  @Override
  public <S extends Quote> Iterable<S> saveAll (Iterable<S> iterable) {
    return null;
  }
  
  @Override
  public Optional<Quote> findById (String s) {
    return Optional.empty();
  }
  
  @Override
  public boolean existsById (String s) {
    return false;
  }
  
  @Override
  public Iterable<Quote> findAll () {
    return null;
  }
  
  @Override
  public Iterable<Quote> findAllById (Iterable<String> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }
  
  @Override
  public long count () {
    return 0;
  }
  
  @Override
  public void deleteById (String s) {
  
  }
  
  @Override
  public void delete (Quote quote) {
    throw new UnsupportedOperationException("Not implemented");
  }
  
  @Override
  public void deleteAll (Iterable<? extends Quote> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }
  
  @Override
  public void deleteAll () {
  
  }
}
