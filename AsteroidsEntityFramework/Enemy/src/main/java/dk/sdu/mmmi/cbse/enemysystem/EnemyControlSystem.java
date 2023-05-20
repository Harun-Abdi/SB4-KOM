package dk.sdu.mmmi.cbse.enemysystem;

import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
//import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Random;

public class EnemyControlSystem implements IEntityProcessingService {

    private Entity enemy;
    private float totalTime = 0f;
    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            //ShootingPart shootingPart = enemy.getPart(ShootingPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);

            this.totalTime = (this.totalTime + gameData.getDelta()) % 100;

            float controlRotateAmplifier = MathUtils.random(0.1f,2f);
            float controlGeneralAmplifier = MathUtils.random(0.1f,2f);

            movingPart.setLeft(
                    (MathUtils.sin(totalTime * controlRotateAmplifier + MathUtils.random(0f, 2f)) * controlGeneralAmplifier) < MathUtils.random(-0.3f, -controlGeneralAmplifier)
            );
            movingPart.setRight(
                    (MathUtils.sin(totalTime * controlRotateAmplifier + MathUtils.random(0f, 2f)) * controlGeneralAmplifier) > MathUtils.random(0.8f, controlGeneralAmplifier)
            );
            movingPart.setUp(
                    MathUtils.random(0.01f, 1f) > MathUtils.random(0.5f, 1f)
            );

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            //shootingPart.process(gameData, enemy);
            lifePart.process(gameData, enemy);

            //shootingPart.setShooting(MathUtils.random(0f,1f) > 0.99f);

            if (lifePart.isDead()) {
                world.removeEntity(enemy);
            }

            updateShape(enemy);
        }
    }


    public void updateShape(Entity entity) {
        int numPoints = 100; // Number of points to approximate the circle

        float[] shapex = new float[numPoints];
        float[] shapey = new float[numPoints];

        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radius = 10; // Radius of the circle

        for (int i = 0; i < numPoints; i++) {
            float angle = (float) (2 * Math.PI * i / numPoints);
            shapex[i] = x + radius * (float) Math.cos(angle);
            shapey[i] = y + radius * (float) Math.sin(angle);
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}