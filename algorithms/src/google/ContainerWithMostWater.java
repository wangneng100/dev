package google;

public class ContainerWithMostWater {
//  public int maxArea(int[] height) {

//  int max = 0;
//  for(int i=0; i<height.length; i++){
//      for(int j=i+1; j< height.length; j++){
//          max = Math.max(max, (j-i)*Math.min(height[j],height[i]));
//      }
//  }

//  return max;
//}

	public int maxArea(int[] height) {

		int begin = 0;
		int end = height.length - 1;
		int max = (end - begin) * Math.min(height[end], height[begin]);

		while (begin < end) {
			max = Math.max(max, (end - begin) * Math.min(height[end], height[begin]));

			if (height[begin] < height[end]) {
				begin++;
			} else {
				end--;
			}

		}

		return max;
	}
}
