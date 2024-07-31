package teno.app.loginandregisterandroidstudio.auth.interactors

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import teno.app.loginandregisterandroidstudio.auth.interactors.forgotPass.ForgotPassInteractor
import teno.app.loginandregisterandroidstudio.auth.interactors.forgotPass.ForgotPassInteractorImpl
import teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor.LoginInteractor
import teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor.LoginInteractorImpl
import teno.app.loginandregisterandroidstudio.auth.interactors.registerInteractor.RegisterInteractor
import teno.app.loginandregisterandroidstudio.auth.interactors.registerInteractor.RegisterInteractorImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class InteractorModule {

    @Binds
    abstract fun bindInteractorServiceLogin(
        loginInteractorImpl: LoginInteractorImpl
    ):LoginInteractor

    @Binds
    abstract fun bindInteractorServiceRegister(
        registerInteractorImpl: RegisterInteractorImpl
    ):RegisterInteractor

    @Binds
    abstract fun bindInteractorServiceForgotPass(
        forgotPassInteractorImpl: ForgotPassInteractorImpl
    ):ForgotPassInteractor
}