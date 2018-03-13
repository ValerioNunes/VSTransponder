package VS;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import LeituraArquivos.BuscaArquivos;
import Model.Logs;
import Model.Transponder;
import View.TabInfoLog;
import View.TabInfoTranspoder;
import img.logo;

import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import DB.DataBase;
import DB.Pastas;
import javax.swing.ImageIcon;

public class VSTransponder {

	private JFrame frmVstransponderValerianosystem;
	private JScrollPane scrollPane;
	private JTable tbTransponder;
	private JPanel panel;
	private Logs LOG;
	private JLabel lblNumeroTransponders;
	private JTextField NumTrans;
	private JTextField NumLeitura;
	private JLabel lblNLeituras;
	private JTextField NumLogs;
	private JLabel lblNLogs;
	private JPanel panel_1;
	private JComboBox TipoTransponder;
	private JLabel lblTipo;
	private JButton btnBuscar;
	private JLabel lblNMinimoDe;
	private JTextField NumMinLeitura;
	private JPanel panel_2;
	private JCheckBox StatusMinLeitura;
	private JPanel panel_3;
	private JCheckBox StatusKM;
	private JLabel lblKm;
	private JTextField FiltroKm;
	private JLabel lblPerodometros;
	private JTextField KmPeriodo;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmImportarLogs;
	private JScrollPane scrollPane_1;
	private JTable tbLogs;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VSTransponder window = new VSTransponder();
					window.frmVstransponderValerianosystem.setVisible(true);
					
