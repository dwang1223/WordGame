package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
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
import controller.SearchWordController;
import controller.SortController;
import controller.SwapWordController;

/**
 * Set up JTable.
 * 
 * @author Chen Chen, Di Wang, Di Yu
 *
 */
public class WordTable extends JPanel {
	private static final long serialVersionUID = 7765250467850231518L;
	Application app = null;
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
		this.app = app;
		this.panel = this.app.getWordPanel();
		searchTestField = new JTextField(10);
		searchButton = new JButton("search");

		// create the model for the data which backs this table
		wordModel = new WordModel(board);

		// the proposed dimension of the UI
		tableSize = new Dimension(280, 500);

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
		jtable.addMouseListener(new SwapWordController(app, jtable));

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
		jtable.addMouseListener(new SwapWordController(app, jtable));
		this.add(jsp);
		this.revalidate();
		this.repaint();
	}
}
