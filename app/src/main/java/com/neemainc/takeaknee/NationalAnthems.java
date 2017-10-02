package com.neemainc.takeaknee;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Mkwelele on 10/1/2017.
 */

public class NationalAnthems {
    private static final String TAG = "NationalAnthems";

    private static final String NATIONAL_ANTHEMS_FOLDER = "national_anthems";
    private static final int MAX_NATIONAL_ANTHEMS = 5;

    private AssetManager mAssets;
    private List<Anthem> mAnthems = new ArrayList<>();
    private SoundPool mSoundPool;

    public NationalAnthems(Context context) {
        mAssets = context.getAssets();
        // Deprecated constructor only used for compatibility
        mSoundPool = new SoundPool(MAX_NATIONAL_ANTHEMS, AudioManager.STREAM_MUSIC, 0);
        loadAnthems();
    }

    private void loadAnthems() {
        String[] anthemNames = {""};
        try {
            anthemNames = mAssets.list(NATIONAL_ANTHEMS_FOLDER);
            Log.i(TAG, "Found " + anthemNames.length + " national anthems.");
        } catch (IOException ioe) {
            Log.e(TAG, "Could not list national anthems", ioe);
        }

        for (String filename : anthemNames) {
            try {
                String assetPath = NATIONAL_ANTHEMS_FOLDER + "/" + filename;
                Anthem anthem = new Anthem(assetPath);
                load(anthem);
                mAnthems.add(anthem);
            } catch (IOException ioe) {

            }
        }
    }

    private void load(Anthem anthem) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(anthem.getAssetPath());
        int anthemId = mSoundPool.load(afd, 1);
        anthem.setAnthemId(anthemId);
    }

    public void play(Anthem anthem) {
        Integer anthemId = anthem.getAnthemId();
        if (anthemId == null) {
            return;
        }
        mSoundPool.play(anthemId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void release() {
        mSoundPool.release();
    }

    public List<Anthem> getAnthems() {
        return mAnthems;
    }
}
