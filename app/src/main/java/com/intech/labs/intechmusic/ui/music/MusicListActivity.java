package com.intech.labs.intechmusic.ui.music;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.intech.labs.intechmusic.R;
import com.intech.labs.intechmusic.adapter.ItemClickListener;
import com.intech.labs.intechmusic.adapter.MusicListAdapter;
import com.intech.labs.intechmusic.app.App;
import com.intech.labs.intechmusic.di.module.MusicActivityModule;
import com.intech.labs.intechmusic.retrofit.model.Song;
import com.intech.labs.intechmusic.ui.player.PlayerActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.intech.labs.intechmusic.Constants.SONG_POSITION;

public class MusicListActivity extends AppCompatActivity implements IMusicListActivity,
        ItemClickListener {

    @BindView(R.id.songs_recycler_view)     RecyclerView    mMusicRecyclerView;
    @BindView(R.id.song_list_progress_bar)  ProgressBar     mProgressBar;
    @BindView(R.id.music_list_helper_label) TextView        mHelperTextView;

    @Inject MusicListPresenter<IMusicListActivity>  mMusicListPresenter;
    @Inject MusicListAdapter                        mMusicListAdapter;
    @Inject LinearLayoutManager                     mGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        init();

        mMusicListPresenter.onCreate();

    }

    private void init() {
        ButterKnife.bind(this);
        App.getComponent()
                .musicActivityComponent(new MusicActivityModule(this, this))
                .inject(this);
        mMusicListPresenter.onAttach(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mMusicListPresenter.onQueryTextSubmit(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mMusicListPresenter.onQueryTextChange(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public void setupRecyclerView() {
        mMusicRecyclerView.setLayoutManager(mGridLayoutManager);
        mMusicRecyclerView.setAdapter(mMusicListAdapter);
        mMusicRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void updateList(List<Song> songs) {
        mHelperTextView.setVisibility(View.INVISIBLE);
        mMusicListAdapter.updateList(songs);
    }

    @Override
    public void showIncorrectLengthError() {
        Snackbar.make(findViewById(R.id.music_list_container), R.string.query_length_error,
                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void openPlayerActivity(int position) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(SONG_POSITION, position);
        startActivity(intent);
    }

    @Override
    public void showError() {
        Snackbar.make(findViewById(R.id.music_list_container), R.string.error,
                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMusicListPresenter.onDetach();
    }

    @Override
    public void onItemClicked(int position) {
        mMusicListPresenter.onClick(position);
    }
}
