import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.util.ArrayList;

public class KingCrab extends CrabCritter{
	public void moveActor(Actor a, int ac, int ar, int kr, int kc) {
        // targetdir to set the direction to move
        // target is the location to move
        int targetdir;
        Location target;
        Grid<?> gr = getGrid();

        if (ar < kr) {
            if (ac < kc) {
                targetdir = Location.NORTHWEST;
            } else {
                targetdir = Location.NORTHEAST;
            }
        } else {
            if (ac < kc) {
                targetdir = Location.SOUTHWEST;
            } else {
                targetdir = Location.SOUTHEAST;
            }
        }

        target = a.getLocation().getAdjacentLocation(targetdir);
        if (gr.isValid(target)) {
            a.moveTo(target);
        } else {
            a.removeSelfFromGrid();
        }
    }
	

    public void processActors(ArrayList<Actor> actors) {
        int r1 = getLocation().getRow();
        int c1 = getLocation().getCol();
        Grid<?> gr = getGrid();

        for (Actor a : actors) {
            int r2 = a.getLocation().getRow();
            int c2 = a.getLocation().getCol();
            int rd = r2 > r1 ? (r2 - r1) : (r1 - r2);
            int cd = c2 > c1 ? (c2 - c1) : (c1 - c2);
            
            if (rd == 1 && cd == 1) {
                moveActor(a, c2, r2, r1, c1);
            } else {
                if (gr.isValid(a.getLocation().getAdjacentLocation(
                        getDirection()))) {
                    a.moveTo(a.getLocation()
                            .getAdjacentLocation(getDirection()));
                } else {
                    a.removeSelfFromGrid();
                }
            }
        }
    }
}
