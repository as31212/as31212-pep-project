package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
            return output;
            
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return null;
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
    
    // CREATE MESSAGE 
    public void createMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("Insert into message(posted_by,message_text,time_posted_epoch) values(?,?,?)"); 
            ps.setInt(1, message.posted_by);
            ps.setString(2, message.message_text);
            ps.setLong(3,message.time_posted_epoch);
            ps.executeUpdate();
            System.out.println("Successfully created message");
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Message creation failed");            
        }
    }
}
