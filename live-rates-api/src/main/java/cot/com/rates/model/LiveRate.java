package cot.com.rates.model;

/**
 * @author davidjmartin
 */
public class LiveRate {

    private String userName;
    private String message;

    public String getUserName() {
        return userName;
    }
    public LiveRate(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public LiveRate( String message) {
        this.userName = "default";
        this.message = message;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "LiveRate{" +
                "userName='" + userName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
