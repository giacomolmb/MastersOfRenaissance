package it.polimi.ingsw.view.gui;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.core.model.Resource;
import it.polimi.ingsw.net.client.Client;
import it.polimi.ingsw.net.msg.MessageType;
import it.polimi.ingsw.net.msg.RequestMsg;
import it.polimi.ingsw.net.msg.ResponseMsg;
import it.polimi.ingsw.view.UserInterface;
import it.polimi.ingsw.view.compact.CompactDevCardStructure;
import it.polimi.ingsw.view.compact.CompactMarket;
import it.polimi.ingsw.view.compact.CompactPlayer;
import javafx.application.Application;
import javafx.application.Platform;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class Gui implements UserInterface {
    private final Client client;
    private CompactPlayer mySelf;

    public Gui(Client client){
        this.client = client;
        (new Thread(() -> Application.launch((JavaFxApp.class)))).start();
        JavaFxApp.setManager(this);
    }

    @Override
    public void handleRequest(RequestMsg request) {
        switch (request.getMessageType()) {
            case REGISTRATION_MESSAGE:
                Platform.runLater(() -> JavaFxApp.setRoot("registration"));
                if(request.getPayload().has("error")) Platform.runLater(() -> JavaFxApp.setData("error", "true"));
                break;
            case WELCOME_MESSAGE:
                Platform.runLater(() -> JavaFxApp.setRootWithData("welcome", request.getPayload()));
                break;
            case NUMBER_OF_PLAYERS:
                Platform.runLater(() -> JavaFxApp.setRoot("creategame"));
                break;
            case JOIN_GAME:
                Platform.runLater(() -> JavaFxApp.setRoot("joinlobby"));
                break;
            case GAME_MESSAGE:
                switch (request.getPayload().get("gameAction").getAsString()){
                    case "WAIT_FOR_PLAYERS":
                    case "WAIT_START_GAME":
                        Platform.runLater(() -> JavaFxApp.setRootWithData("waitplayers", request.getPayload()));
                        break;
                    case "SHORT_UPDATE":
                    case "START_GAME_COMMAND":
                        Platform.runLater(() -> JavaFxApp.setData(request.getPayload()));
                        break;
                    case "CHOOSE_START_LEADERS":
                        Platform.runLater(() -> JavaFxApp.setRootWithData("leaderschoice", request.getPayload()));
                        break;
                    case "CHOOSE_START_RESOURCES":
                        Platform.runLater(() -> JavaFxApp.setRootWithData("resourceschoice", request.getPayload()));
                        break;
                    case "INITIAL_UPDATE":
                        Platform.runLater(() -> JavaFxApp.setRootWithData("gameboard", request.getPayload()));
                        break;
                    case "UPDATE":
                        Platform.runLater(() -> JavaFxApp.setData(request.getPayload()));
                    case "LEADER_ACTIVATION":
                        Platform.runLater(() -> JavaFxApp.showPopup("leaderactivation"));
                        break;
                    case "LEADER_ACTION":
                        Platform.runLater(() -> JavaFxApp.showPopup("leaderaction"));
                        break;
                }
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

    public CompactPlayer getMyself(){
        return mySelf;
    }

    public void firstSetup(int playerId, String username, int[] leaders){
        mySelf = new CompactPlayer(playerId, username);
        mySelf.getCompactBoard().setLeaderCards(leaders);
    }


}
