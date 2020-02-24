package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer> {
  private final Logger logger = LoggerFactory.getLogger(getEntityClass());
  private BeanPropertyRowMapper<T> mapper = new BeanPropertyRowMapper<T>(getEntityClass());

abstract public JdbcTemplate getJdbcTemplate();
  abstract public SimpleJdbcInsert getSimpleJdbcInsert();
  abstract public String getTableName();
  abstract public String getIdColumnName ();
  abstract Class<T> getEntityClass();
  
  @Override
  public <S extends T> S save (S entity) {
    if (entity.getId() != null  && existsById(entity.getId())) {
      if (updateOne(entity) != 1) {
        throw new DataRetrievalFailureException("Unable to update " + entity.getClass().getSimpleName());
      }
    } else {
      addOne(entity);
    }
    return entity;
  }
  
  private <S extends T> void addOne(S entity) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);
    Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
    entity.setID(newId.intValue());
  }


public int updateOne(T entity) {
    StringBuilder update_sql = new StringBuilder();
    String[] fieldNameSplit;
    update_sql.append("UPDATE ").append(getTableName()).append(" SET ");
    List<String> fieldNames =
        Arrays.stream(getEntityClass().getDeclaredFields())
            .map(Field::getName)
            .collect(Collectors.toList());
    
    for (String name : fieldNames) {
      if (name.equals("id")) continue;
      fieldNameSplit = name.split("(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])");
      StringBuilder fieldNameSQL = new StringBuilder();
      
      for (int i = 0; i < fieldNameSplit.length; i++) {
        if (i == 0){
          fieldNameSQL.append(fieldNameSplit[i]);
        } else {
          fieldNameSQL.append("_").append(fieldNameSplit[i].toLowerCase());
        }
      }
      name = fieldNameSQL.toString();
      name = name.concat("=?");
      if (fieldNames.iterator().hasNext()) {
        name = name.concat(", ");
      }
      update_sql.append(name);
    }
    update_sql.append("WHERE ").append(getIdColumnName()).append("=?");
    return getJdbcTemplate().update(update_sql.toString(), makeUpdateValues(entity));
  }
  
private Object[] makeUpdateValues(T entity) {
    List<Object> updatedValues = new ArrayList<>();
    Map<String, Method> methodMap = new HashMap<>();
    
    List<String> fieldNames =
        Arrays.stream(getEntityClass().getDeclaredFields())
            .map(Field::getName)
            .collect(Collectors.toList());
  
    fieldNames.set(fieldNames.size(), fieldNames.get(0));
    fieldNames.remove(0);
  
    methodMap.keySet().addAll(fieldNames);
    
    List<Method> declaredMethods = Arrays.stream(getEntityClass().getDeclaredMethods())
                                       .filter(method ->
                                                   method.getName().matches("^(get).*"))
                                       .collect(Collectors.toList());
    

    
    for (Method method : declaredMethods) {
      try {
        Method matchedMethod =
            fieldNames.stream().filter(fieldName -> fieldName.matches(""));
        updatedValues.add(method.invoke(entity));
      } catch (IllegalAccessException e) {
        throw new RuntimeException("Unable to access fields from class"
                                       + getEntityClass().getSimpleName());
      } catch (InvocationTargetException e) {
        throw new IllegalArgumentException(method.getName() + " used on invalid target");
      }
    }
  
  return updatedValues.toArray();
  }

  @Override
  public <S extends T> Iterable<S> saveAll (Iterable<S> iterable) {
    List<S> toBeSaved = new ArrayList<>();
    iterable.forEach((entity) -> {
      toBeSaved.add(save(entity));
    });
    return toBeSaved;
  }

  @Override
  public  Optional<T> findById (Integer integer) {
    if(existsById(integer)) {
      String find_sql = "SELECT 1 FROM " + getTableName() + " WHERE " + getIdColumnName()
                            + "= '" + integer + "'";
      return Optional.ofNullable(getJdbcTemplate().queryForObject(find_sql, mapper));
    }
    return Optional.empty();
  }
  
  @Override
  public boolean existsById (Integer integer) {
  String exist_sql =
      "SELECT EXISTS(SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() +"='"
          + integer + "')";
  return getJdbcTemplate().queryForObject(exist_sql, Boolean.class);
  }
  
  @Override
  public Iterable findAll () {
    String find_all_sql = "SELECT * FROM " + getTableName();
    return getJdbcTemplate().query(find_all_sql, mapper);
  }
  
  @Override
  public Iterable findAllById (Iterable<Integer> iterable) {
    StringBuilder idsToFind = new StringBuilder();
  
    for (Iterator<Integer> it = iterable.iterator(); it.hasNext(); ) {
      Integer id = it.next();
      idsToFind.append(id);
      if (it.hasNext()) {
        idsToFind.append(", ");
      }
    }
  
    String find_all_id_sql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName()
                                  + " IN (" + idsToFind.toString() + ")";
    return getJdbcTemplate().query(find_all_id_sql, mapper);
  }
  
  @Override
  public long count () {
    String count_sql = "SELECT COUNT(" + getIdColumnName() + ") FROM " + getTableName();
    return getJdbcTemplate().queryForObject(count_sql, Long.class);
  }
  
  @Override
  public void deleteById (Integer id) {
    String delete_sql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName()
                            + "= '" + id.toString() + "'";
    getJdbcTemplate().execute(delete_sql);
  }

  @Override
  public void deleteAll () {
    String delete_all_sql = "DELETE FROM " + getTableName();
    getJdbcTemplate().execute(delete_all_sql);
  }

  @Override
  public void delete (T entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll (Iterable<? extends T> iterable) {
    throw new UnsupportedOperationException("Not implemented.");
  }

}
