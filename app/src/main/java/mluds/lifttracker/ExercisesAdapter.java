package mluds.lifttracker;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ExercisesAdapter extends CursorRecyclerAdapter<ExercisesAdapter.ViewHolder> {

    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mItem;
        public TextView name;
        public ViewHolder(View item) {
            super(item);
            mItem = item;
            name = (TextView) item.findViewById(R.id.exercise_name);
        }
    }

    public ExercisesAdapter(Context context, Cursor cursor) {
        super(cursor);
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolderCursor(ViewHolder holder, final Cursor cursor) {
        final String name = cursor.getString(
                cursor.getColumnIndexOrThrow(DatabaseContract.Exercises.COLUMN_NAME_NAME)
        );
        holder.name.setText(name);
        holder.mItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SetsActivity.class);
                intent.putExtra("ID", cursor.getColumnIndexOrThrow(DatabaseContract.Exercises._ID));
                intent.putExtra("NAME", name);
                mContext.startActivity(intent);
            }
        });
    }
}
