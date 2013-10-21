package com.gratex.perconik.tag.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import com.gratex.perconik.tag.antlr.TagGrammarLexer;
import com.gratex.perconik.tag.antlr.TagGrammarParser;
import com.gratex.perconik.tag.antlr.TagGrammarParser.AttContext;
import com.gratex.perconik.tag.antlr.TagGrammarParser.EndTagContext;
import com.gratex.perconik.tag.antlr.TagGrammarParser.TagContext;
import com.gratex.perconik.tag.plugin.Activator;
import com.gratex.perconik.tag.ws.WsTags;
import com.gratex.perconik.tag.ws.WsTags.Attribute;
import com.gratex.perconik.tag.ws.WsTags.AttributeType;
import com.gratex.perconik.tag.ws.WsTags.EnumCase;

public class FileParser {
	
	public static Collection<Entry> parse(InputStream is, String charset){
		InputStreamReader ir = null;
		BufferedReader br = null;
		try {
			ir = new InputStreamReader(is,charset);
			br = new BufferedReader(ir);
						
//			HashMap<String, List<Entry>> map = null;
			LinkedList<Entry> ret = new LinkedList<FileParser.Entry>();
			
			String line;
			int i = 1;
			while( ( line=br.readLine() ) != null ){
				
				StringBuffer sb = new StringBuffer();
				
				int mode = 0;
				for(char c : line.toCharArray()){
					if(mode == 0){
						if(c == '/') mode = 1;
						else if(c == '"') mode = 3;
					}else if(mode == 1){
						if(c == '/') mode = 2;
						else if(c == '"') mode = 3;
						else mode = 0;
					}else if(mode == 2){
						sb.append(c);
					}else if(mode == 3){ // string parsing
						if(c == '\\') mode = 4;
						else if(c == '"') mode = 0;
					}else if(mode == 4){
						mode = 3;
					}
				}
				
				String s = sb.toString().trim();
				sb = new StringBuffer();
				for(char c : s.toCharArray()){
					if(Character.isLetterOrDigit(c)) sb.append(c);
					else break;
				}
				
				String nm = sb.toString();
				
				Throwable tag = null;
//				Exception endTag = null;
				Entry en = null;
				
				if(s.startsWith("@<")){
					try{
						final StringBuilder err = new StringBuilder();
						
			        	TagGrammarLexer cl = new TagGrammarLexer(new ANTLRInputStream(s));
						TagGrammarParser cp = new TagGrammarParser( new CommonTokenStream(cl));
						cp.addErrorListener(new BaseErrorListener(){
							@Override
							public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
								err.append(charPositionInLine + " " + msg);
							}
						});
				        
				        EndTagContext tc = cp.endTag();
				        
				        if(err.length() > 0) throw new Exception(err.toString());
				        
				        String id = tc.userName().getText()+" "+tc.dateTimeUtc().getText();
				        Entry start = null;
				        for(Entry e : ret) if(id.equals(e.id)){
				        	start = e;
				        	break;					        	
				        }
				        
				        en = new Entry(null, start, start == null ? "unable find start tag" : tc.freeText().getText(), i, start == null ? ".Err" : ".GtBlue");
				        
			        }catch(Exception exc){
			        	tag = exc;
			        }
				}else{
				
					for(WsTags t : WsTags.load()){
						
						if(t.name().equalsIgnoreCase(nm)){
	//						if(map == null) map = new HashMap<String, List<FileParser.Entry>>();
							
							try{
								final StringBuilder err = new StringBuilder();
								
					        	TagGrammarLexer cl = new TagGrammarLexer(new ANTLRInputStream(s));
								TagGrammarParser cp = new TagGrammarParser( new CommonTokenStream(cl));
								cp.addErrorListener(new BaseErrorListener(){
									@Override
									public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
										err.append(charPositionInLine + " " + msg);
									}
								});
						        
						        TagContext tc = cp.tag();
						        
						        if(err.length() > 0) throw new Exception(err.toString());
						        
						        String id = tc.userName().getText()+" "+tc.dateTimeUtc().getText();
						        
	//					        Entry re = null;
						        
						        for(Entry e : ret) if(id.equals(e.id)){
						        	en = new Entry(null, null, "Duplicate "+id, i, ".Err");
						        	break;					        	
						        }
						        	
						        HashSet<String> st = new HashSet<>();
						        
						        if(tc.tagBody() != null) for(AttContext c : tc.tagBody().att()){						        	
				        			String att = c.Identifier().getText();
				        							        			
				        			if(att != null && att.startsWith("#")) att = att.substring(1);
				        			else continue;
				        			
				        			if(!st.add(att.toLowerCase())){
			        					en = new Entry(id, null, "Duplicate attribute "+att, i, ".Err");
		        						break;
			        				}
				        			
				        			String a = c.FreeText() == null ? "" : c.FreeText().getText(); 
				        			boolean ok = false;
				        			
				        			
				        			for(Attribute at : t.attributes()){
				        				if(att.equalsIgnoreCase(at.name())){
				        					ok = true;
						        			if(at.type() == AttributeType.Bool){
					        					if((!"true".equals(a)) && (!"false".equals(a)) && (!a.isEmpty()) ){
					        						en = new Entry(id, null, "Boolean expected for "+at.name(), i, ".Err");
					        						break;
					        					}
					        				}else if(at.type() == AttributeType.Int){
					        					try{ 
					        						long l = Long.parseLong(a);
					        						if(at.getMin() != -1 && l < at.getMin()){
					        							en = new Entry(id, null, " out of range "+at.name()+" / "+(at.getMin()+" - "+at.getMax()), i, ".Err");
						        						break;
					        						}
					        						if(at.getMax() != -1 && l > at.getMax() ){
					        							en = new Entry(id, null, " out of range "+at.name()+" / "+(at.getMin()+" - "+at.getMax()), i, ".Err");
						        						break;
					        						}
					        					}catch(Exception exc){
					        						en = new Entry(id, null, "Int expected for "+at.name()+" / "+exc.getMessage(), i, ".Err");
					        						break;
					        					}
					        				}else if(at.type() == AttributeType.Real){
					        					try{ 
					        						double l = Double.parseDouble(a);
					        						
					        						if(l < at.getMinD()){
					        							en = new Entry(id, null, " out of range "+at.name()+" / "+(at.getMinD()+" - "+at.getMaxD()), i, ".Err");
						        						break;
					        						}
					        						if(l > at.getMaxD() ){
					        							en = new Entry(id, null, " out of range "+at.name()+" / "+(at.getMinD()+" - "+at.getMaxD()), i, ".Err");
						        						break;
					        						}
					        					}catch(Exception exc){
					        						en = new Entry(id, null, "Float expected for "+at.name()+" / "+exc.getMessage(), i, ".Err");
					        						break;
					        					}
					        				}else if(at.type() == AttributeType.Str){
					        					if(at.getPattern() != null){
					        						Matcher m = at.getPattern().matcher(a);
					        						if(!m.matches()){
					        							en = new Entry(id, null, "Invalid regexp for "+at.name()+" : "+at.name(), i, ".Err");
						        						break;
					        						}
					        					}
					        				}else if(at.type() == AttributeType.Enum){
					        					boolean match = false;
					        					for(EnumCase ec : at.enums()) if(ec.name().equalsIgnoreCase(a)){
					        						match = true;
					        						break;
					        					}
					        					if(!match){
					        						en = new Entry(id, null, "Invalid enum value for "+at.name(), i, ".Err");
					        						break;
					        					}
					        				}
					        			}
				        			}
				        			if(!ok){
				        				en = new Entry(id, null, "Undefined "+c.Identifier().getText(), i, ".Err");
//		        						break;
				        			}
						        }
						        if(en == null) en = new Entry(id, null, tc.tagBody() == null ? "" : tc.tagBody().getText(), i, ".EqBlue");						        	
//						        	for(WsTags tt : WsTags.load()){
//						        		if( tt.name().equalsIgnoreCase(nm) && c.Identifier().getText().equalsIgnoreCase(tt.insertionText()) ){
//						        			ok = true;
//						        			String[] txt = c.FreeText() == null ? new String[0] : c.FreeText().getText().split("\\,");
//						        			
//						        			if(tt.attributes().size() != txt.length){
//						        				if(tt.attributes().size() == 1 && txt.length == 0 && tt.attributes().get(0).type() == AttributeType.Bool){
//						        					break;
//						        				}
//						        				en = new Entry(id, null, "Incorrent atrributes count "+id, i, ".Err");
//						        				break;
//						        			}
//						        			
//						        			for(int j = 0; j < txt.length; j++){
//						        				String a = txt[j].trim();
//						        				Attribute at = tt.attributes().get(j);
//						        				if(at.type() == AttributeType.Bool){
//						        					if((!"true".equals(a)) && (!"false".equals(a))){
//						        						en = new Entry(id, null, "Boolean expected for "+at.name(), i, ".Err");
//						        						break;
//						        					}
//						        				}else if(at.type() == AttributeType.Int){
//						        					try{ Long.parseLong(a);
//						        					}catch(Exception exc){
//						        						en = new Entry(id, null, "Int expected for "+at.name()+" / "+exc.getMessage(), i, ".Err");
//						        						break;
//						        					}
//						        				}else if(at.type() == AttributeType.Real){
//						        					try{ Double.parseDouble(a);
//						        					}catch(Exception exc){
//						        						en = new Entry(id, null, "Float expected for "+at.name()+" / "+exc.getMessage(), i, ".Err");
//						        						break;
//						        					}
//						        				}else if(at.type() == AttributeType.Str){
//						        					if(at.getPattern() != null){
//						        						Matcher m = at.getPattern().matcher(a);
//						        						if(!m.matches()){
//						        							en = new Entry(id, null, "Invalid regexp for "+at.name()+" : "+at.name(), i, ".Err");
//							        						break;
//						        						}
//						        					}
//						        				}else if(at.type() == AttributeType.Enum){
//						        					boolean match = false;
//						        					for(EnumCase ec : at.enums()) if(ec.name().equalsIgnoreCase(a)){
//						        						match = true;
//						        						break;
//						        					}
//						        					if(!match){
//						        						en = new Entry(id, null, "Invalid enum value for "+at.name(), i, ".Err");
//						        						break;
//						        					}
//						        				}
//						        			}
//						        		}
//						        	}
//						        	if(en != null) break;
//						        	if(!ok){
//						        		en = new Entry(id, null, "Undefined "+c.Identifier().getText(), i, ".Err");
//		        						break;
//						        	}
//							    }
						        	        
					        }catch(Exception exc){
					        	tag = exc;
					        }
					        
					        
						}
						
					}
				}
						        
//		        Exception exc = tag != null ? tag : endTag; 
				
				if(en != null){
					ret.add(en);
				}else if(en == null && tag != null){
//					String msg = null;
//					if(tag instanceof ParseCancellationException){
//						ParseCancellationException pce = (ParseCancellationException )tag;
//						tag = pce.getCause();
//						if (tag instanceof RecognitionException) {
//						    RecognitionException re = (RecognitionException)tag;
//						    ParserRuleContext context = (ParserRuleContext)re.getCtx();
//						    msg = (context.start == null ? "" : context.start.getText())+" / "+(context.stop == null ? "" : context.stop.getText());
//						}
//					}
					Entry e = new Entry(null, null, tag.getMessage(), i, ".Err");
					ret.add(e);
				}
				
				i++;
			}
			
