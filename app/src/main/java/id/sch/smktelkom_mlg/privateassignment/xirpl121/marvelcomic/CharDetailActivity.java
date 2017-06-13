package id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;

import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.model.Fav;
import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.model.Marvel;
import io.realm.Realm;

public class CharDetailActivity extends AppCompatActivity {

    public Realm realm;
    Fav favsave = new Fav();
    Bitmap bitmap = null;
    byte[] gambar = new byte[102400];
    ImageView imageView;
    TextView textView;
    Marvel marvel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();
        marvel = (Marvel) getIntent().getSerializableExtra("detail");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fav fav = realm.where(Fav.class).equalTo("id",marvel.id).findFirst();
                if(fav==null){
                    try{
                        new AsyncTask<Void,Void,Void>(){

                            @Override
                            protected Void doInBackground(Void... voids) {
                                try {
                                    bitmap = Glide
                                            .with(getBaseContext())
                                            .load(marvel.thumbnail.path + "/portrait_xlarge.jpg")
                                            .asBitmap()
                                            .into(200, 270)
                                            .get();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                gambar = getBytes(bitmap);
                                favsave = new Fav(marvel.id,marvel.name,marvel.description,gambar);
                                realm.beginTransaction();
                                realm.insert(favsave);
                                realm.commitTransaction();
                            }
                        }.execute();
                        Toast.makeText(getBaseContext(), marvel.name + " berhasil masuk Favorite", Toast.LENGTH_LONG).show();
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Sudah ada di Favorite", Toast.LENGTH_LONG).show();
                }
            }
        });
        imageView = (ImageView) findViewById(R.id.imageFoto);
        textView = (TextView) findViewById(R.id.detail);

        setTitle(marvel.name);
        Glide.with(this)
                .load(marvel.thumbnail.path + "/portrait_xlarge.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(150, 225)
                .error(R.drawable.no_image)
                .into(imageView);
        textView.setText(marvel.description);
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }
}
