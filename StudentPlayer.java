package student_player;


import boardgame.Move;

import pentago_swap.PentagoPlayer;
import pentago_swap.PentagoBoardState;
import pentago_swap.PentagoMove;
import pentago_swap.PentagoBoardState.Quadrant;


/** A player file submitted by a student. */
public class StudentPlayer extends PentagoPlayer {

    /**
     * You must modify this constructor to return your student number. This is
     * important, because this is what the code that runs the competition uses to
     * associate you with your agent. The constructor should do nothing else.
     */
    public StudentPlayer() {
        super("260719721");
    }

    /**
     * This is the primary method that you need to implement. The ``boardState``
     * object contains the current state of the game, which your agent must use to
     * make decisions.
     */
    public Move chooseMove(PentagoBoardState boardState) {
        // You probably will make separate functions in MyTools.
        // For example, maybe you'll need to load some pre-processed best opening
        // strategies...
    	
        PentagoBoardState cloneBoard = (PentagoBoardState) boardState.clone();
       
        
        Move myMove = null;
        int myPlayer = boardState.getTurnPlayer();
        if(boardState.getTurnNumber() == 0 || boardState.getTurnNumber() == 1) {
        	//For the first two rounds.
        	//Place at the center of each quadrant.
        	if(boardState.getPieceAt(1, 1) == null) {//TL center is null.
        		myMove = new PentagoMove(1,1,Quadrant.TR,Quadrant.BL,myPlayer);
        	}else if(boardState.getPieceAt(4, 1) == null) {//TR center is null.
        		myMove = new PentagoMove(4,1,Quadrant.TL,Quadrant.TR,myPlayer);
        	}else if(boardState.getPieceAt(1, 4) == null) {//BL center is null
        		myMove = new PentagoMove(1,4,Quadrant.TL,Quadrant.BL,myPlayer);
        	}else {myMove = new PentagoMove(4,4,Quadrant.TL,Quadrant.BR,myPlayer);}
        }
        
        else {//Do a-b pruning.
        myMove = MyTools.max_value(cloneBoard, Integer.MIN_VALUE, Integer.MAX_VALUE, 3).getMove();
           }
        
        // Return your move to be processed by the server.
        return myMove;
    }
}







