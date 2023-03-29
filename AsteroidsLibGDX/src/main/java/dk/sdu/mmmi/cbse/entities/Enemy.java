package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends SpaceObject {

    private Random random;
    private float fireRate;
    private float fireTimer;
    private ArrayList<Bullet> bullets;

    public Enemy(float x, float y, ArrayList<Bullet> bullets) {
        this.bullets = bullets;
        this.x = x;
        this.y = y;
        random = new Random();
        fireRate = 1; // number of shots per second
        fireTimer = 0;
        speed = 100;
        rotationSpeed = 5;
        width = height = 20;
        shapex = new float[4];
        shapey = new float[4];
    }

    public void update(float dt) {
        // Move randomly
        if (random.nextFloat() < 0.01) {
            radians += random.nextFloat() * 2 - 1; // random rotation
        }
        dx = MathUtils.cos(radians) * speed;
        dy = MathUtils.sin(radians) * speed;
        x += dx * dt;
        y += dy * dt;
        wrap();

        // Shoot randomly
        fireTimer += dt;
        if (fireTimer >= 1 / fireRate && random.nextFloat() < 0.1) {
            fireTimer = 0;
            shoot();
        }
        setShape();
    }


    public void setShape() {
        shapex = new float[4];
        shapey = new float[4];
        shapex[0] = x - width / 2;  // bottom-left
        shapey[0] = y - height / 2;
        shapex[1] = x + width / 2;  // bottom-right
        shapey[1] = y - height / 2;
        shapex[2] = x + width / 2;  // top-right
        shapey[2] = y + height / 2;
        shapex[3] = x - width / 2;  // top-left
        shapey[3] = y + height / 2;
    }


    public void draw(ShapeRenderer sr) {
        sr.setColor(6, 155, 34, 1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0, j = shapex.length - 1; i < shapex.length; j = i++) {
            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
        }
        sr.end();
    }

    public void shoot() {
            bullets.add(new Bullet(x,y,radians));

    }
}
