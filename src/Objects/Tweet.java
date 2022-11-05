package Objects;

import java.util.Date;

public class Tweet {
    public String message;
    public Date date;

    public Tweet(String message) {
        this.message = message;
        this.date = new Date();
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }


}
