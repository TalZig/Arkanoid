package collidablesprites;

import geometricshapes.Line;
import geometricshapes.Point;
import geometricshapes.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The type GameLevel environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Instantiates a new GameLevel environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * Instantiates a new GameLevel environment.
     *
     * @param gameEnvironment the game environment
     */
    public GameEnvironment(GameEnvironment gameEnvironment) {
        this.collidables = new ArrayList<>();
        this.collidables = gameEnvironment.collidables;
    }

    /**
     * Add collidablesprites.
     *
     * @param c the collidablesprites we need to add.
     */
// add the given collidablesprites to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Remove sprite.
     *
     * @param c the s
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Get closest collision collision info.
     *
     * @param trajectory the trajectory
     * @return the collision info
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Rectangle r;
        Point findDistancePoint, collisionPoint = null;
        int minCell = -1;
        double minDistance = 5000;
        //run on the list and find the closer collidablesprites.
        for (Collidable c : collidables) {
            r = c.getCollisionRectangle();
            //point of the intersection point of the current collidablesprites.
            findDistancePoint = trajectory.closestIntersectionToStartOfLine(r);
            //check if this the closer collidablesprites.
            if (findDistancePoint != null && trajectory.start().distance(findDistancePoint) < minDistance) {
                //if yes, save the min distance, the index and collision point.
                minDistance = trajectory.start().distance(findDistancePoint);
                minCell = collidables.indexOf(c);
                collisionPoint = findDistancePoint;
            }
        }
        //check if there is collision.
        if (minCell != -1) {
            return new CollisionInfo(collidables.get(minCell), collisionPoint);
        } else {
            return null;
        }
    }
}