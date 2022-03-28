package ru.sylas.service.tvservice

import ru.sylas.model.dataclass.*
import ru.sylas.model.requestdataclasses.NewTV

class TVServiceImpl:TVService {
    override fun getMovie(): Movie {
        return Movie("video.mp4", listOf(Sector(
            "stage1.mp4",
            listOf(Option("",true))
        )))
    }

    override fun sendStat(key: KeyDevice, stat: TVStats) {

    }

    override fun getStat(): List<Statistic> {
        return emptyList()
    }

    override fun regApp(newTV: NewTV): KeyDevice {
        return KeyDevice("1fsdaf")
    }
}