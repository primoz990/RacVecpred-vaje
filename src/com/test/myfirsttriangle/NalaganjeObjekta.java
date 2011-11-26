package com.test.myfirsttriangle;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore.LoadStoreParameter;
import java.util.Random;

import android.util.Log;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import  com.badlogic.gdx.graphics.g3d.loaders.obj.ObjLoader;
import com.badlogic.gdx.math.Vector3;



//POMOČ: http://dpk.net/2011/03/07/libgdx-cubes-handling-inputs-in-applicationlistener-render/
public class NalaganjeObjekta implements ApplicationListener {

	public static  int IzbranaMuzika=0;
	public static  int IzbranObjekt=0;
	public static  int IzbranaTextura=0;

	
	public NalaganjeObjekta(int muzikaID, int objektID, int texturaID) 
	{
			IzbranaMuzika = muzikaID;
			IzbranObjekt = objektID;
			IzbranaTextura = texturaID;
	}
	
	    public Music muzika;
        private PerspectiveCamera camera;
        public int stevec=0;
        Mesh sphere;
        SpriteBatch batch;
        BitmapFont font;
        Vector3[] positions = new Vector3[100];
        private Texture texture;
        
        
        
        public void create() {

        	
        	//izbira muzike
        	if(IzbranaMuzika==2) muzika = Gdx.audio.newMusic(Gdx.files.internal("PianoSonata.mp3"));
        	else if(IzbranaMuzika==0) muzika = Gdx.audio.newMusic(Gdx.files.internal("Nocturne.mp3"));
        	else if(IzbranaMuzika==1) muzika = Gdx.audio.newMusic(Gdx.files.internal("Paganini.mp3"));
        	else if(IzbranaMuzika==3) muzika = Gdx.audio.newMusic(Gdx.files.internal("PreludeBWV.mp3"));
        	else if(IzbranaMuzika==4) muzika = Gdx.audio.newMusic(Gdx.files.internal("Waltz.mp3"));
            
        	
        	muzika.setLooping(true);
        	muzika.setVolume(0.07f);
        	//muzika.play();
        	
        	//izbira Objekta
        	InputStream vhod = null;
        	try {
        		if(IzbranObjekt==0) vhod = new FileInputStream("/mnt/sdcard/diamond.obj"); 
        		else if(IzbranObjekt==1) vhod = new FileInputStream("/mnt/sdcard/vase.obj"); 
        		else if(IzbranObjekt==2) vhod = new FileInputStream("/mnt/sdcard/table.obj"); 
        		else if(IzbranObjekt==3) vhod = new FileInputStream("/mnt/sdcard/candle.obj"); 
        		
        		Log.d("OK","Datoteka nalozena");
			} catch (Exception e) {
				Log.d("NAPAKA"," Pri nalaganju datoteke: \n"+e.toString());
			}
        	
			
			// TExturre  /////////// http://code.google.com/p/libgdx/wiki/MeshColorTexture
			FileHandle imageFileHandle = null;
			if(IzbranaTextura==0) imageFileHandle = Gdx.files.external("/badlogic.jpg");
			else if(IzbranaTextura==1) imageFileHandle = Gdx.files.external("/textura2.jpg");
			else if(IzbranaTextura==2) imageFileHandle = Gdx.files.external("/textura3.jpg");
			
	        texture = new Texture(imageFileHandle);
        	
	      //nalaganje objekta .obj
        	sphere = ObjLoader.loadObj(vhod);
            camera = new PerspectiveCamera(150, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            camera.far = 200;  
        	    
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
        float svetloba = 0.8f;
        
        //@Override
        public void render() 
        {
        	
        	if(Gdx.input.isKeyPressed(Input.Keys.KEYCODE_VOLUME_DOWN)) muzika.pause(); //PAUZA
        	else if(Gdx.input.isKeyPressed(Input.Keys.KEYCODE_VOLUME_UP)) muzika.play(); //PLAY
        	
        	if(stevec==0)
        	{
        		camera.position.x=0;
        		camera.position.y=0;
        		camera.position.z=10;
        	}
        	
        	
        	if (Gdx.input.justTouched()) 
        	{
        	      
        	      
        	      int x = Gdx.input.getX();
             	 int y = Gdx.input.getY();
             	 
             	lastTouchX = x;
      	        lastTouchY = y;

      	        //Spreminjanje svetlobe
             	 if(x<50 && y<50) if(svetloba<1) svetloba+=0.1f;
             	 if(x<50 && y>480) if(svetloba>0) svetloba-=0.1f;

        	} else if (Gdx.input.isTouched()) 
        	{
        		deltaX=lastTouchX - Gdx.input.getX();
        		deltaY=lastTouchY - Gdx.input.getY();

        		
        	      if(deltaX>0) camera.position.x+= 0.5f;
        	      else if(deltaX<0) camera.position.x-= 0.5f;
        	      
        	      
        	      
        	      if(deltaY>0) camera.position.y+= 0.2f;
        	      else if(deltaY<0) camera.position.y-= 0.2f;   	    	

        	      lastTouchX = Gdx.input.getX();
        	      lastTouchY = Gdx.input.getY();
        	}

        	//ZOOMIRANJE
        	if(Gdx.input.isKeyPressed(Input.Keys.KEYCODE_SEARCH))camera.translate(0, 0, 1); //camera.position.z+=0.5f;
        	if(Gdx.input.isKeyPressed(Input.Keys.KEYCODE_MENU))camera.translate(0, 0, -1); //camera.position.z-=0.5f;
        	
        	camera.lookAt(0, 0, 0);
        	 
        	
        	//////////////TEXTURE//////////////////// http://www.java2s.com/Open-Source/Android/Game/libgdx/com/badlogic/gdx/tests/CullTest.java.htm
        	 GL10 gl = Gdx.gl10;

        	 gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
             gl.glEnable(GL10.GL_TEXTURE_2D);
             gl.glEnable(GL10.GL_DEPTH_TEST);
             //nastavljanje svetlosti
             gl.glColor4f(svetloba, svetloba, svetloba, svetloba); //svetlost
             
             camera.update();
             camera.apply(gl);

            
             texture.bind();
             sphere.render(GL10.GL_TRIANGLES);
                

        	stevec++;

        }

        //@Override
        public void resize(int width, int height) 
        {
        	 float aspectRatio = (float) width / (float) height;      	    	
        }

        //@Override
        public void resume() { }
        
        
        
        
        
        
}