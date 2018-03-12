package img;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Canvas;
import javax.swing.ImageIcon;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;

public class logo extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.

	public static void main(String[] args) {
		try {
			logo dialog = new logo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */
	/**
	 * Create the dialog.
	 */
	public logo() {
		setUndecorated(true);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 534, 416);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(logo.class.getResource("/img/NOVA LOGO(SEM FUNDO).png")));
			contentPanel.add(lblNewLabel);
		}
	}

}
