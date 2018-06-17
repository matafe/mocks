package com.matafe.mocks.business.internal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 * Unit test for Sequence Generator.
 * 
 * @author matafe
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(mockit.integration.junit4.JMockit.class)
public class SequenceGeneratorTest {

	@Before
	public void setUp() throws Exception {
		mockit.Deencapsulation.setField(SequenceGenerator.class, "instance", null);
	}

	@Test
	public void shouldGetCurrentValue() {
		assertThat(SequenceGenerator.getInstance().getCurrentValue(), equalTo(0L));
	}

	@Test
	public void shouldGetNextValue() {
		assertThat(SequenceGenerator.getInstance().getNextValue(), equalTo(1L));
	}
	
	@Test
	public void shouldPrettyPrint() {
		assertThat(SequenceGenerator.getInstance().toString(), equalTo("SequenceGenerator [0]"));
	}

}
