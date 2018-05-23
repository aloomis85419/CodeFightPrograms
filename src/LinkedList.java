/**
 * Created by aloom on 5/21/2018.
 * Simple doubly linked list.
 */
public class LinkedList<T> {
	Node head = null,tail = null;
	int size = 0;

	public LinkedList(T data){
		head = new Node(data,null,null);//initialize the linked list with a head node.
	}

	public void addAtTail(T data){
		Node temp;
		if (head.next == null){
			temp = new Node(data,head,null);
			tail = temp;
			head.next = temp;
		}
		else{
			temp = new Node(data,tail,null);
			tail.next = temp;
			tail = temp;
		}
		size++;
	}

	public void delete(T key){
		if (head == null)throw new RuntimeException("Invalid state");
		Node temp = head;
		if (head.data.equals(key)){
			head = head.next;
			return;
		}
		if (tail.data.equals(key)) {
			tail = tail.prev;
			return;
		}
		while(temp!=null && !temp.data.equals(key))temp = temp.next;
		if (temp == null)throw new RuntimeException("List does not contain the specified key");
		temp.prev.next = temp.next;
		temp.next.prev = temp.prev;
	}

	public void insertAfter(T key,T value){
		Node<T>temp = this.head;
		while(temp != null && !temp.data.equals(key))temp = temp.next;
		if(temp == tail)addAtTail(value);
		temp.next = new Node(value,temp,temp.next);
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public Node getHead(){
		return this.head;
	}

	public boolean hasNext(){
		Node temp = head;
		return temp.next != null;
	}

	public void printList(){
		Node temp = this.head;

		while(hasNext()){
			if (temp == null)break;
			System.out.println(temp.toString());
			temp = temp.next;
		}
	}


	private class Node<T>{
		Node prev = null,next = null;
		T data;
		public Node(T data,Node prev, Node next){
			this.next = next;
			this.prev = prev;
			this.data = data;
		}

		public void setNext(Node next){
			this.next = next;
		}

		public Node getNext(){
			return this.next;
		}

		public void setPrev(Node prev){
			this.prev = prev;
		}

		public Node getPrev(){
			return this.next;
		}

		public String toString(){
			return "Data: "+this.data;
		}
	}

	public static void main(String[]args){
		LinkedList<Integer>list = new LinkedList<>(10);
		list.addAtTail(5);
		list.addAtTail(8);
		list.insertAfter(5,15);
		list.printList();
		list.delete(15);
		list.printList();
	}
}
