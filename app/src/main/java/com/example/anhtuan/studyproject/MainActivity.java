package com.example.anhtuan.studyproject;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhtuan.studyproject.Control.FragmentControl;
import com.example.anhtuan.studyproject.PlayMusic.FragmentPlayMusic;
import com.example.anhtuan.studyproject.PlayMusic.FragmentRecord;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button btnPaired;
    ListView deviceList;
    public static String EXTRA_ADDRESS = "device_address";
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;

    FragmentControl fragmentControl;
    FragmentPlayMusic fragmentPlayMusic;
    FragmentRecord fragmentRecord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
        checkBluetoot();
        setEventForBtnPaired();
    }

    private void setup(){
        btnPaired = (Button)findViewById(R.id.btnPairedDevice);
        deviceList = (ListView)findViewById(R.id.lvDevice);
        fragmentControl = (FragmentControl)this.getSupportFragmentManager().findFragmentById(R.id.fragmentControl);
        fragmentRecord = (FragmentRecord)this.getSupportFragmentManager().findFragmentById(R.id.fragmentRecord);
        fragmentPlayMusic = (FragmentPlayMusic)this.getSupportFragmentManager().findFragmentById(R.id.fragmentPlayMusic);
    }

    private void checkBluetoot(){
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if (myBluetooth == null){
            Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();

            finish();
        }
        else {
            if (myBluetooth.isEnabled()){

            }else{
                Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                startActivityForResult(turnBTon,1);
            }
        }
    }

    private void setEventForBtnPaired(){
        btnPaired.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                pairedDevicesList();
            }
        });
    }

    private void pairedDevicesList(){
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size() > 0){
            for (BluetoothDevice bt : pairedDevices){
                list.add(bt.getName() + "\n" + bt.getAddress());
            }
        } else {
            Toast.makeText(getApplicationContext(),"No Paired Bluetooth Devices Found. ", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        deviceList.setAdapter(adapter);

        deviceList.setOnItemClickListener(myListClickListener);
        // set On Item Click
        //T.B.D
    }
    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String info = ((TextView) view).getText().toString();
            String address = info.substring(info.length() - 17);

            Intent intent = new Intent(MainActivity.this,ledControl.class);

            // Change Activity
            intent.putExtra(EXTRA_ADDRESS, address);
            startActivity(intent);
        }
    };
}
