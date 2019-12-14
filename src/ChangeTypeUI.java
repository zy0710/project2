import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeTypeUI extends JFrame {
    public JFrame view;
    public JTextField UserAccount = new JTextField(20);
    public JTextField Usertype = new JTextField(20);


    public JButton btnAdd = new JButton("Change");
    public JButton btnCancel = new JButton("Cancel");

    public UserModel user;

    public ChangeTypeUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add User");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("AccountName"));
        line.add(UserAccount);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("UserType"));
        line.add(Usertype);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnAdd);
        line.add(btnCancel);
        view.getContentPane().add(line);

        btnAdd.addActionListener(new AddButtonListerner());

    }

    public void run() {
        user = new UserModel();
        view.setVisible(true);
    }

    class AddButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {


            user.mUsername = UserAccount.getText();
            user.mUserType = Integer.parseInt(Usertype.getText());


            int res = StoreClient.getInstance().getDataAdapter().changeUserType(user);
            if (res == SQLiteDataAdapter.USER_TYPE_CHANGE_FAILED)
                JOptionPane.showMessageDialog(null, "type change failed");
            else
                JOptionPane.showMessageDialog(null, "type change successfully!" + user);
        }
    }
}
