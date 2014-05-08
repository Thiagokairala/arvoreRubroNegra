package main;

import exceptions.ThereIsNoFatherException;
import exceptions.ThereIsNoGrandFatherException;
import exceptions.ThereIsNoUncleException;
import structures.RBTree;
import util.Keyboard;

public class Main
{

	/**
	 * @param args
	 */
	public static void main( String[ ] args )
	{
		int choice;
		RBTree redBlackTree = new RBTree();

		do {
			System.out.println("\n");
			choice = menu();

			switch (choice) {

			case 1:
				System.out.printf("\tENTRE COM UM NUMERO: ");
				int number = Keyboard.getNumber();

					try
					{
						redBlackTree.addNode( number );
					} catch ( ThereIsNoUncleException | ThereIsNoGrandFatherException
							| ThereIsNoFatherException e )
					{
						e.getMessage( );
					}
				break;
			case 2:
				System.out.printf("\tENTRE COM O NÚMERO A SER REMOVIDO: ");

				int numberToRemove = Keyboard.getNumber();
				
					try
					{
						redBlackTree.removeNode( redBlackTree.getRoot( ), numberToRemove );
						
						System.out.println("\t O NÚMERO "+ numberToRemove + " FOI REMOVIDO COM SUCESSO");
						
						
					} catch ( ThereIsNoUncleException | ThereIsNoGrandFatherException
							| ThereIsNoFatherException e )
					{
						e.getMessage( );
					}

				

				
				break;
			case 3:
				System.out.println("TCHAU");
				break;
			default:
				System.out.println("POR FAVOR ENTRE COM UM NUMERO VALIDO");
				break;
			}

		} while (choice != 3);
	}

	private static int menu() {
		int number;

		System.out.println("\tMENU");
		System.out.println("\t1-ADICIONAR ELEMENTO");
		System.out.println("\t2-REMOVER ELEMENTO");
		System.out.println("\t3-SAIR");

		number = Keyboard.getNumber();

		return number;
	}


	}


