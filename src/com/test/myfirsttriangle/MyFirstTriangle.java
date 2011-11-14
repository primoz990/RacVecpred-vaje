package com.test.myfirsttriangle;

import java.io.File;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;



//POMOČ: http://dpk.net/2011/03/07/libgdx-cubes-handling-inputs-in-applicationlistener-render/

public class MyFirstTriangle implements ApplicationListener {

	    public Music muzika;
        private PerspectiveCamera camera;
        private Mesh[] faces;
        public int stevec=0;
        
        public void create() {
        	//trikotnik
                /*if (mesh == null) {
                        mesh = new Mesh(true, 3, 3, 
                                new VertexAttribute(Usage.Position, 3, "a_position"));          

                        mesh.setVertices(new float[] { -0.5f, -0.5f, 0,
                                                       0.7f, -0.5f, 0,
                                                       0, 0.5f, 0 });   
                        mesh.setIndices(new short[] { 0, 1, 3});                       
                }*/
        	
        	
        	
        	//http://www.opuzz.com/displayproducts-classical.asp?id=88&cat=Classical&st=styles&a=1
        	//http://www.youtube.com/watch?v=ra3H5UHLRGU
        	
            muzika = Gdx.audio.newMusic(Gdx.files.internal("sss.mp3"));
        	muzika.setLooping(true);
        	muzika.setVolume(0.02f);
        	muzika.play();
        	
        	
        	
        	if (faces == null) {
        	      faces = new Mesh[6];
        	 
        	      for (int i = 0; i < 6; i++) {
        	        faces[i] = new Mesh(true, 4, 4,
        	            new VertexAttribute(Usage.Position, 3, "a_position"),
        	            new VertexAttribute(Usage.ColorPacked, 4, "a_color"));
        	 
        	        faces[i].setIndices(new short[] { 0, 1, 2, 3 });
        	      }
        	 
        	      faces[0].setVertices(new float[] {
        	          0.5f, 0.5f, 0.5f, Color.toFloatBits(96, 0, 0, 255),
        	          -0.5f, 0.5f, 0.5f, Color.toFloatBits(96, 0, 0, 255),
        	          0.5f, -0.5f, 0.5f, Color.toFloatBits(96, 0, 0, 255),
        	          -0.5f, -0.5f, 0.5f, Color.toFloatBits(96, 0, 0, 255) });
        	 
        	      faces[1].setVertices(new float[] {
        	          0.5f, 0.5f, -0.5f, Color.toFloatBits(255, 0, 0, 255),
        	          -0.5f, 0.5f, -0.5f, Color.toFloatBits(255, 0, 0, 255),
        	          0.5f, -0.5f, -0.5f,  Color.toFloatBits(255, 0, 0, 255),
        	          -0.5f, -0.5f, -0.5f, Color.toFloatBits(255, 0, 0, 255) });
        	 
        	      faces[2].setVertices(new float[] {
        	          0.5f, 0.5f, -0.5f, Color.toFloatBits(0, 255, 0, 255),
        	          -0.5f, 0.5f, -0.5f, Color.toFloatBits(0, 255, 0, 255),
        	          0.5f, 0.5f, 0.5f, Color.toFloatBits(0, 255, 0, 255),
        	          -0.5f, 0.5f, 0.5f, Color.toFloatBits(0, 255, 0, 255) });
        	 
        	      faces[3].setVertices(new float[] {
        	          0.5f, -0.5f, -0.5f, Color.toFloatBits(0, 96, 0, 255),
        	          -0.5f, -0.5f, -0.5f, Color.toFloatBits(0, 96, 0, 255),
        	          0.5f, -0.5f, 0.5f, Color.toFloatBits(0, 96, 0, 255),
        	          -0.5f, -0.5f, 0.5f,  Color.toFloatBits(0, 96, 0, 255) });
        	 
        	      faces[4].setVertices(new float[] {
        	          0.5f, 0.5f, 0.5f, Color.toFloatBits(0, 0, 255, 255),
        	          0.5f, -0.5f, 0.5f, Color.toFloatBits(0, 0, 255, 255),
        	          0.5f, 0.5f, -0.5f, Color.toFloatBits(0, 0, 255, 255),
        	          0.5f, -0.5f, -0.5f, Color.toFloatBits(0, 0, 255, 255) });
        	 
        	      faces[5].setVertices(new float[] {
        	          -0.5f, 0.5f, 0.5f, Color.toFloatBits(0, 0, 96, 255),
        	          -0.5f, -0.5f, 0.5f, Color.toFloatBits(0, 0, 96, 255),
        	          -0.5f, 0.5f, -0.5f, Color.toFloatBits(0, 0, 96, 255),
        	          -0.5f, -0.5f, -0.5f, Color.toFloatBits(0, 0, 96, 255) });
        	    }
        	 
        	    Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
	    
        }

