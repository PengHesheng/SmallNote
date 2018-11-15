package com.example.a14512.smallnote.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.a14512.smallnote.R;
import com.example.a14512.smallnote.mode.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 14512 on 2018/11/4
 */
public class AllNotesAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<Note> mNotes = new ArrayList<>();
    private Map<Integer, Boolean> mDelMap = new HashMap<>();
    private int mOrientation;
    private OnRecyclerListener mListener;

    public AllNotesAdapter(int orientation) {
        this.mOrientation = orientation;
    }

    public void setOrientation(int orientation) {
        mOrientation = orientation;
        notifyDataSetChanged();
    }

    public void addNotes(ArrayList<Note> notes) {
        if (notes == null) {
            return;
        }
        mNotes.addAll(notes);
        for (int i = 0; i < notes.size(); i++) {
            mDelMap.put(i, false);
        }
        notifyDataSetChanged();
    }

    public boolean addNote(Note note) {
        if (note == null) {
            return false;
        }
        boolean success = mNotes.add(note);
        mDelMap.put(getItemCount(), false);
        notifyDataSetChanged();
        return success;
    }

    public boolean updateNote(int pos, Note note) {
        if (pos < 0 || pos > getItemCount() || note == null) {
            return false;
        }
        mNotes.remove(pos);
        mNotes.add(pos, note);
        notifyDataSetChanged();
        return true;
    }

    public boolean deleteNote(int position) {
        Note delNote = mNotes.remove(position);
        notifyDataSetChanged();
        return delNote != null;
    }

    public boolean delNotes(int[] positions) {
        if (positions == null || positions.length == 0) {
            return false;
        }
        for (int pos : positions) {
            mNotes.remove(pos);
        }
        notifyDataSetChanged();
        return true;
    }

    public void setListener(OnRecyclerListener listener) {
        mListener = listener;
    }

    public Note getNote(int position) {
        if (position < 0 || position >= getItemCount()) {
            return null;
        }
        return mNotes.get(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = null;
        if (mOrientation == RecyclerView.HORIZONTAL) {
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.recycler_view_item_horizontal, parent, false);
        } else if (mOrientation == RecyclerView.VERTICAL) {
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.recycler_view_item_vertical, parent, false);
        }
        return new NotesViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NotesViewHolder) {
            if (mListener != null) {
                holder.itemView.setOnLongClickListener(v -> {
                    ((NotesViewHolder) holder).checkBox.setVisibility(View.VISIBLE);
                    return mListener.onItemLongClick(v, position);
                });
                if (((NotesViewHolder) holder).checkBox.isCursorVisible()) {
                    holder.itemView.setOnClickListener(v -> {
                        if (((NotesViewHolder) holder).checkBox.isChecked()) {
                            ((NotesViewHolder) holder).checkBox.setChecked(false);
                        } else {
                            ((NotesViewHolder) holder).checkBox.setChecked(true);
                        }
                    });
                    ((NotesViewHolder) holder).checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        mDelMap.put(position, isChecked);
                    });
                } else {
                    holder.itemView.setOnClickListener(v -> mListener.onItemClick(v, position));
                }
            }
            Note note = mNotes.get(position);
            ((NotesViewHolder) holder).title.setText(note.getTitle());
            ((NotesViewHolder) holder).content.setText(note.getContent());
            ((NotesViewHolder) holder).date.setText(String.valueOf(note.getDate()));
        }
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    private class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        TextView date;
        CheckBox checkBox;

        NotesViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitleRecycleItem);
            content = itemView.findViewById(R.id.tvContentRecycleItem);
            date = itemView.findViewById(R.id.tvDateRecycleItem);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
