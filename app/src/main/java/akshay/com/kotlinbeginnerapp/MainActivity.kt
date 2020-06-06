package akshay.com.kotlinbeginnerapp

import akshay.com.kotlinbeginnerapp.utils.BatteryUtils
import akshay.com.kotlinbeginnerapp.utils.StorageUtils
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       btn_product_details.setOnClickListener {
           val intent = Intent(this@MainActivity,ShowProductDetailsActivity::class.java)
           startActivity(intent)
       }
    }

    private fun setTotalStorageData(){
        val totalStorageString = applicationContext.resources.getString(R.string.total_storage)
        val totalStorageValue = StorageUtils.convertLongDataToString(0)
        val storageData = "\n" + totalStorageString + "\n"+totalStorageValue + "\n"
        tv_total_storage.text = storageData
    }

    private fun setTotalFreeStorageData(){
        val totalFreeStorageString = applicationContext.resources.getString(R.string.free_storage)
        val totalFreeStorageValue = StorageUtils.convertLongDataToString(1)
        val freeStorageData = "\n" + totalFreeStorageString + "\n"+ totalFreeStorageValue + "\n"
        tv_free_storage.text = freeStorageData
    }

    private fun setTotalUsedStorageData(){
        val totalUsedStorageString = applicationContext.resources.getString(R.string.used_storage)
        val totalUsedStorageValue = StorageUtils.convertLongDataToString(2)
        val usedStorageData = "\n" + totalUsedStorageString + "\n"+ totalUsedStorageValue + "\n"
        tv_used_storage.text = usedStorageData
    }

    override fun onResume() {
        super.onResume()

       registerBroadCastReceiverForBattery()

        setTotalStorageData()
        setTotalFreeStorageData()
        setTotalUsedStorageData()
    }

    private fun registerBroadCastReceiverForBattery(){
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED)
        this.registerReceiver(broadcastReceiver, intentFilter)
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent != null) {
                val batteryStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                val batteryStatusString = applicationContext.resources.getString(R.string.battery_status)
                val batteryStatusValue = BatteryUtils.checkBatteryStatus(batteryStatus,this@MainActivity)
                val status = "\n"+batteryStatusString + "\n"+ batteryStatusValue+ "\n"
                tv_battery_status.text = status


                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val batteryPercentageString = applicationContext.resources.getString(R.string.battery_percentage)
                val batteryPercentageValue = BatteryUtils.checkBatteryPercentage(level,scale).toString()
                val percentage = "\n"+batteryPercentageString + "\n"+ batteryPercentageValue+ "\n"
                tv_battery_percentage.text = percentage

            }
        }
    }
}
