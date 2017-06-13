package id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.adapter.FavAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.model.Fav;
import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.model.Marvel;
import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavAdapter.Favlistener {

    Realm realm;
    FavAdapter favAdapter;
    public List<Fav> favs = new ArrayList<>();
    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
        favAdapter = new FavAdapter(favs,this,getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.favView);
        layoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(favAdapter);
        fillData();
        return view;
    }

    private void fillData() {
        favs.clear();
        favs.addAll(realm.where(Fav.class).findAll());
        favAdapter.notifyDataSetChanged();
    }

    @Override
    public void detail(int pos) {
        Fav fav = favs.get(pos);
        Intent intent =  new Intent(getActivity(),FavActivity.class);
        intent.putExtra("id",fav.id);
        startActivity(intent);
    }
}
