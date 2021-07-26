package gui;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import predictor.Predictor;

public class MainGui extends JFrame{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static private String wTitle = "String Predictor";

	private static final int WIDTH=840/2, HEIGHT=780/2;
	
	private JTextField tfInput;
	private JScrollPane spPredictions ;
	private JTable tPredictions;
	//private JButton bInput;
	
	
	private String[] titulos = { "Predicciones", "Frecuencia" };
	
	public Predictor p;

	
	public MainGui() throws FileNotFoundException {
		
		super(wTitle);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);

		iniciarComponentes();

		setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		p = new Predictor();
		
		
		
	}
	
	private void iniciarComponentes() {
		

		Container cont = new Container();
		setContentPane(cont);
		cont.setLayout(null);


		/* tfInput */
		tfInput = new JTextField();
		cont.add(tfInput);


		/* Tables */
		tPredictions = new JTable(new DefaultTableModel(null, titulos));
		spPredictions = new JScrollPane(tPredictions);
		cont.add(spPredictions);

		/*Table update*/
		MainGui thisFrame = this;
		tfInput.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) { thisFrame.resetTablePredictions(); }
		});

		/* JButton */
		/*bInput = new JButton("Confirm");
		bInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {thisFrame.resetTablePredictions();}
		});
		cont.add(bInput);*/
		

		//tfInput.setPreferredSize(new Dimension(WIDTH/3, 25));

		/* Positions */
		tfInput.setBounds(0, HEIGHT*3/7-10, WIDTH/2, 20);
		//bInput.setBounds(0, HEIGHT*3/7+10, WIDTH/2, 20);
		spPredictions.setBounds(WIDTH/2, 0, WIDTH/2, HEIGHT);

	}

	
	
	private void resetTablePredictions() {

		LinkedHashMap<String, Integer> predF = p.predictFrequency(tfInput.getText());
		int siz = predF.size(), i = 0;
		Object al[][] = new Object[siz][];
		for (String pred : predF.keySet()) {
			Object s[] = { pred, predF.get(pred)};
			al[i] = s;

			i++;
		}
		TableModel tm = new DefaultTableModel((Object[][]) al, titulos);

		tPredictions.setModel(tm);
	}



}
