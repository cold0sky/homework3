package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PlaceholderFragment extends Fragment {
    private View view;
    private RecyclerView mNumbersListView;
    private AnimatorSet animatorSet;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件

        view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        mNumbersListView = view.findViewById(R.id.rv_list);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        mNumbersListView.setLayoutManager(layoutManager);

        mNumbersListView.setHasFixedSize(true);
        mNumbersListView.setAdapter(new GreenAdapter(8));
        //mNumbersListView.setVisibility(View.INVISIBLE);
        mNumbersListView.setAlpha(0f);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(view.findViewById(R.id.animation_view) , "alpha" , 1f , 0f);
                animator1.setDuration(1000);

                //mNumbersListView.setVisibility(View.VISIBLE);

                ObjectAnimator animator2 = ObjectAnimator.ofFloat(view.findViewById(R.id.rv_list) , "alpha" , 0f , 1f);
                animator2.setDuration(1000);

                animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator1 , animator2);
                animatorSet.start();
            }
        }, 5000);
    }
}
