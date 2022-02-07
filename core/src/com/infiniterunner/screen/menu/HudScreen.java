package com.infiniterunner.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.infiniterunner.config.GameConfig;
import com.infiniterunner.entity.HudCoin;
import com.infiniterunner.screen.game.GameScreen;

public class HudScreen implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private static Integer score;
    private static Integer coin;
    private static Integer distance;

    static Label coinLabel;
    static Label scoreLabel;
    static Label distanceLabel;

    Label distanceText;
    Label coinText;
    Label scoreText;

    GameScreen screen;

    HudCoin hudCoin;


    public HudScreen(GameScreen screen) {
        this.screen = screen;


        worldTimer = 300;
        timeCount = 0;
        distance = 0;
        score = 0;
        coin = 0;

        viewport = new FitViewport(GameConfig.HUD_WIDTH / 2, GameConfig.HUD_HEIGHT / 2, new OrthographicCamera());
        stage = new Stage(viewport, screen.getGame().getBatch());

        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.top();
        table.setFillParent(true);


        distanceText = new Label("DISTANCE", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
//        coinText = new Label("COIN", new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        scoreText = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.GOLD));

        coinLabel = new Label(String.format("%03d", coin), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
        distanceLabel = new Label(String.format("%06d", distance), new Label.LabelStyle(new BitmapFont(), Color.GOLD));

        table.add(scoreText).expandX().padTop(10);
        table.add(coinText).expandX().padTop(10);
        table.add(distanceText).expandX().padTop(10);

        table.row();

        table.add(scoreLabel).expandX();
        table.add(coinLabel).expandX();
        table.add(distanceLabel).expandX();

        stage.addActor(table);

    }


    public void update(float dt) {

    }

    public static void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    public static void addCoin(int value) {
        coin += value;
        coinLabel.setText(String.format("%03d", coin));
    }

    public static void addDistance(int value) {
        distance = value;
        distanceLabel.setText(String.format("%06d", distance));
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
