package game.clientside.clienthandler;

import api.IRestClient;
import api.RestClient;
import game.clientside.clientgamelogic.ClientGameLogic;
import game.clientside.clientgamelogic.IClientGameLogic;
import game.clientside.clientmessagegenerator.IClientMessageGenerator;
import game.clientside.gui.IGUI;
import game.models.GameObject;
import game.models.interfaces.ISubmarine;
import game.models.Settings;
import game.models.Torpedo;
import encrypter.Encrypt;
import encrypter.IEncrypt;
import game.shared.logger.LogLevel;
import game.shared.logger.Logger;
import javafx.scene.input.KeyCode;

import java.util.List;

public class ClientHandler implements IClientHandlerGUI, IClientHandler, IClientHandlerLogic {
    int lobbyId= -1;
    int userId = -1;
    String playername;
    IEncrypt encrypter = new Encrypt();
    IGUI gui;
    IClientMessageGenerator messageGenerator;
    IRestClient restHandler = new RestClient();
    IClientGameLogic gameLogic;
    Thread gameThread;

    public ClientHandler(IClientMessageGenerator messageGenerator){
        this.messageGenerator = messageGenerator;
    }

    public void setGui(IGUI gui){
        this.gui = gui;
    }

    //region IClientHandler
    public void death() {

    }

    public void kill() {

    }

    public void moveConfirm(ISubmarine submarine) {
        gameLogic.setServerSubmarine(submarine);
    }

    public void sonar(List<GameObject> visibleObjects) {
        gameLogic.setVision(visibleObjects);
    }

    public void torpedoConfirm(List<Torpedo> torpedoes) {
        gameLogic.setServerTorpedoes(torpedoes);
    }

    public void incorrectLogin() {
        gui.loginResult(false);
    }

    public void lobbyClosed() {
        gui.lobbyClosed();
    }

    public void lobbyJoined(int lobbyId, Settings settings) {
        this.lobbyId = lobbyId;
        gui.joinResult(true, settings);
    }

    public void roundStarted(ISubmarine submarine) {
        gui.roundStarted(submarine);
        gameLogic = new ClientGameLogic(this, submarine);
        gameThread = new Thread(gameLogic);
        gameThread.start();
    }

    public void getLobbies(List<String> lobbies) {
        gui.lobbies(lobbies);
    }

    public void lobbyCreated(int lobbyId) {
        this.lobbyId = lobbyId;
        gui.lobbyCreated();
    }

    public void updatePlayersInLobby(String[] players) {
        gui.playersInLobbyUpdate(players);
    }

    public void validationFinish(boolean success){
        gui.loginResult(success);
    }
    //endregion

    //region IClientHandlerGUI
    public void handleKeyInput(KeyCode input, boolean released){
        if (gameThread != null && gameLogic != null){
            switch(input.getName()){
                case "A":
                    gameLogic.setRotate(-1,released);
                    break;
                case "D":
                    gameLogic.setRotate(1,released);
                    break;
                case "W":
                    gameLogic.setAccelerate(1,released);
                    break;
                case "S":
                    gameLogic.setAccelerate(-1,released);
                    break;
                case "SPACE":
                    gameLogic.fireShot("");
                    break;
                default:
                    Logger.getInstance().log("No valid input", LogLevel.INFORMATION);
                    System.out.println("No valid input");
                    break;
            }
        }
    }

    public void login(String username, String password) {
        this.playername = username;
        password = encrypter.restEncrypt(password);
        String result = restHandler.login(username, password);
        if (result.equals("Invalid login")){
            gui.loginResult(false);
            return;
        }
        String userIdString = result.substring(0, result.indexOf(';'));
        userId = Integer.parseInt(userIdString);
        messageGenerator.sendValidate(userId, result.substring(result.indexOf(';')+1, result.length()));
    }

    public void register(String username, String password) {
        password = encrypter.restEncrypt(password);
        gui.registrationResult(restHandler.register(username, password));
    }

    public void leaveLobby() {
        messageGenerator.sendLobbyLeave(lobbyId);
        lobbyId = -1;
    }

    public void createLobby(String lobbyname, String password, Settings settings) {
        messageGenerator.sendOpenLobby(lobbyname, playername, password, settings, userId);
    }

    public void startRound() {
        messageGenerator.sendStartRound(lobbyId);
    }

    public void joinLobby(String lobbyname, String password) {
        messageGenerator.sendJoinLobby(lobbyname, playername, password, userId);
    }

    public void getLobbies() {
        messageGenerator.sendLobbyRefresh();
    }

    public void getHighScore() {
        List<String> top100 = restHandler.getx(100);
        String personalScore = restHandler.getScore(userId);
        gui.highscore(top100, personalScore);
    }
    //endregion

    // region IClientHandlerLogic
    public void fireTorpedo(){
        messageGenerator.sendFireShot(lobbyId);
    }

    public void moveSubmarine(int rotate, int accelerate){
        messageGenerator.sendMove(lobbyId, rotate, accelerate);
    }

    public void updateScreen(List<GameObject> objects){
        gui.updateGameScreen(objects);
    }

    //endregion
}
