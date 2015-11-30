package mluds.lifttracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ViewHolder> {
    private String[] mData;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.contact_name);
        }
    }

    public ExercisesAdapter(String[] data) {
        mData = data;
        System.out.println(data[0]);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mData[position]);
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }
}
