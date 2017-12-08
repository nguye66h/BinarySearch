
/**
* binarySearch class implements binary search algorith to return
* index of a number in a sorted array.
* @author Haimi
* @Date: 02/22/17
*/

public class binarySearch{

	/**
	* public binarySearch method invokes private binarySearch method 
	* to return index of a number in a sorted array
	* @param sorted the sorted array
	* @param test the number to look for in the rray
	* @return an integer that shows the index of test
	*/
	public static int binarySearch(int[] sorted, int test){

		return binarySearch(sorted, test, 0, sorted.length-1);
	}

	/**
	* private binarySearch method 
	* to return index of a number in a sorted array
	* @param sorted the sorted array
	* @param test the number to look for in the array
	* @loIndex index of lowest number in tested range
	* @hiIndex index of highest number in tested range
	* @return an integer that shows the index of test
	*/

	private static int binarySearch(int[] sorted, int test, int loIndex, int hiIndex){
		
		if (loIndex > hiIndex){		// when the comparison is out of array
			return -1;				// return -1
		}
		
		else{
			int mid = (int)((loIndex+hiIndex)/2);	// find middle value of range

			if (sorted[mid] == test){
				return mid;
			}
			else if (sorted[mid] < test){
				return binarySearch(sorted, test, mid + 1, hiIndex); // use recursion for smaller range
			}
			else {
				return binarySearch(sorted, test, loIndex, mid - 1); // use recursion for smaller range
			}
		}

	}

	/**
	* oddTester class creates a testing method that test binarySearch methods 
	* through two different arrays, one sorted and one unsorted.
	* It prints out the position of the tester inside the oddNum array.
	*/
	private static void oddTester(){
		int[] oddNum = new int[100];
		int[] tester = {26, 78 ,100, 186, 13, 99, 101, 177};

		for (int i = 0; i <= 99; i++)
		{
			oddNum[i] = i*2+1;
		}
		for (int i = 0; i < 8; i++){

			// find each element in tester array in oddNum and print out the index
			System.out.println("searching for "+tester[i]+": "+binarySearch(oddNum, tester[i]));
		}
	}

	/**
	* Call the oddTester method when class is run.
	*/
	public static void main (String[] args){
		oddTester();
	}
	
}
