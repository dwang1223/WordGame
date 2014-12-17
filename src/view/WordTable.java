package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import model.Board;
import model.WordModel;
import broker.util.IProtocol;
import broker.util.Swap;
import controller.SearchWordController;
import controller.SortController;

/**
 * Set up JTable.
 * 
 * @author Chen Chen, Di Wang
 *
 */
public class WordTable extends JPanel {
	private static final long serialVersionUID = 7765250467850231518L;
	ApplicationPanel panel = null;
	WordModel wordModel = null;
	JScrollPane jsp = null;
	JTable jtable = null;
	Dimension tableSize = null;
	JButton searchButton = null;
	JTextField searchTestField = null;
	String keyWord = null;

	public WordTable(Board board, Application app) {
		setLayout(new FlowLayout());
		this.panel = app.getWordPanel();
		searchTestField = new JTextField(10);
		searchButton = new JButton("search");

		// create the model for the data which backs this table
		wordModel = new WordModel(board);

		// the proposed dimension of the UI
		tableSize = new Dimension(280, 550);

		// Scrollable panel will enclose the JTable and support scrolling
		// vertically
		jsp = new JScrollPane();
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setPreferredSize(tableSize);

		// Just add the JTable to the set. First create the list of Players,
		// then the SwingModel that supports the JTable which you now create.
		jtable = new JTable(wordModel);

		// let's tell the JTable about its columns.
		TableColumnModel columnModel = new DefaultTableColumnModel();

		// must build one by one...
		String[] headers = new String[] { WordModel.wordLabel,
				WordModel.typeLabel };
		int index = 0;
		for (String h : headers) {
			TableColumn col = new TableColumn(index++);
			col.setHeaderValue(h);
			col.setResizable(false);
			columnModel.addColumn(col);
		}
		jtable.setColumnModel(columnModel);

		// let's install a sorter and also make sure no one can rearrange
		// columns
		JTableHeader header = jtable.getTableHeader();
		header.setResizingAllowed(false);
		header.setReorderingAllowed(false);

		// purpose of this sorter is to sort by columns.
		header.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTableHeader h = (JTableHeader) e.getSource();
				new SortController(WordTable.this).process(h, e.getPoint());
			}
		});

		// Here's the trick. Make the JScrollPane take its view from the JTable.
		jsp.setViewportView(jtable);

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				keyWord = searchTestField.getText();
				new SearchWordController(board, panel, keyWord).process();
			}
		});

		jtable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int row = jtable.getSelectedRow();
						String keyWord = jtable.getValueAt(row, 0).toString();
						new SearchWordController(board, panel, keyWord)
								.process();
					}

				});

		// di yu added
		jtable.addMouseListener(new MouseAdapter() {
			int count = 0;

			ArrayList<String> offerTypes = new ArrayList<String>();
			ArrayList<String> offerWords = new ArrayList<String>();
			ArrayList<String> requestTypes = new ArrayList<String>();
			ArrayList<String> requestWords = new ArrayList<String>();

			public void mouseClicked(MouseEvent e) {

				// System.out.println("length is"+requestTypes.length);
				// System.out.println("the msg content is now "+msg);

				if (count < app.model.numberOfSwapWords) {
					if (e.getClickCount() == 2) {
						int row = jtable.getSelectedRow();
						// String cellmsg = jtable.getValueAt(row,
						// 0).toString();
						offerTypes.add(jtable.getValueAt(row, 1).toString());
						offerWords.add(jtable.getValueAt(row, 0).toString());
						count++;
					}
					try {
						if (count >= app.model.numberOfSwapWords) {
							count = 0;
							for (int i = 0; i < app.model.numberOfSwapWords; i++) {
								int index = i + 1;
								String requestString = JOptionPane
										.showInputDialog(app, index
												+ ": give a input word", null);
								requestTypes.add(requestString.split(",")[0]);
								requestWords.add(requestString.split(",")[1]);
							}
							System.out.println("information......");

							System.out.println(offerTypes);
							System.out.println();
							System.out.println(offerWords);
							System.out.println();
							System.out.println(requestTypes);
							System.out.println();

							System.out.println(requestWords);
							Swap swapRequest = new Swap(app.model.broker
									.getID(), "*", app.model.numberOfSwapWords,
									offerTypes.toArray(new String[offerWords
											.size()]), offerWords
											.toArray(new String[offerWords
													.size()]), requestTypes
											.toArray(new String[offerWords
													.size()]), requestWords
											.toArray(new String[offerWords
													.size()]));

							String swapMsg = IProtocol.requestSwapMsg
									+ IProtocol.separator
									+ swapRequest.flatten();
							app.model.broker.getBrokerOutput().println(swapMsg);
							offerTypes.clear();
							offerWords.clear();
							requestTypes.clear();
							requestWords.clear();
							System.out.println("size sample is now"
									+ offerTypes.size());
						}
					} catch (Exception es) {
						System.err.println("invalid input, swap is aborted");
						count = 0;
						offerTypes.clear();
						offerWords.clear();
						requestTypes.clear();
						requestWords.clear();
						return;
					}

				}
			}
		});
		// di yu added
		
		// add the scrolling Pane which encapsulates the JTable physical size
		// limited
		this.setPreferredSize(tableSize);
		this.add(searchTestField);
		this.add(searchButton);

		this.add(jsp);
	}

	public void refreshTable() {
		// this is the key method to ensure JTable view is synchronized
		jtable.revalidate();
		jtable.repaint();
		this.revalidate();
		this.repaint();
	}

	public void refreshTable(Board board) {
		wordModel = new WordModel(board);
		jtable = new JTable(wordModel);
		this.remove(jsp);
		jsp = new JScrollPane(jtable);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setPreferredSize(tableSize);
		// let's tell the JTable about its columns.
		TableColumnModel columnModel = new DefaultTableColumnModel();

		// must build one by one...
		String[] headers = new String[] { WordModel.wordLabel,
				WordModel.typeLabel };
		int index = 0;
		for (String h : headers) {
			TableColumn col = new TableColumn(index++);
			col.setHeaderValue(h);
			col.setResizable(false);
			columnModel.addColumn(col);
		}
		jtable.setColumnModel(columnModel);

		// let's install a sorter and also make sure no one can rearrange
		// columns
		JTableHeader header = jtable.getTableHeader();
		header.setResizingAllowed(false);
		header.setReorderingAllowed(false);
		// purpose of this sorter is to sort by columns.
		header.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTableHeader h = (JTableHeader) e.getSource();
				new SortController(WordTable.this).process(h, e.getPoint());
			}
		});

		jtable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int row = jtable.getSelectedRow();
						String keyWord = jtable.getValueAt(row, 0).toString();
						new SearchWordController(board, panel, keyWord)
								.process();
					}

				});
		this.add(jsp);
		this.revalidate();
		this.repaint();
	}
}
