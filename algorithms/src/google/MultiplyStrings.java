package google;

public class MultiplyStrings {
	public static String multiply1(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0"))
            return "0";
        
        int[] arr1 = new int[num1.length()];
        int[] arr2 = new int[num2.length()];
        
        for(int i=0; i<num1.length(); i++){
            arr1[i] = num1.charAt(i)-'0';
        }
        for(int i=0; i<num2.length(); i++){
            arr2[i] = num2.charAt(i)-'0';
        }
        
        int[] res = new int[num1.length()+num2.length()];
        
        for(int i=0; i<num1.length(); i++){
            for(int j=0; j<num2.length(); j++){
                res[i+j+1] += arr1[i]*arr2[j];
            }
        }
        int carry =0;
        for(int i=res.length-1; i>=0; i--) {
            int num = res[i]+carry;
            res[i] = num%10;
            carry = num/10;
        }
        
        //remove leading 0
        StringBuilder sb = new StringBuilder();
        int cur =0;
        while(res[cur]==0){
            cur++;
        }
        
        for(;cur<res.length; cur++){
            sb.append(res[cur]);
        }
        
        return sb.toString();
        
    }
	
	public static String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        
        int m = num1.length(), n = num2.length();
        int[] sum = new int[m + n];
        
        for (int i = m - 1; i >= 0; i --) {
            int a = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j --) {
                int b = num2.charAt(j) - '0';
                sum[i + j + 1] += a * b;
            }
        }
        
        for (int i = m + n - 1; i > 0; i --) {
            sum[i - 1] += sum[i]/10;
            sum[i] %= 10;
        }
        
        int index = sum[0] == 0 ? 1 : 0;
        StringBuilder sb = new StringBuilder();
        while (index < m + n) {
            sb.append(sum[index++]);
        }
        
        return sb.toString();
    }
	
	public static String multiply3(String num1, String num2) {
		if(num1.equals("0") || num2.equals("0"))
            return "0";
		
		int [] num1Arr = new int[num1.length()];
		int [] num2Arr = new int[num2.length()];
		
		int [] res = new int[num1.length()+num2.length()];
		
		for(int i=0; i<num1Arr.length; i++) {
			num1Arr[i] = num1.charAt(i) - '0';
		}
		
		for(int j=0; j<num2Arr.length; j++) {
			num2Arr[j] = num2.charAt(j) - '0';
		}
		
		for(int i=num1Arr.length-1; i>=0; i--) {
			for(int j=num2Arr.length-1; j>=0; j--) { 
				int product = num1Arr[i] * num2Arr[j];
                int tmp = res[i+j+1] + product;
				res[i+j+1] = tmp%10;
				res[i+j] += tmp/10;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int firstIndex = 0;
		while(firstIndex<res.length) {
			if(res[firstIndex]!=0) {
				break;
			}
			firstIndex++;
		}
		for(int i=firstIndex; i<res.length; i++) {
			sb.append(res[i]);
		}
		
		return sb.toString();
	}
	
	public static void  main(String[] args) {
		String num1 = "122312321312312322131231231221348957122669989987989898788899122312321312312322131231231221348957122669989987989898788899122312321312312322131231231221348957122669989987989898788899122312321312312322131231231221348957122669989987989898788899";
		String num2 = "978565898955698578955556555233122222222222222222222222223112231232131231232213123123122134895712266998998798989878889923978565898955698578955556555233122222222222222222222222223112231232131231232213123123122134895712266998998798989878889923";
		
		long startTime = System.currentTimeMillis();
		System.out.println(multiply3(num1, num2));
		System.out.println("timeCost:" + (System.currentTimeMillis()-startTime));
	}

}
