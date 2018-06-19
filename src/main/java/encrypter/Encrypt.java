package encrypter;

import game.shared.logger.Logger;

public class Encrypt implements IRESTEncrypt {

    //private String validationKey = "BadEncryptionKey";
    //private String restKey = "EquallyBadEncryptionKey";
    //int restShift = 4;
    //int validationShift= 8;

    @Override
    public String restUnEncrypt(String input) {
        return input;
        //return crypter(input, -restShift);
        //return deCrypt(input, restKey);
    }

    @Override
    public String validationEncrypt(String input) {
        return input;
        //return crypter(input, validationShift);
        //return enCrypt(input, validationKey);
    }

    @Override
    public String validationUnEncrypt(String input) {
        return input;
        //return crypter(input, -validationShift);
        //return deCrypt(input, validationKey);
    }

    @Override
    public String restEncrypt(String input) {
        return input;
        //return crypter(input, restShift);
        //return enCrypt(input, restKey);
    }


    /*private String crypter(String input, int x){
        try {
            String output = "";
            for (char c : input.toCharArray()) {
                output += (char) ((c - 'a' + x) + 'a');
            }
            return output;
        }
        catch (Exception e){
            Logger.getInstance().log(e);
            return "";
        }
    }*/

    /*private String enCrypt(String input, String key){
        String strData="";

        try {
            SecretKeySpec skeyspec=new SecretKeySpec(key.getBytes(),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
            byte[] encrypted=cipher.doFinal(input.getBytes());
            strData=new String(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strData;
    }

    private String deCrypt(String input, String key){
        String strData="";

        try {
            SecretKeySpec skeyspec=new SecretKeySpec(key.getBytes(),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);
            byte[] decrypted=cipher.doFinal(input.getBytes());
            strData=new String(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strData;
    }*/
}
