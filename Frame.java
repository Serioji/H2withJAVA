package Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Frame extends JFrame {
    Connection conn= null;
    PreparedStatement state = null;
    ResultSet result;

    JTabbedPane tabbedPanel = new JTabbedPane();

    JPanel userPanel = new JPanel();
    JPanel pcGoodsPanel = new JPanel();
    JPanel ordersPanel = new JPanel();
    JPanel advancedSearch = new JPanel();

    JLabel userNumberL = new JLabel("User number:");
    JLabel fnameL = new JLabel("First Name:");
    JLabel lnameL = new JLabel("Last Name:");
    JLabel emailL = new JLabel("Email:");
    JLabel phoneL = new JLabel("Phone:");

    JLabel partNumberL = new JLabel("Part number:");
    JLabel partNameL = new JLabel("Name:");
    JLabel priceL = new JLabel("Price:");

    JLabel userNumberOrdersL = new JLabel("User number: ");
    JLabel partNumberOrdersL = new JLabel("Part number: ");
    JLabel ordersNumberL = new JLabel("Order number: ");
    JLabel quantityL = new JLabel("Quantity:");

    JLabel fNameSearchL = new JLabel("First Name:");
    JLabel partNameSearchL = new JLabel("Part Name:");

    JLabel ScrollL = new JLabel("Scroll:");
    JLabel ScrollL1 = new JLabel("Scroll:");
    JLabel ScrollL2 = new JLabel("Scroll:");

    JTextField userNumberTF = new JTextField();
    JTextField fnameTF = new JTextField();
    JTextField lnameTF = new JTextField();
    JTextField emailTF = new JTextField();
    JTextField phoneTF = new JTextField();
    JTextField partNumberTF = new JTextField();
    JTextField partNameTF = new JTextField();
    JTextField priceTF = new JTextField();
    JTextField partNumberOrdersTF = new JTextField();
    JTextField userNumberOrdersTF = new JTextField();
    JTextField orderNumberTF = new JTextField();
    JTextField quantityTF = new JTextField();
    JTextField fnameOrdersTF = new JTextField();
    JTextField partNameSearchTF = new JTextField();

    JTable table = new JTable();
    JTable table1 = new JTable();
    JTable table2 = new JTable();
    JTable table3 = new JTable();
    JScrollPane myScroll = new JScrollPane(table);
    JScrollPane myScroll1 = new JScrollPane(table1);
    JScrollPane myScroll2 = new JScrollPane(table2);
    JScrollPane myScroll3 = new JScrollPane(table3);

    JButton addBtnUsers = new JButton("Add");
    JButton addBtnPCGoods = new JButton("Add");
    JButton addBtnOrders = new JButton("Add");

    JButton delBtnUsers = new JButton("Delete");
    JButton delBtnPCGoods = new JButton("Delete");
    JButton delBtnOrders = new JButton("Delete");

    JButton editBtnUsers = new JButton("Edit");
    JButton editBtnPCGoods = new JButton("Edit");
    JButton editBtnOrders = new JButton("Edit");

    JButton searchBtn = new JButton("Search");


    public Frame(){
        this.setSize(800,800);
        this.setResizable(false);

        tabbedPanel.addTab("User", userPanel);

        userPanel.setLayout(new GridLayout(8,2));
        userPanel.add(userNumberL);
        userPanel.add(userNumberTF);
        userPanel.add(fnameL);
        userPanel.add(fnameTF);
        userPanel.add(lnameL);
        userPanel.add(lnameTF);
        userPanel.add(emailL);
        userPanel.add(emailTF);
        userPanel.add(phoneL);
        userPanel.add(phoneTF);
        userPanel.add(ScrollL1);
        userPanel.add(myScroll1);
        refreshTable("USERS",table1);
        userPanel.add(addBtnUsers);
        userPanel.add(delBtnUsers);
        userPanel.add(editBtnUsers);
        addBtnUsers.addActionListener(new ButtonClickActionUsers());
        delBtnUsers.addActionListener(new DeleteActionUsers());
        editBtnUsers.addActionListener(new EditActionUsers());


        tabbedPanel.addTab("PCGOODS", pcGoodsPanel);
        pcGoodsPanel.setLayout(new GridLayout(6,2));
        pcGoodsPanel.add(partNumberL);
        pcGoodsPanel.add(partNumberTF);
        pcGoodsPanel.add(partNameL);
        pcGoodsPanel.add(partNameTF);
        pcGoodsPanel.add(priceL);
        pcGoodsPanel.add(priceTF);
        pcGoodsPanel.add(ScrollL2);
        pcGoodsPanel.add(myScroll2);
        refreshTable("PCGOODS",table2);
        pcGoodsPanel.add(addBtnPCGoods);
        pcGoodsPanel.add(delBtnPCGoods);
        pcGoodsPanel.add(editBtnPCGoods);
        addBtnPCGoods.addActionListener(new ButtonClickActionPCGoods());
        delBtnPCGoods.addActionListener(new DeleteActionPCGoods());
        editBtnPCGoods.addActionListener(new EditActionPCGoods());

        tabbedPanel.addTab("Orders", ordersPanel);
        ordersPanel.setLayout(new GridLayout(8,2));
        ordersPanel.add(ordersNumberL);
        ordersPanel.add(orderNumberTF);
        ordersPanel.add(userNumberOrdersL);
        ordersPanel.add(userNumberOrdersTF);
        ordersPanel.add(partNumberOrdersL);
        ordersPanel.add(partNumberOrdersTF);
        ordersPanel.add(quantityL);
        ordersPanel.add(quantityTF);
        ordersPanel.add(ScrollL);
        ordersPanel.add(myScroll);
        refreshTable("ORDERS",table);
        ordersPanel.add(addBtnOrders);
        ordersPanel.add(delBtnOrders);
        ordersPanel.add(editBtnOrders);
        addBtnOrders.addActionListener(new ButtonClickActionOrders());
        delBtnOrders.addActionListener(new DeleteActionOrders());
        editBtnOrders.addActionListener(new EditActionOrders());

        tabbedPanel.addTab("Search",advancedSearch);
        advancedSearch.setLayout(new GridLayout(6,1));
        advancedSearch.add(fNameSearchL);
        advancedSearch.add(fnameOrdersTF);
        advancedSearch.add(partNameSearchL);
        advancedSearch.add(partNameSearchTF);
        advancedSearch.add(searchBtn);
        advancedSearch.add(myScroll3);

        this.add(tabbedPanel);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void refreshTable(String myTable,JTable table){
        conn = DBConnection.getConnection();
        try {
            state = conn.prepareStatement("select * from "+myTable);
            result = state.executeQuery();
            table.setModel(new MyModel(result));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clearFormUsers(){
        userNumberTF.setText("");
        fnameTF.setText("");
        lnameTF.setText("");
        emailTF.setText("");
        phoneTF.setText("");
    }

    public void clearFormPCGoods(){
        partNumberTF.setText("");
        partNameTF.setText("");
        priceTF.setText("");
    }

    public void clearFormOrders(){
        orderNumberTF.setText("");
        userNumberOrdersTF.setText("");
        partNumberOrdersTF.setText("");
        quantityTF.setText("");
    }

    public class ButtonClickActionUsers implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            conn = DBConnection.getConnection();
            String sql = "insert into USERS values(?,?,?,?,?)";

            try{
                state = conn.prepareStatement(sql);
                state.setInt(1,Integer.parseInt(userNumberTF.getText()));
                state.setString(2, fnameTF.getText());
                state.setString(3,lnameTF.getText());
                state.setString(4,emailTF.getText());
                state.setInt(5,Integer.parseInt(phoneTF.getText()));

                state.execute();
                refreshTable("USERS",table1);
                clearFormUsers();
            } catch (SQLException c) {
                c.printStackTrace();
            }
        }
    }

    public class ButtonClickActionPCGoods implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            conn = DBConnection.getConnection();
            String sql = "insert into PCGOODS values(?,?,?)";

            try{
                state = conn.prepareStatement(sql);
                state.setInt(1,Integer.parseInt(partNumberTF.getText()));
                state.setString(2, partNameTF.getText());
                state.setInt(3,Integer.parseInt(priceTF.getText()));

                state.execute();
                refreshTable("PCGOODS",table2);
                clearFormPCGoods();
            } catch (SQLException c) {
                c.printStackTrace();
            }
        }
    }

    public class ButtonClickActionOrders implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            conn = DBConnection.getConnection();
            String sql = "insert into ORDERS values(?,?,?,?)";

            try{
                state = conn.prepareStatement(sql);
                state.setInt(1,Integer.parseInt(orderNumberTF.getText()));
                state.setInt(2,Integer.parseInt(userNumberOrdersTF.getText()));
                state.setInt(3,Integer.parseInt(partNumberOrdersTF.getText()));
                state.setInt(4,Integer.parseInt(quantityTF.getText()));

                state.execute();
                refreshTable("ORDERS",table);
                clearFormOrders();
            } catch (SQLException c) {
                c.printStackTrace();
            }
        }
    }

    class DeleteActionUsers implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sqlDeleteString= "delete from USERS where USER_ID=?";

            try {
                state = conn.prepareStatement(sqlDeleteString);
                state.setInt(1,Integer.parseInt(userNumberTF.getText()));
                state.execute();

                refreshTable("USERS",table1);
                clearFormUsers();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    class DeleteActionPCGoods implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sqlDeleteString= "delete from PCGOODS where PART_ID=?";

            try {
                state = conn.prepareStatement(sqlDeleteString);
                state.setInt(1,Integer.parseInt(partNumberTF.getText()));
                state.execute();

                refreshTable("PCGOODS",table2);
                clearFormPCGoods();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    class DeleteActionOrders implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sqlDeleteString= "delete from ORDERS where ORDER_ID=?";

            try {
                state = conn.prepareStatement(sqlDeleteString);
                state.setInt(1,Integer.parseInt(orderNumberTF.getText()));
                state.execute();

                refreshTable("ORDERS",table);
                clearFormOrders();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    class EditActionUsers implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "update USERS set FIRST_NAME = '"+fnameTF.getText()+"',LAST_NAME='"+lnameTF.getText()+"',EMAIL='"+emailTF.getText()+"',PHONE='"+Integer.parseInt(phoneTF.getText())+"' where USER_ID=?";

            try {
                state = conn.prepareStatement(sql);
                state.setInt(1,Integer.parseInt(userNumberTF.getText()));
                state.execute();

                refreshTable("USERS",table1);
                clearFormUsers();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    class EditActionPCGoods implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "update PCGOODS set PART_NAME = '"+partNameTF.getText()+"',PRICE='"+Integer.parseInt(priceTF.getText())+"' where PART_ID=?";

            try {
                state = conn.prepareStatement(sql);
                state.setInt(1,Integer.parseInt(partNumberTF.getText()));
                state.execute();

                refreshTable("PCGOODS",table2);
                clearFormPCGoods();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    class EditActionOrders implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            conn = DBConnection.getConnection();
            String sql = "update ORDERS set USER_ID='"+Integer.parseInt(userNumberOrdersTF.getText())+"',PART_ID='"+Integer.parseInt(partNumberOrdersTF.getText())+"', QUANTITY = '"+Integer.parseInt(quantityTF.getText())+"' where ORDER_ID=?";

            try {
                state = conn.prepareStatement(sql);
                state.setInt(1,Integer.parseInt(orderNumberTF.getText()));
                state.execute();

                refreshTable("ORDERS",table);
                clearFormOrders();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}