package Service;

import DAO.MessageDAO;
import Model.Message;

import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;

    /**
     * Default Constructor
     */
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    /**
     * Parametrized Constructor
     * @param messageDAO a given MessageDAO
     */
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    /**
     * @return all messages
     */
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    /**
     * 
     * @param message_id of the message
     * @return the message with the message_id, else return empty message
     */
    public Message getMessageByID(int message_id){
        return messageDAO.getMessageByID(message_id);
    }

    /**
     * @return all messages by account_id
     */
    public List<Message> getAllMessagesByAccountID(int account_id){
        return messageDAO.getAllMessagesByAccountID(account_id);
    }

    /**
     * 
     * @param message to be inserted
     * @return message object if persists
     */
    public Message addMessage(Message message){
        return messageDAO.createMessage(message);
    }

}
