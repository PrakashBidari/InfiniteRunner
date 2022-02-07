package com.infiniterunner.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.infiniterunner.config.GameConfig;
import com.infiniterunner.entity.Coin;

public class WorldContactListener implements ContactListener {

    public WorldContactListener() {
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (cDef) {
            case GameConfig.PLAYER_BIT | GameConfig.COIN_BIT:
                if (fixA.getFilterData().categoryBits == GameConfig.COIN_BIT) {
                    ((Coin) fixA.getUserData()).onHit();
                } else {
                    ((Coin) fixB.getUserData()).onHit();
                }
                break;

            case GameConfig.HUD_COIN_BIT | GameConfig.COIN_BIT:
                if (fixA.getFilterData().categoryBits == GameConfig.COIN_BIT) {
                    ((Coin) fixA.getUserData()).onHudCoinHit();
                } else {
                    ((Coin) fixB.getUserData()).onHudCoinHit();
                }
                break;
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
