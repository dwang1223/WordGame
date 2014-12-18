package controller;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.Application;
import broker.util.IProtocol;
import broker.util.Swap;

/**
 * SwapWordController Class
 * 
 * @author Di Yu
 *
 */
public class SwapWordController extends MouseAdapter {

	int previousRow = 10000;
	int count = 0;

	JTable jtable;
	ArrayList<String> offerTypes = new ArrayList<String>();
	ArrayList<String> offerWords = new ArrayList<String>();
	ArrayList<String> requestTypes = new ArrayList<String>();
	ArrayList<String> requestWords = new ArrayList<String>();

	Application app;

	public SwapWordController(Application app, JTable jtable) {
		this.app = app;
		this.jtable = jtable;
	}

	public void mouseClicked(MouseEvent e) {
		if (count < app.getClient().numberOfSwapWords
				&& app.getClient().swapRequested == true) {
			if (e.getClickCount() == 2) {
				process(jtable.getSelectedRow(), previousRow, jtable
						.getValueAt(jtable.getSelectedRow(), 1).toString(),
						jtable.getValueAt(jtable.getSelectedRow(), 0)
								.toString());
			}
			processWordRequirement(count, app.getClient().numberOfSwapWords);
		}
	}

	public void reset() {
		this.app.getClient().swapRequested = false;
		count = 0;
		offerTypes.clear();
		offerWords.clear();
		requestTypes.clear();
		requestWords.clear();
		previousRow = 10000;
	}

	public void process(int selectedRow, int previousRow, String type,
			String content) {

		int row = selectedRow;
		if (row != previousRow) {
			offerTypes.add(type);
			offerWords.add(content);
			count++;
			previousRow = row;
		} else {
			reset();
			JOptionPane.showMessageDialog(app,
					"cannot choose the same word, swap abort", "Exception",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

	}

	public void processWordRequirement(int count, int swapWords) {
		try {
			if (count >= swapWords) {
				for (int i = 0; i < swapWords; i++) {
					JPanel fields = new JPanel(new GridLayout(2, 1));
					JTextField field = new JTextField(10);
					JComboBox<String> comboBox = new JComboBox<>(new String[] {
							"ANYTYPE", "noun", "verb", "prefix", "suffix",
							"conjunction", "determiner", "pronouns", "pronoun",
							"preposition", "number", "adverb", "adjective" });
					field.getDocument().addDocumentListener(
							new DocumentListener() {
								@Override
								public void insertUpdate(DocumentEvent e) {
									setUnable(fields, field, comboBox);
								}

								@Override
								public void removeUpdate(DocumentEvent e) {
									setUnable(fields, field, comboBox);
								}

								@Override
								public void changedUpdate(DocumentEvent e) {

								}

							});

					comboBox.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							field.setEditable(false);
							fields.revalidate();
							fields.repaint();
						}
					});

					fields.add(field);
					fields.add(comboBox);

					pop(fields, field, comboBox);
					String wordContent;
					if (field.getText().equals(""))
						wordContent = "*";
					else
						wordContent = field.getText();

					String wordType;
					if (comboBox.getSelectedItem().toString().equals("ANYTYPE"))
						wordType = "*";
					else
						wordType = comboBox.getSelectedItem().toString();

					requestTypes.add(wordType);
					requestWords.add(wordContent);
				}

				String swapMsg = makeRequest(app.getClient().broker.getID(),
						"*", app.getClient().numberOfSwapWords,
						offerTypes.toArray(new String[offerWords.size()]),
						offerWords.toArray(new String[offerWords.size()]),
						requestTypes.toArray(new String[offerWords.size()]),
						requestWords.toArray(new String[offerWords.size()]));
				sendRequest(swapMsg);

			}
		} catch (Exception es) {
			JOptionPane.showMessageDialog(app,
					"invalid input, swap is aborted..", "Exception",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("invalid input, swap is aborted");
			reset();
			return;
		}
	}

	public String makeRequest(String rid, String aid, int n,
			String[] offerTypes, String[] offerWords, String[] requestTypes,
			String[] requestWords) {
		Swap swapRequest = new Swap(rid, aid, n, offerTypes, offerWords,
				requestTypes, requestWords);

		String swapMsg = IProtocol.requestSwapMsg + IProtocol.separator
				+ swapRequest.flatten();
		return swapMsg;

	}

	public void pop(JPanel fields, JTextField field, JComboBox<String> comboBox) {
		int result = JOptionPane.showConfirmDialog(app, fields,
				"word and type", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		switch (result) {
		case JOptionPane.OK_OPTION:
			System.out.println("result is" + field.getText()
					+ comboBox.getSelectedItem().toString());
			break;
		case JOptionPane.CANCEL_OPTION:
			reset();
			return;
		}
	}

	public void setUnable(JPanel fields, JTextField field,
			JComboBox<String> comboBox) {
		comboBox.setEnabled(false);
		fields.revalidate();
		fields.repaint();
	}

	public void sendRequest(String swapMsg) {
		app.getClient().broker.getBrokerOutput().println(swapMsg);
		reset();
	}
}
