package com.liketiger.cloth_up.Auth

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.liketiger.cloth_up.MainActivity
import com.liketiger.cloth_up.R
import com.liketiger.cloth_up.Utils.FirebaseUtils
import kotlinx.android.synthetic.main.activity_my_comin.*
import kotlinx.android.synthetic.main.viewpager_activity.*
import java.io.ByteArrayOutputStream


class MyCominActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_comin)

        auth = FirebaseAuth.getInstance()


        val docRef = db.collection("users").document(auth.currentUser?.uid.toString())


        docRef.get().addOnSuccessListener { documentSnapshot ->

            nickname_area.setText(documentSnapshot.get("nickname").toString())

        }

        logout_button.setOnClickListener {

            auth.signOut()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }

        profile_img.setOnClickListener {

            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                } else {
                    //permission already granted
                    pickImageFromGallery();
                }
            } else {
                //system OS is < Marshmallow
                pickImageFromGallery();
            }

        }





        profile_photo_edit.setOnClickListener {

            // 이미지 업로드


            val bitmap = (profile_img.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            var uploadTask = FirebaseStorage.getInstance().reference
                .child(FirebaseUtils.getUid() + "_profile").putBytes(data)

            uploadTask.addOnFailureListener {
                Toast.makeText(this, "업로드실패", Toast.LENGTH_LONG).show()
            }.addOnSuccessListener {
                Toast.makeText(this, "업로드성공", Toast.LENGTH_LONG).show()
            }

            //이미지 다운로드
            val ref = FirebaseStorage.getInstance().getReference("profile.png")

            ref.downloadUrl
                .addOnCompleteListener(OnCompleteListener { task ->

                    if (task.isSuccessful) {

                        Glide.with(this)
                            .load(task.result)
                            .into(profile_img)

                    } else {
                        Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()

                    }


                })
        }



    }
    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            profile_img.setImageURI(data?.data)
        }
    }
}