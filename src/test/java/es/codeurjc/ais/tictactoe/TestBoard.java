package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;


import org.junit.Test;

public class TestBoard{
		
	// Crear tablero 
	
	 Board tablero = new Board();
		
	// Creamos jugadores
	 
	Player jugadorUno = new Player(1, "x", "Jugador Uno");
	Player jugadorDos = new Player(2, "o", "Jugador Dos");
		

	@Test
	public void testJugadorUnoWinner() {
		
		//Simulamos una partida
		
		// Posicion ganadora esperada
		
		int[] posicion = {0, 1, 2};
		
		// {0, 1, 2} es una posicion ganadora		
		
		//Jugador Uno -> Una X en la posicion 0
		
		tablero.getCell(0).value = jugadorUno.getLabel();
		
		//Jugador Dos -> Un O en la posicion 3
		
		tablero.getCell(3).value = jugadorDos.getLabel();
		
		//Jugador Uno -> Un X en la posicion 1
		
		tablero.getCell(1).value = jugadorUno.getLabel();
		
		//Jugador Dos -> Un O en la posicion
		
		tablero.getCell(4).value = jugadorDos.getLabel();
		
		//Jugador Uno -> Un X en la posicion 2
		
		tablero.getCell(2).value = jugadorUno.getLabel();
		
		
		// Comprobamos que la posicion ganadora esperada es igual a la obtenida
		
		assertArrayEquals(posicion, tablero.getCellsIfWinner(jugadorUno.getLabel()));
		
		//Comprobamos si el jugador dos no tiene la posicion ganadora
		
		assertNull(tablero.getCellsIfWinner(jugadorDos.getLabel()));
		
		//Comprobamos que la partida no es un empate
		
		assertEquals(false, tablero.checkDraw());		
	}
	
	@Test
	public void testJugadorDosWinner() {
		
		//Simulamos una partida
		
		// Posicion ganadora esperada
		
		int[] posicion = {3, 4, 5};
		
		//Jugador Uno -> Una X en la posicion 0
		
		tablero.getCell(0).value = jugadorUno.getLabel();
		
		//Jugador Dos -> Un O en la posicion 3
		
		tablero.getCell(3).value = jugadorDos.getLabel();
		
		//Jugador Uno -> Un X en la posicion 1
		
		tablero.getCell(1).value = jugadorUno.getLabel();
		
		//Jugador Dos -> Un O en la posicion 4
		
		tablero.getCell(4).value = jugadorDos.getLabel();
		
		//Jugador Uno -> Un X en la posicion 6
		
		tablero.getCell(6).value = jugadorUno.getLabel();
		
		//Jugador Dos -> Un O en la posicion 5
		
		tablero.getCell(5).value = jugadorDos.getLabel();	
		
		
		// Comprobamos que la posicion ganadora esperada es igual a la obtenida
		
		assertArrayEquals(posicion, tablero.getCellsIfWinner(jugadorDos.getLabel()));
		
		//Comprobamos si el jugador uno no tiene la posicion ganadora
		
		assertNull(tablero.getCellsIfWinner(jugadorUno.getLabel()));
		
		//Comprobamos que la partida no es un empate
		
		assertEquals(false, tablero.checkDraw());	
		
	}
	
	@Test 
	public void testJuegoEmpatado() {
		
		
		//Simulamos una partida
		
		//Jugador Uno -> Una X en la posicion 0
		
		tablero.getCell(0).value = jugadorUno.getLabel();
		
		//Jugador Dos -> Un O en la posicion 3
		
		tablero.getCell(3).value = jugadorDos.getLabel();
		
		//Jugador Uno -> Un X en la posicion 1
		
		tablero.getCell(1).value = jugadorUno.getLabel();
		
		//Jugador Dos -> Un O en la posicion 4
		
		tablero.getCell(4).value = jugadorDos.getLabel();
		
		//Jugador Uno -> Un X en la posicion 5
		
		tablero.getCell(5).value = jugadorUno.getLabel();
		
		//Jugador Dos -> Un O en la posicion 2
		
		tablero.getCell(2).value = jugadorDos.getLabel();
				
		// Jugador Uno -> Un X en la posicion 6
		
		tablero.getCell(6).value = jugadorUno.getLabel();



		//Comprobamos que la partida  es un empate
		
		assertTrue(tablero.checkDraw());	
		
	}
}