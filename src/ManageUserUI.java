import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.NoSuchElementException;

public class ManageUserUI {

    public JFrame view;

    public JButton btnLoad = new JButton( "Load User" );
    public JButton btnSave = new JButton( "Save User" );

    public JTextField txtUsername = new JTextField( 20 );
    public JTextField txtPassword = new JTextField( 20 );
    public JTextField txtFullname = new JTextField( 20 );
    public JTextField txtUsertype = new JTextField( 20 );
    public JTextField txtCustomerID = new JTextField( 20 );

    public ManageUserUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        view.setTitle( "Manage User Information" );
        view.setSize( 600, 400 );
        view.getContentPane().setLayout( new BoxLayout( view.getContentPane(), BoxLayout.PAGE_AXIS ) );

        JPanel panelButtons = new JPanel( new FlowLayout() );
        panelButtons.add( btnLoad );
        panelButtons.add( btnSave );
        view.getContentPane().add( panelButtons );



        JPanel line1 = new JPanel( new FlowLayout() );
        line1.add( new JLabel( "User name" ) );
        line1.add( txtUsername );
        view.getContentPane().add( line1 );

        JPanel line2 = new JPanel( new FlowLayout() );
        line2.add( new JLabel( "Password" ) );
        line2.add( txtPassword );
        view.getContentPane().add( line2 );

        JPanel line3 = new JPanel( new FlowLayout() );
        line3.add( new JLabel( "Full name" ) );
        line3.add( txtFullname );
        view.getContentPane().add( line3 );

        JPanel line4 = new JPanel( new FlowLayout() );
        line4.add( new JLabel( "User type" ) );
        line4.add( txtUsertype );
        view.getContentPane().add( line4 );

        JPanel line5 = new JPanel( new FlowLayout() );
        line5.add( new JLabel( "CustomerID" ) );
        line5.add( txtCustomerID);
        view.getContentPane().add( line5 );

        btnLoad.addActionListener( new LoadButtonListerner() );

        btnSave.addActionListener( new SaveButtonListener() );

    }

    public void run() {
        view.setVisible( true );
    }

    class LoadButtonListerner implements ActionListener {


        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();
            String id = txtUsername.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog( null, "User name cannot be null" );
                return;
            }

            try {
                user.mUsername = id;
            } catch (NoSuchElementException e) {
                JOptionPane.showMessageDialog( null, "username  is invalid!" );
                return;
            }

            // call data access!

            user = StoreManager.getInstance().getDataAdapter().loadUser( user.mUsername );

            if (user == null) {
                JOptionPane.showMessageDialog( null, "user NOT exists!" );
            } else {
                txtUsername.setText( user.mUsername );
                txtPassword.setText( user.mPassword );
                txtFullname.setText( user.mFullname );
                txtUsertype.setText( Integer.toString(user.mUserType));
                txtCustomerID.setText( Integer.toString(user.mCustomerID) );
            }
        }
    }

    class SaveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();
            String username = txtUsername.getText();

            if (username.length() == 0) {
                JOptionPane.showMessageDialog( null, "Username cannot be null" );
                return;
            }

            try {
                user.mUsername = username;
            } catch (NoSuchElementException e) {
                JOptionPane.showMessageDialog( null, "User is invalid!" );
                return;
            }


            String name = txtFullname.getText();
            try {
                user.mFullname = name;
            } catch (NoSuchElementException e) {
                JOptionPane.showMessageDialog( null, "name is not present!" );
                return;
            }

            String password = txtPassword.getText();
            try {
                user.mPassword = password;
            } catch (NoSuchElementException e) {
                JOptionPane.showMessageDialog( null, "password is invalid!" );
                return;
            }

            String type = txtUsertype.getText();
            try {
                user.mUserType = Integer.parseInt(type );
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog( null, "invalid!" );
                return;
            }


            String cus = txtCustomerID.getText();
            try {
                user.mCustomerID = Integer.parseInt( cus );
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog( null, "invalid!" );
                return;
            }

            int res = StoreManager.getInstance().getDataAdapter().saveUser( user );

            if (res == IDataAdapter.USER_DUPLICATE_ERROR)
                JOptionPane.showMessageDialog( null, "User is SAVED successfully!" );
        }
    }
}