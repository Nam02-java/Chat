package MVC.Service.LazySingleton.Token;


public class TokenManager {
    private static TokenManager instance;
    private String jwtToken;

    private TokenManager() {
    }

    public static synchronized TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void delete() {
        this.jwtToken = null;
    }
}
