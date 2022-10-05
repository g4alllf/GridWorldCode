import info.gridworld.actor.Actor;
import java.util.ArrayList;

public class ChameleonKid extends ChameleonCritter{
	
	public boolean isValid(int dir) {
		if(getGrid().isValid(getLocation().getAdjacentLocation(getDirection()+dir))) {
			return true;	
		}
		return false;
	}

	public ArrayList<Actor> getActors(){
		ArrayList<Actor> neighbors = new ArrayList<Actor>();
		// check the front
		if(isValid(0)) {
			Actor t = getGrid().get(getLocation().getAdjacentLocation(getDirection()));
			if(t!=null) {
				neighbors.add(t);
			}
		}
		// check the behind
		if(isValid(180)) {
			Actor t = getGrid().get(getLocation().getAdjacentLocation(getDirection()+180));
			if(t!=null) {
				neighbors.add(t);
			}
		}
		return neighbors;
	}
	
}