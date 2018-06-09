package aksenchyk.doit.navigation_views_fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import aksenchyk.doit.R;
import aksenchyk.doit.adapters.TasksAdapter;
import aksenchyk.doit.models.Tasks;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgectsFragment extends Fragment {



    private FirebaseFirestore mFirestore;

    private RecyclerView mTasksListRecyclerView;
    private List<Tasks> tasksList;
    private TasksAdapter tasksAdapter;

    private String userUID;

    public ProgectsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progects, container, false);
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        tasksList = new ArrayList<>();
        tasksAdapter = new TasksAdapter(tasksList);

        mTasksListRecyclerView = (RecyclerView) view.findViewById(R.id.tasksListRecyclerView);
        mTasksListRecyclerView.setHasFixedSize(true);
        mTasksListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTasksListRecyclerView.setAdapter(tasksAdapter);


        mFirestore = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userUID = user.getUid().toString();




        mFirestore.collection("Users").document(userUID).collection("Tasks").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if(e != null) {
                    Log.d("FireTag","Error" + e.getMessage());
                }

                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if(doc.getType() == DocumentChange.Type.ADDED) {
                        //  String name = doc.getDocument().getString("name");
                        //  Toast.makeText(getActivity(),name,Toast.LENGTH_SHORT).show();

                        Tasks tasks =  doc.getDocument().toObject(Tasks.class);
                        tasksList.add(tasks);
                        tasksAdapter.notifyDataSetChanged();
                    }

                }

            }
        });






    }


}
