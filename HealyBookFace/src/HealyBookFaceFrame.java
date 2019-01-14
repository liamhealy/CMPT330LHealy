import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Color;

public class HealyBookFaceFrame extends JFrame {
	static Double retailMinFilter = 0.0;
	static Double retailMaxFilter = 0.0;
	static String categoryFilter = null;
	
	public HealyBookFaceFrame(double retailMinimum, double retailMaximum, String catFilter) {
		retailMinFilter = retailMinimum;
		retailMaxFilter = retailMaximum;
		categoryFilter = catFilter;
	}
	
	private JPanel contentPane;
	private JTable queryTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HealyBookFaceFrame frame = new HealyBookFaceFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	boolean setByBookName = false;
	boolean setByRetailPrice = false;
	boolean setByCategory = false;
	
	/**
	 * Create the frame.
	 */
	public HealyBookFaceFrame() {
		setTitle("BookFace");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 912, 454);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		JMenuItem exitOption = new JMenuItem("Exit");
		exitOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		menuFile.add(exitOption);
		
		JMenu menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		
		JMenuItem mntmBookfaceHelp = new JMenuItem("BookFace Help");
		mntmBookfaceHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contentPane, "-This window contains the item bank displaying book information for books in the inventory.\n" +
														   "-Every book in the inventory has a unique Book ID and Name.\n" +
														   "-There are wide ranges of authors as well as genres.\n" +
														   "-From this window, users can view the inventory, add items, sort the data, and filter the data.\n" +
														   "-In order to add a book to the inventory, follow the tools menu to 'Add Book'.\n" +
														   "-To sort the data in the inventory, follow the tools menu to 'Set Sort'.\n" +
														   "-To set filters for inventory items appearing in the window, follow the tools menu to 'Set Filter'.\n" +
														   "-File, -> Exit will close the form.\n" +
														   "-To resize columns, click in between any two and drag left or right based on desired size.");		
			}
		});
		menuHelp.add(mntmBookfaceHelp);
		
		JMenu menuTools = new JMenu("Tools");
		menuBar.add(menuTools);
		
		JMenuItem addBookOption = new JMenuItem("Add Book");
		addBookOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable(){
					public void run(){
						try{
							HealyAddBookFrame frame = new HealyAddBookFrame();
							frame.setVisible(true);
							frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						}
						catch(Exception i){
							i.printStackTrace();
						}
					}
				});
			}
		});
		menuTools.add(addBookOption);
		
		JMenu menuSort = new JMenu("Set Sort");
		menuTools.add(menuSort);
		
		JMenuItem sortByBookName = new JMenuItem("By Book Name");
		sortByBookName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//By Book Name
				setByBookName = true;
			}
		});
		menuSort.add(sortByBookName);
		
		JMenuItem sortByRetailPrice = new JMenuItem("By Retail Price");
		sortByRetailPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//By Retail Price
				setByRetailPrice = true;
			}
		});
		menuSort.add(sortByRetailPrice);
		
		JMenuItem sortByCategory = new JMenuItem("By Category");
		sortByCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//By Category
				setByCategory = true;
			}
		});
		menuSort.add(sortByCategory);
		
		JMenu menuFilter = new JMenu("Set Filter");
		menuTools.add(menuFilter);
		
		JMenuItem filterByRetailPrice = new JMenuItem("By Retail Price");
		filterByRetailPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Filter By Retail Price
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FilterByRetailPriceFrame frame = new FilterByRetailPriceFrame();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		menuFilter.add(filterByRetailPrice);
		
		JMenuItem filterByCategory = new JMenuItem("By Category");
		filterByCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FilterByCategoryFrame frame = new FilterByCategoryFrame();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		menuFilter.add(filterByCategory);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("This table displays all information on books stored in this database.");
		scrollPane.setBounds(10, 13, 872, 233);
		contentPane.add(scrollPane);
		
		queryTable = new JTable();
		queryTable.setToolTipText("<html>All fields in this item window display corresponding information about a book;<br>\r\nAll fields are also resizable.</html>");
		queryTable.setFont(new Font("Courier New", Font.PLAIN, 13));
		scrollPane.setViewportView(queryTable);
		queryTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Book ID", "Book Name", "Author Name", "Category", "Wholesale Price", "Retail Price", "Quantity", "Minimum Quantity"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class, Double.class, Double.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		queryTable.getColumnModel().getColumn(0).setPreferredWidth(39);
		queryTable.getColumnModel().getColumn(1).setPreferredWidth(108);
		queryTable.getColumnModel().getColumn(2).setPreferredWidth(111);
		queryTable.getColumnModel().getColumn(4).setPreferredWidth(48);
		queryTable.getColumnModel().getColumn(5).setPreferredWidth(47);
		queryTable.getColumnModel().getColumn(6).setPreferredWidth(45);
		queryTable.getColumnModel().getColumn(7).setPreferredWidth(45);
		
		JLabel alertLabel =(new JLabel(""));
		alertLabel.setForeground(Color.GRAY);
		alertLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		alertLabel.setBounds(222, 259, 540, 23);
		contentPane.add(alertLabel);
		alertLabel.setVisible(false);
		
		JButton restoreButton = new JButton("Restore Original");
		restoreButton.setToolTipText("When selected the inventory will re-display the items with no sort or filter restrictions.");
		restoreButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
		restoreButton.setVisible(false);
		restoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String queryStmt = queryStmt = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory";
					alertLabel.setVisible(false);
					
					while(queryTable.getRowCount() > 0)
						((DefaultTableModel) queryTable.getModel()).removeRow(0);
					
					try{
						//Establish
						Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Public/BookFace.accdb");
						//Create Statement
						Statement stmt = conn.createStatement();
						//Execute Statement
						ResultSet rs = stmt.executeQuery(queryStmt);
						
						int numColumns = rs.getMetaData().getColumnCount();
						
						while(rs.next()){
							Object[] row = new Object[numColumns];
							
							//Grab the fields from the record and put them into the row
							for(int i = 0; i < numColumns; i++){
								row[i] = rs.getObject(i+1);
							}
							
							((DefaultTableModel)queryTable.getModel()).insertRow(rs.getRow() -1, row);

						}
						
						restoreButton.setVisible(false);
						rs.close();
						conn.close();

					}
				
					catch (SQLException ex){
						System.out.println("SQL Exception: " + ex.getMessage());
						System.out.println("SQL State: " + ex.getSQLState());
						System.out.println("Vendor Error: " + ex.getErrorCode());
						ex.printStackTrace();
					}
			}
		});
		restoreButton.setBounds(339, 338, 193, 37);
		contentPane.add(restoreButton);
		
		JButton refreshItemsButton = new JButton("Refresh Item Window");
		refreshItemsButton.setToolTipText("When selected, the inventory will re-display the items to suit specified preferences.");
		refreshItemsButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
		refreshItemsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String queryStmt;
				if(setByBookName == true){
					queryStmt = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory ORDER BY BookName asc";
					alertLabel.setText("The items are currently being listed alphabetically by book name.");
					alertLabel.setVisible(true);
					restoreButton.setVisible(true);
				}
				else if(setByRetailPrice == true){
					queryStmt = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory ORDER BY RetailPrice asc";
					alertLabel.setText("The items are currently being listed by retail price from least to greatest.");
					alertLabel.setVisible(true);
					restoreButton.setVisible(true);
				}
				else if(setByCategory == true){
					queryStmt = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory ORDER BY Category";
					alertLabel.setText("The items are currently being listed by category.");
					alertLabel.setVisible(true);
					restoreButton.setVisible(true);
				}
				else if(retailMinFilter != 0.0 && retailMaxFilter == 0.0 && categoryFilter == null){
					queryStmt = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory WHERE RetailPrice >= " + retailMinFilter + " ORDER BY RetailPrice ASC;";
					restoreButton.setVisible(true);
					alertLabel.setText("The minimum retail price for this set of items is $" + retailMinFilter + ".");
					alertLabel.setVisible(true);

				}
				else if(retailMinFilter == 0.0 && retailMaxFilter != 0.0 && categoryFilter == null){
					queryStmt = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory WHERE RetailPrice <= " + retailMaxFilter + " ORDER BY RetailPrice DESC;";
					restoreButton.setVisible(true);
					alertLabel.setText("The maximum retail price for this set of items is $" + retailMaxFilter + ".");
					alertLabel.setVisible(true);
				}
				else if(retailMinFilter != 0.0 && retailMaxFilter != 0.0 && categoryFilter == null){
					queryStmt = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory WHERE RetailPrice >= " + retailMinFilter + " AND RetailPrice <= " + retailMaxFilter + " ORDER BY BookID;";
					restoreButton.setVisible(true);
					alertLabel.setText("The minimum retail price for this set of items is $" + retailMinFilter + " and the maximum is $" + retailMaxFilter + ".");
					alertLabel.setVisible(true);
				}
				else if(categoryFilter != null){
					queryStmt = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory WHERE Category = '" + categoryFilter + "';";
					restoreButton.setVisible(true);
					alertLabel.setText("Only books that fall under the '" + categoryFilter + "' category are being displayed.");
					alertLabel.setVisible(true);
				}
				else{
					queryStmt = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory";
					alertLabel.setText("");
					alertLabel.setVisible(false);
					restoreButton.setVisible(false);
				}
				
				while(queryTable.getRowCount() > 0)
					((DefaultTableModel) queryTable.getModel()).removeRow(0);
				
				try{
					//Establish
					Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Public/BookFace.accdb");
					//Create Statement
					Statement stmt = conn.createStatement();
					//Execute Statement
					ResultSet rs = stmt.executeQuery(queryStmt);
					
					int numColumns = rs.getMetaData().getColumnCount();
					
					while(rs.next()){
						Object[] row = new Object[numColumns];
						
						//Grab the fields from the record and put them into the row
						for(int i = 0; i < numColumns; i++){
							row[i] = rs.getObject(i+1);
						}
						
						((DefaultTableModel)queryTable.getModel()).insertRow(rs.getRow() -1, row);

					}
					
					rs.close();
					conn.close();

				}
			
				catch (SQLException ex){
					System.out.println("SQL Exception: " + ex.getMessage());
					System.out.println("SQL State: " + ex.getSQLState());
					System.out.println("Vendor Error: " + ex.getErrorCode());
					ex.printStackTrace();
				}
		}
		});
		refreshItemsButton.setBounds(339, 288, 193, 37);
		contentPane.add(refreshItemsButton);
		
		
		//Step 1: Get Driver Ready
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs = null;
		Statement stmt = null;
		String queryStmt;
		if(setByBookName == true){
			queryStmt = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory ORDER BY BookName desc";
		}
		else if(setByRetailPrice == true){
			queryStmt = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory ORDER BY RetailPrice asc";
		}
		else if(setByCategory == true){
			queryStmt = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory ORDER BY Category";
		}
		else{
			queryStmt = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory";
		}
		
		//Step 2: Establish the connection
		try{
			//Establish
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Public/BookFace.accdb");
			//Create Statement
			stmt = conn.createStatement();
			//Execute Statement
			rs = stmt.executeQuery(queryStmt);
			
			int numColumns = rs.getMetaData().getColumnCount();
			
			//Process results
			while(rs.next()){
				int bookID = rs.getInt("BookID");
				String bookName = rs.getString("BookName");
				String authorName = rs.getString("AuthorName");
				String category = rs.getString("Category");
				double wholePrice = rs.getDouble("WholesalePrice");
				double retailPrice = rs.getDouble("RetailPrice");
				int quant = rs.getInt("QOH");
				int minQuant = rs.getInt("MinQuant");
				
				//Create an object to hold a record
				Object[] row = new Object[numColumns];
				
				//Grab the fields from the record and put them into the row
				for(int i = 0; i < numColumns; i++){
					row[i] = rs.getObject(i+1);
				}//for
				
				//insert this row into our JTable
				((DefaultTableModel)queryTable.getModel()).insertRow(rs.getRow() -1, row);
				
				
				System.out.println(bookID + "\t" + bookName + "\t" + authorName + "\t" + category + "\t" + wholePrice + "\t" + retailPrice + "\t" + quant + "\t" + minQuant + "\n");
			} //while
			
			//Clean up
			rs.close();
			conn.close();
		}//end of try
		catch (SQLException ex){
			System.out.println("SQL Exception: " + ex.getMessage());
			System.out.println("SQL State: " + ex.getSQLState());
			System.out.println("Vendor Error: " + ex.getErrorCode());
			ex.printStackTrace();
		} //catch
	}
	
	
}
