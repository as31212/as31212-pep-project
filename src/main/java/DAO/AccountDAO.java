package DAO;

import java.sql.*;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

    // Fetch account by ID
public Account fetchAccountByID(int id) {
    Connection connection = ConnectionUtil.getConnection();

    try {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM account WHERE account_id = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Account account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3));
            return account;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Failed to fetch user");
    }

    return null;
}


    // fetch account by user
    public Account fetchAccountByUsername(String user){
        Connection connection = ConnectionUtil.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("Select * from account where username = ?");
            ps.setString(1, user);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Account account = new Account(rs.getInt(1), rs.getString(2), rs.getString(3));   
                return account;
            }

            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to fetch user");
        }

        return null;
    }

    // Create account
    public boolean createAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("Insert Into account(username,password)  values(?,?)");
            ps.setString(1, account.username);
            ps.setString(2, account.password);
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to create account");
        }
        return false;
    }

    // Login
    public Account login(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from Account where username = ? and password = ?");
            ps.setString(1, account.username);
            ps.setString(2, account.password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Account(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
               
        } catch (SQLException e) {
           e.printStackTrace();
           System.out.println("Failed to login to account");
        }
        return null;
    }
    
}