			return ret;
			
		} catch (UnsupportedEncodingException e) {			
			Activator.getDefault().getLog().log( new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Exception", e) );
			return null;
		} catch (IOException e) {
			Activator.getDefault().getLog().log( new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Exception", e) );
			return null;
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					Activator.getDefault().getLog().log( new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Exception", e) );
				}
			}
			if(ir != null){
				try {
					ir.close();
				} catch (IOException e) {
					Activator.getDefault().getLog().log( new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Exception", e) );
				}
			}
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					Activator.getDefault().getLog().log( new Status(IStatus.ERROR, Activator.PLUGIN_ID, IStatus.OK, "Exception", e) );
				}
			}
		}		
	}
	
	public static class Entry{
		private final String id;
		public String getId(){ return id == null ? "" : id; }
		
		private final int line;
		private final String message;
		
//		private boolean open;
		//private final Entry openTag;
		
		private Entry(String id, Entry openTag, String message, int line, String type) {
			this.id = id;
			
			//this.openTag = openTag;
			if(openTag != null){
				if(".EqBlue".equals(openTag.type)) openTag.type = ".LtBlue";
				openTag.close();
			}
			
			this.message = message;
			this.line = line;
			this.type = type;
		}

		private String type;
		
		public void invalidate() {
			type = ".Err";
		}
		
		public int getLine() {
			return line;
		}
		
		private int closed = 0;
		private boolean close(){
			closed ++;
			return closed == 1;
		}
				
		public String getMarkerId() {
			if(closed > 1) return ".Err";
			return type;
		}
		
		public String getMessage() {
			if(closed > 1) return "{close}: "+message;
			return message;
		}
		
		@Override
		public String toString() {
			return "Parser.Entry:[id="+id+" line=" + line + ", message=" + message + "]";
		}	
	}
	
}
