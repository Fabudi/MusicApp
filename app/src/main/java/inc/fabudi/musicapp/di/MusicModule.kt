package inc.fabudi.musicapp.di

import android.app.Application
import androidx.media3.exoplayer.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MusicModule {
    @Provides
    @ViewModelScoped
    fun provideExoPlayer(application: Application): ExoPlayer =
        ExoPlayer.Builder(application).build()
}