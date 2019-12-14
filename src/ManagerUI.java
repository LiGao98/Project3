import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerUI {
    public JFrame view;

    public ProductModel user = null;
    public ProductSaleModel user2 = null;

    public JButton btnManageProduct = new JButton("Manage Products");
    public JButton btnManageSummary = new JButton("Summary report");
    public ManagerUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System - Manager View");
        view.setSize(1000, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("MANAGER WORK");

        title.setFont (title.getFont ().deriveFont (24.0f));
        view.getContentPane().add(title);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnManageProduct);
        panelButtons.add(btnManageSummary);

        view.getContentPane().add(panelButtons);


        btnManageProduct.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent actionEvent) {
                ManageProductUI ui = new ManageProductUI();
                ui.run();
            }
        });

        btnManageSummary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                SummaryUI ui = new SummaryUI();
                ui.view.setVisible(true);
            }
        });

    }
}