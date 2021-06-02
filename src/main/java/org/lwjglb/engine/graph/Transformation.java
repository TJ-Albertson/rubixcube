package org.lwjglb.engine.graph;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjglb.engine.GameItem;

public class Transformation {

    private final Matrix4f projectionMatrix;

    private final Matrix4f modelViewMatrix;
    
    private final Matrix4f viewMatrix;
    
    private final Matrix4f worldMatrix;
    
    private final Matrix4f rotateMatrix;

    public Transformation() {
        projectionMatrix = new Matrix4f();
        modelViewMatrix = new Matrix4f();
        viewMatrix = new Matrix4f();
        worldMatrix = new Matrix4f();
        rotateMatrix = new Matrix4f();
    }

    public final Matrix4f getProjectionMatrix(float fov, float width, float height, float zNear, float zFar) {
        return projectionMatrix.setPerspective(fov, width / height, zNear, zFar);
    }
    
    public Matrix4f getViewMatrix(Camera camera) {
        Vector3f cameraPos = camera.getPosition();
        Vector3f rotation = camera.getRotation();
        
        viewMatrix.identity();
        // First do the rotation so camera rotates over its position
        viewMatrix.rotate((float)Math.toRadians(rotation.x), new Vector3f(1, 0, 0))
                .rotate((float)Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
        // Then do the translation
        viewMatrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        return viewMatrix;
    }

    public Matrix4f getModelViewMatrix(GameItem gameItem, Matrix4f viewMatrix) {
        Vector3f rotation = gameItem.getRotation();
        
        modelViewMatrix.identity().translate(gameItem.getPosition()).
                rotateX((float)Math.toRadians(-rotation.x)).
                rotateY((float)Math.toRadians(-rotation.y)).
                rotateZ((float)Math.toRadians(-rotation.z)).
                scale(gameItem.getScale());
        
        Matrix4f viewCurr = new Matrix4f(viewMatrix);
        return viewCurr.mul(modelViewMatrix);
    }
    
    public Matrix4f getRotateMatrix(GameItem gameItem, Matrix4f viewMatrix) {
        Vector3f position = gameItem.getPosition();
        
        Vector3f center = new Vector3f(0.0f, 3.0f, 4.0f);
        
        Vector3f pointToRotate = new Vector3f(0.0f, 4.0f, 4.0f);
        
        rotateMatrix.identity().translate(center)
              .rotate((float) Math.toRadians(90.0f), 1.0f, 0.0f, 0.0f)
              .translate(center.negate())
              .transformPosition(pointToRotate);
        
        Matrix4f viewCurr = new Matrix4f(viewMatrix);
        return viewCurr.mul(rotateMatrix);
    }
}
    