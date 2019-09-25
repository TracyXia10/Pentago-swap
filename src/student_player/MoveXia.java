package student_player;

import pentago_swap.PentagoMove;

public class MoveXia {
	private double value;
	private PentagoMove move;
	

	public MoveXia(PentagoMove move, double value) {
		this.move = move;
		this.value = value;
	}
	
	public PentagoMove getMove() {
		return move;
	}
	public double getValue() {
		return value;
	}
}
