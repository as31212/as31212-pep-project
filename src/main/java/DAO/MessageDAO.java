package DAO;

import java.sql.*;
import java.util.*;


import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    // SELECT ALL MESSAGES
    public ArrayList<Message> fetchAllMessages(){
        ArrayList<Message> output = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("Select * from message");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Message message = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getLong(4));
                output.add(message);
            }
            
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return output;
    }

    // SELECT ALL MESSAGES BY USER
    public ArrayList<Message> fetchMessagesByUser(int id){
        ArrayList<Message> output = new ArrayList<>();
        Connection connection = ConnectionUtil.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("Select * from message where posted_by = ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Message message = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getLong(4));
                output.add(message);
            }
            
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return output;
    }

    // SELECT MESSAGE BY MESSAGE ID
    public Message fetchMessagesByMessageID( int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("Select * from message where message_id = ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Message message = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getLong(4));
                return message;
            }
            
        } catch (SQLException e) {
           e.printStackTrace();
        }

        return null;
    }

    // DELETE MESSAGE BASED ON MESSAGE ID
    public void deleteMessage( int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("Delete from message where message_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Successfully deleted message");
        } catch (SQLException e) {
           e.printStackTrace();
           System.out.println("Failed to delete message");
        }
    }

    // UPDATES MESSAGE BASED ON MESSAGE ID
    public void updateMessage( int id,String text){
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("Update message set message_text = ? where message_id = ?");
            ps.setString(1, text);
            ps.setInt(2,id);
            ps.executeUpdate();
            System.out.println("Successfully updated message");
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update failed");
        }
    }
    
    // CREATE MESSAGE: Returns message ID if successful, -1 if unsuccessful
public int createMessage(Message message) {
    Connection connection = ConnectionUtil.getConnection();
    try {
        // Include Statement.RETURN_GENERATED_KEYS to retrieve the auto-generated keys
        PreparedStatement ps = connection.prepareStatement(
            "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES(?, ?, ?)", 
            Statement.RETURN_GENERATED_KEYS
        );
        ps.setInt(1, message.posted_by);
        ps.setString(2, message.message_text);
        ps.setLong(3, message.time_posted_epoch);

        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            // Retrieve the generated keys
            ResultSet rsKeys = ps.getGeneratedKeys();
            if (rsKeys.next()) {
                return rsKeys.getInt(1); // Return the generated message_id
            }
        }
        System.out.println("Successfully created message");
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Message creation failed");
    }
    return -1; // Return -1 if unsuccessful
}

}
