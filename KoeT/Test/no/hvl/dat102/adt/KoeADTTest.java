package no.hvl.dat102.adt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import no.hvl.dat102.exceptions.EmptyCollectionException;

import org.junit.jupiter.api.BeforeEach;

public abstract class KoeADTTest {

	private KoeADT<Integer> koe;
	
	private Integer e0 = 1;
	private Integer e1 = 2;
	private Integer e2 = 3;
	private Integer e3 = 4;
	private Integer e4 = 5;
	
	protected abstract KoeADT<Integer> reset();
	
	@BeforeEach
	public void setup() {
		koe = reset();
	}
	
	/*
	 * Test på at ny kø er tom
	 */
	@Test
	public void tomKoeTest() {
		assertTrue(koe.erTom());
	}
	
	/*
	 * Test på at koe ikke er tom når et element er lagt til
	 */
	@Test
	public void ikkeTomKoeTest() {
		koe.innKoe(e0);
		assertFalse(koe.erTom());
	}
	
	/*
	 * Test på at element som er lagt til er det samme som det som kommer ut
	 */
	@Test
	public void innKoeUtKoeTest() {
		koe.innKoe(e0);
		try {
			assertEquals(e0, koe.utKoe());
		} catch (EmptyCollectionException e) {
			fail("utKoe feilet uventet " + e.getMessage());
		}
	}
	
	
	/*
	 * Test på om elementene som kommer ut er samme som er lagt inn.
	 */
	@Test
	public void innKoeUtKoeMedDuplikater() {
		koe.innKoe(e0);
		koe.innKoe(e0);
		koe.innKoe(e1);
		koe.innKoe(e2);
		
		try {
			assertEquals(e0, koe.utKoe());
			assertEquals(e0, koe.utKoe());
			assertEquals(e1, koe.utKoe());
			assertEquals(e2, koe.utKoe());
		} catch (EmptyCollectionException e) {
			fail("utKoe feilet uventet " + e.getMessage());
		}
	}
	
	/*
	 * Test på om kø er tom etter innKoe og utKoe
	 */
	@Test
	public void innKoeUtKoeTom() {
		try {
			koe.innKoe(e0);
			koe.utKoe();
			assertTrue(koe.erTom());
		} catch (EmptyCollectionException e) {
			fail("utKoe feilet uventet " + e.getMessage());
		}
	}
	
	/*
	 * Test på at koe er ikke har noen elementer som kan taes ut
	 */
	@Test
	public void tomKoeException() {
		assertThrows(EmptyCollectionException.class, () -> {
			koe.utKoe();
		});
	}
	
}
