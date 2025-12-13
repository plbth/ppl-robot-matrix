// Generated from src/main/resources/Robot.g4 by ANTLR 4.9.2
package com.group.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RobotParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, INT=13, STRING=14, WS=15;
	public static final int
		RULE_prog = 0, RULE_stmt = 1, RULE_block = 2, RULE_dir = 3, RULE_rotate = 4, 
		RULE_distance = 5, RULE_count = 6, RULE_color = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "stmt", "block", "dir", "rotate", "distance", "count", "color"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'BEGIN'", "'END'", "'SET_PEN'", "'MOVE'", "'TURN'", "'LOOP'", 
			"'{'", "'}'", "'FORWARD'", "'BACK'", "'LEFT'", "'RIGHT'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "INT", "STRING", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Robot.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RobotParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RobotVisitor ) return ((RobotVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(T__0);
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) {
				{
				{
				setState(17);
				stmt();
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
			match(T__1);
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

	public static class StmtContext extends ParserRuleContext {
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
	 
		public StmtContext() { }
		public void copyFrom(StmtContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class LoopCmdContext extends StmtContext {
		public CountContext count() {
			return getRuleContext(CountContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public LoopCmdContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RobotVisitor ) return ((RobotVisitor<? extends T>)visitor).visitLoopCmd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TurnCmdContext extends StmtContext {
		public RotateContext rotate() {
			return getRuleContext(RotateContext.class,0);
		}
		public TurnCmdContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RobotVisitor ) return ((RobotVisitor<? extends T>)visitor).visitTurnCmd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PenCmdContext extends StmtContext {
		public ColorContext color() {
			return getRuleContext(ColorContext.class,0);
		}
		public PenCmdContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RobotVisitor ) return ((RobotVisitor<? extends T>)visitor).visitPenCmd(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MoveCmdContext extends StmtContext {
		public DirContext dir() {
			return getRuleContext(DirContext.class,0);
		}
		public DistanceContext distance() {
			return getRuleContext(DistanceContext.class,0);
		}
		public MoveCmdContext(StmtContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RobotVisitor ) return ((RobotVisitor<? extends T>)visitor).visitMoveCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stmt);
		try {
			setState(37);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				_localctx = new PenCmdContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(25);
				match(T__2);
				setState(26);
				color();
				}
				break;
			case T__3:
				_localctx = new MoveCmdContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				match(T__3);
				setState(28);
				dir();
				setState(29);
				distance();
				}
				break;
			case T__4:
				_localctx = new TurnCmdContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(31);
				match(T__4);
				setState(32);
				rotate();
				}
				break;
			case T__5:
				_localctx = new LoopCmdContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(33);
				match(T__5);
				setState(34);
				count();
				setState(35);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class BlockContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RobotVisitor ) return ((RobotVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(T__6);
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) {
				{
				{
				setState(40);
				stmt();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46);
			match(T__7);
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

	public static class DirContext extends ParserRuleContext {
		public DirContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dir; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RobotVisitor ) return ((RobotVisitor<? extends T>)visitor).visitDir(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirContext dir() throws RecognitionException {
		DirContext _localctx = new DirContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_dir);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			_la = _input.LA(1);
			if ( !(_la==T__8 || _la==T__9) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class RotateContext extends ParserRuleContext {
		public RotateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rotate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RobotVisitor ) return ((RobotVisitor<? extends T>)visitor).visitRotate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RotateContext rotate() throws RecognitionException {
		RotateContext _localctx = new RotateContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_rotate);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			_la = _input.LA(1);
			if ( !(_la==T__10 || _la==T__11) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class DistanceContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(RobotParser.INT, 0); }
		public DistanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_distance; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RobotVisitor ) return ((RobotVisitor<? extends T>)visitor).visitDistance(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DistanceContext distance() throws RecognitionException {
		DistanceContext _localctx = new DistanceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_distance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(INT);
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

	public static class CountContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(RobotParser.INT, 0); }
		public CountContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_count; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RobotVisitor ) return ((RobotVisitor<? extends T>)visitor).visitCount(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CountContext count() throws RecognitionException {
		CountContext _localctx = new CountContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_count);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(INT);
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

	public static class ColorContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(RobotParser.STRING, 0); }
		public ColorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_color; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RobotVisitor ) return ((RobotVisitor<? extends T>)visitor).visitColor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColorContext color() throws RecognitionException {
		ColorContext _localctx = new ColorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_color);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(STRING);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21=\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\7\2\25\n\2"+
		"\f\2\16\2\30\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\5\3(\n\3\3\4\3\4\7\4,\n\4\f\4\16\4/\13\4\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\4\3\2\13\f\3\2"+
		"\r\16\29\2\22\3\2\2\2\4\'\3\2\2\2\6)\3\2\2\2\b\62\3\2\2\2\n\64\3\2\2\2"+
		"\f\66\3\2\2\2\168\3\2\2\2\20:\3\2\2\2\22\26\7\3\2\2\23\25\5\4\3\2\24\23"+
		"\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2\27\31\3\2\2\2\30\26"+
		"\3\2\2\2\31\32\7\4\2\2\32\3\3\2\2\2\33\34\7\5\2\2\34(\5\20\t\2\35\36\7"+
		"\6\2\2\36\37\5\b\5\2\37 \5\f\7\2 (\3\2\2\2!\"\7\7\2\2\"(\5\n\6\2#$\7\b"+
		"\2\2$%\5\16\b\2%&\5\6\4\2&(\3\2\2\2\'\33\3\2\2\2\'\35\3\2\2\2\'!\3\2\2"+
		"\2\'#\3\2\2\2(\5\3\2\2\2)-\7\t\2\2*,\5\4\3\2+*\3\2\2\2,/\3\2\2\2-+\3\2"+
		"\2\2-.\3\2\2\2.\60\3\2\2\2/-\3\2\2\2\60\61\7\n\2\2\61\7\3\2\2\2\62\63"+
		"\t\2\2\2\63\t\3\2\2\2\64\65\t\3\2\2\65\13\3\2\2\2\66\67\7\17\2\2\67\r"+
		"\3\2\2\289\7\17\2\29\17\3\2\2\2:;\7\20\2\2;\21\3\2\2\2\5\26\'-";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}