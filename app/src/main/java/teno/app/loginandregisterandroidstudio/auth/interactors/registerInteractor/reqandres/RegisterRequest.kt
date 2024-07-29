package teno.app.loginandregisterandroidstudio.auth.interactors.registerInteractor.reqandres

data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String,
    val lastName: String
)
