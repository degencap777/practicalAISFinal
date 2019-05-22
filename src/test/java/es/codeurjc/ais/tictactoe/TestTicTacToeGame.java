package es.codeurjc.ais.tictactoe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;
import static org.mockito.ArgumentMatchers.eq;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;

public class TestTicTacToeGame{
	
	
	TicTacToeGame game;
	Player jugadorUno, jugadorDos;
	Connection conexionUno, conexionDos;
	
	// Lo vamos a ejecutar antes de cada test, evitamos duplicar el codigo

	@Before
	public void setUp() {
		
	//	Creamos el objeto TicTacToeGame
		
		game = new TicTacToeGame();
			
	//	Creamos los dobles de los objetos Connection
			
		conexionUno= mock(Connection.class);
		conexionDos = mock(Connection.class);
		
	//	Añadimos los dobles al objeto TicTacToeGame
			
		game.addConnection(conexionUno);
		game.addConnection(conexionDos);
			
	//	Creamos los dos jugadores (Player)
			
		jugadorUno = new Player(1, "x", "Jugador Uno");
		jugadorDos = new Player(2, "o", "Jugador Dos");
			
	// Primero añadimos al jugador uno
		
		game.addPlayer(jugadorUno);
		
	// Verificaremos que las conexiones 1 y 2 reciben el evento JOIN_GAME para el jugador uno
	
		verify(conexionUno).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(jugadorUno)));
		verify(conexionDos).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(jugadorUno)));
		
	// Borramos el registro de llamadas a los metodos del mock con reset
			
		reset(conexionUno);
		reset(conexionDos);

	// AHora añadimos al jugador 2 y comprobamos que las conexiones reciben el evento JOIN_GAME
	
		game.addPlayer(jugadorDos);
			
		verify(conexionUno).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(jugadorUno, jugadorDos)));
		verify(conexionDos).sendEvent(eq(EventType.JOIN_GAME), argThat(hasItems(jugadorUno, jugadorDos)));
		

		
		// Establecemos el primer turno al jugador uno
			
		// TURNO 1 - jugador uno
			
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));

	}
	
	@Test
	public void testJugadorUnoWinner() {
		
		// Lo realiza jugador uno
		
		game.mark(0);
		
		// TURNO 1 - jugador dos 
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		
		// Borramos registro de llamadas 
		
		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza jugador dos 
		
		game.mark(3);

		// TURNO 2 - jugador uno
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		
		// Borramos registro de llamadas 

		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza jugador uno
		
		game.mark(1);
		
		// TURNO 2 - jugador dos
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		
		// Borramos registro de llamadas 

		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza jugador dos
	
		game.mark(4);
		
		// TURNO 3 - jugador uno
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		
		// Borramos registro de llamadas
		
		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza el jugador uno ( EL QUE GANA)
		
		game.mark(2);
		
		
		
		
	//	AL final se comprueba que el juego acaba y segun las casillas marcadas uno gana y otro pierde
	
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(conexionUno).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		WinnerValue event = (WinnerValue) argument.getValue();
		
		// Comprobamos que la conexion 2 recibe el GAME_OVER
		verify(conexionDos).sendEvent(eq(EventType.GAME_OVER), eq(event));
		
		// Comprobamos quien ha ganado y quien ha perdido
		
		assertThat(event.player.equals(jugadorUno));
		assertThat(!event.player.equals(jugadorDos));
		assertNotNull(event);
		
	}
	
	@Test
	public void testJugadorDosWinner(){
	
		// Lo realiza jugador uno
		
		game.mark(0);
		
		// TURNO 1 - jugador dos 
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		
		// Borramos registro de llamadas 
		
		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza jugador dos 
		
		game.mark(3);

		// TURNO 2 - jugador uno
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		
		// Borramos registro de llamadas 

		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza jugador uno
		
		game.mark(1);
		
		// TURNO 2 - jugador dos
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		
		// Borramos registro de llamadas 

		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza jugador dos
	
		game.mark(4);
		
		// TURNO 3 - jugador uno
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		
		// Borramos registro de llamadas
		
		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza el jugador uno
		
		game.mark(6);
		
		// TURNO 3 - jugador dos
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		
		// Borramos registro de llamadas 

		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza el jugador dos (EL QUE GANA)
		
		game.mark(5);
		
		
		//	AL final se comprueba que el juego acaba y segun las casillas marcadas uno gana y otro pierde
		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(conexionUno).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		WinnerValue event = (WinnerValue) argument.getValue();
		
		// Comprobamos que la conexion 2 recibe el GAME_OVER
		
		verify(conexionDos).sendEvent(eq(EventType.GAME_OVER), eq(event));
		
		
		// Comprobamos quien ha ganado y quien ha perdido
		
		assertThat(event.player.equals(jugadorDos));
		assertThat(!event.player.equals(jugadorUno));
		assertNotNull(event);

	}
	
	@Test
	public void testJuegoEmpatado() {
		
		// Lo realiza jugador uno
		
		game.mark(0);
		
		// TURNO 1 - jugador dos 
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		
		// Borramos registro de llamadas 
		
		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza jugador dos 
		
		game.mark(3);

		// TURNO 2 - jugador uno
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		
		// Borramos registro de llamadas 

		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza jugador uno
		
		game.mark(1);
		
		// TURNO 2 - jugador dos
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		
		// Borramos registro de llamadas 

		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza jugador dos
	
		game.mark(4);
		
		// TURNO 3 - jugador uno
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		
		// Borramos registro de llamadas
		
		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza el jugador uno 
		
		game.mark(5);
		
		// TURNO 3 - jugador dos
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		
		// Borramos registro de llamadas 

		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza el jugador dos
		
		game.mark(2);
		
		// TURNO 4 - jugador uno
		
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		
		// Borramos registro de llamadas
		
		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza el jugador uno 
		
		game.mark(6);
		
		// TURNO 4 - jugador dos
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorDos));
				
		// Borramos registro de llamadas 

		reset(conexionUno);
		reset(conexionDos);
				
		// Lo realiza el jugador dos
				
		game.mark(8);
		
		// TURNO 5 - jugador uno
		
		verify(conexionUno).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		verify(conexionDos).sendEvent(eq(EventType.SET_TURN), eq(jugadorUno));
		
		// Borramos registro de llamadas
		
		reset(conexionUno);
		reset(conexionDos);
		
		// Lo realiza el jugador uno 
		
		game.mark(7);
		
		
		
		//	AL final se comprueba que el juego es un empate
		
		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(conexionUno).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		Object event = (WinnerValue) argument.getValue();
		
		// Comprobamos que la conexion 2 recibe el GAME_OVER
		
		verify(conexionDos).sendEvent(eq(EventType.GAME_OVER), eq(event));

		assertNull(event);
		
	}
	
}