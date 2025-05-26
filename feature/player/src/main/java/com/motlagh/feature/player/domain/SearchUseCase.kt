package com.motlagh.feature.player.domain

import com.motlagh.core.utils.result.resultOf
import com.motlagh.domain.video.VideoItemDomainModel

internal fun interface VideoDetailUseCase : suspend (String) -> Result<VideoItemDomainModel>


internal suspend fun videoDetailUseCaseImpl(
    id: String,
    repository: VideoDetailRepository,
): Result<VideoItemDomainModel> =
    resultOf {
        repository.getVideoById(id)
            ?: throw Exception("Video not found") //todo this hard coded exception could a custom exception.
    }
