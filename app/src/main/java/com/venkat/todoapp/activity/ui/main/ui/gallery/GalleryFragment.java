
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

    public ToggleButton option_selector;
    private ItemClickListener itemClickListener;



    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        option_selector= (ToggleButton)itemView.findViewById(R.id.eachToggle);
        option_selector.setOnClickListener(this);
    }
    public void setItemClickListener (ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;

    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);

    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return false;
    }
}
public class RecyclerAdapter  extends RecyclerView.Adapter<RecyclerViewHolder>{
    public RecyclerAdapter(List<String> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    private List<String> listData =new ArrayList<>();
    public static List<String> select = new ArrayList<>();
    public static List<Integer> click = new ArrayList<>();
    public static String str ;

    private Context context;
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemview = inflater.inflate(R.layout.each_option,viewGroup,false);
        return new RecyclerViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder recyclerViewHolder, final int i) {
        recyclerViewHolder.option_selector.setText(listData.get(i));
        recyclerViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                recyclerViewHolder.option_selector.setText(listData.get(position));
                if(recyclerViewHolder.option_selector.isChecked())
                    recyclerViewHolder.option_selector.setTextColor(ContextCompat.getColor(context, R.color.white));
                else{
                    recyclerViewHolder.option_selector.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

                }

                int flag=0;
                for(int x=0 ;x<click.size();x++)
                {
                    if (position == click.get(x)){
                        flag=1;
                        break;
                    }
                }


                click.add(position);
                if(flag==0){
                    select.add(listData.get(position));
                }

                str="";

                for (String s : select)
                {
                    str += s + " ";
                }
                Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}