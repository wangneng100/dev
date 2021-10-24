package google;

import java.util.HashSet;
import java.util.Set;

public class UniqeEmail {
	
	//this method is slower than the other one
	public int numUniqueEmails2(String[] emails) {
		HashSet<String> emailSet = new HashSet<String>();
        for(String s:emails) {
            StringBuilder sb = new StringBuilder();
            String arr[] = s.split("@");
            if(arr[0].contains("+"))
                sb.append(arr[0].substring(0, arr[0].indexOf('+')).replace(".",""));
            else 
                sb.append(arr[0].replace(".",""));
            emailSet.add(sb.append("@").append(arr[1]).toString());
        }
        return emailSet.size();
    }
	
	public static int numUniqueEmails(String[] emails) {
        HashSet<String> emailSet = new HashSet<String>();
        for(String email:emails) {
        	boolean localFinish = false;
        	boolean domainStart = false;
        	StringBuilder b = new StringBuilder();
        	for(int i=0; i< email.length(); i++){
                char c = email.charAt(i);
                
                switch(c) {
                	case '.':
                		if(domainStart) {
                			b.append(c);
                		}
                		break;
                	case '@':
                		b.append(c);
                		domainStart = true;
                		break;
                	case '+':
                		localFinish = true;
                		break;
                	default:
                		if(!localFinish || domainStart) {
                			b.append(c);
                		}
                		
                }
            }
        	emailSet.add(b.toString());
        }
        
        return emailSet.size();
    }
	
	public int numUniqueEmails3(String[] emails) {
        Set<String> uniqueEmails = new HashSet<>();
        for(String email: emails) {
            uniqueEmails.add(getTarget(email));
        }
        return uniqueEmails.size();
    }
    
    private String getTarget(String email) {
        StringBuilder target = new StringBuilder();
        boolean skipUntilDomain = false;
        for(int i = 0; i < email.length(); ++i) {
            char next = email.charAt(i);
            if(skipUntilDomain && next != '@') {
                continue;
            }
            if(next == '+') {
                skipUntilDomain = true;
            } else if(next == '@') {
                target.append(email.substring(i));
                break;
            } else if(next != '.') {
                target.append(next);
            }
        }
        return target.toString();
    }
    
    /**
     * fastest one
     * @param emails
     * @return
     */
    public int numUniqueEmails4(String[] emails) {
        
        Set<Integer> uniqueHash = new HashSet<>();
                 
        for(String email: emails){
            uniqueHash.add(computeHash(email));
        }
        
        return uniqueHash.size();
    }
   
   private int computeHash(String email){
       
       // DJBHash
        int hash = 5381; // will have less collisions
       
       // read untill @ or + which ever comes first
       for(int i=0; i< email.length(); i++){
           char ch = email.charAt(i);
           if( ch == '.') continue;
           else if(ch != '@' && ch != '+') 
               hash = (hash << 5) + hash + ch; // hash * 33 + ch
           else 
               break;        
       }
       
       // read untill @
       for(int i= email.length()-1; i>=0; i--){
           char ch = email.charAt(i);
           if(ch != '@'){
               hash = (hash << 5) + hash + ch; // hash * 33 + ch
           } 
           else 
               break;
       }
       
       return hash;
   }

	public static void main(String[] args) {
		String [] emails = new String[] {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
		System.out.println(numUniqueEmails(emails));
	}
}
