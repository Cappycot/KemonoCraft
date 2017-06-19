package io.github.cappycot.kemonocraft.worldgen;

import static net.minecraftforge.common.ChestGenHooks.DUNGEON_CHEST;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ChestGenHooks;

public class JapariCafeGenerator extends WorldGenerator implements IWorldGenerator {

	private class BlockPos {
		int x;
		int y;
		int z;

		public BlockPos(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	public enum FacingDirection {
		NORTH(1, 3), EAST(0, 0), SOUTH(3, 2), WEST(2, 1);
		public final int dir;
		public final int mcdir;

		private FacingDirection(int usefuldir, int uselessdir) {
			this.dir = usefuldir;
			this.mcdir = uselessdir;
		}

		public static FacingDirection getDir(int usefuldir) {
			switch (usefuldir) {
			case 0:
				return EAST;
			case 1:
				return NORTH;
			case 2:
				return WEST;
			default:
				return SOUTH;
			}
		}
	}

	/**
	 * Typically only Extreme Hills and Extreme Hills Edge can have plateau-like
	 * areas at greater heights.
	 */
	public static final int MIN_CAFE_HEIGHT = 100;
	/**
	 * Maximum difference between the entrance height and the idk what to call
	 * it back end.
	 */
	public static final int MAX_LEDGE_DIFF = 16;
	public static final int MAX_LIFT_DIFF = 2;
	/**
	 * Yee don't edit this unless you want the place looking differently...
	 */
	public static final int[] WOOD_DECK_LENGTHS = { 4, 5, 6, 6, 7, 7, 8, 8, 8, 8, 8, 7, 7, 6, 6, 5, 4 };
	public static final boolean[] WOOD_DECK_FENCE = { true, true, true, false, true, false, true, false, false, false,
			true, false, true, false, true, true, true };

	private BlockPos getBlockPosAt(int x, int y, int z, int xInc, int zInc, FacingDirection dir) {
		switch (dir) {
		case WEST:
			x += zInc;
			z += xInc;
			break;
		case SOUTH:
			x += xInc;
			z -= zInc;
			break;
		case EAST:
			x -= zInc;
			z -= xInc;
			break;
		case NORTH:
			x -= xInc;
			z += zInc;
			break;
		}
		return new BlockPos(x, y, z);
	}

	private boolean getLowerBound(World world, BlockPos pos) {
		// This method get the position right above the topmost solid/liquid
		// block so decrement.
		pos.y = world.getTopSolidOrLiquidBlock(pos.x, pos.z) - 1;
		if (world.getBlock(pos.x, pos.y, pos.z).getMaterial().isLiquid()) {
			pos.y--;
			if (world.getBlock(pos.x, pos.y, pos.z).getMaterial().isLiquid())
				return false;
			pos.y++;
			return true;
		}
		return true;
	}

	private int getLowerBound(World world, int x, int z) {
		// This method get the position right above the topmost solid/liquid
		// block so decrement.
		int y = world.getTopSolidOrLiquidBlock(x, z) - 1;
		if (world.getBlock(x, y, z).getMaterial().isLiquid()) {
			y--;
			if (world.getBlock(x, y, z).getMaterial().isLiquid())
				return -1;
			return y + 1;
		}
		return y;
	}

	private int getLowerBound(World world, int x, int z, int xInc, int zInc, FacingDirection dir) {
		BlockPos pos = getBlockPosAt(x, 0, z, xInc, zInc, dir);
		return getLowerBound(world, pos.x, pos.z);
	}

	private boolean setBlock(World world, int x, int y, int z, Block block, int metadata, boolean override) {
		Block current = world.getBlock(x, y, z);
		if (override || current.isAir(world, x, y, z) || current.isLeaves(world, x, y, z) || current == Blocks.log
				|| current == Blocks.snow_layer || current == Blocks.tallgrass) {
			world.setBlock(x, y, z, block, metadata, 2);
			return true;
		}
		return false;
	}

	private boolean setBlockAt(World world, int x, int y, int z, int xInc, int zInc, FacingDirection dir, Block block,
			int metadata, boolean override) {
		BlockPos pos = getBlockPosAt(x, y, z, xInc, zInc, dir);
		return setBlock(world, pos.x, pos.y, pos.z, block, metadata, override);
	}

	private boolean generate(World world, Random random, int x, int z, FacingDirection dir) {
		// Check 4 corners.
		int y1 = getLowerBound(world, x, z, 0, 0, dir);
		int y2 = getLowerBound(world, x, z, 10, 0, dir);
		int y3 = getLowerBound(world, x, z, 0, 16, dir);
		int y4 = getLowerBound(world, x, z, 10, 16, dir);

		// BlockPos p1 = getBlockPosAt(x, y1, z, 0, 0, dir);
		// BlockPos p12 = getBlockPosAt(x, y1, z, 1, 0, dir);
		// BlockPos p13 = getBlockPosAt(x, y1, z, 0, 1, dir);
		// BlockPos p2 = getBlockPosAt(x, y2, z, 10, 0, dir);
		// BlockPos p3 = getBlockPosAt(x, y3, z, 0, 16, dir);
		// BlockPos p4 = getBlockPosAt(x, y4, z, 10, 16, dir);

		// y1 must be a certain height or greater.
		if (y1 < MIN_CAFE_HEIGHT || y2 == -1 || y3 == -1 || y4 == -1)
			return false;
		// In certain instances we see strange generation.
		if (world.getBlock(x, y1 - 1, z) == Blocks.air)
			return false;
		// y2 must be similar height to y1. y3 and y4 cannot be more than two
		// blocks higher than y1.
		if (y1 + 1 < y2 || y1 + MAX_LIFT_DIFF < y3 || y1 + MAX_LIFT_DIFF < y4)
			return false;
		// y2 must be similar height to y1, y3 and y4 must not exceed ledge
		// difference.
		if (y1 - 1 > y2 || y1 - y3 > MAX_LEDGE_DIFF || y1 - y4 > MAX_LEDGE_DIFF)
			return false;

		// setBlock(world, p1.x, p1.y, p1.z, Blocks.wool, 0, true);
		// setBlock(world, p1.x, p1.y + 1, p1.z, Blocks.wool, 0, true);
		// setBlock(world, p1.x, p1.y + 2, p1.z, Blocks.wool, 0, true);
		// setBlock(world, p12.x, p12.y, p12.z, Blocks.wool, 0, true);
		// setBlock(world, p13.x, p13.y, p13.z, Blocks.wool, 0, true);
		// setBlock(world, p2.x, p2.y, p2.z, Blocks.wool, 1, true);
		// setBlock(world, p3.x, p3.y, p3.z, Blocks.wool, 2, true);
		// setBlock(world, p4.x, p4.y, p4.z, Blocks.wool, 3, true);

		// Check entry level.
		y3 = getLowerBound(world, x, z, 3, -1, dir);
		y4 = getLowerBound(world, x, z, 4, -1, dir);
		if (Math.abs(y3 - y1) > 1 || Math.abs(y4 - y1) > 1)
			return false;

		// BlockPos leftEntry = getBlockPosAt(x, y3, z, 3, -1, dir);
		// BlockPos rightEntry = getBlockPosAt(x, y4, z, 4, -1, dir);
		// if (y3 != -1)
		// setBlock(world, leftEntry.x, leftEntry.y, leftEntry.z, Blocks.wool,
		// Math.abs(y3 - y1) <= 1 ? 13 : 14, true);
		// if (y4 != -1)
		// setBlock(world, rightEntry.x, rightEntry.y, rightEntry.z,
		// Blocks.wool, Math.abs(y4 - y1) <= 1 ? 13 : 14,
		// true);

		// Check wood deck area
		BlockPos check;
		for (int i = 0; i < WOOD_DECK_LENGTHS.length; i++) {
			check = getBlockPosAt(x, 1, z, i - 3, 16 + WOOD_DECK_LENGTHS[i], dir);
			// pass = getLowerBound(world, check) && y1 - check.y <=
			// MAX_LEDGE_DIFF
			// && y1 + MAX_LIFT_DIFF >= check.y;
			// setBlock(world, check.x, check.y, check.z, Blocks.wool, pass ? 13
			// : 14, true);
			if (!getLowerBound(world, check) || y1 - check.y > MAX_LEDGE_DIFF || check.y - y1 > MAX_LIFT_DIFF)
				return false;
			if (i == 0 || i == WOOD_DECK_LENGTHS.length - 1) {
				check = getBlockPosAt(x, 1, z, i - 3, 17, dir);
				if (!getLowerBound(world, check) || y1 - check.y > MAX_LEDGE_DIFF || check.y - y1 > MAX_LIFT_DIFF)
					return false;
				// pass = getLowerBound(world, check) && y1 - check.y <=
				// MAX_LEDGE_DIFF && y1 + MAX_LIFT_DIFF >= check.y;
				// setBlock(world, check.x, check.y, check.z, Blocks.wool, pass
				// ? 13 : 14, true);
			}
		}

		// Begin construction. Clear entry.
		BlockPos leftEntry = getBlockPosAt(x, y3, z, 3, -1, dir);
		BlockPos rightEntry = getBlockPosAt(x, y4, z, 4, -1, dir);
		setBlockAt(world, x, y1 + 1, z, 3, -1, dir, Blocks.air, 0, true);
		setBlockAt(world, x, y1 + 1, z, 4, -1, dir, Blocks.air, 0, true);

		// Cobblestone foundation.
		BlockPos support;
		for (int i = 0; i < 11; i++) {
			for (int k = 0; k < 17; k++) {
				setBlockAt(world, x, y1, z, i, k, dir, Blocks.cobblestone, 0, true);
				// Supporting bricks.
				support = getBlockPosAt(x, y1 - 1, z, i, k, dir);
				for (; !world.getBlock(support.x, support.y, support.z).getMaterial().isSolid(); support.y--)
					setBlock(world, support.x, support.y, support.z, Blocks.stonebrick, 0, true);
			}
		}

		// Wooden deck.
		for (int i = 0; i < WOOD_DECK_LENGTHS.length; i++) {
			for (int k = 0; k < WOOD_DECK_LENGTHS[i]; k++) {
				setBlockAt(world, x, y1, z, i - 3, k + 17, dir, Blocks.planks, 0, true);
				// Supporting bricks.
				support = getBlockPosAt(x, y1 - 1, z, i - 3, k + 17, dir);
				for (; !world.getBlock(support.x, support.y, support.z).getMaterial().isSolid(); support.y--)
					setBlock(world, support.x, support.y, support.z, Blocks.stonebrick, 0, true);
				support = getBlockPosAt(x, y1 + 1, z, i - 3, k + 17, dir);
				// Clear area above if needed.
				if (k == WOOD_DECK_LENGTHS[i] - 1 || k == WOOD_DECK_LENGTHS[i] - 2 && WOOD_DECK_FENCE[i]) {
					setBlock(world, support.x, support.y, support.z, Blocks.fence, 0, true);
					support.y++;
				}
				for (int j = 0; j < MAX_LIFT_DIFF
						|| world.getBlock(support.x, support.y, support.z) != Blocks.air; j++, support.y++)
					setBlock(world, support.x, support.y, support.z, Blocks.air, 0, true);
			}
			// Deck extension along lengths of building...
			if (i < 3 || i > WOOD_DECK_LENGTHS.length - 4) {
				support = getBlockPosAt(x, y1, z, i - 3, 16, dir);
				for (int k = 16; k >= 0 && setBlock(world, support.x, support.y, support.z, Blocks.planks, 0, false);) {
					support.y--;
					for (; setBlock(world, support.x, support.y, support.z, Blocks.stonebrick, 0, false); support.y--)
						;
					k--;
					support = getBlockPosAt(x, y1, z, i - 3, k, dir);
				}
			}
		}

		// Floor slabs.
		for (int i = 1; i < 10; i++) {
			for (int k = 1; k < 16; k++) {
				setBlockAt(world, x, y1 + 1, z, i, k, dir, Blocks.stone_slab, 3, true);
			}
		}
		// Clear interior.
		for (int k = 1; k < 16; k++) {
			for (int i = 1; i < 10; i++) {
				for (int j = 2; j < 9; j++) {
					setBlockAt(world, x, y1 + j, z, i, k, dir, Blocks.air, 0, true);
				}
			}
			for (int i = 3; i < 8; i++) {
				for (int j = 9; j < 13; j++) {
					setBlockAt(world, x, y1 + j, z, i, k, dir, Blocks.air, 0, true);
				}
			}
		}

		// Sandstone walls.
		for (int j = 1; j < 6; j++) {
			for (int i = 0; i < 11; i++) {
				if (i == 0 || i == 10) {
					for (int k = 0; k < 17; k++) {
						setBlockAt(world, x, y1 + j, z, i, k, dir, Blocks.sandstone, 0, true);
					}
				} else {
					setBlockAt(world, x, y1 + j, z, i, 0, dir, Blocks.sandstone, 0, true);
					setBlockAt(world, x, y1 + j, z, i, 16, dir, Blocks.sandstone, 0, true);
				}
			}
			// Extra pillar by storage thing?
			setBlockAt(world, x, y1 + j, z, 9, 15, dir, Blocks.sandstone, 0, true);
		}

		// Entrances.
		int front = (6 - dir.dir) % 4; // Convert c-clockwise to clockwise
										// rotation.
		int back = (front + 2) % 4;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < 2; k++) {
					setBlockAt(world, x, y1 + 1 + j, z, 3 + i, 0 + k, dir, Blocks.air, 0, true);
					setBlockAt(world, x, y1 + 1 + j, z, 5 + i, 15 + k, dir, Blocks.air, 0, true);
				}
			}
		}
		support = getBlockPosAt(x, y1 + 1, z, 3, 0, dir);
		ItemDoor.placeDoorBlock(world, support.x, support.y, support.z, front, Blocks.wooden_door);
		support = getBlockPosAt(x, y1 + 1, z, 4, 0, dir);
		ItemDoor.placeDoorBlock(world, support.x, support.y, support.z, front, Blocks.wooden_door);
		support = getBlockPosAt(x, y1 + 1, z, 5, 16, dir);
		ItemDoor.placeDoorBlock(world, support.x, support.y, support.z, back, Blocks.wooden_door);
		support = getBlockPosAt(x, y1 + 1, z, 6, 16, dir);
		ItemDoor.placeDoorBlock(world, support.x, support.y, support.z, back, Blocks.wooden_door);

