// Generated from src/main/resources/Robot.g4 by ANTLR 4.9.2
package com.group.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RobotParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RobotVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RobotParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(RobotParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PenCmd}
	 * labeled alternative in {@link RobotParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPenCmd(RobotParser.PenCmdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MoveCmd}
	 * labeled alternative in {@link RobotParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoveCmd(RobotParser.MoveCmdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TurnCmd}
	 * labeled alternative in {@link RobotParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTurnCmd(RobotParser.TurnCmdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LoopCmd}
	 * labeled alternative in {@link RobotParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopCmd(RobotParser.LoopCmdContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(RobotParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotParser#dir}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDir(RobotParser.DirContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotParser#rotate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRotate(RobotParser.RotateContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotParser#distance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistance(RobotParser.DistanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotParser#count}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCount(RobotParser.CountContext ctx);
	/**
	 * Visit a parse tree produced by {@link RobotParser#color}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColor(RobotParser.ColorContext ctx);
}