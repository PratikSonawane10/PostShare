package project.pratik.facebookshare.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.HashMap;
import java.util.List;

import project.pratik.facebookshare.Model.ShowMenuItems;
import project.pratik.facebookshare.R;
import project.pratik.facebookshare.SessionManager.SessionManager;
import project.pratik.facebookshare.WebService;


public class ShowMenuAdapter extends RecyclerView.Adapter<ShowMenuAdapter.ViewHolder>{

    List<ShowMenuItems> showMenuItems;
    View v;
    ViewHolder viewHolder;
    private static Context context;
    String displayText;
    String remarkOfMate;
    String menuId;
    String method = "AddCookingFoodRemark";



    public ShowMenuAdapter(List<ShowMenuItems> notificationItemsArrayList) {
        this.showMenuItems = notificationItemsArrayList;

   }
// public ShowMenuAdapter(List<ShowMenuItems> notificationItemsArrayList, ShowMenu showMenu) {
//        this.showMenuItems = notificationItemsArrayList;
//        context = showMenu;
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main, viewGroup, false);
        viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ShowMenuItems notificationItem = showMenuItems.get(position);
        viewHolder.bindNotificationDetailsList(notificationItem );
    }

    @Override
    public int getItemCount() {
        return showMenuItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public TextView descriptipon;
        public ImageView image;
        public View cardView;

        RelativeLayout layoutMateRemark;

        ShowMenuItems showMenuItems = new ShowMenuItems();


        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            descriptipon = (TextView) itemView.findViewById(R.id.description);
            image = (ImageView) itemView.findViewById(R.id.image);

            cardView = itemView;
            cardView.setOnClickListener(this);
        }

        public void bindNotificationDetailsList(ShowMenuItems showMenuItems) {
            this.showMenuItems = showMenuItems;

            Glide.with(image.getContext()).load(showMenuItems.getFirstImagePath()).asBitmap().centerCrop().into(new BitmapImageViewTarget(image) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(image.getContext().getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    image.setImageDrawable(circularBitmapDrawable);
                }
            });

            title.setText(showMenuItems.gettitle());
            descriptipon.setText(showMenuItems.getdescription());


        }

        @Override
        public void onClick(View v) {



        }
    }


}
