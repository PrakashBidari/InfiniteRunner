package com.infiniterunner.assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetDescriptors {

    //TextureAtlas
    public static final AssetDescriptor<TextureAtlas> SPRITE = new AssetDescriptor<TextureAtlas>(AssetsPaths.SPRITE, TextureAtlas.class);






    //Sound
    public static final AssetDescriptor<Sound> COIN_SOUND = new AssetDescriptor<>(AssetsPaths.COIN_SOUND, Sound.class);




    private AssetDescriptors() {

    }
}
