package com.tp.fixedassetaccounting.feature.asset.domain.model

sealed class AssetCategory(val id: Int, val name: String) {
    object Residence : AssetCategory(11, "Residence")
    object Garage : AssetCategory(102, "Garage")
    object SportBuilding : AssetCategory(290, "Sport Building")
    object PowerBoiler : AssetCategory(3, "Power Boiler")
    object GasEngine : AssetCategory(324, "Gas Engine")
    object GasPump : AssetCategory(44, "Gas Pump")
    object Crystalizer : AssetCategory(507, "Crystalizer")
    object TechnicalEquipment : AssetCategory(6, "Technical Equipment")
    object Container : AssetCategory(681, "Container")
    object Car : AssetCategory(741, "Car")
    object Bus : AssetCategory(744, "Bus")
    object Tractor : AssetCategory(746, "Tractor")
    object MobilePhone : AssetCategory(629, "Mobile Phone")
    companion object {
        fun getAll() = listOf(
            Residence,
            Garage,
            SportBuilding,
            PowerBoiler,
            GasEngine,
            GasPump,
            Crystalizer,
            TechnicalEquipment,
            Container,
            Car,
            Bus,
            Tractor,
            MobilePhone
        )
    }
}