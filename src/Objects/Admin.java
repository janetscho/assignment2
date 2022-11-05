package Objects;
import java.util.ArrayList;


public class Admin {
    public ArrayList<Group> allGroup;
    public ArrayList<User> allUser;

    public Admin() {
        this.allGroup = new ArrayList<Group>();
        this.allUser = new ArrayList<User>();
    }
    
    public ArrayList<Group> getAllGroup() {
        return allGroup;
    }

    public void setAllGroup(ArrayList<Group> allGroup) {
        this.allGroup = allGroup;
    }

    public ArrayList<User> getAllUser() {
        return allUser;
    }

    public void setAllUser(ArrayList<User> allUser) {
        this.allUser = allUser;
    }

    public void createGroup(String name) {
        Group newGroup = new Group(name);
        allGroup.add(newGroup);
    }

    public boolean findGroup(String name) {
        for(int i = 0; i < allGroup.size(); i++) {
            if(allGroup.get(i).getName().equals(name))
                return true;
        }
        return false;
    }

    public void createUser(String name) {
        User newUser = new User(name);
        allUser.add(newUser);
    }

    public int totalUsers() {
        return allUser.size();
    }

    public int totalGroups() {
        return allGroup.size();
    }
}
