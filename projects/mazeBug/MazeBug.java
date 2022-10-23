/**
* @author w
* @version create time: 2022年10月22日 下午3:24:04
*/

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 * 
 * @author wh
 *
 */
public class MazeBug extends Bug {
	
	class MazeBugMod {
		private int[] count = {1, 1, 1, 1};
		
		MazeBugMod(){}
		
		public void chooseDir(int dir) {
			count[dir] += 1;
		}
		
		public void deleteDir(int dir) {
			count[dir] -= 1;
		}
		
		public int getDir(int dir) {
			return count[dir];
		}
	}
	
	private Location next;
	private Location last;
	private boolean isEnd = false;
	private Stack<Location> crossLocation = new Stack<Location>();
	private Integer stepCount = 0;
	// flag shows that if the step count pane has shown or not
	private boolean flag = false;
	private Location originLocation = null;
	private MazeBugMod tracker;
	
	
	
	/**
	 * Initialize the MazeBug to the original place (0, 0)
	 */
	public MazeBug() {
		this.last = new Location(0, 0);
		this.tracker = new MazeBugMod();
	}
	
	public void act() {
		boolean willMove = canMove();
		if(isEnd) {
			if(!flag) {
				String msg = stepCount.toString() + "steps";
				JOptionPane.showMessageDialog(null, msg);
				flag = true;
			}
		} else if (willMove) {
			last = getLocation();
			crossLocation.push(last);
			tracker.chooseDir(getLocation().getDirectionToward(next) / 90);
			move();
			this.stepCount++;
		} else {
			back();
		}
	}
	
	/**
	 * Check the valid place near the current location.
	 * If the destination is near the current location, set isEnd=true.
	 * @param loc current location of the MazeBug
	 * @return the valid locations near by.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if(gr == null) {
			return null;
		}
		ArrayList<Location> valid = new ArrayList<Location>();
		
		Location location = getLocation();
		int[] dir = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
		
		for(int direction : dir) {
			Location tmp = location.getAdjacentLocation(direction);
			if(gr.isValid(tmp)) {
				Actor actor = gr.get(tmp);
				if(actor == null) {
					valid.add(tmp);
				} else if(actor instanceof Rock && actor.getColor().equals(Color.RED)) {
					this.isEnd = true;
					valid.clear();
					return valid;
				}
			}
		}
		return valid;
	}
	
	/**
	 * Check if the bug can move.
	 * If the Bug can move, then choose the most selected direction to move.
	 * @return if the bug can move.
	 */
	public boolean canMove() {
		if(originLocation == null) {
			originLocation = getLocation();
		}
		if(getValid(originLocation).size() == 0) {
			return false;
		}
		Grid<Actor> gr = getGrid();
		if(gr == null) {
			return false;
		}
		Location location = getLocation();
		ArrayList<Location> validLocation = getValid(location);
		if(validLocation.size() > 0) {
			int maxWeight = -1;
			Location tmp = null;
			for(Location loc : validLocation) {
				if(tracker.getDir(location.getDirectionToward(loc) / 90) > maxWeight) {
					maxWeight = tracker.getDir(location.getDirectionToward(loc) / 90);
					tmp = loc;
				}
			}
			this.next = tmp;
			return true;
		}
		return false;
	}
	
	public void move() {
		Grid<Actor> gr = getGrid();
		if(gr == null) {
			return;
		}
		Location loc = getLocation();
		if(gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
	
	/**
	 * If go to the wrong direction and get stuck, then rollback.
	 */
	private void back() {
		if(this.crossLocation.size() > 0) {
			next = this.crossLocation.pop();
		}
		tracker.deleteDir((getLocation().getDirectionToward(next) / 90 + 2) % 4);
		move();
		stepCount++;
	}
}
