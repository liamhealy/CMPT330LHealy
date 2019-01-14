import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class HealyAddBookFrame extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HealyAddBookFrame frame = new HealyAddBookFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public HealyAddBookFrame() {
		setTitle("Add a Book to the Database");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel addBookIDlabel = new JLabel("Book ID:");
		addBookIDlabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		addBookIDlabel.setBounds(111, 36, 127, 16);
		contentPane.add(addBookIDlabel);
		
		JLabel addBookNameLabel = new JLabel("Book Name:");
		addBookNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		addBookNameLabel.setBounds(111, 65, 127, 16);
		contentPane.add(addBookNameLabel);
		
		JLabel addAuthorLabel = new JLabel("Author Name:");
		addAuthorLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		addAuthorLabel.setBounds(111, 94, 127, 16);
		contentPane.add(addAuthorLabel);
		
		JLabel addCategoryLabel = new JLabel("Category:");
		addCategoryLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		addCategoryLabel.setBounds(111, 123, 127, 16);
		contentPane.add(addCategoryLabel);
		
		JLabel addWholePriceLabel = new JLabel("Wholesale Price:");
		addWholePriceLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		addWholePriceLabel.setBounds(111, 152, 127, 16);
		contentPane.add(addWholePriceLabel);
		
		JLabel addRetailPriceLabel = new JLabel("Retail Price:");
		addRetailPriceLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		addRetailPriceLabel.setBounds(111, 181, 127, 16);
		contentPane.add(addRetailPriceLabel);
		
		JLabel addQuantLabel = new JLabel("Quantity:");
		addQuantLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		addQuantLabel.setBounds(111, 210, 127, 16);
		contentPane.add(addQuantLabel);
		
		JLabel addMinQuantLabel = new JLabel("Minimum Quantity:");
		addMinQuantLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		addMinQuantLabel.setBounds(111, 239, 127, 16);
		contentPane.add(addMinQuantLabel);
		
		JLabel titleLabel = new JLabel("Please Enter the Following:");
		titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 17));
		titleLabel.setBounds(162, 0, 209, 35);
		contentPane.add(titleLabel);
		
		JFormattedTextField minQuantFTF = new JFormattedTextField();
		minQuantFTF.setFont(new Font("Courier New", Font.PLAIN, 13));
		minQuantFTF.setToolTipText("Enter the minimum quantity for the new book.");
		minQuantFTF.setBounds(238, 238, 153, 22);
		contentPane.add(minQuantFTF);
		
		JFormattedTextField quantFTF = new JFormattedTextField();
		quantFTF.setFont(new Font("Courier New", Font.PLAIN, 13));
		quantFTF.setToolTipText("Enter the quantity of the new book.");
		quantFTF.setBounds(238, 209, 153, 22);
		contentPane.add(quantFTF);
		
		JFormattedTextField retailPriceFTF = new JFormattedTextField();
		retailPriceFTF.setFont(new Font("Courier New", Font.PLAIN, 13));
		retailPriceFTF.setToolTipText("Enter the retail price for the new book.");
		retailPriceFTF.setBounds(238, 180, 153, 19);
		contentPane.add(retailPriceFTF);
		
		JFormattedTextField wholePriceFTF = new JFormattedTextField();
		wholePriceFTF.setFont(new Font("Courier New", Font.PLAIN, 13));
		wholePriceFTF.setToolTipText("Enter the wholesale price for the new book.");
		wholePriceFTF.setBounds(238, 151, 153, 22);
		contentPane.add(wholePriceFTF);
		
		JFormattedTextField authorNameTF = new JFormattedTextField();
		authorNameTF.setFont(new Font("Courier New", Font.PLAIN, 13));
		authorNameTF.setToolTipText("Enter the name of the author of the new book.");
		authorNameTF.setBounds(238, 93, 153, 22);
		contentPane.add(authorNameTF);
		
		JFormattedTextField bookNameTF = new JFormattedTextField();
		bookNameTF.setFont(new Font("Courier New", Font.PLAIN, 13));
		bookNameTF.setToolTipText("Enter a name for the new book being added to the database.");
		bookNameTF.setBounds(238, 64, 153, 22);
		contentPane.add(bookNameTF);
		
		JFormattedTextField bookIdFTF = new JFormattedTextField();
		bookIdFTF.setFont(new Font("Courier New", Font.PLAIN, 13));
		bookIdFTF.setToolTipText("Enter a unique ID for the new book being added to the table.");
		bookIdFTF.setBounds(238, 35, 153, 22);
		contentPane.add(bookIdFTF);
		
		JButton okButton = new JButton("OK");
		okButton.setToolTipText("Return to the previous form where updated items can be viewed.");
		okButton.setFont(new Font("SansSerif", Font.PLAIN, 13));
		okButton.setBounds(155, 290, 97, 25);
		contentPane.add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setToolTipText("Exit this form.");
		cancelButton.setFont(new Font("SansSerif", Font.PLAIN, 13));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setBounds(264, 290, 97, 25);
		contentPane.add(cancelButton);
		
		JComboBox categoryComboBox = new JComboBox();
		categoryComboBox.setFont(new Font("SansSerif", Font.PLAIN, 13));
		categoryComboBox.setToolTipText("Select a category in which this new book would fall under.");
		categoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"-", "Humor", "Biography", "Autobiography", "Literature", "Mystery", "Graphic Novel", "Young Adult", "Romance", "Science Fiction"}));
		categoryComboBox.setBounds(238, 122, 153, 22);
		contentPane.add(categoryComboBox);
		
		JButton helpButton = new JButton("?");
		helpButton.setToolTipText("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(contentPane, "-This form requests input from the user to add another book to the inventory.\n" +
															   "-The book ID field must be a unique ID corresponding with existing data, it also must be an integer value.\n" +
															   "-The wholesale and retail price fields require actual monetary values.\n" +
															   "-The quantity field represents the inventory's current stock of the book.\n" +
															   "-The minimum quantity represents the inventory's minimum stock of the book that is necessary to carry it.");		
			
			}
		});
		helpButton.setFont(new Font("SansSerif", Font.BOLD, 13));
		helpButton.setBounds(473, 299, 47, 41);
		contentPane.add(helpButton);
		
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int newBookId = Integer.parseInt(bookIdFTF.getText());
				String newBookName = bookNameTF.getText();
				String newAuthorName = authorNameTF.getText();
				String newBookCategory = (String) categoryComboBox.getSelectedItem();
				double newBookWholesale = Integer.parseInt(wholePriceFTF.getText());
				double newBookRetail = Integer.parseInt(retailPriceFTF.getText());
				int newBookQuant = Integer.parseInt(quantFTF.getText());
				int newMinQuant = Integer.parseInt(minQuantFTF.getText());
				
				
				//Get the driver ready
				try {
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
				} 
				catch (ClassNotFoundException a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
				}
				
				int rs = 0;
				Statement stmt = null;
				
				try{
					//Establish
					Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Public/BookFace.accdb");
					//Create Statement
					stmt = conn.createStatement();
					//Execute Statement
					String queryAddStmt = "INSERT INTO Inventory(BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant)" + 
							  "VALUES('" + newBookId + "', '" + newBookName + "', '" + newAuthorName + "', '" + newBookCategory + "', '" + newBookWholesale + "', '" + newBookRetail + "', '" + newBookQuant + "', '" + newMinQuant + "');";
					rs = stmt.executeUpdate(queryAddStmt);
					
					conn.close();
					
					dispose();
				}
						
				catch (SQLException ex){
					System.out.println("SQL Exception: " + ex.getMessage());
					System.out.println("SQL State: " + ex.getSQLState());
					System.out.println("Vendor Error: " + ex.getErrorCode());
					ex.printStackTrace();
					dispose();
				}
		}
		});
	}	
}
