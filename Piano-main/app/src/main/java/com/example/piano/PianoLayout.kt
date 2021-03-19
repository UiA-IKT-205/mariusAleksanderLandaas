package com.example.piano

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.piano.data.Note
import com.example.piano.databinding.FragmentPianoBinding
import kotlinx.android.synthetic.main.fragment_piano.view.*
import java.io.File
import java.io.FileOutputStream

class PianoLayout : Fragment() {

    //FULL TONE

    private var _binding:FragmentPianoBinding? = null
    private val binding get() = _binding!!

    private val fullTones = listOf("C","D","E","F","G","A","B","C2","D2","E2","F2","G2")

    //HALF TONE
    private val halfTones = listOf("HC","HD","HF","HG","HA")

    private var score:MutableList<Note> = mutableListOf<Note>() // Score == Noteark?

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
            var startPlay:Long = 0

            fullTonePianoKey.onKeyDown = { note ->
            startPlay = System.nanoTime()
            println("Piano key down $note")
            }

            fullTonePianoKey.onKeyUp ={
                var endPlay = System.nanoTime()
                val note = Note(it,startPlay,endPlay)
                score.add(note)
                println("Piano key up $note")

            }
            ft.add(view.pianoKeys.id,fullTonePianoKey,"note_$orgNoteValue")
        }

        halfTones.forEach { orgNoteValue ->
            val halfTonePianoKey = HalfTonePianoKeyFragment.newInstance(orgNoteValue)
            var startPlay:Long = 0


            halfTonePianoKey.onKeyDown = { note ->
                startPlay = System.nanoTime()
                println("Piano key down $note")
            }
            halfTonePianoKey.onKeyUp ={
                var endPlay = System.nanoTime()
                val note = Note(it,startPlay,endPlay)
                println("Piano key up $note")
                score.add(note)
            }
            ht.add(view.pianoKeys.id,halfTonePianoKey,"note_$orgNoteValue")
        }

        ht.commit()
        ft.commit()


        view.saveScoreBt.setOnClickListener{
            var fileName = view.fileNameTextEdit.text.toString()

            val path = this.activity?.getExternalFilesDir(null)



            if(fileName.isNotEmpty() && path != null){
                fileName = "$fileName.musikk"
                FileOutputStream(File(path,fileName),true).bufferedWriter().use{ writer ->
                score.forEach{
                    writer.write("${it.toString()}\n")
                    }
                }
            }
        }

        // Inflate the layout for this fragment
        return view
    }

}

