package Page;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import Objects.Admin;
import Objects.Tweet;
import Objects.User;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserFrame extends JFrame implements ActionListener{

    private Admin admin;
    public User curr = null;
    private DefaultListModel root = new DefaultListModel<>();   // for following
    private DefaultListModel root2 = new DefaultListModel<>();  // for all tweets

    public void setCurrentUser(Admin admin, User curr) {
        this.admin = admin;
        this.curr = curr;

        for(int i = 0; i < curr.getFollowing().size(); i++) {
            root.addElement(curr.getFollowing().get(i));
        }

        /* 
        for(int i = 0; i < admin.allTweets().size(); i++) {}
            root2.addElement(admin.allTweets().get(i) + "\n");
        }*/

        root2.addElement(admin.allTweets());
    }

    JTextField userid;
    JButton follow;
    JList list;
    JTextField tweet;
    JButton post;
    JList news;

    public UserFrame() {

        userid = new JTextField();
        follow = new JButton();
        list = new JList();
        tweet = new JTextField();
        post = new JButton();
        news = new JList();

        JPanel panel = new JPanel();

        follow.setText("Follow User");
        post.setText("Post Tweet");

        follow.setFocusable(false);
        list.setFocusable(false);
        post.setFocusable(false);
        news.setFocusable(false);

        userid.setPreferredSize(new Dimension(170, 40));
        follow.setPreferredSize(new Dimension(170, 40));
        list.setPreferredSize(new Dimension(350, 100));
        tweet.setPreferredSize(new Dimension(170, 40));
        post.setPreferredSize(new Dimension(170, 40));
        news.setPreferredSize(new Dimension(350,100));
        panel.setBounds(0, 0, 400, 370);

        this.add(panel);
        this.setTitle("user");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 370);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);

        list.setModel(root);
        news.setModel(root2);

        follow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = userid.getText(); 
                boolean check = admin.checkUserAgain(name);

                if(check != false) {
                    JOptionPane.showMessageDialog(null, "That person does not exist.","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                User person = admin.getUser(name);

                curr.addFollowing(person);
                person.addFollower(curr);
                root.addElement(person);
                userid.setText("");

                resetFollowing();
            }
        });

        post.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tweet newTweet = curr.setTweet(tweet.getText());
                root2.add(0, newTweet);
                tweet.setText("");
            }
        });

        panel.add(userid);
        panel.add(follow);
        panel.add(list);
        panel.add(tweet);
        panel.add(post);
        panel.add(news);
    }

    public void resetFollowing() {
        list = new JList(curr.getFollowing().toArray());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }    
    
}
