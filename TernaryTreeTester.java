package cs445.a5;

import java.util.Iterator;

public class TernaryTreeTester{
	public static void main(String[] args) {
			TernaryTree<Integer> one = new TernaryTree<Integer>();
			System.out.println("isEmpty (should be true): "+one.isEmpty());
			one.setTree(3);
			System.out.println("isEmpty (should be false): "+one.isEmpty());
			TernaryTree<Integer> two = new TernaryTree<Integer>(4);
			TernaryTree<Integer> three = new TernaryTree<Integer>(5);
			
			TernaryTree<Integer> four = new TernaryTree<Integer>(2,one,two,three);
			
			one=new TernaryTree<Integer>(7);
			two=new TernaryTree<Integer>(8);
			three=new TernaryTree<Integer>(9);
			TernaryTree<Integer> five = new TernaryTree<Integer>(6,one,two,three);
			
			
			//TernaryTree<Integer> temp = new TernaryTree<Integer>(20,new TernaryTree<Integer>(30),new TernaryTree<Integer>(40),new TernaryTree<Integer>(50));
			
			one=new TernaryTree<Integer>(11);
			two=new TernaryTree<Integer>(12);
			three=new TernaryTree<Integer>(13);
			TernaryTree<Integer> six = new TernaryTree<Integer>(10,one,two,three);
			
			TernaryTree<Integer> tree = new TernaryTree<Integer>();
			tree.setTree(1,four, five, six);

			
			
			
			System.out.println("Number of Nodes: "+tree.getNumberOfNodes());
			System.out.println("Height: "+tree.getHeight());
			//Iterator<Integer> inorder = tree.getInorderIterator();
			System.out.println("isEmpty (should be false): "+tree.isEmpty());
			System.out.println("contains (should be true): "+tree.contains(8));
			System.out.println("contains (should be false): "+tree.contains(14));
			
			System.out.println("Preorder Test:");
			Iterator<Integer> preorder = tree.getPreorderIterator();
			while(preorder.hasNext()) {
				System.out.print(preorder.next()+", ");
			}
			
			System.out.println("\nLevelorder Test:");
			Iterator<Integer> levelorder = tree.getLevelOrderIterator();
			while(levelorder.hasNext()) {
				System.out.print(levelorder.next()+", ");
			}
			System.out.println("\nPostorder Test:");
			Iterator<Integer> postorder = tree.getPostorderIterator();
			while (postorder.hasNext()) {
				System.out.print(postorder.next()+", ");
				
			}
			System.out.println("\nisBalanced (should be true): "+tree.isBalanced());
			tree.setTree(-10,six,null,null);
			System.out.println("\nisBalanced (should be false): "+tree.isBalanced());
			tree.clear();
			System.out.println("isEmpty after clearing (should be true): "+tree.isEmpty());
			System.out.println("\nisBalanced (should be true): "+tree.isBalanced());
			/*
			System.out.println("Preorder Test:");
			Iterator<Integer> preorder2 = tree.getPreorderIterator();
			while(preorder2.hasNext()) {
				System.out.print(preorder2.next()+", ");
			}*/
			
	}
}