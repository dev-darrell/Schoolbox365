package om.GadsMobileEdu22.Schoolbox365.core.data

import kotlinx.android.parcel.Parcelize

data class User(
        var name: String = "",
        var emailAddress: String = "",
        var userType: String = "",
        var userId: String = "",
        var userSex: String = "",
        var userFaculty: String = "",
        var userDepartment: String = "",
        var userProgramOfStudy: String = ""

)

enum class AuthenticationProgress {
    Loading,
    Done,
    AuthError
}


data class News(
        var id: String = "",
        var image: String = "",
        var tittle: String = "",
        var description: String = "")

