package org.lwjglb.game;

import org.joml.Vector2f;
import org.joml.Vector3f;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjglb.engine.GameItem;
import org.lwjglb.engine.IGameLogic;
import org.lwjglb.engine.MouseInput;
import org.lwjglb.engine.Rotation;
import org.lwjglb.engine.Window;
import org.lwjglb.engine.graph.Camera;
import org.lwjglb.engine.graph.Mesh;
import org.lwjglb.engine.graph.Texture;

public class DummyGame implements IGameLogic {

    private static final float MOUSE_SENSITIVITY = 0.2f;

    private final Vector3f cameraInc;

    private final Renderer renderer;

    private final Camera camera;

    private GameItem[] gameItems;

    private static final float CAMERA_POS_STEP = 0.05f;
    
    
    
    
    private float bruh = 0;
    
    private int inc = 0;
    
    
    private Mesh grassMesh;  
    
    
    private float rotation = 0;

    public DummyGame() {
        renderer = new Renderer();
        camera = new Camera();
        cameraInc = new Vector3f();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);

        camera.setPosition(-5f, 4f, 4.92f);
        camera.setRotation(28.58f, 43.30f, 0f);
        
        // Create the Mesh
        float[] positions = new float[]{
            // V0
            -0.5f, 0.5f, 0.5f,
            // V1
            -0.5f, -0.5f, 0.5f,
            // V2
            0.5f, -0.5f, 0.5f,
            // V3
            0.5f, 0.5f, 0.5f,          
        };
        
        float twoThird = (float) 2/3;
        float oneThird = (float) 1/3;
        
        float[] whiteCoord = new float[]{
            twoThird, 0.0f,
            twoThird, 0.5f,
            1.0f, 0.5f,
            1.0f, 0.0f,           
        };
        
        float[] redCoord = new float[]{
            0.0f, 0.5f,
            0.0f, 1.0f,
            oneThird, 1.0f,
            oneThird, 0.5f,           
        };
        
        float[] greenCoord = new float[]{
            oneThird, 0.5f,
            oneThird, 1.0f,
            twoThird, 1.0f,
            twoThird, 0.5f,           
        };
        
        float[] orangeCoord = new float[]{
            0.0f, 0.0f,
            0.0f, 0.5f,
            oneThird, 0.5f,
            oneThird, 0.0f,           
        };
        
        float[] blueCoord = new float[]{
            twoThird, 0.5f,
            twoThird, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.5f,           
        };
        
        float[] yellowCoord = new float[]{
            oneThird, 0.0f,
            oneThird, 0.5f,
            twoThird, 0.5f,
            twoThird, 0.0f,          
        };
        
        int[] indices = new int[]{
            0, 1, 3, 3, 1, 2,
        };     
        
        Texture grass = new Texture("textures/grassblock.png");
        grassMesh = new Mesh(positions, whiteCoord, indices, grass);
        /*
        Texture colors = new Texture("textures/colors.png");
        Mesh colorMesh = new Mesh(positions, textCoords, indices, colors);
        
        Texture rgy = new Texture("textures/Red Green Yellow.png");
        Mesh rgyMesh = new Mesh(positions, textCoords, indices, rgy);
        */
        
        Texture colors = new Texture("textures/all colors.png");
        
        Mesh white = new Mesh(positions, whiteCoord, indices, colors);
        Mesh red = new Mesh(positions, redCoord, indices, colors);
        Mesh green = new Mesh(positions, greenCoord, indices, colors);
        Mesh orange = new Mesh(positions, orangeCoord, indices, colors);
        Mesh blue = new Mesh(positions, blueCoord, indices, colors);
        Mesh yellow = new Mesh(positions, yellowCoord, indices, colors);
        
        gameItems = new GameItem[54];
        
        String name = "white";        
        int count = 0;
        for (int x = -1; x < 2; x++) {
            for (int z = -1; z < 2; z++) { 
                name += count;
                gameItems[count] = new GameItem(white, x, 1, z, name);
                gameItems[count].setRotation(90, 0 , 0);
                name = "white";
                count++;
            }
        }
        
        name = "red";
        for (int z = -1; z < 2; z++) {
            for (int y = -1; y < 2; y++) {
                name += count;
                gameItems[count] = new GameItem(red, -1, y, z, name);
                gameItems[count].setRotation(0, 90 , 0);
                name = "red";
                count++;
            }
        }
        
        name = "green";
        for (int z = -1; z < 2; z++) { 
            for (int y = -1; y < 2; y++) {
                name += count;
                gameItems[count] = new GameItem(green, z, y, -1, name);
                gameItems[count].setRotation(0, 180 , 0);
                name = "green";
                count++;
            }
        }
        
        name = "orange";
        for (int z = -1; z < 2; z++) {
            for (int y = -1; y < 2; y++) {
                name += count;
                gameItems[count] = new GameItem(orange, 1, y, z, name);
                gameItems[count].setRotation(0, 270 , 0);
                name = "orange";
                count++;
            }    
        }
        
        name = "blue";
        for (int z = -1; z < 2; z++) {
            for (int y = -1; y < 2; y++) {
                name += count;
                gameItems[count] = new GameItem(blue, z, y, 1, name);
                gameItems[count].setRotation(0, 0 , 0);
                name = "blue";
                count++;
            }
        }
        
