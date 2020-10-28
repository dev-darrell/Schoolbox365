  package om.GadsMobileEdu22.Schoolbox365.core.data

data class User(
        var name: String = "",
        var emailAddress : String = "",
        var userType: String = "",
        var userId: String = "",
        var userSex: String = "",
        var userFaculty: String = "",
        var userDepartment: String = "",
        var userProgramOfStudy: String = ""

)

  sealed class AuthenticationProgress {
      object Loading : AuthenticationProgress()
      object Done : AuthenticationProgress()
      data class Error(var message: String?) : AuthenticationProgress()
  }

