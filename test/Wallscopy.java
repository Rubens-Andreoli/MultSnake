

import com.iinmorus.multsnake.entity.*;
import com.iinmorus.engine.Renderer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Wallscopy extends Drawable{
        
    private final ArrayList<Point> walls;
    private boolean collidable;
    private long start;
    private int respawnTime = 30000;
    private int ghostTime = 3000;

    private ArrayList<Point> pointsBlackList;
    
    public Wallscopy(int amount, ArrayList<Point> pointsBlackList){
	baseColor = Color.WHITE;
	walls = new ArrayList<>();
	this.pointsBlackList = pointsBlackList;
	this.generate(amount);
    }
    
    public Wallscopy(int amount, Point pointBlackList){
	baseColor = Color.WHITE;
	this.walls = new ArrayList<>();
	pointsBlackList = new ArrayList<>();
	pointsBlackList.add(pointBlackList);
	this.generate(amount);
    }
    
    private void generate(int amount){
//	walls.clear();
	Random random = new Random();
	for(int w=0; w <= amount; w++){
	    Point wall;
	    do{
		wall = new Point(random.nextInt((Renderer.WIDTH/Renderer.SCALE)-2)+1, random.nextInt(Renderer.HEIGHT/Renderer.SCALE));
	    }while(pointsBlackList.contains(wall) || walls.contains(wall));
	    walls.add(wall);
	}
	start = System.currentTimeMillis();
    }
    
    public void update(){
	long current = System.currentTimeMillis();
	if(current - start >= ghostTime) collidable = true;
//	if(current - start >= respawnTime) this.generate(amount);
    }

    @Override
    public void draw(Graphics2D g){
	g.setColor(collidable ? baseColor : baseColor.darker());
	walls.stream().forEach((wall) -> {
		g.fillRect(wall.x*Renderer.SCALE, wall.y*Renderer.SCALE, Renderer.SCALE, Renderer.SCALE); 
	});
    }

    public ArrayList<Point> getWalls(){
	return walls;
    }

    public boolean isCollidable() {
	return collidable;
    } 

}
