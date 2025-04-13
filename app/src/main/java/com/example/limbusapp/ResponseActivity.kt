package com.example.limbusapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.limbusapp.data.InstitutionRepository
import com.example.limbusapp.data.Institution
import com.example.limbusapp.ui.theme.LimbusAppTheme
import kotlinx.coroutines.launch

class RegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Verificar si estamos editando una institución existente
        val institutionId = intent.getIntExtra("institution_id", -1)
        val isEditing = institutionId != -1

        setContent {
            LimbusAppTheme {
                RegistrationScreen(
                    institutionId = institutionId,
                    isEditing = isEditing,
                    onBackClick = { finish() },
                    onRegistrationComplete = { finish() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    institutionId: Int = -1,
    isEditing: Boolean = false,
    onBackClick: () -> Unit,
    onRegistrationComplete: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // Si estamos editando, obtener los datos de la institución existente
    val institution = if (isEditing) {
        InstitutionRepository.getInstitutionById(institutionId)
    } else null

    var name by remember { mutableStateOf(institution?.name ?: "") }
    var address by remember { mutableStateOf(institution?.address ?: "") }
    var city by remember { mutableStateOf(institution?.city ?: "") }
    var personInCharge by remember { mutableStateOf(institution?.personInCharge ?: "") }
    var patientCountText by remember { mutableStateOf(institution?.patientCount?.toString() ?: "") }

    var nameError by remember { mutableStateOf(false) }
    var addressError by remember { mutableStateOf(false) }
    var cityError by remember { mutableStateOf(false) }
    var personInChargeError by remember { mutableStateOf(false) }
    var patientCountError by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Text(if (isEditing) "Editar Institución" else "Registro de Institución")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    nameError = false
                },
                label = { Text("Nombre de Institución*") },
                modifier = Modifier.fillMaxWidth(),
                isError = nameError,
                supportingText = if (nameError) {
                    { Text("Este campo es obligatorio") }
                } else null
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                    addressError = false
                },
                label = { Text("Dirección*") },
                modifier = Modifier.fillMaxWidth(),
                isError = addressError,
                supportingText = if (addressError) {
                    { Text("Este campo es obligatorio") }
                } else null
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = city,
                onValueChange = {
                    city = it
                    cityError = false
                },
                label = { Text("Ciudad*") },
                modifier = Modifier.fillMaxWidth(),
                isError = cityError,
                supportingText = if (cityError) {
                    { Text("Este campo es obligatorio") }
                } else null
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = personInCharge,
                onValueChange = {
                    personInCharge = it
                    personInChargeError = false
                },
                label = { Text("Personal a Cargo*") },
                modifier = Modifier.fillMaxWidth(),
                isError = personInChargeError,
                supportingText = if (personInChargeError) {
                    { Text("Este campo es obligatorio") }
                } else null
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = patientCountText,
                onValueChange = {
                    patientCountText = it
                    patientCountError = false
                },
                label = { Text("Número de Pacientes*") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = patientCountError,
                supportingText = if (patientCountError) {
                    { Text("Ingrese un número válido") }
                } else null
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    // Validar campos
                    nameError = name.isBlank()
                    addressError = address.isBlank()
                    cityError = city.isBlank()
                    personInChargeError = personInCharge.isBlank()

                    val patientCount = patientCountText.toIntOrNull()
                    patientCountError = patientCount == null

                    if (!nameError && !addressError && !cityError && !personInChargeError && !patientCountError) {
                        if (isEditing && institution != null) {
                            // Actualizar institución existente
                            val updatedInstitution = institution.copy(
                                name = name,
                                address = address,
                                city = city,
                                personInCharge = personInCharge,
                                patientCount = patientCount!!
                            )
                            InstitutionRepository.updateInstitution(updatedInstitution)
                            scope.launch {
                                snackbarHostState.showSnackbar("Institución actualizada con éxito")
                            }
                        } else {
                            // Agregar nueva institución
                            val newInstitution = Institution(
                                id = InstitutionRepository.getNextId(),
                                name = name,
                                address = address,
                                city = city,
                                personInCharge = personInCharge,
                                patientCount = patientCount!!,
                                ranking = InstitutionRepository.getInstitutions().size + 1
                            )
                            InstitutionRepository.addInstitution(newInstitution)
                            scope.launch {
                                snackbarHostState.showSnackbar("Institución registrada con éxito")
                            }
                        }

                        // Volver a la pantalla anterior después de un breve retraso
                        scope.launch {
                            kotlinx.coroutines.delay(1000)
                            onRegistrationComplete()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF304FFE)
                )
            ) {
                Text(if (isEditing) "Actualizar" else "Registrar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    LimbusAppTheme {
        RegistrationScreen(
            onBackClick = {},
            onRegistrationComplete = {}
        )
    }
}