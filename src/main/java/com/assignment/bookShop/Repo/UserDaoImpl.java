package com.assignment.bookShop.Repo;

import com.assignment.bookShop.Model.User;
import com.assignment.bookShop.Utils.MySqlConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



@Component
public class UserDaoImpl implements UserDao{

    @Autowired
    MySqlConnector mySqlConnector;


    @Override
    public boolean insertUser(User user) {
        boolean flag=false;
        if(isExist(user))return flag;
        System.out.println("I am in dao");
        String insertQuery =
                "INSERT INTO user (user_name, user_email, password, role) " +
                        "VALUES (?, ?, ?, ?)";
        try (Connection connection = mySqlConnector.connect()) {
            var preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            int rowsInserted = preparedStatement.executeUpdate();
            flag = rowsInserted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            mySqlConnector.disconnect();
        }
        return flag;
    }
    private boolean isExist(User user){
        boolean flag=false;
        String insertQuery =
                "Select user_id from user where user_email = ?";
        try (Connection connection = mySqlConnector.connect()) {
            var preparedStatement = connection.prepareStatement(insertQuery);

            preparedStatement.setString(1, user.getUserEmail());

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                flag=true;
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        } finally {
            mySqlConnector.disconnect();
        }

        return flag;
    }


    public User findUserByEmail(String email) {
        String query = "SELECT * FROM user WHERE user_email = ?";
        try (Connection connection = mySqlConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getInt("user_id"));
                    user.setUserName(resultSet.getString("user_name"));
                    user.setUserEmail(resultSet.getString("user_email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(resultSet.getString("role"));
                    return user;
                }
            }
        } catch (SQLException e) {
            // Log the exception details for better debugging
            e.printStackTrace();
            throw new RuntimeException("Error fetching user by email", e);
        }
        return null; // Or you could throw a custom exception to indicate no user found
    }




}
