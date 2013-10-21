// Generated from TagGrammar.g4 by ANTLR 4.1
package conmark.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TagGrammarParser}.
 */
public interface TagGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TagGrammarParser#endTag}.
	 * @param ctx the parse tree
	 */
	void enterEndTag(@NotNull TagGrammarParser.EndTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TagGrammarParser#endTag}.
	 * @param ctx the parse tree
	 */
	void exitEndTag(@NotNull TagGrammarParser.EndTagContext ctx);

	/**
	 * Enter a parse tree produced by {@link TagGrammarParser#tagType}.
	 * @param ctx the parse tree
	 */
	void enterTagType(@NotNull TagGrammarParser.TagTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TagGrammarParser#tagType}.
	 * @param ctx the parse tree
	 */
	void exitTagType(@NotNull TagGrammarParser.TagTypeContext ctx);

	/**
	 * Enter a parse tree produced by {@link TagGrammarParser#tagBody}.
	 * @param ctx the parse tree
	 */
	void enterTagBody(@NotNull TagGrammarParser.TagBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link TagGrammarParser#tagBody}.
	 * @param ctx the parse tree
	 */
	void exitTagBody(@NotNull TagGrammarParser.TagBodyContext ctx);

	/**
	 * Enter a parse tree produced by {@link TagGrammarParser#tag}.
	 * @param ctx the parse tree
	 */
	void enterTag(@NotNull TagGrammarParser.TagContext ctx);
	/**
	 * Exit a parse tree produced by {@link TagGrammarParser#tag}.
	 * @param ctx the parse tree
	 */
	void exitTag(@NotNull TagGrammarParser.TagContext ctx);

	/**
	 * Enter a parse tree produced by {@link TagGrammarParser#att}.
	 * @param ctx the parse tree
	 */
	void enterAtt(@NotNull TagGrammarParser.AttContext ctx);
	/**
	 * Exit a parse tree produced by {@link TagGrammarParser#att}.
	 * @param ctx the parse tree
	 */
	void exitAtt(@NotNull TagGrammarParser.AttContext ctx);

	/**
	 * Enter a parse tree produced by {@link TagGrammarParser#endTagSign}.
	 * @param ctx the parse tree
	 */
	void enterEndTagSign(@NotNull TagGrammarParser.EndTagSignContext ctx);
	/**
	 * Exit a parse tree produced by {@link TagGrammarParser#endTagSign}.
	 * @param ctx the parse tree
	 */
	void exitEndTagSign(@NotNull TagGrammarParser.EndTagSignContext ctx);

	/**
	 * Enter a parse tree produced by {@link TagGrammarParser#userName}.
	 * @param ctx the parse tree
	 */
	void enterUserName(@NotNull TagGrammarParser.UserNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TagGrammarParser#userName}.
	 * @param ctx the parse tree
	 */
	void exitUserName(@NotNull TagGrammarParser.UserNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link TagGrammarParser#freeText}.
	 * @param ctx the parse tree
	 */
	void enterFreeText(@NotNull TagGrammarParser.FreeTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link TagGrammarParser#freeText}.
	 * @param ctx the parse tree
	 */
	void exitFreeText(@NotNull TagGrammarParser.FreeTextContext ctx);

	/**
	 * Enter a parse tree produced by {@link TagGrammarParser#dateTimeUtc}.
	 * @param ctx the parse tree
	 */
	void enterDateTimeUtc(@NotNull TagGrammarParser.DateTimeUtcContext ctx);
	/**
	 * Exit a parse tree produced by {@link TagGrammarParser#dateTimeUtc}.
	 * @param ctx the parse tree
	 */
	void exitDateTimeUtc(@NotNull TagGrammarParser.DateTimeUtcContext ctx);
}