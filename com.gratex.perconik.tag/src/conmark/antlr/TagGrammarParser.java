// Generated from TagGrammar.g4 by ANTLR 4.1
package conmark.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TagGrammarParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		EndTagSign=1, TagType=2, TagTypeSeparator=3, Identifier=4, BracetL=5, 
		BracetR=6, FreeText=7, BodyIdSeparator=8, DateTimeUtc=9, UserName=10, 
		IgnoreWs=11;
	public static final String[] tokenNames = {
		"<INVALID>", "EndTagSign", "TagType", "TagTypeSeparator", "Identifier", 
		"'('", "')'", "FreeText", "BodyIdSeparator", "DateTimeUtc", "UserName", 
		"IgnoreWs"
	};
	public static final int
		RULE_tag = 0, RULE_endTag = 1, RULE_tagBody = 2, RULE_freeText = 3, RULE_att = 4, 
		RULE_tagType = 5, RULE_userName = 6, RULE_dateTimeUtc = 7, RULE_endTagSign = 8;
	public static final String[] ruleNames = {
		"tag", "endTag", "tagBody", "freeText", "att", "tagType", "userName", 
		"dateTimeUtc", "endTagSign"
	};

	@Override
	public String getGrammarFileName() { return "TagGrammar.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }


	    int mode = 0;
//		protected int EOF = Eof; //https://groups.google.com/forum/#!topic/antlr-discussion/-Ko4w4qlDZM

	public TagGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TagContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(TagGrammarParser.EOF, 0); }
		public UserNameContext userName() {
			return getRuleContext(UserNameContext.class,0);
		}
		public TagBodyContext tagBody() {
			return getRuleContext(TagBodyContext.class,0);
		}
		public TagTypeContext tagType() {
			return getRuleContext(TagTypeContext.class,0);
		}
		public TerminalNode BodyIdSeparator() { return getToken(TagGrammarParser.BodyIdSeparator, 0); }
		public DateTimeUtcContext dateTimeUtc() {
			return getRuleContext(DateTimeUtcContext.class,0);
		}
		public TerminalNode TagTypeSeparator() { return getToken(TagGrammarParser.TagTypeSeparator, 0); }
		public TagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).enterTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).exitTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TagGrammarVisitor ) return ((TagGrammarVisitor<? extends T>)visitor).visitTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TagContext tag() throws RecognitionException {
		TagContext _localctx = new TagContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_tag);
		try {
			setState(33);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(18); tagType();
				setState(19); match(TagTypeSeparator);
				setState(20); tagBody();
				setState(21); match(BodyIdSeparator);
				setState(22); userName();
				setState(23); dateTimeUtc();
				setState(24); match(EOF);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(26); tagType();
				setState(27); match(TagTypeSeparator);
				setState(28); match(BodyIdSeparator);
				setState(29); userName();
				setState(30); dateTimeUtc();
				setState(31); match(EOF);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EndTagContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(TagGrammarParser.EOF, 0); }
		public UserNameContext userName() {
			return getRuleContext(UserNameContext.class,0);
		}
		public FreeTextContext freeText() {
			return getRuleContext(FreeTextContext.class,0);
		}
		public TerminalNode BodyIdSeparator() { return getToken(TagGrammarParser.BodyIdSeparator, 0); }
		public EndTagSignContext endTagSign() {
			return getRuleContext(EndTagSignContext.class,0);
		}
		public DateTimeUtcContext dateTimeUtc() {
			return getRuleContext(DateTimeUtcContext.class,0);
		}
		public EndTagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endTag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).enterEndTag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).exitEndTag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TagGrammarVisitor ) return ((TagGrammarVisitor<? extends T>)visitor).visitEndTag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndTagContext endTag() throws RecognitionException {
		EndTagContext _localctx = new EndTagContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_endTag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35); endTagSign();
			setState(37);
			_la = _input.LA(1);
			if (_la==FreeText) {
				{
				setState(36); freeText();
				}
			}

			setState(39); match(BodyIdSeparator);
			setState(40); userName();
			setState(41); dateTimeUtc();
			setState(42); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TagBodyContext extends ParserRuleContext {
		public AttContext att(int i) {
			return getRuleContext(AttContext.class,i);
		}
		public List<FreeTextContext> freeText() {
			return getRuleContexts(FreeTextContext.class);
		}
		public List<AttContext> att() {
			return getRuleContexts(AttContext.class);
		}
		public FreeTextContext freeText(int i) {
			return getRuleContext(FreeTextContext.class,i);
		}
		public TagBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tagBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).enterTagBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).exitTagBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TagGrammarVisitor ) return ((TagGrammarVisitor<? extends T>)visitor).visitTagBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TagBodyContext tagBody() throws RecognitionException {
		TagBodyContext _localctx = new TagBodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_tagBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(46);
				switch (_input.LA(1)) {
				case FreeText:
					{
					setState(44); freeText();
					}
					break;
				case Identifier:
					{
					setState(45); att();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(48); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Identifier || _la==FreeText );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FreeTextContext extends ParserRuleContext {
		public TerminalNode FreeText() { return getToken(TagGrammarParser.FreeText, 0); }
		public FreeTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_freeText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).enterFreeText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).exitFreeText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TagGrammarVisitor ) return ((TagGrammarVisitor<? extends T>)visitor).visitFreeText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FreeTextContext freeText() throws RecognitionException {
		FreeTextContext _localctx = new FreeTextContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_freeText);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50); match(FreeText);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(TagGrammarParser.Identifier, 0); }
		public TerminalNode FreeText() { return getToken(TagGrammarParser.FreeText, 0); }
		public TerminalNode BracetL() { return getToken(TagGrammarParser.BracetL, 0); }
		public TerminalNode BracetR() { return getToken(TagGrammarParser.BracetR, 0); }
		public AttContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_att; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).enterAtt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).exitAtt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TagGrammarVisitor ) return ((TagGrammarVisitor<? extends T>)visitor).visitAtt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttContext att() throws RecognitionException {
		AttContext _localctx = new AttContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_att);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52); match(Identifier);
			setState(56);
			_la = _input.LA(1);
			if (_la==BracetL) {
				{
				setState(53); match(BracetL);
				setState(54); match(FreeText);
				setState(55); match(BracetR);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TagTypeContext extends ParserRuleContext {
		public TerminalNode TagType() { return getToken(TagGrammarParser.TagType, 0); }
		public TagTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tagType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).enterTagType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).exitTagType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TagGrammarVisitor ) return ((TagGrammarVisitor<? extends T>)visitor).visitTagType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TagTypeContext tagType() throws RecognitionException {
		TagTypeContext _localctx = new TagTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_tagType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58); match(TagType);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UserNameContext extends ParserRuleContext {
		public TerminalNode UserName() { return getToken(TagGrammarParser.UserName, 0); }
		public UserNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_userName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).enterUserName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).exitUserName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TagGrammarVisitor ) return ((TagGrammarVisitor<? extends T>)visitor).visitUserName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UserNameContext userName() throws RecognitionException {
		UserNameContext _localctx = new UserNameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_userName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); match(UserName);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DateTimeUtcContext extends ParserRuleContext {
		public TerminalNode DateTimeUtc() { return getToken(TagGrammarParser.DateTimeUtc, 0); }
		public DateTimeUtcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dateTimeUtc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).enterDateTimeUtc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).exitDateTimeUtc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TagGrammarVisitor ) return ((TagGrammarVisitor<? extends T>)visitor).visitDateTimeUtc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DateTimeUtcContext dateTimeUtc() throws RecognitionException {
		DateTimeUtcContext _localctx = new DateTimeUtcContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_dateTimeUtc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); match(DateTimeUtc);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EndTagSignContext extends ParserRuleContext {
		public TerminalNode EndTagSign() { return getToken(TagGrammarParser.EndTagSign, 0); }
		public EndTagSignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endTagSign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).enterEndTagSign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TagGrammarListener ) ((TagGrammarListener)listener).exitEndTagSign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TagGrammarVisitor ) return ((TagGrammarVisitor<? extends T>)visitor).visitEndTagSign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndTagSignContext endTagSign() throws RecognitionException {
		EndTagSignContext _localctx = new EndTagSignContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_endTagSign);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64); match(EndTagSign);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\rE\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2$\n\2\3\3\3\3\5\3"+
		"(\n\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\6\4\61\n\4\r\4\16\4\62\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\5\6;\n\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\2\13\2\4\6\b\n"+
		"\f\16\20\22\2\2@\2#\3\2\2\2\4%\3\2\2\2\6\60\3\2\2\2\b\64\3\2\2\2\n\66"+
		"\3\2\2\2\f<\3\2\2\2\16>\3\2\2\2\20@\3\2\2\2\22B\3\2\2\2\24\25\5\f\7\2"+
		"\25\26\7\5\2\2\26\27\5\6\4\2\27\30\7\n\2\2\30\31\5\16\b\2\31\32\5\20\t"+
		"\2\32\33\7\2\2\3\33$\3\2\2\2\34\35\5\f\7\2\35\36\7\5\2\2\36\37\7\n\2\2"+
		"\37 \5\16\b\2 !\5\20\t\2!\"\7\2\2\3\"$\3\2\2\2#\24\3\2\2\2#\34\3\2\2\2"+
		"$\3\3\2\2\2%\'\5\22\n\2&(\5\b\5\2\'&\3\2\2\2\'(\3\2\2\2()\3\2\2\2)*\7"+
		"\n\2\2*+\5\16\b\2+,\5\20\t\2,-\7\2\2\3-\5\3\2\2\2.\61\5\b\5\2/\61\5\n"+
		"\6\2\60.\3\2\2\2\60/\3\2\2\2\61\62\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2"+
		"\63\7\3\2\2\2\64\65\7\t\2\2\65\t\3\2\2\2\66:\7\6\2\2\678\7\7\2\289\7\t"+
		"\2\29;\7\b\2\2:\67\3\2\2\2:;\3\2\2\2;\13\3\2\2\2<=\7\4\2\2=\r\3\2\2\2"+
		">?\7\f\2\2?\17\3\2\2\2@A\7\13\2\2A\21\3\2\2\2BC\7\3\2\2C\23\3\2\2\2\7"+
		"#\'\60\62:";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}