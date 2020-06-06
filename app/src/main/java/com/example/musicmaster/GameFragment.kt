package com.example.musicmaster

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.musicmaster.databinding.FragmentGameBinding
import java.lang.Thread.sleep
import kotlin.random.Random

class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding

    private lateinit var mp1 : MediaPlayer
    private lateinit var mp2 : MediaPlayer
    private lateinit var mp3 : MediaPlayer
    private lateinit var mp4 : MediaPlayer
    private lateinit var mp5 : MediaPlayer
    private lateinit var mp6 : MediaPlayer
    private lateinit var mp7 : MediaPlayer

    //var checkstr = ""
    //var playstr = ""
    //var score = 0

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState!=null){
            score = savedInstanceState.getInt("Score")
        }
    }*/



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,
            R.layout.fragment_game,container,false)

        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        binding.gameViewModel = viewModel
        binding.setLifecycleOwner(this)


        createMp()
       // binding.textScore.setText(score.toString())
        binding.button1.setOnClickListener{
            mp1.start()
            viewModel.checkstr = viewModel.checkstr.plus("0")
        }
        binding.button2.setOnClickListener{
            mp2.start()
            viewModel.checkstr = viewModel.checkstr.plus("1")
        }
        binding.button3.setOnClickListener{
            mp3.start()
            viewModel.checkstr = viewModel.checkstr.plus("2")
        }
        binding.button4.setOnClickListener{
            mp4.start()
            viewModel.checkstr = viewModel.checkstr.plus("3")
        }
        binding.button5.setOnClickListener{
            mp5.start()
            viewModel.checkstr  = viewModel.checkstr.plus("4")
        }
        binding.button6.setOnClickListener{
            mp6.start()
            viewModel.checkstr = viewModel.checkstr.plus("5")
        }
        binding.button7.setOnClickListener{
            mp7.start()
            viewModel.checkstr = viewModel.checkstr.plus("6")
        }

        binding.buttonPlaytune.setOnClickListener{ gamePlay() }

        binding.buttonDone.setOnClickListener{/*view : View ->
            if(checkstr.equals(playstr) && !(checkstr.equals(""))) {
                score = score+1
                binding.textScore.setText(score.toString())
            }
            else{
                view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment(score.toString()))
            }
            checkstr = ""
            playstr = "" */
            viewModel.doneClicked()
        }

        viewModel.done.observe(viewLifecycleOwner, Observer{gameOver->
            if(gameOver) {
                findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment(viewModel.score.value.toString()))
                viewModel.doneComplete()
                }
        })

        return binding.root
    }

   /* override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("Score",score)
    }*/

    fun createMp() {
        mp1 = MediaPlayer.create(context,R.raw.a4)
        mp2 = MediaPlayer.create(context,R.raw.b4)
        mp3 = MediaPlayer.create(context,R.raw.c4)
        mp4 = MediaPlayer.create(context,R.raw.d4)
        mp5 = MediaPlayer.create(context,R.raw.e4)
        mp6 = MediaPlayer.create(context,R.raw.f4)
        mp7 = MediaPlayer.create(context,R.raw.g4)

    }

    fun gamePlay() {
        viewModel.checkstr = ""
        viewModel.playstr = ""
        val mplist = listOf<MediaPlayer>(mp1,mp2,mp3,mp4,mp5,mp6,mp7)
        var size = (viewModel.score.value!! /5) + 1
        val randomValues = List(size) { Random.nextInt(0, 7) }
        for (i in randomValues){
            viewModel.playstr = viewModel.playstr.plus(i.toString())
            mplist.elementAt(i).start()
            sleep(1000)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mp1.release()
        mp2.release()
        mp3.release()
        mp4.release()
        mp5.release()
        mp6.release()
        mp7.release()
    }
}
