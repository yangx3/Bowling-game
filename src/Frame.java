
import java.util.Random;

//this class is not efficient. It can be improved, some of the methods can be merged together.
//
public class Frame {
	//class variables
	//some of them are changed, or not-been-used during the TDD process.
	//to make the test easier, some of them are public value. They should not be public and should only be read through getValue method.
	private int frame;
	private int firstPoint;
	private int secondPoint;
	private int framePoint;
	private final int STRIKE = 10;
	private final int SPARE = 10;
	private int bouns;
	public boolean strike = false;
	public boolean spare = false;
	private int totalPoint;
	
	//const
	public Frame(int frame) {
		this.frame=frame;
		this.firstPoint=0;
		this.secondPoint=0;
		this.framePoint=0;
		this.bouns=0;
	}
	
	//get method
	public int getFrame() {
		return this.frame;
	}
	
	public int getFirstPoint() {
		return this.firstPoint;
	}
	
	public int getSecondPoint() {
		return this.secondPoint;
	}
	
	//check if strike
	public boolean isStrike(int point) {
		if(point == 10) {
			this.strike = true;
			return true;
		}
		return false;
	}
	
	//check if spare, only trigger when second ball is thrown
	public boolean isSpare(int firstPoint, int secondPoint) {
		if(firstPoint + secondPoint == 10) {
			this.spare=true;
			return true;
		}
		return false;
	}
	
	//set the total
	public void setTotal(int point) {
		this.totalPoint=point;
	}
	
	//get total
	public int getTotal() {
		return this.totalPoint;
	}
	
	//method is no longer been used
	public void strikeBonus(int ball1, int ball2) {
		this.bouns = ball1 + ball2;
	}
	
	//method is no longer been used
	public void spareBonus(int ball) {
		this.bouns = ball;
	}
	
	//this method add the frame Point with two ball and bonus. ##THIS SHOULD BE IMPROVED
	public void setFramePoint() {
		this.framePoint= this.firstPoint+this.secondPoint + this.bouns;// +framePoint;
	}
	
	public int getFramePoint() {
		return this.framePoint;
	}
	
	//start a frame, will trigger the spare or strike.
	//return String was designed for output the result of current frame
	public String startFrame() {
		if(isStrike(firstPoint)) {
			return "Strike";
		}
		if(isSpare(firstPoint, secondPoint)) {
			return "Spare";
		}
		return "Normal";
	}
	
	public void setFirst(int point) {
		this.firstPoint=point;
	}
	
	public void setSecond(int point) {
		this.secondPoint=point;
	}
	
	//get bonus will always received two ball, but only add them when bonus is applied.
	public void getBonus(int point1, int point2){
		if(this.strike) {
			this.bouns = point1+point2;
		}
		else if(this.spare) {
			this.bouns = point1;
		}
		
	}
	
	//set score with the rule of bowling, "-", "/" and "X"
	public String stringRollScore() {
		String firstRoll = "";
		String secondRoll ="";
		if(strike) {
			firstRoll+=" ";
			secondRoll+="X";
		}
		else if(spare) {
			firstRoll+=this.firstPoint;
			secondRoll+="/";
		}

		else {
			if(this.firstPoint!=0) {
				firstRoll+=this.firstPoint;
			}
			else {
				firstRoll+="-";
			}
			if(this.secondPoint!=0) {
				secondRoll+=this.secondPoint;
			}
			else {
				secondRoll+="-";
			}
		}
		
		return "| " + firstRoll + " | " + secondRoll + " |";
		
	}
	

}
