package es.codeurjc.ais.tictactoe;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.codeurjc.ais.tictactoe.TicTacToeGame.Event;
import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeHandler.ServerToClientMsg;

public class Connection {

	private WebSocketSession session;
	private ObjectMapper json;

	public Connection(ObjectMapper json, WebSocketSession session) {
		this.json = json;
		this.session = session;
	}

	public void sendEvent(Event event) {

		ServerToClientMsg msg = new ServerToClientMsg();
		msg.action = event.type;
		msg.data = event.data;

		try {

			String msgJson = json.writeValueAsString(msg);

			synchronized (session) {
				session.sendMessage(new TextMessage(msgJson));
				System.out.println("Sent message '" + msgJson + "' to client " + session.getId());
			}

		} catch (Exception e) {
			System.err.println("Exception sending action to client.");
			e.printStackTrace(System.err);
		}
	}

	public void sendEvent(EventType type, Object value) {
		
		Event event = new Event();

		event.type = type;
		event.data = value;
		
		sendEvent(event);
	}

}
