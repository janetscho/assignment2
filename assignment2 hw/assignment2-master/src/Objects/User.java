package Objects;

import java.util.ArrayList;

import Pattern.Observable;
import Pattern.Visitable;
import Pattern.Visitor;

public class User extends Observable implements Visitable {
    public String name;
    public ArrayList<Tweet> tweets;
    public ArrayList<User> following;
    public ArrayList<User> follower;

    public User(String name) {
        this.name = name;
        this.tweets = new ArrayList<Tweet>();
        this.following = new ArrayList<User>();
        this.follower = new ArrayList<User>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public Tweet setTweet(String message) {
        Tweet newTweet = new Tweet(name + " " + message);
        tweets.add(newTweet);
        return newTweet;
    }

    public int totalTweets() {
        return tweets.size();
    }

    public void setTweets(ArrayList<Tweet> tweets) {
        this.tweets = tweets;
    }

    public Tweet postTweet(String message) {
        Tweet twweeett = new Tweet(name + " : " + message);
        tweets.add(twweeett);
        notify(twweeett);
        return twweeett;
    }

    public ArrayList<User> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<User> following) {
        this.following = following;
    }

    public ArrayList<User> getFollower() {
        return follower;
    }

    public void setFollower(ArrayList<User> follower) {
        this.follower = follower;
    }

    public void addFollower(User user) {
        follower.add(user);
    }

    public void addFollowing(User user) {
        following.add(user);
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public String toString() {
        return name;
    }
}
