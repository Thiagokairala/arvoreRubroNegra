package structures;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.ThereIsNoFatherException;
import exceptions.ThereIsNoGrandFatherException;
import exceptions.ThereIsNoUncleException;

public class TestRBTree {

	@SuppressWarnings("unused")
	@Test
	public void testGenerateSentinel() {
		RBTree rbTree = new RBTree();

		Node node = RBTree.getSentinel();

		RBTree seccondTree = new RBTree();

		assertTrue(RBTree.getSentinel() != null);
		assertTrue(RBTree.getSentinel().equals(node));
	}

	@Test
	public void testCreateNode() {
		RBTree rbtree = new RBTree();

		Node node = rbtree.createNode(10);

		assertTrue(node.getLeft().equals(RBTree.getSentinel()));
		assertTrue(node.getRight().equals(RBTree.getSentinel()));
		assertTrue(node.getColor().equals(Color.RED));
		assertTrue(node.getKey() == 10);
	}

	
	@Test
	public void testRemoveNode() throws ThereIsNoUncleException, ThereIsNoGrandFatherException, ThereIsNoFatherException {
		RBTree rbtree = new RBTree();
		rbtree.addNode( 10 );
		rbtree.addNode( 20 );
		
		assertNotNull( rbtree.removeNode( rbtree.getRoot( ), 20 ) );
		
		
	}
	@Test
	public void testSearchToAddLeft() {
		Node root = new Node();
		root.setKey(10);
		root.setRight(RBTree.getSentinel());
		root.setLeft(RBTree.getSentinel());

		RBTree rbTree = new RBTree();

		Node firstleft = new Node();
		firstleft.setKey(5);
		firstleft.setRight(RBTree.getSentinel());
		firstleft.setLeft(RBTree.getSentinel());

		root.setLeft(firstleft);

		Node add = new Node();
		add.setKey(3);

		Node test = rbTree.searchToAdd(root, add);

		assertTrue(test.equals(add));
		assertTrue(test.getFather().equals(firstleft));
	}

	@Test
	public void testSearchToAddRight() {
		Node root = new Node();
		root.setKey(10);
		root.setRight(RBTree.getSentinel());
		root.setLeft(RBTree.getSentinel());

		RBTree rbTree = new RBTree();

		Node firstright = new Node();
		firstright.setKey(15);
		firstright.setRight(RBTree.getSentinel());
		firstright.setLeft(RBTree.getSentinel());

		root.setRight(firstright);

		Node add = new Node();
		add.setKey(30);

		Node test = rbTree.searchToAdd(root, add);

		assertTrue(test.equals(add));
		assertTrue(test.getFather().equals(firstright));
	}

	@Test
	public void testWichCaseCase1() throws ThereIsNoUncleException,
			ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node root = new Node();
		root.setFather(null);

		RBTree rbTree = new RBTree();

		assertTrue(rbTree.wichCase(root).equals(Balancing.CASE_ONE));

	}

	@Test
	public void testWichCaseCase2() throws ThereIsNoUncleException,
			ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node root = new Node();
		Node son = new Node();

		root.setFather(null);
		root.setColor(Color.BLACK);

		son.setFather(root);

		RBTree rbTree = new RBTree();

		assertTrue(rbTree.wichCase(son).equals(Balancing.CASE_TWO));

	}

	@Test
	public void testWichCaseCase3() throws ThereIsNoUncleException,
			ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node root = new Node();
		Node father = new Node();
		Node uncle = new Node();
		Node son = new Node();

		father.setFather(root);
		uncle.setFather(root);
		root.setLeft(father);
		root.setRight(uncle);

		root.setFather(null);
		root.setColor(Color.BLACK);

		father.setColor(Color.RED);
		uncle.setColor(Color.RED);

		son.setFather(father);
		father.setLeft(son);

		RBTree rbTree = new RBTree();

		assertTrue(rbTree.wichCase(son).equals(Balancing.CASE_THREE));

	}

	@Test
	public void testWichCaseCase4() throws ThereIsNoUncleException,
			ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node root = new Node();
		Node father = new Node();
		Node uncle = new Node();
		Node rightSon = new Node();
		Node leftSon = new Node();

		father.setFather(root);
		uncle.setFather(root);
		root.setLeft(father);
		root.setRight(uncle);

		root.setFather(null);
		root.setColor(Color.BLACK);

		father.setColor(Color.RED);
		uncle.setColor(Color.BLACK);

		rightSon.setFather(father);
		father.setRight(rightSon);
		leftSon.setFather(father);
		father.setLeft(leftSon);

		RBTree rbTree = new RBTree();

		assertTrue(rbTree.wichCase(rightSon).equals(Balancing.CASE_FOUR));

	}

	@Test
	public void testWichCaseCase5() throws ThereIsNoUncleException,
			ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node root = new Node();
		Node father = new Node();
		Node uncle = new Node();
		Node rightSon = new Node();
		Node leftSon = new Node();

		father.setFather(root);
		uncle.setFather(root);
		root.setLeft(father);
		root.setRight(uncle);

		root.setFather(null);
		root.setColor(Color.BLACK);

		father.setColor(Color.RED);
		uncle.setColor(Color.BLACK);

		rightSon.setFather(father);
		father.setRight(rightSon);
		leftSon.setFather(father);
		father.setLeft(leftSon);

		RBTree rbTree = new RBTree();

		assertTrue(rbTree.wichCase(leftSon).equals(Balancing.CASE_FIVE));
	}

	@Test
	public void testVerifyNodeCase1NodeRed() throws ThereIsNoUncleException,
			ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node root = new Node();
		root.setFather(null);
		root.setColor(Color.RED);

		RBTree rbtree = new RBTree();

		rbtree.verifyNode(root);

		assertTrue(root.getColor().equals(Color.BLACK));
	}

	@Test
	public void testVerifyNodeCase2NodeBlack() throws ThereIsNoUncleException,
			ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node root = new Node();
		root.setFather(null);
		root.setColor(Color.BLACK);

		RBTree rbtree = new RBTree();

		rbtree.verifyNode(root);

		assertTrue(root.getColor().equals(Color.BLACK));
	}

	@Test
	public void testVerifyNodeCase3() throws ThereIsNoUncleException,
			ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node root = new Node();
		Node father = new Node();
		Node uncle = new Node();
		Node son = new Node();

		root.setFather(null);
		root.setColor(Color.BLACK);

		father.setColor(Color.RED);
		uncle.setColor(Color.RED);

		root.setLeft(father);
		root.setRight(uncle);
		father.setFather(root);
		uncle.setFather(root);

		father.setLeft(son);
		son.setFather(father);

		RBTree rbtree = new RBTree();

		rbtree.verifyNode(son);

		assertTrue(root.getColor().equals(Color.BLACK));
		assertTrue(father.getColor().equals(Color.BLACK));
		assertTrue(uncle.getColor().equals(Color.BLACK));
	}
	
	public void testVerifyNodeCase4() {
		
	}
}
