package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import actors.AnimatedSprite;
import actors.BonusSprite;
import utility.Constants;
import utility.UniversalResource;

public class MyGdxGame extends ApplicationAdapter {
	static final Color BACKGROUND_COLOUR = new Color(0F, 0F, 0F, 1F);
	static final float WORLD_TO_SCREEN = 1.0F / 100.0F;
	static final float SCENE_WIDTH = 1280F;
	static final float SCENE_HEIGHT = 720F;
	AnimatedSprite blades_1, blades_2;
	BonusSprite bp;

	OrthographicCamera camera;
	Viewport view;
	SpriteBatch batch;
	float animationTime;

	public void create() {
		camera = new OrthographicCamera();
		view = new ExtendViewport(800, 600, camera);
		batch = new SpriteBatch();
		//Texture used to size AnimatedSprite through call to super(size);
		Texture small = new Texture(Gdx.files.internal("smallSize.png"));
		Texture medium = new Texture(Gdx.files.internal("mediumSize.png"));
		//create 2 new AnimatedSprites
		blades_1 = new AnimatedSprite("gfx/blade_assets.atlas", small, Animation.PlayMode.LOOP);
		blades_2 = new AnimatedSprite("gfx/blade_assets.atlas", medium, Animation.PlayMode.LOOP);
		//position sprites
		blades_1.setPosition((SCENE_WIDTH / 2) - small.getWidth(), (SCENE_HEIGHT / 2) - small.getHeight());
		blades_2.setPosition((SCENE_WIDTH / 2) - small.getWidth(), (SCENE_HEIGHT / 2) - small.getHeight());
		//set XY position
		bp = new BonusSprite("gfx/blade_assets.atlas", medium, new Vector2(Constants.SCENE_WIDTH/2, Constants.SCENE_HEIGHT/2), Animation.PlayMode.LOOP);
		//call destroy routine
		bp.destroyRoutine();

	}

	public void render()
	{
		Gdx.gl.glClearColor(BACKGROUND_COLOUR.r, BACKGROUND_COLOUR.g, BACKGROUND_COLOUR.b, BACKGROUND_COLOUR.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//Update animationTime
		animationTime += Gdx.graphics.getDeltaTime(); // Animation time each frame
		UniversalResource.getInstance().tweenManager.update(animationTime);
		batch.begin();
		//update
		blades_1.update(animationTime);
		blades_2.update(animationTime);
		bp.update(animationTime);
		//draw
		blades_1.draw(batch);
		blades_2.draw(batch);
		bp.draw(batch);
		batch.end();
	}

	public void resize(int width, int height)
	{
		view.update(width, height, true);
		batch.setProjectionMatrix(camera.combined);
	}

	public void dispose()
	{
		batch.dispose();
	}
}

