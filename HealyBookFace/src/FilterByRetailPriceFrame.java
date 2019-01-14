import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

public class FilterByRetailPriceFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	
	
	/**
	 * Create the frame.
	 */
	public FilterByRetailPriceFrame() {
		setTitle("Specify Filter Instructions");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 657, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel instructionLabel = new JLabel("Please specify the minimum, maximum, or range of retail prices of items you would like to view.");
		instructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		instructionLabel.setBounds(12, 13, 621, 25);
		contentPane.add(instructionLabel);
		
		JFormattedTextField minRetailFTF = new JFormattedTextField();
		minRetailFTF.setFont(new Font("Courier New", Font.PLAIN, 15));
		minRetailFTF.setValue(new Double(0.0));
		minRetailFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		minRetailFTF.setToolTipText("Enter a minimum retail price value for items you would like to view.");
		minRetailFTF.setBounds(204, 106, 70, 42);
		contentPane.add(minRetailFTF);
		
		JFormattedTextField maxRetailFTF = new JFormattedTextField();
		maxRetailFTF.setFont(new Font("Courier New", Font.PLAIN, 15));
		maxRetailFTF.setValue(new Double(0.0));
		maxRetailFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		maxRetailFTF.setToolTipText("Enter a maximum retail price value for items you would like to view.");
		maxRetailFTF.setBounds(343, 106, 70, 42);
		contentPane.add(maxRetailFTF);
		
		JLabel divLabel = new JLabel("--");
		divLabel.setBounds(304, 119, 16, 16);
		contentPane.add(divLabel);
		
		JLabel lblMinimum = new JLabel("Minimum");
		lblMinimum.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblMinimum.setBounds(204, 77, 70, 16);
		contentPane.add(lblMinimum);
		
		JLabel lblMaximum = new JLabel("Maximum");
		lblMaximum.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblMaximum.setBounds(343, 77, 70, 16);
		contentPane.add(lblMaximum);
		
		JButton okButton = new JButton("OK");
		okButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
		okButton.setToolTipText("Return to previous form in which desired items can be displayed.");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Double retailMinimum = (Double) minRetailFTF.getValue();
				Double retailMaximum = (Double) maxRetailFTF.getValue();

				HealyBookFaceFrame.retailMinFilter = retailMinimum;
				HealyBookFaceFrame.retailMaxFilter = retailMaximum;

				dispose();	
			}
		});
		
		okButton.setBounds(259, 214, 97, 25);
		contentPane.add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setToolTipText("Exit this form.");
		cancelButton.setBounds(259, 252, 97, 25);
		contentPane.add(cancelButton);
		
		JButton filterHelpButton = new JButton("?");
		filterHelpButton.setToolTipText("Help");
		filterHelpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
					JOptionPane.showMessageDialog(contentPane, "-The two text fields on this form request monetary values.\n" +
															   "-One is a minimum value, representing the lowest possible retail price in view.\n" +
															   "-One is a maximum value, representing the highest possible retail price in view.\n" +
															   "-The fields can be used individually to filter items and can also be used together to create a price range.");		
				}
		});
		filterHelpButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		filterHelpButton.setBounds(580, 270, 47, 42);
		contentPane.add(filterHelpButton);
		
	}
	
}