		// Entrance front decor.
		switch (dir) {
		case EAST:
		case WEST:
			front = 4;
			break;
		default:
			front = 8;
			break;
		}
		for (int i = 3; i < 5; i++) {
			setBlockAt(world, x, y1 + 3, z, i, 0, dir, Blocks.stained_glass, 12, true);
			setBlockAt(world, x, y1 + 4, z, i, 0, dir, Blocks.log, front, true);
		}
		for (int j = 2; j < 4; j++) {
			setBlockAt(world, x, y1 + j, z, 1, 0, dir, Blocks.stained_glass_pane, 12, true);
			setBlockAt(world, x, y1 + j, z, 2, 0, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + j, z, 7, 0, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + j, z, 8, 0, dir, Blocks.stained_glass_pane, 12, true);
			setBlockAt(world, x, y1 + j, z, 9, 0, dir, Blocks.planks, 1, true);
		}
		// Entrance back decor.
		for (int i = 5; i < 7; i++) {
			setBlockAt(world, x, y1 + 3, z, i, 16, dir, Blocks.stained_glass, 12, true);
			setBlockAt(world, x, y1 + 4, z, i, 16, dir, Blocks.log, front, true);
		}
		for (int j = 2; j < 4; j++) {
			setBlockAt(world, x, y1 + j, z, 1, 16, dir, Blocks.planks, 1, true);
			for (int i = 2; i < 4; i++)
				setBlockAt(world, x, y1 + j, z, i, 16, dir, Blocks.stained_glass_pane, 12, true);
			setBlockAt(world, x, y1 + j, z, 7, 16, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + j, z, 8, 16, dir, Blocks.stained_glass_pane, 12, true);
			setBlockAt(world, x, y1 + j, z, 9, 16, dir, Blocks.planks, 1, true);
		}
		setBlockAt(world, x, y1 + 1, z, 7, 16, dir, Blocks.planks, 1, true);
		// Front awning.
		front = calcStairFace(FacingDirection.SOUTH, dir) + 4;
		for (int i = 2; i < 9; i++) {
			setBlockAt(world, x, y1 + 5, z, i, -2, dir, Blocks.stained_hardened_clay, 3, true);
			setBlockAt(world, x, y1 + 5, z, i, -1, dir, Blocks.quartz_stairs, front, true);
			setBlockAt(world, x, y1 + 6, z, i, -1, dir, Blocks.stained_hardened_clay, 3, true);
		}

