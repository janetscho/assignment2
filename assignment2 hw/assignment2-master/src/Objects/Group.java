package Objects;

import java.util.ArrayList;

public class Group {
    public String name;
    public ArrayList<User> users;
    public ArrayList<Group> groups;

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public Group(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.groups = new ArrayList<Group>();
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

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public String toString() {
        return name;
    }

}
