package assignment1;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DesignedList<E> extends AbstractList<E>{
	private E[] data;
	private int count;
	private static int INITIALCAPACITY = 16;
	
	@SuppressWarnings("unchecked")
	//Constructor
	public DesignedList(){
		data = (E[])new Object[INITIALCAPACITY];
	}
	
	@Override
	//return the size of list
	public int size() {
		return count;
	}

	//Test whether the list is empty
	public boolean isEmpty(){
		return count==0;
	}
	
	//Get the element by the index
	@Override
	public E get(int index) {
		if(index<0||index>=count)
		throw new IndexOutOfBoundsException();
		return data[index];
	}
	
	//Set the value of element by the index
	public E set(int index, E value){
		if(index<0||index>=count)
			throw new IndexOutOfBoundsException();
		E ans = data[index];
		data[index] = value;
		return ans;
	}
	
	//Remove one element
	public E remove(int index){
		if(index<0||index>=count)
			throw new IndexOutOfBoundsException();
		E ans = data[index];
		for(int i=index+1;i<count;i++)
			data[i-1] = data[i];
		count--;
		data[count] = null;
		return ans;
	}
	
	//Add an item in the list
	public void add(int index, E item){
		if(index<0||index>count)
			throw new IndexOutOfBoundsException();
		ensureCapacity();
		for(int i=count;i>index;i--)
			data[i] = data[i-1];
		data[index] = item;
		count++;
	}

	//Ensure the list has enough space
	@SuppressWarnings("unchecked")
	public void ensureCapacity(){
		if(count<data.length)
			return;
		E[] newArray = (E[])(new Object[data.length+INITIALCAPACITY]);
		for(int i=0;i<count;i++)
			newArray[i] = data[i];
		data = newArray;
	}
	
	public Iterator<E> iterator(){
		return new DesignedListIterator<E>(this);
	}
	
	//Internal Iterator class
	@SuppressWarnings("hiding")
	private class DesignedListIterator<E> implements Iterator<E>{
		private DesignedList<E> list;
		private int nextIndex = 0;
		private boolean canRemove = false;
		
		private DesignedListIterator(DesignedList<E> list){
			this.list = list;
		}
		
		@Override
		public boolean hasNext() {
			return (nextIndex<list.count);
		}

		@Override
		public E next() {
			if(nextIndex>=list.count) throw new NoSuchElementException();
			canRemove = true;
			return list.get(nextIndex++);
		}
		
		public void remove(){
			if(!canRemove) throw new IllegalStateException();
			canRemove = false;
			nextIndex--;
			list.remove(nextIndex);
		}
	}
	
	//Return the occurrence times of one element in list
	public int elementOccurrenceTimes(Object item){
		int elementOccurenceTimes = 0;
		for(int i=0;i<count;i++){
			if(data[i].equals(item))
				elementOccurenceTimes++;
		}
		return elementOccurenceTimes;
	}
	
	//Test if the list is in decreasing order
	public boolean isInOrder(){
		if(count==0||count==1)
			return true;
		if(data[0] instanceof String){
				for(int i=0;i<count-1;i++){
					String s1 = (String)data[i];
					String s2 = (String)data[i+1];
					if(s1.compareTo(s2)<0)
						return false;
				}
				return true;
			}
		if(data[0] instanceof Integer){
			   for(int i=0;i<count-1;i++){
				   Integer i1 = (Integer)data[i];
				   Integer i2 = (Integer)data[i+1];
				   if(i1.compareTo(i2)<0)
					   return false;
			   }
			   return true;
		}
		return false;
	}

	//Return the sublist containing every 3rd element of the list 
	public DesignedList<E> everyThree(){
		DesignedList<E> sublist = new DesignedList<E>();
		for(int i=2;i<count;i=i+3){
			sublist.add(data[i]);
		}
		return sublist;
	}
	
	//Test whether the list contains the same elements as list received
	public boolean simList(DesignedList<? extends Object> comeparedList){
		if(this.size()!=comeparedList.size())
			return false;
		for(int i=0;i<count;i++){
			E item = data[i];
			if(this.elementOccurrenceTimes(item)!=comeparedList.elementOccurrenceTimes(item))
				return false;
		}
		return true;
	}

	//Display all the elements
	public void display(){
		for(E e: this){
			System.out.print(e+" ");
		}
	}
}