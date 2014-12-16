package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Board;
import model.Model;
import controller.AddWordController;
import controller.ConnectPoemController;
import controller.ConnectRowController;
import controller.ConnectWordController;
import controller.DisconnectRowController;
import controller.DisconnectWordController;
import controller.MovePoemController;
import controller.MoveRowController;
import controller.MoveWordController;
import controller.PublishController;
import controller.RedoController;
import controller.ReleasePoemController;
import controller.RemoveWordController;
import controller.ShiftRowController;
import controller.StartWordController;
import controller.UndoController;

/**
 * Application Class
 * 
 * @author diwang
 *
 */
public class Application extends JFrame {
	private static final long serialVersionUID = 5446646227285497514L;

	/** Stores reference to model, so can use at anytime during GUI navigation. */
	Model model;

	JPanel topMenuPanel;
	ApplicationPanel panel;
	JPanel bottomMenuPanel;
	WordTable wordTable;

	JButton startButton;
	JButton addButton;
	JButton removeButton;
	JButton moveWordButton;
	JButton moveRowButton;
	JButton connectButton;
	JButton disconnectButton;
	JButton connectRowButton;
	JButton disconnectRowButton;
	JButton shiftRowButton;
	JButton connectPoemButton;
	JButton movePoemButton;
	JButton releasePoemButton;
	JButton publishButton;
	JButton undoButton;
	JButton redoButton;

	/**
	 * This is the default constructor
	 */
	public Application(Model m) {
		super();
		model = m;
		setTitle("Word Game");
		setSize(Board.widthOfFrame, Board.heightOfFrame);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

		Container pane = this.getContentPane();
		setBackground(Color.GRAY);

		// menu at top
		topMenuPanel = new JPanel();
		topMenuPanel.setBounds(0, 0, Board.widthOfFrame, Board.heightOfTopMenu);
		topMenuPanel.setBackground(Color.BLACK);

		addButton = new JButton("Add");
		topMenuPanel.add(addButton);

		removeButton = new JButton("Remove");
		topMenuPanel.add(removeButton);

		moveWordButton = new JButton("Move Word");
		topMenuPanel.add(moveWordButton);

		moveRowButton = new JButton("Move Row");
		topMenuPanel.add(moveRowButton);

		connectButton = new JButton("Connect");
		topMenuPanel.add(connectButton);

		disconnectButton = new JButton("Disconnect");
		topMenuPanel.add(disconnectButton);

		connectRowButton = new JButton("Connect Row");
		topMenuPanel.add(connectRowButton);

		disconnectRowButton = new JButton("Disconnect Row");
		topMenuPanel.add(disconnectRowButton);

		pane.add(topMenuPanel); // at top

		// Where words appear
		panel = new ApplicationPanel(model);
		panel.setBounds(0, Board.heightOfTopMenu, Board.widthOfBoard,
				Board.heightOfBoard);
		pane.add(panel); // in middle

		// menu at bottom
		bottomMenuPanel = new JPanel();
		bottomMenuPanel.setBounds(0, Board.heightOfTopMenu
				+ Board.heightOfBoard, Board.widthOfFrame,
				Board.heightOfBottomMenu);
		bottomMenuPanel.setBackground(Color.RED);

		startButton = new JButton("Start");
		bottomMenuPanel.add(startButton);

		shiftRowButton = new JButton("Shift Row");
		bottomMenuPanel.add(shiftRowButton);

		connectPoemButton = new JButton("Connect Poem");
		bottomMenuPanel.add(connectPoemButton);

		movePoemButton = new JButton("Move Poem");
		bottomMenuPanel.add(movePoemButton);

		releasePoemButton = new JButton("Release Poem");
		bottomMenuPanel.add(releasePoemButton);

		undoButton = new JButton("Undo");
		bottomMenuPanel.add(undoButton);

		redoButton = new JButton("Redo");
		bottomMenuPanel.add(redoButton);

		publishButton = new JButton("Publish");
		bottomMenuPanel.add(publishButton);

		pane.add(bottomMenuPanel);

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new StartWordController(model, Application.this).process();
				startButton.setEnabled(false);
			}
		});

		publishButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new PublishController(model, panel).process();
				JOptionPane.showMessageDialog(null,
						"Please check publish.png in the project!", "Publish",
						JOptionPane.YES_OPTION);
			}
		});

		// This is a traditional controller acting in response to single button
		// press
		undoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UndoController(model, Application.this).process();
			}
		});

		redoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RedoController(model, Application.this).process();
			}
		});

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new AddWordController(model, Application.this).register();
			}
		});

		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new RemoveWordController(model, Application.this).register();
			}
		});

		moveWordButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new MoveWordController(model, Application.this).register();
			}
		});

		moveRowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new MoveRowController(model, Application.this).register();
			}
		});

		connectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new ConnectWordController(model, Application.this).register();
			}
		});

		disconnectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new DisconnectWordController(model, Application.this)
						.register();
			}
		});

		connectRowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new ConnectRowController(model, Application.this).register();
			}
		});

		disconnectRowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new DisconnectRowController(model, Application.this).register();
			}
		});

		shiftRowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new ShiftRowController(model, Application.this).register();
			}
		});

		connectPoemButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ConnectPoemController(model, Application.this).register();
			}
		});

		movePoemButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MovePoemController(model, Application.this).register();
			}
		});

		releasePoemButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ReleasePoemController(model, Application.this).register();
			}
		});

		/**
		 * Add word table into the frame
		 */
		// chen chen added
		wordTable = new WordTable(model.getBoard(), panel);
		wordTable.setBounds(700, 50, 300, 600);
		pane.add(wordTable);
	}

	public ApplicationPanel getWordPanel() {
		return panel;
	}

	public WordTable getWordTable() {
		return wordTable;
	}

}
