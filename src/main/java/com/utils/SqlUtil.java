package com.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vuelosjanbi.airline.domain.models.Airline;
import com.vuelosjanbi.crewRole.domain.models.CrewRole;
import com.vuelosjanbi.employee.domain.models.Employee;
import com.vuelosjanbi.employee.infrastructure.adapters.out.MySQLEmployeeRepository;

public class SqlUtil {
  private static final String url = "jdbc:mysql://localhost:3307/vuelosJanpi";
  private static final String user = "root";
  private static final String password = "1324";

  public static <T> T save(T object, String tableName) throws NoSuchFieldException, SecurityException {
    StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
    queryBuilder.append(tableName).append(" (");

    StringBuilder placeholders = new StringBuilder("VALUES (");

    List<Object> params = new ArrayList<>();

    try {
      Field[] fields = object.getClass().getDeclaredFields();

      for (Field field : fields) {
        field.setAccessible(true); 

    
        if ("id".equals(field.getName()) || "name".equals(field.getName())) {
          continue;
        }

        Object value = field.get(object);
        if (value != null) {
          // Si el campo es un objeto y tiene un método getId(), obtener el ID
          if (isEntity(value)) {
            Field idField = value.getClass().getDeclaredField("id");
            System.out.println(idField);
            idField.setAccessible(true);
            Object idValue = idField.get(value);
            queryBuilder.append(field.getName()).append("_id, ");
            placeholders.append("?, ");
            params.add(idValue);
          } else {
            // De lo contrario, solo agregar el valor directamente
            queryBuilder.append(field.getName()).append(", ");
            placeholders.append("?, ");
            params.add(value);
          }
        }
      }

      queryBuilder.setLength(queryBuilder.length() - 2);
      placeholders.setLength(placeholders.length() - 2);

      queryBuilder.append(") ").append(placeholders).append(")");

      String query = queryBuilder.toString();

      try (Connection connection = DriverManager.getConnection(url, user, password);
          PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {

        for (int i = 0; i < params.size(); i++) {
          statement.setObject(i + 1, params.get(i));
        }

        statement.executeUpdate();

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
          if (generatedKeys.next()) {
            setId(object, generatedKeys.getLong(1));
          }
        }

      } catch (SQLException e) {
        e.printStackTrace();
      }

    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }

    return object;
  }

  private static <T> void setId(T object, Long id) {
    try {
      Field field = object.getClass().getDeclaredField("id");
      field.setAccessible(true);
      field.set(object, id);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  private static boolean isEntity(Object object) {
    try {
      object.getClass().getDeclaredMethod("getId");
      return true;
    } catch (NoSuchMethodException e) {
      return false;
    }
  }

  public static void main(String[] args) throws NoSuchFieldException, SecurityException {
    // Crear un objeto de prueba
    Employee employee = new Employee();
    employee.setId(10L);
    employee.setName("John Doe");
    employee.setRol(new CrewRole(1L, "Manager"));
    employee.setAirline(new Airline(1L, "Delta"));

    MySQLEmployeeRepository mySQLEmployeeRepository = new MySQLEmployeeRepository(url, user, password);
    // Guardar el objeto en la base de datos
    Employee savedEmployee = SqlUtil.save(employee, "employee");

    System.out.println("Employee saved with ID: " + savedEmployee.getId());
  }
}
