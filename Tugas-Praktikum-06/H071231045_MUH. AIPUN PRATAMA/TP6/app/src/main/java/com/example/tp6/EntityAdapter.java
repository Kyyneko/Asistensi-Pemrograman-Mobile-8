package com.example.tp6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class EntityAdapter extends RecyclerView.Adapter<EntityAdapter.ViewHolder> {
    public List<Entity> entities;

    public EntityAdapter(List<Entity> entities) {
        this.entities = entities;
    }

    @NonNull
    @Override
    public EntityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntityAdapter.ViewHolder holder, int position) {
        Entity entity = entities.get(position);
        holder.setData(entity);
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView backgroundImage;
        private TextView name;
        private TextView species;
        private TextView status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            backgroundImage = itemView.findViewById(R.id.image_background);
            name = itemView.findViewById(R.id.name);
            species = itemView.findViewById(R.id.species);
            status = itemView.findViewById(R.id.status);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(entities.get(position));
                }
            });
        }


        public void setData(Entity entity) {
            Picasso.get().load(entity.getImage()).into(backgroundImage);
            name.setText(entity.getName());
            species.setText(entity.getSpecies());
            status.setText(entity.getStatus());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Entity entity);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
