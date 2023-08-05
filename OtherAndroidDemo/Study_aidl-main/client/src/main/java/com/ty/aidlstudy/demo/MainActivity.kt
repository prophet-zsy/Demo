package com.ty.aidlstudy.demo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ty.aidlstudy.NetByte
import com.ty.aidlstudy.UserMessage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val tag = this.javaClass.simpleName
    private var list: LinkedList<String> = LinkedList()
    private var adapter: ArrayAdapter<String>? = null;
    private var netService: NetByte? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setClick()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        list_view?.adapter = adapter;

        val intent2 = Intent().setComponent(ComponentName(
            "com.ty.aidlstudy.demo",
            "com.ty.aidlstudy.demo.NetService"))
        bindService(intent2, mConnection2, Context.BIND_AUTO_CREATE)
    }

    private fun setClick() {
        send.setOnClickListener { v ->
            if ("" == content?.text.toString().trim { it <= ' ' }) {
                Toast.makeText(this, "请输入信息", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (netService == null) {
                Toast.makeText(this, "服务正在启动...", Toast.LENGTH_SHORT).show()
                val intent1 = Intent().setComponent(ComponentName(
                    "com.ty.aidlstudy.size",
                    "com.ty.aidlstudy.size.NetService"))
                bindService(intent1, mConnection1, Context.BIND_AUTO_CREATE)
                return@setOnClickListener
            }

            try {
                netService?.sendMessage(UserMessage(content?.text.toString().trim { it <= ' ' }))
                list.addFirst("client:" + content?.text.toString().trim { it <= ' ' })
                content?.setText("")
                adapter?.notifyDataSetChanged()
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    private val mConnection1 = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.e(tag, "demo mConnection1 onServiceConnected")
            netService = NetByte.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName) {

        }
    }

    private val mConnection2 = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            Log.e(tag, "demo mConnection2 onServiceConnected")
            val innerIBinder = service as NetService.InnerIBinder
            val remoteService = innerIBinder.service as NetService
            remoteService.setCallBack { message ->
                list.addFirst("server:" + message.messageContent)
                Log.d("demo showMessage impl", Thread.currentThread().name)
                runOnUiThread(Runnable {
                    adapter?.notifyDataSetChanged()
                })
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
        }
    }
}