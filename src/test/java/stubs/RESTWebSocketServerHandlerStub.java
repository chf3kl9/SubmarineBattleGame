package stubs;

import api.IRestWebsocket;

public class RESTWebSocketServerHandlerStub implements IRestWebsocket {
    @Override
    public boolean validateLogin(int userId, String validationKey) {
        return true;
    }

    @Override
    public void updateScore(int userId) {

    }
}
