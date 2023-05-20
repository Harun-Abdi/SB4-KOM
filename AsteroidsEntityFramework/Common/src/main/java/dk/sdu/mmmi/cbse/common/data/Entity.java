package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.data.entityparts.EntityPart;


import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.awt.Color;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();

    private float[] shapeX = new float[4];
    private float[] shapeY = new float[4];
    private float radius;
    private Color color;
    private float red;
    private float green;
    private float blue;
    private float alpha;
    private Map<Class, EntityPart> parts;

    public Entity() {
        parts = new ConcurrentHashMap<>();
        color = new Color(1,0,0,1);
        red = 1.0f;
        green = 0f;
        blue = 0f;
        alpha = 1.0f;
    }


    /*
    public Entity() {
        parts = new ConcurrentHashMap<>();
    }

     */
    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public float getAlpha() {
        return alpha;
    }


    public void add(EntityPart part) {
        parts.put(part.getClass(), part);
    }
    
    public void remove(Class partClass) {
        parts.remove(partClass);
    }
    
    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }
    
    public void setRadius(float r){
        this.radius = r;
    }
    
    public float getRadius(){
        return radius;
    }

    public String getID() {
        return ID.toString();
    }

    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }

    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
}