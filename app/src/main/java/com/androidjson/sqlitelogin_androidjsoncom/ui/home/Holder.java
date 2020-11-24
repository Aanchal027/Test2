package com.androidjson.sqlitelogin_androidjsoncom.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.androidjson.sqlitelogin_androidjsoncom.R;

public class Holder extends RecyclerView.ViewHolder {
    TextView title,infoTitle;
    ImageView iconImage,infoIconImage;

    CardView cardView,infoCard;

    Holder(@NonNull View itemView) {
        super(itemView);
        this.title = itemView.findViewById(R.id.title);
        this.iconImage = itemView.findViewById(R.id.icon);
        cardView = itemView.findViewById(R.id.cardview);
        infoCard = itemView.findViewById(R.id.infoCards);
        infoTitle = itemView.findViewById(R.id.contentTitleText);
        infoIconImage = itemView.findViewById(R.id.contentImageIcon);
    }
}
