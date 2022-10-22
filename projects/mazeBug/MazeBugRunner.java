/**
* @author w
* @version create time: 2022年10月22日 下午3:59:19
*/
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.actor.Rock;
import java.awt.Color;

public class MazeBugRunner {
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		world.add(new Location(0, 0), new MazeBug());
		world.add(new Location(1, 3), new Rock());
		world.add(new Location(1, 0), new Rock());
		world.add(new Location(1, 0), new Rock());
		world.add(new Location(1, 1), new Rock());
		world.add(new Location(3, 2), new Rock());
		world.add(new Location(4, 4), new Rock());
		world.add(new Location(4, 3), new Rock());
		world.add(new Location(4, 5), new Rock());
		world.add(new Location(6, 5), new Rock());
		world.add(new Location(7, 4), new Rock());
		world.add(new Location(7, 3), new Rock());
		world.add(new Location(7, 5), new Rock());
		world.add(new Location(5, 5), new Rock());
		world.add(new Location(3, 0), new Rock());
		world.add(new Location(4, 2), new Rock());
		world.add(new Location(5, 2), new Rock());

		world.add(new Location(6, 4), new Rock(Color.RED));
		world.show();
	}
}
