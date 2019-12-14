import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserUI extends JFrame {
    public JFrame view;
    public JTextField txtCustomerName = new JTextField(20);
    public JTextField Password = new JTextField(20);
    public JTextField FullName = new JTextField(20);
    public JTextField Usertype = new JTextField(20);
    public JTextField CustomerID = new JTextField(20);

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public UserModel user;

    public AddUserUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add User");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("CustomerName"));
        line.add(txtCustomerName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("password"));
        line.add(Password);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Fullname"));
        line.add(FullName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("UserType"));
        line.add(Usertype);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("CustomerID"));
        line.add(CustomerID);
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


            user.mUsername = txtCustomerName.getText();
            user.mPassword = Password.getText();
            user.mFullname = FullName.getText();
            user.mUserType = Integer.parseInt(Usertype.getText());
            user.mCustomerID = Integer.parseInt(CustomerID.getText());


            int res = StoreClient.getInstance().getDataAdapter().saveUser(user);
            if (res == SQLiteDataAdapter.PURCHASE_SAVE_FAILED)
                JOptionPane.showMessageDialog(null, "Customer NOT added successfully! Duplicate customer ID!");
            else
                JOptionPane.showMessageDialog(null, "Customer added successfully!" + user);
        }
    }
}
