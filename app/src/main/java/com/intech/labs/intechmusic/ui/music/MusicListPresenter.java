package com.intech.labs.intechmusic.ui.music;

import android.text.TextUtils;
import android.util.Log;

import com.intech.labs.intechmusic.data.MusicRepository;
import com.intech.labs.intechmusic.retrofit.model.Song;
import com.intech.labs.intechmusic.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Anatoly Chernyshev on 11.04.2018.
 */

@Singleton
public class MusicListPresenter<V extends IMusicListActivity> extends BasePresenter<V> {

    private static final String TAG = MusicListPresenter.class.getSimpleName();

    private MusicRepository mMusicRepository;

    private String mLastSearchQuery;

    @Inject
    public MusicListPresenter(MusicRepository musicRepository) {
        mMusicRepository = musicRepository;
    }

    public void onCreate() {

        mView.setupRecyclerView();

        if (!TextUtils.isEmpty(mLastSearchQuery))
            updateAdapterList(mLastSearchQuery);
    }

    public void onQueryTextSubmit(String query) {

        if (query.length() < 5) {
            mView.showIncorrectLengthError();
            return;
        }

        mLastSearchQuery = query;
        updateAdapterList(query);
    }

    public void onQueryTextChange(String newText) {

        if (newText.length() < 5) return;

        mLastSearchQuery = newText;

        mCompositeDisposable.add(
                mMusicRepository
                        .getSongList(newText)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            mMusicRepository.setSongList(response.getSongs());
                            mView.updateList(response.getSongs());
                        }, error -> {
                            mView.showError();
                            mView.hideProgressBar();
                        }));
    }

    public void onClick(int position) {
        mView.openPlayerActivity(position);
    }

    private void updateAdapterList(String query){
        mView.showProgressBar();
        mCompositeDisposable.add(
                mMusicRepository
                        .getSongList(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            mMusicRepository.setSongList(response.getSongs());
                            mView.updateList(response.getSongs());
                            mView.hideProgressBar();
                        }, error -> {
                            mView.showError();
                            mView.hideProgressBar();
                        }));
    }
}
