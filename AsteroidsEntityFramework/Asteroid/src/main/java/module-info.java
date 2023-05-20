import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Asteroid {
    exports dk.sdu.mmmi.cbse;
    requires Common;
    requires java.desktop; // farver

    provides IGamePluginService with dk.sdu.mmmi.cbse.AsteroidPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.AsteroidControlSystem;
}