		// Front and back walls.
		for (int j = 1; j < 6; j++) {
			setBlockAt(world, x, y1 + j, z, 5, 0, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + j, z, 4, 16, dir, Blocks.planks, 1, true);
		}
		patternThingy(world, x, y1, z, dir, false);
		patternThingy(world, x, y1, z, dir, true);

		// Create interior.
		front = calcStairFace(FacingDirection.SOUTH, dir);
		back = calcStairFace(FacingDirection.NORTH, dir);
		int left = calcStairFace(FacingDirection.WEST, dir);
		int right = calcStairFace(FacingDirection.EAST, dir);

		// Bottom right corner.
		setBlockAt(world, x, y1 + 1, z, 9, 1, dir, Blocks.planks, 1, true);
		setBlockAt(world, x, y1 + 2, z, 9, 1, dir, Blocks.spruce_stairs, right + 4, true);
		check = getBlockPosAt(x, y1 + 3, z, 9, 1, dir);
		setBlock(world, check.x, check.y, check.z, Blocks.chest, 0, true);
		// Rotate the damn chest with this method because setBlock can't
		// overrule stuff.
		world.setBlockMetadataWithNotify(check.x, check.y, check.z, 5 - left, 2);
		TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(check.x, check.y, check.z);
		if (tileentitychest != null)
			WeightedRandomChestContent.generateChestContents(random, ChestGenHooks.getItems(DUNGEON_CHEST, random),
					tileentitychest, ChestGenHooks.getCount(DUNGEON_CHEST, random));
		setBlockAt(world, x, y1 + 1, z, 8, 3, dir, Blocks.planks, 1, true);
		setBlockAt(world, x, y1 + 2, z, 8, 3, dir, Blocks.jukebox, 0, true);
		setBlockAt(world, x, y1 + 3, z, 8, 3, dir, Blocks.planks, 1, true);

