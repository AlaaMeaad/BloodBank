package com.example.bloodbank.view.fragment.homeCycle;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.PostAdapter;
import com.example.bloodbank.data.api.ApiServers;
import com.example.bloodbank.data.model.allposts.AllPosts;
import com.example.bloodbank.data.model.allposts.PostsData;
import com.example.bloodbank.view.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;
import static com.example.bloodbank.helper.BloodConstants.API_TOKEN;
import static com.example.bloodbank.helper.HelperMethod.dismissProgressDialog;
import static com.example.bloodbank.helper.HelperMethod.showProgressDialog;
import static com.example.bloodbank.helper.ShrdPreferences.LoadStringData;

/**
 * A simple {@link Fragment} subclass.
 */


public class PostsFragment extends BaseFragment {

    @BindView(R.id.posts_fragment_rv_posts)
    RecyclerView postsFragmentRvPosts;

    private ApiServers apiServers;
    private LinearLayoutManager linearLayoutManager;
    private List<PostsData> postDataList = new ArrayList<>();
    private PostAdapter postAdapter;

    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //initFragment();
        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        ButterKnife.bind(this, view);
        apiServers = getClient().create(ApiServers.class);
        showProgressDialog(getActivity(), "Loading");
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        linearLayoutManager
                = new LinearLayoutManager(getActivity());
        //GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity() , 3);
        postsFragmentRvPosts.setLayoutManager(linearLayoutManager);
        postAdapter = new PostAdapter(getActivity(), getActivity(), postDataList);
        postsFragmentRvPosts.setAdapter(postAdapter);
        getAllPosts(1);
    }


    private void getAllPosts(int pages) {
        String apitoken = LoadStringData(getActivity(), API_TOKEN);

        apiServers.getAllPosts(apitoken, pages).enqueue(new Callback<AllPosts>() {
            @Override
            public void onResponse(Call<AllPosts> call, Response<AllPosts> response) {
                dismissProgressDialog();

                try {

                    if (response.body().getStatus() == 1) {

                        postDataList.addAll(response.body().getData().getData());

                        postAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<AllPosts> call, Throwable t) {
                dismissProgressDialog();
            }

        });
    }


    @Override
    public void onBack() {
        super.onBack();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
