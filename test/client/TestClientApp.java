package client;

import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;
import model.Model;
import model.Word;
import view.Application;
import broker.BrokerClient;
import broker.util.Swap;

/**
 * TestClientApp Class
 * @author Di Yu
 *
 */
public class TestClientApp extends TestCase {

	public TestClientApp(String name) {
		super(name);
	}
	
	public void testClientApp(){
		Model model = new Model();
		Application app = new Application(model);
		
		ClientApp client = new ClientApp(model,app);
	}
	
	public void testSetBrokerClient(){
		Model model = new Model();
		Application app = new Application(model);
		
		ClientApp client = new ClientApp(model,app);
		BrokerClient broker = new BrokerClient("localhost", 9172);
		client.setBrokerClient(broker);
	}
	
	public void testExecute(){
		Model model = new Model();
		Application app = new Application(model);
		
		ClientApp client = new ClientApp(model,app);
		BrokerClient broker = new BrokerClient("localhost", 9172);
		client.setBrokerClient(broker);
		
		try {
			client.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void testExecuteException(){
		Model model = new Model();
		Application app = new Application(model);
		
		ClientApp client = new ClientApp(model,app);
		BrokerClient broker = new BrokerClient("localhost", 9173);
		client.setBrokerClient(broker);
		
		client.brokerGone();
		
	}
	
	public void testDenySwap(){
		Model model = new Model();
		Application app = new Application(model);
		
		ClientApp client = new ClientApp(model,app);
		BrokerClient broker = new BrokerClient("localhost", 9172);
		client.setBrokerClient(broker);
		
		client.process(broker, "DENY_SWAP..");
	}
	
	public void testMatchSwap(){
		Model model = new Model();
		Application app = new Application(model);
		
		ClientApp client = new ClientApp(model,app);
		BrokerClient broker = new BrokerClient("localhost", 9172);
		client.setBrokerClient(broker);
		String message = "MATCH_SWAP:c0165c5c-feb2-47e3-8461-227a4f258c17:0fe2cb76-b086-4ac5-9072-db46616b8745:1:noun:Sample:*:Git:";
		
		//client.process(broker, message);
	}
	
	public void testConfirmSwap(){
		Model model = new Model();
		Application app = new Application(model);
		
		ClientApp client = new ClientApp(model,app);
		BrokerClient broker = new BrokerClient("localhost", 9172);
		client.setBrokerClient(broker);
		String message = "CONFIRM_SWAP:c0165c5c-feb2-47e3-8461-227a4f258c17:0fe2cb76-b086-4ac5-9072-db46616b8745:1:noun:Sample:noun:Git:";
		
		client.process(broker, message);
	}
	
	public void testUpdateRequester(){
		Model model = new Model();
		Application app = new Application(model);
		ArrayList<Word> wl = new ArrayList<>();
		Word other = new Word(20, 20, "wang", "noun", false, false, false,
				false);
		wl.add(other);
		model.getBoard().words =wl;
		ClientApp client = new ClientApp(model,app);
		BrokerClient broker = new BrokerClient("localhost", 9172);
		client.setBrokerClient(broker);
		String message = "CONFIRM_SWAP:c0165c5c-feb2-47e3-8461-227a4f258c17:0fe2cb76-b086-4ac5-9072-db46616b8745:1:noun:Sample:noun:Git:";
		
		
		String rid = "uniqueid";
		String aid = "*";
		int n =1;
		String[] offerTypes = {"noun"};
		String[] offerWords = {"have"};
		String[] requestTypes = {"noun"};
		String[] requestWords = {"*"};
		
		Swap s = new Swap(rid,aid,n,offerTypes,offerWords,requestTypes,requestWords);
		client.updateRequester(s);
		
	}
	
	public void testUpdateAcceptor(){
		Model model = new Model();
		Application app = new Application(model);
		ArrayList<Word> wl = new ArrayList<>();
		Word other = new Word(20, 20, "wang", "noun", false, false, false,
				false);
		wl.add(other);
		model.getBoard().words =wl;
		ClientApp client = new ClientApp(model,app);
		BrokerClient broker = new BrokerClient("localhost", 9172);
		client.setBrokerClient(broker);
		String message = "CONFIRM_SWAP:c0165c5c-feb2-47e3-8461-227a4f258c17:0fe2cb76-b086-4ac5-9072-db46616b8745:1:noun:Sample:noun:Git:";
		
		
		String rid = "uniqueid";
		String aid = "*";
		int n =1;
		String[] offerTypes = {"noun"};
		String[] offerWords = {"have"};
		String[] requestTypes = {"noun"};
		String[] requestWords = {"*"};
		
		Swap s = new Swap(rid,aid,n,offerTypes,offerWords,requestTypes,requestWords);
		client.updateAcceptor(s);
		
	}

}
