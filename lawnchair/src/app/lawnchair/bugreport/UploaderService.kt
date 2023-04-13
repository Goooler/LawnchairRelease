package app.lawnchair.bugreport

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.android.launcher3.R
import java.util.LinkedList
import java.util.Queue

class UploaderService : Service() {
    private val uploadQueue: Queue<BugReport> = LinkedList()

    override fun onBind(intent: Intent): IBinder {
        TODO("not implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent == null) return START_REDELIVER_INTENT
        uploadQueue.offer(intent.getParcelableExtra("report"))
        sendBroadcasts()
        stopSelf()
        return START_STICKY
    }

    private fun sendBroadcasts() {
        while (uploadQueue.isNotEmpty()) {
            val report = uploadQueue.poll()!!
            sendBroadcast(
                Intent(this@UploaderService, BugReportReceiver::class.java)
                    .setAction(BugReportReceiver.UPLOAD_COMPLETE_ACTION)
                    .putExtra("report", report)
            )
        }
    }

    override fun onCreate() {
        super.onCreate()

        Log.d("DUS", "onCreate")

        startForeground(101, NotificationCompat.Builder(this, BugReportReceiver.statusChannelId)
            .setSmallIcon(R.drawable.ic_bug_notification)
            .setContentTitle(getString(R.string.dogbin_uploading))
            .setColor(ContextCompat.getColor(this, R.color.bugNotificationColor))
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .build())
    }
}
