package Page;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import Objects.Admin;
import Objects.Group;
import Objects.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;

public class MyFrame extends JFrame implements ActionListener {

    private Admin admin;
    
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

        admin = new Admin();

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

        TreeNode start = new DefaultMutableTreeNode(admin.getGroup("Root"));
        TreeModel startModel = new DefaultTreeModel(start, true);

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
        tree.setModel(startModel);
        panel.add(tree);

        // for admin GUI
        this.setTitle("tweeter teehee");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 400);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.add(panel);
        this.add(buttons);
        this.add(totals);

        // button actions
        addU.addActionListener(new ActionListener() {
            void letsgo(){}
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
                DefaultMutableTreeNode selected = ((DefaultMutableTreeNode)tree.getLastSelectedPathComponent());
                if(selected == null) {
                    JOptionPane.showMessageDialog(null, "Please select a node.","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // gonna need to check if last selected node is a user, cant add if so

                String username = userId.getText();
                boolean temp = admin.checkUserAgain(username);

                if(!temp) {
                    JOptionPane.showMessageDialog(null, "User already exists.","Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if(selected.getAllowsChildren() == false) {
                        JOptionPane.showMessageDialog(null, "Please select a group node.","Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    User usl = admin.addUser(username);
                    DefaultMutableTreeNode user = new DefaultMutableTreeNode(usl);
                    user.setAllowsChildren(false);  // not allowed to have children
                    selected.add(user);
                    model.reload();
                }
                userId.setText("");
            }
        });
        addG.addActionListener(new ActionListener() {
            void letsgo() {}
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
                DefaultMutableTreeNode selected = ((DefaultMutableTreeNode)tree.getLastSelectedPathComponent());
                if(selected == null) {
                    JOptionPane.showMessageDialog(null, "Please select a node.","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(groupId.getText() == "") {
                    JOptionPane.showMessageDialog(null, "Please type in a name.","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String groupname = groupId.getText();
                boolean temp = admin.checkGroupAgain(groupname);

                if(!temp) {
                    JOptionPane.showMessageDialog(null, "Group already exists.","Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    
                    if(selected.getAllowsChildren() == false) {
                        JOptionPane.showMessageDialog(null, "Please select a group node.","Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Group gorp = (Group) selected.getUserObject();
                    Group groupie = admin.addGroup(gorp, groupname);
                    DefaultMutableTreeNode grp = new DefaultMutableTreeNode(groupie);
                    grp.setAllowsChildren(true);    // allowed to have children
                    selected.add(grp);
                    model.reload();
                }
                groupId.setText("");
            }
        });

        userView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selected = ((DefaultMutableTreeNode)tree.getLastSelectedPathComponent());

                if(selected == null || selected.getAllowsChildren() == true) {
                    JOptionPane.showMessageDialog(null, "Please select an user.","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                UserFrame view = new UserFrame();
                User yo = (User)selected.getUserObject();
                view.setCurrentUser(admin, yo);
                view.setVisible(true);
                view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
            
        });

        userTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans = admin.totalUsers();
                JOptionPane.showMessageDialog(null, ans,"Total Users", JOptionPane.ERROR_MESSAGE);
            }
        });
        groupTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ans = admin.totalGroups();
                JOptionPane.showMessageDialog(null, ans,"Total Groups", JOptionPane.ERROR_MESSAGE);
            }
        });

        messages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ttl = admin.allMessages();
                JOptionPane.showMessageDialog(null, ttl,"Total Messages ", JOptionPane.ERROR_MESSAGE);
            }
        });

        positive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double ttl = admin.postive();
                JOptionPane.showMessageDialog(null, ttl + "%","Positive Percentage", JOptionPane.ERROR_MESSAGE);
            }
            
        });

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

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
