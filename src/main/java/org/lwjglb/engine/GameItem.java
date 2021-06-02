package org.lwjglb.engine;

import org.joml.Vector3f;
import org.lwjglb.engine.graph.Mesh;

public class GameItem {

    private Mesh mesh;
    
    private final Vector3f position;
    
    private float scale;

    private final Vector3f rotation;
    
    private String name;
    
    public GameItem(Mesh mesh, float x, float y, float z, String name) {
        this.mesh = mesh;
        position = new Vector3f();
        scale = 1;
        rotation = new Vector3f();
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
        this.name = name;
    }

    public Vector3f getPosition() {
        return position;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }   
    
    public void movePosition(float offsetX, float offsetY, float offsetZ) {
        if ( offsetZ != 0 ) {
            position.x += (float)Math.sin(Math.toRadians(rotation.y)) * -1.0f * offsetZ;
            position.z += (float)Math.cos(Math.toRadians(rotation.y)) * offsetZ;
        }
        if ( offsetX != 0) {
            position.x += (float)Math.sin(Math.toRadians(rotation.y - 90)) * -1.0f * offsetX;
            position.z += (float)Math.cos(Math.toRadians(rotation.y - 90)) * offsetX;
        }
        position.y += offsetY;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }
    
    public Mesh getMesh() {
        return mesh;
    }
    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}