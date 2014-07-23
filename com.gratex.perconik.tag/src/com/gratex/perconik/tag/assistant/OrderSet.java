package com.gratex.perconik.tag.assistant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

public class OrderSet {
	
	private final ArrayList<String> words; // = new ArrayList<String>();
	private final ArrayList<String> toWords; // = new ArrayList<String>();
	
	public OrderSet(Set<String> w){
		words = new ArrayList<String>(w.size());
		toWords = new ArrayList<String>(w.size());
		for(String s : w){
			words.add(s.toLowerCase());
			toWords.add(s);
		}
		Collections.sort(words);
		Collections.sort(toWords, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.toLowerCase().compareTo(o2.toLowerCase());
			}			
		});
	}
	
	public Collection<String> get(String pref){
		if(pref == null || pref.isEmpty()){
			return toWords;
		}else{
			pref = pref.toLowerCase();
			
			int from = 0;
			int to = words.size();
			
			while(from < to){
				int mid = (from + to) / 2;
				
				int cmp = pref.compareTo(words.get(mid));
				
				if (cmp < 0) { to = mid; // repeat search in bottom half.
		        } else if (cmp > 0) { from = mid + 1;  // Repeat search in top half.
		        } else {
		            from = mid;
		            break;
		        }				
			}
			int fromInd = from;
			
			to = words.size();
			
			while(from < to){
				int mid = (from + to) / 2;
				
				if(words.get(mid).startsWith(pref)){ from = mid + 1;
				}else{ to = mid; }
			}
			
			return toWords.subList(fromInd, from);			
		}
	}
	
	/* public static void main(String[] args) {
		HashSet<String> set = new HashSet<String>();
		set.add("a");
		set.add("aa");
		set.add("ab");
		set.add("abc");
		set.add("b");
		set.add("bb");
		set.add("bbc");
		set.add("d");
		set.add("da");
		set.add("daa");
		set.add("x");
		OrderSet orderSet = new OrderSet(set);
		System.out.println(orderSet.get(null));
		System.out.println(orderSet.get("z"));
		System.out.println(orderSet.get("c"));
		System.out.println(orderSet.get("d"));
		System.out.println(orderSet.get("da"));
	} */
}
