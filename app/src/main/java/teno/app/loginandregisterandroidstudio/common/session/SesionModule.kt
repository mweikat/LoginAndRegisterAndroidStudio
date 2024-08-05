package teno.app.loginandregisterandroidstudio.common.session

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SesionModule {

    @Binds
    abstract fun bindUserLogged(
        userLoggedImpl: UserLoggedImpl
    ):UserLogged
}