       // @Override
        public void dispose() 
        { 
        	muzika.dispose();
        }

        //@Override
        public void pause() { }

        
        protected int lastTouchX;
        protected int lastTouchY;
        int deltaX;
        int deltaY;
        
        //@Override
        public void render() 
        {
        
        	if(stevec==0)
        	{
        		camera.position.x=0;
        		camera.position.y=0;
        		camera.position.z=10;
        		
        	}
        	
        	if (Gdx.input.justTouched()) 
        	{
        		  muzika.stop();
        	      lastTouchX = Gdx.input.getX();
        	      lastTouchY = Gdx.input.getY();
        	      
        	} else if (Gdx.input.isTouched()) 
        	{
        		deltaX=lastTouchX - Gdx.input.getX();
        		deltaY=lastTouchY - Gdx.input.getY();

        		//if(camera.position.x==10 || camera.position.x==-10) camera.position.x*=-1;
        		//if(camera.position.y==10 || camera.position.y==-10) camera.position.y*=-1;
        		

        	      if(deltaX>0)
        	      {
        	    	  camera.rotate(2, 0, 1, 0);
        	    	  camera.position.x+= 0.5f;
        	      }
        	      else if(deltaX<0)
        	      {
        	    	  camera.rotate(-2, 0, 1, 0);
        	    	  camera.position.x-= 0.5f;
        	      }
        	      
        	      
        	      
        	      if(deltaY>0)
        	      {
        	    	  camera.rotate(1, 1, 0, 0);
        	    	  camera.position.y+= 0.2f;
        	      }
        	      else if(deltaY<0)
        	      {
        	    	  camera.rotate(-1, 1, 0, 0);
        	    	  camera.position.y-= 0.2f;   	    	
        	      }


        	      lastTouchX = Gdx.input.getX();
        	      lastTouchY = Gdx.input.getY();
        	}
        	
        	//Log.i("tag", camera.position.x+"\n");

        	//ZOOMIRANJE
        	if(Gdx.input.isKeyPressed(Input.Keys.KEYCODE_SEARCH))camera.translate(0, 0, 1); //camera.position.z+=0.5f;
        	if(Gdx.input.isKeyPressed(Input.Keys.KEYCODE_MENU))camera.translate(0, 0, -1); //camera.position.z-=0.5f;
        	
        	camera.lookAt(0, 0, 0);
        	 
        	camera.update();
        	camera.apply(Gdx.gl10);
        	 
        	Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        	 
        	for (Mesh face : faces) 
        	{
        	    face.render(GL10.GL_TRIANGLE_STRIP, 0, 4);
        	}
        	 
        	try 
        	{
        	      Thread.sleep(16);
        	} catch (InterruptedException e) 
        	{ }
        	
        	stevec++;

        }

        //@Override
        public void resize(int width, int height) 
        {
        	 float aspectRatio = (float) width / (float) height;
        	    camera = new PerspectiveCamera(67, 2f * aspectRatio, 2f);
        	    camera.near = 0.1f;
        	    camera.translate(0, 0, 0);
        	
        }

        //@Override
        public void resume() { }
}