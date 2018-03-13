package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Logs;
import Model.Transponder;
import Model.TransponderBD;

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

public class TabInfoLog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tbTransponder;
	private JPanel panel;
	private JTextField NomeLog;
	private JLabel lblTipo;

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
	public TabInfoLog(Logs logs , String nomeLog) {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(TabInfoLog.class.getResource("/img/NOVA LOGO(SEM FUNDO).png")));
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
				lblTipo = new JLabel("Poss\u00EDveis Transponder(s) n\u00E3o Lido do log :  ");
				lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
				panel.add(lblTipo);
			}
			{
				NomeLog = new JTextField();
				NomeLog.setEditable(false);
				NomeLog.setFont(new Font("Tahoma", Font.PLAIN, 26));
				NomeLog.setColumns(10);
				NomeLog.setText(nomeLog);
				panel.add(NomeLog);
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
						"Km"
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
		
		Set<Integer> trans = logs.getTranspondersPorLogs().get(nomeLog);
		List TranspodersLidos = new ArrayList(trans); 
		Collections.sort(TranspodersLidos);
		

		
		
		setTbTransponder(TranspodersLidos,logs);
	}
	
	private void   setTbTransponder (List<Integer> Transponders, Logs logs) {

        DefaultTableModel modeloTable = (DefaultTableModel) tbTransponder.getModel();
      

        while (modeloTable.getRowCount() > 0) {
            modeloTable.removeRow(0);
        }
        
        ArrayList<Integer> T = logs.getTranspondersBD();
        
        int pos = -1;
        if( Transponders != null){
	        for (Integer t : Transponders) {
	        	
	        	if(pos != -1) {
	        		Integer km = T.get(pos);
	        		if( !km.equals(t) && !Transponders.contains(km)) {
	        			
			        	modeloTable.addRow(new Object[] { T.get(pos) });
	        		}
	        	}
	        	pos =  T.indexOf(t)+1;

	      }
        }
    }
	
	private Integer valor(int v) {
		
		if(v == 0)
			return null;
		else
			return v;
		
	}
}
