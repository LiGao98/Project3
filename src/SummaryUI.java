import com.sun.istack.internal.localization.NullLocalizable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SummaryUI {

    public JFrame view;
    //public JList purchases;
    public JTable purchaseTable;


    public SummaryUI() {

        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("View Product History - manager View");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Product summary");

        title.setFont (title.getFont().deriveFont (24.0f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        view.getContentPane().add(title);

        SummaryListModel list = StoreManager.getInstance().getDataAdapter().loadSummaryProduct();

        System.out.println( "Summary list= " + list );

        DefaultTableModel tableData = new DefaultTableModel();
        tableData.addColumn("ProductID");
        tableData.addColumn("Product Name");
        tableData.addColumn("Total Unit sold");
        tableData.addColumn("Total Money Sold");

        for ( ProductSaleModel product1 : list.products) {
            Object[] row = new Object[tableData.getColumnCount()];
            row[0] = product1.mProductID;
            row[1] = product1.mProductName;
            row[2] = product1.mTotalUnitSold;
            row[3] = product1.mTotalMoneySold;
            tableData.addRow(row);
        }


        purchaseTable = new JTable(tableData);

        JScrollPane scrollableList = new JScrollPane(purchaseTable);

        view.getContentPane().add(scrollableList);


    }
}