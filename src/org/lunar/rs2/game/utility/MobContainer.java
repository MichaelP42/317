package org.lunar.rs2.game.utility;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.lunar.rs2.game.mob.Mob;

/**
 * A container which contains a single set of mob type's.
 * 
 * @author Michael P
 *
 * @param <M>
 *            The type of {@link Mob} to contain.
 */
public final class MobContainer<M extends Mob> implements Set<M> {

	/**
	 * Constructs a new {@link MobContainer}.
	 * 
	 * @param capacity
	 *            The capacity of the container.
	 */
	public MobContainer(int capacity) {
		this.mobs = new Mob[capacity];
	}
	
	/**
	 * The array of mobs in the container.
	 */
	private Mob[] mobs;
	
	/**
	 * Gets the next index for the mob.
	 * @return The next index in the array.
	 */
	private int nextIndex() {
		for (int i = 1; i < mobs.length; i++) {
			if (mobs[i] == null)
				return i;
		}
		return -1;
	}
	
	@Override
	public boolean add(M arg0) {
		int index = nextIndex();
		if (index == -1)
			return false;
		mobs[index] = arg0;
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends M> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<M> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
