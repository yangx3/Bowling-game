import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Bowling {
	
	public static int totalPoint;
	public static List<Frame> frameList = new ArrayList<Frame>();
	
	//Main class of bowling class
	public static void main(String [] argu) {
		System.out.println("Welcome to Bowling System.");
		
		//generate Frame and assign them to list
		generateFrame();
		
		//initialize quit program flag.
		String flag = "";
		
		//welcome message and hit message, ask for input.
		System.out.println("Please enter any key to start the bowling. (Enter q to quit the system.)");
		Scanner scanObj = new Scanner(System.in);
		flag = scanObj.nextLine();
		
		//initialize frame.
		int frame = 0;
		
		//loop for round
		while(!flag.equals("q")) {
			if(frame < 10) {
				frame++;
				
				//score title.
				System.out.println("\n|FrameNumber|Roll 1|Roll 2|Total Score|");
				
				//for current frame, output only the current score.
				for(int i =0; i<frame; i++) {
					System.out.print("| Frame-"+ (i+1)+ " "+frameList.get(i).stringRollScore());
					
					//if in the current frame, score is hidden for strike and spare, waiting for next frame.
					if(i==frame-1) {
						if(!frameList.get(i).strike && !frameList.get(i).spare) {
							System.out.println(" "+frameList.get(i).getTotal() + " |");
						}
					}
					//output the score of the previous frame
					else {
						System.out.println(" "+frameList.get(i).getTotal() + " |");
					}
				}
			
			}
			//check for the 11's frame bonus. there are three situation: 10 frame is normal frame, strike or spare.
			else if(frame==10) {
				
				//if ten's round get bonus
				if(frameList.get(9).strike || frameList.get(9).spare) {
					System.out.println("\n|FrameNumber|Roll 1|Roll 2|Total Score|");
					for(int i =0; i<frame; i++) {
						System.out.print("| Frame-"+ (i+1)+" "+ frameList.get(i).stringRollScore());
						System.out.println(" "+frameList.get(i).getTotal() + " |");
					}
					//for strike, output two bonus
					if(frameList.get(9).strike) {
						System.out.println("Bonus| "+frameList.get(10).getFirstPoint() + " | " + frameList.get(10).getSecondPoint() + " |");
					}
					//for spare, output only one bonus.
					if(frameList.get(9).spare) {
						System.out.println("Bonus| "+frameList.get(10).getFirstPoint() + " | ");
					}
				}
				System.out.println("Game is finished.");
				System.out.println("Your Score is " + frameList.get(9).getTotal());
				
				frame = 0;
				break;
			}
			//ask for input to continue
			System.out.println("\nEnter any key to continue, or enter q to quit.");
			System.out.print("Continue?  ");
			flag=scanObj.nextLine();
				
		}
		//when game is over, or received q, quit.
		System.out.println("System quit! Have a nice day!");
		scanObj.close();
		
	}
	
	//random assign the score for first ball. It is between 0-10
	public static int RollFirstBall() {
		Random randRoll = new Random();
		return randRoll.nextInt(11);
	}
	
	//random assign the second ball, it is between the first ball and 10. it will be 0 if first ball is 10
	public static int RollSecondBall(int first) {
		Random randRoll = new Random();
		return randRoll.nextInt(11-first);
	}
	
	//in this method, 12 frames will be generated. only first 10 will have total grade and frame point. since the bonus is applied, 
	//it requires two more frames. 11 frame is for bonus of 10 frame, 12 frame is applied when 10 frame and 11 frame are strike.
	public static void generateFrame() {
		int[] firstRoll = new int[12];
		for(int i = 0; i< firstRoll.length; i++) {
			firstRoll[i] = RollFirstBall();
		}
		int[] secondRoll = new int[12];
		for(int i = 0; i<secondRoll.length; i++) {
			secondRoll[i] = RollSecondBall(firstRoll[i]);
		}
		
		//assign first and second roll
		for(int i = 0; i < 12; i++) {
			Frame temp = new Frame(i+1);
			temp.setFirst(firstRoll[i]);
			temp.setSecond(secondRoll[i]);
			frameList.add(temp);
		}
		
		//searching bonus and set bonus
		for(int i = 0; i< 10; i++) {
			String frameResult = frameList.get(i).startFrame();
			if(!frameList.get(i+1).strike) {
				frameList.get(i).getBonus(frameList.get(i+1).getFirstPoint(), frameList.get(i+1).getSecondPoint());
			}
			else {
				frameList.get(i).getBonus(frameList.get(i+1).getFirstPoint(), frameList.get(i+2).getFirstPoint());
			}
			frameList.get(i).setFramePoint();
			totalPoint += frameList.get(i).getFramePoint();
			frameList.get(i).setTotal(totalPoint);
			//System.out.println("Frame point: " +frameList.get(i).getFramePoint() + "\nTotal Point: "+ totalPoint);
			
			}
		}
	
	//method which used for testing before. it is no longer been used.
	public void rollFrame(int frameNo) {
		if(frameNo <=10 && frameNo>0) {
			System.out.println(frameList.get(frameNo-1).getFrame() +frameList.get(frameNo-1).stringRollScore() + " " + frameList.get(frameNo-1).getTotal() + " |");
		}
	}
	
}
