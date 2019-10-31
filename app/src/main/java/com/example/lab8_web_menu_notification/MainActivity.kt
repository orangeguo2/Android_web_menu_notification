package com.example.lab8_web_menu_notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebViewClient
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val id = "my_channel_02"

        val name = getString(R.string.abc_action_bar_home_description)
        val description = getString(R.string.abc_action_bar_home_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel(id, name, importance)

        mChannel.description = description
        mChannel.enableLights(true)

        mChannel.lightColor= Color.RED
        mChannel.enableVibration(true)
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.createNotificationChannel(mChannel)
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        mywebview.setWebViewClient(WebViewClient())
        when(item?.itemId){
            R.id.item1 -> {
                mywebview.loadUrl("http://www.androidatc.com")
                val CHANNEL_ID = "my_channel_02"

                val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.notification_icon_background)
                    .setContentTitle("Android ATC Notificaiton")
                    .setContentText("check Android ATC NEW Course!!")

                val resultIntent = Intent(this, ResultActivity::class.java)
                val stackBuilder = TaskStackBuilder.create(this)

                stackBuilder.addNextIntent(resultIntent)
                val resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
                mBuilder.setContentIntent(resultPendingIntent)
                val mNorificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                mNorificationManager.notify(2, mBuilder.build())

                return super.onOptionsItemSelected(item)
            }


            R.id.item2 -> {
                mywebview.loadUrl("http://www.pearsonvue.com/androidatc")
                return super.onOptionsItemSelected(item)
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun gotoweb(view: View){
        mywebview.settings.javaScriptEnabled = true
        mywebview.settings.loadsImagesAutomatically = true
        mywebview.scrollBarStyle=View.SCROLLBARS_INSIDE_OVERLAY

        val url = url_address.text.toString()
        mywebview.setWebViewClient(WebViewClient())

        mywebview.loadUrl(url)


    }
}
