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



}
