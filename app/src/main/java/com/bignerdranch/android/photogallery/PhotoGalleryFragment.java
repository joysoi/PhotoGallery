package com.bignerdranch.android.photogallery;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.photogallery.retrofit.ApiUtil;
import com.bignerdranch.android.photogallery.retrofit.models.JSONResponse;
import com.bignerdranch.android.photogallery.retrofit.models.Photo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoGalleryFragment extends Fragment {

    RecyclerView mPhotoRecyclerView;
    PhotoAdapter mPhotoAdapter;

    public static PhotoGalleryFragment newInstance() {
        return new PhotoGalleryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        mPhotoRecyclerView = view.findViewById(R.id.photo_recycler_view);
        mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mPhotoAdapter = new PhotoAdapter();
        mPhotoRecyclerView.setAdapter(mPhotoAdapter);
        loadPhotos();
        return view;
    }

    private void loadPhotos() {
        ApiUtil.getApiService().getPhotos().enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                if (response.isSuccessful()) {
                    JSONResponse jsonResponse = response.body();
                    List<Photo> photosList = null;
                    if (jsonResponse != null) {
                        photosList = jsonResponse.getPhotos().getRecentPhoto();
                    }
                    mPhotoAdapter.updatePhotoList(photosList);
                }
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error" + " " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

// ADAPTER

    public class PhotoAdapter extends RecyclerView.Adapter<PhotoViewHolder> {
        private List<Photo> mItemList = new ArrayList<>();

        public void updatePhotoList(List<Photo> photos) {
            this.mItemList.clear();
            this.mItemList.addAll(photos);
            notifyDataSetChanged();
        }

        @Override
        public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_tem_layout, parent, false);
            return new PhotoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PhotoViewHolder holder, int position) {
            Photo item = mItemList.get(position);
            holder.bindActivity(item);
        }

        @Override
        public int getItemCount() {
            return mItemList.size();
        }
    }

// VIEW_HOLDER

    private class PhotoViewHolder extends RecyclerView.ViewHolder {
        TextView viewId;
        TextView viewTitle;
        Photo mPhoto;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            viewId = itemView.findViewById(R.id.textId);
            viewTitle = itemView.findViewById(R.id.textTitle);
        }

        public void bindActivity(Photo item) {
            mPhoto = item;
            viewId.setText(mPhoto.getId());
            viewTitle.setText(mPhoto.getTitle());
        }
    }


}
