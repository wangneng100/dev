package datastructure.stackandqueue;

public class CompareVersionNumber {
	
	public int compareVersion(String version1, String version2) {
        String[] version1arr = version1.split("\\.");
        String[] version2arr = version2.split("\\.");
        
        int length = Math.min(version1arr.length, version2arr.length);
        
        for(int i=0; i< length; i++) {
            int res = compare(version1arr[i], version2arr[i]);
            if(res!= 0){
                return res;
            }
        }
        
        if(version1arr.length > version2arr.length) {
            for(int i = length; i< version1arr.length; i++){
                if(Integer.parseInt(version1arr[i]) > 0 ){
                    return 1;
                }
            }
        } else if(version1arr.length < version2arr.length) {
            for(int i = length; i< version2arr.length; i++){
                if(Integer.parseInt(version2arr[i]) > 0 ){
                    return -1;
                }
            }
        }
        
        return 0;
    }
    
    private int compare(String s1, String s2){
        if(Integer.parseInt(s1) == Integer.parseInt(s2)){
            return 0;
        }else{
            return Integer.parseInt(s1) > Integer.parseInt(s2)?1:-1;
        }
    }
	
	public static void main(String[] args) {
		System.out.println("1.2.3".split("\\.")[0]);
	}

}
