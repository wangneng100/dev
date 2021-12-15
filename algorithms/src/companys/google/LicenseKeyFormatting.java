package companys.google;

public class LicenseKeyFormatting {
	public String licenseKeyFormatting(String s, int k) {
		s = s.toUpperCase().replace("-", "");
		int n = s.length();

		int firstGroupSize = n % k;

		StringBuilder sb = new StringBuilder();
		sb.append(s.substring(0, firstGroupSize));

		for (int i = firstGroupSize; i < n; i++) {
			if (i > 0 && (n - i) % k == 0) {
				sb.append("-");
			}
			sb.append(s.charAt(i));

		}

		return sb.toString();

	}
	
	public String licenseKeyFormatting2(String s, int k) {
        StringBuilder sb = new StringBuilder();
        s = s.toUpperCase().replace("-","");
        int n  = s.length();
        
        if(n == 0){
            return "";
        }
        int firstGroupSize = n%k;
        
        
        
        if(firstGroupSize == 0 ){
            firstGroupSize = k;
            sb.append(s.substring(0,k));   
        } else {
            sb.append(s.substring(0,firstGroupSize));
        }
        
        for(int i=firstGroupSize; i<n; i++){
            if((n-i)%k == 0){
                sb.append("-");
            }
            sb.append(s.charAt(i));
            
        }
        
        return sb.toString();
        
    }

}