        name = "yellow";        
        for (int x = -1; x < 2; x++) {
            for (int z = -1; z < 2; z++) { 
                name += count;
                gameItems[count] = new GameItem(yellow, x, -1, z, name);
                gameItems[count].setRotation(270, 0 , 0);
                name = "yellow";
                count++;
            }
        }
        
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0, 0);

        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }
        
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            bruh = window.getVal();

        } else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            bruh = window.getVal();
        }
        
        if (window.isKeyPressed(GLFW_KEY_SPACE)) {
            
        }
        
        //bruh = window.getVal();
        //gameItems[window.getVal()].setMesh(grassMesh);
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        
        // Update camera position
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);
        //System.out.println(cameraInc.x);
        
        //gameItems[0].movePosition(objectInc.x * CAMERA_POS_STEP, 0, 0);
        
        float x = (float) Math.sin(bruh);
        float y = (float) Math.cos(bruh);
        
        float mult = (float) 1.4142;
        float add = (float) 0.785;
        
        float x2 = (float) Math.sin(bruh+add);
        x2 *= mult;
        float y2 = (float) Math.cos(bruh+add);
        y2 *= mult;
        
        rotation = (float) (180/Math.PI) * bruh;
       /*
        if (rotation > 360 || rotation < 0) {
            bruh = 0;
        }
        */
        
        //Top Rotation
        
        
        
        /*
        //edges position
        gameItems[7].setPosition(y, 1, x);
        gameItems[5].setPosition(-x, 1, y);
        gameItems[1].setPosition(-y, 1, -x);
        gameItems[3].setPosition(x, 1, -y);
        */
        //corners position
        //gameItems[8].setPosition(y2, 1, x2);
        
        /*
        gameItems[2].setPosition(-x2, 1, y2);
        gameItems[0].setPosition(-y2, 1, -x2);
        gameItems[6].setPosition(x2, 1, -y2);
        
        //edges rotation
        gameItems[7].setRotation(90, 0, rotation);
        gameItems[5].setRotation(90, 0, rotation);
        gameItems[1].setRotation(90, 0, rotation);
        gameItems[3].setRotation(90, 0, rotation);

        //corners rotation
        gameItems[8].setRotation(90, 0, rotation);
        gameItems[2].setRotation(90, 0, rotation);
        gameItems[0].setRotation(90, 0, rotation);
        gameItems[6].setRotation(90, 0, rotation);
        
        //middle rotation
        gameItems[4].setRotation(90, 0, rotation);
            
        
        //red side
        gameItems[11].setPosition(-y2, 1, -x2);
        gameItems[14].setPosition(-y, 1, -x);
        gameItems[17].setPosition(-x2, 1, y2);
        
        gameItems[11].setRotation(0, rotation + 90, 0);
        gameItems[14].setRotation(0, rotation + 90, 0);
        gameItems[17].setRotation(0, rotation + 90, 0);
        
        
        //green side
        gameItems[26].setPosition(-y2, 1, -x2);
        gameItems[23].setPosition(x, 1, -y);
        gameItems[20].setPosition(x2, 1, -y2);
        
        gameItems[26].setRotation(0, rotation + 180, 0);
        gameItems[23].setRotation(0, rotation + 180, 0);
        gameItems[20].setRotation(0, rotation + 180, 0);
        
        //orange side
        gameItems[35].setPosition(x2, 1, -y2);
        gameItems[32].setPosition(y, 1, x);
        gameItems[29].setPosition(y2, 1, x2);
        
        gameItems[35].setRotation(0, rotation + 270, 0);
        gameItems[32].setRotation(0, rotation + 270, 0);
        gameItems[29].setRotation(0, rotation + 270, 0);
        
        //blue side
        gameItems[38].setPosition(y2, 1, x2);
        gameItems[41].setPosition(-x, 1, y);
        gameItems[44].setPosition(-x2, 1, y2);
        
        gameItems[38].setRotation(0, rotation, 0);
        gameItems[41].setRotation(0, rotation, 0);
        gameItems[44].setRotation(0, rotation, 0);
        
        */
        
        for (GameItem gameItem : gameItems) {
            if (gameItem.getPosition().equals(1, 1, 1)) {
                gameItem.setPosition(2, 2, 2);
            }
        
        }
        
        
        /*
        Middle Rotation 
        
        //edges pos
        gameItems[15].setPosition(x, y, 0f);
        gameItems[21].setPosition(y, -x, 0f);
        gameItems[10].setPosition(-x, -y, 0f);
        gameItems[4].setPosition(-y, x, 0f);         

        //corners pos
        gameItems[7].setPosition(y2, -x2, 0f);
        gameItems[24].setPosition(x2, y2, 0f);
        gameItems[18].setPosition(-y2, x2, 0f);
        gameItems[1].setPosition(-x2, -y2, 0f);
        
        //edges rot
        gameItems[15].setRotation(0, 0, rotation);
        gameItems[21].setRotation(0, 0, rotation + 90);
        gameItems[10].setRotation(0, 0, rotation + 180);
        gameItems[4].setRotation(0, 0, rotation + 270);

        //corners rot
        gameItems[7].setRotation(0, 0, rotation + 180);
        gameItems[24].setRotation(0, 0, rotation);
        gameItems[18].setRotation(0, 0, rotation); 
        gameItems[1].setRotation(0, 0, rotation + 180);
        */     
        
        // Update camera based on mouse            
        if (mouseInput.isRightButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, gameItems);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }

}
