package io.github.cappycot.kemonocraft;

public class Resource {

	public static String blockTexture(String name) {
		return KemonoCraft.MOD_ID + ":" + name;
	}

	public static String entityTexture(String name) {
		return KemonoCraft.MOD_ID + ":textures/entity/" + name + ".png";
	}

}
