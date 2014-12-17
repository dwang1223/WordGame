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

public class ClientApp implements IHandleBrokerMessage {
	public BrokerClient broker;
	Board board;
	ArrayList<Word> unprotectedWords;

	Application app;
	ApplicationPanel panel;

	Model model;

	public int numberOfSwapWords;
	public String requestMessage;

	// public BrokerClient broker;

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
		// TODO Auto-generated method stub
		System.out.println("Broker Gone");
	}

	public void execute() throws IOException {
//		broker = new BrokerClient("gheineman.cs.wpi.edu", 9172);
		broker = new BrokerClient("localhost", 9172);
		// model.setBrokerClient(broker);

		System.out.println("id is" + broker.getID());

		if (!broker.connect()) {
			System.err.println("unable to connect to broker");
			broker.shutdown();
		}

		// at this point we are connected, and we will block waiting for
		// any messages from the broker. These will be sent to the process
		System.out.println("Status:" + broker.getStatus());

		// start thread to process commands from broker.
		ReaderThread thread = new ReaderThread(broker, this);
		thread.start();

	}

	@Override
	public void process(BrokerClient broker, String msg) {
		System.out.println("word list information...");
		for (Word wd : this.board.words) {
			System.out.print(wd.getContent() + " ");
		}
		System.out.println("-------------");
		// TODO Auto-generated method stub
		System.out.println("Process message:" + msg);

		if (msg.startsWith(IProtocol.denySwapMsg)) {
			System.out.println("Denied swap request");
			return;
		}

		if (msg.startsWith(IProtocol.matchSwapMsg)) {
			Swap s = MatchSwapMessage.getSwap(msg);
			// /// initialize the request word bean
			ArrayList<WordBean> requestWordBeanList = new ArrayList<WordBean>();
			for (int i = 0; i < s.n; i++) {
				requestWordBeanList.add(new WordBean(s.requestTypes[i],
						s.requestWords[i]));
			}

			for (String st : s.requestTypes) {
				System.out.print(st + " ");
			}
			System.out.println();
			for (String st : s.requestWords) {
				System.out.print(st + " ");
			}

			System.out.println("------------------");
			Hashtable<Integer, Word> tempRemovedTable = ConvertWildcard
					.convert(unprotectedWords, requestWordBeanList);
			// /add back the temporarily removed word back to the unprotected
			// word list
			System.out.println("the temp removed table is now created!");

			try {

				// System.out.println(hs.get(0).content+hs.get(0).type);
				// System.out.println(hs.get(1).content+hs.get(1).type);
				// System.out.println(hs.get(2).content+hs.get(2).type);
				System.out.println("info of the temp hashtable "
						+ tempRemovedTable.size());
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
					System.out.println("now in the for loop");
					// unprotectedWords.add(new
					// WordBean(tempRemovedTable.get(i).type,tempRemovedTable.get(i).content));
					// this.unprotectedWords.addAll(tempRemovedTable);

					Enumeration<Integer> enumKey = tempRemovedTable.keys();
					while (enumKey.hasMoreElements()) {
						Integer key = enumKey.nextElement();
						Word wd = tempRemovedTable.get(key);
						this.unprotectedWords.add(wd);
					}
				}

			} catch (Exception e) {
				System.out.println("Exception!!!!!");
				e.printStackTrace();
			}

			System.out.println();
			System.out.println("------------------");
			System.out.println("A client is trying such action message: "
					+ s.flatten());

			System.out.println("Accepting satisfy swap request");
			// what should we do? Agree of course! Here is where your code would
			// normally "convert" wildcards into actual words in your board
			// state.
			// for now this is already assumed (note sample/other swap)
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
			System.out.println("the confirm message received is: ");
			System.out.println(msg);
			// /////////////////////////debug
			System.out.println("offer word is: ");
			for (String st : s.offerWords) {
				System.out.println(st);
			}

			System.out.println("offer type is: ");
			for (String st : s.offerTypes) {
				System.out.println(st);
			}

			System.out.println("request word is: ");
			for (String st : s.requestWords) {
				System.out.println(st);
			}

			System.out.println("request type is: ");
			for (String st : s.requestTypes) {
				System.out.println(st);
			}
			// /////////////////////////
			System.out.println("^^^^^^^^^^^^^^^^");
			try {
				for (Word wb : unprotectedWords) {
					System.out.println("Prior State:" + wb.getContent() + "--"
							+ wb.getType());
				}
				System.out.println("^^^^^^^^^^^^^^^^");
				// // carry out the swap.
				if (broker.getID().equals(s.requestor_id)) {
					// We made the request: remove offer words and take request
					// words
					for (int i = 0; i < s.n; i++) {
						UpdateWordList.updateRemove(board.words, s.offerWords,
								i);
						for (int j = 0; j < s.n; j++) {
							// Word showWord = new Word()
						}

					}
					for (int i = 0; i < s.n; i++) {
						UpdateWordList.updateAdd(board.words, s.requestWords,
								s.requestTypes, i);
						for (int j = 0; j < s.n; j++) {

							System.out.println("should refresh GUI!!!");

						}
					}
				} else {
					// We accepted request: remove request words and take offer
					// words
					// We made the request: remove offer words and take request
					// words
					for (int i = 0; i < s.n; i++) {
						UpdateWordList.updateRemove(board.words,
								s.requestWords, i);
					}
					for (int i = 0; i < s.n; i++) {
						UpdateWordList.updateAdd(board.words, s.offerWords,
								s.offerTypes, i);
						System.out.println("offered words are: ");
						for (String st : s.offerWords) {
							System.out.println(st);
						}

						for (int j = 0; j < s.n; j++) {
							// jp.setBackground(Color.RED);
						}
					}
				}
			} catch (Exception e) {
				System.out.println("Exception in confirming!!!");
			}

			app.getWordTable().refreshTable();
			panel.redraw();
			panel.repaint();
			System.out.println("^^^^^^^^^^^^^^^^");
			for (Word wb : board.getUnprotectedWords()) {
				System.out.println("Update State:" + wb.getContent() + "--"
						+ wb.getType());
			}
			System.out.println("^^^^^^^^^^^^^^^^");
			return;
		}
	}

}
