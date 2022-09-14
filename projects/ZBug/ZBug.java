import info.gridworld.actor.Bug;


public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int count;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public ZBug(int length)
    {
        steps = 0;
        count = 0;
        sideLength = length;
        setDirection(90);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else if(canMove() && count < 2)
        {
        	if(count==0) {
        		setDirection(225);
        	}else if(count==1) {
        		setDirection(90);
        	}
//            turn();
//            turn();
            steps = 0;
            count++;
        }
    }
}
