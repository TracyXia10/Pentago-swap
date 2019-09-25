package student_player;

import boardgame.Board;

import pentago_swap.PentagoBoardState;
import pentago_swap.PentagoMove;
import pentago_swap.PentagoBoardState.Piece;

public class MyTools {
	public static double score(PentagoBoardState boardState) {
    	double v = 0;
    	//tempWhite and tempBlack to store all of the current pieces.
    	//Initialize the array, set all of the value to -1.
    	int white[][] = new int[16][2];
    	int black[][] = new int[16][2];
    	for(int i=0;i<16;i++) {
    		for(int j=0;j<2;j++) {
    			white[i][j] = -1;
    			black[i][j] = -1;
    		}
    	}
    	
    	if(boardState.gameOver() == true) {//Game is over. 
    		if(boardState.getWinner() ==  boardState.getTurnPlayer()) {//I am the winner.
    			v = 100000;
    		}else {v = -100000;}//I am the loser.		
    	}
    	
    	else {//Game is not over.
    		//Loop through the board and store all of the Pieces.
    		for(int i=0;i<6;i++) {
    			for(int j=0;j<6;j++) {
    				//When there is a piece.
    				if(boardState.getPieceAt(i, j) != null) {
    					//If the piece is white.
    					if(boardState.getPieceAt(i, j) == Piece.WHITE) {
    						for(int c=0;c<16;c++) {
    							if(white[c][0] == -1) {//It is empty
    								white[c][0] = i;
    								white[c][1] = j;
    							}
    						}
    					}
    					//If the piece is black.
    					if(boardState.getPieceAt(i, j) == Piece.BLACK) {
    						for(int c=0;c<16;c++) {
    							if(black[c][0] == -1) {//It is empty
    								black[c][0] = i;
    								black[c][1] = j;
    							}
    						}
    					}
    				}
    			}
    		}
    		//We have all of the pieces. Now do the calculation.
    		int whitePoint = 0;
    		int blackPoint = 0;
    		for(int i=0;i<16;i++) {
    			for(int j=0;j<16;j++) {
    				if(white[i][0] != -1 && white[j][0] != -1) {
    					whitePoint = Math.abs(white[i][0]-white[j][0])
    							+Math.abs(white[i][1]-white[j][1])+whitePoint;
    				}
    				if(black[i][0] != -1 && black[j][0] != -1) {
    					blackPoint = Math.abs(black[i][0]-black[j][0])
    							+Math.abs(black[i][1]-black[j][1])+blackPoint;
    				}
    			}
    		}
    		    		
    		if(boardState.getTurnPlayer() == 0) {//I am white.
    			v = blackPoint - whitePoint;
    		}else {//I am black.
    			v = whitePoint - blackPoint ;
    		}
    	}
    	return v;
    }
	
    
    public static MoveXia max_value(PentagoBoardState s, double alpha, double beta, int depth){
    	MoveXia temp = null;;
    	depth -=1;
    	
    	if (depth == 0 || s.getWinner() != Board.NOBODY)
			return new MoveXia(null,  score(s));
    	//Initialize the value to minimum.
		double value = Integer.MIN_VALUE;	
		
		for (PentagoMove move : s.getAllLegalMoves()){
			PentagoBoardState cloned1 = (PentagoBoardState) s.clone();
			cloned1.processMove(move);
		
			MoveXia temp1 = min_value(cloned1, alpha, beta, depth);
			if(temp1.getValue() > value) {
				value = temp1.getValue();
				temp = new MoveXia(move, value);
			}
			if (value>=beta) return new MoveXia(move, value);
			alpha = Math.max(alpha, value);
		}
		return temp;
	}
    
    private static MoveXia min_value(PentagoBoardState s, double alpha, double beta,  int depth){
    	MoveXia temp = null;;
		depth -=1;
		
		if (depth == 0 || s.getWinner() != Board.NOBODY)
			return new MoveXia(null,score(s));
		//Initialize the value to maximum.
		double value = Integer.MAX_VALUE;
		
		for (PentagoMove move : s.getAllLegalMoves()){
			PentagoBoardState cloned2 = (PentagoBoardState) s.clone();
			cloned2.processMove(move);
		
			MoveXia temp1 = min_value(cloned2, alpha, beta, depth);
			if(temp1.getValue() < value) {
				value = temp1.getValue();
				temp = new MoveXia(move, value);
			}
			if (value<=alpha) return new MoveXia(move, value);
			beta = Math.min(beta, value);
		}
		return temp;
	}  
    
}