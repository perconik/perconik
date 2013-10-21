// Generated from TagGrammar.g4 by ANTLR 4.1
package com.gratex.perconik.tag.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TagGrammarLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		EndTagSign=1, TagType=2, TagTypeSeparator=3, Identifier=4, BracetL=5, 
		BracetR=6, FreeText=7, BodyIdSeparator=8, DateTimeUtc=9, UserName=10, 
		IgnoreWs=11;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"EndTagSign", "TagType", "TagTypeSeparator", "Identifier", "'('", "')'", 
		"FreeText", "BodyIdSeparator", "DateTimeUtc", "UserName", "IgnoreWs"
	};
	public static final String[] ruleNames = {
		"EndTagSign", "TagType", "TagTypeSeparator", "NumberSign", "Identifier", 
		"BracetL", "BracetR", "FreeText", "BodyIdSeparator", "DateTimeUtc", "UserName", 
		"Letter", "WsFrag", "IgnoreWs"
	};


	    int mode = 0;
//		protected int EOF = Eof; //https://groups.google.com/forum/#!topic/antlr-discussion/-Ko4w4qlDZM


	public TagGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TagGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 0: EndTagSign_action((RuleContext)_localctx, actionIndex); break;

		case 1: TagType_action((RuleContext)_localctx, actionIndex); break;

		case 2: TagTypeSeparator_action((RuleContext)_localctx, actionIndex); break;

		case 8: BodyIdSeparator_action((RuleContext)_localctx, actionIndex); break;

		case 13: IgnoreWs_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void IgnoreWs_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4: skip();  break;
		}
	}
	private void EndTagSign_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: mode = 2; break;
		}
	}
	private void BodyIdSeparator_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3: mode = 3; break;
		}
	}
	private void TagType_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: mode = 1; break;
		}
	}
	private void TagTypeSeparator_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2: mode = 2; break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0: return EndTagSign_sempred((RuleContext)_localctx, predIndex);

		case 1: return TagType_sempred((RuleContext)_localctx, predIndex);

		case 2: return TagTypeSeparator_sempred((RuleContext)_localctx, predIndex);

		case 4: return Identifier_sempred((RuleContext)_localctx, predIndex);

		case 7: return FreeText_sempred((RuleContext)_localctx, predIndex);

		case 8: return BodyIdSeparator_sempred((RuleContext)_localctx, predIndex);

		case 9: return DateTimeUtc_sempred((RuleContext)_localctx, predIndex);

		case 10: return UserName_sempred((RuleContext)_localctx, predIndex);

		case 13: return IgnoreWs_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean DateTimeUtc_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6: return mode == 3;
		}
		return true;
	}
	private boolean IgnoreWs_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8: return mode == 0 | mode == 2 | mode == 3;
		}
		return true;
	}
	private boolean EndTagSign_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return mode == 0;
		}
		return true;
	}
	private boolean UserName_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7: return mode == 3;
		}
		return true;
	}
	private boolean BodyIdSeparator_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5: return mode == 2;
		}
		return true;
	}
	private boolean Identifier_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3: return mode == 2;
		}
		return true;
	}
	private boolean FreeText_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4: return mode == 2;
		}
		return true;
	}
	private boolean TagType_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return mode == 0;
		}
		return true;
	}
	private boolean TagTypeSeparator_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return mode == 1;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\r\u008a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\7\3*\n\3\f\3\16\3-\13\3\3\3\3\3\3\4\3\4\7\4\63\n\4\f\4\16"+
		"\4\66\13\4\3\4\3\4\5\4:\n\4\3\4\7\4=\n\4\f\4\16\4@\13\4\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\7\6K\n\6\f\6\16\6N\13\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\6\tV\n\t\r\t\16\tW\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\6\f~\n\f\r\f\16"+
		"\f\177\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\2\20\3\3\2\5\4\3\7\5"+
		"\4\t\2\1\13\6\1\r\7\1\17\b\1\21\t\1\23\n\5\25\13\1\27\f\1\31\2\1\33\2"+
		"\1\35\r\6\3\2\6\3\2\62;\5\2%%*+~~\5\2\13\13\16\17\"\"\5\2C\\aac|\u008f"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\35\3\2\2\2"+
		"\3\37\3\2\2\2\5%\3\2\2\2\7\60\3\2\2\2\tC\3\2\2\2\13E\3\2\2\2\rO\3\2\2"+
		"\2\17Q\3\2\2\2\21S\3\2\2\2\23Y\3\2\2\2\25]\3\2\2\2\27{\3\2\2\2\31\u0081"+
		"\3\2\2\2\33\u0083\3\2\2\2\35\u0085\3\2\2\2\37 \6\2\2\2 !\7B\2\2!\"\7>"+
		"\2\2\"#\3\2\2\2#$\b\2\2\2$\4\3\2\2\2%&\6\3\3\2&+\5\31\r\2\'*\5\31\r\2"+
		"(*\t\2\2\2)\'\3\2\2\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2"+
		"\2-+\3\2\2\2./\b\3\3\2/\6\3\2\2\2\60\64\6\4\4\2\61\63\5\33\16\2\62\61"+
		"\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\659\3\2\2\2\66\64\3"+
		"\2\2\2\67:\5\33\16\28:\7<\2\29\67\3\2\2\298\3\2\2\2:>\3\2\2\2;=\5\33\16"+
		"\2<;\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2\2AB\b\4\4"+
		"\2B\b\3\2\2\2CD\7%\2\2D\n\3\2\2\2EF\6\6\5\2FG\5\t\5\2GL\5\31\r\2HK\5\31"+
		"\r\2IK\t\2\2\2JH\3\2\2\2JI\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2M\f\3"+
		"\2\2\2NL\3\2\2\2OP\7*\2\2P\16\3\2\2\2QR\7+\2\2R\20\3\2\2\2SU\6\t\6\2T"+
		"V\n\3\2\2UT\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2\2X\22\3\2\2\2YZ\6\n\7"+
		"\2Z[\7~\2\2[\\\b\n\5\2\\\24\3\2\2\2]^\6\13\b\2^_\t\2\2\2_`\t\2\2\2`a\t"+
		"\2\2\2ab\t\2\2\2bc\7/\2\2cd\t\2\2\2de\t\2\2\2ef\7/\2\2fg\t\2\2\2gh\t\2"+
		"\2\2hi\7V\2\2ij\t\2\2\2jk\t\2\2\2kl\7<\2\2lm\t\2\2\2mn\t\2\2\2no\7<\2"+
		"\2op\t\2\2\2pq\t\2\2\2qr\7\60\2\2rs\t\2\2\2st\t\2\2\2tu\t\2\2\2uv\t\2"+
		"\2\2vw\t\2\2\2wx\t\2\2\2xy\t\2\2\2yz\7\\\2\2z\26\3\2\2\2{}\6\f\t\2|~\n"+
		"\4\2\2}|\3\2\2\2~\177\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\30"+
		"\3\2\2\2\u0081\u0082\t\5\2\2\u0082\32\3\2\2\2\u0083\u0084\t\4\2\2\u0084"+
		"\34\3\2\2\2\u0085\u0086\6\17\n\2\u0086\u0087\5\33\16\2\u0087\u0088\3\2"+
		"\2\2\u0088\u0089\b\17\6\2\u0089\36\3\2\2\2\f\2)+\649>JLW\177";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}