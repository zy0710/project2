import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductSearchUI {

        public JFrame view;
        public JTable productTable;
        public UserModel user = null;
        public JTextField txtSearchName;
        public JButton Searchbtn;
        public DefaultTableModel tableData;

        public ProductSearchUI(UserModel user) {
            this.user = user;

            this.view = new JFrame();

            view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            view.setTitle("Search Product");
            view.setSize(600, 400);
            view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

            JLabel title = new JLabel("Search results for " + user.mFullname);

            title.setFont (title.getFont().deriveFont (24.0f));
            title.setHorizontalAlignment(SwingConstants.CENTER);
            view.getContentPane().add(title);


            tableData = new DefaultTableModel();

            tableData.addColumn("ProductID");
            tableData.addColumn("Product Name");
            tableData.addColumn("Price");
            tableData.addColumn("Quantity");



//        purchases = new JList(data);

            productTable = new JTable(tableData);

            JScrollPane scrollableList = new JScrollPane(productTable);

            view.getContentPane().add(scrollableList);

            // add txtfield and btns
            txtSearchName = new JTextField(10);
            Searchbtn = new JButton("Search");

            view.getContentPane().add(txtSearchName);
            view.getContentPane().add(Searchbtn);
            Searchbtn.addActionListener(new AddButtonListerner());


        }

    class AddButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (tableData.getRowCount() > 0) {
                for (int i = tableData.getRowCount() - 1; i > -1; i--) {
                    tableData.removeRow(i);
                }
            }

            String name = txtSearchName.getText();

            ProductListModel list = StoreClient.getInstance().getDataAdapter().searchProduct(name, 0, 10000);
            for (ProductModel p : list.products) {
                Object[] row = new Object[tableData.getColumnCount()];

                row[0] = p.mProductID;
                row[1] = p.mName;
                row[2] = p.mPrice;
                row[3] = p.mQuantity;
                tableData.addRow(row);
            }
        }
    }
}

