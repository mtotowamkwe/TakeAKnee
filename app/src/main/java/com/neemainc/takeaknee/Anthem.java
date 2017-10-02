package com.neemainc.takeaknee;

/**
 * Created by Michael Mkwelele on 10/1/2017.
 */

public class Anthem {
    private String mAssetPath;
    private String mName;
    private Integer mAnthemId;

    public Anthem(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".mid", "");
    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getAnthemId() {
        return mAnthemId;
    }

    public void setAnthemId(Integer anthemId) {
        mAnthemId = anthemId;
    }
}
