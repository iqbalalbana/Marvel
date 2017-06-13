package id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.model.Fav;
import io.realm.Realm;
import io.realm.RealmResults;

public class FavActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    Fav fav;
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();
        fav =  realm.where(Fav.class).equalTo("id",getIntent().getIntExtra("id",0)).findFirst();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<Fav> result = realm.where(Fav.class).equalTo("id", fav.id).findAll();
                        result.deleteAllFromRealm();
                    }
                });
            }
        });

        setTitle(fav.name);
        textView = (TextView) findViewById(R.id.detFav);
        imageView = (ImageView) findViewById(R.id.imageFotoFav);
        textView.setText(fav.description);
        Bitmap bitmap = getImage(fav.picture);
        imageView.setImageBitmap(bitmap);
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
