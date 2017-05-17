package anagram;

import java.util.List;

/**
 * Created by mgw on 5/16/17.
 */
public class ErrorMessage {

    private String message;

    public ErrorMessage(String word) {
        this.message = "Couldn't find word " + word;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {

    }

}
