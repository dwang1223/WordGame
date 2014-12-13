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
import controller.RefreshTableListener;
import controller.SearchWordController;
import controller.SortController;

/**
 * Set up JTable.
 * 
 * @author Chen Chen
 *
 */
public class WordTable extends JPanel {
	private static final long serialVersionUID = 7765250467850231518L;
	WordModel wordModel = null;
	ApplicationPanel panel = null;
	JTable jtable = null;
	Board board;

	JButton searchButton;
	JTextField searchTestField;

	String keyWord;

	public WordTable(Board board, ApplicationPanel panel) {

		this.board = board;
		this.panel = panel;

		setLayout(new FlowLayout());

		searchTestField = new JTextField(10);
		searchButton = new JButton("search");

		// create the model for the data which backs this table
		wordModel = new WordModel(board);

		// add to listener chain
		// this is the point that will result in bug
		board.addListener(new RefreshTableListener(this));

		// set preferred size
		Dimension tableSize = new Dimension(280, 550);

		jtable = new JTable(wordModel);
		jtable.setPreferredSize(tableSize);

		TableColumnModel columnModel = new DefaultTableColumnModel();

		String[] headers = new String[] { WordModel.wordLabel,
				WordModel.typeLabel };
		int index = 0;
		for (String h : headers) {
			TableColumn col = new TableColumn(index++);
			col.setHeaderValue(h);
			columnModel.addColumn(col);
		}
		jtable.setColumnModel(columnModel);
		JTableHeader header = jtable.getTableHeader();
		header.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTableHeader h = (JTableHeader) e.getSource();
				new SortController(WordTable.this).process(h, e.getPoint());
			}
		});
		JScrollPane jsp = new JScrollPane(jtable);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp.setPreferredSize(tableSize);
		// jsp.setViewportView(jtable);

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				keyWord = searchTestField.getText();
				new SearchWordController(board, keyWord, panel).process();
			}
		});

		jtable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						// board.findWordList = new ArrayList<Word>();
						// int index = jtable.getSelectedRow();
						// Word selectedWord =
						// board.unprotectedWords.get(index);
						// board.findWordList.add(selectedWord);
						// // System.out.println(selectedWord.getContent());
						// new FindWordController(board, panel,
						// selectedWord).process();
					}

				});

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

}