		// Large connected tables.
		for (int k = 3; k < 8; k++) {
			if (k == 5) {
				setBlockAt(world, x, y1 + 1, z, 4, 5, dir, Blocks.stone_slab, 3, true);
				setBlockAt(world, x, y1 + 2, z, 4, 5, dir, Blocks.wooden_slab, 9, true);
				setBlockAt(world, x, y1 + 1, z, 3, 5, dir, Blocks.spruce_stairs, left, true);
				setBlockAt(world, x, y1 + 2, z, 3, 5, dir, Blocks.spruce_stairs, left + 4, true);
				setBlockAt(world, x, y1 + 1, z, 5, 5, dir, Blocks.spruce_stairs, right, true);
				setBlockAt(world, x, y1 + 2, z, 5, 5, dir, Blocks.spruce_stairs, right + 4, true);
			} else {
				if (k % 3 == 0) {
					setBlockAt(world, x, y1 + 1, z, 4, k, dir, Blocks.spruce_stairs, front, true);
					setBlockAt(world, x, y1 + 1, z, 4, k + 1, dir, Blocks.spruce_stairs, back, true);
					// +4 to metadata means upside-down.
					setBlockAt(world, x, y1 + 2, z, 4, k, dir, Blocks.spruce_stairs, front + 4, true);
					setBlockAt(world, x, y1 + 2, z, 4, k + 1, dir, Blocks.spruce_stairs, back + 4, true);
				}
				setBlockAt(world, x, y1 + 1, z, 3, k, dir, Blocks.planks, 1, true);
				setBlockAt(world, x, y1 + 1, z, 5, k, dir, Blocks.planks, 1, true);
			}
		}

