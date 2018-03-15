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
import Model.TransponderBD;
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
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
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
import Dao.DaoTransponder;

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
	
	DaoTransponder daoTransponder = new DaoTransponder();
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JCheckBox StatusMinErro;
	private JLabel lblNMdiaDe;
	private JTextField MinErro;
	
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
				"KM", "TIPO DE TRANSPONDER", "QUANTIDADE DE LEITURAS","Média de Erro (Metros)"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false,false
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
		panel_1.setLayout(new BorderLayout(0, 0));
				
				panel_6 = new JPanel();
				panel_6.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				panel_1.add(panel_6, BorderLayout.NORTH);
				lblTipo = new JLabel("Tipo de Transponder: ");
				panel_6.add(lblTipo);
				
				TipoTransponder = new JComboBox();
				panel_6.add(TipoTransponder);
				TipoTransponder.addItem("TODOS");
				
				panel_3 = new JPanel();
				panel_6.add(panel_3);
				panel_3.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				StatusKM = new JCheckBox("");
				StatusKM.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						FiltroKm.setEditable(StatusKM.isSelected());
						KmPeriodo.setEditable(StatusKM.isSelected());
						if(!StatusKM.isSelected()) PesquisarTrans();
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
				
				panel_7 = new JPanel();
				panel_1.add(panel_7, BorderLayout.CENTER);
				
				panel_2 = new JPanel();
				panel_7.add(panel_2);
				panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				
				StatusMinLeitura = new JCheckBox("");
				StatusMinLeitura.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						NumMinLeitura.setEditable(StatusMinLeitura.isSelected());
						if(!StatusMinLeitura.isSelected()) PesquisarTrans();
					}
					
				});
				panel_2.add(StatusMinLeitura);
				
				lblNMinimoDe = new JLabel("N\u00BA M\u00E1ximo de Leituras:");
				panel_2.add(lblNMinimoDe);
				
				NumMinLeitura = new JTextField();
				NumMinLeitura.setEditable(false);
				panel_2.add(NumMinLeitura);
				NumMinLeitura.setColumns(3);
				
				panel_8 = new JPanel();
				panel_8.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				panel_7.add(panel_8);
				
				StatusMinErro = new JCheckBox("");
				StatusMinErro.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent arg0) {
						MinErro.setEditable(StatusMinErro.isSelected());
						if(!StatusMinErro.isSelected()) PesquisarTrans();
					}
				});
				panel_8.add(StatusMinErro);
				
				lblNMdiaDe = new JLabel("N\u00BA M\u00E9dia de Erro:");
				panel_8.add(lblNMdiaDe);
				
				MinErro = new JTextField();
				MinErro.setEditable(false);
				MinErro.setColumns(3);
				panel_8.add(MinErro);
				

				
				btnBuscar = new JButton("Buscar");
				panel_7.add(btnBuscar);
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						
						PesquisarTrans();
					}

				});
				

				
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
					},
					new String[] {
						"Log","Loco","N\u00FAmero de Transponders", "Quantidade Leituras","Possível Perda"
					}
				) {
					boolean[] columnEditables = new boolean[] {
						false, false, false,false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
				tbLogs.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						String log = tbLogs.getValueAt(tbLogs.getSelectedRow(), 0).toString();
						if(LOG != null) {
							try {
								TabInfoLog dialog =  new TabInfoLog(LOG, log);
								dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								dialog.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
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
						
						daoTransponder = new DaoTransponder();
						
							ArrayList<String> c = new ArrayList<String>();
							for(TransponderBD t :  daoTransponder.getTodosTransposnders()){
								String tipo = t.getTipo();
								if(!c.contains(tipo))
									TipoTransponder.addItem(tipo);
								c.add(tipo);
							}
						
						daoTransponder.Close();
						
						PesquisarTrans();
						setTbLogs(LOG);
						int numNovostransponders = 0;
						if(LOG.getNewTranspoderBDs() != null)
							numNovostransponders =  LOG.getNewTranspoderBDs().size();
						JOptionPane.showMessageDialog(null, numNovostransponders + " Transponder(s) Novos Encontrados.");
				}
			}
		});
		mnNewMenu.add(mntmImportarLogs);
		

		ArrayList<String> c = new ArrayList<String>();
		
		for(TransponderBD t :  daoTransponder.getTodosTransposnders()){
			String tipo = t.getTipo();
			if(!c.contains(tipo))
				TipoTransponder.addItem(tipo);
			c.add(tipo);
		}
		daoTransponder.Close();
		setTbTransponder (null);

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
			if(logs != null) {
				TabInfoTranspoder dialog = new TabInfoTranspoder(logs, keyTrans);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
			
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
		Integer leituras = 0;
		
		
		
	
        DefaultTableModel modeloTable = (DefaultTableModel) tbTransponder.getModel();
        
        
        while (modeloTable.getRowCount() > 0) {
            modeloTable.removeRow(0);
        }
        
  
            daoTransponder = new DaoTransponder();
	        for (TransponderBD t : daoTransponder.getTodosTransposnders() ) {
	        
	                 int qtd  = 0;
	                 int erro = 0;
	                 
			         
			         String tipo = (String) TipoTransponder.getSelectedItem();
			         
			         
			         String numMinLeitura = NumMinLeitura.getText();
			         String minErroString = MinErro.getText();
			         int minQtd = 0;
			         int  minKM = 0;
			         int  maxKm = 0;
			         int  minErro = 0;
			         
			         int KM = t.getKm();
			         
			         if(StatusKM.isSelected()) {
				 		 int  FKm = Integer.valueOf(FiltroKm.getText());
					     int  PKm = Integer.valueOf(KmPeriodo.getText());	
					       minKM = FKm - PKm;
					       maxKm = FKm + PKm;
			         }
			         if(StatusMinLeitura.isSelected())
			        	 minQtd = Integer.valueOf(numMinLeitura);
			         if(StatusMinErro.isSelected())
			        	 minErro = Integer.valueOf(minErroString);
			         
			         
	                 if(logs != null) {
	                	 if(logs.getQuantidadeLeituras().get(t.getKey()) != null)
	                		 qtd =  logs.getQuantidadeLeituras().get(t.getKey());
	                	 
	                	 if(logs.getQuantidadeErros().get(t.getKey()) != null)
	                		 erro = logs.getQuantidadeErros().get(t.getKey());
	                 }
	                 int MediaErro =  MediaErros(erro,qtd);
			         if((tipo.equals("TODOS") || t.getTipo().equals(tipo)) && 
			            (!StatusMinLeitura.isSelected() || ( qtd <= minQtd ))&&
			            (!StatusMinErro.isSelected() || (  Math.abs(MediaErro) >= minErro ))&&
			            (!StatusKM.isSelected() || ((KM>=minKM)&&(KM<=maxKm)))){
			        	 
			        	 leituras = 0;
			        	 
			        	 if(logs !=  null) {
			        	         leituras    = qtd;
			        	         numLeituras =  numLeituras + leituras;
			        	 }
			        	 		 numTrans++;
			        	 		 
						         modeloTable.addRow(new Object[] { t.getKm(),
						        		                           t.getTipo(),
						        		                           leituras,
						        		                           MediaErros(erro,leituras)
								                                 });
			         }
	  
        }
        NumTrans.setText(String.valueOf(numTrans));
    	NumLeitura.setText(String.valueOf(numLeituras));
    	daoTransponder.Close();
    }
	
	private int MediaErros(int erro , int qtd) {
		if(qtd == 0 )
			return 0;
		return erro/qtd;
	}
	
	
	private String locomotiva(String nomeLog) {
		
		if(nomeLog.indexOf('0') == 0)
			return nomeLog.substring(1, 4);
		else
			return nomeLog.substring(0, 4);
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
	        		 							locomotiva(logNome),		
					   						   qtdLogs.get(logNome).size(),
	        		 						   qtdLeitura.get(logNome),
	        		 						   qtdPedido.get(logNome)
			                                 });

	      }
        }
        
    }
}
