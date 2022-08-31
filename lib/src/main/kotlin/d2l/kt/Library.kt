package d2l.kt

import d2l.kt.models.Course
import d2l.kt.models.MyOrgUnitInfo
import d2l.kt.models.PagedResultSet
import d2l.kt.routes.CourseRoute

suspend fun main() {
    with(D2LRequest) {
        appID = ""
        appKey = ""
        userID = ""
        userKey = ""
    }
    val data = CourseRoute.getCourses()

    println(data.items[0].orgUnit.name)

    val data2 = CourseRoute.getCourseSemesterInfo(data.items.map { it.orgUnit.id })

    println(data2[0].semester?.name)
}
