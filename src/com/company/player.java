package com.company;

import java.util.HashMap;

/**
 * Created by thayer on 7/15/15.
 */
public class player {
    HashMap<Integer, Boolean> map = new HashMap<Integer,Boolean>();
    pieces pcs = new pieces();
    public player(){

    }
    public void addav(Integer space, Integer x, Integer y){

        pcs.av.setlocation(space, x, y);





    }
    public void addpr(Integer space, Integer x, Integer y){
        pcs.pr.setlocation(space,x ,y);
    }
    public void addor(Integer space, Integer x, Integer y){
        pcs.or.setlocation(space, x, y);

    }

    public void addch(Integer space, Integer x, Integer y){
        pcs.ch.setlocation(space,x ,y);

    }

}
