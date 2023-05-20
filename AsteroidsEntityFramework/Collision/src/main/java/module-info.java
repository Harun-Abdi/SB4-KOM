import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    exports dk.sdu.mmmi.cbse.collision;
    requires Common;

    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collision.CollisionChecker;

}