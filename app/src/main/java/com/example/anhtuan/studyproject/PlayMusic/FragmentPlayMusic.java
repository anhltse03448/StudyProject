package com.example.anhtuan.studyproject.PlayMusic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anhtuan.studyproject.R;
import com.example.anhtuan.studyproject.ledControl;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPlayMusic.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPlayMusic#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPlayMusic extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnPlay;
    private Button btnPrevious;
    private Button btnNext;

    private Button btnAddSong;
    private ListView lvSongs;
    private TextView tvName;

    private ledControl mledControl;

    public FragmentPlayMusic() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPlayMusic.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPlayMusic newInstance(String param1, String param2) {
        FragmentPlayMusic fragment = new FragmentPlayMusic();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_play_music, container, false);

        this.initUI(view);
        this.setBtnFunction();
        return view;
    }

    private void initUI(View view){
        btnPlay = (Button)view.findViewById(R.id.btnPlay);
        btnNext = (Button)view.findViewById(R.id.btnNext);
        btnPrevious = (Button)view.findViewById(R.id.btnPrevious);
        tvName = (TextView)view.findViewById(R.id.tvName);
        lvSongs = (ListView)view.findViewById(R.id.lvSongs);
        btnAddSong = (Button)view.findViewById(R.id.btnAddMusic);
    }

    private void setBtnFunction(){
        btnAddSong.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openSongPicker();
            }
        });
    }

    private void openSongPicker(){
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ledControl) {
            this.mledControl = (ledControl)context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.mledControl = null;
    }
}
