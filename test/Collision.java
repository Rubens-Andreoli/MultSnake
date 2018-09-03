

import com.iinmorus.multsnake.objects.Snake;
import static com.iinmorus.multsnake.objects.Snake.DOWN;
import static com.iinmorus.multsnake.objects.Snake.LEFT;
import static com.iinmorus.multsnake.objects.Snake.RIGHT;
import static com.iinmorus.multsnake.objects.Snake.UP;
import java.awt.Point;
import java.util.ArrayList;

public class Collision {
    
    public static boolean isCollision(Snake snake, ArrayList<Point> pointsBlackList){
	return Collision.isCollision(snake.getHead(), snake.getDirection(), pointsBlackList);
    }
    
    public static boolean isCollision(Point point, int direction, ArrayList<Point> pointsBlackList){
//	if(point.y < 0 || point.y > Config.HEIGHT/Config.SCALE-1 || 
//		point.x < 0 || point.x >= Config.WIDTH/Config.SCALE ||
//		isCollisionWithBlackList(point.x, point.y, pointsBlackList))
//	    return true;
	
	switch (direction) {
	    case UP:
		if(point.y -1 < 0 || isCollisionWithBlackList(point.x, point.y-1, pointsBlackList))  
		    return true;
		break;
	    case DOWN:
		if(point.y +1 >= Config.HEIGHT/Config.SCALE || isCollisionWithBlackList(point.x, point.y+1, pointsBlackList))  
		    return true;
		break;
	    case LEFT:
		if(point.x -1 < 0 || isCollisionWithBlackList(point.x-1, point.y, pointsBlackList))  
		    return true;
		break;
	    case RIGHT:
		if(point.x +1 >= Config.WIDTH/Config.SCALE || isCollisionWithBlackList(point.x+1, point.y, pointsBlackList))  
		    return true;
		break;
	    }
	return false;
    }
    
    private static boolean isCollisionWithBlackList(int headX, int headY, ArrayList<Point> pointsBlackList){
	Point head = new Point(headX, headY);
	for(Point point : pointsBlackList){
	    if(head.equals(point))
		return true;
	}
	return false;
    
    }
    
}
