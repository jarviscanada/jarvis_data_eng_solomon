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

  /**
   * Access JdbcTemplate for this particular DAO
   * @return JdbcTemplate instance
   */
  abstract public JdbcTemplate getJdbcTemplate();
  
  /**
   * Access SimpleJdbcInsert for this particular DAO
   * @return SimpleJdbcInsert instance
   */
  abstract public SimpleJdbcInsert getSimpleJdbcInsert();
  
  /**
   * Access table name associated with this DAO
   * @return Table name
   */
    abstract public String getTableName();
  
  /**
   * Access id column name associated with this DAO
   * @return Id column name as a string
   */
  abstract public String getIdColumnName ();
  
  /**
   * Return class type for the entity associated with this DAO
   * @return Entity class
   */
  abstract Class<T> getEntityClass();

  /**
   * Persists entity, or attempts to update an entity if it is already present.
   * @param entity
   * @param <S>
   * @return A copy of the entity which was saved/updated
   */
  @Override
  public <S extends T> S save (S entity) {
    if (entity.getId() != 0  && existsById(entity.getId())) {
      if (updateOne(entity) != 1) {
        throw new DataRetrievalFailureException("Unable to update "
                                                    + entity.getClass().getSimpleName());
      }
    } else {
      addOne(entity);
    }
    return entity;
  }
  
  /**
   * Adds the entity into the database, as well as automaically setting its id value
   * @param entity
   * @param <S> Entity type
   */
  private <S extends T> void addOne(S entity) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);
    Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
    entity.setID(newId.intValue());
  }
  
  /**
   * Accesses entity's fields in order to create an update PSQL statement
   * @param entity
   * @return The number of rows effected by the update
   */
  public int updateOne(T entity) {
    StringBuilder update_sql = new StringBuilder();
    String[] fieldNameSplit;
    
    update_sql.append("UPDATE ").append(getTableName()).append(" SET ");
    
    List<String> fieldNames =
        Arrays.stream(getEntityClass().getDeclaredFields())
            .map(Field::getName)
            .collect(Collectors.toList());
    
    int appendCount = 0;
    
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
      if (appendCount < fieldNames.size() - 2) {
        name = name.concat(", ");
        appendCount++;
      }
      update_sql.append(name);
    }
    update_sql.append(" WHERE ").append(getIdColumnName()).append("=?");
    return getJdbcTemplate().update(update_sql.toString(), makeUpdateValues(entity, fieldNames));
  }

  /**
   * Accesses the entity's declared methods and invokes them,
   * returning them in the order of the entity fields
   * @param entity
   * @param entityFieldNames
   * @return Array of Objects containing the updated values for the entity
   */
  private Object[] makeUpdateValues(T entity, List<String> entityFieldNames) {
    List<Object> updatedValues = new ArrayList<>();
    Map<String, Method> methodMap = new LinkedHashMap<>();
    
    for (String fieldName : entityFieldNames) {
      methodMap.put(fieldName.toLowerCase(), null);
    }
    
    List<Method> declaredGetters = Arrays.stream(getEntityClass().getDeclaredMethods())
                                         .filter(method ->
                                                     method.getName().matches("^(get).*"))
                                         .collect(Collectors.toList());
    
    for (String methodName : methodMap.keySet()) {
      declaredGetters.stream().filter((Method getter) -> methodName.toLowerCase()
                      .matches(getter.getName().replace("get", "").toLowerCase()))
          .forEach(method -> methodMap.put(methodName, method));
    }
    
    
    for (Method method : methodMap.values()) {
        try {
            updatedValues.add(method.invoke(entity));
        } catch (IllegalAccessException e) {
          throw new RuntimeException("Unable to access fields from class"
                                         + getEntityClass().getSimpleName());
        } catch (InvocationTargetException e) {
          throw new IllegalArgumentException(method.getName() + " used on invalid target");
        }
      }
    updatedValues.add(updatedValues.get(0));
    updatedValues = updatedValues.subList(1, updatedValues.size());
    
    return updatedValues.toArray();
  }

  /**
   * Saves all entities represented by the provided iterable
   * @param iterable
   * @param <S>
   * @return An iterable representing copies of the saved entities
   */
  @Override
  public <S extends T> Iterable<S> saveAll (Iterable<S> iterable) {
    List<S> toBeSaved = new ArrayList<>();
    iterable.forEach((entity) -> {
      toBeSaved.add(save(entity));
    });
    return toBeSaved;
  }

  /**
   * Given the id of an entity, find and return said entity
   * as an optional, containing the found value or Optional.empty()
   * @param integer
   * @return Optional of found entity
   */
  @Override
  public  Optional<T> findById (Integer integer) {
    if(existsById(integer)) {
      String find_sql = "SELECT 1 FROM " + getTableName() + " WHERE " + getIdColumnName()
                            + "= '" + integer + "'";
      return Optional.ofNullable(getJdbcTemplate().queryForObject(find_sql, mapper));
    }
    return Optional.empty();
  }

  /**
   * Determines the existence of an entity using its id
   * @param integer
   * @return Boolean representing existence
   */
  @Override
  public boolean existsById (Integer integer) {
  String exist_sql =
      "SELECT EXISTS(SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() +"='"
          + integer + "')";
  return getJdbcTemplate().queryForObject(exist_sql, Boolean.class);
  }

  /**
   * Finds and returns all saved entities
   * @return Iterable representing all found entities
   */
  @Override
  public Iterable findAll () {
    String find_all_sql = "SELECT * FROM " + getTableName();
    return getJdbcTemplate().query(find_all_sql, mapper);
  }

  /**
   * Finds and returns all saved entities, provided their ids
   * @param iterable
   * @return Iterable representing found entities
   */
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
  
  /**
   * Return count of persistent entities
   * @return Entity count
   */
  @Override
  public long count () {
    String count_sql = "SELECT COUNT(" + getIdColumnName() + ") FROM " + getTableName();
    return getJdbcTemplate().queryForObject(count_sql, Long.class);
  }

  /**
   * Delete entity given its id
   * @param id
   */
  @Override
  public void deleteById (Integer id) {
    String delete_sql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName()
                            + "= '" + id.toString() + "'";
    getJdbcTemplate().execute(delete_sql);
  }

  /**
   * Delete all entries of from the associated table
   */
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
