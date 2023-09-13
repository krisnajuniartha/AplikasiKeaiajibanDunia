package com.example.aplikasikeaiajibandunia

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class KeajaibanDetail : AppCompatActivity() {
    companion object {
        const val EXTRA_KEAJAIBAN = "extra_keajaiban"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_keajaiban_detail)

        val judulKeajaiban: TextView = findViewById(R.id.text_judul)
        val deskrispi: TextView = findViewById(R.id.text_deskripsi)
        val gambarKeajaiban: ImageView = findViewById(R.id.image_keajaiban)
        val share: Button = findViewById(R.id.aksi_share)

        val keajaiban = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra(EXTRA_KEAJAIBAN, Keajaiban:: class.java)
        }else{
            intent.getParcelableExtra(EXTRA_KEAJAIBAN)
        }

        val judulKeaj = keajaiban?.name.toString()
        judulKeajaiban.text = judulKeaj

        val deskripsitem = keajaiban?.description.toString()
        deskrispi.text = deskripsitem

        val foto = keajaiban?.photo
        Glide
            .with(this)
            .load(foto)
            .centerCrop()
            .into(gambarKeajaiban)

        share.setOnClickListener{
            val mengirimInten: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Anda membaca\nJudul: ${keajaiban?.name.toString()}")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(mengirimInten, null)
            startActivity(shareIntent)
        }

    }
}