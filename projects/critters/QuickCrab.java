import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter{

	public ArrayList<Location> processMove(){
		ArrayList<Location> locs = new ArrayList<Location>();
		Grid<?> g = getGrid();
		Location loc = getLocation();
		Location left = loc.getAdjacentLocation(270);
		Location right = loc.getAdjacentLocation(90);
		if(g.isValid(left)&&(g.get(left)==null)) {
			left = left.getAdjacentLocation(270);
			if(g.isValid(left)&&(g.get(left)==null)) {
				locs.add(left);
			}
			
		}
		if(g.isValid(right)&&(g.get(right)==null)) {
			right = right.getAdjacentLocation(90);
			if(g.isValid(right)&&(g.get(right)==null)) {
				locs.add(right);
			}
			
		}
		return locs;
	}
	
	public void makeMove(Location loc) {
		if(loc.equals(getLocation())) {
			double r = Math.random();
			int angle;
			if(r<0.5) angle = Location.LEFT;
			else angle = Location.RIGHT;
			setDirection(getDirection()+angle);
		}else {
			super.makeMove(loc);
		}
	}
}
