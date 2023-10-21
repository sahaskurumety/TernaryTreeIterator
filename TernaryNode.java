/*package cs445.a5;



public class TernaryNode<E>{
	E data;
	@SuppressWarnings("unchecked")
	private TernaryNode <E>[]  children = (TernaryNode <E>[]) new  TernaryNode <?>[3];
	
	TernaryNode(E elem, TernaryNode<E> leftIn, TernaryNode<E> rightIn, TernaryNode<E> middleIn ){
		data = elem;
		children[0] = leftIn;
		children[1] = middleIn;
		children[2] = rightIn;
	}
		
	public int getHeight() {
		int max = 0;
    	for(int k = 0; k < 3; k++) {
        	int temp = 0;
        	if(children[k]!=null) { 
        		temp = children[k].getHeight();
        	}
        	if(temp>max) max = temp;
    	}
    	return max;
    
    public int numberOfDecendents() {
    	int sum = 0;
    	for(int k = 0; k<3;k++) {
    		if(children[k]!=null) {
    			sum += children[k].numberOfDecendents();
    		}
    	}
    	return sum+1;
		
	}	
}
*/