package mno.mohamed_youssef.myfaculty.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;

import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Info;
import mno.mohamed_youssef.myfaculty.model.TeamInfo;

/**
 * Created by Mohamed Yossif on 13/10/2016.
 */
public class InfoTeamAdapterR extends RecyclerView.Adapter<InfoTeamAdapterR.ViewHolder> {

    private Context context;
    private LinkedList<TeamInfo> teamInfo;
    private LayoutInflater inflater;
    private StorageReference storageRef;




    public InfoTeamAdapterR(Context context , LinkedList<TeamInfo> teamInfo){

        this.context = context;
        this.teamInfo =teamInfo ;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //storageRef = Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/images");

    }

    @Override
    public InfoTeamAdapterR.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_infoteam, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InfoTeamAdapterR.ViewHolder holder, int position) {
        TeamInfo teamInf =teamInfo.get(position);
        holder.infoTeamTitle.setText(teamInf.getTitle()+" # ");
        holder.infoTeamContent.setText(teamInf.getContent());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return teamInfo.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        private TextView infoTeamTitle;
        private TextView infoTeamContent;
        public  View view;
        public ViewHolder(View v ) {
            super(v);
            this.view=v;
            infoTeamTitle = (TextView) v.findViewById(R.id.textInfoTeam);
            infoTeamContent =(TextView) v.findViewById(R.id.contentInfoTeam) ;

        }
    }
}
