package Page;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ControlPanel {
    JFrame frame = new JFrame();

    ControlPanel() {
        JTextField userId = new JTextField();   // input user id
        JTextField groupId = new JTextField();  // input group id
        JButton addU = new JButton();   // add user
        JButton addG = new JButton();   // add group
        JButton userView = new JButton();   // open user view
        JButton userTotal = new JButton();     // show user total
        JButton groupTotal = new JButton();    // show group total
        JButton messages = new JButton();   // show messages total
        JButton positive = new JButton();   // show positive percentage

        addU.setText("Add User");
        addU.setVerticalTextPosition(AbstractButton.CENTER);
        addG.setText("Add Group");
        userView.setText("View User");
        userTotal.setText("Total Users");
        groupTotal.setText("Total Groups");
        messages.setText("Show Messages Total");
        positive.setText("Show Positive Percentage");
    }
}
