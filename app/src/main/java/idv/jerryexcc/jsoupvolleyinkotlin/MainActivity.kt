package idv.jerryexcc.jsoupvolleyinkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requestQueue = Volley.newRequestQueue(this@MainActivity)
        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://udn.com/news/index",
            Response.Listener { response -> //Response Listener 反饋成功後會看到整個網頁的HTML原始碼(String)
                val doc = Jsoup.parse(response)//使用Jsoup將response回來的HTML轉成Document方便直接取節點
                tvShow.text = doc.body().select("h2").text()//把整份HTML中的body取其中的h2標籤中的文字顯示
            },
            Response.ErrorListener { error -> //Error Listener 反饋回來有錯誤的時候執行這個區塊
                Log.d("TAG", "Error: $error")
            }
        )
        //加入到請求佇列
        requestQueue.add(stringRequest)
    }
}
