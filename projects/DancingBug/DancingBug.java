import java.util.Arrays;

import info.gridworld.actor.Bug;

public class DancingBug extends Bug
{
    private int steps;
    private int sideLength;
    private int count;
    private int[] pattern;

    public DancingBug(int length, int arr[])
    {
        steps = 0;
        sideLength = length;
        pattern = (int[])arr.clone();
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (steps < sideLength && canMove())
        {
        	setDirection(pattern[count]*45);
            move();
            steps++;
            count=(count+1)%pattern.length;
        }
        else
        {
            turn();
            turn();
            steps = 0;
        }
    }
}
