package ru.sylas.model.tablesDAO.app

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.sylas.model.dataclass.KeyDevice
import ru.sylas.model.requestdataclasses.NewApp
import ru.sylas.model.requestdataclasses.NewMobile
import ru.sylas.model.tables.app.App
import ru.sylas.model.tables.app.KeyDeviceT
import ru.sylas.model.tables.app.Mobile

class AppDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AppDao>(App)

    var appId by App.appId
    var competitor by App.competitor

}

class MobileDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MobileDao>(Mobile)

    var deviceId by Mobile.deviceId
    var appId by AppDao referencedOn Mobile.appId
    var device by Mobile.device

}

class KeyDeviceDao(id:EntityID<Int>):IntEntity(id){
    companion object : IntEntityClass<KeyDeviceDao>(KeyDeviceT)

    var mobileId by MobileDao referencedOn KeyDeviceT.mobileId
    var keyDevice by KeyDeviceT.keyDevice
}

fun KeyDeviceDao.toKey()= KeyDevice(this.keyDevice)


fun MobileDao.toNewMobile() = NewMobile(
    deviceId = this.deviceId,
    appId = this.appId.appId,
    device = this.device
)

fun AppDao.toNewApp() = NewApp(
    appId, competitor
)