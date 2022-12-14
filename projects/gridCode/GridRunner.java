/**
* @author w
* @version create time: 2022年10月13日 下午8:28:35
*/
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class GridRunner {
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		world.addGridClass("SparseBoundedGrid");
		world.addGridClass("SparseBoundedGrid2");
		world.addGridClass("SparseBoundedGrid3");
		world.addGridClass("UnboundedGrid2");
		world.add(new Location(2, 2), new Critter());
		world.show();
	}
}
