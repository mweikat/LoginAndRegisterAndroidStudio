package teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor.reqandres

data class LoginResponse(
    //val user: User
    val name: String,
    val lastName: String,
    val email :String,
    val token:String,
    val status:String,
    val message: String
)
