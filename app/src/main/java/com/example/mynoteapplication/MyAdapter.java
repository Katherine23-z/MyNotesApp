package com.example.mynoteapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private NoteSource noteSource;
    private Fragment fragment;
    private OnItemClickListener listener;
    private int menuPosition;

    public MyAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setNoteSource(NoteSource noteSource){
        this.noteSource = noteSource;
        notifyDataSetChanged();
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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public int getMenuPosition() {
        return menuPosition;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView content;
        private AppCompatImageView image;
        private TextView date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            image = itemView.findViewById(R.id.image);
            date = itemView.findViewById(R.id.date);

            registerContextMenu(itemView);

            image.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(MyViewHolder.this.getAdapterPosition());
                }
            });

            image.setOnLongClickListener(v -> {
                menuPosition = getLayoutPosition();
                itemView.showContextMenu();
                return true;
            });
        }

        private void registerContextMenu(View itemView) {
            if (fragment != null) {
                itemView.setOnLongClickListener(v -> {
                    menuPosition = getLayoutPosition();
                    return false;
                });
                fragment.registerForContextMenu(itemView);
            }
        }

            void bind (Note note){
                title.setText(note.getTitle());
                content.setText(note.getText());
                image.setImageResource(note.getPicture());
                date.setText(new SimpleDateFormat("dd-MM-yy").format(note.getDate()));

            }
        }


    }

