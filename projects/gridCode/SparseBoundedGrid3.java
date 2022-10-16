/**
* @author w
* @version create time: 2022年10月13日 下午7:01:24
*/
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * implementation of SparseBoundedGrid by tree map
 * @author wh
 */
public class SparseBoundedGrid3<E> extends AbstractGrid<E> {
	private int rows;
	private int cols;
	private Map<Location, E> entityMap;
	
	public SparseBoundedGrid3(int rows, int cols)
	{
		this.rows = rows;
		this.cols = cols;
		this.entityMap = new TreeMap<Location, E>();
	}
	
	/**
	 * get the number of rows in the grid
	 * @return the number of rows in the grid
	 */
	public int getNumRows()
	{
		return this.rows;
	}
	
	/**
	 * get the number of columns in the grid
	 * @return the number of columns in the grid
	 */
	public int getNumCols()
	{
		return this.cols;
	}
	
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
		return this.entityMap.put(loc, obj);
	}
	
	/**
     * Returns the object at a given location in this grid. <br />
     * Precondition: <code>loc</code> is valid in this grid
     * @param loc a location in this grid
     * @return the object at location <code>loc</code> (or <code>null<code> 
     *  if the location is unoccupied)
     */
	public E get(Location loc)
	{
		if(loc == null)
		{
			throw new NullPointerException("loc == null");
		}
		return this.entityMap.get(loc);
	}
	
	/**
     * Removes the object at a given location from this grid. <br />
     * Precondition: <code>loc</code> is valid in this grid
     * @param loc the location of the object that is to be removed
     * @return the object that was removed (or <code>null<code> if the location
     *  is unoccupied)
     */
	public E remove(Location loc)
	{
		if(loc == null)
		{
			throw new NullPointerException("loc == null");
		}
		return this.entityMap.remove(loc);
	}
	
	/**
     * Gets the locations in this grid that contain objects.
     * @return an array list of all occupied locations in this grid
     */
	public ArrayList<Location> getOccupiedLocations()
	{
		ArrayList<Location> locations = new ArrayList<Location>();
		for(Location loc : this.entityMap.keySet())
		{
			locations.add(loc);
		}
		return locations;
	}
}
