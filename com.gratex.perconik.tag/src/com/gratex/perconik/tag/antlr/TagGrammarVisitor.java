// Generated from TagGrammar.g4 by ANTLR 4.1
package com.gratex.perconik.tag.antlr;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TagGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TagGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TagGrammarParser#endTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndTag(@NotNull TagGrammarParser.EndTagContext ctx);

	/**
	 * Visit a parse tree produced by {@link TagGrammarParser#tagType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagType(@NotNull TagGrammarParser.TagTypeContext ctx);

	/**
	 * Visit a parse tree produced by {@link TagGrammarParser#tagBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagBody(@NotNull TagGrammarParser.TagBodyContext ctx);

	/**
	 * Visit a parse tree produced by {@link TagGrammarParser#tag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag(@NotNull TagGrammarParser.TagContext ctx);

	/**
	 * Visit a parse tree produced by {@link TagGrammarParser#att}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtt(@NotNull TagGrammarParser.AttContext ctx);

	/**
	 * Visit a parse tree produced by {@link TagGrammarParser#endTagSign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndTagSign(@NotNull TagGrammarParser.EndTagSignContext ctx);

	/**
	 * Visit a parse tree produced by {@link TagGrammarParser#userName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserName(@NotNull TagGrammarParser.UserNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link TagGrammarParser#freeText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFreeText(@NotNull TagGrammarParser.FreeTextContext ctx);

	/**
	 * Visit a parse tree produced by {@link TagGrammarParser#dateTimeUtc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDateTimeUtc(@NotNull TagGrammarParser.DateTimeUtcContext ctx);
}