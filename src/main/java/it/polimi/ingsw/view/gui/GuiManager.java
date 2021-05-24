package it.polimi.ingsw.view.gui;

import it.polimi.ingsw.net.client.Client;
import it.polimi.ingsw.net.msg.RequestMsg;
import it.polimi.ingsw.net.msg.ResponseMsg;
import it.polimi.ingsw.view.UserInterface;
import javafx.application.Application;
import javafx.application.Platform;

import java.io.IOException;

public class GuiManager implements UserInterface {
    private Client client;

    public GuiManager(Client client){
        this.client = client;
        (new Thread(() -> Application.launch((Gui.class)))).start();
        Gui.setManager(this);
    }

    @Override
    public void handleRequest(RequestMsg request) {
        switch (request.getMessageType()) {
            case REGISTRATION_MESSAGE:
                Platform.runLater(() -> Gui.setRoot("registration"));
                break;
            case WELCOME_MESSAGE:
                Platform.runLater(() -> Gui.setRoot("welcome"));
                break;
        }
    }

    public void send(ResponseMsg responseMsg){
        client.send(responseMsg);
    }

    public void start(){
        this.client.run();
    }

    public void close(){
        this.client.closeConnection();
        System.exit(0);
    }
}
