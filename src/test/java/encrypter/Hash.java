package encrypter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Hash {

    String restEncrypted = "Liro";
    String restDecrypted = "Henk";

    String authEncrypted = ">";//":89@58>59@\\9AB8;B;=6@>8";
    String authDecrypted = ">";//"2018-06-18T19:03:35.860";
    Encrypt encrypter;

    @Before
    public void init(){
        encrypter = new Encrypt();
    }

    @Test
    public void testEncryptREST(){
        String encrypt = encrypter.restEncrypt(restDecrypted);
        Assert.assertEquals(encrypt, restEncrypted);
    }

    @Test
    public void testUnEncryptREST(){
        String decrypt = encrypter.restUnEncrypt(restEncrypted);
        Assert.assertEquals(decrypt, restDecrypted);
    }

    @Test
    public void testEncryptAuthentication(){
        String encrypt = encrypter.validationEncrypt(authDecrypted);
        Assert.assertEquals(encrypt, authEncrypted);
    }

    @Test
    public void testUnEncryptAuthentication(){
        String decrypt = encrypter.validationUnEncrypt(authEncrypted);
        Assert.assertEquals(decrypt, authDecrypted);
    }

}
