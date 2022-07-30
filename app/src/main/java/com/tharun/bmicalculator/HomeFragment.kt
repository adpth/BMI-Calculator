package com.tharun.bmicalculator

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tharun.bmicalculator.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var height: Float = 0f
    private var weight: Float = 0f
    private var countWeight = 50
    private var countAge = 19
    private var chosen: Boolean = true
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        initComponents()

        return binding.root
    }

    private fun initComponents() {

        binding.apply {

            Seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    val ht = progress.toString() + resources.getString(R.string.cm)
                    binding.heightTxt.text = ht
                    height = progress.toFloat() / 100
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })

            weightPlus.setOnClickListener {
                binding.weightTxt.text = countWeight++.toString()
            }

            weightMinus.setOnClickListener {
                binding.weightTxt.text = countWeight--.toString()
            }

            agePlus.setOnClickListener {
                binding.age.text = countAge++.toString()
            }

            ageMinus.setOnClickListener {
                binding.age.text = countAge--.toString()
            }

            calculate.setOnClickListener {
                if(!chosen) {
                    if(height.equals(0f)) {
                        Toast.makeText(requireContext(),"Height cannot be Zero, Try again", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        weight = binding.weightTxt.text.toString().toFloat()
                        calculateBMI(age.text.toString())
                    }
                }
                else {
                    Toast.makeText(requireContext(),"Choose your gender", Toast.LENGTH_SHORT).show()
                }
            }

            cardViewMale.setOnClickListener {
                if(chosen) {
                    maleTxt.setTextColor(Color.parseColor("#FFFFFF"))
                    maleTxt.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.male_white, 0, 0)
                    cardViewFemale.isEnabled = false
                    chosen = false

                } else {
                    maleTxt.setTextColor(Color.parseColor("#8D8E99"))
                    maleTxt.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.male, 0, 0)
                    cardViewFemale.isEnabled = true
                    chosen = true
                }
            }

            cardViewFemale.setOnClickListener {
                if(chosen) {
                    femaleTxt.setTextColor(Color.parseColor("#FFFFFF"))
                    femaleTxt.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.female_white, 0, 0)
                    cardViewMale.isEnabled = false
                    chosen = false

                } else {
                    femaleTxt.setTextColor(Color.parseColor("#8D8E99"))
                    femaleTxt.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.female, 0, 0)
                    cardViewMale.isEnabled = true
                    chosen = true
                }
            }
        }
    }


    private fun calculateBMI(age: String) {
        val bmi = weight / (height*height)
        val action = HomeFragmentDirections.actionHomeFragmentToResultFragment(bmi,age)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}