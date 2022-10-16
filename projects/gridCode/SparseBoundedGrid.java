/**
* @author w
* @version create time: 2022年10月12日 下午8:05:01
*/
import java.util.ArrayList;
import java.util.LinkedList;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class SparseBoundedGrid<E> implements Grid<E> {

	/**
	 * define the entity in SparseBoundedGrid
	 */
	class OccupantInCol
	{
		private Object occupant;
		private int col;
		
		OccupantInCol(int column, Object object)
		{
			occupant = object;
			col = column;
		}
		
		public int getCol()
		{
			return this.col;
		}
		
		public Object getOccupant()
		{
			return this.occupant;
		}
	}
	
	private int columns;
	private int rows;
	// store different grids
	private ArrayList<LinkedList<OccupantInCol>> sparseArrayList;
	
	/**
	 * Initialize SparseBoundedGrid
	 * @param rows number of rows in SparseBoundedGrid
	 * @param cols number of columns in SparseBoundedGrid
	 */
	public SparseBoundedGrid(int rows, int cols)
	{
		this.rows = rows;
		this.columns = cols;
		this.sparseArrayList = new ArrayList<LinkedList<OccupantInCol>>();
		for(int i = 0; i < rows; i++)
		{
			this.sparseArrayList.add(new LinkedList<OccupantInCol>());
		}
	}
	/**
     * Returns the number of rows in this grid.
     * @return the number of rows, or -1 if this grid is unbounded
     */
	@Override
    public int getNumRows()
    {
		return this.rows;
    }

    /**
     * Returns the number of columns in this grid.
     * @return the number of columns, or -1 if this grid is unbounded
     */
	@Override
    public int getNumCols()
    {
		return this.columns;
    }

    /**
     * Checks whether a location is valid in this grid. <br />
     * Precondition: <code>loc</code> is not <code>null</code>
     * @param loc the location to check
     * @return <code>true</code> if <code>loc</code> is valid in this grid,
     * <code>false</code> otherwise
     */
	@Override
    public boolean isValid(Location loc)
    {
		int targetRow = loc.getRow();
		int curRow = getNumRows();
		int targetCol = loc.getCol();
		int curCol = getNumCols();
		if(targetRow >=0 && targetRow < curRow && targetCol >= 0 && targetCol < curCol)
		{
			return true;
		} else {
			return false;
		}
    }

    /**
     * Puts an object at a given location in this grid. <br />
     * Precondition: (1) <code>loc</code> is valid in this grid (2)
     * <code>obj</code> is not <code>null</code>
     * @param loc the location at which to put the object
     * @param obj the new object to be added
     * @return the object previously at <code>loc</code> (or <code>null</code>
     * if the location was previously unoccupied)
     */
	@Override
    public E put(Location loc, E obj)
    {
		if(!isValid(loc))
		{
			throw new IllegalArgumentException("Location "+ loc + " is not Valid");
		}
		if(obj == null)
		{
			throw new NullPointerException("obj == null");
		}
		
		E oldOccupant = get(loc);
		this.sparseArrayList.get(loc.getRow()).add(new OccupantInCol(loc.getCol(), obj));
		return oldOccupant;
    }

	/**
     * Returns the object at a given location in this grid. <br />
     * Precondition: <code>loc</code> is valid in this grid
     * @param loc a location in this grid
     * @return the object at location <code>loc</code> (or <code>null<code> 
     *  if the location is unoccupied)
     */
	@SuppressWarnings("unchecked")
	@Override
    public E get(Location loc)
    {
		if(!isValid(loc))
		{
			throw new IllegalArgumentException("Location " + loc + " is not valid");
		}
		for(OccupantInCol temp : this.sparseArrayList.get(loc.getRow()))
		{
			if(temp.getCol() == loc.getCol())
			{
				return (E) temp.getOccupant();
			}
		}
		return (E) null;
    }

    /**
     * Removes the object at a given location from this grid. <br />
     * Precondition: <code>loc</code> is valid in this grid
     * @param loc the location of the object that is to be removed
     * @return the object that was removed (or <code>null<code> if the location
     *  is unoccupied)
     */
	@Override
    public E remove(Location loc)
    {
		if(!isValid(loc))
		{
			throw new IllegalArgumentException("Location " + loc + " is not valid");
		}
		E gr = get(loc);
		int mark = 0;
		for(OccupantInCol temp : sparseArrayList.get(loc.getRow()))
		{
			if(temp.getCol() == loc.getCol())
			{
				break;
			}
			mark++;
		}
		this.sparseArrayList.get(loc.getRow()).remove(mark);
		return gr;
    }

    /**
     * Gets the locations in this grid that contain objects.
     * @return an array list of all occupied locations in this grid
     */
	@Override
    public ArrayList<Location> getOccupiedLocations()
    {
		ArrayList<Location> locations = new ArrayList<Location>();
		for(int i = 0; i < this.getNumRows(); i++)
		{
			for(int j = 0; j < this.getNumCols(); j++)
			{
				Location loc = new Location(i, j);
				if(this.get(loc) != null)
				{
					locations.add(loc);
				}
			}
		}
		return locations;
    }

    /**
     * Gets the valid locations adjacent to a given location in all eight
     * compass directions (north, northeast, east, southeast, south, southwest,
     * west, and northwest). <br />
     * Precondition: <code>loc</code> is valid in this grid
     * @param loc a location in this grid
     * @return an array list of the valid locations adjacent to <code>loc</code>
     * in this grid
     */
	@Override
    public ArrayList<Location> getValidAdjacentLocations(Location loc)
    {
		ArrayList<Location> locations = new ArrayList<Location>();
		int dir;
		// turn 45 degree each time to find
		for(int i = 0; i < 8; i++)
		{
			dir = i * Location.HALF_RIGHT;
			Location neighbor = loc.getAdjacentLocation(dir);
			if(this.isValid(neighbor))
			{
				locations.add(neighbor);
			}
		}
		return locations;
    }

    /**
     * Gets the valid empty locations adjacent to a given location in all eight
     * compass directions (north, northeast, east, southeast, south, southwest,
     * west, and northwest). <br />
     * Precondition: <code>loc</code> is valid in this grid
     * @param loc a location in this grid
     * @return an array list of the valid empty locations adjacent to
     * <code>loc</code> in this grid
     */
	@Override
    public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
    {
		ArrayList<Location> locations = new ArrayList<Location>();
		for(Location neighbor : this.getValidAdjacentLocations(loc))
		{
			if(this.get(neighbor) == null)
			{
				locations.add(neighbor);
			}
		}
		return locations;
    }

    /**
     * Gets the valid occupied locations adjacent to a given location in all
     * eight compass directions (north, northeast, east, southeast, south,
     * southwest, west, and northwest). <br />
     * Precondition: <code>loc</code> is valid in this grid
     * @param loc a location in this grid
     * @return an array list of the valid occupied locations adjacent to
     * <code>loc</code> in this grid
     */
	@Override
    public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
    {
		ArrayList<Location> locations = new ArrayList<Location>();
		for(Location neighbor : this.getValidAdjacentLocations(loc))
		{
			if(this.get(neighbor) != null)
			{
				locations.add(neighbor);
			}
		}
		return locations;
    }

    /**
     * Gets the neighboring occupants in all eight compass directions (north,
     * northeast, east, southeast, south, southwest, west, and northwest).
     * <br />
     * Precondition: <code>loc</code> is valid in this grid
     * @param loc a location in this grid
     * @return returns an array list of the objects in the occupied locations
     * adjacent to <code>loc</code> in this grid
     */
	@Override
    public ArrayList<E> getNeighbors(Location loc)
    {
		ArrayList<E> neighbors = new ArrayList<E>();
		for(Location neighbor : this.getOccupiedAdjacentLocations(loc))
		{
			neighbors.add(this.get(neighbor));
		}
		return neighbors;
    }
}
