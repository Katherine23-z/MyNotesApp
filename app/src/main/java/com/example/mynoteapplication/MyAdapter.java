package com.example.mynoteapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    /*private List<Note> notes;*/
    private NoteSource noteSource;
    private OnItemClickListener listener;

    public MyAdapter(NoteSource noteSource) {
        this.noteSource = noteSource;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(noteSource.getNote(position));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return noteSource.size();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

     class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }

        void bind(Note note) {
            textView.setText(note.getTitle());
            textView.setOnClickListener(v -> {
                if(listener != null){
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }

    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }
}
