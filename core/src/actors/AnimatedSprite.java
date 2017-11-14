package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Comparator;

/**
 * Created by hj167 on 07/11/2017.
 */

public class AnimatedSprite extends Sprite {
    static final float FRAME_DURATION = 1.0F / 30.0F;

    private Animation animation;
    private TextureAtlas atlas;

    public AnimatedSprite(String atlasString, Texture size, Animation.PlayMode loopType)
    {
        super(size);
        atlas = new TextureAtlas(Gdx.files.internal(atlasString));
        // Load animations
        Array<TextureAtlas.AtlasRegion> regions = new Array<TextureAtlas.AtlasRegion>(atlas.getRegions()); // Creates the array and returns all regions
        regions.sort(new RegionComparator()); // Sorts the integers in order
        animation = new Animation(FRAME_DURATION, regions, loopType); // Creates an animation object targeting the array and loops the animation
    }

    public void update(float deltaTime)
    {
        //update animation
        this.setRegion((TextureRegion)animation.getKeyFrame(deltaTime));
        //change position
        //...?
    }

    private static class RegionComparator implements Comparator<TextureAtlas.AtlasRegion>
    {
        @Override
        public int compare(TextureAtlas.AtlasRegion region1, TextureAtlas.AtlasRegion region2)
        {
            return region1.name.compareTo(region2.name);
        }
    }

}

