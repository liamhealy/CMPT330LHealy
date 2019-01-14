import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FilterByCategoryFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public FilterByCategoryFrame() {
		setTitle("Specify Filter Instructions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel instructionLabel = new JLabel("Please select the category corresponding with the items you would like to view.");
		instructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
		instructionLabel.setBounds(12, 29, 458, 16);
		contentPane.add(instructionLabel);
		
		JComboBox catComboBox = new JComboBox();
		catComboBox.setFont(new Font("SansSerif", Font.PLAIN, 13));
		catComboBox.setToolTipText("This field contains all possible category options.");
		catComboBox.setModel(new DefaultComboBoxModel(new String[] {"Humor", "Biography", "Autobiography", "Literature", "Mystery", "Graphic Novel", "Young Adult", "Romance", "Science Fiction"}));
		catComboBox.setBounds(168, 58, 134, 22);
		contentPane.add(catComboBox);
		
		JButton okButton = new JButton("OK");
		okButton.setFont(new Font("SansSerif", Font.PLAIN, 13));
		okButton.setToolTipText("Return to previous form in which desired items can be displayed.");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String category = (String) catComboBox.getSelectedItem();
				HealyBookFaceFrame.categoryFilter = category;
				dispose();
			}
		});
		okButton.setBounds(129, 108, 97, 25);
		contentPane.add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("SansSerif", Font.PLAIN, 13));
		cancelButton.setToolTipText("Exit this form.");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setBounds(246, 108, 97, 25);
		contentPane.add(cancelButton);
	}
}
