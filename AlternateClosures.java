import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {
	public static void main(String[] args) {
		String fd= "A->B;C->DE;AC->F";
		String rls="R(A,B,C,D,E,F)";
		
		Closure closure = new Closure();
		//closure.findclosure(fd, rls);
		
		Map<Set<Character>, Set<Character>> fds = new HashMap<>();
		fds.put("A".chars().mapToObj(c -> (char) c).collect(Collectors.toSet()), "B".chars().mapToObj(c -> (char) c).collect(Collectors.toSet()));
		fds.put("C".chars().mapToObj(c -> (char) c).collect(Collectors.toSet()), "DE".chars().mapToObj(c -> (char) c).collect(Collectors.toSet()));
		fds.put("AC".chars().mapToObj(c -> (char) c).collect(Collectors.toSet()), "F".chars().mapToObj(c -> (char) c).collect(Collectors.toSet()));
		
		System.out.println(fds);
		getClosure1(fds);
		System.out.println(fds);
	}
	
	public static void getClosure1(Map<Set<Character>, Set<Character>> fds) {
		
		boolean changed = false;
		
		fds.forEach((k, v) -> v.addAll(k));
		
		while(true) {
			
			changed = false;
			
			for (Map.Entry<Set<Character>, Set<Character>> entry : fds.entrySet()) {
				for (Map.Entry<Set<Character>, Set<Character>> entry1 : fds.entrySet()) {
					int before = entry.getValue().size();
					if(entry.getKey().containsAll(entry1.getKey())) entry.getValue().addAll(entry1.getValue());
					int after = entry.getValue().size();
					
					if(before!=after) changed = true;
				}
			}
			
			if(!changed) break;
		}
	}
	
	public static void getClosure(Map<Set<Character>, Set<Character>> fds) {
		
	    Flag flag = new Flag();
		
		fds.forEach((k, v) -> v.addAll(k)); // Adding all the characters in key to value
		
		while(true) {
			
			flag.changed = false;
			
			fds.forEach((k, v) -> 
				fds.forEach((k1, v1) -> {
					int before = v.size();
					if(k.containsAll(k1)) v.addAll(v1); //Check if k1 is subset of k, if so add all in v1 to v
					int after = v.size();
					
					if(before!=after) flag.changed = true;
				})
			);
			
			if(!flag.changed) break;
		}
	}
	
	public static class Flag {
		boolean changed;
		public Flag() {
			this.changed = false;
		}
	}
}