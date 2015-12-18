package Physics;

import java.util.ArrayList;

public class CollisionDetect {
	private ArrayList<Collidable> objects;
	
	public CollisionDetect() {
		objects = new ArrayList<Collidable>();
	}
	
	public void update() {
		for(int i = 0;i < objects.size();i++) {
			for(int j = 0;j < objects.size();j++) {
				if(i == j) continue;	//Skip itself
				
				
			}
		}
	}
	
//	private boolean collided(Collidable a, Collidable b) {
//		
//	}
}
