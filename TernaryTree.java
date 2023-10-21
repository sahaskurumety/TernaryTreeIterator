package cs445.a5;

import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

public class TernaryTree<E> implements TernaryTreeBonus<E>{
	private E root;
	@SuppressWarnings("unchecked")
	private TernaryTree <E>[]  children = (TernaryTree <E>[]) new  TernaryTree <?>[3];
	public TernaryTree() {
		root = null;
	}
	
	public TernaryTree(E data) {
		root = data;
	}
	
	public TernaryTree(E data, TernaryTree<E> left, TernaryTree<E> middle, TernaryTree<E> right) {
		root = data;
		children[0] = left;
		children[1] = middle;
		children[2] = right;
	}
	
    public E getRootData() {
    	if(root == null) throw new EmptyTreeException();
    	return root;
    }
    private TernaryTree<E>[] getChildren() {
    	return children;
    }

    public int getHeight() {
    	int max = 0;
    	for(int k = 0; k < 3; k++) {
        	int temp = 0;
        	if(children[k]==null) { 
        		temp = 0;
        	}else {
        		temp = children[k].getHeight();
        	}
        	if(temp>max) max = temp;
    	}
    	return max+1;
    }

    public int getNumberOfNodes() {
    	int sum = 0;
    	for(int k = 0; k<3;k++) {
    		if(children[k]!=null) {
    			sum += children[k].getNumberOfNodes();
    		}
    	}
    	return sum+1;
    	
    }

    public boolean isEmpty() {
    	if(root!=null) return false;
    	for(int k=0;k<3;k++) {
    		if(children[k]!=null) return false;
    	}
    	return true;
    	
    }

	@SuppressWarnings("unchecked")
    public void clear() {
    	root = null;
    	children = (TernaryTree <E>[]) new  TernaryTree<?>[3];
    }
	@SuppressWarnings("unchecked")
	public void setTree(E rootData) {
		root = rootData;
		children = (TernaryTree <E>[]) new  TernaryTree <?>[3];
	}
	
    public void setTree(E rootData, TernaryTreeInterface<E> leftTree,
    								TernaryTreeInterface<E> middleTree,
    								TernaryTreeInterface<E> rightTree) {
    	root = rootData;
    	children[0] = (TernaryTree<E>)leftTree;
    	children[1] = (TernaryTree<E>)middleTree;
    	children[2] = (TernaryTree<E>)rightTree;
    	
    }
    public E remove() {
    	throw new java.lang.UnsupportedOperationException();
    }
    
    public E remove(E toRemove) {
    	throw new java.lang.UnsupportedOperationException();
    }
    


    //----------------------BONUS------------------------
    
    /**
     * Determines whether the tree contains the given entry. Equality is
     * determined by using the .equals() method.
     * @param elem The entry to be searched for
     * @return true if elem is in the tree, false if not
     */
    public boolean contains(E elem) {
    	
    	if(elem.equals(root)) return true;
    	for(int k = 0; k<3;k++) {
    		if(children[k]!=null && children[k].contains(elem)) {
    			return true;
    		}
    	}
    	return false;
    	
    }
    
    public boolean isBalanced() {
    	if(children[0]==null&&children[1]==null&&children[2]==null) return true;
    	int leftHeight = 0;
    			if(children[0]!=null) leftHeight =children[0].getHeight();
    	int midHeight = 0;
    			if(children[1]!=null) midHeight =children[1].getHeight();
    	int rightHeight = 0;
    			if(children[2]!=null) rightHeight = children[2].getHeight();
    			
    	if(rightHeight > midHeight + 1 || rightHeight<midHeight-1) return false;
    	if(leftHeight > midHeight + 1 || leftHeight<midHeight-1) return false;
    	boolean isBalancedC0 = (children[0]==null||children[0].isBalanced());
    	boolean isBalancedC1 = (children[1]==null||children[1].isBalanced());
    	boolean isBalancedC2 = (children[2]==null||children[2].isBalanced());
    	return(isBalancedC0&&isBalancedC1&&isBalancedC2);
    	
    }
//---------------------PRIVATE METHODS---------------
    
