/*
 * Copyright (c) 2015-2015 by Shanghai shootbox Information Technology Co., Ltd.
 * Create: 2015/9/15 3:50:38
 * Project: T_Notification
 * File: MainActivity.java
 * Class: MainActivity
 * Module: app
 * Author: yangyankai
 * Version: 1.0
 */

package com.mob.t_notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.a)
		{
			showNormalNotification();
//			showSelfNotification();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}


	private void showNormalNotification() {
		/**  第一步  通过 Notification_Service  拿到   notificationManager  */
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		/**      第二步  声明  notification         */
		Notification        notification ;


		/**    第三部    声明   pendingIntent            */
		Intent intent = new Intent(this,MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);


		/**       第四部   通过 Builder 设置通知各种属性        */
		Notification.Builder builder = new Notification.Builder(MainActivity.this);
		builder.setAutoCancel(true)//
				.setContentTitle("title 02")//
				.setContentText("describe 02")//
				.setContentIntent(pendingIntent)//
				.setSmallIcon(R.mipmap.ic_launcher)//
				.setDefaults(Notification.DEFAULT_VIBRATE)//
				.setWhen(System.currentTimeMillis())//
				.setOngoing(true)//  长期运行
				.setAutoCancel(true)//
		;

		/**      第五步    builder 把设置好的各种属性赋值给 notification          */
		notification = builder.getNotification();


		/**     第六步    显示通知          */
		int notification_ID = 117;
		notificationManager.notify(notification_ID, notification);

	}


/**   没有通过  Builder  */
	private void showSelfNotification() {

		/**  第一步  通过 Notification_Service  拿到   notificationManager  */
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		//自定义下拉视图，比如下载软件时，显示的进度条。
		Notification notification = new Notification();

		notification.icon = R.mipmap.ic_launcher;
		notification.tickerText = "Custom!";

		RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom);
		contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
		contentView.setTextViewText(R.id.text, "Hello, this message is in a custom expanded view");
		notification.contentView = contentView;


		Intent intent = new Intent(this,MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);


		//使用自定义下拉视图时，不需要再调用setLatestEventInfo()方法
		//但是必须定义 contentIntent
		notification.contentIntent = pendingIntent;

		notificationManager.notify(119, notification);

	}
}
