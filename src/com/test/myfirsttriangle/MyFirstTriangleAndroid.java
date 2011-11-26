package com.test.myfirsttriangle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import com.badlogic.gdx.backends.android.AndroidApplication;


public class MyFirstTriangleAndroid extends AndroidApplication
{
	Button potrdi;
	Spinner spin;
	
    @Override
        public void onCreate (Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
               
                izbira A = new izbira(); //hranjenje muzike
//               initialize(new MyFirstTriangle(A.stevilkaMuzike), false);  //prejšnja vaja
                initialize(new NalaganjeObjekta(A.stevilkaMuzike, A.stevilkaObjekta, A.stevilkaTexture), false);  //nova vaja
	
        }
    
  
    
    
    
    
}