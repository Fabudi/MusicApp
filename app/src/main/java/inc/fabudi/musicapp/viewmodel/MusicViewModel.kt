package inc.fabudi.musicapp.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import inc.fabudi.musicapp.repository.MusicRepository
import javax.inject.Inject


@HiltViewModel
class MusicViewModel @Inject constructor(private val repository: MusicRepository) : ViewModel() {

}