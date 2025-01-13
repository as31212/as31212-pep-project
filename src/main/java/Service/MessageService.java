package Service;

import java.util.ArrayList;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    // Create Message Service
    public Message createMessage(){
        // Todo this requires userDAO, refer to readme #3 for clarification
        // this will require you to figure out how to query the newly created message without explicitly knowing the id, unsure if i need to alter the DAO or not. TOO SLEEPY CURRENTLY TO FIGURE IT OUT, YOU GOT THIS FUTURE ME. IM MAKING THE COMMIT NOW GOODNIGHT.
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
