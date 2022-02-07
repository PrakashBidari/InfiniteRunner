package com.infiniterunner.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.infiniterunner.InfiniteRunner;
import com.infiniterunner.assets.AssetDescriptors;
import com.infiniterunner.screen.menu.HudScreen;
import com.infiniterunner.tools.WorldContactListener;

public class GameScreen extends ScreenAdapter {
    private GameRenderer renderer;
    private GameController controller;
    private World world;

    private InfiniteRunner game;
    private AssetManager assetManager;

    private HudScreen hudScreen;
    SpriteBatch batch;
    Texture cTexture;

    public GameScreen(InfiniteRunner game, AssetManager assetManager) {
        this.game = game;
        this.assetManager = assetManager;
        this.batch = game.getBatch();
        loadAssetManager();

        world = new World(new Vector2(0, -35), true);

        world.setContactListener(new WorldContactListener());

        cTexture = new Texture("badlogic.jpg");

    }

    @Override
    public void show() {
        controller = new GameController(world,this);
        renderer = new GameRenderer(batch, assetManager, this);
        hudScreen = new HudScreen(this);
    }

    @Override
    public void render(float delta) {
        controller.update(delta);
        renderer.render(delta);

        game.getBatch().setProjectionMatrix(hudScreen.stage.getCamera().combined);

        hudScreen.stage.draw();
    }

    private void loadAssetManager() {
        assetManager.finishLoading();
    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }


    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        world.dispose();
    }

    public InfiniteRunner getGame() {
        return game;
    }

    public GameController getController() {
        return controller;
    }

    public GameRenderer getRenderer() {
        return renderer;
    }
}
