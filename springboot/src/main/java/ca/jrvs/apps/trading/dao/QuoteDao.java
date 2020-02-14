package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ca.jrvs.apps.trading.util.TradingAppUtils.verifyTicker;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

  public static final String TABLE_NAME = "quote";
  public static final String ID_COLUMN_NAME = "ticker";
  
  public static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;
  private BeanPropertyRowMapper<Quote> mapper = new BeanPropertyRowMapper<>(Quote.class);
  
  @Autowired
  public QuoteDao (DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
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
    String update_sql = "UPDATE quote SET last_price=?, bid_price=?, bid_size=?, ask_price=?, "
                            + "ask_size=? WHERE ticker=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(quote));
  }
  
  private Object[] makeUpdateValues(Quote quote) {
    return new Object[]{quote.getLastPrice(), quote.getBidPrice(), quote.getBidSize(),
                             quote.getAskPrice(), quote.getAskSize(), quote.getId()};
  }
  
  @Override
  public <S extends Quote> Iterable<S> saveAll (Iterable<S> iterable) {
    List<Quote> savedQuotes = new ArrayList<>();
    iterable.forEach((quote) -> {
      verifyTicker(quote.getId());
      savedQuotes.add(save(quote));
    });
    return (Iterable<S>) savedQuotes;
  }
  
  @Override
  public Optional<Quote> findById (String s) {
    verifyTicker(s);
    if(existsById(s.toUpperCase())) {
      String find_sql = "SELECT 1 FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME
                            + "= '" + s + "'";
      return Optional.ofNullable(jdbcTemplate.queryForObject(find_sql, mapper));
    }
    return Optional.empty();
  }
  
  @Override
  public boolean existsById (String s) {
    verifyTicker(s);
    String exist_sql =
        "SELECT EXISTS(SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME +"='" + s + "')";
    return jdbcTemplate.queryForObject(exist_sql, Boolean.class);
  }
  
  @Override
  public Iterable<Quote> findAll () {
    String find_all_sql = "SELECT * FROM " + TABLE_NAME;
    return jdbcTemplate.query(find_all_sql, mapper);
  }

  @Override
  public void deleteAll () {
    String delete_all_sql = "DELETE FROM " + TABLE_NAME;//(SELECT * FROM " + TABLE_NAME + ")";
    jdbcTemplate.execute(delete_all_sql);
  }
  
  @Override
  public long count () {
    String count_sql = "SELECT COUNT(" + ID_COLUMN_NAME + ") FROM " + TABLE_NAME;
    return jdbcTemplate.queryForObject(count_sql, Long.class);
  }
  
  @Override
  public void deleteById (String s) {
    verifyTicker(s);
    String delete_sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME
                            + "= '" + s + "'";
    jdbcTemplate.execute(delete_sql);
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
  public Iterable<Quote> findAllById (Iterable<String> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
