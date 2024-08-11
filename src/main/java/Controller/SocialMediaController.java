package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    MessageService messageService;
    AccountService accountService;

    public SocialMediaController(){
        this.messageService = new MessageService();
        this.accountService = new AccountService();
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIDHandler);
        app.post("/register", this::registerAccountHandler);



        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    /**
     * Handler to retrieve all messages.
     * @param context the context object 
     */
    private void getAllMessagesHandler(Context context){
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
    }

    /**
     * Handler to retrieve message based on message_id
     * If message does NOT exist, output an empty response body
     * @param context the context object
     */
    private void getMessageByIDHandler(Context context){

        Message message = messageService.getMessageByID(Integer.valueOf(context.pathParam("message_id")));

        if (message == null){
            context.json("");
        }
        else{
            context.json(message);
        }
    }

    /**
     * Handler to register account 
     * @param context object handles information HTTP requests and generates responses within Javalin
     * @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object
     */
    private void registerAccountHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account registeredAccount = accountService.registerAccount(account);

        if(registeredAccount!=null){
            context.json(mapper.writeValueAsString(registeredAccount));
        }else{
            context.status(400);
        }
    
    }


}