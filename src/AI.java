/**
 * Class for the ALPHA BETA AI. 
 * Includes 4 modes of easy(0), medium(1), hard(2) and simulation(otherwise).
 */

import java.util.ArrayList;
import java.util.Random;

public class AI implements Player {
	private String name;
	private AlphaBetaHeuristic hAlgo;
	private int mode;
	private int depth;
	private int nextMove;

	/**
	 * Construct a Alpha Beta AI with the given name and mode of the AI
	 * @param name AI name
	 * @param mode AI mode, three levels from 0 to 2 in increasing difficulty,
	 *            otherwise for simulation specifically
	 */
	public AI(String name, int mode) {
		this.name = name;
		this.mode = mode;
	}

	@Override
	public int decideMove(GameState currState) {
		long startTime = 0; // starting timer for simulation delay

		// set difficulty
		switch (mode) {
		case 0:
			// easy
			hAlgo = new H0();
			depth = 5;
			break;
		case 1:
			// medium
			hAlgo = new H1();
			depth = 7;
			break;
		case 2:
			// hard
			hAlgo = new H2();
			depth = 9;
			break;
		default:
			// simulation
			hAlgo = new H0();
			depth = 3;
			startTime = System.currentTimeMillis();
		}

		alphabeta(currState, depth, Integer.MIN_VALUE, Integer.MAX_VALUE);

		// delay for simulation moves, avoiding rapid changing of animation
		if (mode == -1) {
			long endTime = System.currentTimeMillis();
			long delay = 500 - (endTime - startTime);

			if (delay > 0)
				try {
					Thread.sleep(delay);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
		}

		return nextMove;
	}

	/**
	 * Alpha Beta Algorithm. Including the basic heuristic of winning/losing.
	 * 
	 * @param state
	 *            GameState
	 * @param depth
	 *            depth limit
	 * @param alpha
	 * @param beta
	 * @return
	 */
	private int alphabeta(GameState state, int depth, int alpha, int beta) {
		// check win/lose
		if (state.getTurn() > 42) {
			return state.getTurn() - 42;
		}
		if (state.getWinner() == this) {
			// depth return a higher score for closer state, which make sure
			// always take the blocking or winning move for next turn once it is
			// available.
			return 1000 << depth;
		}
		if (state.getWinner() == state.getOtherPlayer()) {
			return -1000 << depth;
		}
		// check depth limit
		if (depth == 0) {
			return hAlgo.h(state);
		}

		ArrayList<Integer> moves = getRandomPositions();
		if (state.getCurrPlayer() == this) {
			int max = Integer.MIN_VALUE;
			for (int move : moves) {
				if (!state.isValidMove(move)) {
					continue;
				}
				GameState cloneState = state.clone();
				doNextMove(cloneState, move);

				int v = alphabeta(cloneState, depth - 1, alpha, beta);

				if (v > max) {
					max = v;
					if (this.depth == depth) {
						nextMove = move;
					}
				}
				if (alpha < max) {
					alpha = max;
				}
				if (alpha >= beta) {
					break;
				}
			}
			return alpha;
		} else {
			int min = Integer.MAX_VALUE;
			for (int move : moves) {
				if (!state.isValidMove(move)) {
					continue;
				}
				GameState cloneState = state.clone();
				doNextMove(cloneState, move);

				int v = alphabeta(cloneState, depth - 1, alpha, beta);

				if (min > v) {
					min = v;
				}
				if (beta > min) {
					beta = min;
				}
				if (alpha >= beta) {
					break;
				}
			}
			return beta;
		}
	}

	/**
	 * Execute a move to a state like a real game for simulation.
	 * 
	 * @param state
	 *            game state to be execute
	 * @param move
	 *            requested move
	 */
	private void doNextMove(GameState state, int move) {
		state.runNextMove(move);
		state.setCurrPlayer(state.getOtherPlayer());
		state.incTurn();
		state.checkGameEnd();
	}

	/**
	 * Use for generate successors of a state.
	 * 
	 * @return a random ArrayList of 0 to 6
	 */
	private ArrayList<Integer> getRandomPositions() {
		ArrayList<Integer> randList = new ArrayList<Integer>();
		Random rand = new Random();
		int i = rand.nextInt(7);
		while (randList.size() < 7) {
			while (randList.contains(i)) {
				i = rand.nextInt(7);
			}
			randList.add(i);
		}
		return randList;
	}

	/**
	 * Getter for name.
	 * 
	 * @return AI name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for AI mode.
	 * 
	 * @return AI mode
	 */
	public int getMode() {
		return mode;
	}
}
