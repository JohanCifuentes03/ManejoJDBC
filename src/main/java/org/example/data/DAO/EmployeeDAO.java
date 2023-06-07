package org.example.data.DAO;

import org.example.data.access.DBConnection;
import org.example.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeDAO {

    private Connection connection;

    public EmployeeDAO() {
        connection = DBConnection.getInstance().getConnection();
    }


    // CRUD


    public void auxInsert(Employee employee, PreparedStatement preparedStatement){
        try {
            preparedStatement.setLong(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3,employee.getLastName());
            preparedStatement.setLong(4,employee.getPhone());
            preparedStatement.setInt(5,employee.getCodActivity());
            preparedStatement.setInt(6,employee.getCodHotel());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    // Find all employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM Empleado";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("CEDULA"));
                employee.setName(resultSet.getString("NOMBRE"));
                employee.setLastName(resultSet.getString("APELLIDO"));
                employee.setPhone(resultSet.getLong("TELEFONO"));
                employee.setCodActivity(resultSet.getInt("CODACTIVIDAD"));
                employee.setCodHotel(resultSet.getInt("CODHOTEL"));
                employees.add(employee);
            }

        } catch (SQLException e) {
            System.out.println("There's been an error returning all employees" + e.getMessage());
        }
        employees.sort(Comparator.comparingLong(Employee::getId));
        return employees;
    }

    // Insert employee CREATE
    public void insertEmployee(Employee employee) {
        String query = "INSERT INTO empleado(cedula,nombre,apellido,telefono,codactividad,codhotel) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            auxInsert(employee,preparedStatement);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println("There's been an error inserting an employee: " + e.getMessage());
        }
    }

    // Update employee
    public void updateEmployee(Employee employee){
        String query = "UPDATE empleado " +
                       "SET cedula=?, nombre=?,apellido=?,telefono=?,codactividad=?,codhotel=? " +
                       "WHERE cedula = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            auxInsert(employee,preparedStatement);
            preparedStatement.setLong(7,employee.getId());
            preparedStatement.executeUpdate();
            System.out.println("Employee: + " + employee.getName() + " Id: " + employee.getId() + "updated. ");
        }catch (SQLException e){
            System.out.println("There's been an error updating an employee");
        }

    }

    // Delete employee
    public void deleteEmployee(Employee employee){
        String query = "DELETE FROM empleado " +
                       "WHERE cedula=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,employee.getId());
            preparedStatement.executeUpdate();
            System.out.println("Employee: + " + employee.getName() + " Id: " + employee.getId() + "deleted. ");
        }catch (SQLException e){
            System.out.println("There's been an error deleting an employee: " + e.getMessage());
        }
    }


}
