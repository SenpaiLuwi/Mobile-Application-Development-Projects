package com.example.act11firebaserealtimedatabase


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.orhanobut.dialogplus.DialogPlus
import com.orhanobut.dialogplus.ViewHolder
import de.hdodenhof.circleimageview.CircleImageView

class MainAdapter(options: FirebaseRecyclerOptions<MainModel>) :
    FirebaseRecyclerAdapter<MainModel, MainAdapter.MyViewHolder>(options) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: MainModel) {
        holder.name.text = model.B_name
        holder.number.text = model.C_number
        holder.power.text = model.D_power

        Glide.with(holder.img.context)
            .load(model.E_imageurl)
            .placeholder(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark)
            .circleCrop()
            .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
            .into(holder.img)

        holder.edit.setOnClickListener {
            Toast.makeText(holder.name.context, "Editing the Character", Toast.LENGTH_SHORT).show()

            val dialogPlus = DialogPlus.newDialog(holder.img.context)
                .setContentHolder(ViewHolder(R.layout.show_update))
                .setExpanded(true, 1200)
                .create()

            val view = dialogPlus.holderView
            val name: EditText = view.findViewById(R.id.edit_char_name)
            val bounty:EditText = view.findViewById(R.id.edit_char_number)
            val power:EditText = view.findViewById(R.id.edit_char_power)
            val img:EditText = view.findViewById(R.id.edit_char_image)
            val btnUpdate:Button = view.findViewById(R.id.btn_id_update)

            name.setText(model.B_name)
            bounty.setText(model.C_number)
            power.setText(model.D_power)
            img.setText(model.E_imageurl)

            dialogPlus.show()
            btnUpdate.setOnClickListener {
                if (name.text.isEmpty()){
                    validateMsg("Name", holder.name.context)
                }else if (bounty.text.isEmpty()){
                    validateMsg("Bounty", holder.name.context)
                }else if (power.text.isEmpty()){
                    validateMsg("Power", holder.name.context)
                }else if (img.text.isEmpty()){
                    validateMsg("Picture", holder.name.context)
                }else{
                    val map = HashMap<String, Any>()
                    map["B_name"] = name.text.toString()
                    map["C_number"] = bounty.text.toString()
                    map["D_power"] = power.text.toString()
                    map["E_imageurl"] = img.text.toString()

                    FirebaseDatabase.getInstance().reference.child("values")
                        .child(getRef(position).key!!).updateChildren(map)
                        .addOnSuccessListener {
                            Toast.makeText(holder.name.context, "Record Update Successfully", Toast.LENGTH_SHORT).show()
                            dialogPlus.dismiss()
                        }
                        .addOnFailureListener {
                            Toast.makeText(holder.name.context, "Record Update Unsuccessfully", Toast.LENGTH_SHORT).show()
                            dialogPlus.dismiss()
                        }
                }
            }
        }

        holder.delete.setOnClickListener {
            Toast.makeText(holder.name.context, "Deleting the Character", Toast.LENGTH_SHORT).show()
            val builder = AlertDialog.Builder(holder.name.context)
            builder.setTitle("Attention")
            builder.setMessage("Are you sure you want to delete this record?")
            builder.setPositiveButton("Yes"){ _, _ ->
                FirebaseDatabase.getInstance().reference.child("values")
                    .child(getRef(position).key!!).removeValue()
            }
            builder.setNegativeButton("No"){ dialog, _ ->
                Toast.makeText(holder.name.context, "Cancelled", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
            builder.show()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_character, parent, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: CircleImageView = itemView.findViewById(R.id.img1_id)
        var name: TextView = itemView.findViewById(R.id.name_char_id)
        var number: TextView = itemView.findViewById(R.id.number_char_id)
        var power: TextView = itemView.findViewById(R.id.power_char_id)
        var edit: Button = itemView.findViewById(R.id.btn_edit)
        var delete: Button = itemView.findViewById(R.id.btn_delete)
    }

    private fun validateMsg(field: String, context: Context?) {
        Toast.makeText(context,"Please enter the Characters $field", Toast.LENGTH_SHORT).show()
    }
}
