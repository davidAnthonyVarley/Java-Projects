package csu22011_a2;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  David Varley
 *  @version 9/10/23
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{
	
	int listLength;

    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
      listLength=0;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     */
    public boolean isEmpty()
    {
      if (this.head==null && this.tail==null && this.listLength==0) {
    	  return true;
      }
      
      //else
      return false;
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     */
    
    	
    //}

    

    
    public void insertBefore( int pos, T data ) {
    	DLLNode node = new DLLNode(data, null, null);
    	//DLLNode(T theData, DLLNode prevNode, DLLNode nextNode)
    	
    	//ie if 0 or a neg num
    	if (pos<1) {
    		insertFirst(data);
    	}
    	//if its in bounds
    	
    	else if (0 < pos && pos <listLength-1) {
    		DLLNode pointer = pointTo(pos) ;

        	//insert before current pointer      prev          next
        	DLLNode newnode = new DLLNode(data, pointer.prev, pointer);
        	
        	//set pointers of nodes after and before
        	pointer.prev = newnode;
        	newnode.prev.next = newnode;
        	
        	listLength++;
    	} //if larger than list
    	
    	else if (pos>=listLength) {
    		insertLast( data);
    	}
 
    		
    }
    
    public void insertLast(T data ) {
    	
    	//point old head to new head
    	
    	if (listLength==0) {
    		head= new DLLNode(data, null, null);
    		tail=head;
    		
    	}
    	else {
    		DLLNode node = new DLLNode(data, tail, null);
    		tail.next = node;
    	
    	//make new node the head
    		tail=node;
    	}
    	
    	listLength++;
    }
    
    public void insertFirst(T data ) {
    	//DLLNode node = new DLLNode(data, null, head);
    	//point old head to new head
    	
    	if (listLength==0) {
    		head= new DLLNode(data, null, null);
    		tail=head;
    	
    	}
    	else {
    		
    		DLLNode node = new DLLNode(data, null, head);
    		head.prev=node;
    	
    	//make new node the head
    		head=node;
    	}
    	
    	listLength++;
    	
    	
    	
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     *
     */
    public T get(int pos) 
    {	
    	if (pos  < -1 && (listLength-1) < pos) {
      		return null;
      	  }
    	DLLNode node = pointTo(pos);

    	return node.data;
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     */
    public DLLNode pointTo(int pos) {

    	if ( pos < 1) {
    		return head;
    	}
    	else if (pos >= (listLength)) {
       		return tail;
    	}
    	//else
    	DLLNode node = head;
    	for (int i=0;i<pos;i++ ) {
    		node=node.next;
    	}
    	return node;

    	
    }
    
    public boolean deleteAt(int pos) 
    {
      DLLNode node;
      if ( (pos  < 0) || (listLength-1) < pos) {
  		return false;
  	  }
      else {
    	  node = pointTo(pos);
    	  delete(node);
          listLength--;
      }
      
      
      
      
      return true;
    }
    
    public void delete(DLLNode node) 
    {
      
      //get the node's previous and next nodes in list
      DLLNode previousnode = node.prev;
      DLLNode nextnode = node.next;
      
      //set NEXT pointer of deleted node's previous node, to next node of delelted node, and vice versa
      if (previousnode!=null) {
    	  previousnode.next = nextnode;
      }
      
      if (nextnode!=null) {
    	  nextnode.prev = previousnode;
      }
      
      //clear heap memory
//      node.next=null;
//      node.prev=null;
//      node.data=null;
      
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     */
    public void reverse()
    {
    	//DLLNode start_at_tail = tail;
    	
    	//by end, start at tail will be address of new head, and vice versa
    	
    	//big theta(N)
		DLLNode start_at_head = head;
		
		DLLNode oldhead = head;
		DLLNode oldtail = tail;
		
		
    	for (int i=0;i<listLength;i++ ) {
//    		DLLNode tempend = start_at_tail;
    		
    		//switch next and prev
    		DLLNode newprev = start_at_head.next;
    		DLLNode newnext = start_at_head.prev ;
    		
    		start_at_head.next = newnext;
    		start_at_head.prev = newprev;
    		
    		//bc next and prev were switched, move to prev
    		start_at_head= start_at_head.prev;

    	}
    	
    	head = oldtail;
    	tail=  oldhead;
    	
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     */
     public void makeUnique()
    {
    	 //not including constants in asypmtotic notation
    	 
    	 //worst case, O(N^2/2)
    	 //best case,  big alpha(N^2/2)
      
      //but if the next pointer is null, we know it to be the tail anyway
      for ( DLLNode currentnode = head; currentnode!=null; currentnode=currentnode.next) { //1
    	  
    	  
    	  for ( DLLNode j=currentnode; j!=null;) {  //2
    		  
    		  
    		  //System.out.println( "if ("+currentnode.data+"=="+j.data);
    		  if (currentnode.data.compareTo(j.data)==0 && currentnode!=j) {  //3.1
    			  DLLNode temp =j;
    			  j = j.next;
    			  delete(temp);
    		  } //3.1
    		  else { //3.2
    			  j = j.next;
    		  } //3.2
    			  
    		  
    			  
    	  } //2
    	  
  	} //1
      
    } //0




    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  The code consists of the lines before the for-loop, the for-loop itself and the lines after the for-loop.
     *
     *  The lines before the for loop involve 
     *  - the creation of a StringBuilder object which does not depend on the length of the list. Therefore it takes Theta(1) time.
     *  - the allocation and assignment of two variables which are constant time operations.
     *  Thus the code before the for-loop will take Theta(1) time to run.
     *
     *  The lines after the for-loop involve a single return instruction and thus take Theta(1) time.
     *
     *  The for-loop itself will iterate over all elements of the DLL. Thus it will perform Theta(N) iterations.
     *  Each iteration will involve:
     *   * The if-conditional will run:
     *       - the if-then-else conditions and branching, which are constant time operations. Thus these cost Theta(1) time.
     *       - The then-branch of the conditional calls StringBuilder's append() method, which (from the Java documentation) we know it runs in Theta(1) time.
     *       - the else-branch of the conditional involves a single assignment, thus runs in Theta(1) time.
     *     Therefore the if-then-else conditions will cost Theta(1) in the worst case.
     *   * The final call to StringBuilder's append will cost again Theta(1)
     *   * the nested call to toString() will cost time proportional to the length of the strings (but not the length of the list). 
     *     Assuming strings are relatively small compared to the size of the list, this cost will be Theta(1).
     *  Therefore each iteration of the loop will cost Theta(1).
     *  Thus the lines involving the for-loop will run in Theta(N)*Theta(1) = Theta(N).
     *
     * Therefore this method will run in Theta(1) + Theta(1) + Theta(N) = Theta(N) time in the worst case.
     *
     */
     
     
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}



