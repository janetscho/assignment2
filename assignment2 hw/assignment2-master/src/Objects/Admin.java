package Objects;
import java.util.ArrayList;

import Pattern.PositiveVisitor;
import Pattern.TweetVisitor;


public class Admin {
    public static Admin instance = new Admin();
    public ArrayList<Group> allGroup;
    public ArrayList<User> allUser;

    public Admin() {
        this.allGroup = new ArrayList<Group>();
        this.allUser = new ArrayList<User>();
        allGroup.add(new Group("Root"));
    }

    public static Admin getInstance() {
        if(instance == null) {
            synchronized(Admin.class) {
                if(instance == null)
                    instance = new Admin();
            }
        }
        return instance;
    }
    
    public ArrayList<Group> getAllGroup() {
        return allGroup;
    }

    public void returnNames() {
        for(int i = 0; i < allUser.size(); i++) {
            System.out.println(allUser.get(i).getName());
        }
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

    public Group addGroup(Group parent, String name) {
        Group newOne = new Group(name);
        parent.addGroup(newOne);
        allGroup.add(newOne);
        return newOne;
    }

    public User addUser(String name) {
        User use = new User(name);
        allUser.add(use);
        return use;
    }

    // false if found (yes I know it is confusing)
    public boolean findGroup(String name) {
        for(int i = 0; i < allGroup.size(); i++) {
            if(allGroup.get(i).getName().equals(name))
                return false;
        }
        return true;
    }

    // true if the user was properly created
    public boolean createUser(String name) {
        User newUser = new User(name);
        if(checkUser(newUser)) {
            allUser.add(newUser);
            return true;
        }
        return false;
    }

    // return false if a name already exists
    public boolean checkUser(User name) {
        for(int i = 0; i < allUser.size(); i++) {
            if(allUser.get(i).getName().equals(name.getName())) {
                return false;
            }
        }
        return true;
    }

    // same premise as method above, just uses String instead of User
    public boolean checkUserAgain(String name){
        for(int i = 0; i < allUser.size(); i++) {
            if(allUser.get(i).getName().equals(name))
                return false;
        }
        return true;
    }

    public User getUser(String name) {
        for(int i = 0; i < allUser.size(); i++) {
            if(allUser.get(i).getName().equals(name))
                return allUser.get(i);
        }
        return null;
    }

    public Group getGroup(String name) {
        for(int i = 0; i < allGroup.size(); i++) {
            if(allGroup.get(i).getName().equals(name))
                return allGroup.get(i);
        }
        return null;
    }

    public boolean newGroup(String name) {
        Group newGroup = new Group(name);
        if(checkGroup(newGroup)) {
            allGroup.add(newGroup);
            return true;
        }
        return false;
    }

    public boolean checkGroup(Group name) {
        for(int i = 0; i < allGroup.size(); i++) {
            if(allGroup.get(i).getName().equals(name.getName())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkGroupAgain(String name) {
        for(int i = 0; i < allGroup.size(); i++) {
            if(allGroup.get(i).getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public int totalUsers() {
        return allUser.size();
    }

    public int totalGroups() {
        return allGroup.size() - 1;
    }

    public int allMessages() {
        int ttl = 0;
        for(int i = 0; i < allUser.size(); i++) {
            ttl += allUser.get(i).totalTweets();
        }
        return ttl;
    }

    public ArrayList<Tweet> allTweets() {
        ArrayList<Tweet> temp = new ArrayList<>();
        for(int i = 0; i < allUser.size(); i++) {
            temp.addAll(allUser.get(i).getTweets());
        }
        return temp;
    }

    public double postive() {
        double num = 0;
        PositiveVisitor pos = new PositiveVisitor();
        for(int i = 0; i < allUser.size(); i++) {
            User temp = allUser.get(i);
            num += temp.accept(pos);
        }
        System.out.println(num);
        return (num/allMessages()) * 100;
    }

    public int visitorTweets() {
        int num = 0;
        TweetVisitor twt = new TweetVisitor();
        for(int i = 0; i < allUser.size(); i++) {
            User temp = allUser.get(i);
            num += temp.accept(twt);
        }
        return num;
    }
}
