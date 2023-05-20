import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Core {



    requires Common;
    requires java.desktop;
    requires gdx;
    requires Collision;
    requires Enemy;
    requires Asteroid;
    requires Player;
    requires com.badlogic.gdx;
    requires gdx.backend.lwjgl;

    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;

    exports dk.sdu.mmmi.cbse.main;
    exports dk.sdu.mmmi.cbse.managers;

}