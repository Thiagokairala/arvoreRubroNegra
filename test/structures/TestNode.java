package structures;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.ThereIsNoFatherException;
import exceptions.ThereIsNoGrandFatherException;
import exceptions.ThereIsNoUncleException;

public class TestNode {

	@Test
	public void testGetGrandFather() throws ThereIsNoGrandFatherException,
			ThereIsNoFatherException {
		Node root = new Node();
		Node firstLeft = new Node();
		Node secondLeft = new Node();

		root.setFather(null);
		firstLeft.setFather(root);
		secondLeft.setFather(firstLeft);

		root.setLeft(firstLeft);
		firstLeft.setLeft(secondLeft);

		Node grandfather = secondLeft.getGrandFather();

		assertTrue(grandfather.equals(root));
	}

	@SuppressWarnings("unused")
	@Test(expected = ThereIsNoFatherException.class)
	public void testGetGrandFatherNoFather()
			throws ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node root = new Node();

		root.setFather(null);

		Node grandfather = root.getGrandFather();
	}

	@SuppressWarnings("unused")
	@Test(expected = ThereIsNoGrandFatherException.class)
	public void testGetGrandFatherNoGrandFather()
			throws ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node root = new Node();
		Node firstLeft = new Node();

		root.setFather(null);
		firstLeft.setFather(root);

		root.setLeft(firstLeft);

		Node grandfather = firstLeft.getGrandFather();
	}

	@Test
	public void testGetUncle() throws ThereIsNoUncleException {
		Node root = new Node();
		Node firstLeft = new Node();
		Node secondLeft = new Node();
		Node firstRight = new Node();

		root.setFather(null);
		firstLeft.setFather(root);
		firstRight.setFather(root);
		secondLeft.setFather(firstLeft);

		root.setLeft(firstLeft);
		root.setRight(firstRight);
		firstLeft.setLeft(secondLeft);

		Node uncle = secondLeft.getUncle();
		assertTrue(uncle.equals(firstRight));
	}

	@SuppressWarnings("unused")
	@Test(expected = ThereIsNoUncleException.class)
	public void testGetUncleThereIsNoUncle() throws ThereIsNoUncleException {
		Node root = new Node();
		Node firstLeft = new Node();
		Node firstRight = new Node();

		root.setFather(null);
		firstLeft.setFather(root);
		firstRight.setFather(root);

		root.setLeft(firstLeft);
		root.setRight(firstRight);

		Node uncle = firstLeft.getUncle();

	}

	@Test
	public void testIsSameDirectionOfUncle()
			throws ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node root = new Node();
		Node father = new Node();
		Node uncle = new Node();
		Node leftSon = new Node();
		Node rightSon = new Node();
		Node leftCousion = new Node();
		Node rightCousin = new Node();

		root.setLeft(father);
		root.setRight(uncle);
		father.setFather(root);
		uncle.setFather(root);

		father.setLeft(leftSon);
		father.setRight(rightSon);
		rightSon.setFather(father);
		leftSon.setFather(father);

		uncle.setLeft(leftCousion);
		uncle.setRight(rightCousin);
		leftCousion.setFather(uncle);
		rightCousin.setFather(uncle);

		assertTrue(leftSon.isSameDirectionOfUncle() == false);
		assertTrue(rightSon.isSameDirectionOfUncle());
		
		assertTrue(leftCousion.isSameDirectionOfUncle());
		assertTrue(rightCousin.isSameDirectionOfUncle() == false);

	}
}
