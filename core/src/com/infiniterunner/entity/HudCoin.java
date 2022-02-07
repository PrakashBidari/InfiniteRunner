package com.infiniterunner.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.infiniterunner.assets.AssetDescriptors;
import com.infiniterunner.config.GameConfig;
import com.infiniterunner.screen.game.GameScreen;

public class HudCoin extends Sprite{
    GameScreen screen;
    Body body;
    float x, y;
    OrthographicCamera camera;

    private TextureAtlas gamePlayAtlas;
    private TextureRegion texture;

    public HudCoin(GameScreen screen, OrthographicCamera camera, float x, float y) {
        this.screen = screen;
        this.camera = camera;
        this.x = x;
        this.y = y;

        gamePlayAtlas = screen.getGame().getAssetManager().get(AssetDescriptors.SPRITE);
        texture = gamePlayAtlas.findRegion("coin");

        defineCoin();

        setBounds(getX(), getY(), 1.2f, 1.2f);
    }


    private void defineCoin() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = screen.getController().getWorld().createBody(bodyDef);


        FixtureDef fixtureDef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(0.6f);

        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = GameConfig.HUD_COIN_BIT;
        fixtureDef.filter.maskBits = GameConfig.COIN_BIT;
        fixtureDef.isSensor = true;


        body.createFixture(fixtureDef).setUserData(this);

        shape.dispose();
    }

    public void update(float delta) {
        body.setTransform(new Vector2(camera.position.x - 0.8f,  18.5f), 0);
        setRegion(texture);
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
    }

    public float getX() {
        return camera.position.x - 0.8f;
    }

    public float getY() {
        return y;
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }
}
