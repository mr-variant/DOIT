package aksenchyk.doit.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import aksenchyk.doit.R;
import aksenchyk.doit.models.Tasks;

/**
 * Created by ixvar on 6/8/2018.
 */

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    private List<Tasks> tasksList;


    public TasksAdapter(List<Tasks> tasksList) {
            this.tasksList = tasksList;
            }

    @Override
    public TasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_list_item,parent,false);
            return new TasksAdapter.ViewHolder(view);
            }

    @Override
    public void onBindViewHolder(TasksAdapter.ViewHolder holder, int position) {
            holder.nicknameTextView.setText(tasksList.get(position).getCategory());
            holder.messageTextView.setText(tasksList.get(position).getName());
            holder.dateTextView.setText(tasksList.get(position).getStatus().toString());
            }

    @Override
    public int getItemCount() {
            return tasksList.size();
            }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public TextView nicknameTextView;
        public TextView messageTextView;
        public TextView dateTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            nicknameTextView = (TextView) mView.findViewById(R.id.nicknameTextView);
            messageTextView = (TextView) mView.findViewById(R.id.messageTextView);
            dateTextView = (TextView) mView.findViewById(R.id.dateTextView);
        }

    }


}