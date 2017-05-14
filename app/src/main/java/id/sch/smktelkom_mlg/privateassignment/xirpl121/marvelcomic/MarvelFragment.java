package id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.adapter.MarvelAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.model.Marvel;
import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.model.Response;
import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.service.GsonGetRequest;
import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.service.VolleySingleton;


/**
 * A simple {@link Fragment} subclass.
 */
public class MarvelFragment extends Fragment implements MarvelAdapter.marvelListener{

    public MarvelAdapter marvelAdapter;
    public List<Marvel> marvelList = new ArrayList<>();
    RecyclerView recyclerView;
    GridLayoutManager layoutManager;

    public MarvelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        marvelAdapter = new MarvelAdapter(marvelList, getContext(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_marvel, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.comicView);
        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(marvelAdapter);
        fillData();

        return view;
    }

    private void fillData() {
        DialogFragment newProgressFragment = ProgressDialogFragment.newInstance("Loading");
        newProgressFragment.show(getActivity().getFragmentManager(), "progress");
        String url = "https://gateway.marvel.com/v1/public/characters?ts=1&apikey=e4ad44aab5d8f2c56faa40577b8d82c6&hash=33a5ddeacd8cd4cd0ba64ace02a1de5d";
        GsonGetRequest<Response> req = new GsonGetRequest<Response>(url, Response.class, null, new com.android.volley.Response.Listener<Response>() {
            @Override
            public void onResponse(Response response) {
                if (response.status.equals("Ok")) {
                    marvelList.addAll(response.data.results);
                }
                marvelAdapter.notifyDataSetChanged();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("CHAR FRAGMENT", "Error : ", error);
            }
        });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(req);
        newProgressFragment.dismiss();
    }

    @Override
    public void detail(int pos) {
        Marvel Marveli = marvelList.get(pos);
        Intent intent = new Intent(getActivity(), MarvelDetailActivity.class);
        intent.putExtra("detail", Marveli);
        startActivity(intent);
    }
}
