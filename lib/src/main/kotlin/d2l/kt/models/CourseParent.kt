package d2l.kt.models

data class CourseParent(
    val courseOfferingId: String,
    val semester: BaseOrgUnit?,
    val department: BaseOrgUnit?
)

data class BaseOrgUnit(
    val identifier: String,
    val name: String,
    val code: String
)