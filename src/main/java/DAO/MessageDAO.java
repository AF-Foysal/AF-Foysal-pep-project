package DAO;

import Util.ConnectionUtil;
import Model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    /**
     * @return all Messages
     */

     public List<Message> getAllMessages(){
        
        List<Message> messages = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConnection();) {
            String sql = "SELECT * FROM message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return messages;
     }

     /**
      * 
      * @param message_id of the intended Message
      * @return Message with the corresponding message_id, else return an empty message
      */
     public Message getMessageByID(int message_id){

        try (Connection connection = ConnectionUtil.getConnection();){
            String sql = "SELECT * FROM message WHERE message_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                );
                return message;
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

        return null;
     }




     
}
