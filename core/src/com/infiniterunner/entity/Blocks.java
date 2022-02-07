package com.infiniterunner.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.infiniterunner.assets.AssetDescriptors;
import com.infiniterunner.config.GameConfig;
import com.infiniterunner.screen.game.GameController;

public class Blocks extends Sprite {
    public Body body;
    private World world;
    private float x, y;

    private boolean setToDestroy;
    private boolean destroy;

    GameController controller;
    EntityCreater creater;

    private TextureAtlas gamePlayAtlas;
    private TextureRegion texture;

    public Blocks(World world, GameController controller, EntityCreater creater, float x, float y) {
        this.world = world;
        this.creater = creater;
        this.x = x;
        this.y = y;
        this.controller = controller;

        gamePlayAtlas = controller.getScreen().getGame().getAssetManager().get(AssetDescriptors.SPRITE);
        texture = gamePlayAtlas.findRegion("grassHalf");

        setToDestroy = false;
        destroy = false;

        defineBlock();
    }

    int random = MathUtils.random(2, 4);

    private void defineBlock() {
        if (creater.getBlocks().size <= 0) {
            random = 6;
        } else {
            random = MathUtils.random(2, 4);
        }
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(bodyDef);


        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(random, 0.25f);

        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = GameConfig.BLOCK_BIT;
        fixtureDef.friction = 0.2f;


        body.createFixture(fixtureDef).setUserData(this);

        shape.dispose();

        creater.createCoin(x, y + 1, random);

        setBounds(x, y, random*2, 1f);
    }

    public void update(float delta) {
        if (setToDestroy && !destroy) {
            world.destroyBody(body);
            //controller.getCreator().removeBlock(this);
            destroy = true;
        } else if (!destroy) {
            setRegion(texture);
            setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 1.5f);
        }

        if (controller.getPlayer().body.getPosition().x - body.getPosition().x >= 16) {
            setToDestroy = true;
        }
    }

    @Override
    public void draw(Batch batch) {
        if(!destroy){
            super.draw(batch);
        }
    }
}
