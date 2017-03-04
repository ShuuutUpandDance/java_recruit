package stack_work;

import java.util.ArrayList;

public class Stack<T> {
	private int size;
	private ArrayList<T> list;
	private int top = 0;

	public Stack(int size) {
		this.size = size;
		list = new ArrayList<T>(size);
	}

	public void push(T t) {
		if (top < size) {
			list.add(t);
			top++;
		}
	}

	public T pop() {
		if (top > 0) {
			T t = list.get(--top);
			list.remove(top);
			return t;
		}
		else
			return null;
	}

	public Boolean isEmpty() {
		return top == 0;
	}

	public T getTop() {
		if (top > 0)
			return list.get(top - 1);
		else return null;
	}
	
	public int getPoint(){
		return top;
	}
}
