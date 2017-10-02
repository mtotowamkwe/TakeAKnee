package com.neemainc.takeaknee;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

/**
 * Created by Michael Mkwelele on 10/1/2017.
 */

public class NationalAnthemsFragment extends Fragment {

    private NationalAnthems mNationalAnthems;

    public static NationalAnthemsFragment newInstance() {
        return new NationalAnthemsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mNationalAnthems = new NationalAnthems(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_national_anthems, container, false);

        RecyclerView recyclerView = (RecyclerView) view
                .findViewById(R.id.fragment_national_anthems_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(new AnthemAdapter(mNationalAnthems.getAnthems()));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNationalAnthems.release();
    }

    private class AnthemHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private Button mButton;
        private Anthem mAnthem;

        public AnthemHolder(LayoutInflater inflater, ViewGroup container) {
            super(inflater.inflate(R.layout.list_item_national_anthems, container, false));

            mButton = (Button) itemView.findViewById(R.id.list_item_national_anthem_button);
            mButton.setOnClickListener(this);
        }

        public void bindAnthem(Anthem anthem) {
            mAnthem = anthem;
            mButton.setText(mAnthem.getName());
        }

        @Override
        public void onClick(View v) {
            mNationalAnthems.play(mAnthem);
        }
    }

    private class AnthemAdapter extends RecyclerView.Adapter<AnthemHolder> {
        private List<Anthem> mAnthems;

        public AnthemAdapter(List<Anthem> anthems) {
            mAnthems = anthems;
        }

        @Override
        public AnthemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new AnthemHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(AnthemHolder anthemHolder, int position) {
            Anthem anthem = mAnthems.get(position);
            anthemHolder.bindAnthem(anthem);
        }

        @Override
        public int getItemCount() {
            return mAnthems.size();
        }
    }

}
