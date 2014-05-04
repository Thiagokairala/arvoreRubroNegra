package structures;

import exceptions.ThereIsNoFatherException;
import exceptions.ThereIsNoGrandFatherException;
import exceptions.ThereIsNoUncleException;

public class RBTree {

	private Node root;
	private static Node sentinel;

	public RBTree() {
		if (sentinel == null) {
			sentinel = generateSentinel();
		}
	}

	public static Node getSentinel() {
		return sentinel;
	}

	public static void setSentinel(Node sentinel) {
		RBTree.sentinel = sentinel;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node generateSentinel() {

		Node node = new Node();

		node.setLeft(node);
		node.setRight(node);

		node.setColor(Color.BLACK);
		node.setKey(0);

		return node;
	}

	public Node createNode(int key) {
		Node node = new Node();

		node.setColor(Color.RED);
		node.setLeft(getSentinel());
		node.setRight(getSentinel());
		node.setKey(key);

		return node;
	}

	public void addNode(int key) throws ThereIsNoUncleException,
			ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node node = createNode(key);

		if (this.getRoot() == null) {
			node.setColor(Color.BLACK);
			setRoot(node);
		} else {
			Node added = searchToAdd(this.getRoot(), node);

			verifyNode(added);
		}
	}

	public Node removeNode(Node node, int key)throws ThereIsNoUncleException,
	ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Color nodeColor = node.getColor( );
		
		if ( node == null){
			
			return null;
		}
		
		else if (node.getKey( ) > key){
			
			node.setLeft( removeNode(node.getLeft( ), key) );
		}
		else if (node.getKey( )< key){
			
			node.setRight( removeNode(node.getRight( ),key) );
		}
		else{
			
			    if (node.getLeft( ) == null && node.getRight( ) == null){
			    	
			    	node = null;
			    }
			    //only have son to right
			    else if( node.getLeft( ) == null){
			    	
			    	   	node = node.getRight( );
			    }
			    //have only son to left
			    else if (node.getRight( ) == null){
			    	
			    	  node = node.getLeft( );
			    }
			    //have two sons
			    else {
			    	
			    	Node aux = node.getLeft( );
			    	   while (aux.getRight( )!= null){
			    		   aux = aux.getRight( );
			    	   }
			    	   
			    	   node.setKey( aux.getKey( ) );
			    	   aux.setKey( key );
			    	   node.setLeft( removeNode(node.getLeft( ),key) );
			    }
		}
		
		 if (nodeColor == Color.BLACK){
			 verifyNode(node);
		 }
		
		return node;
			
		
	}
	public void verifyNode(Node added) throws ThereIsNoUncleException,
			ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Balancing balancing = wichCase(added);

		switch (balancing) {
		case CASE_ONE:
			balanceCaseOne(added);
			break;
		case CASE_TWO:
			/**
			 * nothing to do because the tree is ok.
			 */
			break;
		case CASE_THREE:
			balanceCaseThree(added);
			break;
		case CASE_FOUR:
			balanceCaseFour(added);
			break;
		case CASE_FIVE:
			balanceCaseFive(added);
		default:
			break;
		}
	}

	public void balanceCaseFive(Node added)
			throws ThereIsNoGrandFatherException, ThereIsNoFatherException,
			ThereIsNoUncleException {
		Node father = added.getFather();
		Node grandFather = added.getGrandFather();
		Node uncle = added.getUncle();

		father.setFather(grandFather.getFather());

		if (grandFather.getLeft().equals(father)) {
			grandFather.setLeft(father.getLeft());
			father.setRight(grandFather);
		} else {
			grandFather.setRight(father.getRight());
			father.setLeft(grandFather);
		}
	}

	public void balanceCaseFour(Node added)
			throws ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node father = added.getFather();
		Node grandFather = added.getGrandFather();

		if (grandFather.getLeft().equals(father)) {
			grandFather.setLeft(added);
		} else {
			grandFather.setRight(added);
		}

		added.setFather(father.getFather());

		if (father.getRight().equals(added)) {
			added.setLeft(father);
		} else {
			added.setRight(father);
		}

		father.setFather(added);
	}

	public void balanceCaseOne(Node added) {
		if (added.getColor().equals(Color.RED)) {
			added.changeColor();
		} else {
			/**
			 * nothing to do.
			 */
		}

	}

	public void balanceCaseThree(Node added) throws ThereIsNoUncleException,
			ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node father = added.getFather();
		Node uncle = added.getUncle();
		Node grandFather = added.getGrandFather();

		father.changeColor();
		uncle.changeColor();
		grandFather.changeColor();

		verifyNode(grandFather);

	}

	public Balancing wichCase(Node added) throws ThereIsNoUncleException,
			ThereIsNoGrandFatherException, ThereIsNoFatherException {
		// case that the node is the root.
		if (added.getFather() == null) {
			return Balancing.CASE_ONE;
		} else {
			// this is the case, the tree is ok, no balancing needed.
			if (added.getFather().getColor().equals(Color.BLACK)) {
				return Balancing.CASE_TWO;
			} else {
				if (added.getUncle().getColor().equals(Color.RED)) {
					return Balancing.CASE_THREE;
				} else {
					if (added.isSameDirectionOfUncle()) {
						return Balancing.CASE_FOUR;
					} else {
						return Balancing.CASE_FIVE;
					}
				}
			}
		}
	}

	public Node searchToAdd(Node root, Node nodeToAdd) {
		// in this case goes left
		if (nodeToAdd.getKey() < root.getKey()) {
			// here the node is added if the spot is right.
			if (root.getLeft().equals(getSentinel())) {
				nodeToAdd.setFather(root);
				root.setLeft(nodeToAdd);
				return root.getLeft();
			}
			// if not, continues search.
			else {
				return searchToAdd(root.getLeft(), nodeToAdd);
			}
		}
		// in this case goes to right.
		else {
			// here the node is added if the spot is right.
			if (root.getRight().equals(getSentinel())) {
				nodeToAdd.setFather(root);
				root.setRight(nodeToAdd);
				return root.getRight();
			}
			// if not, continues search.
			else {
				return searchToAdd(root.getRight(), nodeToAdd);
			}
		}
	}
}
