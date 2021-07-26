/**
 * 
 */
package predictor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Erik
 *
 */
class PNode {
	
	private HashMap<String, PNode> subnodes;
	
	private String character;
	private int endTimes;
	private int pathTimes;

	PNode(String car){

		endTimes=0;
		pathTimes=0;
		subnodes=new HashMap<String, PNode>();
		
		if(car.isEmpty()) 
			character="";
		else 
			character = car.substring(0, 1);

	}


	//  = map.getOrDefault(key, def);
	
	
	void insertWord(String word){
		
		if(word.isEmpty()) {
			endTimes++;
			
		}else {
			pathTimes++;
			String next = word.substring(0, 1);
			
			PNode n = subnodes.getOrDefault(next, null);
		
			if(n==null) {
				n= new PNode(next);
				subnodes.put(next, n);
			}
			
			n.insertWord(word.substring(1));
		}
	}
	
	
	
	
	
	ArrayList<String> getAllWords(String s){
		
		ArrayList<String> wds = new ArrayList<>();
		
		if(this.endTimes>0) {
			wds.add(s+this.character);
		}
		
		for(PNode n: this.subnodes.values()) {
			wds.addAll(n.getAllWords(s+this.character));
		}
		
		return wds;
	}

	

	
	
	HashMap<String, Integer> findPredict(String w, int ind){
		
		

		if(w.length()-1==ind) {
			String next = w.substring(ind);
			PNode n = this.subnodes.getOrDefault(next, null);
			
			if(n!=null) {				
				return n.getWordsEnds(w.substring(0, ind));
			}
			
		}else {
			String next = w.substring(ind, ind+1);
			PNode n = this.subnodes.getOrDefault(next, null);
			
			if(n!=null) {
				return n.findPredict(w, ind+1);
			}
			
		}
		
		return new HashMap<String, Integer>();
	}



	
	HashMap<String, Integer> getWordsEnds(String w) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		
		if(endTimes>0) {
			hm.put(w+character, endTimes);
			
		}
		
		for(String s: subnodes.keySet()) {
			HashMap<String, Integer> m = subnodes.get(s).getWordsEnds(w+character);
			
			for(String s2: m.keySet()) {
				hm.put(s2, m.get(s2));
			}
		}
		
		return hm;
	}
	
	

	
	boolean removeWord(String w, int ind) {
		if(w.length()==ind) {
			if(endTimes>0) {
				endTimes--;
				return true;
			}else {
				return false;
			}
			
			
		}else {
			String next = w.substring(ind, ind+1);
			PNode n = subnodes.getOrDefault(next, null);
			if(n==null) {
				return false;
			}else {
				boolean rmd = n.removeWord(w, ind+1);
				if(rmd && pathTimes>0) {
					pathTimes--;
				}
				return rmd;
			}
				
		}
	}
	
	
	
	
	




	String getCharacter() {
		return character;
	}

	int getEndTimes() {
		return endTimes;
	}

	int getPathTimes() {
		return pathTimes;
	}
	
	HashMap<String, PNode> getSubNodesMap(){
		return subnodes;
	}
	
	ArrayList<PNode> getSubNodes(){
		return new ArrayList<PNode>(subnodes.values());
	}
	
	
	String getString(String word, int level) {
		String s="";

		if(endTimes>0) {
			for(int i=0;i<level;i++) s+="  ";
			
			s+=word+character+"\n";
		}
		
		for(PNode n: subnodes.values()) {
			s+=n.getString(word+character, level+1);
		}
		
		return s;
	}
	
	public String toString() {
		String s = "";
	
		return s;
	}
	
	
	
	int getSubnodeCount() {
		int c=0;
		
		for(PNode n: subnodes.values()) {
			c+= n.getSubnodeCount()+1;
		}
		
		return c;
	}
	
	int getWordCount() {
		int c=0;
		
		if(endTimes>0) c++;
		
		for(PNode n:subnodes.values()) {
			c+=n.getWordCount();
		}
		
		return c;
	}
	
	
	

}
