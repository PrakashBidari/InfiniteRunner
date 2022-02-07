package com.infiniterunner.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.infiniterunner.assets.AssetDescriptors;
import com.infiniterunner.config.GameConfig;
import com.infiniterunner.screen.game.GameScreen;
import com.infiniterunner.screen.menu.HudScreen;

public class Coin extends Sprite{
    public Body body;
    private World world;
    private float x, y;

    private boolean setToDestroy;
    private boolean destroy;
    private boolean moveToHudCoin;


    private GameScreen screen;

    private TextureAtlas gamePlayAtlas;
    private TextureRegion texture;


    public Coin(World world, GameScreen screen, float x, float y) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.screen = screen;

        gamePlayAtlas = screen.getGame().getAssetManager().get(AssetDescriptors.SPRITE);
        texture = gamePlayAtlas.findRegion("coin");


        defineCoin();

        setBounds(x, y, 0.8f, 0.8f);

        System.out.println(getX());


        setToDestroy = false;
        destroy = false;
        moveToHudCoin = false;
    }

    private void defineCoin() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        body = world.createBody(bodyDef);


        FixtureDef fixtureDef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(0.4f);

        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = GameConfig.COIN_BIT;
        fixtureDef.filter.maskBits = GameConfig.HUD_COIN_BIT | GameConfig.PLAYER_BIT;
        fixtureDef.isSensor = true;


        body.createFixture(fixtureDef).setUserData(this);

        shape.dispose();
    }

    public void update(float delta) {
        if (setToDestroy && !destroy) {
            world.destroyBody(body);
            screen.getController().getCreator().removeCoin(this);
            destroy = true;
        } else if (!destroy) {
            followPlayer(screen.getRenderer().getHudCoin().body.getPosition(), body.getPosition());
            setRegion(texture);
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        }

        if (screen.getController().getPlayer().body.getPosition().x - body.getPosition().x >= 14) {
            setToDestroy = true;
        }

        if (body.getPosition().y >= 18) {
            setToDestroy = true;
        }

    }

    public void followPlayer(Vector2 targetBody, Vector2 bullateBody) {
        Vector2 direction = new Vector2();

        if (moveToHudCoin) {
            direction.x = (targetBody.x + 40) - (bullateBody.x + 40);
            direction.y = (targetBody.y + 40) - (bullateBody.y + 40);

            direction.nor();

            body.setTransform(body.getPosition(), body.getAngle() * MathUtils.degreesToRadians);
            body.setLinearVelocity(new Vector2(direction.x * 20.5f, direction.y * 20.5f));

        }
    }

    @Override
    public void draw(Batch batch) {
            super.draw(batch);
    }

    public void onHit() {
        moveToHudCoin = true;
        HudScreen.addScore(10);
        HudScreen.addCoin(1);
    }

    public void onHudCoinHit() {
        setToDestroy = true;
    }

    public void dispose() {

    }
}
