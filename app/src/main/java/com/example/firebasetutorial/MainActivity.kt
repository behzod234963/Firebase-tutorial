package com.example.firebasetutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasetutorial.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    private lateinit var firebaseReference:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseReference=FirebaseDatabase.getInstance().getReference("test")
        initView()


    }

    private fun initView() {

        binding.apply {

            tvText.setOnClickListener {

                ontvTextClick()
                getData()

            }

            getData()

        }

    }

    private fun getData() {

        firebaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                val value=snapshot.getValue(true)
                binding.tvText.text=value.toString()

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }

    private fun ontvTextClick() {


//        Saving data in FireBase DataBase
        firebaseReference.setValue("Behzod").
                addOnCompleteListener {

                    Toast.makeText(this, "the data saved successfully", Toast.LENGTH_SHORT).show()
                    
                }

    }
}