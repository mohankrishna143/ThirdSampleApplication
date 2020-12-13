package adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.android.sampleapplication.MainActivity;
import com.android.sampleapplication.R;
import com.android.sampleapplication.ThirdScreen;

import java.util.ArrayList;


public class LiveGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    OnItemClickListener mItemClickListener;
    int itemSize;

    public LiveGridAdapter(Context mContext,int size) {
        this.context = mContext;
        this.itemSize=size;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View listItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ListHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        try {
            ListHolder holder = (ListHolder) viewHolder;
            holder.btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    displayAlertDialog();

                }
            });

            holder.btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(context instanceof MainActivity){

                        ((MainActivity) context).nextFragment(1);
                    }

                }
            });

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return itemSize;
    }


    public void updateItems(int size){
        this.itemSize=size;

    }

    private class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_subjectid, tv_sbjname,tv_sbjstatus,tv_rch,tv_enrol,tv_queries;
       // ProgressBar tv_progress;
      Button btn_edit,btn_delete;

        private ListHolder(View v) {
            super(v);

            btn_edit=v.findViewById(R.id.btn_edit);
            btn_delete=v.findViewById(R.id.btn_delete);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                // focusedVodItem = getLayoutPosition();
                mItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);


    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private  void displayAlertDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Are you sure you want to delete?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (itemSize > 1){
                            itemSize = itemSize - 1;
                            notifyDataSetChanged();
                        }
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
