package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Logs;
import Model.Transponder;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Toolkit;

public class TabInfoTranspoder extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tbTransponder;
	private JPanel panel;
	private JLabel lblKm;
	private JTextField km;
	private JTextField tipo;
	private JLabel lblTipo;
	private JTextField NumLeituras;
	private JLabel lblLeituras;
	private JTextField NumLogs;
	private JLabel lblLogs;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			TabInfoTranspoder dialog = new TabInfoTranspoder();
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
	public TabInfoTranspoder(Logs logs , String keyTrans) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TabInfoTranspoder.class.getResource("/img/NOVA LOGO(SEM FUNDO).png")));
		setTitle("VSTransponder  -  ValerianoSystem");
		setBounds(100, 100, 815, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				lblKm = new JLabel("KM :  ");
				lblKm.setFont(new Font("Tahoma", Font.PLAIN, 15));
				panel.add(lblKm);
			}
			{
				km = new JTextField();
				km.setEditable(false);
				km.setFont(new Font("Tahoma", Font.PLAIN, 26));
				panel.add(km);
				km.setColumns(5);
			}
			{
				lblTipo = new JLabel("Tipo :  ");
				lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
				panel.add(lblTipo);
			}
			{
				tipo = new JTextField();
				tipo.setEditable(false);
				tipo.setFont(new Font("Tahoma", Font.PLAIN, 26));
				tipo.setColumns(5);
				panel.add(tipo);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				tbTransponder = new JTable();
				tbTransponder.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null},
					},
					new String[] {
						"ERRO (m)", "LINHA", "DATA LEITURA", "Kph", "NOME DO LOG"
					}
				) {
					boolean[] columnEditables = new boolean[] {
						false, false, false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				scrollPane.setViewportView(tbTransponder);
			}
		}
		ArrayList<Transponder> trans = logs.getKeyTransponders().get(keyTrans);
		km.setText(String.valueOf(trans.get(0).getKM()));
		tipo.setText(trans.get(0).getTipo());
		{
			lblLeituras = new JLabel("Leituras:  ");
			lblLeituras.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel.add(lblLeituras);
		}
		{
			NumLeituras = new JTextField();
			NumLeituras.setText(String.valueOf(trans.size()));
			NumLeituras.setFont(new Font("Tahoma", Font.PLAIN, 26));
			NumLeituras.setEditable(false);
			NumLeituras.setColumns(5);
			panel.add(NumLeituras);
		}
		{
			lblLogs = new JLabel("Logs:  ");
			lblLogs.setFont(new Font("Tahoma", Font.PLAIN, 15));
			panel.add(lblLogs);
		}
		{
			NumLogs = new JTextField();
			NumLogs.setText("0");
			NumLogs.setFont(new Font("Tahoma", Font.PLAIN, 26));
			NumLogs.setEditable(false);
			NumLogs.setColumns(3);
			panel.add(NumLogs);
		}
		
	
		setTbTransponder(trans);
	}
	
	private void   setTbTransponder (ArrayList<Transponder> KeyTransponders) {

        DefaultTableModel modeloTable = (DefaultTableModel) tbTransponder.getModel();
        
        Set <String>  ArquivosLog = new  HashSet <String>();
        while (modeloTable.getRowCount() > 0) {
            modeloTable.removeRow(0);
        }
        
        if(KeyTransponders != null){
	        for (Transponder t : KeyTransponders) {
	        	// "ERRO (Metros)", "LINHA", "DATA LEITURA", "Kph", "NOME DO LOG"
	        	
	         ArquivosLog.add(t.getLog());
	         modeloTable.addRow(new Object[] { t.getErro(),
	        		 						 valor(t.getLinha1()),
	        		 						  t.getData(),
	        		 						 valor(t.getKph()),
	        		 						  t.getLog()
			                                 });

	      }
        }
        NumLogs.setText(String.valueOf(ArquivosLog.size()));
    }
	
	private Integer valor(int v) {
		
		if(v == 0)
			return null;
		else
			return v;
		
	}
}
