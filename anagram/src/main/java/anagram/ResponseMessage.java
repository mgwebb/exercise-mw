package anagram;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by mgw on 5/16/17.
 */
public class ResponseMessage {

    private String word;
    private List<String> anagrams;

    public ResponseMessage() {}

    public ResponseMessage(String word, List<String> anagrams) {
        this.word = word;
        this.anagrams = anagrams;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String code) {
        this.word = word;
    }

    public List<String> getAnagrams() {
        return anagrams;
    }

    public void setAnagrams(List<String> anagrams) {
        this.anagrams = anagrams;
    }
}
