package Page;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;

import Objects.Admin;
import Objects.Group;
import Objects.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

public class MyFrame extends JFrame implements ActionListener {

    private Admin admin = new Admin();
    
    JPanel panel;
    JPanel buttons;
    JPanel totals;

    JTextField userId;
    JTextField groupId;
    JButton addU;
    JButton addG;
    JButton userView;
    JButton userTotal;
    JButton groupTotal;
    JButton messages;
    JButton positive;
    JTree tree;

    public MyFrame() {

        // Creating the Admin Panel elements
        panel = new JPanel();   // tree panel
        buttons = new JPanel(); // buttons panel
        totals = new JPanel();  // bottom half of panel

        userId = new JTextField();   // input user id
        groupId = new JTextField();  // input group id
        addU = new JButton();   // add user
        addG = new JButton();   // add group
        userView = new JButton();   // open user view
        userTotal = new JButton();     // show user total
        groupTotal = new JButton();    // show group total
        messages = new JButton();   // show messages total
        positive = new JButton();   // show positive percentage
        tree = new JTree();

        // Labeling all of the buttons
        addU.setText("Add User");
        addG.setText("Add Group");
        userView.setText("Open User View");
        userTotal.setText("Total Users");
        groupTotal.setText("Total Groups");
        messages.setText("Show Messages Total");
        positive.setText("Show Positive %-age");    // "Show Positive Percentage" is too long for the button

        addU.setFocusable(false);
        addG.setFocusable(false);
        userView.setFocusable(false);
        userTotal.setFocusable(false);
        groupTotal.setFocusable(false);
        messages.setFocusable(false);
        positive.setFocusable(false);

        Border blackline = BorderFactory.createLineBorder(Color.black);

        // Creating each panel to separate certain features
        panel.setBounds(10, 10, 240, 345);
        buttons.setBounds(260, 10, 410, 150);
        totals.setBounds(260, 250, 410, 100);
        panel.setBorder(blackline);
        buttons.setBorder(blackline);
        totals.setBorder(blackline);

        // Setting element sizes
        userId.setPreferredSize(new Dimension(250, 40));
        groupId.setPreferredSize(new Dimension(250, 40));
        addU.setPreferredSize(new Dimension(140, 40));
        addG.setPreferredSize(new Dimension(140, 40));

        userTotal.setPreferredSize(new Dimension(180, 40));
        groupTotal.setPreferredSize(new Dimension(180, 40));
        messages.setPreferredSize(new Dimension(180, 40));
        positive.setPreferredSize(new Dimension(180, 40));

        userView.setPreferredSize(new Dimension(390, 40));

        // for tree
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode group = new DefaultMutableTreeNode("Groups");
        DefaultMutableTreeNode user = new DefaultMutableTreeNode("Users");

        root.add(group);
        root.add(user);

        tree = new JTree(root);
        add(tree);
        this.setTitle("tweeter teehee");

        //

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 400);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.add(panel);
        this.add(buttons);
        this.add(totals);

        addU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userId.getText();
                admin.createUser(username);
            }
        });
        addG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String groupname = groupId.getText();
                admin.createGroup(groupname);
            }
        });

        userTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(admin.totalUsers());
            }
        });
        groupTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(admin.totalGroups());
            }
        });

        messages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }); // need to click on user and then can get user.totalTweets()

        //panel.setLayout(new FLowLayout(FlowLayout.LEADING));

        buttons.add(userId);
        buttons.add(addU);
        buttons.add(groupId);
        buttons.add(addG);
        buttons.add(userView);

        totals.add(userTotal);
        totals.add(groupTotal);
        totals.add(messages);
        totals.add(positive);
        
    }

    private void userAdded() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
