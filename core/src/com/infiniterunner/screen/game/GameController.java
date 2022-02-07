package com.infiniterunner.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.infiniterunner.config.GameConfig;
import com.infiniterunner.entity.Background;
import com.infiniterunner.entity.Blocks;
import com.infiniterunner.entity.Coin;
import com.infiniterunner.entity.EntityCreater;
import com.infiniterunner.entity.Player;
import com.infiniterunner.screen.menu.HudScreen;

public class GameController {
    private Player player;
    private Array<Blocks> blocks;
    private World world;
    private float speed = 3f;


    public EntityCreater creator;

    private float gap;
    GameScreen screen;

    public GameController(World world, GameScreen screen) {
        this.world = world;
        this.screen = screen;
        player = new Player(this,world, GameConfig.WORLD_CENTER_X - 14, GameConfig.WORLD_CENTER_Y - 4);
        blocks = new Array<>();

        creator = new EntityCreater(world, screen);


    }


    //==public method==
    public void update(float delta) {
        gap += delta;
        world.step(1 / 60f, 6, 2);


        creator.createBlocks();
        for (Blocks block : creator.getBlocks()) {
            block.update(delta);
        }
        for (Coin coin : creator.getCoins()) {
            coin.update(delta);
        }
        player.update(delta);

        speedManager(delta);
        inputHandler();

        HudScreen.addDistance((int) player.body.getPosition().x);
    }

    private void speedManager(float delta) {
        if (gap >= 20) {
            speed = 4f;
        }
        if (gap >= 60) {
            speed = 5;
        }
        if (gap >= 100) {
            speed = 5.5f;
        }
        if (gap >= 150) {
            speed = 6;
        }
    }

    private void inputHandler() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.body.applyLinearImpulse(new Vector2(0, 20f), player.body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.body.getLinearVelocity().x >= -2) {
            player.body.applyLinearImpulse(new Vector2(-8f, 0), player.body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.body.getLinearVelocity().x <= 2) {
            player.body.applyLinearImpulse(new Vector2(8f, 0), player.body.getWorldCenter(), true);
        }

        if (Gdx.input.justTouched()) {
            player.body.applyLinearImpulse(new Vector2(0, 20f), player.body.getWorldCenter(), true);
        }

        if (player.body.getLinearVelocity().x <= speed) {
            player.body.applyLinearImpulse(new Vector2(speed, 0), player.body.getWorldCenter(), true);
        }


    }


    public GameScreen getScreen() {
        return screen;
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public Array<Blocks> getBlocks() {
        return blocks;
    }

    public EntityCreater getCreator() {
        return creator;
    }

    public float getSpeed() {
        return speed;
    }

}
