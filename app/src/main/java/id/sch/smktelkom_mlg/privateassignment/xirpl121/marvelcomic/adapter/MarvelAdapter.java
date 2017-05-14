package id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.model.Marvel;

/**
 * Created by Iqbal Albana on 14/05/2017.
 */

public class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.ViewHolder> {

    Context context;
    List<Marvel> marvels = new ArrayList<>();
    marvelListener marvelListener;

    public MarvelAdapter(List<Marvel> marvels, Context context, Fragment fragment){
        this.marvels = marvels;
        this.context = context;
        marvelListener = (marvelListener) fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.marvelitem, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Marvel marveling = marvels.get(position);
        holder.txtMarvelName.setText(marveling.name);
        holder.txtMarvelDesc.setText(marveling.description);
        try {
            Glide.with(context)
                    .load(marveling.thumbnail.path + "/portrait_xlarge.jpg")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(150, 225)
                    .error(R.drawable.no_image)
                    .into(holder.ivMarvelPict);
        } catch (Exception ex){
            Log.e("MARVEL ADAPTER", ex.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        if (marvels != null){
            return marvels.size();
        }
        return 0;
    }

    public interface marvelListener{
        void detail(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtMarvelName;
        TextView txtMarvelDesc;
        ImageView ivMarvelPict;

        public ViewHolder(View itemView){
            super(itemView);
            txtMarvelName = (TextView) itemView.findViewById(R.id.marvelName);
            txtMarvelDesc = (TextView) itemView.findViewById(R.id.marvelDesc);
            ivMarvelPict = (ImageView) itemView.findViewById(R.id.imageMarvel);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    marvelListener.detail(getAdapterPosition());
                }
            });
        }
    }
}