					 try {
				            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				                if ("Windows".equals(info.getName())) {
				                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
				                    break;
				                }
				            }	           
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (InstantiationException e1) {
							e1.printStackTrace();
						} catch (IllegalAccessException e1) {
							e1.printStackTrace();
						} catch (UnsupportedLookAndFeelException e1) {
							e1.printStackTrace();
						}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VSTransponder() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmVstransponderValerianosystem = new JFrame();
		frmVstransponderValerianosystem.setIconImage(Toolkit.getDefaultToolkit().getImage(VSTransponder.class.getResource("/img/NOVA LOGO(SEM FUNDO).png")));
		frmVstransponderValerianosystem.setTitle("VSTransponder  -  ValerianoSystem");
		frmVstransponderValerianosystem.setBounds(100, 100, 1065, 339);
		frmVstransponderValerianosystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVstransponderValerianosystem.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		panel_4 = new JPanel();
		frmVstransponderValerianosystem.getContentPane().add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);
		
		tbTransponder = new JTable();
		tbTransponder.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"KM", "TIPO DE TRANSPONDER", "QUANTIDADE DE LEITURAS"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbTransponder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String KM = tbTransponder.getValueAt(tbTransponder.getSelectedRow(), 0).toString();
				String tipo = tbTransponder.getValueAt(tbTransponder.getSelectedRow(), 1).toString();
				System.out.println(KM+" - "+tipo);
				String key = KM+" - "+tipo;
				openInfoTranspoder(LOG, key);
			}
		});
		scrollPane.setViewportView(tbTransponder);
		
		panel = new JPanel();
		panel_4.add(panel, BorderLayout.NORTH);
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNLogs = new JLabel("N\u00BA Arquivos de Logs :  ");
		lblNLogs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblNLogs);
		
		NumLogs = new JTextField();
		NumLogs.setText("0");
		NumLogs.setFont(new Font("Tahoma", Font.PLAIN, 21));
		NumLogs.setEditable(false);
		NumLogs.setColumns(5);
		panel.add(NumLogs);
		
		
		lblNumeroTransponders = new JLabel("N\u00BA Transponders Encontrados:  ");
		lblNumeroTransponders.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblNumeroTransponders);
		
		NumTrans = new JTextField();
		NumTrans.setText("0");
		NumTrans.setFont(new Font("Tahoma", Font.PLAIN, 21));
		NumTrans.setEditable(false);
		NumTrans.setColumns(5);
		panel.add(NumTrans);
		
		lblNLeituras = new JLabel("N\u00BA Leituras Realizadas :  ");
		lblNLeituras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblNLeituras);
		
		NumLeitura = new JTextField();
		NumLeitura.setText("0");
		NumLeitura.setFont(new Font("Tahoma", Font.PLAIN, 21));
		NumLeitura.setEditable(false);
		NumLeitura.setColumns(5);
		panel.add(NumLeitura);
		
		panel_1 = new JPanel();
		panel_4.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.add(panel_3);
		StatusKM = new JCheckBox("");
		StatusKM.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				FiltroKm.setEditable(StatusKM.isSelected());
				KmPeriodo.setEditable(StatusKM.isSelected());
			}
		});
		panel_3.add(StatusKM);
		
		lblKm = new JLabel("KM:");
		panel_3.add(lblKm);
		
		FiltroKm = new JTextField();
		FiltroKm.setEditable(false);
		FiltroKm.setColumns(5);
		panel_3.add(FiltroKm);
		
		lblPerodometros = new JLabel("Periodo (Metros):");
		panel_3.add(lblPerodometros);
		
		KmPeriodo = new JTextField();
		KmPeriodo.setEditable(false);
		KmPeriodo.setColumns(5);
		panel_3.add(KmPeriodo);
		lblTipo = new JLabel("Tipo de Transponder: ");
		panel_1.add(lblTipo);
		
		TipoTransponder = new JComboBox();
		

				panel_1.add(TipoTransponder);
				
				panel_2 = new JPanel();
				panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				panel_1.add(panel_2);
				
				StatusMinLeitura = new JCheckBox("");
				StatusMinLeitura.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						NumMinLeitura.setEditable(StatusMinLeitura.isSelected());
					}
				});
				panel_2.add(StatusMinLeitura);
				
				lblNMinimoDe = new JLabel("N\u00BA M\u00E1ximo de Leituras:");
				panel_2.add(lblNMinimoDe);
				
				NumMinLeitura = new JTextField();
				NumMinLeitura.setEditable(false);
				panel_2.add(NumMinLeitura);
				NumMinLeitura.setColumns(3);
				

				
				btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						
						PesquisarTrans();
					}

				});
				panel_1.add(btnBuscar);
				
				TipoTransponder.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(TipoTransponder.getSelectedItem() != null)
								PesquisarTrans();
					}
				});
				
				panel_5 = new JPanel();
				frmVstransponderValerianosystem.getContentPane().add(panel_5, BorderLayout.CENTER);
				panel_5.setLayout(new BorderLayout(0, 0));
				
				
				scrollPane_1 = new JScrollPane();
				panel_5.add(scrollPane_1);
				
				tbLogs = new JTable();
				tbLogs.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null},
					},
					new String[] {
						"Nome do Log", "N\u00FAmero de Transponders", "Quantidade Leituras","Possível Perda"
					}
				) {
					boolean[] columnEditables = new boolean[] {
						false, false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				tbLogs.getColumnModel().getColumn(0).setPreferredWidth(87);
				tbLogs.getColumnModel().getColumn(1).setPreferredWidth(123);
				tbLogs.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						String log = tbLogs.getValueAt(tbLogs.getSelectedRow(), 0).toString();
						
						try {
							TabInfoLog dialog =  new TabInfoLog(LOG, log);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				scrollPane_1.setViewportView(tbLogs);
				
				lblNewLabel = new JLabel("");
				lblNewLabel.setIcon(new ImageIcon(VSTransponder.class.getResource("/img/NOVA LOGO(SEM FUNDO).png")));
				panel_5.add(lblNewLabel, BorderLayout.SOUTH);
		
		menuBar = new JMenuBar();
		frmVstransponderValerianosystem.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Arquivo");
		menuBar.add(mnNewMenu);
		
		mntmImportarLogs = new JMenuItem("Importar Logs");
		mntmImportarLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscaArquivos busca = new BuscaArquivos();
				LOG = busca.BuscarArquivo();
				if(LOG != null) {
						int numlogs  = LOG.getLogs().size();
						NumLogs.setText(String.valueOf(numlogs));
						TipoTransponder.removeAllItems();
						TipoTransponder.addItem("TODOS");
						for(String tipo : LOG.getTipoTransponders()) {
							TipoTransponder.addItem(tipo);
						}
						
						PesquisarTrans();
						setTbLogs(LOG);
						int numNovostransponders =  LOG.getNewTranspoderBDs().size();
						JOptionPane.showMessageDialog(null, numNovostransponders + " Transponder(s) Novos Encontrados.");
				}
			}
		});
		mnNewMenu.add(mntmImportarLogs);

	
	}
	private Boolean isNumero(String texto) {
		
		  try  
		  {  
			 Integer.valueOf(texto);
			 return true;
		  }  
		  catch(NumberFormatException nfe)  
		  {  
			  return false;
		  } 
	}
	private void openInfoTranspoder(Logs logs, String keyTrans) {
		
		try {
			TabInfoTranspoder dialog = new TabInfoTranspoder(logs, keyTrans);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void PesquisarTrans() {
		
		String numMinLeitura = NumMinLeitura.getText();
		String km = FiltroKm.getText();
		String PeriodoKm = KmPeriodo.getText();
		
		if(!StatusMinLeitura.isSelected() && !StatusKM.isSelected())
				setTbTransponder (LOG);
		else if(StatusMinLeitura.isSelected() && isNumero(numMinLeitura) && !StatusKM.isSelected()) {
			    setTbTransponder (LOG);
		}else if((!StatusMinLeitura.isSelected() ||
				 isNumero(numMinLeitura)) && (
				 !StatusKM.isSelected() ||
				 isNumero(km)&&
				 isNumero(PeriodoKm))) {
			 setTbTransponder (LOG);
		}else {
	          JOptionPane.showMessageDialog(null, "Número do Filtro  Inválido");
	  	
		}
	}
	
	private void   setTbTransponder (Logs logs) {

		int numLeituras = 0;
		int numTrans = 0;
		int leituras;
		
		
        DefaultTableModel modeloTable = (DefaultTableModel) tbTransponder.getModel();
        Map<String,Integer> QuantidadeLeituras = logs.getQuantidadeLeituras();
        
        while (modeloTable.getRowCount() > 0) {
            modeloTable.removeRow(0);
        }
        
 
        
        
        if(logs != null){
	        for (String t : logs.getTranspodersLidos()) {
			         String trans[] = t.split(" - ");
			         String tipo = (String) TipoTransponder.getSelectedItem();
			         int qtd = QuantidadeLeituras.get(t);
			         String numMinLeitura = NumMinLeitura.getText();
			         int minQtd = 0;
			         int  minKM = 0;
			         int  maxKm = 0;
			         
			         int KM = Integer.valueOf(trans[0]);
			         if(StatusKM.isSelected()) {
				 		 int  FKm = Integer.valueOf(FiltroKm.getText());
					     int  PKm = Integer.valueOf(KmPeriodo.getText());	
					       minKM = FKm - PKm;
					       maxKm = FKm + PKm;
			         }
			         if(StatusMinLeitura.isSelected())
			        	 minQtd = Integer.valueOf(numMinLeitura);
			         
			         if((tipo.equals("TODOS") || trans[1].equals(tipo)) && 
			            (!StatusMinLeitura.isSelected() || ( qtd <= minQtd ))&&
			            (!StatusKM.isSelected() || ((KM>=minKM)&&(KM<=maxKm)))){
			        	         leituras =  QuantidadeLeituras.get(t);
			        	 		 numTrans++;
			        	 		 numLeituras =  numLeituras + leituras;
			        	 	
						         modeloTable.addRow(new Object[] { trans[0],
						        		                           trans[1],
						        		                          leituras
								                                 });
			         }

	      }
        }
        NumTrans.setText(String.valueOf(numTrans));
    	NumLeitura.setText(String.valueOf(numLeituras));
    }
	
	private void   setTbLogs(Logs log) {

        DefaultTableModel modeloTable = (DefaultTableModel) tbLogs.getModel();
        
        
        Map<String, Integer> qtdLeitura = log.getQuantidadeLeiturasPorLogs();
        Map<String,Set<Integer>> qtdLogs = log.getTranspondersPorLogs();
        Map<String, Integer> qtdPedido = new HashMap<String, Integer>();
        
        ArrayList<Integer> T = log.getTranspondersBD();
        
        
        
        for (String l :  log.getLogs()) {
        	    Set<Integer> Transponders = log.getTranspondersPorLogs().get(l);
        	    int i=0;
        	    int pos = -1;
		        for (Integer t : Transponders) {
		        	if(pos != -1  && pos < T.size()) {
		        		Integer km = T.get(pos);
		        		if(!km.equals(t) && !Transponders.contains(km)) {
		        			i++;
		        		}
		        	}
		        	pos =  T.indexOf(t)+1;
		        }
		        qtdPedido.put(l,i);
		      }
        
        
        while (modeloTable.getRowCount() > 0) {
            modeloTable.removeRow(0);
        }
        
        if(log != null){
	        for (String logNome : log.getLogs()) {
	        	// "ERRO (Metros)", "LINHA", "DATA LEITURA", "Kph", "NOME DO LOG"
	        	

	         modeloTable.addRow(new Object[] { logNome,		
					   						   qtdLogs.get(logNome).size(),
	        		 						   qtdLeitura.get(logNome),
	        		 						  qtdPedido.get(logNome)
			                                 });

	      }
        }
        
    }
}
