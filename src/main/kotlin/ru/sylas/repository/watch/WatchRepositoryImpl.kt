package ru.sylas.repository.watch

import ru.sylas.common.Utils.guard
import ru.sylas.common.Utils.loggedTransaction
import ru.sylas.exceptions.BadKeyDeviceException
import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.dataclass.PhoneUser
import ru.sylas.model.dataclass.WatchStats
import ru.sylas.model.requestdataclasses.GameType
import ru.sylas.model.requestdataclasses.PinCode
import ru.sylas.model.tables.watch.WatchKeysT
import ru.sylas.model.tablesDAO.pincode.generatePinCode
import ru.sylas.model.tablesDAO.watch.WatchKeysDao
import ru.sylas.model.tablesDAO.watch.toPhoneUser
import ru.sylas.repository.auth.getKeyDevice
import kotlin.random.Random

class WatchRepositoryImpl:WatchRepository {
    override fun getDevices(): List<PhoneUser> {
        return loggedTransaction {
            WatchKeysDao.all().toList().map {
                it.toPhoneUser()
            }
        }
    }

    override fun getPinCode(key: KeyDevice): PinCode {
        loggedTransaction{
            WatchKeysDao.find { WatchKeysT.keyDevice eq key.keyDevice }.firstOrNull().guard {
                throw BadKeyDeviceException()
            }
        }
        return PinCode(generatePinCode())
    }

    override fun getStats(key: KeyDevice): List<WatchStats> {
        loggedTransaction{
            WatchKeysDao.find { WatchKeysT.keyDevice eq key.keyDevice }.firstOrNull().guard {
                throw BadKeyDeviceException()
            }
        }
       return listOf(
           WatchStats(
               type = "Изучение цифр",
               percent = Random.nextInt(1,100)
           ),
           WatchStats(
               type = "Изучение звуков",
               percent = Random.nextInt(1,100)
           ),
           WatchStats(
               type = "Изучение фигур",
               percent = Random.nextInt(1,100)
           ),
           WatchStats(
               type = "Соответствие цветов",
               percent = Random.nextInt(1,100)
           ),
           WatchStats(
               type = "Счет",
               percent = Random.nextInt(1,100)
           ),
       )

    }
}