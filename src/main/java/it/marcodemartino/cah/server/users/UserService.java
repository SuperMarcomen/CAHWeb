package it.marcodemartino.cah.server.users;

import java.util.*;

public class UserService {

    private final Map<UUID, String> uuidToUserName;

    public UserService() {
        uuidToUserName = new HashMap<>();
    }

    public String getUserName(UUID uuid) {
        return uuidToUserName.get(uuid);
    }

    public UUID addUser(String username) {
        UUID uuid = UUID.randomUUID();
        uuidToUserName.put(uuid, username);
        return uuid;
    }
}
