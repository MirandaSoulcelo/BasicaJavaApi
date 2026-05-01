package com.Soucelo.repository;

import com.Soucelo.domain.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository
{
    private final String url = "jdbc:sqlite:database.db";

    public UserRepository()
    {
        createTableIfNotExists();
    }

    private void createTableIfNotExists()
    {
        String sql = """
               CREATE TABLE IF NOT EXISTS Users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    email TEXT NOT NULL);""";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement())
        {
            stmt.execute(sql);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<User> GetUsers()
    {
        String sql = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                User user = new User(rs.getLong("id"),
                                     rs.getString("nome"),
                                     rs.getString("email"));
                users.add(user);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return users;
    }

    public void save(User user)
    {
        String sql = "INSERT INTO Users (nome, email) VALUES (?, ?)";

        try(Connection conn = DriverManager.getConnection(url);
        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}