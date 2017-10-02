package com.neemainc.takeaknee;

import android.support.v4.app.Fragment;

public class NationalAnthemsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return NationalAnthemsFragment.newInstance();
    }
}
