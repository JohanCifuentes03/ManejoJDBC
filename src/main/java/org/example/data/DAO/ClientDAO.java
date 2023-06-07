package org.example.data.DAO;


import org.example.data.access.DBConnection;
import org.example.entity.Client;
import org.example.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClientDAO {

    Connection connection;

    public ClientDAO(){
        connection = DBConnection.getInstance().getConnection();
    }

    // CRUD


    // aux insert
    public void auxInsert(PreparedStatement preparedStatement, Client client){
        try {
            preparedStatement.setLong(1,client.getId());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3,client.getLastName());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // READ CLIENTS
    public List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                Client client = new Client();
                client.setId(resultSet.getLong("cedula"));
                client.setName(resultSet.getString("nombre"));
                client.setLastName(resultSet.getString("apellido"));
                clients.add(client);
            }


        }catch (SQLException e){
            System.out.println("There's been an error reading all clients: " + e.getMessage());
        }

        clients.sort(Comparator.comparingLong(Client::getId));
        return clients;
    }

    // INSERT CLIENT
    public void insertClient(Client client){
        String query = "INSERT INTO cliente (cedula, nombre, apellido )" +
                       "VALUES (?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            auxInsert(preparedStatement,client);
            preparedStatement.executeUpdate();
            System.out.println("Client: " + client + "added. " );
        }catch (SQLException e){
            System.out.println("There's been an error inserting a client: " + e.getMessage());
        }
    }

    // UPDATE CLIENT
    public void updateClient(Client client){
        String query = "UPDATE cliente" +
                       "SET cedula=?, nombre=?, apellido=?" +
                       "WHERE cedula=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            auxInsert(preparedStatement,client);
            preparedStatement.setLong(4,client.getId());
            preparedStatement.executeUpdate();
            System.out.println("Client: " +  client + "updated");
        }catch (SQLException e){
            System.out.println("There's been an error updating a client");
        }
    }

    // DELETE
    public void deleteClient(Client client){
        String query = "DELETE FROM empleado" +
                       "WHERE cedula=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, client.getId());
            preparedStatement.executeUpdate();
            System.out.println("Client: " + client + "deleted");
        }catch (SQLException e){
            System.out.println("There's been an error deleting a client");
        }
    }

}
