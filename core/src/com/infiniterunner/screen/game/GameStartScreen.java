package com.infiniterunner.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.infiniterunner.InfiniteRunner;
import com.infiniterunner.config.GameConfig;


public class GameStartScreen implements Screen {
    private Viewport viewport;
    private Stage stage;
    private InfiniteRunner game;

    public GameStartScreen(InfiniteRunner game) {
        this.game = game;
        viewport = new FitViewport(GameConfig.HUD_WIDTH/2, GameConfig.HUD_HEIGHT/2, new OrthographicCamera());
        stage = new Stage(viewport, game.getBatch());

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.CHARTREUSE);
        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameLabel = new Label("InfiniteRunner", font);
        Label playLabel = new Label("Click to Play Again", font);
        Label GameDescreptionLabel = new Label("        This Game Is Developed By Mr.Prakash Bidari.\n" +
                "The Objective of this game is to run and collect coin.\n" +
                "coin can be use to unlock different item.These item\n" +
                "These item can be used in game for fighting", font);


        table.add(gameLabel).expandX();
        table.row();
        table.add(playLabel).expandX().padTop(10f);
        table.row();
        table.add(GameDescreptionLabel).expandX().padTop(10f);


        stage.addActor(table);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game,game.getAssetManager()));
            dispose();
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
