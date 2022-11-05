package Objects;

import java.util.ArrayList;

public class Group {
    public String name;
    public ArrayList<User> users;

    public Group(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<User> getUsers() {
        return users;
    }
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

}
