package DAO;

import Util.ConnectionUtil;
import Model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    /**
     * @return all Messages
     */
    public List<Message> getAllMessages() {

        List<Message> messages = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConnection();) {
            String sql = "SELECT * FROM message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Message message = new Message(
                        rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
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
     * @return Message with the corresponding message_id, else return an empty
     *         message
     */
    public Message getMessageByID(int message_id) {

        try (Connection connection = ConnectionUtil.getConnection();) {
            String sql = "SELECT * FROM message WHERE message_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Message message = new Message(
                        rs.getInt("message_id"),
                        rs.getInt("posted_by"),
                        rs.getString("message_text"),
                        rs.getLong("time_posted_epoch"));
                return message;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }

        return null;
    }

    /**
     * @return all Messages sent by account_id
     */
    public List<Message> getAllMessagesByAccountID(int account_id) {

        List<Message> messages = new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConnection();) {
            String sql = "SELECT * FROM message INNER JOIN account ON message.posted_by = account.account_id WHERE account.account_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Message message = new Message(
                        rs.getInt("message.message_id"),
                        rs.getInt("message.posted_by"),
                        rs.getString("message.message_text"),
                        rs.getLong("message.time_posted_epoch"));
                messages.add(message);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return messages;
    }

    /**
     * Message_text should must be 0 < length <= 255
     * User must exist
     * 
     * @param message object to be inserted into table
     * @return message if persists, otherwise null
     */

    public Message createMessage(Message message) {

        try (Connection connection = ConnectionUtil.getConnection();) {
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();

            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if (pkeyResultSet.next()) {
                int generatedMessageID = (int) pkeyResultSet.getLong(1);
                message.setMessage_id(generatedMessageID);
            }
            return message;

        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Update message_text
     * 
     * @param message_id   message to be updated
     * @param message_text updated message
     */
    public void updateMessageText(int message_id, String message_text) {

        try (Connection connection = ConnectionUtil.getConnection();) {
            String sql = "UPDATE message SET message_text=? WHERE message_id=?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, message_text);
            preparedStatement.setInt(2, message_id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
