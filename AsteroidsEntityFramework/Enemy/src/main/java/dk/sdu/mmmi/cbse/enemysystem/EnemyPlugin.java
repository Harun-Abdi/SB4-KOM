package dk.sdu.mmmi.cbse.enemysystem;

import com.badlogic.gdx.math.MathUtils;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.awt.*;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;

    public EnemyPlugin() {}


    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData) {


        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = MathUtils.random(0, gameData.getDisplayWidth());
        float y = MathUtils.random(0, gameData.getDisplayHeight());
        float radians = MathUtils.random(0f, (float) (2 * Math.PI));

        Entity enemyShip = new Enemy();
        enemyShip.setRadius(10);

        float[] enemyShapeX = { -10, 10, 10, -10 };
        float[] enemyShapeY = { -10, -10, 10, 10 };
        enemyShip.setShapeX(enemyShapeX);
        enemyShip.setShapeY(enemyShapeY);

        enemyShip.setColor(new Color(1,0,0,1));
        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new PositionPart(x, y, radians));
        enemyShip.add(new LifePart(1,2));
        //enemyShip.add(new ShootingPart(0.2f));


        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

}