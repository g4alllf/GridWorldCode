import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import java.util.ArrayList;

public class RockHound extends Critter{
	public void processActors(ArrayList<Actor> actors) {
		for(Actor t : actors) {
			if((t instanceof Rock)) t.removeSelfFromGrid();
		}
	}
}
