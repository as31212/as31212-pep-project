package Service;

import java.util.ArrayList;

import DAO.AccountDAO;
import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    public MessageDAO messageDAO;
    public AccountDAO accountDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO){
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
    }

    // Create Message Service
    public Message createMessage(Message message){
        if(accountDAO.fetchAccountByID(message.posted_by) != null && !message.message_text.isBlank() && message.message_text.length() <= 255){
           int id =  messageDAO.createMessage(message);
           return messageDAO.fetchMessagesByMessageID(id);
        }
        
        return null;
    }


    // Fetch All Messages
    public ArrayList<Message> fetchAllMessages(){
       return messageDAO.fetchAllMessages();
    }

    // Fetch All Messages By User ID
    public ArrayList<Message> fetchAllMessagesByUser(int id){
        return messageDAO.fetchMessagesByUser(id);
    }

    // Fetch Message By Message ID
    public Message fetchMessagesByMessageID(int id){
        return messageDAO.fetchMessagesByMessageID(id);
    }

    // Update Message By Message ID
    public Message updateMessage(int id, String text){
            if(messageDAO.fetchMessagesByMessageID(id) == null || text.length() > 255 || text.isBlank()){
                return null;
            }
            messageDAO.updateMessage(id, text);

            // if the id does not exist this will return false which is the same thing as saying the update was unsuccessful

            return messageDAO.fetchMessagesByMessageID(id);
    }

    // Delete Message
    public Message deleteMessage(int id){
        // fetch will either be a Message or null
        Message fetch = messageDAO.fetchMessagesByMessageID(id);

        if(fetch != null){
            messageDAO.deleteMessage(id);
        }
        return fetch;
    }

}
