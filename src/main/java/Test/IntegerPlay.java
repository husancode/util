package Test;

public class IntegerPlay {
	
	static class IntegerCache{
		static final int low = -128;
		static final int high = 127;
		static final Integer[] cache;
		static {
			System.out.println("cacheInit");
			cache = new Integer[high-low+1];
			int j = low;
			for(int i=0; i< cache.length; i++){
				cache[i] = j++;
 			}
		}
		private IntegerCache(){
			
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Integer.bitCount(15));
	}

}
