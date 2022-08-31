package d2l.kt.models

import io.ktor.http.*
import kotlinx.serialization.*
import kotlinx.serialization.encoding.*
import java.net.URL
import java.util.*


data class Course(
    val info: MyOrgUnitInfo
) {
    val id: Int = info.orgUnit.id
    val isPinned: Boolean = info.pinDate != null
}

data class MyOrgUnitInfo(
    val orgUnit: OrgUnitInfo,
    val access: Access,
    val pinDate: String?
)

data class Access(
    val isActive: Boolean,
    val startDate: String?,
    val endDate: String?,
    val canAccess: Boolean,
    val classlistRoleName: String?,
    val lISRoles: List<String>?,
    val lastAccessed: String?
)

data class OrgUnitInfo (
    val id: Int,
    val type: OrgUnitTypeInfo,
    val name: String,
    val code: String,
    val homeUrl: URL,
    val imageUrl: URL
)

data class OrgUnitTypeInfo(
    val id: Int,
    val code: String,
    val name: String
)