package com.gratex.perconik.tag.ws;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.eclipse.jface.preference.IPreferenceStore;
import com.gratex.perconik.services.tag.ArrayOfTagProfileSearchResItemDto;
import com.gratex.perconik.services.tag.ArrayOfTagType;
import com.gratex.perconik.services.tag.BoolTagAttribute;
import com.gratex.perconik.services.tag.EnumTagAttribute;
import com.gratex.perconik.services.tag.EnumValue;
import com.gratex.perconik.services.tag.FloatTagAttribute;
import com.gratex.perconik.services.tag.GetTagProfileRequest;
import com.gratex.perconik.services.tag.GetTagProfileResponse2;
import com.gratex.perconik.services.tag.IntTagAttribute;
import com.gratex.perconik.services.tag.SearchTagProfileRequest;
import com.gratex.perconik.services.tag.SearchTagProfileResponse2;
import com.gratex.perconik.services.tag.StringTagAttribute;
import com.gratex.perconik.services.tag.TagAttribute;
import com.gratex.perconik.services.tag.TagProfileSearchResItemDto;
import com.gratex.perconik.services.tag.TagProfileWcfSvc;
import com.gratex.perconik.services.tag.TagType;
import com.gratex.perconik.tag.plugin.Activator;
import com.gratex.perconik.tag.prefs.PrefKeys;

public class WsTags {
	
	private final String name;
	public String name() { return name; }
	
	private final String insertionText;
	public String insertionText() { return insertionText; }
	
	private final String description;
	public String description() { return description; }
	
	private final List<Attribute> attributes = new ArrayList<Attribute>();
	public List<Attribute> attributes() { return attributes; }

	public WsTags(String name, String insertionText, String description) {
		this.name = name;
		this.insertionText = insertionText; //.endsWith("()") ? insertionText.substring(0, insertionText.length()-2) : insertionText;
		this.description = description;
	}
	
	public enum AttributeType{
		Enum, Bool, Real, Int,Str 
	}
	
	public static class Attribute{
		private final AttributeType type;
		public AttributeType type(){ return type; }
		
		private final String name;
		public String name() { return name; }

		private final String description;
		public String description() { return description; }
		
		private final Pattern pattern;
		public Pattern getPattern() { return pattern; }

		private final List<EnumCase> enums = new ArrayList<EnumCase>();
		public List<EnumCase> enums() { return enums; }
		
		private final int min;
		public int getMin() { return min; }
		
		private final int max;
		public int getMax() { return max; }

		private final float mind;
		public float getMinD() { return mind; }
		
		private final float maxd;
		public float getMaxD() { return maxd; }
		
		public Attribute(AttributeType type, String name, String description, String p, Integer min, Integer max, Float minf, Float maxF) {
			this.type = type;
			this.name = name;
			this.description = description;
			this.pattern = p == null || p.isEmpty() ? null : Pattern.compile(p);
			
			this.min = min == null ? -1 : min;
			this.max = max == null ? -1 : max;
			
			this.mind = minf == null ? Float.MIN_VALUE : minf;
			this.maxd = maxF == null ? Float.MAX_VALUE : maxF;
		}
		
	}
	
	public static class EnumCase{
		private final String name;
		public String name() { return name; }

		private final String description;
		public String description() { return description; }
		
		public EnumCase(String name, String description) {
			this.name = name;
			this.description = description;
		}
			
	}
	
	// ----------------------------------------------------------
	
	private static List<WsTags> l;
	public static void clear(){ l = null; }
	
	// "Test1"
	public static List<WsTags> load(){
		if(l != null) return l;
		ArrayList<WsTags> tmp = new ArrayList<WsTags>();
		
		IPreferenceStore ps = Activator.getDefault().getPreferenceStore();
		
		TagProfileWcfSvc s;
		try {// http://perconikapp1:9903/Adm/Wcf/TagProfileWcfSvc.svc?singleWsdl
			String tmp2 = ps.getString(PrefKeys.url);
			if(!tmp2.endsWith("/")) tmp2 = tmp2 + "/";
			s = new TagProfileWcfSvc(new URL(tmp2+"TagProfileWcfSvc.svc?singleWsdl")); // Activator.class.getResource("/TagProfileWcfSvc.svc")
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		String id = null;
		{
			SearchTagProfileRequest r = new SearchTagProfileRequest();
			r.setNameStartPart(ps.getString(PrefKeys.profile));
			//r.setNameStartPart("Test1");
			
			SearchTagProfileResponse2 rs = s.getBasicHttpBindingITagProfileWcfSvc().searchTagProfile(r);
			ArrayOfTagProfileSearchResItemDto a = rs.getResultSet();
			for(TagProfileSearchResItemDto i : a.getTagProfileSearchResItem()){
				id = i.getId();
				break;
			}
		}{
			GetTagProfileRequest r = new GetTagProfileRequest();
			r.setProfileId(id);
			
			GetTagProfileResponse2 rs = s.getBasicHttpBindingITagProfileWcfSvc().getTagProfile(r);
			
			ArrayOfTagType at = rs.getProfile().getTagTypes();
			
			for(TagType tt : at.getTagType()){
				WsTags t;
				tmp.add(t = new WsTags(tt.getName(), tt.getInsertionText(), tt.getDescription()));
				for(TagAttribute ta : tt.getAttributes().getTagAttribute()){
					AttributeType att = null; 
					if(ta instanceof EnumTagAttribute){
						att = AttributeType.Enum;
						Attribute a;
						t.attributes.add(a = new Attribute(att, ta.getName(), ta.getDescription(), null, -1, -1, null, null));
						
						EnumTagAttribute enms = (EnumTagAttribute)ta;
						for(EnumValue ev : enms.getValues().getEnumValue()){
							a.enums().add(new EnumCase(ev.getName(), ev.getDescription()));
						}
						
						continue;
					}else if(ta instanceof StringTagAttribute){
						att = AttributeType.Str;
						StringTagAttribute sa = (StringTagAttribute)ta;
						t.attributes.add(new Attribute(att, ta.getName(), ta.getDescription(), sa.getRegex(), -1, -1, null, null));
						continue;
					}else if(ta instanceof BoolTagAttribute){
						att = AttributeType.Bool;
					}else if(ta instanceof FloatTagAttribute){
						att = AttributeType.Real;
						FloatTagAttribute fa = (FloatTagAttribute)ta;
						t.attributes.add(new Attribute(att, ta.getName(), ta.getDescription(), null, -1, -1, fa.getMinimum(), fa.getMaximum()));
					}else if(ta instanceof IntTagAttribute){
						att = AttributeType.Int;
						IntTagAttribute ia = (IntTagAttribute)ta;
						t.attributes.add(new Attribute(att, ta.getName(), ta.getDescription(), null, ia.getMinimum(), ia.getMaximum(), null, null));
						continue;
					}
					t.attributes.add(new Attribute(att, ta.getName(), ta.getDescription(), null, -1, -1, null, null));
				}
			}
		}
		
		l = tmp;
		return l;
	}
	
}
