package anagram;

/**
 * Created by mgw on 5/16/17.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

@Component
public class DictionaryHelper2 {

//    @Autowired
    private ConfigurationService configServ;

    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

//    @Autowired
// looks like we don't actually need the @Autowired annotation
    public DictionaryHelper2(ConfigurationService cs)  {
        try {
            this.configServ = cs;
            System.out.println("mgw - created DictionaryHelper2");
            System.out.println("mgw - dictLoc = " + configServ.getDictLoc());
            if (null == configServ) System.out.println("mgw - ref is null");

            URL dict = new URL(configServ.getDictLoc());
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(dict.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                processWord(hashWord(inputLine), inputLine);
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Exception caught while creating map:");
            System.out.println(e.toString());
        }
        //System.out.println(map.toString());
    }

    private String hashWord(String word) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder hashed = new StringBuilder();
        for (char c : alphabet) {
            int counter = 0;
            for( int i=0; i<word.length(); i++ ) {
                if( word.toLowerCase().charAt(i) == c ) {
                    counter++;
                }
            }
            if (counter > 0) {
                hashed.append(c).append(counter);
            }
        }
        //System.out.println("word = " + word + ", hashed = " + hashed.toString());
        return hashed.toString();
    }


    private String sortString(String word) {
        char[] arr = word.toLowerCase().toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }


    private void processWord(String key, String word) {
        //NOTE - list will be in correct sort order because the input list is already sorted
        if (map.containsKey(key)) {
            ArrayList<String> list = map.get(key);
            list.add(word);
            map.put(key,list);
        } else {
            map.put(key, new ArrayList<>(Arrays.asList(word)));
        }
    }

    public List<String> getAnagrams(String word) {
        // 1) sort word
        // 2) use sorted word to lookup list in map
        // 3) return list
        String hashed = hashWord(word);
        ArrayList<String> anagrams = map.get(hashed);

        if (null != anagrams) {
            // make sure the actual word we requested is in the result (case sensitive)
            if (anagrams.contains(word))
                return (ArrayList<String>) anagrams.clone();
        }

        // Either we didn't get anything back from the map, or the actual word we requested isn't in the results
        return null;

    }

}
