package anagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by mgw on 5/22/17.
 */

@Service
public class ConfigurationService {

    @Value("${dictionary.location}")
    private String dictLoc;

    public ConfigurationService() { System.out.println("creating ConfigurationService"); }
    public String getDictLoc() {
        return dictLoc;
    }

    public void setDictLoc(String dictLoc) {
        this.dictLoc = dictLoc;
    }


}
