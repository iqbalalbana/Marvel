package id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic.model;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by Iqbal Albana on 16/05/2017.
 */

public class Fav extends RealmObject {
    public int id;
    public String name;
    public String description;
    public byte[] picture = new byte[102400];

    public Fav() {

    }

    public Fav(int id, String name, String description, byte[] picture) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
    }
}
