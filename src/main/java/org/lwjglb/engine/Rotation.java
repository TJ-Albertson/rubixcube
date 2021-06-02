/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lwjglb.engine;

import org.joml.Vector3f;

/**
 *
 * @author thoma
 */
public class Rotation {
    
    private String[] vectorColors = {"RGY", "GY", "GOY", "RG", "G", "GO", "RGW", "GW", "GOW", "RY", "Y", "OY", "R", "O", "RW", "W", "OW", "RBY", "BY", "BOY", "RB", "B", "BO", "RBW", "BW", "BOW"};
    
    private String[] vectorNames = {"FBL", "LBM", "LBL", "FML", "LM", "LML", "FTL", "LTM", "LTL", "FBM", "BM", "BMB", "FM", "BM", "FTM", "TM", "TMB", "FBR", "RBM", "RBR", "FMR", "RM", "RMR", "FTR", "RMT", "RTR"};
    
    public final String name;
    
    public final Vector3f position;
    
    public Rotation[] vectors;
    
    public Vector3f[] vecArr;
    
    private float bruh = 0;
    
    public Rotation() {
        position = new Vector3f();
        name = new String();
    }
    
    public Rotation(String name, Vector3f position) {
        this.position = position;
        this.name = name;
    }
    
    public Rotation[] positionMaker() {
        
        vectors = new Rotation[26];
        
        int count = 1;
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    if (x == 0 && y == 0 && z == 0) {
                        continue;
                    }
                    Vector3f vector = new Vector3f(x, y ,z);
                    String names = "cube" + count;
                    vectors[count - 1] = new Rotation(names, vector);               
                    count++;       
                }
            }
        }
        return vectors;
    }
    
    public Vector3f[] vectorPositionMaker() {
        
        vecArr = new Vector3f[26];
        
        int count = 0;
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {
                    if (x == 0 && y == 0 && z == 0) {
                        continue;
                    }
                    Vector3f vector = new Vector3f(x, y ,z);
                    vecArr[count] = new Vector3f(vector);               
                    count++;       
                }
            }
        }
        return vecArr;
    }
    
    public Vector3f getPosition(String name) {
        for (Rotation vector : vectors) {
            if (name.equals(vector.name)) {
                return vector.position;
            }
        }
        Vector3f tempVector = new Vector3f(0, 0, 0);
        return tempVector;
    }
    
    public void topRightRotation(GameItem[] gameItems) {
        
        for (GameItem gameItem : gameItems) {
            if (gameItem.getPosition().equals(1, 1, 1) && gameItem.getRotation().equals(0, 90, 0) ) {
                String topright = gameItem.getName(); 
            } else if (gameItem.getPosition().equals(1, 1, 1)) {
                
            }
        }                                 
    }
}
