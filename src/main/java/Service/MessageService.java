package Service;

import DAO.MessageDAO;
import Model.Message;

import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;

    /**
     * Default Constructor
     */
    public MessageService() {
        messageDAO = new MessageDAO();
    }

    /**
     * Parametrized Constructor
     * 
     * @param messageDAO a given MessageDAO
     */
    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    /**
     * @return all messages
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }

    /**
     * 
     * @param message_id of the message
     * @return the message with the message_id, else return empty message
     */
    public Message getMessageByID(int message_id) {
        return messageDAO.getMessageByID(message_id);
    }

    /**
     * @return all messages by account_id
     */
    public List<Message> getAllMessagesByAccountID(int account_id) {
        return messageDAO.getAllMessagesByAccountID(account_id);
    }

    /**
     * 
     * @param message to be inserted
     * @return message object if persists
     */
    public Message addMessage(Message message) {
        if ((message.getMessage_text().length() == 0) || (message.getMessage_text().length() > 255)) {
            return null;
        }
        return messageDAO.createMessage(message);
    }

    /**
     * Restrictions: 0 < message_text length <= 255, and the message_id must exist
     * 
     * @param message_id
     * @param message_text
     * @return If message persists, the message object, otherwise null
     */
    public Message updateMessageText(int message_id, String message_text) {
        if ((messageDAO.getMessageByID(message_id) == null) || (message_text.length() == 0)
                || (message_text.length() > 255)) {
            return null;
        }
        messageDAO.updateMessageText(message_id, message_text);
        return messageDAO.getMessageByID(message_id);
    }

    /**
     * Delete Message by ID
     * @param message_id to be deleted
     * @return Message that was deleted, otherwise return null for nonexistent message
     */
    public Message deleteMessageByID(int message_id) {

        Message message = messageDAO.getMessageByID(message_id);
        if (message != null) {
            messageDAO.deleteMessageByID(message_id);
        }

        return message;
    }
}
