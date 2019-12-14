import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePassUI extends JFrame {
    public JFrame view;

    public JTextField Password = new JTextField(20);

    public JButton btnAdd = new JButton("Change");
    public JButton btnCancel = new JButton("Cancel");

    public UserModel user;

    public ChangePassUI(UserModel user) {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add User");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("Password"));
        line.add(Password);
        view.getContentPane().add(line);


        line = new JPanel(new FlowLayout());
        line.add(btnAdd);
        line.add(btnCancel);
        view.getContentPane().add(line);

        btnAdd.addActionListener(new AddButtonListerner());

        this.user = user;
    }

    public void run() {
        view.setVisible(true);
    }

    class AddButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {


            user.mPassword = Password.getText();


            int res = StoreClient.getInstance().getDataAdapter().changePassword(user);
            if (res == SQLiteDataAdapter.USER_TYPE_CHANGE_FAILED)
                JOptionPane.showMessageDialog(null, "type change failed");
            else
                JOptionPane.showMessageDialog(null, "type change successfully!" + user);
        }
    }
}