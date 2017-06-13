package id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.model;


import java.io.Serializable;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.model.Price;

/**
 * Created by Iqbal Albana on 14/05/2017.
 */

public class Comic implements Serializable{
    public int id;
    public String title;
    public String description;
    public Thumbnail thumbnail;
    public List<Price> prices;
    public List<Url> urls;
}
