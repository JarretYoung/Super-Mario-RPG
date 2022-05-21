package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.World;
import game.actions.TeleportAction;
import game.actors.Player;
import game.actors.PrincessPeach;
import game.actors.Toad;
import game.actors.enemies.Bowser;
import game.currency.Coin;
import game.items.PowerStar;
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
                "............+............+..................#...................................",
                "............................................#...................................",
                ".............................................##......................+..........",
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
                "....................................................#...........................",
                "...................+.................................#..........................",
                "......................................................#.........................",
                ".......................................................##.......................");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        Actor mario = new Player("Player", 'm', 1000);
        world.addPlayer(mario, gameMap.at(42, 10));


        Item superMushroom = new SuperMushroom();
        Item powerStar = new PowerStar();
        Item wrench = new Wrench();
        Item coin = new Coin(10);

        Toad toad = new Toad();

        mario.addItemToInventory(wrench);

        gameMap.at(42, 9).addItem(superMushroom);
        gameMap.at(42, 9).addItem(powerStar);
        gameMap.at(42, 9).addItem(coin);
        gameMap.at(41,9).addActor(toad);
        gameMap.at(41,10).setGround(new HealthFountain());
        gameMap.at(40,10).setGround(new PowerFountain());
        //gameMap.at(24,9).addActor(new Toad());



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
                "..........LLLL........................................................",
                "................LLLL..................................................",
                "......................................................................",
                "......................................................................");
        GameMap gameMap2 = new GameMap(groundFactory2, map2);
        world.addGameMap(gameMap2);
        //world.addPlayer(mario, gameMap2.at(0, 1));

        // Adding Princess Peach and Bowser
        gameMap2.at(0,6).addActor(new PrincessPeach());
        gameMap2.at(1,6).addActor(new Bowser(gameMap2,1,6));

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
