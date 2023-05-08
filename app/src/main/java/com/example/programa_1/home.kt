package com.example.programa_1

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.example.programa_1.databinding.ActivityHomeBinding

import java.text.SimpleDateFormat
import java.time.Year
import java.util.*


class home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        if (bundle != null) {
            val name = bundle.getString("name", "")
            val lastname = bundle.getString("lastname", "")
            val date = bundle.getString("date", "")
            val mail = bundle.getString("email", "")
            val number = bundle.getString("number", "")
            val major = bundle.getString("major", "")

            val delid = date.split("/")
            val age:Int = calculAge(delid[2], delid[1], delid[0])
            val zodiac:String = zodiac(delid[0], delid[1])
            val chinese:String = chinese(delid[2])

            binding.tvNameUserResult.text = "$name $lastname"
            binding.tvAgeUserResult.text = resources.getString(R.string.tvAgeCalUser, age)
            binding.tvZodiacalUserResult.text = zodiac
            binding.tvChineseUserResult.text = chinese
            binding.tvEmailUserResult.text = mail
            binding.tvNoCuentaUserResult.text = number
            selectMajor(binding.imageView, major)
            Log.d("LOGTAG", major)

        }
    }
    fun calculAge(year: String, month:String, day:String): Int {
        val db = "$year-$month-$day"
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) //Date Format
        val date = df.parse(db)
        val dateT = Calendar.getInstance().time //Today.
        val dif = dateT.time - date.time
        return (dif / (1000 * 60 * 60 * 24 * 365.25)).toInt()
    }

    fun zodiac(dd:String, mm:String): String {
        val dd:Int = dd.toInt()
        val mm:Int = mm.toInt()

        return when {
            (dd >= 21 && mm == 3) || (dd <= 20 && mm == 4) -> resources.getString(R.string.aries)
            (dd >= 21 && mm == 4) || (dd <= 20 && mm == 5) -> resources.getString(R.string.tauro)
            (dd >= 21 && mm == 5) || (dd <= 21 && mm == 6) -> resources.getString(R.string.geminis)
            (dd >= 22 && mm == 6) || (dd <= 22 && mm == 7) -> resources.getString(R.string.cancer)
            (dd >= 23 && mm == 7) || (dd <= 23 && mm == 8) -> resources.getString(R.string.leo)
            (dd >= 24 && mm == 8) || (dd <= 23 && mm == 9) -> resources.getString(R.string.virgo)
            (dd >= 24 && mm == 9) || (dd <= 23 && mm == 10) -> resources.getString(R.string.libra)
            (dd >= 24 && mm == 10) || (dd <= 22 && mm == 11) -> resources.getString(R.string.escorpio)
            (dd >= 23 && mm == 11) || (dd <= 21 && mm == 12) -> resources.getString(R.string.sagitario)
            (dd >= 22 && mm == 12) || (dd <= 20 && mm == 1) -> resources.getString(R.string.capricornio)
            (dd >= 21 && mm == 1) || (dd <= 19 && mm == 2) -> resources.getString(R.string.acuario)
            (dd >= 19 && mm == 2) || (dd <= 20 && mm == 3) -> resources.getString(R.string.piscis)
            else -> resources.getString(R.string.aries)
        }
    }
    fun chinese(yy: String):String {
        return when((yy.toInt() - 1900) % 12) {
            0 -> resources.getString(R.string.rata)
            1 -> resources.getString(R.string.buey)
            2 -> resources.getString(R.string.tigre)
            3 -> resources.getString(R.string.conejo)
            4 -> resources.getString(R.string.dragon)
            5 -> resources.getString(R.string.serpiente)
            6 -> resources.getString(R.string.caballo)
            7 -> resources.getString(R.string.cabra)
            8 -> resources.getString(R.string.mono)
            9 -> resources.getString(R.string.gallo)
            10 -> resources.getString(R.string.perro)
            11 -> resources.getString(R.string.cerdo)
            else -> resources.getString(R.string.nulo)
        }
    }
    fun selectMajor(iv:ImageView, career:String){

        if (career == "Ingeniería Aeroespacial" || career == "Aerospace Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_aeroespacial",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Ambiental" || career == "Environmental Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_ambiental",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería en Sistemas Biomedicos" || career == "Engineering in Biomedical Systems"){
            val resourceId = iv.context.resources.getIdentifier("ing_biomedicas",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Civil" || career == "Civil Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_civil",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería en Computación" || career == "Computer Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_computacion",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Eléctrica Electrónica" ||
            career == "Electronic Electrical Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_electrico_electronico",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Geofísica" || career == "Geophysical Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_geofisica",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Geomática" || career == "Geomatics Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_geomatica",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Geológica" || career == "Geological Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_geologica",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Industrial" || career == "Industrial Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_industrial",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Mecánica" || career == "Mechanical Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_mecanica",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Mecatrónica" || career == "Mechatronics Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_mecatronico",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería de Minas y Metalurgia" ||
            career == "Mining and Metallurgical Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_minas_metalurgia",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Petrolera" || career == "Petroleum Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_petrolera",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if(career == "Ingeniería en Telecomunicaciones" || career == "Telecommunications Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ing_telecomunicaciones",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
    }

    override fun onResume() {
        super.onResume()

        // Si el objeto MediaPlayer no es nulo, reiniciarlo
        if (mediaPlayer != null) {
            mediaPlayer!!.seekTo(0)
            mediaPlayer!!.start()
        }
    }

    override fun onStart() {
        super.onStart()

        mediaPlayer = MediaPlayer.create(this, R.raw.music2)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }
}