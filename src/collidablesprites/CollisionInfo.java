package collidablesprites;

import geometricshapes.Point;

/**
 * The type Collision info.
 */
public class CollisionInfo {
    private Collidable currentCollidable;
    private Point collisionPoint;

    /**
     * Instantiates a new Collision info.
     *
     * @param currentCollidable the current collidablesprites
     * @param collisionPoint    the collision point
     */
    public CollisionInfo(Collidable currentCollidable, Point collisionPoint) {
        this.collisionPoint = collisionPoint;
        this.currentCollidable = currentCollidable;
    }

    /**
     * Collision point point.
     *
     * @return the point
     */
// the point at which the collision occurs.
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Collision object collidablesprites.
     *
     * @return the collidablesprites
     */
// the collidablesprites object involved in the collision.
    public Collidable collisionObject() {
        return this.currentCollidable;
    }
}