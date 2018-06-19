package encrypter;

public interface IRESTEncrypt extends IEncrypt {
    String restUnEncrypt(String input);
    String validationEncrypt(String input);
    String validationUnEncrypt(String input);
}
