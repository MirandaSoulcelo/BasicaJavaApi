package com.Soucelo.repository;

import com.Soucelo.domain.model.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository
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
                    name TEXT NOT NULL,
                    email TEXT NOT NULL,
                    password TEXT NOT NULL,
                    cpf TEXT NOT NULL,
                    excluded BOOL NOT NULL,
                    isAdmin BOOL NOT NULL);""";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement())
        {
            stmt.execute(sql);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserById(Long id)
    {
        String sql = "SELECT * FROM Users WHERE excluded != 1 AND id = ?";
        User user = null;
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                  user = new User(rs.getLong("id"),
                                  rs.getString("name"),
                                  rs.getString("email"),
                                  rs.getString("password"),
                                  rs.getString("cpf"),
                                  rs.getBoolean("excluded"),
                                  rs.getBoolean("isAdmin"));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getUsers()
    {
        String sql = "SELECT * FROM Users WHERE Users.excluded != 1";
        List<User> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                User user = new User(rs.getLong("id"),
                                     rs.getString("name"),
                                     rs.getString("email"),
                                     rs.getString("password"),
                                     rs.getString("cpf"),
                                     rs.getBoolean("excluded"),
                                     rs.getBoolean("isAdmin"));
                users.add(user);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public boolean existsByCpf(String cpf)
    {
        String sql = "SELECT cpf from Users where cpf = ?";
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String email)
    {
        String sql = "SELECT email from Users where email = ?";
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email)
    {
        String sql = "SELECT * FROM Users WHERE excluded != 1 AND email = ?";

        User user = null;

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if(rs.next())
            {
                user = new User(rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("cpf"),
                                rs.getBoolean("excluded"),
                                rs.getBoolean("isAdmin"));
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void updateUser(User user)
    {
        String sql = "UPDATE Users SET name = ?, email = ?, password = ?, cpf = ?, excluded = ?, isAdmin = ? WHERE id = ?";
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getCpf());
            stmt.setBoolean(5, user.getVerifyIsExcluded());
            stmt.setBoolean(6, user.getVerifyIsAdmin());
            stmt.setLong(7, user.getId());
            stmt.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void createUser(User user)
    {
        String sql = "INSERT INTO Users (name, " +
                "                        email, " +
                "                        password, " +
                "                        cpf, " +
                "                        excluded, " +
                "                        isAdmin) VALUES (?, ?, ?, ?, ?, ?)";

        try(Connection conn = DriverManager.getConnection(url);
        PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getCpf());
            stmt.setBoolean(5, user.getVerifyIsExcluded());
            stmt.setBoolean(6, user.getVerifyIsAdmin());
            stmt.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void inactivateUser(Long id)
    {
        String sql = "UPDATE Users SET excluded = true WHERE id = ?";
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}