package com.infiniterunner.config;

public class GameConfig {
    public static final float WIDTH = 800f;// pixel
    public static final float HEIGHT = 480f;// fixel

    public static final float WORLD_WIDTH = 30f;//world unit 30
    public static final float WORLD_HEIGHT = 20f; //world unit 20



    public static final float HUD_WIDTH = 800f;//world unit
    public static final float HUD_HEIGHT = 480f;//world unit

    public static final float WORLD_CENTER_X = WORLD_WIDTH / 2f;//world unit
    public static final float WORLD_CENTER_Y = WORLD_HEIGHT / 2f;//world unit


    public static final short PLAYER_BIT = 4;
    public static final short BLOCK_BIT = 8;
    public static final short COIN_BIT = 16;
    public static final short HUD_COIN_BIT = 32;
}
