package pairSum;

import java.util.*;

public class PairSum {

	public int[] checkPairsum(int n,int[] arr,int m) {
		if(n<=1) {
			System.out.println("Array Size should be greater than or equal to 2");
			throw new IllegalArgumentException("Array Size should be >=2");
		}
		else if(n==2) {
			if(arr[0]+arr[1]==m) {
				int[] res= {arr[0],arr[1]};
				return res;
			}
			int[] res= {-1};
			return res;
		}
		else {
		HashSet<Integer> hs = new HashSet<Integer>();
		for(int i=0;i<n;i++) {
			if(hs.contains(m-arr[i])) {
				//System.out.println(m-arr[i]+" "+arr[i]);
				int[] res= {m-arr[i],arr[i]};
				return res;
			}
			hs.add(arr[i]);
		}
		int[] res= {-1};
		return res;
	}
	}		
	
}
