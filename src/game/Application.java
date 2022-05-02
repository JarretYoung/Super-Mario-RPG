package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Goomba;
import game.actors.Player;
import game.actors.Toad;
import game.currency.Coin;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Wrench;
import game.nature.Sprout;
import game.surfaces.Dirt;
import game.surfaces.Floor;
import game.surfaces.Wall;


/**
 * The main class for the Mario World game.
 *
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

			Actor mario = new Player("Player", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 10));


			Item superMushroom = new SuperMushroom();
			Item powerStar = new PowerStar();
			Item wrench = new Wrench();
			Item coin = new Coin(10);



			gameMap.at(42,9).addItem(superMushroom);
			gameMap.at(42,9).addItem(powerStar);
			gameMap.at(42,9).addItem(coin);
			gameMap.at(24,9).addActor(new Toad());

//			// FIXME: the Goomba should be generated from the Tree
//			gameMap.at(35, 10).addActor(new Goomba());

			world.run();

	}
}