    private boolean isParentOf(TernaryTree<E> child) {
    	for(int k = 0; k<3; k++) {
    		if(children[k]!=null && children[k]==child) {
    			return true;
    		}
    	}
    	return false;
    	
    }

//--------------------ITERATOR_CLASSES---------------

/** Creates an iterator to traverse the tree in preorder fashion
 *  @return  the iterator */
 public Iterator<E> getPreorderIterator(){
	 return new PreorderIterator<E>(this);
 }

/** Creates an iterator to traverse the tree in postorder fashion
 *  @return  the iterator */
 public Iterator<E> getPostorderIterator(){
	 return new PostorderIterator<E>(this);
 }

/** Not supported because in-order traversal would only work for binary trees
 * The order would be ambiguous, as the root could be returned after the left child or after the middle child. */
 public Iterator<E> getInorderIterator(){
	 throw new java.lang.UnsupportedOperationException();
 }

/** Creates an iterator to traverse the tree in level-order fashion (breadth first traversal)
 *  @return  the iterator */
 public Iterator<E> getLevelOrderIterator(){
	 return new LevelorderIterator<E>(this);
 }
 
 
 
 
 
 private class PreorderIterator<E> implements Iterator<E>{
	 private StackInterface<TernaryTree<E>> nodeStack;
	 private TernaryTree<E> curNode;
	 
	 private PreorderIterator(TernaryTree<E> tree) {
		 nodeStack = new LinkedStack<TernaryTree<E>>();
		 curNode = tree;
		 if(curNode!=null) {
			 nodeStack.push(curNode);
		 }
		 
	 }
	 
	 public boolean hasNext() {
		 
		 return !nodeStack.isEmpty();
	 }
	
	 public E next() {
		 curNode = nodeStack.pop();
		 TernaryTree<E>[] children = curNode.getChildren();
		 for(int k = 2; k>=0; k--) {
			 if(children[k]!=null) {
				 nodeStack.push(children[k]);
			 }
			 
		 }
		 return curNode.getRootData();
		 
	 }
 }
	 
	 private class PostorderIterator<E> implements Iterator<E>{
    	 private StackInterface<TernaryTree<E>> nodeStack;
    	 private TernaryTree<E> curNode;
    	 private TernaryTree<E> justPopped;
    	 
    	 private PostorderIterator(TernaryTree<E> tree) {
    		 nodeStack = new LinkedStack<TernaryTree<E>>();
    		 curNode = tree;
    		 if(curNode!=null) {
    			 nodeStack.push(curNode);
    		 }
    		 
    	 }
    	 
    	 public boolean hasNext() {
    		 return !nodeStack.isEmpty();
    	 }
    	 
    	 public E next() {
    		 curNode = nodeStack.peek();
    		 if(justPopped!=null&&curNode.isParentOf(justPopped)) {
    			 justPopped=curNode;
    			 return nodeStack.pop().getRootData();
    		 }else{
    			 
    			 boolean hasChildren = true;
    			 while(hasChildren) {
    				 hasChildren = false;
    				 for(int k = 2; k>=0; k--){
    					 TernaryTree<E>[] childArray = curNode.getChildren();
    					 if(childArray[k]!=null) {
    						 nodeStack.push(childArray[k]);
    						 hasChildren = true;
    					 } 		
    		 		//System.out.println(curNode.getRootData());
    				 }
    				 curNode = nodeStack.peek();
    		 	
       		 
    		 }
    		 justPopped = curNode;
    		 return nodeStack.pop().getRootData();
    		 }
    	 }
	 }
	 
	 
	 
	 
	 private class LevelorderIterator<E> implements Iterator<E>{
    	 private LinkedQueue<TernaryTree<E>> nodeQueue;
    	 private TernaryTree<E> curNode;
    	 
    	 private LevelorderIterator(TernaryTree<E> tree) {
    		 nodeQueue = new LinkedQueue<TernaryTree<E>>();
    		 curNode = tree;
    		 if(curNode!=null) {
    			 nodeQueue.enqueue(curNode);
    		 }
    		 
    	 }
    	 
    	 public boolean hasNext() {
    		 return !nodeQueue.isEmpty();
    	 }
    	 
    	 @SuppressWarnings("unchecked")
    	 public E next() {
    		 curNode = nodeQueue.dequeue();
    		 TernaryTree<E>[] children = curNode.getChildren();
    		 for(int k = 0; k<=2; k++) {
    			 if(children[k]!=null) {
    				 nodeQueue.enqueue(children[k]);
    			 }
    			 
    		 }
    		 return curNode.getRootData();
    		 
    	 }

	 }
}




 
 
 

