package warmigration.repositories;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {

    List<String> validUserIds = Lists.newArrayList("Alice", "Bob", "Carol");

    public boolean isValidUserId(String userId){
        return validUserIds.contains(userId);
    }
}
