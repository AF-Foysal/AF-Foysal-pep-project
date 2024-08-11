package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    /**
     * @param account to be searched 
     * @return account if found, else null
     */

     private Account findAccount(Account account){
        try (Connection connection = ConnectionUtil.getConnection();){
            String sql = "SELECT * FROM account WHERE username=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account.getUsername());
            
            ResultSet rs =  preparedStatement.executeQuery();
            while (rs.next()){
                return new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password"));
            }

        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
        return null;
     }

     /**
      * Username must not be blank, 
      * password must be at least length of 4, 
      * username must also be unique (non-existent prior to insertion)
      * @param account to be registered
      * @return Account Object if successful, otherwise null
      */
     public Account registerAccount(Account account){

        if ((findAccount(account) != null) || (account.getUsername().length() == 0) || (account.getPassword().length() < 4)){
            return null;
        }

        try (Connection connection = ConnectionUtil.getConnection();){
            String sql = "INSERT INTO account (username, password) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();

            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int account_id = (int) pkeyResultSet.getLong(1);
                return new Account(account_id, account.getUsername(), account.getPassword());
            }


        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
     }


}
