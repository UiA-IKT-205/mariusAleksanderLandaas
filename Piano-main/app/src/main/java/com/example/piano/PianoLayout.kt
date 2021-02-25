package com.example.piano

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.piano.databinding.FragmentPianoBinding
import kotlinx.android.synthetic.main.fragment_piano.view.*

class PianoLayout : Fragment() {

    //FULL TONE

    private var _binding:FragmentPianoBinding? = null
    private val binding get() = _binding!!

    private val fullTones = listOf("C","D","E","F","G","A","B","C2","D2","E2","F2","G2")

    //HALF TONE
    private val halfTones = listOf("HC","HD","HF","HG","HA")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentPianoBinding.inflate(layoutInflater)
        val view = binding.root

        val fn = childFragmentManager
        val ft = fn.beginTransaction()
        val ht = fn.beginTransaction()


        fullTones.forEach { orgNoteValue ->
            val fullTonePianoKey = FullTonePianoKeyFragment.newInstance(orgNoteValue)

            fullTonePianoKey.onKeyDown = {
            println("Piano key down $it")
            }
            fullTonePianoKey.onKeyUp ={
                    println("Piano key up $it")
            }
            ft.add(view.pianoKeys.id,fullTonePianoKey,"note_$orgNoteValue")
        }

        halfTones.forEach { orgNoteValue ->
            val halfTonePianoKey = HalfTonePianoKeyFragment.newInstance(orgNoteValue)

            halfTonePianoKey.onKeyDown = {
                println("Piano key down $it")
            }
            halfTonePianoKey.onKeyUp ={
                println("Piano key up $it")
            }
            ht.add(view.pianoKeys.id,halfTonePianoKey,"note_$orgNoteValue")
        }

        ht.commit()
        ft.commit()



        // Inflate the layout for this fragment
        return view
    }

}

