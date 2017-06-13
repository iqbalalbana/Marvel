package id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.model.Fav;
import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.model.Marvel;

/**
 * Created by Iqbal Albana on 13/06/2017.
 */

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    List<Fav> favList = new ArrayList<>();
    Fragment fragment;
    Context context;
    Favlistener favlistener;
    public FavAdapter(List<Fav> favList, Fragment fragment, Context context){
        this.favList = favList;
        this.fragment = fragment;
        this.context = context;
        favlistener = (Favlistener) fragment;
    }

    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.marvelitem, parent, false);
        FavAdapter.ViewHolder vh = new FavAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(FavAdapter.ViewHolder holder, int position) {
        Fav marveling = favList.get(position);
        holder.txtMarvelName.setText(marveling.name);
        holder.txtMarvelDesc.setText(marveling.description);
        Bitmap bitmap = getImage(marveling.picture);
        holder.ivMarvelPict.setImageBitmap(bitmap);
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public int getItemCount() {
        if (favList != null){
            return favList.size();
        }
        return 0;
    }

    public interface Favlistener{
        void detail(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMarvelName;
        TextView txtMarvelDesc;
        ImageView ivMarvelPict;
        public ViewHolder(View itemView) {
            super(itemView);
            txtMarvelName = (TextView) itemView.findViewById(R.id.marvelName);
            txtMarvelDesc = (TextView) itemView.findViewById(R.id.marvelDesc);
            ivMarvelPict = (ImageView) itemView.findViewById(R.id.imageMarvel);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    favlistener.detail(getAdapterPosition());
                }
            });
        }
    }
}
