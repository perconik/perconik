package com.gratex.perconik.tag.assistant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.ui.text.java.ContentAssistInvocationContext;
import org.eclipse.jdt.ui.text.java.IJavaCompletionProposalComputer;
import org.eclipse.jdt.ui.text.java.JavaContentAssistInvocationContext;
import org.eclipse.jface.fieldassist.ContentProposal;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import com.gratex.perconik.tag.plugin.Activator;
import com.gratex.perconik.tag.utils.MarkTemplate;
import com.gratex.perconik.tag.ws.WsTags;
import com.gratex.perconik.tag.ws.WsTags.Attribute;
import com.gratex.perconik.tag.ws.WsTags.EnumCase;

public class ConAssist implements IJavaCompletionProposalComputer, IContentProposalProvider {
	
	private final static HashMap<String, String> docs = new HashMap<String, String>();
	
	private final static HashMap<String, OrderSet> keyValue = new HashMap<String, OrderSet>();	
	private final static HashMap<String, OrderSet> keyWords = new HashMap<String, OrderSet>();
	
//	private static OrderSet keyWords; // = new OrderSet();
	private static OrderSet names = null;
	
	public static void clear(){
		names = null;
		docs.clear();
		keyValue.clear();
		keyWords.clear();
	}
	
	private static void init(){
		if(names == null){
//			HashMap<String, Set<String>> kW = new HashMap<String, Set<String>>();
			
			HashSet<String> nmW = new HashSet<String>();
			for(WsTags t : WsTags.load()){
				nmW.add(t.name());
				
				docs.put(t.name().toLowerCase(), t.description());
				
				HashSet<String> values = new HashSet<String>();
				for(Attribute a : t.attributes()){
					
					docs.put(t.name().toLowerCase()+":#"+a.name().toLowerCase(), a.description());
					
					values.add("#"+a.name());
					HashSet<String> enums = new HashSet<String>();
					for(EnumCase ec : a.enums()){
						enums.add(ec.name());
						docs.put(t.name().toLowerCase()+":#"+a.name().toLowerCase()+":"+ec.name().toLowerCase(), ec.description());
					}
					if(enums != null)keyValue.put(t.name().toLowerCase()+":#"+a.name().toLowerCase(), new OrderSet(enums));	
				}
				keyWords.put(t.name().toLowerCase(), new OrderSet(values));
				
//				for(Map.Entry<String, Set<String>> e : kW.entrySet()){
//					keyWords.put(e.getKey().toLowerCase(), new OrderSet(e.getValue()));
//				}
				
//				Set<String> s = kW.get(t.name());
//				if(s == null){
//					s = new HashSet<>();
//					kW.put(t.name(), s);
//				}
//				s.add(t.insertionText());
//				
//				StringBuilder sb = new StringBuilder();
//				
//				for(Attribute a : t.attributes()){
//					sb.append("\n     "+a.name()+" : "+a.description()+"");
//					HashSet<String> values = new HashSet<String>();
//					for(EnumCase ec : a.enums()){
//						values.add(ec.name());
//						docs.put(t.name().toLowerCase()+":"+a.name().toLowerCase()+":"+ec.name().toLowerCase(), "<html><pre>"+ec.description()+"</pre></html>");
//					}
//					if(!values.isEmpty()) keyValue.put(t.name().toLowerCase()+":"+a.name().toLowerCase(), new OrderSet(values));					
//				}
//				
//				docs.put(t.name().toLowerCase()+":"+t.insertionText().toLowerCase(), "<html><pre>"+t.description()+""+sb.toString()+"</pre></html>");
				
			}
			
			//keyWords = new OrderSet(kW);
			names = new OrderSet(nmW);
		}
	}
//	static{
//		HashSet<String> kW = new HashSet<String>();
//		kW.add("performance"); 
//		kW.add("error"); 
//		kW.add("error-prone"); 
//		
//		_keyWords = new OrderSet(kW);
//	}
	
//	private final static OrderSet _keyKeys;
	
	
//	static{
//		HashSet<String> values = new HashSet<String>();
//		values.add("lazy");
//		values.add("eager");
//		keyValue.put("initialization", new OrderSet(values));
//	
//		values = new HashSet<String>();
//		values.add("massively-parallel");
//		values.add("parallel");
//		values.add("sequential");
//		keyValue.put("concurrency", new OrderSet(values));
//		
//		keyKeys = new OrderSet(keyValue.keySet());
//	}
	
