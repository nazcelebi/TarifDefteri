package com.example.tarifdefteri.adapter;

import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tarifdefteri.NoteListener;
import com.example.tarifdefteri.R;
import com.example.tarifdefteri.model.Not;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    ArrayList<Not> notListesi;
    NoteListener listener;

    public NoteAdapter(ArrayList<Not> notListesi, NoteListener listener) {
        this.notListesi = notListesi;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.SetNote(notListesi.get(position));
    }

    @Override
    public int getItemCount() {
        return notListesi.size();
    }


    class NoteViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewContainer;
        TextView textTitle, textNote, textDate,textNoteUrl;
        ImageView imageNoteImage;


        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewContainer = itemView.findViewById(R.id.cardViewContainer);
            textTitle = itemView.findViewById(R.id.textTitle);
            textNote = itemView.findViewById(R.id.textNote);
            textDate = itemView.findViewById(R.id.textDate);
            imageNoteImage = itemView.findViewById(R.id.imageNoteImage);
            textNoteUrl = itemView.findViewById(R.id.textNoteUrl);
        }

        void SetNote(Not not) {
                textTitle.setText(not.getBaslik());
                textNote.setText(not.getNotMetin());
                textDate.setText(not.getTarih());
                cardViewContainer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.NoteClick(not);
                    }

                });
                if (not.getImageUrl() != null && not.getImageUrl().length() > 4) {
                    imageNoteImage.setVisibility(View.VISIBLE);
                    imageNoteImage.setImageBitmap(BitmapFactory.decodeFile(not.getImageUrl()));
                }else{
                    imageNoteImage.setVisibility(View.GONE);
                }

                if (not.getWebUrl() != null &&  not.getWebUrl().length() > 4) {
                    textNoteUrl.setVisibility(View.VISIBLE);
                    textNoteUrl.setText(not.getWebUrl());
                }else {
                    textNoteUrl.setVisibility(View.GONE);
                }
        }
    }
}
