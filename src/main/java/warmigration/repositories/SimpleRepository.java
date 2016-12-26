package warmigration.repositories;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleRepository {
    public List<String> someData(){
        return Lists.newArrayList("Some", "data");
    }
}
