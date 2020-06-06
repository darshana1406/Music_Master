package com.example.musicmaster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class GameViewModel : ViewModel() {

    var checkstr = ""
    var playstr = ""

    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private var _done = MutableLiveData<Boolean>()
    val done : LiveData<Boolean>
        get() = _done

    init {
        _score.value = 0
    }

    fun doneClicked() {
        if(checkstr.equals(playstr) && !(checkstr.equals(""))) {
            _score.value = _score.value!! +1
            //binding.textScore.setText(score.toString())
        }
        else{
           // view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment(score.toString()))
            _done.value = true
        }
        checkstr = ""
        playstr = ""
    }

    fun doneComplete() {
        _done.value = false
    }

}

