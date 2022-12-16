package es.nexcreep.testing.focus5

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import es.nexcreep.testing.focus5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val camposBinding = arrayListOf(binding.fieldA, binding.fieldB)
        camposBinding.forEach {
            it.addTextChangedListener(
                object: TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

                    override fun afterTextChanged(p0: Editable?) {
                        comprobarCampos()
                    }

                }
            )

            it.onFocusChangeListener = View.OnFocusChangeListener {
                    view, b -> changeFocusedTag(view, b)

            }
        }

        binding.checkBtn.setOnClickListener { mutarCampos() }

    }

    private fun comprobarCampos(){
        binding.checkBtn.isEnabled = binding.fieldA.text.isNotEmpty() && binding.fieldB.text.isNotEmpty()
    }

    private fun mutarCampos() {
        binding.fieldA.text = binding.fieldB.text
        binding.fieldB.text.clear()
    }

    private fun changeFocusedTag(v: View, hashFocus: Boolean) {
        if (currentFocus != null){
            binding.focusText.text = currentFocus!!.tag.toString()
        }

    }
}