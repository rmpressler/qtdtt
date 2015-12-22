package Physics;

import java.util.ArrayList;

public class CollisionDetect {
	private ArrayList<Collidable> objects;
	
	public CollisionDetect() {
		objects = new ArrayList<Collidable>();
	}
	
	public void add(Collidable c) {
		objects.add(c);
	}
	
	public void remove(Collidable c) {
		objects.remove(c);
	}
	
	public void update() {
		for(int i = 0;i < objects.size();i++) {
			for(int j = i + 1;j < objects.size();j++) {
				if(colliding(objects.get(i), objects.get(j)))
					CollisionHandler.handleCollision(objects.get(i), objects.get(j));
			}
			if(!objects.get(i).isAlive()) objects.remove(i);
		}
	}
	
	public boolean colliding(Collidable a, Collidable b) {
		HitBox aBox = a.getHitBox();
		HitBox bBox = b.getHitBox();
		
		HitBox left;
		HitBox right;
		HitBox top;
		HitBox bottom;
		
		//Orient left-right
		if(aBox.getX1() < bBox.getX1()) {
			left = aBox;
			right = bBox;
		}
		else {
			left = bBox;
			right = aBox;
		}
		
		//Orient top-bottom
		if(aBox.getY1() < bBox.getY1()) {
			top = aBox;
			bottom = bBox;
		}
		else {
			bottom = bBox;
			top = aBox;
		}
		
		//if right's x1, y1 is is inside left
		if(right.getX1() >= left.getX1() &&
				right.getX1() <= left.getX2() &&
				right.getY1() >= left.getY1() &&
				right.getY1() <= left.getY2()) {
			return true;
		}
		
		//if right's x1, y2 is is inside left
		else if(right.getX1() >= left.getX1() &&
			right.getX1() <= left.getX2() &&
			right.getY2() >= left.getY1() &&
			right.getY2() <= left.getY2()) {
			return true;
		}
		
		//if left's x2, y2 is is inside right
		else if(left.getX2() >= right.getX1() &&
			left.getX2() <= right.getX2() &&
			left.getY2() >= right.getY1() &&
			left.getY2() <= right.getY2()) {
			return true;
		}
		
		//if left's x2, y1 is is inside right
		else if(left.getX2() >= right.getX1() &&
			left.getX2() <= right.getX2() &&
			left.getY1() >= right.getY1() &&
			left.getY1() <= right.getY2()) {
			return true;
		}
		
		//if bottom's x1, y1 is is inside top
		if(bottom.getX1() >= top.getX1() &&
				bottom.getX1() <= top.getX2() &&
				bottom.getY1() >= top.getY1() &&
				bottom.getY1() <= top.getY2()) {
			return true;
		}
		
		//if bottom's x2, y1 is is inside top
		else if(bottom.getX2() >= top.getX1() &&
			bottom.getX2() <= top.getX2() &&
			bottom.getY1() >= top.getY1() &&
			bottom.getY1() <= top.getY2()) {
			return true;
		}
		
		//if top's x1, y2 is is inside bottom
		else if(top.getX1() >= bottom.getX1() &&
				top.getX1() <= bottom.getX2() &&
				top.getY2() >= bottom.getY1() &&
				top.getY2() <= bottom.getY2()) {
			return true;
		}
		
		//if top's x2, y2 is is inside bottom
		else if(top.getX2() >= bottom.getX1() &&
			top.getX2() <= bottom.getX2() &&
			top.getY2() >= bottom.getY1() &&
			top.getY2() <= bottom.getY2()) {
			return true;
		}
		
		return false;
	}
}
