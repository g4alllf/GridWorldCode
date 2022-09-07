1. Test the setDirection method with the following inputs and complete the table, giving the compass direction each input represents:
    | Degrees | Compass Direction |
    | :-----: | :---------------: |
    | 0       | North             |
    | 45      | North-East        |
    | 90      | East              |
    | 135     | South-East        |
    | 180     | South             |
    | 225     | South-West        |
    | 270     | West              |
    | 315     | North-West        |
    | 360     | North             |
2. Move a bug to a different location using the moveTo method. In which directions can you move it? How far can you move it? What happens if you try to move the bug outside the grid?
    
    I can move the bug to the directions mentioned in 1 and I can only move to the grids near the bug. Illigal argument occure when I try to move the bug outside the grid.
3. Change the color of a bug, a flower, and a rock. Which method did you use?
   
   setColor.
4. Move a rock on top of a bug and then move the rock again. What happened to the bug?
   
   If the bug is near to the rock, it will try to adjust the direction to avoid hitting the rock.