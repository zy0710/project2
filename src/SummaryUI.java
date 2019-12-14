import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SummaryUI {

    public JFrame view;
    public JTable productTable;


    public JButton btnSearch = new JButton("Search");


    public SummaryUI() {

        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Purchase summary");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Search results for all purchases");

        title.setFont (title.getFont().deriveFont (24.0f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        view.getContentPane().add(title);

        PurchaseListModel list = StoreClient.getInstance().getDataAdapter().loadAllPurchases();
        DefaultTableModel tableData = new DefaultTableModel();

        tableData.addColumn("PurchaseID");
        tableData.addColumn("CustomerID");
        tableData.addColumn("ProductID");
        tableData.addColumn("Price");
        tableData.addColumn("Quantity");
        tableData.addColumn("Cost");
        tableData.addColumn("Tax");
        tableData.addColumn("Total");
        tableData.addColumn("Date");

        for (PurchaseModel p : list.purchases) {
            Object[] row = new Object[tableData.getColumnCount()];

            row[0] = p.mPurchaseID;
            row[1] = p.mCustomerID;
            row[2] = p.mProductID;
            row[3] = p.mPrice;
            row[4] = p.mQuantity;
            row[5] = p.mCost;
            row[6] = p.mTax;
            row[7] = p.mTotal;
            row[8] = p.mDate;
            tableData.addRow(row);
        }

//        purchases = new JList(data);

        productTable = new JTable(tableData);

        JScrollPane scrollableList = new JScrollPane(productTable);

        view.getContentPane().add(scrollableList);


    }

    public void run(){
        view.setVisible(true);
    }
}
