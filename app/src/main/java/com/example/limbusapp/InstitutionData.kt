
package com.example.limbusapp.data

data class Institution(
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val patientCount: Int,
    val personInCharge: String,
    val ranking: Int
)

/**
 * Un repositorio simple en memoria para instituciones.
 * En una aplicación real, esto usaría la base de datos Room u otro almacenamiento persistente.
 */
object InstitutionRepository {
    private val institutions = mutableListOf<Institution>(
        Institution(
            id = 1,
            name = "Hospital Central",
            address = "Av. Principal 123",
            city = "Ciudad",
            patientCount = 1500,
            personInCharge = "Dr. Juan Pérez",
            ranking = 1
        ),
        Institution(
            id = 2,
            name = "Clínica San Miguel",
            address = "Calle Secundaria 456",
            city = "Ciudad",
            patientCount = 750,
            personInCharge = "Dra. María Rodríguez",
            ranking = 2
        ),
        Institution(
            id = 3,
            name = "Centro Médico Regional",
            address = "Paseo de la Salud 789",
            city = "Ciudad",
            patientCount = 2300,
            personInCharge = "Dr. Carlos Sánchez",
            ranking = 3
        )
    )

    // Contador para generar nuevos IDs
    private var idCounter = institutions.maxOfOrNull { it.id } ?: 0

    fun getInstitutions(): List<Institution> {
        return institutions.toList()
    }

    fun getInstitutionById(id: Int): Institution? {
        return institutions.find { it.id == id }
    }

    fun addInstitution(institution: Institution) {
        institutions.add(institution)
        updateRankings()
    }

    fun updateInstitution(institution: Institution) {
        val index = institutions.indexOfFirst { it.id == institution.id }
        if (index != -1) {
            institutions[index] = institution
            updateRankings()
        }
    }

    fun deleteInstitution(id: Int) {
        institutions.removeIf { it.id == id }
        updateRankings()
    }

    fun getNextId(): Int {
        return ++idCounter
    }

    private fun updateRankings() {
        // Ordenar por número de pacientes (descendente) y actualizar rankings
        val sortedList = institutions.sortedByDescending { it.patientCount }
        sortedList.forEachIndexed { index, institution ->
            val updatedRanking = index + 1
            if (institution.ranking != updatedRanking) {
                // Actualizar solo si el ranking ha cambiado
                val institutionIndex = institutions.indexOfFirst { it.id == institution.id }
                if (institutionIndex != -1) {
                    institutions[institutionIndex] = institution.copy(ranking = updatedRanking)
                }
            }
        }
    }
}