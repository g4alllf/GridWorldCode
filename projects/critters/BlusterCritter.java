import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import java.awt.Color;
import java.util.ArrayList;

public class BlusterCritter extends Critter{
	
	private int num;
	
	public BlusterCritter(int c) {
		super();
		num = c;
	}
	
	public int getNum() {
		return num;
	}
	
	public ArrayList<Actor> getActors(){
		ArrayList<Actor> res = new ArrayList<Actor>();
		int row = getLocation().getRow();
		int col = getLocation().getCol();
		for(int i = row-2; i <= row+2; i++) {
			for(int j = col-2; j <= col+2; j++) {
				if(i==row&&j==col) continue;
				else {
					Location temp = new Location(i, j);
					if(getGrid().isValid(temp)) {
						Actor t = getGrid().get(temp);
						if(t!=null) {
							res.add(t);
						}
					}
				}
			}
		}
		return res;
	}
	
	public void change(ArrayList<Actor> a) {
		int n = 0;
		for(Actor t : a) {
			if((t instanceof Critter)&&!t.getLocation().equals(this.getLocation())) {
				n++;
			}
		}
		if(n>num) {
			Color c = getColor();
			int r = (int)(c.getRed()*0.9);
			int g = (int)(c.getGreen()*0.9);
			int b = (int)(c.getBlue()*0.9);
			setColor(new Color(r, g, b));
		}else if(n<num) {
			Color c = getColor();
			int r = Math.max((int)c.getRed()*2, 255);
			int g = Math.max((int)c.getGreen()*2, 255);
			int b = Math.max((int)c.getBlue()*2, 255);	
			setColor(new Color(r, g, b));
		}
	}
}
