package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Board;
import model.Model;
import controller.AddWordController;
import controller.ConnectWordController;
import controller.DisconnectWordController;
import controller.MoveWordController;
import controller.PublishController;
import controller.RemoveWordController;
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

	JButton addButton;
	JButton removeButton;
	JButton moveButton;
	JButton connectButton;
	JButton disconnectButton;
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

		moveButton = new JButton("Move");
		topMenuPanel.add(moveButton);

		connectButton = new JButton("Connect");
		topMenuPanel.add(connectButton);
		
		disconnectButton = new JButton("Disconnect");
		topMenuPanel.add(disconnectButton);
		
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

		undoButton = new JButton("Undo");
		bottomMenuPanel.add(undoButton);

		redoButton = new JButton("Redo");
		bottomMenuPanel.add(redoButton);
		
		publishButton = new JButton("Publish");
		bottomMenuPanel.add(publishButton);

		pane.add(bottomMenuPanel);

		publishButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new PublishController(model, panel).process();
			}
		});

		// This is a traditional controller acting in response to single button
		// press
		undoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UndoController(model, panel).process();
			}
		});

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new AddWordController(model, panel).register();
			}
		});

		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new RemoveWordController(model, panel).register();
			}
		});
		
		moveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// register controller
				new MoveWordController(model, Application.this).register();
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
				new DisconnectWordController(model, Application.this).register();
			}
		});
	}

	public ApplicationPanel getWordPanel() {
		return panel;
	}

}
