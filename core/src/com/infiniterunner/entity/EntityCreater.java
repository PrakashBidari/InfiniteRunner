package com.infiniterunner.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.infiniterunner.screen.game.GameScreen;

public class EntityCreater {
    public Array<Coin> coins;
    private Array<Blocks> blocks;
    GameScreen screen;
    World world;

    public EntityCreater(World world, GameScreen screen) {
        this.world = world;
        this.screen = screen;

        coins = new Array<>();
        blocks = new Array<>();

    }

    public void createCoin(float x, float y, int bWidth) {
        int random = MathUtils.random(1, 5);
        if (random == 1) {
            if (bWidth == 2) {
                for (int i = 1; i <= 4; i++) {
                    coins.add(new Coin(world, screen, (x - 2.5f) + i, y));
                }
            }
            if (bWidth == 3) {
                for (int i = 1; i <= 6; i++) {
                    coins.add(new Coin(world, screen, (x - 3.5f) + i, y));
                }
            }
            if (bWidth == 4) {
                for (int i = 1; i <= 8; i++) {
                    coins.add(new Coin(world, screen, (x - 4.5f) + i, y));
                }
            }
        }
    }

    public void createBlocks() {

        if (blocks.size <= 0) {
            blocks.add(new Blocks(world, screen.getController(), this, 4, 4));
        }
        if ((blocks.get(blocks.size - 1).body.getPosition().x) < screen.getController().getPlayer().body.getPosition().x + 10) {
            int random = MathUtils.random(1, 8);
            if (random == 1) {
                float valueOfX = blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10);
                blocks.add(new Blocks(world, screen.getController(), this, valueOfX, 4));
            } else if (random == 6) {
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 6));
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 8));
            } else if (random == 3) {
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 6));
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 9));
            } else if (random == 4) {
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 9));
            } else if (random == 5) {
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 5));
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 9));
            } else if (random == 2) {
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 3));
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 7));
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 12));

            } else if (random == 7) {
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 6));
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 9));

            } else {
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 4));
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 9));
                blocks.add(new Blocks(world, screen.getController(), this, blocks.get(blocks.size - 1).body.getPosition().x + MathUtils.random(8, 10), 14));

            }
        }
    }


    public Array<Coin> getCoins() {
        return coins;
    }

    public Array<Blocks> getBlocks() {
        return blocks;
    }

    public void removeCoin(Coin coin) {
        coins.removeValue(coin, true);
    }

    public void removeBlock(Blocks block) {
        blocks.removeValue(block, true);
    }
}
