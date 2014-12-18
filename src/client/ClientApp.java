package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import model.Board;
import model.Model;
import model.Word;
import util.ConvertWildcard;
import util.UpdateWordList;
import util.WordBean;
import view.Application;
import view.ApplicationPanel;
import broker.BrokerClient;
import broker.handler.IHandleBrokerMessage;
import broker.handler.ReaderThread;
import broker.util.ConfirmSwapMessage;
import broker.util.IProtocol;
import broker.util.MatchSwapMessage;
import broker.util.Swap;

/**
 * ClientApp Class
 * 
 * @author Di Yu
 *
 */

public class ClientApp implements IHandleBrokerMessage {
	public BrokerClient broker;
	Board board;
	ArrayList<Word> unprotectedWords = new ArrayList<Word>();

	Application app;
	ApplicationPanel panel;

	Model model;

	public int numberOfSwapWords;
	public String requestMessage;
	public boolean swapRequested = false;

	public ClientApp(Model model, Application app) {
		this.model = model;
		this.board = model.getBoard();
		this.unprotectedWords = this.board.getUnprotectedWords();

		this.app = app;
		this.panel = app.getWordPanel();
	}

	public void setBrokerClient(BrokerClient broker) {
		this.broker = broker;
	}

	// @Override
	public void brokerGone() {
		System.out.println("Broker Gone");
	}

	public void execute() throws IOException {

		broker = new BrokerClient("localhost", 9172);

		if (!broker.connect()) {
			System.err.println("unable to connect to broker");
			broker.shutdown();
		}
		System.out.println("id is" + broker.getID());

		System.out.println("Status:" + broker.getStatus());

		ReaderThread thread = new ReaderThread(broker, this);
		thread.start();

	}

	@Override
	public void process(BrokerClient broker, String msg) {

		if (msg.startsWith(IProtocol.denySwapMsg)) {
			System.out.println("Denied swap request");
			return;
		}

		if (msg.startsWith(IProtocol.matchSwapMsg)) {
			unprotectedWords = this.board.getUnprotectedWords();
			Swap s = MatchSwapMessage.getSwap(msg);
			ArrayList<WordBean> requestWordBeanList = new ArrayList<WordBean>();
			for (int i = 0; i < s.n; i++) {
				requestWordBeanList.add(new WordBean(s.requestTypes[i],
						s.requestWords[i]));
			}

			Hashtable<Integer, Word> tempRemovedTable = ConvertWildcard
					.convert(unprotectedWords, requestWordBeanList);

			try {

				/**
				 * this means that the match is failed since the temp table
				 * contains nothing
				 */
				if (tempRemovedTable.size() == 0) {
					System.out.println("Unable to satisfy swap request");
					broker.getBrokerOutput().println(
							IProtocol.denySwapMsg + IProtocol.separator
									+ s.requestor_id);
					System.out.println("this swap doesn't match ...");
					return;
				}

				for (int i = 0; i < s.n; i++) {
					Enumeration<Integer> enumKey = tempRemovedTable.keys();
					while (enumKey.hasMoreElements()) {
						Integer key = enumKey.nextElement();
						Word wd = tempRemovedTable.get(key);
						this.unprotectedWords.add(wd);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			StringBuffer matchMsg = new StringBuffer();
			matchMsg.append(IProtocol.confirmSwapMsg)
					.append(IProtocol.separator).append(s.requestor_id)
					.append(IProtocol.separator).append(s.acceptor_id)
					.append(IProtocol.separator).append(s.n)
					.append(IProtocol.separator);

			for (String oType : s.offerTypes) {
				matchMsg.append(oType).append(IProtocol.separator);
			}
			for (String oWord : s.offerWords) {
				matchMsg.append(oWord).append(IProtocol.separator);
			}
			for (int i = 0; i < s.n; i++) {
				matchMsg.append(tempRemovedTable.get(i).getType()).append(
						IProtocol.separator);
			}
			for (int i = 0; i < s.n; i++) {
				matchMsg.append(tempRemovedTable.get(i).getContent()).append(
						IProtocol.separator);
			}
			System.out.println("the match message is" + matchMsg);
			broker.getBrokerOutput().println(matchMsg);
			return;

		}

		if (msg.startsWith(IProtocol.confirmSwapMsg)) {
			Swap s = ConfirmSwapMessage.getSwap(msg);
			try {

				if (broker.getID().equals(s.requestor_id)) {
					updateRequester(s);

				} else {
					updateAcceptor(s);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.clearRedoMoves();
			model.clearUndoMoves();
			app.getWordTable().refreshTable();
			panel.redraw();
			panel.repaint();
			return;
		}
	}

	public void updateRequester(Swap s) {
		for (int i = 0; i < s.n; i++) {
			UpdateWordList.updateRemove(board.words, s.offerWords, i);
		}
		for (int i = 0; i < s.n; i++) {
			UpdateWordList.updateAdd(board.words, s.requestWords,
					s.requestTypes, i);
		}

	}

	public void updateAcceptor(Swap s) {
		for (int i = 0; i < s.n; i++) {
			UpdateWordList.updateRemove(board.words, s.requestWords, i);
		}
		for (int i = 0; i < s.n; i++) {
			UpdateWordList
					.updateAdd(board.words, s.offerWords, s.offerTypes, i);
		}
	}

}
