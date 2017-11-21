package actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import utility.TweenData;
import utility.TweenDataAccessor;
import utility.UniversalResource;

/**
 * Created by hj167 on 21/11/2017.
 */

public class BonusSprite extends AnimatedSprite {
    private TweenData tweenData;
    private TweenManager tweenManager;

    public BonusSprite(String atlasString, Texture t, Vector2 pos, Animation.PlayMode loopType)
    {
        super(atlasString, t, loopType); // Calls the constructor on AnimatedSprite
        this.setPosition(pos.x, pos.y); // Set position of the object
        initTweenData(); // Initialise tween data
    }

    private void initTweenData()
    {
        tweenData = new TweenData(); // New instance of TweenData
        tweenData.setXY(this.getX(), this.getY()); // Access the position
        tweenData.setColour(this.getColor()); // Access the colour
        tweenData.setScale(this.getScaleX()); // Access the scale
        tweenManager = UniversalResource.getInstance().tweenManager; // Get reference to the tween manager
    }

    private TweenData getTweenData()
    {
        return tweenData; // Used to update the data
    }

    @Override
    public void update(float stateTime)
    {
       super.update(stateTime); // Call the parent update to update the frame
        this.setX(tweenData.getXY().x); // Update X
        this.setY(tweenData.getXY().y); // Update Y
        this.setColor(tweenData.getColour()); // Update colour
        this.setScale(tweenData.getScale()); // Update scale
        this.setRotation(tweenData.getRotation()); // Update rotation
    }

    public void destroyRoutine()
    {
        Tween.to(tweenData, TweenDataAccessor.TYPE_POS, 250F) // Static call to tween. Access the current position
                .target(200, 100).start(tweenManager).to(tweenData, TweenDataAccessor.TYPE_ROTATION, 250F) // Change position. Call tween manager to take care of this. Access the current rotation
                .target(180).start(tweenManager).to(tweenData, TweenDataAccessor.TYPE_SCALE, 250F) // Change rotation. Call tween manager to take care of this. Access the current scale
                .target(.15F).start(tweenManager).to(tweenData, TweenDataAccessor.TYPE_COLOUR, 500F) // Change scale. Call tween manager to take care of this. Access the current colour
                .target(.15F, .15F, .15F, .0F).start(tweenManager); // Change colour. Call tween manager to take care of this
    }
}
