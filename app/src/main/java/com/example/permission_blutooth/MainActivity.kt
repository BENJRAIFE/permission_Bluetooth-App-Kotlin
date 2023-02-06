package com.example.permission_blutooth

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

        private var CODE_ENABLE = 1
        private var CODE_DISCOVERABLE = 2
        lateinit var statusBluetooth:TextView
        lateinit var paired:TextView
        lateinit var on_btn:Button
        lateinit var off_btn:Button
        lateinit var discoverable_btn:Button
        lateinit var paired_btn:Button
        lateinit var bluetoothAdapter: BluetoothAdapter
        @SuppressLint("MissingInflatedId", "MissingPermission")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            on_btn = findViewById(R.id.on_btn)
            off_btn = findViewById(R.id.off_btn)
            discoverable_btn = findViewById(R.id.discoverable_btn)
            paired_btn = findViewById(R.id.paired_device_btn)
            statusBluetooth = findViewById(R.id.status_text)
            paired = findViewById(R.id.list_paired)
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()


            if(bluetoothAdapter == null)
                statusBluetooth.text = "Bluetooth is not avilable"
            else
                statusBluetooth.text = "Bluetooth is avilable"

            if(bluetoothAdapter.isEnabled)
            else

            on_btn.setOnClickListener{
                if (bluetoothAdapter.isEnabled){
                    Toast.makeText(this,"Bluetooth is already ON",Toast.LENGTH_LONG).show()
                }else{
                    var intent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startActivityForResult(intent,CODE_ENABLE)
                }
            }

            off_btn.setOnClickListener {
                if (!bluetoothAdapter.isEnabled){
                    Toast.makeText(this,"Bluetooth is already OFF",Toast.LENGTH_LONG).show()
                }else{
                    bluetoothAdapter.disable()
                    Toast.makeText(this,"Bluetooth turned off",Toast.LENGTH_LONG).show()


                }
            }

            discoverable_btn.setOnClickListener {
                if (!bluetoothAdapter.isDiscovering){
                    Toast.makeText(this,"making your device discoverable",Toast.LENGTH_LONG).show()
                    var intent = Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE)
                    startActivityForResult(intent,CODE_DISCOVERABLE)

                }
            }

            paired_btn.setOnClickListener {
                if (bluetoothAdapter.isEnabled){
                    paired.text= "Paired Devices"
                    val devices = bluetoothAdapter.bondedDevices
                    for (devices in devices){
                        val deviceName = devices.name
                        val devceAdress = devices
                        paired.append("\nDevices: $deviceName , $devices")
                    }
                }else{
                    Toast.makeText(this,"turn on bluetooth first",Toast.LENGTH_LONG).show()

                }
            }

        }

        override fun onActivityReenter(resultCode: Int, data: Intent?) {
            when(resultCode){
                CODE_ENABLE->
                    if (resultCode == Activity.RESULT_OK){
                        Toast.makeText(this,"bluetooth turn  on",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this," bluetooth turn off",Toast.LENGTH_LONG).show()
                    }
            }


    }

}