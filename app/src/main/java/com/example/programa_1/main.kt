package com.example.programa_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.programa_1.databinding.ActivityMainBinding
import android.view.View
import android.widget.Toast
import java.util.regex.Matcher
import java.util.regex.Pattern

class main : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var nameUser:String
    private lateinit var lastNameUser:String
    private lateinit var dateUSer:String
    private lateinit var emailUser:String
    private lateinit var careerUser:String
    private lateinit var noCuentaUser: String
    private lateinit var careerOptions: ArrayAdapter<String>
    private lateinit var careerSelected:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_loading)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Resticcion a 9 digtos del Numero de cuenta
        binding.etNoCuenta.filters= arrayOf<InputFilter>(InputFilter.LengthFilter(9))

        //Carreras
        careerOptions=ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item)

        val majorOptions =resources.getStringArray(R.array.careersSelectSpinner)
        val adapter = ArrayAdapter(this, R.layout.major_items, majorOptions)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        careerOptions.addAll(
            listOf(
                "Carrera",
                "Ingeniería Aeroespacial",
                "Ingeniería Ambiental",
                "Ingeniería Civil",
                "Ingeniería de Minas y Metalurgia",
                "Ingeniería Eléctrica Electrónica",
                "Ingeniería en Computación",
                "Ingeniería en Sistemas Biomédicos",
                "Ingeniería en Telecomunicaciones",
                "Ingeniería Geofísica",
                "Ingeniería Geológica",
                "Ingeniería Geomática",
                "Ingeniería Industrial",
                "Ingeniería Mecánica",
                "Ingeniería Mecatrónica",
                "Ingeniería Petrolera"
            )
        )

        binding.spCareers.contentDescription = "Carrera"
        binding.spCareers.onItemSelectedListener = this
        binding.spCareers.adapter = adapter

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        careerSelected = careerOptions.getItem(position).toString()
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun mostrarDatePicker(view: View) {
        val date = datePicker { yy, mm, dd -> setDate(yy, mm, dd)}
        date.show(supportFragmentManager, "DatePicker")
    }

    private fun setDate(yy:Int, mm:Int, dd:Int) {
        binding.etDate.setText("$dd/$mm/$yy")
    }

    fun validateEmail(email: String): Boolean {
        var pattern_email: Pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        var com: Matcher = pattern_email.matcher(email)
        return com.find()
    }

    fun click(view: View) {

        var verifyData:Boolean = true

        nameUser = binding.etNameInput.text.toString()
        lastNameUser = binding.etLastName.text.toString()
        dateUSer = binding.etDate.text.toString()
        emailUser = binding.etEmail.text.toString()
        noCuentaUser = binding.etNoCuenta.text.toString()
        careerUser = careerSelected

        val intent = Intent(this, home::class.java)
        val bundle = Bundle()

        //Nombre
        if (nameUser.isNotEmpty()){
            bundle.putString("name", nameUser)
        }else {
            verifyData = false
            binding.etNameInput.error = resources.getString(R.string.emptyNameInput)
            Toast.makeText(
                this, resources.getString(R.string.toastNameInput),
                Toast.LENGTH_SHORT
            ).show()
        }

        // Apellido
        if (lastNameUser.isNotEmpty()){
            bundle.putString("lastname", lastNameUser)
        }else{
            verifyData = false
            binding.etLastName.error = resources.getString(R.string.emptyLastNameInput)
            Toast.makeText(this, resources.getString(R.string.toastLastNameInput),
                Toast.LENGTH_SHORT
            ).show()
        }

        //Fecha de nacimiento.
        if (dateUSer.isNotEmpty()){
            bundle.putString("date",dateUSer)

        }else{
            verifyData = false
            binding.etDate.error = resources.getString(R.string.emptyDateInput)
            Toast.makeText(this, resources.getString(R.string.toastDateInput),
                Toast.LENGTH_SHORT
            ).show()
        }

        //Correo eletrónico.
        if (emailUser.isNotEmpty()){
            Log.d("LOGTAG", emailUser)

            if (validateEmail(emailUser)) {
                bundle.putString("email", emailUser)
            } else {
                verifyData = false
                binding.etEmail.error = resources.getString(R.string.emptyEmailInput)
                Toast.makeText(this, resources.getString(R.string.toastEmailInvalid),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }else{
            verifyData = false
            binding.etEmail.error = resources.getString(R.string.emptyEmailInput)
            Toast.makeText(this, resources.getString(R.string.toastEmailInput),
                Toast.LENGTH_SHORT
            ).show()
        }


        //Número de cuenta.
        if (noCuentaUser.isNotBlank()){
            if (noCuentaUser.length != 9) {
                verifyData = false
                Toast.makeText(this, resources.getString(R.string.toastNoCuentaInvalid1),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                bundle.putString("number", noCuentaUser)
            }
        } else {
            verifyData = false
            binding.etNoCuenta.error = resources.getString(R.string.emptyNoCuentaInput)
            Toast.makeText(this, resources.getString(R.string.toastNoCuentaInvalid1),
                Toast.LENGTH_SHORT
            ).show()
        }

        //Carrera seleccionada.
        when (careerUser) {
            "Carrera" -> {
                verifyData = false
                Toast.makeText(this, resources.getString(R.string.toastCareerInvalid),
                    Toast.LENGTH_SHORT
                ).show()
            }
            "Major" -> {
                verifyData = false
                Toast.makeText(this, resources.getString(R.string.toastCareerInvalid),
                    Toast.LENGTH_SHORT).show()
            }
            else -> bundle.putString("major", careerUser)
        }


        if (verifyData) {
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}