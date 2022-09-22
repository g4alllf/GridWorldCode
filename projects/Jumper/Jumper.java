import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Flower;
import java.awt.Color;

public class Jumper extends Actor{
    public Jumper(){
        setColor(Color.BLUE);
    }

    public Jumper(Color color){
        setColor(color);
    }

    public void act(){
        if(canJump()){
            jump();
        }else if(canMove()){
            move();
        }else{
            turn();
        }
    }

    public void turn(){
        setDirection(getDirection()+Location.HALF_RIGHT);
    }

    public boolean canJump(){
        Grid<Actor> g = getGrid();
        if(g==null){
            return false;
        }
        int dir = getDirection();
        Location loc = getLocation();
        Location nextLoc = loc.getAdjacentLocation(dir);
        if(g.isValid(nextLoc)==false){
            return false;
        }
        Location nextTwoLoc = nextLoc.getAdjacentLocation(dir);
        if(g.isValid(nextTwoLoc)==false){
            return false;
        }
        Actor t = g.get(nextTwoLoc);
        if(t==null){
            return true;
        }
        else{
            return false;
        }
    }

    public void jump(){
        Grid<Actor> g = getGrid();
        if(g==null) 
            return;
        int dir = getDirection();
        Location loc = getLocation();
        Location nextLoc = loc.getAdjacentLocation(dir);
        Location nextTwoLoc = nextLoc.getAdjacentLocation(dir);
        if(g.isValid(nextTwoLoc)){
            moveTo(nextTwoLoc);
        }else{
            removeSelfFromGrid();
        }
    }

    public boolean canMove(){
        Grid<Actor> gr = getGrid();
        if (gr == null) 
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) 
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Flower);
    }

    public void move(){
        Grid<Actor> gr = getGrid();
        if (gr == null) 
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next)) 
            moveTo(next);
        else 
            removeSelfFromGrid();
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
}