		// Table next to bar.
		setBlockAt(world, x, y1 + 1, z, 1, 10, dir, Blocks.planks, 1, true);
		setBlockAt(world, x, y1 + 1, z, 1, 12, dir, Blocks.spruce_stairs, front, true);
		setBlockAt(world, x, y1 + 1, z, 1, 11, dir, Blocks.spruce_stairs, back, true);
		// +4 to metadata means upside-down.
		setBlockAt(world, x, y1 + 2, z, 1, 12, dir, Blocks.spruce_stairs, front + 4, true);
		setBlockAt(world, x, y1 + 2, z, 1, 11, dir, Blocks.spruce_stairs, back + 4, true);
		setBlockAt(world, x, y1 + 1, z, 1, 13, dir, Blocks.planks, 1, true);

		// Bar area.
		setBlockAt(world, x, y1 + 1, z, 4, 12, dir, Blocks.spruce_stairs, back, true);
		for (int k = 10; k < 14; k++) {
			setBlockAt(world, x, y1 + 1, z, 5, k, dir, Blocks.spruce_stairs, right, true);
			setBlockAt(world, x, y1 + 1, z, 7, k, dir, Blocks.stone_stairs, left, true);
		}
		for (int j = 1; j < 3; j++) {
			for (int i = 4; i < 7; i++)
				setBlockAt(world, x, y1 + j, z, i, 13, dir, Blocks.planks, 1, true);
			for (int k = 10; k < 13; k++)
				setBlockAt(world, x, y1 + j, z, 6, k, dir, Blocks.planks, 1, true);
		}

