package stackandarray;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列，要求1. 可以按先后顺序弹出所有元素poll. 2.可以按先后顺序弹出猫pollCat 3.可以按先后顺序弹出狗pollCat
 * @author George Wang
 *
 */
public class CatDogQueue {
	private long order;
	
	private Queue<PetWithOrder> catQ;
	private Queue<PetWithOrder> dogQ;
	
	public CatDogQueue() {
		order = 0;
		catQ = new LinkedList<PetWithOrder>();
		dogQ = new LinkedList<PetWithOrder>();
	}
	
	public void add(Pet p) {
		order++;
		PetWithOrder op = new PetWithOrder(p,order);
		if(p instanceof Cat) {
			catQ.add(op);
		} else {
			dogQ.add(op);
		}
	}
	
	public Pet poll() {
		PetWithOrder cat = catQ.peek();
		PetWithOrder dog = dogQ.peek();
		order--;
		return cat.getOrder() < dog.getOrder()? catQ.poll().getPet() : dogQ.peek().getPet();
	}
	
	public Cat pollCat() {
		return (Cat)catQ.poll().getPet();
	}
	
	public Dog pollDog() {
		return (Dog)dogQ.poll().getPet();
	}
}

class PetWithOrder{
	private Pet pet;
	private long order;
	
	public PetWithOrder(Pet pet, long order) {
		this.pet = pet;
		this.order = order;
	}
	
	public long getOrder() {
		return order;
	}
	
	public Pet getPet() {
		return pet;
	}
}

class Pet {
	private String type;
	
	public Pet(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

class Cat extends Pet{
	public Cat() {
		super("Cat");
	}
}

class Dog extends Pet{
	public Dog() {
		super("Dog");
	}
}