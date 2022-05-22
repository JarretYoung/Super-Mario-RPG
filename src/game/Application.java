package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actions.TeleportAction;
import game.actors.Player;
import game.actors.enemies.Mimic;
import game.actors.enemies.Shark;
import game.actors.enemies.Squid;
import game.actors.npc.PrincessPeach;
import game.actors.npc.Toad;
import game.actors.enemies.Bowser;
import game.currency.Coin;
import game.items.*;
import game.items.PowerStar;
import game.items.Snorkel;
import game.items.SuperMushroom;
import game.items.Wrench;
import game.nature.Sprout;
import game.surfaces.*;


/**
 * The main class for the Mario World game.
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout());

        List<String> map = Arrays.asList(
                "..........................................##..........+.........................",
                "............+...###......+..................#...................................",
                "................#.#.........................#...................................",
                "................###..........................##......................+..........",
                ".....+.........................................#................................",
                "................................................#........+......................",
                ".................+................................#.............................",
                ".................................................##.............................",
                ".......................###......................##..............................",
                ".........+.............#.#..............+#____####.................+............",
                ".......................###.............+#_____###++.............................",
                ".......................................+#______###..............................",
                "........................................+#_____###..............................",
                "..........+.............+........................##.............+...............",
                "...................................................#............................",
                "....................................................#....................###....",
                "...................+.................................#...................###....",
                "......................................................#..................###....",
                ".......................................................##.......................");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        Actor mario = new Player("Player", 'm', 100);
        world.addPlayer(mario, gameMap.at(42, 10));

        // Adding tradeable items
        Item superMushroom = new SuperMushroom(true);
        Item powerStar = new PowerStar();
        Item wrench = new Wrench();
        Item coin = new Coin(5);
        Item snorkel = new Snorkel();
        Item syringe = new Syringe();
        Item medPack = new Medpack();


        Squid squid = new Squid();

        // Creating water area
        for(int y = 0; y<9; y++){
            for (int x =0; x<16; x++){
                gameMap.at(x,y).setGround(new WaterArea());
            }
        }


        gameMap.at(42, 9).addItem(superMushroom);
        gameMap.at(42, 9).addItem(powerStar);
        gameMap.at(42, 9).addItem(coin);

        gameMap.at(41,10).setGround(new HealthFountain());
        gameMap.at(40,10).setGround(new PowerFountain());
        gameMap.at(74,16).setGround(new Chest());
        gameMap.at(17,2).addActor(new Mimic());


        // Adding aquatic enemies
        gameMap.at(8,3).addActor(squid);
        gameMap.at(15,4).addActor(new Shark());
        gameMap.at(0,6).addActor(new Shark());
        gameMap.at(7,7).addActor(new Shark());
        gameMap.at(15,7).addActor(new Shark());
        gameMap.at(10,2).addActor(new Shark());
        gameMap.at(6,2).addActor(new Shark());
        gameMap.at(6,3).addActor(new Shark());
        gameMap.at(4,7).addActor(new Shark());

        // Adding toad
        gameMap.at(24,9).addActor(new Toad());



        // Adding Second Map
        FancyGroundFactory groundFactory2 = new FancyGroundFactory(new Dirt(), new Wall(), new Sprout(), new Floor(), new Lava());
        List<String> map2 = Arrays.asList(
                "......................................................................",
                "......................................................................",
                "......................................................................",
                "LLLL..................................................................",
                "......................................................................",
                "......................................................................",
                "......................................................................",
                "......................................................................",
                "....................LLLL..............................................",
                "..................LLLL.....................____.......................",
                ".........................................._____.......................",
                "..........................................______......................",
                "......................................................................",
                "......................................................................",
                "..........LLLL...............................................###......",
                "................LLLL.........................................#.#......",
                ".............................................................###......",
                "......................................................................");
        GameMap gameMap2 = new GameMap(groundFactory2, map2);
        world.addGameMap(gameMap2);
        //world.addPlayer(mario, gameMap2.at(0, 1));

        // Adding Princess Peach and Bowser
        gameMap2.at(0,6).addActor(new PrincessPeach());
        gameMap2.at(1,6).addActor(new Bowser(gameMap2,1,6));

        //Adding a chest on the map
        gameMap.at(62,15).setGround(new Chest());

        //------------------- Adding Warp Pipes -------------------------//

        //Adding Warp Pipe in Second  map
        WarpPipe wp1gm2 = new WarpPipe();

        //Adding Warp Pipe in First Main map
        WarpPipe wp1gm1 = new WarpPipe();
        WarpPipe wp2gm1 = new WarpPipe();

        // Set teleport Action
        // TeleportAction parameters:sourceLocation, destinationLocation, sourceWarpPipe, destinationWarpPipe
        // game map 1
        wp1gm1.setTeleportAction( new TeleportAction( gameMap.at(42,8), gameMap2.at(0,0),wp1gm1,wp1gm2));
        wp2gm1.setTeleportAction( new TeleportAction( gameMap.at(42,16), gameMap2.at(0,0),wp2gm1,wp1gm2));

        // game map 2
        // initialise 2nd map's default teleport destination to Warp Pipe 1 in game map 1
        wp1gm2.setTeleportAction( new TeleportAction( gameMap2.at(0,0),gameMap.at(42,8),wp1gm2,wp1gm1));

        // Placing warp pipes into respective maps
        gameMap2.at(0,0).setGround(wp1gm2);
        gameMap.at(42,8).setGround(wp1gm1);
        gameMap.at(42,16).setGround(wp2gm1);


        world.run();

    }
}
