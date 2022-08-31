package d2l.kt.routes

import d2l.kt.D2LService
import d2l.kt.fetch
import d2l.kt.models.Course
import d2l.kt.models.CourseParent
import d2l.kt.models.MyOrgUnitInfo
import d2l.kt.models.PagedResultSet
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*

object CourseRoute {
    suspend fun getCourses(): PagedResultSet<MyOrgUnitInfo> {
        return fetch {
            url {
                path("enrollments", "myenrollments/")
                parameters.appendAll("sortBy", listOf("-StartDate", "-PinDate"))
                parameters.append("orgUnitTypeId", "3")
            }
        }
    }

    suspend fun getCourseSemesterInfo(courseIDs: List<Int>): List<CourseParent> {
        val parents: List<List<CourseParent>> = courseIDs.chunked(25).map { IDs ->
            fetch {
                url {
                    path("courses", "parentorgunits")
                    encodedParameters.append("orgUnitIdsCSV", IDs.joinToString(","))
                }
            }
        }
        return parents.flatten()
    }
}