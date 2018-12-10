import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class IntervalUnion {
		public static String getAuthorName() {
	        return "Yap, Calvin"; // This is my name
	    }

	    public static String getRyersonID() {
	        return "500825267"; // This is my Student id
	    }
	    
	    private int start; // start value for IntervalUnion object;
	    private int end; // end value for IntervalUnion object
	    private  ArrayList<IntervalUnion> badBoy; // ArrayList to store the the IntervalUnions

	    public IntervalUnion(int s, int e) {
	    	badBoy = new ArrayList<>(); // create new arrayList in each Object 
	    	start= s; // set value
			end = e; // set value
			badBoy.add(this); // add element into arrayList
	    }
	    
	    public  IntervalUnion(ArrayList<IntervalUnion> miniBoy) {
	    	badBoy = miniBoy; // set the results sent from Union and Intersection into the array 	
		}
	    
		class sortIntervals implements Comparator<IntervalUnion>{
			@Override // method to do the Collections sort
			public int compare(IntervalUnion thisA, IntervalUnion otherB) {		
				return thisA.start - otherB.start;
			}		
		}
		
		public static IntervalUnion create(int start, int end) {
			return new IntervalUnion(start,end);
	    }
	    
	    @Override public String toString(){
	    	StringBuilder sb = new StringBuilder();
	    	boolean single; // for instances with the same numbers ex. 155-155 is 155
	    		sb.append("[");
	    	for (int i=0; i< badBoy.size(); i++) {
	    		single = false; // cases where array has two different numbers
	    		if(badBoy.get(i).start == badBoy.get(i).end) {
    				sb.append(badBoy.get(i).start);
    				single =true;
    				if (i< badBoy.size()-1) {
	    				sb.append(","); // add in the comma in if it is a multi set
    				}
    			}
	    		if(single == false) {  // not a single element add in comma
		    		if(badBoy.size() >1) {
		    				sb.append(badBoy.get(i).start+"-"+ badBoy.get(i).end );
			    			if (i < badBoy.size()-1)
			    				sb.append(",");
		    		}
		    		else  // is a single element
		    			sb.append(badBoy.get(i).start+"-"+ badBoy.get(i).end );
	    		}
	    	}
	    		sb.append("]"); 
		return sb.toString();
	    }
	    
	    @Override public boolean equals(Object other){
          if(this.getClass() != other.getClass()) {
                return false;
            }
            IntervalUnion oUnion = (IntervalUnion) other; // casting object into a IntervalUnion Obj
            int s1=0,s2=0,o1=0,o2=0; //representing values this.start, this.end, other.start, other.end
            if(this.badBoy.size() != oUnion.badBoy.size()){
                return false; // if the size of the two are not the same then they cannot be equal
            }else {
                for (int i = 0; i< oUnion.badBoy.size(); i++) {
                    s1 = this.badBoy.get(i).start; // Grab values
                    s2 = this.badBoy.get(i).end;
                    o1 = oUnion.badBoy.get(i).start;
                    o2 = oUnion.badBoy.get(i).end;
                    if(s1 != o1  || s2 != o2) // if one element is not equal then it will return false
                    {
                        return false;
                    }
                }
            }           
            return true;
        }
	    
	    @Override public int hashCode(){ 
	    	String Interval = this.toString();
	        int hash =3; // prime number
	        for(int j=0; j<Interval.length();j++) {
	        	hash=hash*17+Interval.charAt(j); // encodes it to unique answer with other prime
	        }
			return hash;
	    }
	    
	    public boolean contains(int x){	    	
	    	for(int i = 0; i < badBoy.size(); i++) {
	    		start = this.badBoy.get(i).start; // set start value
	    		end = this.badBoy.get(i).end; // set end values
	    		if (x >= start && x <= end ) // compare if x is in the middle
	    		{ 
	    			return true;
	    		}
	    	}
	 	    return false;
	       }
	    
	    public IntervalUnion union(IntervalUnion other){
	    	if(this.badBoy.size() ==0) {
	    		return other; //if empty return []
	    	}
	    	if(other.badBoy.size() ==0) {
	    		return this; // if empty return[]
	    	}
	    	ArrayList<IntervalUnion> miniBoy = new ArrayList<>(); // array to store the results 
	    	ArrayList<IntervalUnion> bigBoy = new ArrayList<>(); // array to store current IntervalUnions
	    	bigBoy.addAll(this.badBoy); // add all elements of this
	    	bigBoy.addAll(other.badBoy); // add all elements of other
	    	Collections.sort(bigBoy, new sortIntervals()); // sort by start value of this and other
	    	int thisStart =0,thisEnd =0;
	    	if(bigBoy.size() != 0){ // if array is not empty then put in these values
	    	thisStart = bigBoy.get(0).start; 
        	thisEnd = bigBoy.get(0).end;
	    	}
        	int otherStart =0, otherEnd =0; 	       
	        for(int i = 1; i < bigBoy.size();i++) {
		        otherStart 	= bigBoy.get(i).start; 
		        otherEnd 	= bigBoy.get(i).end;
					if (thisEnd>= otherStart-1) { // situation one since they are sorted we only need to look at the end of this and the start of other to see if they meet
						thisStart = Math.min(thisStart,otherStart); // find new start value	
						thisEnd = Math.max(thisEnd, otherEnd); // find new end value	
		        	}
		        	else {
		        		miniBoy.add(new IntervalUnion(thisStart,thisEnd)); // if not in union add it to results
		        		thisStart = otherStart; // set the next element in the list
		        		thisEnd = otherEnd; // set the next element
		        	}	        	
	        }
        	miniBoy.add(new IntervalUnion(thisStart,thisEnd)); // add final result into the array that holds the results
	    	return new IntervalUnion(miniBoy); // return back the array list
	    }
	    
	    public IntervalUnion intersection(IntervalUnion other){	    	 
	    	ArrayList<IntervalUnion> miniBoy = new ArrayList<>(); // holds results
	    	ArrayList<IntervalUnion> bigBoy = new ArrayList<>(); // holds current intervals
	    	if(this.badBoy.size() == 0 || other.badBoy.size() == 0) {
	    		return new IntervalUnion(bigBoy); // return []
	    	}
	    	bigBoy.addAll(this.badBoy); 
	    	bigBoy.addAll(other.badBoy);
	    	Collections.sort(bigBoy, new sortIntervals());
	    	int thisStart =0;
	    	int thisEnd =0;
		    	if(bigBoy.size() != 0){
		    	thisStart = bigBoy.get(0).start;
		    	thisEnd = bigBoy.get(0).end;
		    	}
	    	int otherStart = 0, otherEnd = 0, startTemp =0, endTemp =0;
	    	for (int i = 1; i< bigBoy.size();i++) {
	    		otherStart = bigBoy.get(i).start;
	    		otherEnd = bigBoy.get(i).end;
	    		if(otherStart <= thisEnd) { // if this start is greater or equal to the start must means there is intersection 
	    			startTemp = Math.min(thisEnd,otherStart); //  find new temp to hold new begining value
	    			endTemp = Math.min(thisEnd, otherEnd); // find new temp to hold new end value
	    			miniBoy.add(new IntervalUnion(startTemp,endTemp)); // add them into the results 
	    			thisStart = Math.max(thisStart, otherStart); // change starting point for the next element
	    			thisEnd = Math.max(thisEnd, otherEnd); // change end point for the next element
	    		}	
	    		else { 
	    			thisStart = otherStart; // if they do not intersect
	    			thisEnd = otherEnd;
	    		}	
	    	}
	    	return new IntervalUnion(miniBoy);
	    }
	    
	    public int getPieceCount(){
	        return this.badBoy.size(); // returns the size of each array which is basically the pieces each element contains
	    }
}