		// Shelves and stuff behind bar.
		for (int j = 1; j < 5; j++) {
			setBlockAt(world, x, y1 + j, z, 9, 10, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + j, z, 9, 14, dir, Blocks.planks, 1, true);
			for (int k = 11; k < 14; k++) {
				switch (j) {
				case 1:
					setBlockAt(world, x, y1 + j, z, 9, k, dir, Blocks.planks, 1, true);
					break;
				case 2:
					setBlockAt(world, x, y1 + j, z, 9, k, dir, Blocks.cauldron, random.nextInt(4), true);
					break;
				default:
					setBlockAt(world, x, y1 + j, z, 9, k, dir, Blocks.spruce_stairs, right + 4, true);
					break;
				}
			}
		}

		// Bottom of stairs.
		setBlockAt(world, x, y1 + 1, z, 8, 7, dir, Blocks.planks, 1, true);
		setBlockAt(world, x, y1 + 1, z, 9, 7, dir, Blocks.planks, 1, true);
		setBlockAt(world, x, y1 + 1, z, 8, 8, dir, Blocks.stone_stairs, front, true);
		setBlockAt(world, x, y1 + 1, z, 9, 8, dir, Blocks.stone_stairs, front, true);
		// Unintentional "C++" pun thing.
		for (int c = 0; c < 3; c++) {
			// I think I'm getting addicted to using for loops for even two
			// iterations and I think it's sickening.
			for (int i = 8; i < 10; i++) {
				setBlockAt(world, x, y1 + 2 + c, z, i, 7 - c, dir, Blocks.spruce_stairs, front, true);
				setBlockAt(world, x, y1 + 2 + c, z, i, 6 - c, dir, Blocks.spruce_stairs, back + 4, true);
			}
		}

