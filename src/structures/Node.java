package structures;

import exceptions.ThereIsNoFatherException;
import exceptions.ThereIsNoGrandFatherException;
import exceptions.ThereIsNoUncleException;

public class Node {

	private int key;
	private Node father;
	private Node left;
	private Node right;
	private Color color;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Node getFather() {
		return father;
	}

	public void setFather(Node father) {
		this.father = father;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getGrandFather() throws ThereIsNoGrandFatherException,
			ThereIsNoFatherException {
		Node father = this.getFather();

		if (father != null) {
			Node grandFather = father.getFather();

			if (grandFather != null) {
				return grandFather;
			} else {
				throw new ThereIsNoGrandFatherException();
			}
		} else {
			throw new ThereIsNoFatherException();
		}

	}

	public Node getUncle() throws ThereIsNoUncleException {
		Node father = this.getFather();
		Node grandFather;

		try {
			grandFather = this.getGrandFather();
		} catch (ThereIsNoGrandFatherException e) {
			throw new ThereIsNoUncleException();
		} catch (ThereIsNoFatherException e) {
			throw new ThereIsNoUncleException();
		}

		if (grandFather.getLeft().equals(father)) {
			return grandFather.getRight();
		} else {
			return grandFather.getLeft();
		}
	}

	public boolean isSameDirectionOfUncle()
			throws ThereIsNoGrandFatherException, ThereIsNoFatherException {
		Node father = this.getFather();
		Node grandFather = this.getGrandFather();

		if (father.getLeft().equals(this)
				&& grandFather.getRight().equals(father)) {
			return true;
		} else {
			if (father.getRight().equals(this)
					&& grandFather.getLeft().equals(father)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void changeColor() {
		if (this.color.equals(Color.BLACK)) {
			this.setColor(Color.RED);
		} else {
			this.setColor(Color.BLACK);
		}
	}
}
