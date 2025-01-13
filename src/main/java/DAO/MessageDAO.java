package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Message;

public class MessageDAO {

    // SELECT ALL MESSAGES
    public ArrayList<Message> fetchAllMessages(Connection connection){
        ArrayList<Message> output = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("Select * from message");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Message message = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
                output.add(message);
            }
            return output;
            
        } catch (SQLException e) {
           e.printStackTrace();
        }

        return null;
    }

    // SELECT ALL MESSAGES BY USER
    public ArrayList<Message> fetchMessagesByUser(Connection connection,int id){
        ArrayList<Message> output = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("Select * from message where posted_by = ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Message message = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
                output.add(message);
            }
            return output;
            
        } catch (SQLException e) {
           e.printStackTrace();
        }

        return null;
    }

    // SELECT ALL MESSAGES BY MESSAGE ID
    public ArrayList<Message> fetchMessagesByMessageID(Connection connection, int id){
        ArrayList<Message> output = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("Select * from message where message_id = ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Message message = new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));
                output.add(message);
            }
            return output;
            
        } catch (SQLException e) {
           e.printStackTrace();
        }

        return null;
    }
    
}
