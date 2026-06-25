import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainDashboard {

    DefaultTableModel tableModel = new DefaultTableModel();
    JTable table = new JTable(tableModel);

    {
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
    }


    public MainDashboard(){

        JFrame frame = new JFrame("Smart Library Management System");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLayout(null);

        tableModel.addColumn("Book ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Author");

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu booksMenu = new JMenu("Books");
        JMenu reportsMenu = new JMenu("Reports");

        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        menuBar.add(booksMenu);
        menuBar.add(reportsMenu);

        frame.setJMenuBar(menuBar);

        exitItem.addActionListener(e -> System.exit(0));
        //===sidebar====
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(new Color(30,30,45));
        sidebar.setBounds(0,0,200,1000);



        JButton dashboardBtn = new JButton("Dashboard");
        JButton addBookBtn = new JButton("Add Book");
        JButton viewBookBtn = new JButton("View Books");
        JButton searchBookBtn = new JButton("Search Book");

        addBookBtn.setFocusPainted(false);
        viewBookBtn.setFocusPainted(false);
        searchBookBtn.setFocusPainted(false);

        dashboardBtn.setBounds(20,40,160,40);
        addBookBtn.setBounds(20,100,160,40);
        viewBookBtn.setBounds(20,160,160,40);
        searchBookBtn.setBounds(20,220,160,40);

        sidebar.add(dashboardBtn);
        sidebar.add(addBookBtn);
        sidebar.add(viewBookBtn);
        sidebar.add(searchBookBtn);

        //====main panel==
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(200,0,1200,800);
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(null);
        dashboardPanel.setBounds(0,0,1200,800);
        JLabel dashTitle = new JLabel("Library Dashboard");
        dashTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        dashTitle.setBounds(450,50,400,40);

        dashboardPanel.add(dashTitle);
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);

        {
            table.setRowHeight(28);
            table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        }

        //===addbook panel===
        JPanel addBookPanel = new JPanel();
        addBookPanel.setLayout(null);
        addBookPanel.setBounds(0,0,1200,800);

        JLabel idLabel = new JLabel("Book ID:");
        JLabel titleLabel = new JLabel("Book Title:");
        JLabel authorLabel = new JLabel("Author:");

        JTextField idField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();

        //===save book=====
        JButton saveBookBtn = new JButton("Save Book");

        idLabel.setBounds(400,200,100,30);
        idField.setBounds(500,200,200,30);

        titleLabel.setBounds(400,250,100,30);
        titleField.setBounds(500,250,200,30);

        authorLabel.setBounds(400,300,100,30);
        authorField.setBounds(500,300,200,30);

        saveBookBtn.setBounds(500,360,150,35);

        addBookPanel.add(idLabel);
        addBookPanel.add(idField);
        addBookPanel.add(titleLabel);
        addBookPanel.add(titleField);
        addBookPanel.add(authorLabel);
        addBookPanel.add(authorField);
        addBookPanel.add(saveBookBtn);

        JPanel viewBookPanel = new JPanel(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(table);

        JButton deleteBtn = new JButton("Delete Selected Book");

        JPanel topPanel = new JPanel();
        topPanel.add(deleteBtn);

        viewBookPanel.add(topPanel,BorderLayout.NORTH);
        viewBookPanel.add(scrollPane,BorderLayout.CENTER);

        //==search panel===
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        searchPanel.setBounds(0,0,1200,800);

        JLabel searchLabel = new JLabel("Enter Book ID:");

        JTextField searchField = new JTextField();

        JButton searchBtn = new JButton("Search");

        searchLabel.setBounds(450,200,120,30);
        searchField.setBounds(580,200,200,30);
        searchBtn.setBounds(580,250,120,35);

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchBtn);

        addBookBtn.addActionListener(e -> {

            mainPanel.removeAll();
            mainPanel.setLayout(null);
            mainPanel.add(addBookPanel);
            mainPanel.revalidate();
            mainPanel.repaint();

        });

        viewBookBtn.addActionListener(e -> {

            mainPanel.removeAll();
            mainPanel.setLayout(new BorderLayout());
            mainPanel.add(viewBookPanel);
            mainPanel.revalidate();
            mainPanel.repaint();

        });
        dashboardBtn.addActionListener(e -> {

            mainPanel.removeAll();
            mainPanel.setLayout(null);
            mainPanel.add(dashboardPanel);
            mainPanel.revalidate();
            mainPanel.repaint();

        });

        searchBookBtn.addActionListener(e -> {

            mainPanel.removeAll();
            mainPanel.setLayout(null);
            mainPanel.add(searchPanel);
            mainPanel.revalidate();
            mainPanel.repaint();

        });

        saveBookBtn.addActionListener(e -> {

            String id = idField.getText();
            String title = titleField.getText();
            String author = authorField.getText();

            tableModel.addRow(new Object[]{id,title,author});

            idField.setText("");
            titleField.setText("");
            authorField.setText("");

        });

        deleteBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row!=-1){

                tableModel.removeRow(row);

            }else{

                JOptionPane.showMessageDialog(frame,"Select a book first");

            }

        });

        searchBtn.addActionListener(e -> {

            String searchId = searchField.getText();

            for(int i=0;i<tableModel.getRowCount();i++){

                if(tableModel.getValueAt(i,0).toString().equals(searchId)){

                    table.setRowSelectionInterval(i,i);

                    mainPanel.removeAll();
                    mainPanel.setLayout(new BorderLayout());
                    mainPanel.add(viewBookPanel);
                    mainPanel.revalidate();
                    mainPanel.repaint();

                    return;

                }

            }

            JOptionPane.showMessageDialog(frame,"Book not found");

        });

        frame.add(sidebar);
        mainPanel.setLayout(null);
        mainPanel.add(dashboardPanel);
        frame.add(mainPanel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}