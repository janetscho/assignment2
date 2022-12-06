package Page;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Objects.Admin;
import Objects.Tweet;
import Objects.User;
import Pattern.Observer;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class UserFrame extends JFrame implements ActionListener, Observer{

    private Admin admin = Admin.getInstance();  // gets Admin from MyFrame.java
    public User curr = null;
    private DefaultListModel root = new DefaultListModel<>();   // for following
    private DefaultListModel root2 = new DefaultListModel<>();  // for all tweets

    public void setCurrentUser(User curr) {
        this.curr = curr;

        userName.setText("Name: " + curr.getName());
        LocalDateTime localDateTime = Instant.ofEpochMilli(curr.getCreationTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        userTime.setText("Creation Time: " + String.valueOf(localDateTime));

        // sets the following list per user
        for(int i = 0; i < curr.getFollowing().size(); i++) {
            root.addElement(curr.getFollowing().get(i));
        }

        pleaseUpdate();
    }

    // refreshes twitter feed when a new JFrame is opened
    public void pleaseUpdate() {
        ArrayList<Tweet> upTweet = new ArrayList<>();
        upTweet.addAll(curr.getTweets());

        lastUpdatedTime.setText("Last Updated User: " + admin.getLastUpdatedUser());

        for(int i = 0; i < curr.getFollowing().size(); i++) {
            upTweet.addAll(curr.getFollowing().get(i).getTweets());
            curr.getFollowing().get(i).addObs(this);
            //root2.addElement(upTweet.get(i));
        }

        if(curr.getFollowing().size() == 0) {
            lastUpdatedTime.setText("Last Updated User: ");
        } else {
            lastUpdatedTime.setText("Last Updated User: " + curr.lastUpdatedTimeUser());
        }

        // for keeping tweet feed after frame is closed
        for(int i = 0; i < upTweet.size(); i++)
            root2.addElement(upTweet.get(i));
    }

    JTextField userid;
    JButton follow;
    JList list;
    JTextField tweet;
    JButton post;
    JList news;
    JTextArea userTime;
    JTextArea groupTime;
    JTextArea userName;
    JTextArea lastUpdatedTime;

    public UserFrame() {

        userid = new JTextField();
        follow = new JButton();
        list = new JList();
        tweet = new JTextField();
        post = new JButton();
        news = new JList();
        userTime = new JTextArea();
        groupTime = new JTextArea();
        userName = new JTextArea();
        lastUpdatedTime = new JTextArea();

        JTextArea tweetShow = new JTextArea();
        JTextArea newsShow = new JTextArea();

        JPanel panel = new JPanel();
        JPanel title = new JPanel();

        follow.setText("Follow User");
        post.setText("Post Tweet");

        userTime.setFocusable(false);
        userName.setFocusable(false);
        follow.setFocusable(false);
        list.setFocusable(false);
        post.setFocusable(false);
        news.setFocusable(false);
        tweetShow.setFocusable(false);
        newsShow.setFocusable(false);
        lastUpdatedTime.setFocusable(false);

        tweetShow.setText("Current Following");
        tweetShow.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        newsShow.setText("News Feed");

        userid.setPreferredSize(new Dimension(170, 40));
        follow.setPreferredSize(new Dimension(170, 40));
        list.setPreferredSize(new Dimension(350, 100));
        tweet.setPreferredSize(new Dimension(170, 40));
        post.setPreferredSize(new Dimension(170, 40));
        news.setPreferredSize(new Dimension(350,100));
        title.setBounds(0, 0, 400, 50);
        panel.setBounds(0, 50, 400, 370);

        this.add(title);
        this.add(panel);
        this.setTitle("User View");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 450);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);

        list.setModel(root);
        news.setModel(root2);

        follow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                followClick(e);
            }
        });

        post.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tweetClick(e);
            }
        });

        title.add(userName);
        title.add(userTime);
        title.add(lastUpdatedTime);
        panel.add(new JSeparator(SwingConstants.HORIZONTAL));
        panel.add(userid);
        panel.add(follow);
        panel.add(tweetShow);
        panel.add(list);
        panel.add(tweet);
        panel.add(post);
        panel.add(newsShow);
        panel.add(news);
    }

    private void followClick(ActionEvent e) {
        String name = userid.getText(); 
        boolean check = admin.checkUserAgain(name);

        if(check != false) {
            JOptionPane.showMessageDialog(null, "That person does not exist.","Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User person = admin.getUser(name);

        person.addObs(this);
        curr.addFollowing(person);
        person.addFollower(curr);
        root.addElement(person);
        userid.setText("");

        //resetFollowing();
    }

    private void tweetClick(ActionEvent e) {
        Tweet newTweet = curr.postTweet(tweet.getText());
        //newTweet.addObs(this);
        //root2.add((curr.getTweets().size() - 1), newTweet);
        root2.add(0, newTweet);
        tweet.setText("");
    }

    public void resetFollowing() {
        list = new JList(curr.getFollowing().toArray());
    }

    public void resetFeed() {
        news = new JList(curr.getTweets().toArray());
    }

    @Override
    public void update(Object obj) {
        //root2.add((curr.getTweets().size() - 1), (Tweet)obj));
        root2.add(0, (Tweet)obj);
        lastUpdatedTime.setText("Last Updated User: " + curr.lastUpdatedTimeUser());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
