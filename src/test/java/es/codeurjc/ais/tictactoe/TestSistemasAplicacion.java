package es.codeurjc.ais.tictactoe;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class TestSistemasAplicacion{

	// Creamos el objeto WebDriver especifico para el navegador
	// que queramos utilizar
	
	protected WebDriver driver1, driver2;
	
	@BeforeClass
	public static void setupClass() {
		ChromeDriverManager.getInstance().setup();
		WebApp.start();
	}
	
	@AfterClass
	public static void teardownClass() {
		WebApp.stop();
	}
	
	//LO ejecutamos antes de cada test
	@Before
	public void setup() {
		driver1 = new ChromeDriver();
		driver2 = new ChromeDriver();
	}

	// Al finalizar el test se cierra el browser con el metodo quit()
	@After
	public void teardown() {
		if(driver1 != null) {
			driver1.quit();
		}
		if(driver2 != null) {
			driver2.quit();
		}
	}
	
	// Localizamos los elementos en la pagina e interactuamos con ellos (click)
	
	@Test
	public void testJugadorUnoWinner() throws InterruptedException{
		
		driver1.get("http://localhost:8080");
		driver2.get("http://localhost:8080");
		
		driver1.findElement(By.id("nickname")).sendKeys("Jugador Uno");
		driver1.findElement(By.id("nickname")).sendKeys("Jugador Dos");

		driver2.findElement(By.id("startBtn")).click();
		driver2.findElement(By.id("startBtn")).click();
		
		
		driver1.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-3")).click();
		driver1.findElement(By.id("cell-1")).click();
		driver2.findElement(By.id("cell-4")).click();
		driver1.findElement(By.id("cell-2")).click();

		assertEquals(driver1.switchTo().alert().getText(), "Jugador Uno wins! Jugador Dos looses.");

	}
	
	@Test
	public void testJugadorDosWinner() throws InterruptedException{
		
		driver1.get("http://localhost:8080");
		driver2.get("http://localhost:8080");
		
		driver1.findElement(By.id("nickname")).sendKeys("Jugador Uno");
		driver1.findElement(By.id("startBtn")).click();
		
		driver2.findElement(By.id("nickname")).sendKeys("Jugador Dos");
		driver2.findElement(By.id("startBtn")).click();
		
		
		driver1.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-3")).click();
		driver1.findElement(By.id("cell-1")).click();
		driver2.findElement(By.id("cell-4")).click();
		driver1.findElement(By.id("cell-6")).click();
		driver2.findElement(By.id("cell-5")).click();

		assertEquals(driver2.switchTo().alert().getText(), "Jugador Dos wins! Jugador Uno looses.");

	}
	
	@Test
	public void testPartidaEmpatada() throws InterruptedException{
		
		driver1.get("http://localhost:8080");
		driver2.get("http://localhost:8080");
		
		driver1.findElement(By.id("nickname")).sendKeys("Jugador Uno");
		driver1.findElement(By.id("startBtn")).click();
		
		driver2.findElement(By.id("nickname")).sendKeys("Jugador Dos");
		driver2.findElement(By.id("startBtn")).click();
		
		
		driver1.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-3")).click();
		driver1.findElement(By.id("cell-1")).click();
		driver2.findElement(By.id("cell-4")).click();
		driver1.findElement(By.id("cell-5")).click();
		driver2.findElement(By.id("cell-2")).click();
		driver1.findElement(By.id("cell-6")).click();
		driver2.findElement(By.id("cell-8")).click();
		driver1.findElement(By.id("cell-7")).click();
		

		assertEquals(driver1.switchTo().alert().getText(), "Draw");
		assertEquals(driver2.switchTo().alert().getText(), "Draw!");


	}
	
	
	
}