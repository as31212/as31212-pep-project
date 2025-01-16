package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public SocialMediaController(){
        accountService = new AccountService();
        messageService = new MessageService();
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register",this::registerHandler );
        app.post("/login", this::loginHandler);
        app.post("/messages", this::createMessageHandler);
        app.get("/messages", this::retrieveAllMessages);
        app.get("/messages/{message_id}", this::retrieveMessageByID);
        app.delete("/messages/{message_id}", this::deleteMessageByID);
        app.patch("/messages/{message_id}", this::updateMessageID);


        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    // Registration handler
    private void registerHandler(Context ctx){
        Account account = ctx.bodyAsClass(Account.class);
        Account output = accountService.createAccount(account);
        if(output != null){
            ctx.status(200).json(output);
        }
        else
        {ctx.status(400);}
        }

    // login handler
    private void loginHandler(Context ctx){
        Account account = ctx.bodyAsClass(Account.class);
        Account output = accountService.login(account);
        if(output != null){
            ctx.status(200).json(output);
        }
        else{
            ctx.status(401);
        }
    }

    // Create message handler
    private void createMessageHandler(Context ctx){
        Message message = ctx.bodyAsClass(Message.class);
        Message output =  messageService.createMessage(message);
        if(output != null){
            ctx.status(200).json(output);
        }
        else{
            ctx.status(400);
        }
    }

    // Retrieve all messages
    private void retrieveAllMessages(Context ctx){
        ctx.status(200).json(messageService.fetchAllMessages());
    }

    // Retrieve message by id
    private void retrieveMessageByID(Context ctx){
        String id = ctx.pathParam("message_id");
        int numID = Integer.parseInt(id);
        Message output = messageService.fetchMessagesByMessageID(numID);
        if(output != null){
            ctx.status(200).json(output);
    }
    else{
        ctx.status(200);
    }
        }

    // Delete message
    private void deleteMessageByID(Context ctx){
        String idString = ctx.pathParam("message_id");
        int id = Integer.parseInt(idString);
        Message output = messageService.deleteMessage(id);

        if(output != null){
            ctx.status(200).json(output);
        }
        else{
            ctx.status(200);
        }
    }

    // Update message 
    private void updateMessageID(Context ctx){
        String idString = ctx.pathParam("message_id");
        String text = ctx.body();
        int id = Integer.parseInt(idString);
        Message output = messageService.updateMessage(id,text );
        if(output != null){
            ctx.status(200).json(output);
        }
        else{
            ctx.status(400);
        }
    }
      
    // Retrieve messages by user
    private void retrieveMessagesByUser(Context ctx){
        
    }


    }


