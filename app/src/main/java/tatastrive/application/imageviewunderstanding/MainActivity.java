package tatastrive.application.imageviewunderstanding;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import tatastrive.application.imageviewunderstanding.R.drawable;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;
    ImageButton imageButton;
    Intent intent;
    final static int clickcode = 100;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        button=findViewById( R.id.button );
        imageView = findViewById( R.id.imageView );
        InputStream inputStream=getResources().openRawResource( R.drawable.test );
        bitmap= BitmapFactory.decodeStream( inputStream );
        imageButton = findViewById( R.id.imageButton );
        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( MainActivity.this, "Camara Button Click", Toast.LENGTH_SHORT ).show();
                intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
                startActivityForResult( intent, clickcode );

            }
        } );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getApplicationContext().setWallpaper( bitmap );
                } catch (IOException e){
                    e.printStackTrace();
                }
                Toast.makeText( MainActivity.this, "Wallpaper Set", Toast.LENGTH_SHORT ).show();
            }
        } );
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (resultCode == RESULT_OK)
        {
            Bundle bundle_sush=data.getExtras();
            bitmap = (Bitmap) bundle_sush.get("data");
            imageView.setImageBitmap( bitmap);
        }
    }
}