package com.example.googledeveloper;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LearnersFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView errorTextView;
    ArrayList<User> ListItems;
    String learning = "Learning Hours";

    private static final String TAG = "LearnersFragment main";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_learners, container, false);

        Log.d(TAG, "onCreate: inflating the view");
        recyclerView = root.findViewById(R.id.rv_list_learning);
        progressBar = root.findViewById(R.id.pb_loading);
        errorTextView = root.findViewById(R.id.tv_error);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);

        getDataFromServer();

        return root;
    }

    public void showRecyclerView() {
        MainRecyclerViewAdapter listAdapter = new MainRecyclerViewAdapter(ListItems, getActivity());
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void getDataFromServer() {
        String url = "https://gadsapi.herokuapp.com/api/hours";
        ListItems = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            ListItems.add(new User(
                                    data.getString("name"),
                                    data.getString("country"),
                                    data.getString("hours"),
                                    R.drawable.top_learner,
                                    learning
                            ));
                        }showRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        Log.e(TAG, "onResponse: " + error);
                        errorTextView.setVisibility(View.VISIBLE);
                    }
                });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

}