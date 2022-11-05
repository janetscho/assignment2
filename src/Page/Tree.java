package Page;

import java.beans.FeatureDescriptor;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class Tree extends JFrame {

    private JTree tree;

    public Tree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode group = new DefaultMutableTreeNode("Groups");
        DefaultMutableTreeNode user = new DefaultMutableTreeNode("Users");

        root.add(group);
        root.add(user);

        tree = new JTree(root);
        add(tree);
        this.setTitle("Tree View");
    }
}
