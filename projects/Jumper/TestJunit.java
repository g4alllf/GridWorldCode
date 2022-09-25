import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import org.example.Jumper;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
public class TestJunit {


    @Test
    public void testJumperRun(){
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        alice.setColor(Color.ORANGE);
        Jumper bob = new Jumper(Color.ORANGE);

        world.add(new Location(7, 8), alice);
        world.add(new Location(5, 5), bob);
        world.show();
    }

    @Test
    public void test1(){

        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(8, 5), alice);
        alice.setDirection(0);

        // if the cell in front of it is empty
        alice.act();
        assertEquals(new Location(6,5),alice.getLocation());

        // if the cell in front of it is rock
        world.add(new Location(4,5),new Rock());
        alice.act();
        assertEquals(new Location(5,5),alice.getLocation());

    }

    @Test
    public void test2(){
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(1, 1), alice);
        alice.setDirection(0);

        alice.act();
        assertEquals(new Location(0,1),alice.getLocation());

    }

    @Test
    public void test3(){
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(0, 1), alice);
        alice.setDirection(0);

        alice.act();
        assertEquals(new Location(0,1),alice.getLocation());
    }

    // if  an actor is two cells in front of the jumper, the jumper just move forward if canMove.
    @Test
    public void test4() {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(8, 5), alice);
        alice.setDirection(0);
        Jumper other = new Jumper();
        world.add(new Location(6,5),other);

        alice.act();
        assertEquals(new Location(7,5),alice.getLocation());

    }
    //if jumper encounters another jumper in the path, jump through it
    @Test
    public void test5(){
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(8, 5), alice);
        alice.setDirection(0);
        Jumper other = new Jumper();
        world.add(new Location(7,5),other);

        alice.act();
        assertEquals(new Location(6,5),alice.getLocation());
    }
    //other tests:if a jumper encounters rock or flower on its path, jumper will jump through it
    @Test
    public void test6(){
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        world.add(new Location(8, 5), alice);
        alice.setDirection(0);
        //if that is rock
        Rock other = new Rock();
        world.add(new Location(7,5),other);
        alice.act();
        assertEquals(new Location(6,5),alice.getLocation());
        //if that is flower
        Flower f = new Flower();
        world.add(new Location(5,5),f);
        alice.act();
        assertEquals(new Location(4,5),alice.getLocation());
    }
    //other: jumper should not leave the flower
    @Test
    public void test7(){
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        Location location = new Location(8,5);
        world.add(location, alice);
        alice.setDirection(0);

        Grid<Actor> g = world.getGrid();
        alice.act();
        assert(! (g.get(location) instanceof Flower));
    }
}