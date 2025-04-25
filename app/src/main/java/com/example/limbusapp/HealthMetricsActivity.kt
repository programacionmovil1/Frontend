package com.example.limbusapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.limbusapp.ui.theme.LimbusAppTheme

class HealthMetricsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimbusAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HealthMetricsScreen(
                        onFinishClicked = {
                            // Navigate to profile completed screen
                            val intent = Intent(this, ProfileCompletedActivity::class.java)
                            startActivity(intent)
                            // Clear back stack so user can't go back to form
                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        },
                        onBackClicked = {
                            // Go back to DietaryPreferencesActivity
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealthMetricsScreen(
    onFinishClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var systolic by remember { mutableStateOf("") }
    var diastolic by remember { mutableStateOf("") }

    val primaryPink = Color(0xFFE91E63)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Added top padding to separate from status bar
        Spacer(modifier = Modifier.height(20.dp))

        // Progress bar - now at 100% for step 4 of 4
        LinearProgressIndicator(
            progress = 1.0f, // Fourth and final step
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = primaryPink,
            trackColor = Color.LightGray
        )

        // Left-aligned step indicator
        Text(
            text = "Paso 4 de 4",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            textAlign = TextAlign.Start
        )

        // Title with left alignment and proper spacing
        Text(
            text = "Métricas de salud",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textAlign = TextAlign.Start
        )

        // Subtitle with proper spacing and left alignment
        Text(
            text = "Introduce tus datos si los conoces",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Start
        )

        // Height field
        Text(
            text = "Altura (cm)",
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Start
        )

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            placeholder = { Text("Ej: 165") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryPink,
                unfocusedBorderColor = Color.LightGray
            ),
            shape = RoundedCornerShape(8.dp)
        )

        // Weight field
        Text(
            text = "Peso (kg)",
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Start
        )

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            placeholder = { Text("Ej: 65") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryPink,
                unfocusedBorderColor = Color.LightGray
            ),
            shape = RoundedCornerShape(8.dp)
        )

        // Blood pressure fields
        Text(
            text = "Presión arterial (mmHg)",
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            textAlign = TextAlign.Start
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = systolic,
                onValueChange = { systolic = it },
                placeholder = { Text("Sistólica") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryPink,
                    unfocusedBorderColor = Color.LightGray
                ),
                shape = RoundedCornerShape(8.dp)
            )

            OutlinedTextField(
                value = diastolic,
                onValueChange = { diastolic = it },
                placeholder = { Text("Diastólica") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryPink,
                    unfocusedBorderColor = Color.LightGray
                ),
                shape = RoundedCornerShape(8.dp)
            )
        }

        // Spacer to push buttons to bottom
        Spacer(modifier = Modifier.weight(1f))

        // Finish button - rounded corners matching design
        Button(
            onClick = onFinishClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryPink
            )
        ) {
            Text("Finalizar")
        }

        // Back button - outlined style with rounded corners
        OutlinedButton(
            onClick = onBackClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(50.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = primaryPink
            ),
            border = ButtonDefaults.outlinedButtonBorder.copy(
                brush = androidx.compose.ui.graphics.SolidColor(primaryPink)
            )
        ) {
            Text("Atrás")
        }

        // Small bottom padding
        Spacer(modifier = Modifier.height(16.dp))
    }
}