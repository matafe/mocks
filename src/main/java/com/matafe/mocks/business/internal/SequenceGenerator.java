package com.matafe.mocks.business.internal;

import java.util.concurrent.atomic.AtomicLong;

import com.matafe.mocks.business.ISequeceGenerator;

/**
 * Simple Sequence Generator Implementation.
 * 
 * @author matafe
 */
public class SequenceGenerator implements ISequeceGenerator {

	private static final long SEQUENCE_INITIAL_VALUE = 0L;
	
	/** The sequence value */
	private final AtomicLong sequence;

	/** The singleton instance of this class */
	private static ISequeceGenerator instance;

	/** Private Contructor */
	private SequenceGenerator(long initialValue) {
		this.sequence = new AtomicLong(initialValue);
	}

	/**
	 * Get the singleton instance of this class using double checked locking.
	 */
	public static ISequeceGenerator getInstance() {
		if (instance == null) {
			synchronized (SequenceGenerator.class) {
				if (instance == null) {
					instance = new SequenceGenerator(SEQUENCE_INITIAL_VALUE);
				}
			}
		}
		return instance;
	}

	/**
	 * Get the current value of the sequence.
	 * 
	 * @see com.matafe.mocks.business.ISequeceGenerator#getCurrentValue()
	 */
	@Override
	public long getCurrentValue() {
		return sequence.get();
	}

	/**
	 * Get the next value of the sequence.
	 * 
	 * @see com.matafe.mocks.business.ISequeceGenerator#getNextValue()
	 */
	@Override
	public long getNextValue() {
		return sequence.incrementAndGet();
	}

	@Override
	public String toString() {
		return "SequenceGenerator [" + sequence + "]";
	}

}
