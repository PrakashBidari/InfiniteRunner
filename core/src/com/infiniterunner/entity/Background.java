package com.infiniterunner.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.infiniterunner.assets.AssetDescriptors;
import com.infiniterunner.config.GameConfig;
import com.infiniterunner.screen.game.GameScreen;


public class Background {

    //private Viewport viewport;
    private float x;
    private float y;
    private float width;
    private float height;
    GameScreen screen;
    OrthographicCamera camera;

    private TextureRegion backgroundRegion;

    public Background(GameScreen screen,OrthographicCamera camera) {
        this.screen = screen;
        this.camera = camera;
//        viewport = new FitViewport(GameConfig.WORLD_WIDTH , GameConfig.WORLD_HEIGHT , new OrthographicCamera());
//        camera.position.set(viewport.getWorldWidth(), viewport.getWorldHeight() , 0);

        TextureAtlas gamePlayAtlas = screen.getGame().getAssetManager().get(AssetDescriptors.SPRITE);
        backgroundRegion = gamePlayAtlas.findRegion("back");

    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;


    }

    public void setSize(float width, float height) {
        //viewport.apply();
        this.width = width;
        this.height = height;

    }

    public void render(float dt) {
        update(dt);
        screen.getGame().getBatch().setProjectionMatrix(camera.combined);
        screen.getGame().getBatch().begin();
        screen.getGame().getBatch().draw(backgroundRegion, camera.position.x-15, 0,
                GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT);
        screen.getGame().getBatch().end();
    }

    private void update(float dt){
        setPosition(camera.position.x - 0.8f,  18.5f);
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
