package anagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AnagramController  {

    @Autowired
    private DictionaryHelper2 helper;

    @RequestMapping("/")
    public String index() {

        return "Hey whaaaaa?";
    }


    @RequestMapping(value="/word/{inWord}", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity checkWord(@PathVariable("inWord") String word) {
        //System.out.println("checkWord()");

        List<String> anagrams = helper.getAnagrams(word);

        if (null == anagrams) {
            ResponseEntity resp =  ResponseEntity.status(404).body(new ErrorMessage(word));
            return resp;
        } else {
            anagrams.remove(word);  //make sure we don't include the word itself in the results
        }

        ResponseMessage responseMessage = new ResponseMessage(word,anagrams);
        return ResponseEntity.ok(responseMessage);

    }

    @RequestMapping(value="/word", method=RequestMethod.GET)
    public @ResponseBody ResponseEntity noWord(HttpServletRequest request) {
        //System.out.println("noWord()");
        return ResponseEntity.notFound().build();
    }


}
