package org.algo.davidyu.getInstance;

public class Instance {
    private static Instance instance;
    public final static Instance getInstance() {
        if (instance == null) {
            instance = new Instance();
        }
        return instance;
    }
    public Instance(){

    }
}
