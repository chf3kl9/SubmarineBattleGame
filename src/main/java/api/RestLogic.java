package api;

import encrypter.Encrypt;
import encrypter.Hash;
import encrypter.IRESTEncrypt;
import game.shared.logger.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestLogic {

    DataBaseCommunication dbc = new DataBaseCommunication();
    Hash hash = new Hash();
    IRESTEncrypt encrypter = new Encrypt();

    public boolean register(String username, String password){
        password = encrypter.restUnEncrypt(password);
        password = hash.dbcHash(password);

        return dbc.register(username, password);
    }

    public int login(String username, String password){
        password = encrypter.restUnEncrypt(password);
        password = hash.dbcHash(password);

        return dbc.login(username, password);
    }

    public String getValidationKey(int id){
        String validationKey = hash.dbcHash(LocalDateTime.now().toString());
        dbc.setValidationKey(id, validationKey);
        return validationKey;
    }

    public boolean validateLogin(String validationKey, int userId){
        //unhash validation token
        //find user id from database with name corresponding to the unhashed validation token
        //if the userId is same as given userid the person is validated
        return dbc.validateLogin(validationKey, userId);
    }

    public void updateScore(int userId){
        //raise the score of given username with 1
            dbc.updateScore(userId);
    }

    public List<String> getTopX(String amount){
        //return top 100 scores
        try{
            int number = Integer.parseInt(amount);
            return dbc.getTopX(number);
        }
        catch (Exception e){
            Logger.getInstance().log(e);
        }
        return new ArrayList<>();
    }

    public String getScore(String userId){
        //return score of the given username
        int id = -1;
        try {
            id = Integer.parseInt(userId);
            return dbc.getScore(id);
        }
        catch (Exception e){
            return "Invalid Id";
        }
    }
}
