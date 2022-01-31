package pairSum;

import static org.junit.Assert.*;


import org.junit.Test;

public class PairSumTest {
	@Test
	public void TestHappyPairSum() {
		PairSum p=new PairSum();
		int[] arr= {1,2,3};
		int[] expected= {2,3};
		assertArrayEquals(expected,p.checkPairsum(3,arr, 5));
	}
	@Test
	public void TestEqualtoTwoPairSum() {
		PairSum p=new PairSum();
		int[] arr= {1,2};
		int[] expected= {1,2};
		assertArrayEquals(expected,p.checkPairsum(2,arr, 3));
	}
	@Test
	public void TestFalseHappyPairSum() {
		PairSum p=new PairSum();
		int[] arr= {1,2,3};
		int[] expected= {-1};
		assertArrayEquals(expected,p.checkPairsum(3,arr, 6));
	}
	@Test
	public void TestFalseEqualtoTwoPairSum() {
		PairSum p=new PairSum();
		int[] arr= {1,2};
		int[] expected= {-1};
		assertArrayEquals(expected,p.checkPairsum(2,arr, 2));
	}
	@Test
	public void TestLessthanTwoValuePairSum() {
		PairSum p=new PairSum();
		int[] arr= {1};
		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,()->p.checkPairsum(1,arr, 5));
		assertEquals("Array Size should be >=2",ex.getMessage());
	}

}
