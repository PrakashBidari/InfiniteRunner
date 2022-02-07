package com.infiniterunner.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.infiniterunner.assets.AssetDescriptors;
import com.infiniterunner.config.GameConfig;
import com.infiniterunner.screen.game.GameController;

public class Player extends Sprite {
    public Body body;
    private World world;
    private float x, y;

    private TextureRegion playerStand;
    private TextureRegion playerJump;
    private Animation playerRun;

    public Player(GameController controller, World world, float x, float y) {
        this.world = world;
        this.x = x;
        this.y = y;

        definePlayer();

        TextureAtlas gamePlayAtlas = controller.getScreen().getGame().getAssetManager().get(AssetDescriptors.SPRITE);
        Array<TextureRegion> frames = new Array<>();

        playerStand = gamePlayAtlas.findRegion("Idle");

        for (int i = 1; i < 9; i++) {
            frames.add(gamePlayAtlas.findRegion("Run" + i));
        }
        playerRun = new Animation(0.06f, frames);
        frames.clear();

        playerJump = gamePlayAtlas.findRegion("Jump");

        setBounds(x,y,2.5f,1.9f);
    }

    private void definePlayer() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);


        FixtureDef fixtureDef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 0.85f);

        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = GameConfig.PLAYER_BIT;
        fixtureDef.friction = 0f;


        body.createFixture(fixtureDef).setUserData(this);

        shape.dispose();

    }

    public void update(float dt) {
        setRegion(getFrame(dt));
        setPosition(body.getPosition().x - getWidth() / 2+0.1f, body.getPosition().y - getHeight() / 2 + 0.06f);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
    }

        float stateTimer;
    private TextureRegion getFrame(float dt) {
        stateTimer += dt;

        TextureRegion region;
        if(body.getLinearVelocity().x>0 && body.getLinearVelocity().y==0){
            region = (TextureRegion) playerRun.getKeyFrame(stateTimer, true);
        }else if(body.getLinearVelocity().y>0 ||body.getLinearVelocity().y<0){
            region = playerJump;
        }
        else{
            region = playerStand;
        }

        return region;
    }
}
