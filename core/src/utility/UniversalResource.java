package utility;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by hj167 on 14/11/2017.
 */

public class UniversalResource {
    //common objects
    //common objects
    public TweenManager tweenManager;
    //singleton stuff
    private static UniversalResource instance;

    public static UniversalResource getInstance(){
        if(instance == null){
            instance = new UniversalResource();
        }
        return instance;
    }
    private UniversalResource(){configure();}
    //end of singleton stuff

    //register and configure
    public void configure(){
        Tween.setCombinedAttributesLimit(4);
        tweenManager = new TweenManager();
        Tween.registerAccessor(utility.TweenData.class, new utility.TweenDataAccessor());
    }
}
