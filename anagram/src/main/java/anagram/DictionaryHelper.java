package anagram;

/**
 * Created by mgw on 5/16/17.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;


public class DictionaryHelper {

    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();


    public DictionaryHelper()  {
        try {
            URL oracle = new URL("https://users.cs.duke.edu/~ola/ap/linuxwords");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String sorted = sortString(inputLine);
                processWord(sorted, inputLine);
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Exception caught while creating map:");
            System.out.println(e.toString());
        }
        //System.out.println(map.toString());
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
        String sorted = sortString(word);
        ArrayList<String> anagrams = map.get(sorted);

        if (null != anagrams) {
            // make sure the actual word we requested is in the result (case sensitive)
            if (anagrams.contains(word))
                return (ArrayList<String>) anagrams.clone();
        }

        // Either we didn't get anything back from the map, or the actual word we requested isn't in the results
        return null;

    }

}
