package game.clientside.gui;

import game.clientside.clienthandler.ClientHandler;
import game.clientside.clienthandler.IClientHandler;
import game.clientside.clienthandler.IClientHandlerGUI;
import game.clientside.clientmessagegenerator.ClientMessageGenerator;
import game.clientside.clientmessagegenerator.IClientMessageGenerator;
import game.clientside.clientmessagehandlers.ClientMessageHandlerFactory;
import game.clientside.clientmessageprocessor.ClientMessageProcessor;
import game.clientside.clientwebsockets.ClientWebsocket;
import game.clientside.clientwebsockets.IClientWebsocket;
import game.models.*;
import game.models.interfaces.IGameObject;
import game.models.interfaces.IObstacle;
import game.models.interfaces.ISubmarine;
import game.models.interfaces.ITorpedo;
import game.shared.messagehandlers.IMessageHandlerFactory;
import game.shared.messageprocessor.IMessageProcessor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class GUI extends Application implements IGUI {

    final int MAX_PLAYERS = 8;
    final double MAX_SPEED = 2d;
    final double MAX_ROTATION_SPEED = 20d;
    final double MAX_RELOAD_TIME = 30d;
    final double MAX_ACCELERATION = 20d;

    private Settings settings;

    IClientHandlerGUI clientHandler;

    Pane root;
    Scene scene;

    //region Pane
    Pane loginPane; //done
    Pane mainPane; //done
    Pane highscorePane; //done
    Pane lobbyPane; //done
    Pane lobbiesPane; //done
    Pane gamePane;
    Pane createLobbyPane; //done
    //endregion
    //region Button
    Button toMainMenuButton;
    Button toMainMenuButton2;
    Button toMainMenuButton3;
    Button loginButton;
    Button registerButton;
    Button toLobbiesButton;
    Button toHighScoreButton;
    Button startRoundButton;
    Button leaveLobbyButton;
    Button joinLobbyButton;
    Button refreshLobbiesButton;
    Button createButton;
    Button backButton;
    Button createLobbyButton;
    //endregion
    //region Label
    Label usernameLabel;
    Label passwordLabel;
    Label passwordLabel2;
    Label passwordLabel3;
    Label yourScoreLabel;
    Label settingsLabel;
    Label usersInLobbyLabel;
    Label lobbiesLabel;
    Label maxPlayersLabel;
    Label maxSpeedLabel;
    Label rotationSpeedLabel;
    Label reloadTimeLabel;
    Label accelerationLabel;
    Label torpedoSpeedLabel;
    Label lobbyNameLabel;
    //endregion

    TextField usernameField;
    TextField lobbyNameField;

    PasswordField passwordField;
    PasswordField passwordField2;
    PasswordField passwordField3;
    //region ListView
    ListView top100View;
    ListView settingsView;
    ListView usersInLobbyView;
    ListView lobbiesView;
    //endregion
    //region Slider
    Slider maxPlayersSlider;
    Slider maxSpeedSlider;
    Slider rotationSpeedSlider;
    Slider reloadTimeSlider;
    Slider accelerationSlider;
    Slider torpedoSpeedSlider;
    //endregion


    public void setClientHandler(IClientHandlerGUI clientHandler){
        this.clientHandler = clientHandler;
    }

    @Override
    public void start(Stage primaryStage){

        IClientWebsocket socket = new ClientWebsocket();
        IClientMessageGenerator messageGenerator = new ClientMessageGenerator(socket);
        IMessageHandlerFactory factory = new ClientMessageHandlerFactory();
        IClientHandler clientHandler = new ClientHandler(messageGenerator);
        IMessageProcessor messageProcessor = new ClientMessageProcessor(factory, clientHandler);
        socket.setMessageHandler(messageProcessor);
        socket.start();
        setClientHandler((IClientHandlerGUI) clientHandler);
        clientHandler.setGui(this);


        loginPane = new Pane();
        mainPane = new Pane();
        highscorePane = new Pane();
        lobbiesPane = new Pane();
        lobbyPane = new Pane();
        gamePane = new Pane();
        createLobbyPane = new Pane();
        loginPane.setPrefSize(1360, 768);
        mainPane.setPrefSize(1360, 768);
        highscorePane.setPrefSize(1360, 768);
        lobbyPane.setPrefSize(1360, 768);
        lobbiesPane.setPrefSize(1360, 768);
        gamePane.setPrefSize(1360, 768);
        createLobbyPane.setPrefSize(1360, 768);

        loginPane.setVisible(true);
        mainPane.setVisible(false);
        highscorePane.setVisible(false);
        lobbiesPane.setVisible(false);
        lobbyPane.setVisible(false);
        gamePane.setVisible(false);
        createLobbyPane.setVisible(false);

        //gamePane.getChildren().addAll(gameobjects);

        root = new Pane();
        scene = new Scene(root, 1360, 768);
        root.getChildren().addAll(loginPane, mainPane, highscorePane, lobbiesPane, lobbyPane, createLobbyPane);





        scene.setOnKeyPressed( (event) -> {this.clientHandler.handleKeyInput(event.getCode(), false);});
        scene.setOnKeyReleased( (event) -> {this.clientHandler.handleKeyInput(event.getCode(), true);});










        toMainMenuButton = new Button("Main menu");
        toMainMenuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toMainmenu();
            }
        });
        toMainMenuButton3 = new Button("Main menu");
        toMainMenuButton3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toMainmenu();
            }
        });
        highscorePane.getChildren().add(toMainMenuButton);
        lobbiesPane.getChildren().add(toMainMenuButton3);


        loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                login();
            }
        });
        loginButton.setLayoutX(680);
        loginButton.setLayoutY(450);

        registerButton = new Button("Register");
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                register();
            }
        });
        registerButton.setLayoutX(680);
        registerButton.setLayoutY(500);


        usernameLabel = new Label("Username:");
        usernameLabel.setLayoutX(680);
        usernameLabel.setLayoutY(250);
        passwordLabel = new Label("Password:");
        passwordLabel.setLayoutX(680);
        passwordLabel.setLayoutY(350);

        usernameField = new TextField();
        usernameField.setLayoutX(680);
        usernameField.setLayoutY(300);
        passwordField = new PasswordField();
        passwordField.setLayoutX(680);
        passwordField.setLayoutY(400);
        loginPane.getChildren().addAll(usernameField, passwordField, passwordLabel, usernameLabel, registerButton, loginButton);


        toLobbiesButton = new Button("Show Lobbies");
        toLobbiesButton.setLayoutX(680);
        toLobbiesButton.setLayoutY(300);
        toLobbiesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toLobbies();
            }
        });
        toHighScoreButton = new Button("Show Highscore");
        toHighScoreButton.setLayoutX(680);
        toHighScoreButton.setLayoutY(400);
        toHighScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toHighScore();
            }
        });
        mainPane.getChildren().addAll(toLobbiesButton, toHighScoreButton);

        top100View = new ListView();
        top100View.setLayoutX(300);
        top100View.setLayoutY(100);
        yourScoreLabel = new Label("Your score");
        yourScoreLabel.setLayoutX(600);
        yourScoreLabel.setLayoutY(100);
        highscorePane.getChildren().addAll(top100View, yourScoreLabel);


        settingsView = new ListView();
        settingsView.setLayoutX(100);
        settingsView.setLayoutY(100);
        settingsLabel = new Label("Settings:");
        settingsLabel.setLayoutX(100);
        settingsLabel.setLayoutY(0);
        usersInLobbyView = new ListView();
        usersInLobbyView.setLayoutX(400);
        usersInLobbyView.setLayoutY(100);
        usersInLobbyLabel = new Label("Players:");
        usersInLobbyLabel.setLayoutX(400);
        usersInLobbyLabel.setLayoutY(0);
        startRoundButton = new Button("Start round");
        startRoundButton.setLayoutX(700);
        startRoundButton.setLayoutY(300);
        startRoundButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startRound();
            }
        });
        leaveLobbyButton = new Button("Leave");
        leaveLobbyButton.setLayoutX(700);
        leaveLobbyButton.setLayoutY(0);
        leaveLobbyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                leaveLobby();
            }
        });
        lobbyPane.getChildren().addAll(settingsLabel,settingsView,usersInLobbyLabel,usersInLobbyView,startRoundButton,leaveLobbyButton);


        passwordField2 = new PasswordField();
        passwordField2.setLayoutX(680);
        passwordField2.setLayoutY(400);
        passwordLabel2 = new Label("Password:");
        passwordLabel2.setLayoutX(680);
        passwordLabel2.setLayoutY(350);
        lobbiesView = new ListView();
        lobbiesView.setLayoutX(200);
        lobbiesView.setLayoutY(100);
        lobbiesLabel = new Label("Current lobbies:");
        lobbiesLabel.setLayoutX(200);
        lobbiesLabel.setLayoutY(0);
        joinLobbyButton = new Button("Join lobby");
        joinLobbyButton.setLayoutX(500);
        joinLobbyButton.setLayoutY(100);
        joinLobbyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                joinLobby();
            }
        });
        refreshLobbiesButton = new Button("Refresh");
        refreshLobbiesButton.setLayoutX(500);
        refreshLobbiesButton.setLayoutY(150);
        refreshLobbiesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                refreshLobbies();
            }
        });
        createButton = new Button("Create new Lobby");
        createButton.setLayoutX(500);
        createButton.setLayoutY(200);
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toLobbyCreator();
            }
        });
        lobbiesPane.getChildren().addAll(passwordField2,passwordLabel2,lobbiesView,lobbiesLabel,joinLobbyButton,refreshLobbiesButton,createButton);


        backButton = new Button("Back");
        backButton.setLayoutX(680);
        backButton.setLayoutY(100);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                toLobbies();
            }
        });
        createLobbyButton = new Button("Create");
        createLobbyButton.setLayoutX(680);
        createLobbyButton.setLayoutY(200);
        createLobbyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createLobby();
            }
        });

        maxPlayersLabel = new Label("max players: 2");
        maxPlayersLabel.setLayoutX(300);
        maxPlayersLabel.setLayoutY(100);
        maxPlayersSlider = new Slider();
        maxPlayersSlider.setLayoutX(100);
        maxPlayersSlider.setLayoutY(100);
        //region Slider settings
        maxPlayersSlider.setMin(2);
        maxPlayersSlider.setMax(MAX_PLAYERS);
        maxPlayersSlider.setBlockIncrement(1);
        maxPlayersSlider.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                maxPlayersLabel.textProperty().setValue("max players: "+
                        String.valueOf((int)maxPlayersSlider.getValue()));

            }
        });
        //endregion

        maxSpeedLabel = new Label("max speed: 1");
        maxSpeedLabel.setLayoutX(300);
        maxSpeedLabel.setLayoutY(150);
        maxSpeedSlider = new Slider();
        maxSpeedSlider.setLayoutX(100);
        maxSpeedSlider.setLayoutY(150);
        //region Slider settings
        maxSpeedSlider.setMin(1d);
        maxSpeedSlider.setMax(MAX_SPEED);
        maxSpeedSlider.setBlockIncrement(0.1d);
        maxSpeedSlider.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                maxSpeedLabel.textProperty().setValue("max speed: "+
                        String.valueOf( maxSpeedSlider.getValue()));

            }
        });
        //endregion

        rotationSpeedLabel = new Label("rotation speed: 1");
        rotationSpeedLabel.setLayoutX(300);
        rotationSpeedLabel.setLayoutY(200);
        rotationSpeedSlider = new Slider();
        rotationSpeedSlider.setLayoutX(100);
        rotationSpeedSlider.setLayoutY(200);
        //region Slider settings
        rotationSpeedSlider.setMin(1d);
        rotationSpeedSlider.setMax(MAX_ROTATION_SPEED);
        rotationSpeedSlider.setBlockIncrement(0.5d);
        rotationSpeedSlider.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                rotationSpeedLabel.textProperty().setValue("rotation speed: "+
                        String.valueOf(rotationSpeedSlider.getValue()));

            }
        });
        //endregion

        reloadTimeLabel = new Label("reload time: 1");
        reloadTimeLabel.setLayoutX(300);
        reloadTimeLabel.setLayoutY(250);
        reloadTimeSlider = new Slider();
        reloadTimeSlider.setLayoutX(100);
        reloadTimeSlider.setLayoutY(250);
        //region Slider settings
        reloadTimeSlider.setMin(1d);
        reloadTimeSlider.setMax(MAX_RELOAD_TIME);
        reloadTimeSlider.setBlockIncrement(0.1d);
        reloadTimeSlider.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                reloadTimeLabel.textProperty().setValue("reload time: "+
                        String.valueOf( reloadTimeSlider.getValue()));

            }
        });
        //endregion

        accelerationLabel = new Label("acceleration: 0.1");
        accelerationLabel.setLayoutX(300);
        accelerationLabel.setLayoutY(300);
        accelerationSlider = new Slider();
        accelerationSlider.setLayoutX(100);
        accelerationSlider.setLayoutY(300);
        //region Slider settings
        accelerationSlider.setMin(0.1d);
        accelerationSlider.setMax(MAX_ACCELERATION);
        accelerationSlider.setBlockIncrement(0.1d);
        accelerationSlider.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                accelerationLabel.textProperty().setValue("acceleration: "+
                        String.valueOf( accelerationSlider.getValue()));

            }
        });
        //endregion

        torpedoSpeedLabel = new Label("torpedo speed: 1");
        torpedoSpeedLabel.setLayoutX(300);
        torpedoSpeedLabel.setLayoutY(350);
        torpedoSpeedSlider = new Slider();
        torpedoSpeedSlider.setLayoutX(100);
        torpedoSpeedSlider.setLayoutY(350);
        //region Slider settings
        torpedoSpeedSlider.setMin(1d);
        torpedoSpeedSlider.setMax(MAX_SPEED);
        torpedoSpeedSlider.setBlockIncrement(0.1d);
        torpedoSpeedSlider.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                torpedoSpeedLabel.textProperty().setValue("torpedo speed: "+
                        String.valueOf(torpedoSpeedSlider.getValue()));

            }
        });
        //endregion

        lobbyNameField = new TextField();
        lobbyNameField.setLayoutX(680);
        lobbyNameField.setLayoutY(300);
        lobbyNameLabel = new Label("Lobby name:");
        lobbyNameLabel.setLayoutX(680);
        lobbyNameLabel.setLayoutY(250);

        passwordField3 = new PasswordField();
        passwordField3.setLayoutX(680);
        passwordField3.setLayoutY(400);
        passwordLabel3 = new Label("Password:");
        passwordLabel3.setLayoutX(680);
        passwordLabel3.setLayoutY(350);

        createLobbyPane.getChildren().addAll(backButton,createLobbyButton,maxPlayersLabel,maxPlayersSlider,maxSpeedLabel,maxSpeedSlider,rotationSpeedLabel,
                                            rotationSpeedSlider,reloadTimeLabel,reloadTimeSlider,accelerationLabel,accelerationSlider,torpedoSpeedLabel,
                                            torpedoSpeedSlider,lobbyNameField,lobbyNameLabel,passwordField3,passwordLabel3);


        primaryStage.setTitle("Submarine battles");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void login(){
        clientHandler.login(usernameField.getText(), passwordField.getText());
    }

    public void register(){

        clientHandler.register(usernameField.getText(), passwordField.getText());
    }

    public void toMainmenu(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                highscorePane.setVisible(false);
                lobbyPane.setVisible(false);
                lobbiesPane.setVisible(false);
                mainPane.setVisible(true);
            }
        });
    }

    public void toLobbies(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                mainPane.setVisible(false);
                lobbiesPane.setVisible(true);
                refreshLobbies();
            }
        });
    }

    public void toHighScore(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                mainPane.setVisible(false);
                highscorePane.setVisible(true);
                clientHandler.getHighScore();
            }
        });
    }

    public void leaveLobby(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lobbyPane.setVisible(false);
                clientHandler.leaveLobby();
                toLobbies();
            }
        });
    }

    public void createLobby(){
        Settings settings = new Settings((int)maxPlayersSlider.getValue(),(float)maxSpeedSlider.getValue(),(float)rotationSpeedSlider.getValue(),(float)reloadTimeSlider.getValue(),(float)accelerationSlider.getValue(),(float)torpedoSpeedSlider.getValue());
        clientHandler.createLobby(lobbyNameField.getText(), passwordField3.getText(), settings);
        this.settings = settings;
    }

    public void startRound(){
        clientHandler.startRound();
    }

    public void toLobbyCreator(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lobbiesPane.setVisible(false);
                createLobbyPane.setVisible(true);
            }
        });
    }

    public void joinLobby(){
        String lobby = (String)lobbiesView.getSelectionModel().getSelectedItem();
        clientHandler.joinLobby(lobby, passwordField2.getText());
    }

    public void refreshLobbies(){
        clientHandler.getLobbies();
    }




    public void lobbies(List<String> lobbies){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lobbiesView.setItems(FXCollections.observableArrayList(lobbies));
            }
        });
    }

    public void joinResult(boolean success, Settings settings){
        if (success){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    lobbiesPane.setVisible(false);
                    lobbyPane.setVisible(true);
                    setSettings(settings);
                }
            });
        }
    }

    public void lobbyCreated(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                createLobbyPane.setVisible(false);
                lobbyPane.setVisible(true);
                setSettings(settings);
            }
        });
    }

    public void playersInLobbyUpdate(String[] players){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                usersInLobbyView.setItems(FXCollections.observableArrayList(players));
            }
        });
    }

    public void highscore(List<String> top100, String personalScore){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                top100View.setItems(FXCollections.observableArrayList(top100));
                yourScoreLabel.setText(personalScore);
            }
        });
    }

    public void loginResult(boolean success){
        if (success){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    loginPane.setVisible(false);
                    mainPane.setVisible(true);
                }
            });
        }
    }

    public void registrationResult(boolean success){
        if (success) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    registerButton.setText("Registration success!");
                }
            });
        }
    }

    public void lobbyClosed(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lobbyPane.setVisible(false);
                lobbiesPane.setVisible(true);
            }
        });
    }

    private void setSettings(Settings settings){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ObservableList<String> settingsList = FXCollections.observableArrayList();
                settingsList.add("Max players: " + settings.getMaxPlayers());
                settingsList.add("Acceleration: " + settings.getAcceleration());
                settingsList.add("Max speed: " + settings.getMaxSpeed());
                settingsList.add("Reload time: " + settings.getReloadTime());
                settingsList.add("Rotation speed: " + settings.getRotationSpeed());
                settingsList.add("Torpedo speed: " + settings.getTorpedoSpeed());
                settingsView.setItems(settingsList);
            }
        });
    }

    public void roundStarted(ISubmarine submarine){
        Platform.runLater(new Runnable() {
            @Override
            public void run(){
                //gameobjects.clear();
                gamePane.getChildren().clear();
                lobbyPane.setVisible(false);

                Coördinate c = submarine.getCoördinate();
                float angle =  submarine.getAngle();
                Ellipse submarine = new Ellipse(c.getX(), c.getY(), 20, 10);
                submarine.setFill(Color.GREEN);
                submarine.setRotate(angle);
                root.getChildren().add(submarine);
                gamePane.setVisible(true);
            }
        });
    }

    public void updateGameScreen(List<GameObject> objects) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                root.getChildren().clear();
                List<GameObject> objectsCopy = new ArrayList<>(objects);
                    for (int i = 0; i< objectsCopy.size(); i++){
                        IGameObject gameObject = objectsCopy.get(i);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (gameObject.getClass() == Submarine.class) {
                                //model is a submarine, draw it as such
                                Coördinate c = gameObject.getCoördinate();
                                float angle = ((ISubmarine) gameObject).getAngle();
                                Ellipse submarine = new Ellipse(c.getX(), c.getY(), 20, 10);
                                submarine.setFill(Color.GREEN);
                                submarine.setRotate(angle);
                                root.getChildren().add(submarine);

                            } else if (gameObject.getClass() == Torpedo.class) {
                                //model is a torpedo, draw it as such
                                Coördinate c = gameObject.getCoördinate();
                                float angle = ((ITorpedo) gameObject).getAngle();
                                Ellipse torpedo = new Ellipse(c.getX(), c.getY(), 20, 10);
                                torpedo.setFill(Color.RED);
                                torpedo.setRotate(angle);
                                root.getChildren().add(torpedo);
                            } else if (gameObject.getClass() == Obstacle.class) {
                                //models is an obstacle, draw it as such
                                IObstacle submarine = (IObstacle) gameObject;

                                Coördinate c = gameObject.getCoördinate();
                                Coördinate c2 = ((IObstacle) gameObject).getRightBottom();
                                javafx.scene.shape.Rectangle obstacle = new Rectangle(c.getX(), c.getY(), c2.getX() - c.getX(), c2.getY() - c.getY());
                                obstacle.setFill(Color.BLACK);
                                root.getChildren().add(obstacle);
                            }
                        }
                    });
                }
            }
        });
    }
}