		// Turn at stairs.
		for (int k = 1; k < 5; k++) {
			setBlockAt(world, x, y1 + 4, z, 8, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 4, z, 9, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 5, z, 9, k, dir, Blocks.planks, 1, true);
		}
		setBlockAt(world, x, y1 + 4, z, 9, 1, dir, Blocks.wooden_slab, 1, true);
		setBlockAt(world, x, y1 + 5, z, 9, 5, dir, Blocks.spruce_stairs, front + 4, true);
		setBlockAt(world, x, y1 + 4, z, 7, 1, dir, Blocks.spruce_stairs, right + 4, true);
		setBlockAt(world, x, y1 + 4, z, 7, 2, dir, Blocks.spruce_stairs, right + 4, true);
		setBlockAt(world, x, y1 + 5, z, 6, 1, dir, Blocks.spruce_stairs, right + 4, true);
		setBlockAt(world, x, y1 + 5, z, 6, 2, dir, Blocks.spruce_stairs, right + 4, true);

		// The start of the second floor.
		for (int i = 1; i < 10; i++)
			for (int k = 1; k < 16; k++)
				setBlockAt(world, x, y1 + 6, z, i, k, dir, Blocks.planks, 1, true);

		// Lights
		for (int i = 3; i < 10; i += 4) {
			for (int k = 2; k < 16; k += 3) {
				setBlockAt(world, x, y1 + 5, z, i, k, dir, Blocks.redstone_lamp, 0, true);
				setBlockAt(world, x, y1 + 6, z, i, k, dir, Blocks.redstone_lamp, 0, true);
			}
		}
		setBlockAt(world, x, y1 + 6, z, 7, 5, dir, Blocks.planks, 1, true); // Erase
																			// light.

		// The rest of the main stairs.
		setBlockAt(world, x, y1 + 5, z, 7, 1, dir, Blocks.spruce_stairs, left, true);
		setBlockAt(world, x, y1 + 5, z, 7, 2, dir, Blocks.spruce_stairs, left, true);
		setBlockAt(world, x, y1 + 5, z, 7, 3, dir, Blocks.planks, 1, true);
		setBlockAt(world, x, y1 + 5, z, 7, 4, dir, Blocks.planks, 1, true);
		setBlockAt(world, x, y1 + 6, z, 6, 1, dir, Blocks.spruce_stairs, left, true);
		setBlockAt(world, x, y1 + 6, z, 6, 2, dir, Blocks.spruce_stairs, left, true);
		setBlockAt(world, x, y1 + 6, z, 7, 1, dir, Blocks.air, 0, true);
		setBlockAt(world, x, y1 + 6, z, 7, 2, dir, Blocks.air, 0, true);
		for (int k = 1; k < 7; k++)
			setBlockAt(world, x, y1 + 6, z, 8, k, dir, Blocks.air, 0, true);

		// Walls of 2nd floor.
		for (int k = 1; k < 16; k++) {
			setBlockAt(world, x, y1 + 7, z, 1, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 7, z, 2, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 8, z, 2, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 9, z, 3, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 10, z, 3, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 11, z, 4, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 12, z, 4, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 13, z, 5, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 12, z, 6, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 11, z, 6, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 10, z, 7, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 9, z, 7, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 8, z, 8, k, dir, Blocks.planks, 1, true);
			if (k > 4)
				setBlockAt(world, x, y1 + 7, z, 8, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 8, z, 8, k, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 7, z, 9, k, dir, Blocks.planks, 1, true);
		}
		// Plus some pillar.
		for (int i = 6; i < 8; i++) {
			for (int k = 3; k < 5; k++) {
				setBlockAt(world, x, y1 + 7, z, i, k, dir, Blocks.planks, 1, true);
				setBlockAt(world, x, y1 + 8, z, i, k, dir, Blocks.planks, 1, true);
			}
		}
		
