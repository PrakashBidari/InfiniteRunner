package com.infiniterunner.screen.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.infiniterunner.assets.AssetDescriptors;
import com.infiniterunner.config.GameConfig;
import com.infiniterunner.entity.Background;
import com.infiniterunner.entity.Blocks;
import com.infiniterunner.entity.Coin;
import com.infiniterunner.entity.HudCoin;
import com.infiniterunner.util.GdxUtils;

public class GameRenderer implements Disposable {

    private GameController controller;
    private SpriteBatch batch;
    private AssetManager assetManager;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Box2DDebugRenderer b2dr;
    HudCoin hudCoin;

    private Background background;

    private TextureAtlas gamePlayAtlas;
    private TextureRegion texture;
    Texture cTexture;

    GameScreen screen;

    public GameRenderer(SpriteBatch batch, AssetManager assetManager, GameScreen screen) {
        this.batch = batch;
        this.assetManager = assetManager;
        this.screen = screen;
        this.controller = screen.getController();
        cTexture = new Texture("badlogic.jpg");




        init();
        hudCoin = new HudCoin(screen, camera, camera.position.x + 15, camera.position.y + 18);
    }


    private void init() {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera);
        b2dr = new Box2DDebugRenderer();

        background = new Background(screen,camera);

        background.setPosition(0, 0);
        background.setSize(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);


        TextureAtlas gamePlayAtlas = assetManager.get(AssetDescriptors.SPRITE);
        texture = gamePlayAtlas.findRegion("coin");

    }

    //==public method==
    public void render(float delta) {
        camera.update();
        hudCoin.update(delta);

        GdxUtils.clearScreen();
        b2dr.render(controller.getWorld(), camera.combined);

        background.render(delta);

        if (controller.getPlayer().body.getPosition().x > 15) {
            camera.position.set(controller.getPlayer().body.getPosition().x, camera.position.y, 0);
        }

        //camera.position.set(controller.getPlayer().body.getPosition().x, controller.getPlayer().body.getPosition().y, 0);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();


        drawGamePlay();

        batch.end();
    }


    private void drawGamePlay() {
        viewport.apply();

        controller.getPlayer().draw(batch);
        for (Blocks block : controller.getCreator().getBlocks()) {
            block.draw(batch);
        }
        for (Coin coin : controller.getCreator().getCoins()) {
            coin.draw(batch);
        }
        hudCoin.draw(batch);

        System.out.println();

    }


    public void resize(int width, int height) {
        viewport.apply();
        viewport.update(width, height, true);
    }


    @Override
    public void dispose() {
        for (Coin coin : controller.getCreator().getCoins()) {
            coin.dispose();
        }
        b2dr.dispose();
    }

    public HudCoin getHudCoin() {
        return hudCoin;
    }
}
