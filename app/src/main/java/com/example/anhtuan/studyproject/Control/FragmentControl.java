package com.example.anhtuan.studyproject.Control;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.example.anhtuan.studyproject.R;
import com.example.anhtuan.studyproject.TypeButton;
import com.example.anhtuan.studyproject.ledControl;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FragmentControl#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentControl extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnConnect;
    private Button btnDisconnect;

    private Button btnUp;
    private Button btnDown;
    private Button btnLeft;
    private Button btnRight;

    private Button btnUpDown;
    private Button btnLeftRight;
    private Button btnRotate;

    private Switch swtDance;

    private ledControl mledControl;

    private RelativeLayout layout_Dancing;
    private RelativeLayout layout_Control;

    public FragmentControl() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentControl.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentControl newInstance(String param1, String param2) {
        FragmentControl fragment = new FragmentControl();
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
        View view = inflater.inflate(R.layout.fragment_fragment_control, container, false);

        btnUp = (Button)view.findViewById(R.id.btnUp);
        btnDown = (Button)view.findViewById(R.id.btnDown);
        btnLeft = (Button)view.findViewById(R.id.btnLeft);
        btnRight = (Button)view.findViewById(R.id.btnRight);
        btnConnect = (Button)view.findViewById(R.id.btnConnect);
        btnDisconnect = (Button)view.findViewById(R.id.btnDisconnect);
        swtDance = (Switch)view.findViewById(R.id.swtDance);
        layout_Control = (RelativeLayout)view.findViewById(R.id.layoutDance);
        layout_Dancing = (RelativeLayout)view.findViewById(R.id.viewRight);


        btnLeftRight = (Button)view.findViewById(R.id.btnLeftRight);
        btnUpDown = (Button)view.findViewById(R.id.btnUpDown);
        btnRotate = (Button)view.findViewById(R.id.btnRotate);

        setupCommand();
        return view;
    }

    private void setupCommand(){
        btnUp.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sendCommand("F");
                        break;
                    case MotionEvent.ACTION_UP:
                        sendCommand("D");
                        break;
                }
                return false;
            }
        });

        btnDown.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sendCommand("B");
                        break;
                    case MotionEvent.ACTION_UP:
                        sendCommand("D");
                        break;
                }
                return false;
            }
        });

        btnLeft.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sendCommand("L");
                        break;
                    case MotionEvent.ACTION_UP:
                        sendCommand("D");
                        break;
                }
                return false;
            }
        });

        btnRight.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sendCommand("R");
                        break;
                    case MotionEvent.ACTION_UP:
                        sendCommand("D");
                        break;
                }
                return false;
            }
        });

        btnConnect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sendCommand("+CONN");
            }
        });

        btnDisconnect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sendCommand("+DISC");
            }
        });

        swtDance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                changeMode(b);
            }
        });


        btnUpDown.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        setCurrentHold(TypeButton.UPDOWN);
                        break;
                    case MotionEvent.ACTION_UP:
                        setCurrentHold(TypeButton.NONE);
                        break;
                }
                return false;
            }
        });

        btnLeftRight.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        setCurrentHold(TypeButton.LEFTRIGHT);
                        break;

                    case MotionEvent.ACTION_UP:
                        setCurrentHold(TypeButton.NONE);
                        break;
                }
                return false;
            }
        });

        btnRotate.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        setCurrentHold(TypeButton.ROTATE);
                        break;
                    case MotionEvent.ACTION_UP:
                        setCurrentHold(TypeButton.NONE);
                        break;
                }
                return false;
            }
        });
    }

    private void changeMode(boolean dance){
        if (dance){
            layout_Dancing.setVisibility(RelativeLayout.VISIBLE);
            layout_Control.setVisibility(RelativeLayout.INVISIBLE);
        }else{
            layout_Dancing.setVisibility(RelativeLayout.INVISIBLE);
            layout_Control.setVisibility(RelativeLayout.VISIBLE);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event

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

    private void sendCommand(String s){
        this.mledControl.sendData(s);
    }

    private void setCurrentHold(TypeButton type){
        this.mledControl.setCurrentHold(type);
    }
}