	@Override
	public List<ICompletionProposal> computeCompletionProposals(ContentAssistInvocationContext context, IProgressMonitor monitor) {
		final int max = 25;
		
		try {
			LinkedList<ICompletionProposal> listCompl = null;
			IDocument doc = context.getDocument();
			int off = context.getInvocationOffset() - 1;
			
			StringBuilder sb = new StringBuilder();
			int start;
			boolean first = false;
			
			StringBuilder pn = new StringBuilder();
			
			for(start = off; start >= 0; start --){
				char c = doc.getChar(start);
				if(c == '/') break;
				pn.append(c);
			}
			
			String prf = pn.reverse().toString();
			StringBuilder xx = new StringBuilder();
			for(char c : prf.toCharArray()){
				if(Character.isLetterOrDigit(c)) xx.append(c);
				else break;
			}
			prf = xx.toString();
//			int i1 = prf.indexOf(" ");
//			int i2 = prf.indexOf("\t");
//			if(i1 > 0 && i2 > 0){
//				prf = prf.substring(0, Math.min(i1, i2));
//			}else if(i1 > 0){
//				prf = prf.substring(0, i1);
//			}else if(i2 > 0){
//				prf = prf.substring(0, i2);
//			}
			
			for(start = off; start >= 0; start --){
				char c = doc.getChar(start);
				if(c == '|'){
					if(listCompl == null) listCompl = new LinkedList<ICompletionProposal>();
					
					int len = off-start+1;
					
					String s = "| "+new MarkTemplate().getInfo();
					listCompl.add( new CompletionProposal(s, start, len, s.length()) );
					
					break;
				}else if(c == '<' || c == '@'){
					
					int line = doc.getLineOfOffset( context.getInvocationOffset() );
					
					JavaContentAssistInvocationContext cx = (JavaContentAssistInvocationContext)context;
					IResource r = cx.getCompilationUnit().getResource();
					IMarker markers[] = r.findMarkers(Activator.MARKER_TYPE+".EqBlue", true, IResource.DEPTH_INFINITE);
					
					int ml = -1;
					String id = null;
					
					for(IMarker m : markers){
						Object l = m.getAttribute(IMarker.LINE_NUMBER);
						if(l != null && l instanceof Integer && (Integer)l < line){
							int q = (Integer)l;
							if(q > ml){
								ml = q;
								id = (String)m.getAttribute(Activator.MARKER_ID);
							}
						}
					}
					
					if(id != null){
						if(listCompl == null) listCompl = new LinkedList<ICompletionProposal>();
						
						if(c == '@'){
							int len = off-start+1;
							
							String s = "@< | "+id;
							listCompl.add( new CompletionProposal(s, start, len, s.length()) );
						}else{
							int len = off-start+1;
							
							String s = "@< | "+id;
							listCompl.add( new CompletionProposal(s, start-1, len+1, s.length()) );
						}
					}
					
					break;
					
				}else if(c == '/' || c == ')'){
					first = c == '/';
//					int len = off-start;
//					int i = 0;
//					for(String s: names.get("")){
//						if(listCompl == null) listCompl = new LinkedList<ICompletionProposal>();
//						listCompl.add( new CompletionProposal(s, start+1, len, s.length()) );
//						
//						i++;
//						if(i >= max) break;
//					}	
					
					break;
				}else if(c == '#'){
					sb.append('#');
					break;
				}else{
					sb.append(c);
				}
			}
			
			String part = sb.reverse().toString();
			String pt = part.trim();
			
			if(pt.startsWith("#")){
				int k = pt.indexOf("(");
				if(k != -1){
					init();
					String nm = pt.substring(0, k);
//					int cnt = 0;
//					for(char c : pt.toCharArray()) if(c == ',') cnt ++;
					OrderSet st = keyValue.get(prf.toLowerCase()+":"+nm.toLowerCase());
					
					if(st != null){
						StringBuilder q = new StringBuilder();
						for(int j = pt.length() -1; j >= 0; j--){
							char x = pt.charAt(j);
							if(x == '('){
								break;
							}else{
								q.append(x);
							}
						}
						
						int s2 = part.lastIndexOf('(');
						
						start = start + s2;
						
						int len = off-start;
						
						int i = 0;
						for(String s: st.get(q.reverse().toString())){
							if(listCompl == null) listCompl = new LinkedList<ICompletionProposal>();
							listCompl.add( new CompletionProposal(s, start+1, len, s.length(), null, null, null, docs.get(prf.toLowerCase()+":"+nm.toLowerCase()+":"+s.toLowerCase())) );
							
							i++;
							if(i >= max) break;
						}
					}
					
				}else{
					init();
					int i = 0;
					int len = off-start;
					
					if(keyWords.get(prf.toLowerCase()) != null) for(String s: keyWords.get(prf.toLowerCase()).get(pt)){
						if(listCompl == null) listCompl = new LinkedList<ICompletionProposal>();
						listCompl.add( new CompletionProposal(s, start, len+1, s.length(), null, null, null, docs.get(prf.toLowerCase()+":"+s.toLowerCase())) );
						
						i++;
						if(i >= max) break;
					}	
				}				
			}else if(first && (!pt.contains(" ")) && (!pt.contains("\t"))){
				init();
				
				int i = 0;
				int len = off-start;
				
				for(String s: names.get(pt)){
					if(listCompl == null) listCompl = new LinkedList<ICompletionProposal>();
					listCompl.add( new CompletionProposal(s, start+1, len, s.length(), null, null, null, docs.get(s.toLowerCase())) );
					
					i++;
					if(i >= max) break;
				}	
			}
			
//			int type = 0;
//			int len; // = off-start+1;
//			String w; // = doc.get(start, len);
//			int start;
			
//			if(off < 0){ // to tu netreba
//				start = 0;
//				len = 0;
//				w = "";
//			}else{
//				
//				for(start = off; start >= 0; start --){
//					char c = doc.getChar(start);
//					if(c == '|'){
//						type = 2;
//						break;
//					}else if(c == '#'){
//						type = 1;
//						break;
//					}else if( Character.isWhitespace(c) ){
//						break;
//					}
//				}
//				
//				start ++;
//				
//				len = off-start+1;
//				w = doc.get(start, len);
//			}
//			if(type == 2){
//				listCompl = new LinkedList<ICompletionProposal>();
//				
//				if(context instanceof JavaContentAssistInvocationContext){
//					int line = doc.getLineOfOffset( context.getInvocationOffset() );
//					
//					JavaContentAssistInvocationContext cx = (JavaContentAssistInvocationContext)context;
//					IResource r = cx.getCompilationUnit().getResource();
//					try {
//						IMarker markers[] = r.findMarkers(Activator.MARKER_TYPE+".Err", true, IResource.DEPTH_INFINITE);
//						for(IMarker m : markers){
////							Object ot = m.getAttribute(Activator.MARKER_OPEN_TYPE);
////							if(ot != null && ot instanceof Boolean && (Boolean)ot ){
////								Object l = m.getAttribute(IMarker.LINE_NUMBER);
////								if(l != null && l instanceof Integer && (Integer)l < line){								
////									String s = "| "+m.getAttribute(IMarker.MESSAGE).toString();
////									listCompl.add( new CompletionProposal(s, start, len, s.length()) );
////								}
////							}
//						}
//					} catch (CoreException e) {
//						Activator.getDefault().getLog().log( new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Exception", e) );
//					}
//				}
//				
//				String s = "| "+new MarkTemplate().getInfo();
//				listCompl.add( new CompletionProposal(s, start, len, s.length()) );
//				
//				s = "| "+new MarkTemplate().getInfo();
//				listCompl.add( new CompletionProposal(s, start, len, s.length()) );
//				
//			}else if(type == 1){
//				int i = 0;
//				for(String s: keyWords.get(w)){
//					if(listCompl == null) listCompl = new LinkedList<ICompletionProposal>();
//					listCompl.add( new CompletionProposal(s, start, len, s.length()) );
//					
//					i++;
//					if(i >= max) break;
//				}
//			}else{
//				
//				int ind = w.indexOf(':'); 
//				if(ind == -1){
//					int i = 0;
//					for(String s: keyKeys.get(w)){
//						if(listCompl == null) listCompl = new LinkedList<ICompletionProposal>();
//						listCompl.add( new CompletionProposal(s, start, len, s.length()) );
//						
//						i++;
//						if(i >= max) break;
//					}	
//				}else{
//					String pref = w.substring(0, ind);
//					String post = w.substring(ind+1);
//					OrderSet tmp = keyValue.get(pref);
//					if(tmp != null){
//						int i = 0;
//						for(String s: tmp.get(post)){
//							if(listCompl == null) listCompl = new LinkedList<ICompletionProposal>();
//							listCompl.add( new CompletionProposal(s, start+ind+1, post.length(), s.length()) );
//							
//							i++;
//							if(i >= max) break;
//						}	
//					}
//				}
//			}
						
			if(listCompl != null) return listCompl;
		} catch (Exception e) {
			Activator.getDefault().getLog().log( new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Exception", e) );
		}
		return Collections.emptyList();
	}
	
	@Override
	public IContentProposal[] getProposals(String contents, int position) {
		final int max = 25;
		ArrayList<IContentProposal> listCompl = new ArrayList<IContentProposal>(max); 

		boolean keyWord = false;
		int start;
		
		position--;

		String w;
		
		if(position >= 0){
			for(start = position; start >= 0; start --){
				char c = contents.charAt(start);
				if(c == '#'){
					keyWord = true;
					break;
				}else if( Character.isWhitespace(c) ){
					break;
				}
			}
			
			start ++;
			
			w = contents.substring(start, position+1);
		}else{
			w = "";
		}
		
		if(keyWord){
			int i = 0;
			init();
			for(String s: keyWords.get("").get(w)){
				listCompl.add( new ContentProposal(s) );
				
				i++;
				if(i >= max) break;
			}
		}else{
			int ind = w.indexOf('(');
			//System.out.println(w+" "+ind);
			if(ind == -1){
				int i = 0;
				init();
				for(String s: keyWords.get("").get(w)){
					listCompl.add( new ContentProposal(s) );
					
					i++;
					if(i >= max) break;
				}	
			}else{
				String pref = w.substring(0, ind);
				String post = w.substring(ind+1);
				init();
				OrderSet tmp = keyValue.get(pref);
				if(tmp != null){
					int i = 0;
					for(String s: tmp.get(post)){
						listCompl.add( new ContentProposal(s) );
						
						i++;
						if(i >= max) break;
					}	
				}
			}
		}
		
		return listCompl.toArray(new IContentProposal[listCompl.size()]);		
	}
	
	@Override
	public List<IContextInformation> computeContextInformation(ContentAssistInvocationContext context, IProgressMonitor monitor) {
		return Collections.emptyList();
	}

	@Override
	public String getErrorMessage() { return null; }

	@Override
	public void sessionEnded() {}

	@Override
	public void sessionStarted() {}
	
}
