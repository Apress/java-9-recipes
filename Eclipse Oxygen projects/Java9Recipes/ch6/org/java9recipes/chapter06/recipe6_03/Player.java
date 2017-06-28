
package org.java9recipes.chapter06.recipe6_03;

/**
 * Recipe 6-3
 * 
 * @author Juneau
 * @author anatolij kosorukov
 */
public class Player {

	public static int compareByGoal(Player a, Player b) {
		int eval;
		if (a.getGoals() > b.getGoals()) {
			eval = 1;
		} else if (a.getGoals() < b.getGoals()) {
			eval = -1;
		} else {
			eval = 0;
		}
		return eval;
	}

	private String firstName = null;
	private String lastName = null;
	private String position = null;
	private int status = -1;

	private int goals;

	public Player() {

	}

	public Player(String position, int status) {
		this.position = position;
		this.status = status;
	}

	public Player(String position, int status, String first, String last) {
		this.position = position;
		this.status = status;
		this.firstName = first;
		this.lastName = last;
	}

	public String findPlayerStatus(int status) {
		String returnValue = null;

		switch (status) {
		case 0:
			returnValue = "ACTIVE";
		case 1:
			returnValue = "INACTIVE";
		case 2:
			returnValue = "INJURY";
		default:
			returnValue = "ON_BENCH";
		}

		return returnValue;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the goals
	 */
	public int getGoals() {
		return goals;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	public String playerString() {
		return getFirstName() + " " + getLastName() + " - " + getPosition();
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param goals
	 *            the goals to set
	 */
	public void setGoals(int goals) {
		this.goals = goals;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return this.playerString();
	}
}
