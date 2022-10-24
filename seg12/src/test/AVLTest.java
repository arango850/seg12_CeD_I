package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import model.AVL;
import model.Node;

class AVLTest {

	public void setupScenary1() {

	}

	@Test
	void testAdd() {
		setupScenary1();

		Node<Integer> c = new Node<>(1);
		Node<Integer> b = new Node<>(2);
		Node<Integer> a = new Node<>(45);
		Node<Integer> d = new Node<>(10);
		Node<Integer> b1 = new Node<>(32);
		AVL<Integer> avl = new AVL<>(new Comparator<Integer>() {
			public int compare(Integer p1, Integer p2) {
				return p1.compareTo(p2);
			}
		});

		assertTrue(true);

	}

}
