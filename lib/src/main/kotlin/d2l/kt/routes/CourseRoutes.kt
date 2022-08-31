package d2l.kt.routes

import d2l.kt.D2LRoute
import d2l.kt.D2LService
import io.ktor.client.request.*
import io.ktor.http.*

sealed class CourseRoute: D2LRoute {

    override val platform: D2LService = D2LService.LP

    class Courses: CourseRoute() {
        override val builder = HttpRequestBuilder.invoke {
            path("enrollments", "myenrollments/")
            parameters.appendAll("sortBy", listOf("-StartDate", "-PinDate"))
            parameters.append("orgUnitTypeId", "3")
        }
    }

    class CourseSemesterInfo(courseIds: List<Int>): CourseRoute() {
        override val builder = HttpRequestBuilder.invoke {
            path("courses", "parentorgunits")
            parameters.append("orgUnitIdsCSV", courseIds.joinToString(","))
        }
    }

}