		// Stairs to 3rd floor thing.
		setBlockAt(world, x, y1 + 7, z, 5, 3, dir, Blocks.spruce_stairs, back, true);
		setBlockAt(world, x, y1 + 7, z, 5, 4, dir, Blocks.planks, 1, true);
		setBlockAt(world, x, y1 + 8, z, 5, 4, dir, Blocks.spruce_stairs, back, true);
		setBlockAt(world, x, y1 + 7, z, 5, 5, dir, Blocks.planks, 1, true);
		setBlockAt(world, x, y1 + 8, z, 5, 5, dir, Blocks.planks, 1, true);
		
		// 3rd floor section.
		
		
		
		// Roofing.
		

		// Test: Seed is -3831134395952103524
		// Test: /tp 0 120 -420
		// Test: or use Tunneler's Dream preset and die to lag.
		System.out.println("Cafe generation at " + x + ", " + z);
		return true;
	}

	/**
	 * Calculate rotation for stairs and redstone torches. Why does 0 = east
	 * then 1 = west in Minecraft blocks? Someone please explain this shitty
	 * mathematical convention.
	 * 
	 * @param stairDir
	 * @param dir
	 * @return
	 */
	public int calcStairFace(FacingDirection faceDir, FacingDirection dir) {
		return FacingDirection.getDir((faceDir.dir + 1 + dir.dir) % 4).mcdir;
	}

	public void patternThingy(World world, int x, int y1, int z, FacingDirection dir, boolean back) {
		int zInc = back ? 0 : 16;
		for (int i = 1; i < 10; i++) {
			setBlockAt(world, x, y1 + 6, z, i, zInc, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 7, z, i, zInc, dir, Blocks.log, 2, true);
			setBlockAt(world, x, y1 + 8, z, i, zInc, dir, Blocks.planks, 1, true);
		}
		for (int i = 2; i < 10; i += 3) {
			setBlockAt(world, x, y1 + 6, z, i, zInc, dir, Blocks.planks, 5, true);
			setBlockAt(world, x, y1 + 7, z, i, zInc, dir, Blocks.planks, 1, true);
			setBlockAt(world, x, y1 + 8, z, i, zInc, dir, Blocks.planks, 5, true);
		}
		for (int i = 3; i < 8; i++) {
			for (int j = 0; j < 3; j += 2) {
				boolean mid = i == 5;
				setBlockAt(world, x, y1 + 9 + j, z, i, zInc, dir, mid ? Blocks.planks : Blocks.log, mid ? 1 : 2, true);
				setBlockAt(world, x, y1 + 10 + j, z, i, zInc, dir, Blocks.planks, mid ? 5 : 1, true);
			}
		}
		setBlockAt(world, x, y1 + 13, z, 5, zInc, dir, Blocks.log, 2, true);
		setBlockAt(world, x, y1 + 13, z, 5, back ? 17 : -1, dir, Blocks.nether_brick_fence, 0, true);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider) {
		this.generate(world, random, chunkX * 16 + random.nextInt(16), 0, chunkZ * 16 + random.nextInt(16));
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		int dir = random.nextInt(4);
		switch (dir) {
		case 0:
			return generate(world, random, x, z, FacingDirection.NORTH);
		case 1:
			return generate(world, random, x, z, FacingDirection.EAST);
		case 2:
			return generate(world, random, x, z, FacingDirection.SOUTH);
		default:
			return generate(world, random, x, z, FacingDirection.WEST);
		}
	}
}
