package org.ascolto.onlus.geocrowd19.android.models.survey.raw

import org.ascolto.onlus.geocrowd19.android.models.survey.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RawQuestion(
    @field:Json(name = "id") val id: QuestionId,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "widget") val widget: RawWidget,
    @field:Json(name = "frequency") val frequency: Int,
    @field:Json(name = "only_when") val showConditions: List<RawConditionItem>,
    @field:Json(name = "state_updater") val healthStateUpdaters: List<RawHealthStateUpdaterItem>,
    @field:Json(name = "jump") val jumps: List<RawJumpItem>
) {
    fun question() = Question(
        id = id,
        title = title,
        description = description,
        widget = widget.widget(),
        frequency = frequency,
        showCondition = Condition(showConditions.map { item -> item.conditionItem() }),
        healthStateUpdater = HealthStateUpdater(healthStateUpdaters.map { it.healthStateUpdaterItem() }),
        jump = Jump(jumps.map { item -> item.jumpItem() })
    )
}